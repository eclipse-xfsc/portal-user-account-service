package eu.gaiax.user.account.rest;

import eu.gaiax.user.account.model.ErrorDto;
import eu.gaiax.user.account.model.ProviderAccountDetails;
import eu.gaiax.user.account.repo.entities.FrRequestType;
import eu.gaiax.user.account.service.SdService;
import eu.gaiax.user.account.utils.ProxyCall;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.reactive.function.client.WebClient;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;
import java.util.Objects;

@RestController
@Log4j2
@RequestMapping("/api/account/provider")
@Api(tags = "Provider Account Service")
public class ProviderAccountRest {
  @Autowired
  private SdService sdService;

  @Autowired
  @Qualifier("identitySrv")
  private WebClient identitySrv;

  @GetMapping
  @ApiOperation("Get provider info")
  @ResponseBody
  public ProviderAccountDetails getProviderAccountData(HttpServletRequest request) {
    final Map<String, Object> map =
            Objects.requireNonNull(ProxyCall.<Map<String, Object>>doProxyCall(identitySrv, request).getBody());
    return ProviderAccountDetails.from(map);

  }

  @DeleteMapping
  @ApiOperation("Delete provider")
  public ResponseEntity<?> deleteProviderAccountData() {
    try {
      return identitySrv
              .method(HttpMethod.DELETE)
              .uri("/api/account/provider/delete")
              .exchange().block().toBodilessEntity().block();
    } catch (Exception e) {
      return ResponseEntity.internalServerError()
                           .body(
                                   new ErrorDto(
                                           "/api/account/provider/",
                                           e.getMessage())
                           );
    }
  }

  @GetMapping("/history")
  @ResponseBody
  public Map getLoginHistory(HttpServletRequest request) {
    return ProxyCall.<Map<String, Object>>doProxyCall(identitySrv, request).getBody();
  }

  @PostMapping
  public ResponseEntity<?> updateProviderSD(
          HttpServletRequest request,
          @RequestHeader HttpHeaders headers,
          @Parameter(
                  description = "File to be uploaded",
                  content = @Content(mediaType = MediaType.MULTIPART_FORM_DATA_VALUE)
          ) @RequestParam("providerFile") MultipartFile file
  ) {
    return processSDRequest("/api/account/provider/", request, file, headers, FrRequestType.SD_PPR, null);
  }

  private ResponseEntity<?> processSDRequest(
          final String errorPath, final HttpServletRequest request,
          final MultipartFile file, final HttpHeaders headers,
          final FrRequestType frRequestType,
          final String resourceId
  ) {
    log.info("Request: {}; Filename: {}", request.getRequestURI(), file.getOriginalFilename());

    final String jwt = headers.getFirst(HttpHeaders.AUTHORIZATION);
    if (jwt == null || jwt.isBlank()) {
      return ResponseEntity
              .status(HttpStatus.UNAUTHORIZED)
              .body(new ErrorDto(errorPath, "Not authorized"));
    }

    try {
      sdService.processSDPostPutRequest(jwt, file, frRequestType, resourceId);
    } catch (Exception e) {
      return ResponseEntity
              .status(HttpStatus.INTERNAL_SERVER_ERROR)
              .body(new ErrorDto(errorPath, e.getMessage()));
    }

    return ResponseEntity.ok().build();
  }
}
