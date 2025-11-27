// [file name]: tests/TestBase.java
package tests;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.logevents.SelenideLogger;
import drivers.EmulationDriver;
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
        Configuration.browser = EmulationDriver.class.getName();
        Configuration.browserSize = null;
        Configuration.timeout = 60000;

        SelenideLogger.addListener("AllureSelenide", new AllureSelenide());

        System.out.println("🚀 Настройка тестового окружения...");
    }

    @BeforeEach
    void startDriver() {
        System.out.println("🎬 Инициализация драйвера...");
        open();
    }

    @AfterEach
    void addAttachments() {
        System.out.println("📸 Сбор вложений для отчета...");
        Attach.screenshotAs("Final screenshot");
        Attach.pageSource();
        closeWebDriver();
    }
}