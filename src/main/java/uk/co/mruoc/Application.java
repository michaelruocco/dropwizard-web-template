package uk.co.mruoc;

import io.dropwizard.jdbi.DBIFactory;
import io.dropwizard.jdbi.bundles.DBIExceptionsBundle;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import io.dropwizard.views.ViewBundle;
import io.federecio.dropwizard.swagger.SwaggerBundle;
import io.federecio.dropwizard.swagger.SwaggerBundleConfiguration;
import org.eclipse.jetty.server.session.SessionHandler;
import org.skife.jdbi.v2.DBI;
import uk.co.deloittedigital.dropwizard.hikari.HikariBundle;
import uk.co.mruoc.auth.AuthFactory;
import uk.co.mruoc.auth.DefaultAuthFactory;
import uk.co.mruoc.facade.*;
import uk.co.mruoc.jdbi.CustomerDao;
import uk.co.mruoc.resources.rest.CustomerResource;
import uk.co.mruoc.resources.view.OAuthCallbackResource;
import uk.co.mruoc.resources.view.*;

public class Application extends io.dropwizard.Application<Config> {

    @Override
    public String getName() {
        return "web-template";
    }

    @Override
    public void initialize(Bootstrap<Config> bootstrap) {
        bootstrap.addBundle(new HikariBundle());
        bootstrap.addBundle(new DBIExceptionsBundle());
        bootstrap.addBundle(new SwaggerBundle<Config>() {
            @Override
            protected SwaggerBundleConfiguration getSwaggerBundleConfiguration(Config config) {
                return config.swaggerBundleConfiguration;
            }
        });
        bootstrap.addBundle(new ViewBundle<>());
    }

    @Override
    public void run(Config config, Environment env) {
        final AuthFactory authFactory = new DefaultAuthFactory();
        env.jersey().register(new IndexViewResource());
        env.jersey().register(new OAuthCallbackResource(authFactory));
        env.jersey().register(new LogoutViewResource());
        env.servlets().setSessionHandler(new SessionHandler());

        final DBIFactory factory = new DBIFactory();
        final DBI dbi = factory.build(env, config.getDataSourceFactory(), "database");
        setUpCustomerResources(env, dbi);
    }

    private void setUpCustomerResources(Environment env, DBI dbi) {
        final CustomerDao dao = dbi.onDemand(CustomerDao.class);
        final CustomerFacade customerFacade = new DefaultCustomerFacade(dao);

        env.jersey().register(new CustomersViewResource(customerFacade));
        env.jersey().register(new CreateCustomerViewResource(customerFacade));
        env.jersey().register(new UpdateCustomerViewResource(customerFacade));
        env.jersey().register(new DeleteCustomerViewResource(customerFacade));

        env.jersey().register(new CustomerResource(customerFacade));
    }

}
