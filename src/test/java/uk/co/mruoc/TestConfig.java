package uk.co.mruoc;

import io.dropwizard.testing.ResourceHelpers;

public class TestConfig {

    private static final String CONFIG_NAME = "unit-test-web-template.yml";

    public static final String CONFIG_PATH = "/" + CONFIG_NAME;
    public static final String RESOURCE_PATH = ResourceHelpers.resourceFilePath(CONFIG_NAME);

}
