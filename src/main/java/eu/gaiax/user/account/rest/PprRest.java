package eu.gaiax.user.account.rest;


import eu.gaiax.user.account.model.CredentialUser;
import eu.gaiax.user.account.model.ErrorDto;
import eu.gaiax.user.account.utils.ProxyCall;
import io.swagger.annotations.ApiOperation;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.client.WebClient;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

@RestController
@Log4j2
@RequestMapping("/api/account/provider/users")
public class PprRest {

  @Autowired
  @Qualifier("identitySrv")
  private WebClient identitySrv;

  @GetMapping
  @ResponseBody
  @ApiOperation("Get Users")
  public ResponseEntity<?> getUsers(HttpServletRequest request) {
    log.info("in getUsers");
    try {
      final List<CredentialUser> r = ProxyCall.<List>doProxyCall(identitySrv, request).getBody();
      return ResponseEntity.ok().body(r);
    } catch (Exception e) {
      return ResponseEntity.internalServerError().body(
              new ErrorDto(
                      "/api/account/provider/users",
                      e.getMessage()
              )
      );
    }
  }

  @PutMapping("/{userId}")
  @ApiOperation("Update User")
  public ResponseEntity<?> updateUsers(
          HttpServletRequest request,
          @RequestBody CredentialUser rq
  ) {
    try {
      log.info("in updateUsers");
      final Map r = ProxyCall.<Map, CredentialUser>doPut(identitySrv, request, rq).getBody();

      return ResponseEntity.ok(CredentialUser.from(r));
    } catch (Exception e) {
      return ResponseEntity.internalServerError().body(
              new ErrorDto(
                      "/api/account/provider/users/",
                      e.getMessage()
              )
      );
    }
  }

  @PostMapping
  @ApiOperation("Add new User")
  public ResponseEntity<?> addUsers(
          HttpServletRequest request,
          @RequestBody CredentialUser rq
  ) {
    try {
      log.info("in addUsers");
      final Map r = ProxyCall.<Map, CredentialUser>doPost(identitySrv, request, rq).getBody();

      return ResponseEntity.ok(CredentialUser.from(r));
    } catch (Exception e) {
      return ResponseEntity.internalServerError().body(
              new ErrorDto(
                      "/api/account/provider/users/",
                      e.getMessage()
              )
      );
    }
  }

  @DeleteMapping("/{userId}")
  @ApiOperation("Remove User by ID")
  public ResponseEntity<?> deleteUsers(
          HttpServletRequest request,
          @PathVariable(value = "userId") Integer userId
  ) {
    try {
      log.info("In deleteUsers");
      ProxyCall.<Map>doDelete(identitySrv, request).getBody();
      return ResponseEntity.ok(null);
    } catch (Exception e) {
      return ResponseEntity.internalServerError().body(
              new ErrorDto(
                      String.format("/api/account/provider/users/%s", userId),
                      e.getMessage()
              )
      );
    }

  }

}
