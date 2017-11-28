package uk.co.mruoc.resources.rest;

import io.dropwizard.views.View;
import org.junit.Test;
import uk.co.mruoc.FakeHttpSession;
import uk.co.mruoc.FakeUriInfo;
import uk.co.mruoc.auth.AuthFactory;
import uk.co.mruoc.auth.DefaultAuthFactory;
import uk.co.mruoc.auth.FakeUserInfo;
import uk.co.mruoc.view.IndexView;

import javax.ws.rs.core.UriInfo;

import static org.assertj.core.api.Assertions.assertThat;

public class OAuthCallbackResourceTest {

    private final AuthFactory authFactory = new DefaultAuthFactory();
    private final UriInfo uriInfo = new FakeUriInfo();
    private final FakeHttpSession session = new FakeHttpSession();

    private final OAuthCallbackResource resource = new OAuthCallbackResource(authFactory);

    @Test
    public void shouldHandleCallbackFromAuthenticator() {
        resource.auth(uriInfo, session, "fake", "my-code", "my-state");

        assertThat(session.getLoggedInUser()).isEqualToComparingFieldByField(new FakeUserInfo());
    }

    @Test
    public void shouldReturnIndexView() {
        View view = resource.auth(uriInfo, session, "fake", "my-code", "my-state");

        assertThat(view).isInstanceOf(IndexView.class);
    }

}
