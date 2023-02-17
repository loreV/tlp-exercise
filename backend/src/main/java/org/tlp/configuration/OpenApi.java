package org.tlp.configuration;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(info = @Info(
        title = "Tlp-Exercise",
        version= "1.0-SNAPSHOT",
        description = "An exercise to get hired!"))
public class OpenApi { }
