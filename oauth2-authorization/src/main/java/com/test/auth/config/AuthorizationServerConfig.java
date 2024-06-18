package com.test.auth.config;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.time.Duration;
import java.time.Instant;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.OAuth2AuthorizationServerConfiguration;
import org.springframework.security.config.annotation.web.configurers.oauth2.server.authorization.OAuth2AuthorizationServerConfigurer;
import org.springframework.security.config.annotation.web.configurers.oauth2.server.resource.OAuth2ResourceServerConfigurer;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.core.AuthorizationGrantType;
import org.springframework.security.oauth2.core.ClientAuthenticationMethod;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.server.authorization.client.InMemoryRegisteredClientRepository;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClient;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClientRepository;
import org.springframework.security.oauth2.server.authorization.config.ClientSettings;
import org.springframework.security.oauth2.server.authorization.config.ProviderSettings;
import org.springframework.security.oauth2.server.authorization.config.TokenSettings;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.LoginUrlAuthenticationEntryPoint;

import com.nimbusds.jose.jwk.JWKSet;
import com.nimbusds.jose.jwk.RSAKey;
import com.nimbusds.jose.jwk.source.ImmutableJWKSet;
import com.nimbusds.jose.jwk.source.JWKSource;
import com.nimbusds.jose.proc.SecurityContext;

@Configuration
@Import(OAuth2AuthorizationServerConfiguration.class)
public class AuthorizationServerConfig {

	@Value("${app.token.issuer}")
	private String tokenissuer;

	@Bean
	@Order(Ordered.HIGHEST_PRECEDENCE)
	public SecurityFilterChain authServerSecurityFilterChain(HttpSecurity http) throws Exception {
		// OAuth2AuthorizationServerConfiguration.applyDefaultSecurity(http);
		// return http.authorizeRequests(Customizer.withDefaults()).build();
		OAuth2AuthorizationServerConfiguration.applyDefaultSecurity(http);
		http.getConfigurer(OAuth2AuthorizationServerConfigurer.class).oidc(Customizer.withDefaults()); // Enable OpenID
																										// Connect 1.0
		http
				// Redirect to the login page when not authenticated from the
				// authorization endpoint
				.exceptionHandling((exceptions) -> exceptions
						.authenticationEntryPoint(new LoginUrlAuthenticationEntryPoint("/login")))
				// Accept access tokens for User Info and/or Client Registration
				.oauth2ResourceServer(OAuth2ResourceServerConfigurer::jwt);

		return http.build();
	}

	@Bean
	public RegisteredClientRepository registeredClientRepository() {
		TokenSettings tokenSettings = TokenSettings.builder().accessTokenTimeToLive(Duration.ofMinutes(10)).build();
		RegisteredClient testClient = RegisteredClient.withId(UUID.randomUUID().toString()).clientId("TEST")
				.clientSecret("{noop}TEST_SECRET")
				.clientAuthenticationMethod(ClientAuthenticationMethod.CLIENT_SECRET_BASIC)
				.authorizationGrantType(AuthorizationGrantType.AUTHORIZATION_CODE)
				.authorizationGrantType(AuthorizationGrantType.REFRESH_TOKEN)
				.authorizationGrantType(AuthorizationGrantType.CLIENT_CREDENTIALS)
				.redirectUri("http://127.0.0.1:8080/login/oauth2/code/").redirectUri("http://127.0.0.1:8080/authorized")
				.clientIdIssuedAt(Instant.now()).tokenSettings(tokenSettings).scope("VIEW_BOOK").scope("MAKE_ORDER")
				.clientSettings(ClientSettings.builder().requireAuthorizationConsent(true).build()).build();
		return new InMemoryRegisteredClientRepository(testClient);

	}

	/**
	 * Manage OAuth2 authorization information services
	 *
	 * @param jdbcTemplate               the jdbc template
	 * @param registeredClientRepository the registered client repository
	 * @return the o auth 2 authorization service
	 */
	/*
	 * @Bean public JdbcOAuth2AuthorizationService
	 * jdbcOAuth2AuthorizationService(JdbcTemplate jdbcTemplate,
	 * RegisteredClientRepository registeredClientRepository) { return new
	 * JdbcOAuth2AuthorizationService(jdbcTemplate, registeredClientRepository); }
	 */

	@Bean
	public PasswordEncoder passwordEncoder() {
		return PasswordEncoderFactories.createDelegatingPasswordEncoder();
	}

	@Bean
	public JWKSource<SecurityContext> jwkSource() {
		KeyPair keyPair = generateRsaKey();
		RSAPublicKey publicKey = (RSAPublicKey) keyPair.getPublic();
		RSAPrivateKey privateKey = (RSAPrivateKey) keyPair.getPrivate();
		RSAKey rsaKey = new RSAKey.Builder(publicKey).privateKey(privateKey).keyID(UUID.randomUUID().toString())
				.build();
		JWKSet jwkSet = new JWKSet(rsaKey);
		return new ImmutableJWKSet<>(jwkSet);
	}

	private static KeyPair generateRsaKey() {
		KeyPair keyPair;
		try {
			KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
			keyPairGenerator.initialize(2048);
			keyPair = keyPairGenerator.generateKeyPair();
		} catch (Exception ex) {
			throw new IllegalStateException(ex);
		}
		return keyPair;
	}

	@Bean
	public ProviderSettings providerSettings() {
		return ProviderSettings.builder().issuer(tokenissuer)
				.tokenEndpoint("/realms/master/protocol/openid-connect/token")
				.tokenIntrospectionEndpoint("/realms/master/protocol/openid-connect/token/introspect")
				.jwkSetEndpoint("/auth/realms/protocol/openid-connect/jwks")
				.authorizationEndpoint("/auth/realms/protocol/openid-connect/authorize")
				.oidcClientRegistrationEndpoint("/register")
				.tokenRevocationEndpoint("/realms/master/protocol/openid-connect/token/revoke").build();

	}

	@Bean
	public JwtDecoder jwtDecoder(JWKSource<SecurityContext> jwkSource) {
		return OAuth2AuthorizationServerConfiguration.jwtDecoder(jwkSource);
	}

}