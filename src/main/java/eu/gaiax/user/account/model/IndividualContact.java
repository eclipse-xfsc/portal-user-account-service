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
public class IndividualContact implements Serializable {
  @ObjFieldName(name = "Individual Contact Name", nullable = false)
  private String name;
  @ObjFieldName(name = "Individual Contact Surname", nullable = false)
  private String surname;
  @ObjFieldName(name = "Individual Contact E-Mail", nullable = false)
  private String email;
  @ObjFieldName(name = "Individual Contact Phone Number", nullable = false)
  private String phoneNumber;

  public static IndividualContact from(Map<String, String> m) {
    return new IndividualContact(
            m.get("name"),
            m.get("surname"),
            m.get("email"),
            m.get("phoneNumber")
    );
  }
}
