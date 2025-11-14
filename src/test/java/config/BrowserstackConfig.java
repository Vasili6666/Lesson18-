package config;

import org.aeonbits.owner.Config;

@Config.Sources({
        "classpath:config/${platform}.properties",
        "system:properties",
        "system:env"
})
public interface BrowserstackConfig extends Config {

    @Key("deviceName")
    String device();

    @Key("platformVersion")
    String osVersion();
}