package uk.co.mruoc.resources.view;

import uk.co.mruoc.facade.UserInfo;

import javax.servlet.http.HttpSession;

public class SessionUser {

    private static final String ATTRIBUTE_NAME = "userInfo";

    private final String loginUrl;
    private final HttpSession session;

    public SessionUser(String loginUrl, HttpSession session) {
        this.loginUrl = loginUrl;
        this.session = session;
    }

    public String getLoginUrl() {
        return loginUrl;
    }

    public boolean isPresent() {
        return getInfo() != null;
    }

    public UserInfo getInfo() {
        return (UserInfo) session.getAttribute(ATTRIBUTE_NAME);
    }

    public void setInfo(UserInfo userInfo) {
        session.setAttribute(ATTRIBUTE_NAME, userInfo);
    }

}
