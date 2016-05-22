package uk.co.mruoc.hello;

import io.dropwizard.jdbi.DBIFactory;
import io.dropwizard.jdbi.bundles.DBIExceptionsBundle;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import org.flywaydb.core.Flyway;
import org.skife.jdbi.v2.DBI;
import uk.co.mruoc.hello.health.TemplateHealthCheck;
import uk.co.mruoc.hello.jdbi.SayingDao;
import uk.co.mruoc.hello.resources.HelloResource;

public class Application extends io.dropwizard.Application<Configuration> {

    public static void main(String[] args) throws Exception {
        new Application().run(args);
    }

    @Override
    public String getName() {
        return "web-template";
    }

    @Override
    public void initialize(Bootstrap<Configuration> bootstrap) {
        bootstrap.addBundle(new DBIExceptionsBundle());
    }

    @Override
    public void run(Configuration configuration, Environment environment) {
        final DBIFactory factory = new DBIFactory();
        final DBI jdbi = factory.build(environment, configuration.getDataSourceFactory(), "database");
        final SayingDao dao = jdbi.onDemand(SayingDao.class);
        final HelloResource resource = new HelloResource(dao);
        environment.jersey().register(resource);

        //final TemplateHealthCheck healthCheck = new TemplateHealthCheck(configuration.getTemplate());
        //environment.healthChecks().register("template", healthCheck);
    }

}
