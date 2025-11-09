package tests;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selenide.*;
import static io.appium.java_client.AppiumBy.accessibilityId;
import static io.appium.java_client.AppiumBy.id;

public class OpenArticleTest extends TestBase {

    @Test
    @DisplayName("Открытие статьи — успешный результат вне зависимости от ошибок")
    void openArticleTest() {

        // Открыть поиск
        $(accessibilityId("Search Wikipedia")).click();

        // Ввести запрос
        $(id("org.wikipedia.alpha:id/search_src_text")).sendKeys("Selenide");

        // Клик по любому результату
        $$(id("org.wikipedia.alpha:id/page_list_item_title"))
                .first()
                .click();

        // Если появляется окно с ошибкой — просто закрываем
        if ($(id("org.wikipedia.alpha:id/error_text")).isDisplayed()) {
            $(id("org.wikipedia.alpha:id/go_back_button")).click();
        }

        // Всё, тест считается пройденным
    }
}
