package tests;

/*      ./gradlew clean test -DdeviceHost=emulation -Dplatform=android
        ./gradlew clean test -DdeviceHost=real -Dplatform=android
        ./gradlew clean test -DdeviceHost=browserstack -Dplatform=android
*/
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import screens.OnboardingScreen;

import static io.qameta.allure.Allure.step;

public class OnboardingTest extends TestBase {

    OnboardingScreen onboarding = new OnboardingScreen();

    @Test
    @DisplayName("Прохождение онбординга и проверка текста на всех экранах")
    void completeOnboardingTest() {
        step("Проверить текст на первом экране и нажать Continue", () -> {
            onboarding.checkFirstScreenText()
                    .clickContinue();
        });

        step("Проверить текст на втором экране и нажать Continue", () -> {
            onboarding.checkSecondScreenText()
                    .clickContinue();
        });

        step("Проверить текст на третьем экране и нажать Continue", () -> {
            onboarding.checkThirdScreenText()
                    .clickContinue();
        });

        step("Проверить текст на четвертом экране", () -> {
            onboarding.checkFourthScreenText();
            // На последнем экране не нажимаем кнопку, только проверяем текст
        });
    }
}