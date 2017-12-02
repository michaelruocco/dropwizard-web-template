package uk.co.mruoc.auth;

import javax.ws.rs.container.DynamicFeature;
import javax.ws.rs.container.ResourceInfo;
import javax.ws.rs.core.FeatureContext;
import java.util.Arrays;

public class AuthLoginFeature implements DynamicFeature {

    @Override
    public void configure(ResourceInfo resourceInfo, FeatureContext context) {
        System.out.println("********** " + Arrays.toString(resourceInfo.getResourceClass().getAnnotations()));
        if (resourceInfo.getResourceClass().isAnnotationPresent(AuthenticatedResource.class)) {
            System.out.println("registering filter");
            context.register(AuthLoginFilter.class);
        }
    }

}
