package hive.caronte.swagger;

import com.fasterxml.classmate.TypeResolver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.ApiListingScannerPlugin;
import springfox.documentation.spi.service.OperationModelsProviderPlugin;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.HashSet;
import java.util.Set;

@Configuration
@EnableSwagger2
public class SwaggerConfig extends WebMvcConfigurationSupport {

  @Bean
  public Docket apiDocumentation() {
    Set<String> responseContentTypes = new HashSet<>();
    responseContentTypes.add("*/*");
    responseContentTypes.add("application/json");
    responseContentTypes.add("image/jpeg");
    responseContentTypes.add("text/plain");
    return new
        Docket(DocumentationType.SWAGGER_2)
        .select()
        .apis(RequestHandlerSelectors.basePackage("hive.caronte.security"))
        .build()
        .produces(responseContentTypes)
        .consumes(responseContentTypes)
        .apiInfo(metaData());
  }

  private ApiInfo metaData() {
    return new
        ApiInfoBuilder()
        .title("Caronte endpoints")
        .description("\"Hive's Token API (Java JWT)\""
            + "\n Repository: https://github.com/hex-g/caronte"
            + "\n Created by: https://github.com/hex-g/")
        .version("v1.0")
        .license("")
        .licenseUrl("")
        .build();
  }
  @Bean
  public ApiListingScannerPlugin listingScanner() {
    return new CaronteLoginSwagger();
  }
  @Override
  protected void addResourceHandlers(ResourceHandlerRegistry registry) {
    registry.addResourceHandler("swagger-ui.html")
        .addResourceLocations("classpath:/META-INF/resources/");
    registry.addResourceHandler("/webjars/**")
        .addResourceLocations("classpath:/META-INF/resources/webjars/");
  }
}