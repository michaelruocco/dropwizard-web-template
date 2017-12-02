package uk.co.mruoc.auth;

import javax.servlet.http.HttpSession;

public class SessionFakeId {

    private static final String FAKE_ID = "fakeID";

    private final HttpSession session;

    public SessionFakeId(HttpSession session) {
        this.session = session;
    }

    public boolean isPresent() {
        return get() != null;
    }

    public String get() {
        return (String) session.getAttribute(FAKE_ID);
    }

    public void set(String fakeId) {
        session.setAttribute(FAKE_ID, fakeId);
    }

    public void clear() {
        session.removeAttribute(FAKE_ID);
    }

}
