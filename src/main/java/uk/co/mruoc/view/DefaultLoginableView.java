package uk.co.mruoc.view;

import io.dropwizard.views.View;
import uk.co.mruoc.facade.UserInfo;
import uk.co.mruoc.resources.view.SessionUser;

public class DefaultLoginableView extends View implements LoginableView {

    private final SessionUser sessionUser;

    public DefaultLoginableView(String templateName, SessionUser sessionUser) {
        super(templateName);
        this.sessionUser = sessionUser;
    }

    @Override
    public boolean getLoggedIn() {
        return sessionUser.isPresent();
    }

    @Override
    public UserInfo getUserInfo() {
        return sessionUser.getInfo();
    }

    @Override
    public String getLoginUrl() {
        return sessionUser.getLoginUrl();
    }

}
