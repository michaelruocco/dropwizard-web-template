package uk.co.mruoc.auth;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static uk.co.mruoc.auth.DefaultUserInfo.*;

public class DefaultUserInfoTest {

    private final DefaultUserInfoBuilder builder = new DefaultUserInfoBuilder();

    @Test
    public void shouldReturnId() {
        String id = "my-id";

        UserInfo userInfo = builder.setId(id).build();

        assertThat(userInfo.getId()).isEqualTo(id);
    }

    @Test
    public void shouldReturnUsername() {
        String username = "my-username";

        UserInfo userInfo = builder.setUsername(username).build();

        assertThat(userInfo.getUsername()).isEqualTo(username);
    }

    @Test
    public void shouldReturnName() {
        String name = "my-name";

        UserInfo userInfo = builder.setName(name).build();

        assertThat(userInfo.getName()).isEqualTo(name);
    }

    @Test
    public void shouldReturnPictureUrl() {
        String pictureUrl = "my-picture-url";

        UserInfo userInfo = builder.setPictureUrl(pictureUrl).build();

        assertThat(userInfo.getPictureUrl()).isEqualTo(pictureUrl);
    }

}
