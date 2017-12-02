package uk.co.mruoc.auth;

import uk.co.mruoc.http.client.SimpleHttpClient;

public class GitHubAuthenticator extends BaseAuthenticator {

    public GitHubAuthenticator() {
        this(new GitHubUserInfoConverter());
    }

    public GitHubAuthenticator(UserInfoConverter userInfoConverter) {
        super(new SimpleHttpClient(), new GitHubAuthConfig(), userInfoConverter);
    }

}
