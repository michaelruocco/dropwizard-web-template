package uk.co.mruoc.auth;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.io.IOException;
import java.net.URI;

@AuthenticatedResource
public class AuthLoginFilter implements ContainerRequestFilter {

    private final String loginPath;

    @Context
    protected HttpServletRequest request;

    public AuthLoginFilter() {
        this("/");
    }

    public AuthLoginFilter(String loginPath) {
        this.loginPath = loginPath;
    }

    @Override
    public void filter(ContainerRequestContext requestContext) throws IOException {
        HttpSession session = request.getSession();
        SessionUser user = new SessionUser(session);
        if (!user.isPresent()) {
            UriInfo uriInfo = requestContext.getUriInfo();
            URI uri = uriInfo.getBaseUriBuilder().path(loginPath).build();
            Response response = Response.seeOther(uri).build();
            throw new WebApplicationException(response);
        }
    }

}
