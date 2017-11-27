package uk.co.mruoc.facade;

import uk.co.mruoc.http.client.*;

public class GoogleAuthenticator extends BaseAuthenticator {

    public GoogleAuthenticator() {
        super(new SimpleHttpClient(), new GoogleAuthConfig(), new GoogleUserInfoConverter());
    }

}
