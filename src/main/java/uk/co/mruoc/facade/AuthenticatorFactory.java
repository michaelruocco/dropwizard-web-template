package uk.co.mruoc.facade;

public class AuthenticatorFactory {

    public Authenticator build() {
        return build(getAuthenticatorType());
    }

    private String getAuthenticatorType() {
        if (System.getenv().containsKey("APP_AUTH_TYPE"))
            return System.getenv("APP_AUTH_TYPE");
        return "fake";
    }

    public Authenticator build(String type) {
        switch (type.toLowerCase()) {
            case "google" : return new GoogleAuthenticator();
            default : return new FakeAuthenticator();
        }
    }

}
