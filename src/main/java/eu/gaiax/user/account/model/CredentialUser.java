package eu.gaiax.user.account.model;

import eu.gaiax.user.account.utils.ObjFieldName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;
import lombok.*;

import java.io.Serializable;
import java.util.Map;

@ApiModel
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class CredentialUser implements Serializable {
  @ApiModelProperty(name = "ID", value = "ID", example = "ase1asfd3fg45g")
  @ObjFieldName(name = "ID", nullable = true)
  private String id;
  @ApiModelProperty(name = "First Name", value = "First Name", example = "Rachel")
  @ObjFieldName(name = "First Name", nullable = false)
  private String firstName;
  @ApiModelProperty(name = "Last Name", value = "Last Name", example = "Green")
  @ObjFieldName(name = "Last Name", nullable = false)
  private String lastName;
  @ApiModelProperty(name = "Role", value = "Role", example = "gaiax-fr")
  @ObjFieldName(name = "Role", nullable = false)
  private String role;
  @ApiModelProperty(name = "E-Mail", value = "E-Mail", example = "email@email.em")
  @ObjFieldName(name = "E-Mail", nullable = false)
  private String email;
  @ApiModelProperty(name = "User Name", value = "User Name", example = "Gregory House")
  @ObjFieldName(name = "User Name", nullable = false)
  private String userName;

  public static CredentialUser from(Map m) {
    return new CredentialUser(
            (String) m.get("id"),
            (String) m.get("firstName"),
            (String) m.get("lastName"),
            (String) m.get("role"),
            (String) m.get("email"),
            (String) m.get("userName")
    );
  }
}
