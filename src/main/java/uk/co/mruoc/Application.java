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
import uk.co.mruoc.resources.IndexViewResource;

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
        env.jersey().register(new IndexViewResource());

        final DBIFactory factory = new DBIFactory();
        final DBI jdbi = factory.build(env, config.getDataSourceFactory(), "database");
        final CustomerDao customerDao = jdbi.onDemand(CustomerDao.class);

        env.jersey().register(new CustomerViewResource(customerDao));
        env.jersey().register(new CustomerResource(customerDao));

        final Database database = new Database(jdbi);
        env.healthChecks().register("customerTable", new CustomerTableHealthCheck(database));
    }

}
