package uk.co.mruoc.resources.view;

import org.junit.Test;
import uk.co.mruoc.FakeUriInfo;

import javax.servlet.http.HttpSession;
import javax.ws.rs.core.UriInfo;

import static org.assertj.core.api.Java6Assertions.assertThat;
import static org.mockito.Mockito.mock;

public class IndexViewResourceTest {

    private final HttpSession session = mock(HttpSession.class);
    private final UriInfo uriInfo = new FakeUriInfo();

    private final IndexViewResource resource = new IndexViewResource();

    @Test
    public void shouldReturnIndexView() {
        assertThat(resource.getIndex(uriInfo, session)).isNotNull();
    }

}
