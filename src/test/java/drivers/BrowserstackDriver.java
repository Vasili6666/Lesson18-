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

// Класс, который создаёт драйвер для Selenide/Appium на BrowserStack
// Он реализует интерфейс WebDriverProvider — это стандарт Selenide для кастомных драйверов
public class BrowserstackDriver implements WebDriverProvider {

    // Гарантирует, что метод никогда не вернёт null
    @Nonnull
    @Override
    public WebDriver createDriver(@Nonnull Capabilities capabilities) {
        // Создаём "mutable" объект для настроек драйвера — можно добавлять или менять capabilities
        MutableCapabilities caps = new MutableCapabilities();

        // Получаем платформу из системных свойств (если не задано, ставим "android" по умолчанию)
        String platform = System.getProperty("platform", "android");
        System.setProperty("platform", platform); // сохраняем это свойство, чтобы оно было доступно в других местах

        // Загружаем настройки доступа к BrowserStack и общие настройки платформы через OWNER
        CredentialsConfig credentials = ConfigFactory.create(CredentialsConfig.class); // учётные данные
        BrowserstackConfig browserstackConfig = ConfigFactory.create(BrowserstackConfig.class); // устройство, ОС

        // Устанавливаем capabilities — параметры для BrowserStack
        caps.setCapability("browserstack.user", credentials.userName()); // логин BrowserStack
        caps.setCapability("browserstack.key", credentials.accessKey()); // ключ доступа
        caps.setCapability("app", credentials.app()); // идентификатор приложения для теста
        caps.setCapability("device", browserstackConfig.device()); // модель устройства
        caps.setCapability("os_version", browserstackConfig.osVersion()); // версия ОС
        caps.setCapability("project", "First Java Project"); // название проекта
        caps.setCapability("build", "browserstack-build-1"); // идентификатор сборки
        caps.setCapability("name", "first_test"); // название конкретного теста

        try {
            // Создаём новый RemoteWebDriver — он будет управлять устройством в облаке BrowserStack
            return new RemoteWebDriver(
                    new URL(credentials.browserstackUrl()), caps); // URL BrowserStack + capabilities
        } catch (MalformedURLException e) {
            // Если URL задан неправильно, выбрасываем RuntimeException (тест не сможет стартовать)
            throw new RuntimeException(e);
        }
    }
}