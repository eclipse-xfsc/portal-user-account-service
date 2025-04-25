package eu.gaiax.user.account.rest;

import eu.gaiax.user.account.model.UserRegistrationRequest;
import eu.gaiax.user.account.utils.ProxyCall;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;
import java.util.Objects;

@Log4j2
@RestController
@RequestMapping("/api/account/user")
@Api(tags = "User Account Service")
public class UserAccountRest {
  @Autowired
  @Qualifier("identitySrv")
  private WebClient webClient;


  @GetMapping
  @ApiOperation("Get user info")
  public UserRegistrationRequest getUserAccountData(HttpServletRequest request) {
    return UserRegistrationRequest.from(
      Objects.requireNonNull(ProxyCall.<Map<String, String>>doProxyCall(webClient, request).getBody())
    );
  }
}
