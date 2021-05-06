package com.gateway.mdw.config;


import org.springframework.context.annotation.Configuration;
import org.springframework.data.r2dbc.repository.config.EnableR2dbcRepositories;

@Configuration
@EnableR2dbcRepositories(basePackages = {"com.gateway.mdw.repository"},
        basePackageClasses = {})
public class ApplicationConfig {
}
