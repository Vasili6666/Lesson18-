package tests;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selenide.$;
import static io.appium.java_client.AppiumBy.id;
import static com.codeborne.selenide.Condition.text;

public class OnboardingTest extends TestBase {

    @Test
    @DisplayName("Прохождение 2 экранов onboarding в Wikipedia")
    void twoOnboardingScreensTest() {

        // 1 экран
        $(id("org.wikipedia:id/primaryTextView"))
                .shouldHave(text("The free encyclopedia"));
        $(id("org.wikipedia:id/fragment_onboarding_forward_button")).click();

        // 2 экран
        $(id("org.wikipedia:id/primaryTextView"))
                .shouldHave(text("New ways to explore"));
        $(id("org.wikipedia:id/fragment_onboarding_forward_button")).click();
    }
}
