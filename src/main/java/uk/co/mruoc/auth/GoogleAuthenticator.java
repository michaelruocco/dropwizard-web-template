package uk.co.mruoc.auth;

import uk.co.mruoc.http.client.SimpleHttpClient;

public class GoogleAuthenticator extends BaseAuthenticator {

    public GoogleAuthenticator() {
        super(new SimpleHttpClient(), new GoogleAuthConfig(), new GoogleUserInfoConverter());
    }

}
