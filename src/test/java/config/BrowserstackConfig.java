package config;

import org.aeonbits.owner.Config;

@Config.Sources({
        "classpath:config/android.properties",
        "system:properties",
        "system:env"
})
public interface BrowserstackConfig extends Config {

    @Key("deviceName")
    String device();

    @Key("platformVersion")
    String osVersion();


    default boolean isBrowserstack() {
        String deviceHost = System.getProperty("deviceHost", "emulation");
        return "browserstack".equals(deviceHost);
    }
}