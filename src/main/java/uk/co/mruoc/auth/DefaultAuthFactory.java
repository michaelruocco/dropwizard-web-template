package uk.co.mruoc.auth;

import javax.ws.rs.core.UriInfo;

public class DefaultAuthFactory implements AuthFactory {

    private final AuthConfig googleAuthConfig = new GoogleAuthConfig();
    private final AuthConfig gitHubAuthConfig = new GitHubAuthConfig();
    private final AuthConfig fakeAuthConfig = new FakeAuthConfig();

    private final Authenticator googleAuthenticator = new GoogleAuthenticator();
    private final Authenticator gitHubAuthenticator = new GitHubAuthenticator();
    private final Authenticator fakeAuthenticator = new FakeAuthenticator();

    @Override
    public String getAuthUrl(UriInfo uriInfo, String type) {
        AuthConfig config = getAuthConfig(type);
        return config.getAuthUrl(uriInfo);
    }

    @Override
    public boolean canAuth(String type) {
        switch(type.toLowerCase()) {
            case "google" : return googleAuthConfig.canAuth();
            case "github" : return gitHubAuthConfig.canAuth();
            default : return fakeAuthConfig.canAuth();
        }
    }

    @Override
    public Authenticator getAuthenticator(String type) {
        switch (type.toLowerCase()) {
            case "google" : return googleAuthenticator;
            case "github" : return gitHubAuthenticator;
            default : return fakeAuthenticator;
        }
    }

    private AuthConfig getAuthConfig(String type) {
        switch(type.toLowerCase()) {
            case "google" : return googleAuthConfig;
            case "github" : return gitHubAuthConfig;
            default : return fakeAuthConfig;
        }
    }

}
