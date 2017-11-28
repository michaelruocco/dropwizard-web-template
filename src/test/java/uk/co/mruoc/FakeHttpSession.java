package uk.co.mruoc;

import uk.co.mruoc.auth.UserInfo;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionContext;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

public class FakeHttpSession implements HttpSession {

    private final Map<String, Object> sessionAttributes = new HashMap<>();

    @Override
    public long getCreationTime() {
        return 0;
    }

    @Override
    public String getId() {
        return null;
    }

    @Override
    public long getLastAccessedTime() {
        return 0;
    }

    @Override
    public ServletContext getServletContext() {
        return null;
    }

    @Override
    public void setMaxInactiveInterval(int interval) {
        // intentionally blank
    }

    @Override
    public int getMaxInactiveInterval() {
        return 0;
    }

    @Override
    public HttpSessionContext getSessionContext() {
        return null;
    }

    @Override
    public Object getAttribute(String name) {
        return sessionAttributes.get(name);
    }

    @Override
    public Object getValue(String name) {
        return null;
    }

    @Override
    public Enumeration<String> getAttributeNames() {
        return null;
    }

    @Override
    public String[] getValueNames() {
        return new String[0];
    }

    @Override
    public void setAttribute(String name, Object value) {
        sessionAttributes.put(name, value);
    }

    @Override
    public void putValue(String name, Object value) {
        // intentionally blank
    }

    @Override
    public void removeAttribute(String name) {
        sessionAttributes.remove(name);
    }

    @Override
    public void removeValue(String name) {
        // intentionally blank
    }

    @Override
    public void invalidate() {
        // intentionally blank
    }

    @Override
    public boolean isNew() {
        return false;
    }

    public void setLoggedInUser(UserInfo userInfo) {
        sessionAttributes.put("userInfo", userInfo);
    }

    public UserInfo getLoggedInUser() {
        return (UserInfo) sessionAttributes.get("userInfo");
    }

    public boolean hasLoggedInUser() {
        return getLoggedInUser() != null;
    }

}
