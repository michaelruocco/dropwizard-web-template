package uk.co.mruoc.resources.view;

import io.dropwizard.views.View;
import org.junit.Test;
import uk.co.mruoc.FakeHttpSession;
import uk.co.mruoc.FakeUriInfo;
import uk.co.mruoc.auth.FakeUserInfo;
import uk.co.mruoc.view.IndexView;

import javax.ws.rs.core.UriInfo;

import static org.assertj.core.api.Assertions.assertThat;

public class LogoutViewResourceTest {

    private final FakeHttpSession session = new FakeHttpSession();
    private final UriInfo uriInfo = new FakeUriInfo();

    private final LogoutViewResource resource = new LogoutViewResource();

    @Test
    public void shouldRemoveUserFromSession() {
        session.setLoggedInUser(new FakeUserInfo());

        resource.doLogout(session, uriInfo);

        assertThat(session.hasLoggedInUser()).isFalse();
    }

    @Test
    public void shouldReturnIndexView() {
        session.setLoggedInUser(new FakeUserInfo());

        View view = resource.doLogout(session, uriInfo);

        assertThat(view).isInstanceOf(IndexView.class);
    }

}
