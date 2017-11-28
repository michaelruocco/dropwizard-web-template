package uk.co.mruoc.auth;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import uk.co.mruoc.auth.DefaultUserInfo.DefaultUserInfoBuilder;

public class GoogleUserInfoConverter implements UserInfoConverter {

    private final JsonParser parser = new JsonParser();

    @Override
    public UserInfo fromJson(String jsonString) {
        JsonObject json = parser.parse(jsonString).getAsJsonObject();
        return new DefaultUserInfoBuilder()
                .setId(json.get("id").getAsString())
                .setUsername(json.get("email").getAsString())
                .setName(json.get("name").getAsString())
                .setPictureUrl(json.get("picture").getAsString())
                .build();
    }

}
