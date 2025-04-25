package eu.gaiax.user.account.model;

import eu.gaiax.user.account.utils.ObjFieldName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

import java.io.Serializable;
import java.util.Map;

@ApiModel
@ToString
@Getter
@AllArgsConstructor
public class ProviderAccountDetails implements Serializable {
  @ApiModelProperty(name = "avatarImageLink", value = "Avatar Image Link", example = "http://avatar.link/img.svg")
  @ObjFieldName(name = "Avatar Image Link", nullable = true)
  private String avatarImageLink;
  @ApiModelProperty(name = "email", value = "E-Mail", example = "email@email.em")
  @ObjFieldName(name = "E-Mail", nullable = true)
  private String email;
  @ApiModelProperty(name = "companyName", value = "Company Name", example = "Company Name Gmbh")
  @ObjFieldName(name = "Company Name", nullable = true)
  private String companyName;
  @ApiModelProperty(name = "commercialRegister", value = "Commercial Register", example = "34543")
  @ObjFieldName(name = "Commercial Register", nullable = true)
  private String commercialRegister;
  @ApiModelProperty(name = "registeredAddress", value = "Registered Address")
  @ObjFieldName(name = "Registered Address", nullable = false)
  private String registeredAddress;
  @ObjFieldName(name = "Web Address", nullable = false)
  private String webAddress;
  @ApiModelProperty(name = "individualContact", value = "Individual Contact")
  @ObjFieldName(name = "Individual Contact", nullable = false)
  private IndividualContact individualContact;
  @ApiModelProperty(name = "certification", value = "Certification", example = "233423")
  @ObjFieldName(name = "Certification", nullable = false)
  private String certification;
  @ApiModelProperty(name = "alias", value = "Alias", example = "Alias")
  @ObjFieldName(name = "Alias", nullable = false)
  private String alias;
  @ApiModelProperty(name = "localAttestation", value = "Local Attestation", example = "345645")
  @ObjFieldName(name = "Local Attestation", nullable = false)
  private String localAttestation;
  @ApiModelProperty(name = "transparencyRegister", value = "Transparency Register", example = "3467564")
  @ObjFieldName(name = "Transparency Register", nullable = false)
  private String transparencyRegister;
  @ApiModelProperty(name = "dunsNumber", value = "DUNS Number", example = "87654")
  @ObjFieldName(name = "DUNS Number", nullable = false)
  private String dunsNumber;
  @ApiModelProperty(name = "legalEntityIdentifier", value = "Legal Entity Identifier", example = "12345678")
  @ObjFieldName(name = "Legal Entity Identifier", nullable = false)
  private String legalEntityIdentifier;
  @ApiModelProperty(name = "dataProviderOfficer", value = "Data Provider Officer")
  @ObjFieldName(name = "Data Provider Officer", nullable = false)
  private DataProviderOfficer dataProviderOfficer;

  public static ProviderAccountDetails from(Map<String, Object> m) {
    return new ProviderAccountDetails(
            (String) m.get("avatarImageLink"),
            (String) m.get("email"),
            (String) m.get("companyName"),
            (String) m.get("commercialRegister"),
            (String) m.get("registeredAddress"),
            (String) m.get("webAddress"),
            IndividualContact.from((Map<String, String>) m.get("individualContact")),
            (String) m.get("certification"),
            (String) m.get("alias"),
            (String) m.get("localAttestation"),
            (String) m.get("transparencyRegister"),
            (String) m.get("dunsNumber"),
            (String) m.get("legalEntityIdentifier"),
            DataProviderOfficer.from((Map<String, String>) m.get("dataProviderOfficer"))
    );
  }
}

