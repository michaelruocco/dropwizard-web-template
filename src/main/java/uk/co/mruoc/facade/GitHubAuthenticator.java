package uk.co.mruoc.facade;

import uk.co.mruoc.http.client.*;

public class GitHubAuthenticator extends BaseAuthenticator {

    public GitHubAuthenticator() {
        super(new SimpleHttpClient(), new GitHubAuthConfig(), new GitHubUserInfoConverter());
    }

}
