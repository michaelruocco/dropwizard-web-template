package uk.co.mruoc.view;

import uk.co.mruoc.resources.view.SessionUser;

import javax.servlet.http.HttpSession;
import javax.ws.rs.core.UriInfo;

public class IndexView extends LoginableView {

    public IndexView(HttpSession session, UriInfo uriInfo) {
        this(new SessionUser(session), uriInfo);
    }

    public IndexView(SessionUser sessionUser, UriInfo uriInfo) {
        super("index.ftl", sessionUser, uriInfo);
    }

    public String getName() {
        return "Web Template";
    }

}
