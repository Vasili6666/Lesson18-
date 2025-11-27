package tests;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.logevents.SelenideLogger;
import drivers.EmulationDriver;
import helpers.Attach;
import helpers.VideoAttach;
import io.appium.java_client.android.AndroidDriver;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

import static com.codeborne.selenide.WebDriverRunner.getWebDriver;
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

        // Просто запускаем запись без параметров
        startVideoRecording();
    }

    @AfterEach
    void addAttachments() {
        System.out.println("📸 Сбор вложений для отчета...");

        // Прикрепляем видео
        VideoAttach.attachVideo();

        Attach.screenshotAs("Final screenshot");
        Attach.pageSource();
        closeWebDriver();
    }

    private void startVideoRecording() {
        try {
            AndroidDriver driver = (AndroidDriver) getWebDriver();
            // Простой запуск записи без параметров
            driver.startRecordingScreen();
            System.out.println("🎥 Запись видео запущена");
        } catch (Exception e) {
            System.out.println("⚠️ Не удалось запустить запись видео: " + e.getMessage());
        }
    }
}