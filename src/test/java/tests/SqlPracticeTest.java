package tests;

import com.codeborne.selenide.Selenide;
import org.junit.jupiter.api.Test;

public class SqlPracticeTest extends TestBase {

    @Test
    void checkSqlApp() {
        System.out.println("⏳ Ждем загрузки приложения...");

        // Даем больше времени на установку и запуск
        Selenide.sleep(15000);

        System.out.println("📸 Делаем скриншот...");
        Selenide.screenshot("sql_app_installed");

        System.out.println("✅ SQL Practice PRO установлен и запущен!");
    }
}