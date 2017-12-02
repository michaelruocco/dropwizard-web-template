package uk.co.mruoc.view;

import javax.servlet.http.HttpSession;
import javax.ws.rs.core.UriInfo;

public class LoginView extends AuthenticatedView {

    public LoginView(HttpSession session, UriInfo uriInfo) {
        super("fakeLogin.ftl", session, uriInfo);
    }

    public String getUserId() {
        return "fake.user@web.template.co.uk";
    }

}
