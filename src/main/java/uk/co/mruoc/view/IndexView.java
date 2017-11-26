package uk.co.mruoc.view;

import uk.co.mruoc.resources.view.SessionUser;

public class IndexView extends DefaultLoginableView {

    public IndexView(SessionUser sessionUser) {
        super("index.ftl", sessionUser);
    }

    public String getName() {
        return "Web Template";
    }

}
