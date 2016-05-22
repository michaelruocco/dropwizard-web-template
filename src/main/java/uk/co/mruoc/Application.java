package uk.co.mruoc;

import io.dropwizard.jdbi.DBIFactory;
import io.dropwizard.jdbi.bundles.DBIExceptionsBundle;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import io.federecio.dropwizard.swagger.SwaggerBundle;
import io.federecio.dropwizard.swagger.SwaggerBundleConfiguration;
import org.skife.jdbi.v2.DBI;
import uk.co.mruoc.health.CustomerTableHealthCheck;
import uk.co.mruoc.health.Database;
import uk.co.mruoc.jdbi.CustomerDao;
import uk.co.mruoc.resources.CustomerResource;

public class Application extends io.dropwizard.Application<Configuration> {

    @Override
    public String getName() {
        return "web-template";
    }

    @Override
    public void initialize(Bootstrap<Configuration> bootstrap) {
        bootstrap.addBundle(new DBIExceptionsBundle());
        bootstrap.addBundle(new SwaggerBundle<Configuration>() {
            @Override
            protected SwaggerBundleConfiguration getSwaggerBundleConfiguration(Configuration configuration) {
                return configuration.getSwaggerBundleConfiguration();
            }
        });
    }

    @Override
    public void run(Configuration configuration, Environment environment) {
        final DBIFactory factory = new DBIFactory();
        final DBI jdbi = factory.build(environment, configuration.getDataSourceFactory(), "database");

        final CustomerDao customerDao = jdbi.onDemand(CustomerDao.class);
        final CustomerResource customerResource = new CustomerResource(customerDao);
        environment.jersey().register(customerResource);

        final Database database = new Database(jdbi);
        final CustomerTableHealthCheck customerTableHealthCheck = new CustomerTableHealthCheck(database);
        environment.healthChecks().register("customerTable", customerTableHealthCheck);
    }

}
