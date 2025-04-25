package eu.gaiax.user.account.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import eu.gaiax.user.account.utils.ObjFieldName;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

import java.io.Serializable;
import java.util.Map;

@ToString
@Getter
@AllArgsConstructor
public class UserAccountDetails implements Serializable {
  @ObjFieldName(name = "Avatar Image Link", nullable = false)
  private String avatarImageLink;
  @ObjFieldName(name = "E-Mail", nullable = false)
  private String email;
  @ObjFieldName(name = "Name", nullable = false)
  private String name;
  @ObjFieldName(name = "Surname", nullable = false)
  private String surname;
  @ObjFieldName(name = "Attribute A", nullable = false)
  private String attributeA;
  @ObjFieldName(name = "Attribute B", nullable = false)
  private String attributeB;
  @ObjFieldName(name = "Attribute C", nullable = false)
  private String attributeC;

  public static UserAccountDetails from(Map<String, String> m) {
    return new UserAccountDetails(
            m.get("avatarImageLink"),
            m.get("email"),
            m.get("name"),
            m.get("surname"),
            m.get("attributeA"),
            m.get("attributeB"),
            m.get("attributeC")
    );
  }
}
