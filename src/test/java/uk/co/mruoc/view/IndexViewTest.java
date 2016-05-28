package uk.co.mruoc.view;

import org.junit.Test;

import static org.assertj.core.api.Java6Assertions.assertThat;

public class IndexViewTest {

    private final IndexView view = new IndexView();

    @Test
    public void shouldReturnIndexViewTemplate() {
        assertThat(view.getTemplateName()).isEqualTo("/uk/co/mruoc/view/index.ftl");
    }

    @Test
    public void shouldReturnName() {
        assertThat(view.getName()).isEqualTo("Web Template");
    }

}
