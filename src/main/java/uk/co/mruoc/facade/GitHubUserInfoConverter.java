package uk.co.mruoc.facade;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import uk.co.mruoc.facade.DefaultUserInfo.DefaultUserInfoBuilder;

public class GitHubUserInfoConverter implements UserInfoConverter {

    private final JsonParser parser = new JsonParser();

    @Override
    public UserInfo fromJson(String jsonString) {
        JsonObject json = parser.parse(jsonString).getAsJsonObject();
        return new DefaultUserInfoBuilder()
                .setId(json.get("id").getAsString())
                .setUsername(json.get("login").getAsString())
                .setName(json.get("name").getAsString())
                .setPictureUrl(json.get("avatar_url").getAsString())
                .build();
    }

}
