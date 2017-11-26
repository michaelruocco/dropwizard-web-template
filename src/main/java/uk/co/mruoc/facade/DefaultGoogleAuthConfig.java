package uk.co.mruoc.facade;

public class DefaultGoogleAuthConfig implements GoogleAuthConfig {

    @Override
    public String getAuthUrl() {
        return "https://accounts.google.com/o/oauth2/auth";
    }

    @Override
    public String getClientId() {
        return System.getenv("APP_GOOGLE_CLIENT_ID");
    }

    @Override
    public String getClientSecret() {
        return System.getenv("APP_GOOGLE_CLIENT_SECRET");
    }

    @Override
    public String getRedirectResource() {
        return "oauth2callback";
    }

    @Override
    public String getTokenUrl() {
        return "https://accounts.google.com/o/oauth2/token";
    }

    @Override
    public String getUserInfoUrl() {
        return "https://www.googleapis.com/oauth2/v1/userinfo";
    }

}
