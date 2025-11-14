package config;

import org.aeonbits.owner.Config;

@Config.Sources("classpath:config/credentials.properties")
public interface CredentialsConfig extends Config {

    @Key("user.name")
    String userName();

    @Key("access.key")
    String accessKey();

    @Key("browserstack.url")
    String browserstackUrl();

    @Key("app")
    String app();
}