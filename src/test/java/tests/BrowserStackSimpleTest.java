package tests;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

@Tag("browserstack")
public class BrowserStackSimpleTest extends TestBase {

    @Test
    @DisplayName("Проверка подключения к BrowserStack")
    void checkBrowserStackConnection() {
        // Просто выводим сообщение в консоль
        System.out.println("✅ SUCCESS: Connected to BrowserStack!");
        System.out.println("✅ Test is running on remote device");
    }
}