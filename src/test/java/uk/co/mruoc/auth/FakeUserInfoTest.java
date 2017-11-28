package uk.co.mruoc.auth;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class FakeUserInfoTest {

    private final UserInfo userInfo = new FakeUserInfo();

    @Test
    public void shouldReturnId() {
        assertThat(userInfo.getId()).isEqualTo("123456789");
    }

    @Test
    public void shouldReturnUsername() {
        assertThat(userInfo.getUsername()).isEqualTo("fake.user@web.template.co.uk");
    }

    @Test
    public void shouldReturnName() {
        assertThat(userInfo.getName()).isEqualTo("Fake User");
    }

    @Test
    public void shouldReturnPictureUrl() {
        assertThat(userInfo.getPictureUrl()).isEqualTo("https://cdn.pixabay.com/photo/2015/10/05/22/37/blank-profile-picture-973460_1280.png");
    }

}
