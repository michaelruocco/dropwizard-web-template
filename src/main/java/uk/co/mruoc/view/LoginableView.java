package uk.co.mruoc.view;

import uk.co.mruoc.facade.UserInfo;

public interface LoginableView {

    boolean getLoggedIn();

    UserInfo getUserInfo();

    String getLoginUrl();

}
