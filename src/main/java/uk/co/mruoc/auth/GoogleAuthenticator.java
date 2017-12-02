package uk.co.mruoc.auth;

import uk.co.mruoc.http.client.SimpleHttpClient;

public class GoogleAuthenticator extends BaseAuthenticator {

    public GoogleAuthenticator() {
        this(new GoogleUserInfoConverter());
    }

    public GoogleAuthenticator(UserInfoConverter userInfoConverter) {
        super(new SimpleHttpClient(), new GoogleAuthConfig(), userInfoConverter);
    }

}
