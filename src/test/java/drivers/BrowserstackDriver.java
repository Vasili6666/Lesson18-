package drivers;

import com.codeborne.selenide.WebDriverProvider;
import config.ConfigProvider;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;

import javax.annotation.Nonnull;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Map;

public class BrowserstackDriver implements WebDriverProvider {

    @Nonnull
    @Override
    public WebDriver createDriver(@Nonnull Capabilities capabilities) {
        MutableCapabilities caps = new MutableCapabilities();

        // Устройство и версия
        caps.setCapability("device", ConfigProvider.get("device"));
        caps.setCapability("os_version", ConfigProvider.get("os_version"));

        // Приложение
        caps.setCapability("app", ConfigProvider.get("app"));

        // Настройки через bstack:options
        Map<String, Object> bstackOptions = Map.of(
                "userName", ConfigProvider.get("userName"),
                "accessKey", ConfigProvider.get("accessKey"),
                "projectName", ConfigProvider.get("projectName"),
                "buildName", ConfigProvider.get("buildName"),
                "sessionName", ConfigProvider.get("sessionName"),
                "appiumVersion", ConfigProvider.get("appiumVersion"),
                "owner", ConfigProvider.get("owner")
        );

        caps.setCapability("bstack:options", bstackOptions);

        try {
            return new RemoteWebDriver(new URL(ConfigProvider.get("remoteUrl")), caps);
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }
}
