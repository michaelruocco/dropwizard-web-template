package uk.co.mruoc.resources.view;

import org.junit.Test;

import static org.assertj.core.api.Java6Assertions.assertThat;

public class IndexViewResourceTest {

    private IndexViewResource resource = new IndexViewResource();

    @Test
    public void shouldReturnIndexView() {
        assertThat(resource.getIndex()).isNotNull();
    }

}
