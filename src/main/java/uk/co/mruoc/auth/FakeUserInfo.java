package uk.co.mruoc.auth;

public class FakeUserInfo implements UserInfo {

    private static final String DEFAULT_USERNAME = "fake.user@web.template.co.uk";

    private final String username;

    public FakeUserInfo() {
        this(DEFAULT_USERNAME);
    }

    public FakeUserInfo(String username) {
        this.username = username;
    }

    @Override
    public String getId() {
        return "123456789";
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public String getName() {
        return "Fake User";
    }

    @Override
    public String getPictureUrl() {
        return "https://cdn.pixabay.com/photo/2015/10/05/22/37/blank-profile-picture-973460_1280.png";
    }

}
