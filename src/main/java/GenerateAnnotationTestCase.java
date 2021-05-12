

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import freemarker.template.Configuration;
import freemarker.template.Template;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

/**
 * @author GC-shaoyongyang
 */
public class GenerateAnnotationTestCase {
/*

{
  arr:[
    {
      field:"membershipId",
      elements:[
        "javax.validation.constraints.Min",
        "javax.validation.constraints.NotNull"
      ]
    }
  ]
}

  * */



  public static JSONArray resolveFieldProperty() throws ClassNotFoundException {

    File[] files = new File("src/main/java/model/").listFiles();

    JSONArray array = new JSONArray();
    for(File f : files) {
      JSONObject classProperty = new JSONObject();

      System.out.println("Get File Entity Object: " + f.getName());

      String className = f.getName().split("\\.")[0];

      Class<?> infoClass = Class.forName("model." + className);

      Field[] fileds = infoClass.getDeclaredFields();

      classProperty.put("className", className);

      for (Field field : fileds) {
        JSONObject fieldJson = new JSONObject();

        String fieldName = field.getName();
        if("serialVersionUID".equals(fieldName)) {
          continue;
        }
        fieldJson.put("fieldName", fieldName);
        fieldJson.put("type", field.getType().getTypeName());

        Annotation[] annotations = field.getDeclaredAnnotations();


        JSONArray annotationArray = new JSONArray();
        for (Annotation annotation : annotations) {
          String annotationName = annotation.annotationType().getTypeName();

          //dont do anything
          if("io.swagger.v3.oas.annotations.media.Schema".equals(annotationName)) {
            continue;
          }
          if("javax.validation.constraints.Min".equals(annotationName)) {
            annotationArray.add("javax.validation.constraints.Min");
          }

          if("javax.validation.constraints.NotNull".equals(annotationName)) {
            annotationArray.add("javax.validation.constraints.NotNull");
          }

          if("javax.validation.constraints.NotEmpty".equals(annotationName)) {
            annotationArray.add("javax.validation.constraints.NotEmpty");
          }
        }
        fieldJson.put("property", annotationArray);

        classProperty.put("elements", fieldJson);
      }
      array.add(classProperty);
    }
    return array;
  }

  public static void main(String[] args) {
    Configuration configuration = new Configuration(Configuration.DEFAULT_INCOMPATIBLE_IMPROVEMENTS);
    Writer out = null;

    try {
        System.out.println(resolveFieldProperty());


        configuration.setDirectoryForTemplateLoading(new File("src/main/resources/"));

        Map<String, Object> dataMap = new HashMap<>();
        dataMap.put("packageName", "com.freemark.hello");
        dataMap.put("className", "Test");
        dataMap.put("today", "2021-04-28");
        dataMap.put("methodName", "userName");

        Template template = configuration.getTemplate("GenerateAnnotationTestCase.ftl");

        File docFile = new File("src/main/resources/User.java");
        out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(docFile)));

        template.process(dataMap, out);
        System.out.println("File Created.");
    } catch (Exception e) {
        e.printStackTrace();
    }

  }
}
