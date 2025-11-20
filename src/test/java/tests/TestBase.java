package tests;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.logevents.SelenideLogger;
import drivers.BrowserstackDriver;
import drivers.EmulationDriver;
import drivers.RealDeviceDriver;
import helpers.Attach;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

import static com.codeborne.selenide.Selenide.closeWebDriver;
import static com.codeborne.selenide.Selenide.open;

public class TestBase {

    @BeforeAll
    static void setUp() {
        String deviceHost = System.getProperty("deviceHost", "emulation");
        String platform = System.getProperty("platform", "android");

        System.setProperty("platform", platform);

        System.out.println("🚀 Запуск тестов:");
        System.out.println("📱 Platform: " + platform);
        System.out.println("🏠 DeviceHost: " + deviceHost);

        switch (deviceHost) {
            case "browserstack":
                Configuration.browser = BrowserstackDriver.class.getName();
                // ОСОБЫЕ НАСТРОЙКИ ДЛЯ BROWSERSTACK
                setupBrowserStackConfig();
                break;
            case "emulation":
                Configuration.browser = EmulationDriver.class.getName();
                setupLocalConfig();
                break;
            case "real":
                Configuration.browser = RealDeviceDriver.class.getName();
                setupLocalConfig();
                break;
            default:
                throw new IllegalArgumentException("Unknown deviceHost: " + deviceHost);
        }
    }

    private static void setupBrowserStackConfig() {

        Configuration.browserSize = null;
        Configuration.timeout = 10000;
        Configuration.pageLoadStrategy = "none";
        Configuration.remoteReadTimeout = 60000;
        Configuration.remoteConnectionTimeout = 60000;
        System.setProperty("selenide.timeout", "10000");
        System.setProperty("selenide.pageLoadStrategy", "none");
    }

    private static void setupLocalConfig() {

        Configuration.browserSize = null;
        Configuration.timeout = 30000;
    }

    @BeforeEach
    void addAllureListener() {
        SelenideLogger.addListener("AllureSelenide", new AllureSelenide());

        String deviceHost = System.getProperty("deviceHost", "emulation");
        if ("browserstack".equals(deviceHost)) {
            System.out.println("🎬 Starting BrowserStack test...");
        }

        open();
    }

    @AfterEach
    void addAttachments() {
        String deviceHost = System.getProperty("deviceHost", "emulation");

        try {
            String sessionId = Selenide.sessionId().toString();
            System.out.println("📎 Session ID: " + sessionId);

            Attach.pageSource();

            if ("browserstack".equals(deviceHost)) {
                Attach.addVideo(sessionId);
            }
        } catch (Exception e) {
            System.out.println("⚠️ Could not get session ID for attachments: " + e.getMessage());
        }

        closeWebDriver();
    }
}