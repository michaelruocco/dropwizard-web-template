package uk.co.mruoc.view;

import javax.servlet.http.HttpSession;
import javax.ws.rs.core.UriInfo;

public class IndexView extends AuthenticatedView {

    public IndexView(HttpSession session, UriInfo uriInfo) {
        super("index.ftl", session, uriInfo);
    }

    public String getName() {
        return "Web Template";
    }

}
