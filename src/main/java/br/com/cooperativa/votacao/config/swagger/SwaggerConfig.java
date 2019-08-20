package br.com.cooperativa.votacao.config.swagger;

import br.com.cooperativa.votacao.config.ConfigConstants;
import com.google.common.collect.Lists;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.AuthorizationScope;
import springfox.documentation.service.SecurityReference;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger.web.SecurityConfiguration;
import springfox.documentation.swagger.web.SecurityConfigurationBuilder;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Arrays;
import java.util.List;

@Configuration
@EnableConfigurationProperties(SwaggerConfigProperties.class)
@EnableSwagger2
public class SwaggerConfig {

	private final SwaggerConfigProperties configProperties;

	SwaggerConfig(SwaggerConfigProperties configProperties){
		this.configProperties = configProperties;
	}

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
				.apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("br.com.cooperativa.votacao"))
                .paths(PathSelectors.any())
                .build()
                .securitySchemes(Lists.newArrayList(apiKey()))
                .securityContexts(Lists.newArrayList(securityContext()));
    }

    @Bean
    public SecurityConfiguration security(){
        return SecurityConfigurationBuilder.builder()
                .clientId(null).clientSecret(null).realm(null).appName(null).scopeSeparator("").build();
    }

    private ApiKey apiKey(){
        return new ApiKey("apiKey", ConfigConstants.HEADER_STRING, "header");
    }

    private SecurityContext securityContext(){
        return SecurityContext.builder().securityReferences(defaultAuth())
                .forPaths(PathSelectors.any()).build();
    }

    private List<SecurityReference> defaultAuth(){
        AuthorizationScope authorizationScope = new AuthorizationScope(
                "global", "accessEverything");
        AuthorizationScope[] authorizationScopes = new AuthorizationScope[1];
        authorizationScopes[0] = authorizationScope;
        return Arrays.asList(new SecurityReference("apiKey",
                authorizationScopes));

    }

    private ApiInfo apiInfo(){
		return new ApiInfoBuilder()
				.title(this.configProperties.getName())
				.description(this.configProperties.getDescription())
				.version(this.configProperties.getVersion())
				.build();
	}
}
