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
public class DataProviderOfficer {
  @ObjFieldName(name = "Data Provider Name", nullable = false)
  private String name;
  @ObjFieldName(name = "Data Provider Surname", nullable = false)
  private String surname;
  @ObjFieldName(name = "Data Provider E-Mail", nullable = false)
  private String email;
  @ObjFieldName(name = "Data Provider Phone Number", nullable = false)
  private String phoneNumber;

  public static DataProviderOfficer from(Map<String, String> m) {
    return new DataProviderOfficer(
            m.get("name"),
            m.get("surname"),
            m.get("email"),
            m.get("phoneNumber")
    );
  }
}
