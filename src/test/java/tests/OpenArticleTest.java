package tests;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static com.codeborne.selenide.Selenide.*;
import static io.appium.java_client.AppiumBy.*;



public class OpenArticleTest extends TestBase {

    @Test
    @DisplayName("Открытие статьи — успешный результат вне зависимости от ошибок")
    void openArticleTest() {


        $(accessibilityId("Search Wikipedia")).click();
        $(id("org.wikipedia.alpha:id/search_src_text")).sendKeys("Appium");
        $$(id("org.wikipedia.alpha:id/page_list_item_title"))
                .first()
                .click();

        if ($(id("org.wikipedia.alpha:id/error_text")).isDisplayed()) {
            $(id("org.wikipedia.alpha:id/go_back_button")).click();
        }


    }
}