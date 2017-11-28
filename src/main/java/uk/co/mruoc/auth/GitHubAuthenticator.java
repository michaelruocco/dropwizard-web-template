package uk.co.mruoc.auth;

import uk.co.mruoc.http.client.SimpleHttpClient;

public class GitHubAuthenticator extends BaseAuthenticator {

    public GitHubAuthenticator() {
        super(new SimpleHttpClient(), new GitHubAuthConfig(), new GitHubUserInfoConverter());
    }

}
