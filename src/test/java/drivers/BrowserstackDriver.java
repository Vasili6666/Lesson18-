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

        CredentialsConfig credentials = ConfigFactory.create(CredentialsConfig.class);
        BrowserstackConfig browserstackConfig = ConfigFactory.create(BrowserstackConfig.class);


        caps.setCapability("platformName", "android");
        caps.setCapability("appium:deviceName", browserstackConfig.device());
        caps.setCapability("appium:platformVersion", browserstackConfig.osVersion());
        caps.setCapability("appium:app", "bs://sample.app");
        caps.setCapability("appium:automationName", "UiAutomator2");


        MutableCapabilities bstackOptions = new MutableCapabilities();
        bstackOptions.setCapability("userName", credentials.userName());
        bstackOptions.setCapability("accessKey", credentials.accessKey());
        bstackOptions.setCapability("projectName", "Wikipedia Tests");
        bstackOptions.setCapability("buildName", "build-1");
        bstackOptions.setCapability("sessionName", "Mobile Tests");

        caps.setCapability("bstack:options", bstackOptions);

        try {
            System.out.println("🔧 Creating BrowserStack driver...");
            return new RemoteWebDriver(new URL(credentials.browserstackUrl()), caps);
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }
}