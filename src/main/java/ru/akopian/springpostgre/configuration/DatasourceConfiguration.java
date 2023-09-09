package ru.akopian.springpostgre.configuration;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EntityScan(basePackages = "ru.akopian.springpostgre.model.entity")
@EnableJpaRepositories(basePackages = "ru.akopian.springpostgre.repository")
public class DatasourceConfiguration {
}
