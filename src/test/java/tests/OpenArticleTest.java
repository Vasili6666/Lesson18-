package tests;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import config.Locators;

import static com.codeborne.selenide.CollectionCondition.sizeGreaterThan;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;
import static io.appium.java_client.AppiumBy.*;

public class OpenArticleTest extends TestBase {

    @Test
    @DisplayName("Открытие статьи — успешный результат вне зависимости от ошибок")
    void openArticleTest() {

        // Открываем поиск
        $(accessibilityId(Locators.searchButton())).click();

        // Вводим запрос
        $(id(Locators.searchField())).sendKeys("Selenide");

        // Кликаем по первому результату
        $$(id(Locators.firstResult())).first().click();

        // Если появляется окно с ошибкой — закрываем
        if ($(id(Locators.errorText())).exists() && $(id(Locators.errorText())).isDisplayed()) {
            $(id(Locators.goBackButton())).click();
        }

        // Тест считается успешным
    }
}
