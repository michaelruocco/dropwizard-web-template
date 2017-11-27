package uk.co.mruoc.view;

import org.junit.Test;
import uk.co.mruoc.FakeUriInfo;

import javax.servlet.http.HttpSession;
import javax.ws.rs.core.UriInfo;

import static org.assertj.core.api.Java6Assertions.assertThat;
import static org.mockito.Mockito.mock;

public class IndexViewTest {

    private final HttpSession session = mock(HttpSession.class);
    private final UriInfo uriInfo = new FakeUriInfo();
    private final IndexView view = new IndexView(session, uriInfo);

    @Test
    public void shouldReturnIndexViewTemplate() {
        assertThat(view.getTemplateName()).isEqualTo("/uk/co/mruoc/view/index.ftl");
    }

    @Test
    public void shouldReturnName() {
        assertThat(view.getName()).isEqualTo("Web Template");
    }

}
