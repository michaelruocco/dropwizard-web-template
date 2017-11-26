package uk.co.mruoc.facade;

public class FakeUserInfo implements UserInfo {

    @Override
    public String getId() {
        return "123456789";
    }

    @Override
    public String getEmail() {
        return "fake.user@web.template.co.uk";
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
