package uk.co.mruoc;

import io.dropwizard.jdbi.DBIFactory;
import io.dropwizard.jdbi.bundles.DBIExceptionsBundle;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import io.dropwizard.views.ViewBundle;
import io.federecio.dropwizard.swagger.SwaggerBundle;
import io.federecio.dropwizard.swagger.SwaggerBundleConfiguration;
import org.skife.jdbi.v2.DBI;
import uk.co.mruoc.health.CustomerTableHealthCheck;
import uk.co.mruoc.health.Database;
import uk.co.mruoc.jdbi.CustomerDao;
import uk.co.mruoc.resources.CustomerResource;
import uk.co.mruoc.resources.CustomerViewResource;

public class Application extends io.dropwizard.Application<Config> {

    @Override
    public String getName() {
        return "web-template";
    }

    @Override
    public void initialize(Bootstrap<Config> bootstrap) {
        bootstrap.addBundle(new DBIExceptionsBundle());
        bootstrap.addBundle(new SwaggerBundle<Config>() {
            @Override
            protected SwaggerBundleConfiguration getSwaggerBundleConfiguration(Config config) {
                return config.getSwaggerBundleConfiguration();
            }
        });
        bootstrap.addBundle(new ViewBundle<>());
    }

    @Override
    public void run(Config config, Environment env) {
        final DBIFactory factory = new DBIFactory();
        final DBI jdbi = factory.build(env, config.getDataSourceFactory(), "database");

        final CustomerDao customerDao = jdbi.onDemand(CustomerDao.class);
        final CustomerResource customerResource = new CustomerResource(customerDao);
        env.jersey().register(customerResource);

        final CustomerViewResource customerViewResource = new CustomerViewResource(customerDao);
        env.jersey().register(customerViewResource);

        final Database database = new Database(jdbi);
        final CustomerTableHealthCheck customerTableHealthCheck = new CustomerTableHealthCheck(database);
        env.healthChecks().register("customerTable", customerTableHealthCheck);
    }

}
