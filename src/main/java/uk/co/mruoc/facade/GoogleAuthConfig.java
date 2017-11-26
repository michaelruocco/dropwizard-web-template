package uk.co.mruoc.facade;

public interface GoogleAuthConfig {

    String getAuthUrl();

    String getClientId();

    String getClientSecret();

    String getRedirectResource();

    String getTokenUrl();

    String getUserInfoUrl();

}
