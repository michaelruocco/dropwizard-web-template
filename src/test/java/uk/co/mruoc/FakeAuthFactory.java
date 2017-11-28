package uk.co.mruoc;

import uk.co.mruoc.auth.AuthFactory;
import uk.co.mruoc.auth.Authenticator;
import uk.co.mruoc.auth.FakeAuthenticator;

import javax.ws.rs.core.UriInfo;

public class FakeAuthFactory implements AuthFactory {

    @Override
    public String getAuthUrl(UriInfo uriInfo, String type) {
        return "authUrl/" + type;
    }

    @Override
    public boolean canAuth(String type) {
        return type.toLowerCase().equals("google");
    }

    @Override
    public Authenticator getAuthenticator(String type) {
        return new FakeAuthenticator();
    }

}
