package train.shp4k.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;

/**
 * 15/12/2024 shp4k
 *
 * @author Boris Iurciuc (cohort36)
 */

@OpenAPIDefinition(
    info = @Info(
        title = "My project shop",
        description = "Application for testing REST API",
        version = "1.0.0",
        contact = @Contact(
            name = "Boris",
            email = "boris.iurciuc@gmail.com",
            url = ""
        )
    )
)
public class SwaggerConfig {

}
