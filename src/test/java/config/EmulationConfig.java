package config;

import org.aeonbits.owner.Config;

@Config.Sources("classpath:config/emulator.properties")
public interface EmulationConfig extends Config {

    @Key("device.name")
    String deviceName();

    @Key("os_version")
    String osVersion();

    @Key("appium.server.url")
    @DefaultValue("http://127.0.0.1:4723/")
    String appiumServerUrl();

    @Key("app.package")
    @DefaultValue("randomappsinc.com.sqlpracticeplus")
    String appPackage();

    @Key("app.activity")
    @DefaultValue("randomappsinc.com.sqlpractice.activities.SplashActivity")
    String appActivity();
}