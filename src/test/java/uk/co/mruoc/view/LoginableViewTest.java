package uk.co.mruoc.view;

import org.junit.Test;
import uk.co.mruoc.FakeAuthFactory;
import uk.co.mruoc.FakeHttpSession;
import uk.co.mruoc.FakeUriInfo;
import uk.co.mruoc.auth.AuthFactory;
import uk.co.mruoc.auth.FakeUserInfo;
import uk.co.mruoc.auth.UserInfo;

import javax.ws.rs.core.UriInfo;

import static org.assertj.core.api.Assertions.assertThat;

public class LoginableViewTest {

    private static final String TEMPLATE_NAME = "my-template.ftl";

    private final AuthFactory authFactory = new FakeAuthFactory();
    private final FakeHttpSession session = new FakeHttpSession();
    private final UriInfo uriInfo = new FakeUriInfo();

    private final LoginableView view = new LoginableView(TEMPLATE_NAME, authFactory, session, uriInfo);

    @Test
    public void shouldReturnTemplateName() {
        assertThat(view.getTemplateName()).isEqualTo("/uk/co/mruoc/view/" + TEMPLATE_NAME);
    }

    @Test
    public void shouldReturnLoggedInTrueIfSessionHasUser() {
        session.setLoggedInUser(new FakeUserInfo());

        assertThat(view.getLoggedIn()).isTrue();
    }

    @Test
    public void shouldReturnLoggedInFalseIfNoSessionUser() {
        assertThat(view.getLoggedIn()).isFalse();
    }

    @Test
    public void shouldReturnSessionUser() {
        UserInfo userInfo = new FakeUserInfo();
        session.setLoggedInUser(userInfo);

        assertThat(view.getUserInfo()).isEqualTo(userInfo);
    }

    @Test
    public void shouldReturnAuthUrlForType() {
        assertThat(view.getAuthUrl("google")).isEqualTo("authUrl/google");
        assertThat(view.getAuthUrl("github")).isEqualTo("authUrl/github");
        assertThat(view.getAuthUrl("")).isEqualTo("authUrl/");
    }

    @Test
    public void shouldReturnCanAuth() {
        assertThat(view.canAuth("google")).isTrue();
        assertThat(view.canAuth("github")).isFalse();
        assertThat(view.canAuth("")).isFalse();
    }

}
