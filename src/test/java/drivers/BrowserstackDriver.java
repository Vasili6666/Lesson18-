package drivers;

import com.codeborne.selenide.WebDriverProvider;
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

        // --- Для App Automate устройство и версия должны быть на верхнем уровне ---
        caps.setCapability("device", "Google Pixel 7 Pro");
        caps.setCapability("os_version", "13.0");

        // --- Приложение (укажи свой app ID) ---
        caps.setCapability("app", "bs://sample.app");

        // --- Всё остальное передаётся через bstack:options ---
        Map<String, Object> bstackOptions = Map.of(
                "userName", "wert_xmAKrg",
                "accessKey", "uT7dVzwyyWncoVdHvWdE",
                "projectName", "First Java Project",
                "buildName", "browserstack-build-1",
                "sessionName", "first_test",
                "appiumVersion", "1.22.0"
        );

        caps.setCapability("bstack:options", bstackOptions);

        try {
            return new RemoteWebDriver(
                    new URL("https://hub.browserstack.com/wd/hub"), caps);
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }
}
