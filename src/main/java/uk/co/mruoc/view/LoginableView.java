package uk.co.mruoc.view;

import io.dropwizard.views.View;
import uk.co.mruoc.facade.UserInfo;
import uk.co.mruoc.resources.view.AuthFactory;
import uk.co.mruoc.resources.view.SessionUser;

import javax.servlet.http.HttpSession;
import javax.ws.rs.core.UriInfo;

public class LoginableView extends View {

    private final AuthFactory authFactory = new AuthFactory();
    private final SessionUser sessionUser;
    private final UriInfo uriInfo;

    public LoginableView(String templateName, HttpSession session, UriInfo uriInfo) {
        this(templateName, new SessionUser(session), uriInfo);
    }

    public LoginableView(String templateName, SessionUser sessionUser, UriInfo uriInfo) {
        super(templateName);
        this.sessionUser = sessionUser;
        this.uriInfo = uriInfo;
    }

    public boolean getLoggedIn() {
        return sessionUser.isPresent();
    }

    public UserInfo getUserInfo() {
        return sessionUser.getInfo();
    }

    public String getAuthUrl(String type) {
        return authFactory.getAuthUrl(uriInfo, type);
    }

    public boolean canAuth(String type) {
        return authFactory.canAuth(type);
    }

}
