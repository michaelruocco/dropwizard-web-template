package uk.co.mruoc.view;

import io.dropwizard.views.View;
import uk.co.mruoc.auth.DefaultAuthFactory;
import uk.co.mruoc.auth.UserInfo;
import uk.co.mruoc.auth.AuthFactory;
import uk.co.mruoc.auth.SessionUser;

import javax.servlet.http.HttpSession;
import javax.ws.rs.core.UriInfo;

public class LoginableView extends View {

    private final AuthFactory authFactory;
    private final SessionUser sessionUser;
    private final UriInfo uriInfo;

    public LoginableView(String templateName, HttpSession session, UriInfo uriInfo) {
        this(templateName, new DefaultAuthFactory(), session, uriInfo);
    }

    public LoginableView(String templateName, AuthFactory authFactory, HttpSession session, UriInfo uriInfo) {
        super(templateName);
        this.authFactory = authFactory;
        this.sessionUser = new SessionUser(session);
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
