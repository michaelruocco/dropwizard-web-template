package uk.co.mruoc.facade;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import uk.co.mruoc.facade.DefaultUserInfo.DefaultUserInfoBuilder;

public class UserInfoConverter {

    private final JsonParser parser = new JsonParser();

    public UserInfo fromJson(String jsonString) {
        JsonObject json = parser.parse(jsonString).getAsJsonObject();
        return new DefaultUserInfoBuilder()
                .setId(json.get("id").getAsString())
                .setEmail(json.get("email").getAsString())
                .setName(json.get("name").getAsString())
                .setPictureUrl(json.get("picture").getAsString())
                .build();
    }

}
