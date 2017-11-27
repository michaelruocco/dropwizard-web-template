package uk.co.mruoc.facade;

public class DefaultUserInfo implements UserInfo {

    private final String id;
    private final String username;
    private final String name;
    private final String pictureUrl;

    public DefaultUserInfo(DefaultUserInfoBuilder builder) {
        this.id = builder.id;
        this.username = builder.username;
        this.name = builder.name;
        this.pictureUrl = builder.pictureUrl;
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getPictureUrl() {
        return pictureUrl;
    }

    public static class DefaultUserInfoBuilder {

        private String id;
        private String username;
        private String name;
        private String pictureUrl;

        public DefaultUserInfoBuilder setId(String id) {
            this.id = id;
            return this;
        }

        public DefaultUserInfoBuilder setUsername(String username) {
            this.username = username;
            return this;
        }

        public DefaultUserInfoBuilder setName(String name) {
            this.name = name;
            return this;
        }

        public DefaultUserInfoBuilder setPictureUrl(String pictureUrl) {
            this.pictureUrl = pictureUrl;
            return this;
        }

        public UserInfo build() {
            return new DefaultUserInfo(this);
        }

    }

}
