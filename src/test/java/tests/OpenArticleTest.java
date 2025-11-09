package tests;

import org.junit.jupiter.api.Test;
import static com.codeborne.selenide.CollectionCondition.sizeGreaterThan;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;
import static io.appium.java_client.AppiumBy.*;
import static io.qameta.allure.Allure.step;

public class OpenArticleTest extends TestBase {

    @Test
    void openArticleTest() {
        step("Open search input", () -> {
            $(accessibilityId("Search Wikipedia")).click();
        });

        step("Type search query 'Appium'", () -> {
            $(id("org.wikipedia.alpha:id/search_src_text")).sendKeys("Appium");
        });

        step("Click the first search result", () -> {
            $$(id("org.wikipedia.alpha:id/page_list_item_title"))
                    .first()
                    .click();
        });

        step("Verify article page is opened", () -> {
            $(id("org.wikipedia.alpha:id/view_page_title_text"))
                    .shouldBe(visible);
        });
    }
}
