package eu.gaiax.user.account.repo.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class JSONDetails {
  private Map<String, String> properties;
}
