package uk.co.mruoc.auth;

import org.junit.Test;
import uk.co.mruoc.FakeHttpSession;
import uk.co.mruoc.FakeUriInfo;

import javax.servlet.http.HttpSession;
import javax.ws.rs.core.UriInfo;

import static org.assertj.core.api.Assertions.assertThat;

public class FakeAuthenticatorTest {

    private final UriInfo uriInfo = new FakeUriInfo();
    private final FakeHttpSession session = new FakeHttpSession();
    private final SessionFakeId fakeId = new SessionFakeId(session);

    private final Authenticator authenticator = new FakeAuthenticator();

    @Test
    public void shouldPopulateFakeUserInfoOnSession() {
        fakeId.set("fake.user@web.template.co.uk");

        HttpSession result = authenticator.handleCallback(uriInfo, session, "fake", "my-state");

        assertThat(result.getAttribute("userInfo")).isEqualToComparingFieldByField(new FakeUserInfo());
    }

}
