package eu.gaiax.user.account.config;

import lombok.extern.slf4j.Slf4j;
import org.keycloak.adapters.KeycloakConfigResolver;
import org.keycloak.adapters.springboot.KeycloakSpringBootConfigResolver;
import org.keycloak.adapters.springsecurity.KeycloakConfiguration;
import org.keycloak.adapters.springsecurity.authentication.KeycloakAuthenticationProvider;
import org.keycloak.adapters.springsecurity.config.KeycloakWebSecurityConfigurerAdapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.authority.mapping.SimpleAuthorityMapper;
import org.springframework.security.web.authentication.session.NullAuthenticatedSessionStrategy;
import org.springframework.security.web.authentication.session.SessionAuthenticationStrategy;

@Slf4j
@KeycloakConfiguration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends KeycloakWebSecurityConfigurerAdapter {

  @Value("${check-keycloak-token:true}")
  private boolean checkToken;

  @Override
  protected SessionAuthenticationStrategy sessionAuthenticationStrategy() {
    return new NullAuthenticatedSessionStrategy();
  }

  @Autowired
  public void configureGlobal(AuthenticationManagerBuilder authManagerBuilder) {
    if (checkToken) {
      KeycloakAuthenticationProvider keycloakAuthenticationProvider = keycloakAuthenticationProvider();
      keycloakAuthenticationProvider.setGrantedAuthoritiesMapper(new SimpleAuthorityMapper());
      authManagerBuilder.authenticationProvider(keycloakAuthenticationProvider);
    }
  }

  @Bean
  public KeycloakConfigResolver keycloakConfigResolver() {
    return new KeycloakSpringBootConfigResolver();
  }

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    if (checkToken) {
      log.info("\n/*******************************" +
              "\n      WITH KEYCLOAK " +
              "\n/*******************************");
      super.configure(http);
      http.csrf().disable()
          .authorizeRequests()
          .antMatchers(
                  "/api/account/swagger-ui/*",
                  "/api/account/swagger-resources/**",
                  "/**/api-docs").permitAll()
          .antMatchers("/api/account/user/**").hasRole("gaiax-pcr")
          .antMatchers("/api/account/provider/**").hasRole("gaiax-ppr")
          .anyRequest().authenticated();

    } else {
      log.info("\n/*******************************" +
              "\n      WITHOUT KEYCLOAK " +
              "\n/*******************************");
      http.csrf().disable()
          .authorizeRequests()
          .antMatchers("**").permitAll()
          .anyRequest().authenticated();
    }
  }
}
