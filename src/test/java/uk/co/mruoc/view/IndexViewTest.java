package uk.co.mruoc.view;

import org.junit.Test;
import uk.co.mruoc.resources.view.SessionUser;

import javax.servlet.http.HttpSession;

import static org.assertj.core.api.Java6Assertions.assertThat;
import static org.mockito.Mockito.mock;

public class IndexViewTest {

    private final HttpSession session = mock(HttpSession.class);
    private final IndexView view = new IndexView(new SessionUser("", session));

    @Test
    public void shouldReturnIndexViewTemplate() {
        assertThat(view.getTemplateName()).isEqualTo("/uk/co/mruoc/view/index.ftl");
    }

    @Test
    public void shouldReturnName() {
        assertThat(view.getName()).isEqualTo("Web Template");
    }

}
