package drivers;

import com.codeborne.selenide.WebDriverProvider;
import config.BrowserstackConfig;
import config.CredentialsConfig;
import org.aeonbits.owner.ConfigFactory;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;

import javax.annotation.Nonnull;
import java.net.MalformedURLException;
import java.net.URL;

public class BrowserstackDriver implements WebDriverProvider {

    @Nonnull
    @Override
    public WebDriver createDriver(@Nonnull Capabilities capabilities) {
        MutableCapabilities caps = new MutableCapabilities();

        String platform = System.getProperty("platform", "android");
        System.setProperty("platform", platform);

        CredentialsConfig credentials = ConfigFactory.create(CredentialsConfig.class);
        BrowserstackConfig browserstackConfig = ConfigFactory.create(BrowserstackConfig.class);

        // Основные capabilities
        caps.setCapability("platformName", "android");
        caps.setCapability("appium:app", credentials.app());
        caps.setCapability("appium:automationName", "UiAutomator2");
        caps.setCapability("appium:deviceName", browserstackConfig.device());  // deviceName
        caps.setCapability("appium:platformVersion", browserstackConfig.osVersion());  // platformVersion

        // BrowserStack options
        MutableCapabilities bstackOptions = new MutableCapabilities();
        bstackOptions.setCapability("userName", credentials.userName());
        bstackOptions.setCapability("accessKey", credentials.accessKey());
        bstackOptions.setCapability("projectName", "Wikipedia Tests");
        bstackOptions.setCapability("buildName", "build-1");
        bstackOptions.setCapability("sessionName", "Onboarding Test");

        caps.setCapability("bstack:options", bstackOptions);

        try {
            return new RemoteWebDriver(
                    new URL(credentials.browserstackUrl()), caps);
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }
}