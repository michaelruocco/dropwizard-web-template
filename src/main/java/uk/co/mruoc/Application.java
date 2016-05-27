package uk.co.mruoc;

import io.dropwizard.jdbi.DBIFactory;
import io.dropwizard.jdbi.bundles.DBIExceptionsBundle;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import io.dropwizard.views.ViewBundle;
import io.federecio.dropwizard.swagger.SwaggerBundle;
import io.federecio.dropwizard.swagger.SwaggerBundleConfiguration;
import org.skife.jdbi.v2.DBI;
import uk.co.mruoc.facade.CustomerFacade;
import uk.co.mruoc.facade.CustomerFacade.CustomerFacadeBuilder;
import uk.co.mruoc.health.CustomerTableHealthCheck;
import uk.co.mruoc.health.Database;
import uk.co.mruoc.jdbi.CustomerDao;
import uk.co.mruoc.resources.CustomerResource;
import uk.co.mruoc.resources.CustomerViewResource;
import uk.co.mruoc.resources.IndexViewResource;
import uk.co.mruoc.service.CreateCustomerService;
import uk.co.mruoc.service.ReadCustomerService;

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
        final DBI dbi = factory.build(env, config.getDataSourceFactory(), "database");

        setUpCustomerResources(env, dbi);
    }

    private void setUpCustomerResources(Environment env, DBI dbi) {
        final CustomerDao customerDao = dbi.onDemand(CustomerDao.class);
        final CustomerFacade customerFacade = new CustomerFacadeBuilder()
                .setCreateService(new CreateCustomerService(customerDao))
                .setReadService(new ReadCustomerService(customerDao))
                .build();

        env.jersey().register(new CustomerViewResource(customerFacade));
        env.jersey().register(new CustomerResource(customerFacade));

        env.healthChecks().register("customerTable", new CustomerTableHealthCheck(new Database(dbi)));
    }

}
