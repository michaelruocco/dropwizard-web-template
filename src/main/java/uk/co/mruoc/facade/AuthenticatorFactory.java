package uk.co.mruoc.facade;

public class AuthenticatorFactory {

    public Authenticator build(String type) {
        switch (type.toLowerCase()) {
            case "google" : return new GoogleAuthenticator();
            case "github" : return new GitHubAuthenticator();
            default : return new FakeAuthenticator();
        }
    }

}
