package eu.gaiax.user.account.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.io.Serializable;
import java.util.Map;

/**
 * ...
 */
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserRegistrationRequest implements Serializable {
  @JsonProperty(value = "first_name")
  @NonNull String firstname;
  @JsonProperty(value = "last_name")
  @NonNull String lastname;
  @NonNull String email;
  @NonNull String phone;
  @NonNull String address;
  @JsonProperty(value = "zip_code")
  @NonNull String zip;
  @NonNull String city;
  @NonNull String country;

  public static UserRegistrationRequest from(Map<String, String> m) {
    return new UserRegistrationRequest(
            m.get("first_name"),
            m.get("last_name"),
            m.get("email"),
            m.get("phone"),
            m.get("address"),
            m.get("zip_code"),
            m.get("city"),
            m.get("country")
    );
  }
}
