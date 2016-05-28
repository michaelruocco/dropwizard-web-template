package uk.co.mruoc;

import javax.ws.rs.core.UriInfo;
import java.net.URI;
import java.net.URISyntaxException;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

public class MockUriInfoBuilder {

    private static final String BASE_URI = "http://localhost:8090";

    public UriInfo build(String contextPath) {
        try {
            UriInfo uriInfo = mock(UriInfo.class);
            URI baseUri = new URI(BASE_URI + contextPath);
            given(uriInfo.getBaseUri()).willReturn(baseUri);
            return uriInfo;
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }

}
