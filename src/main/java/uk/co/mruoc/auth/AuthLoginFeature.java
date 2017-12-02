package uk.co.mruoc.auth;

import javax.ws.rs.container.DynamicFeature;
import javax.ws.rs.container.ResourceInfo;
import javax.ws.rs.core.FeatureContext;

public class AuthLoginFeature implements DynamicFeature {

    @Override
    public void configure(ResourceInfo resourceInfo, FeatureContext context) {
        if (resourceInfo.getResourceClass().isAnnotationPresent(AuthenticatedResource.class))
            context.register(AuthLoginFilter.class);
    }

}
