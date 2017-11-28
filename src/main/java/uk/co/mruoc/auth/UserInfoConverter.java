package uk.co.mruoc.auth;

public interface UserInfoConverter {

    UserInfo fromJson(String jsonString);

}
