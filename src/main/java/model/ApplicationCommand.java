package model;

import io.swagger.v3.oas.annotations.media.Schema;
import java.io.Serializable;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * @author GC-tiannaicheng
 */
public class ApplicationCommand implements Serializable {

    private static final long serialVersionUID = 1036773436766624409L;

    @Schema(description = "Membership Id")
    @Min(0)
    @NotNull
    private Long membershipId;

    @Schema(description = "Plan Id")
    @Size(max = 36)
    @NotNull()
    @NotEmpty()
    private String planId;

    @Schema(description = "Dealer Id")
    @Size(max = 36)
    @NotNull()
    @NotEmpty()
    private String dealerId;

    @Schema(description = "Branch Id")
    @Size(max = 36)
    @NotNull()
    @NotEmpty()
    private String branchId;

    @Schema(description = "Mail Address")
    @Size(max = 64)
    @NotNull()
    @NotEmpty()
    private String mailAddress;

    @Schema(description = "Family Name")
    @Size(max = 32)
    @NotNull()
    @NotEmpty()
    private String familyName;

    @Schema(description = "Given Name")
    @Size(max = 32)
    @NotNull()
    @NotEmpty()
    private String givenName;

    @Schema(description = "Date Of Birthday")
    @NotNull()
    @NotEmpty()
    private String dateOfBirth;

    public Long getMembershipId() {
        return membershipId;
    }

    public void setMembershipId(Long membershipId) {
        this.membershipId = membershipId;
    }

    public String getPlanId() {
        return planId;
    }

    public void setPlanId(String planId) {
        this.planId = planId;
    }

    public String getDealerId() {
        return dealerId;
    }

    public void setDealerId(String dealerId) {
        this.dealerId = dealerId;
    }

    public String getBranchId() {
        return branchId;
    }

    public void setBranchId(String branchId) {
        this.branchId = branchId;
    }

    public String getMailAddress() {
        return mailAddress;
    }

    public void setMailAddress(String mailAddress) {
        this.mailAddress = mailAddress;
    }

    public String getFamilyName() {
        return familyName;
    }

    public void setFamilyName(String familyName) {
        this.familyName = familyName;
    }

    public String getGivenName() {
        return givenName;
    }

    public void setGivenName(String givenName) {
        this.givenName = givenName;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }
}
