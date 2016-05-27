package uk.co.mruoc;

import com.codahale.metrics.MetricRegistry;
import com.codahale.metrics.health.HealthCheckRegistry;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.dropwizard.db.DataSourceFactory;
import io.federecio.dropwizard.swagger.SwaggerBundleConfiguration;
import uk.co.deloittedigital.dropwizard.hikari.config.HikariConfiguration;
import uk.co.deloittedigital.dropwizard.hikari.config.HikariConfigurationProvider;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

public class Config extends io.dropwizard.Configuration implements HikariConfigurationProvider {

    private SwaggerBundleConfiguration swaggerBundleConfiguration;

    @Valid
    @NotNull
    @JsonProperty
    private DataSourceFactory database = new DataSourceFactory();

    private final HikariConfiguration hikariConfiguration = new HikariConfiguration();

    @Override
    public HikariConfiguration getHikariConfiguration() {
        hikariConfiguration.driverClassName = database.getDriverClass();
        hikariConfiguration.jdbcUrl = database.getUrl();
        hikariConfiguration.username = database.getUser();
        hikariConfiguration.password = database.getPassword();
        return hikariConfiguration;
    }

    @JsonProperty("swagger")
    public void setSwaggerBundleConfiguration(SwaggerBundleConfiguration swaggerBundleConfiguration) {
        this.swaggerBundleConfiguration = swaggerBundleConfiguration;
    }

    public SwaggerBundleConfiguration getSwaggerBundleConfiguration() {
        return swaggerBundleConfiguration;
    }

    public DataSourceFactory getDataSourceFactory() {
        return database;
    }

}
