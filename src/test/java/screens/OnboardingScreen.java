package screens;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static io.appium.java_client.AppiumBy.id;

public class OnboardingScreen {

    private final SelenideElement
            primaryTextView = $(id("org.wikipedia.alpha:id/primaryTextView")),
            continueButton = $(id("org.wikipedia.alpha:id/fragment_onboarding_forward_button")),
            acceptButton = $(id("org.wikipedia.alpha:id/acceptButton"));

    @Step("Проверить текст на первом экране онбординга")
    public OnboardingScreen checkFirstScreenText() {
        primaryTextView.shouldHave(text("The Free Encyclopedia"));
        return this;
    }

    @Step("Проверить текст на втором экране онбординга")
    public OnboardingScreen checkSecondScreenText() {
        primaryTextView.shouldHave(text("New ways to explore"));
        return this;
    }

    @Step("Проверить текст на третьем экране онбординга")
    public OnboardingScreen checkThirdScreenText() {
        primaryTextView.shouldHave(text("Reading lists with sync"));
        return this;
    }

    @Step("Проверить текст на четвертом экране онбординга")
    public OnboardingScreen checkFourthScreenText() {
        primaryTextView.shouldHave(text("Data & Privacy"));
        return this;
    }

    @Step("Нажать кнопку Continue")
    public OnboardingScreen clickContinue() {
        continueButton.click();
        return this;
    }

    @Step("Нажать кнопку Accept")
    public OnboardingScreen clickAccept() {
        acceptButton.click();
        return this;
    }
}