package hive.caronte.swagger;

import com.fasterxml.classmate.TypeResolver;
import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;
import org.springframework.http.HttpMethod;
import springfox.documentation.builders.OperationBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.ResponseMessageBuilder;
import springfox.documentation.schema.Example;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiDescription;
import springfox.documentation.service.ResponseMessage;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.ApiListingScannerPlugin;
import springfox.documentation.spi.service.contexts.DocumentationContext;
import springfox.documentation.spring.web.readers.operation.CachingOperationNameGenerator;

import java.util.*;

import static java.util.Collections.*;

public class CaronteLoginSwagger implements ApiListingScannerPlugin {
  @Override
  public List<ApiDescription> apply(DocumentationContext context) {
    return new ArrayList<>(
        Arrays.asList(
            new ApiDescription(
                "authentication",
                "/auth",
                "Hive JWT Swagger",
                Collections.singletonList(
                    new OperationBuilder(
                        new CachingOperationNameGenerator())
                        .authorizations(new ArrayList<>())
                        .codegenMethodNameStem("LogIn")
                        .method(HttpMethod.POST)
                        .notes("Use this method to authenticate in all HIVE's APIs,\n"
                            + " the result is a Bearer token in the response head."
                            + "\n\nBody request example:\n\n"
                            + "{\"username\":\"admin\",\"password\":\"admin\"}")
                        .parameters(
                            Collections.singletonList( //<4>
                                new ParameterBuilder()
                                    .description("name & password")
                                    .type(new TypeResolver().resolve(Object.class))
                                    .name("Credentials:")
                                    .parameterType("body")
                                    .parameterAccess("access")
                                    .required(true)
                                    .modelRef(new ModelRef("object"))
                                    .build()))
                        .responseMessages(responseMessages())
                        .responseModel(new ModelRef("string"))
                        .build()),
                false)
            ));
  }

  /**
   * @return Set of response messages that overide the default/global response messages
   */
  private Set<ResponseMessage> responseMessages() {
    return singleton(new ResponseMessageBuilder()
        .code(200)
        .message("see the bearer token in the response header")
        .responseModel(new ModelRef("string"))
        .build());
  }
  // tag::api-listing-plugin[]

  @Override
  public boolean supports(DocumentationType delimiter) {
    return DocumentationType.SWAGGER_2.equals(delimiter);
  }

}
