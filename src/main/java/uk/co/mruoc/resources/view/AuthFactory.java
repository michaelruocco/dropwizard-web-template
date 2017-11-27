package uk.co.mruoc.resources.view;

import uk.co.mruoc.facade.AuthConfig;
import uk.co.mruoc.facade.FakeAuthConfig;
import uk.co.mruoc.facade.GitHubAuthConfig;
import uk.co.mruoc.facade.GoogleAuthConfig;

import javax.ws.rs.core.UriInfo;

public class AuthFactory {

    private final AuthConfig googleAuthConfig = new GoogleAuthConfig();
    private final AuthConfig gitHubAuthConfig = new GitHubAuthConfig();
    private final AuthConfig fakeAuthConfig = new FakeAuthConfig();

    public String getAuthUrl(UriInfo uriInfo, String type) {
        AuthConfig config = getAuthConfig(type);
        return config.getAuthUrl(uriInfo);
    }

    public boolean canAuth(String type) {
        switch(type.toLowerCase()) {
            case "google" : return googleAuthConfig.canAuth();
            case "github" : return gitHubAuthConfig.canAuth();
            default : return fakeAuthConfig.canAuth();
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
