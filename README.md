Make sure you have these dependencies 
```xml
<dependency>
    <groupId>org.springframework.security.oauth</groupId>
    <artifactId>spring-security-oauth2</artifactId>
    <version>2.2.0.RELEASE</version>
</dependency>
<dependency>
    <groupId>org.springframework.cloud</groupId>
    <artifactId>spring-cloud-starter-oauth2</artifactId>
</dependency>
<dependency>
    <groupId>io.pivotal.spring.cloud</groupId>
    <artifactId>spring-cloud-sso-connector</artifactId>
    <version>2.1.1.RELEASE</version>
</dependency>
```

You will need ```ClientCredentailsResourceDetails``` and instead of using RestTemplate, you use ```OAuth2RestTemplate```. 

```java
@Configuration
@EnableOAuth2Client
@EnableConfigurationProperties
public class ClientCredentialConfig {
    @Bean
    @ConfigurationProperties(prefix = "security.oauth2.client")
    ClientCredentialsResourceDetails clientCredentialsResourceDetails() {
        return new ClientCredentialsResourceDetails();
    }

    @Bean
    OAuth2RestTemplate clientCredentialsRestTemplate() {
        OAuth2RestTemplate restTemplate = new OAuth2RestTemplate(clientCredentialsResourceDetails());
        return restTemplate;
    }
}
```

You can bootstrap the SSO with these environment variables specified in manifest.yml
```
env:
  GRANT_TYPE: client_credentials
  SSO_AUTHORITIES: contact.read
```    

Please refer to Resource Server sample [here](https://github.com/dwong-pivotal/pcf-sso-resource-server) 