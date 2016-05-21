package uk.co.mruoc.hello;

import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import uk.co.mruoc.hello.health.TemplateHealthCheck;
import uk.co.mruoc.hello.resources.HelloResource;

public class Application extends io.dropwizard.Application<Configuration> {

    public static void main(String[] args) throws Exception {
        new Application().run(args);
    }

    @Override
    public String getName() {
        return "hello-world";
    }

    @Override
    public void initialize(Bootstrap<Configuration> bootstrap) {
        // nothing to do yet
    }

    @Override
    public void run(Configuration configuration, Environment environment) {
        final HelloResource resource = new HelloResource(
                configuration.getTemplate(),
                configuration.getDefaultName()
        );
        environment.jersey().register(resource);

        final TemplateHealthCheck healthCheck =
                new TemplateHealthCheck(configuration.getTemplate());
        environment.healthChecks().register("template", healthCheck);
    }

}
