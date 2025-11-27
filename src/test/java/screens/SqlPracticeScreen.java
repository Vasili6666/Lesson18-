// [file name]: screens/SqlPracticeScreen.java
package screens;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.sleep;
import static io.appium.java_client.AppiumBy.*;

public class SqlPracticeScreen {

    private final SelenideElement
            okButton = $(id("randomappsinc.com.sqlpracticeplus:id/md_buttonDefaultPositive")),
            taskButton = $(androidUIAutomator("new UiSelector().className(\"android.widget.LinearLayout\").instance(6)")),
            queryField = $(id("randomappsinc.com.sqlpracticeplus:id/query_entry")),
            submitButton = $(id("randomappsinc.com.sqlpracticeplus:id/submit_query"));

    private static final String SQL_QUERY = "SELECT Professor_Name, MAX(Salary) FROM SALARIES;";

    @Step("Нажать кнопку OK в приветственном окне")
    public SqlPracticeScreen clickOkButton() {
        okButton.shouldBe(visible).click();
        return this;
    }

    @Step("Выбрать задачу №6")
    public SqlPracticeScreen selectTask() {
        taskButton.shouldBe(visible).click();
        sleep(2000);
        return this;
    }

    @Step("Ввести SQL запрос")
    public SqlPracticeScreen enterSqlQuery() {
        queryField.shouldBe(visible).click();
        sleep(1000);
        queryField.sendKeys(SQL_QUERY);
        return this;
    }

    @Step("Отправить SQL запрос")
    public SqlPracticeScreen submitQuery() {
        submitButton.shouldBe(visible).click();
        sleep(3000);
        return this;
    }

    @Step("Проверить результаты выполнения запроса")
    public SqlPracticeScreen verifyResults() {
        SelenideElement firstGuerin = $(androidUIAutomator("new UiSelector().text(\"Guerin\").instance(0)"));
        SelenideElement secondGuerin = $(androidUIAutomator("new UiSelector().text(\"Guerin\").instance(1)"));
        SelenideElement firstSalary = $(androidUIAutomator("new UiSelector().text(\"500000\").instance(0)"));
        SelenideElement secondSalary = $(androidUIAutomator("new UiSelector().text(\"500000\").instance(1)"));

        String guerin1 = firstGuerin.getText();
        String guerin2 = secondGuerin.getText();
        String salary1 = firstSalary.getText();
        String salary2 = secondSalary.getText();

        if (guerin1.equals(guerin2) && salary1.equals(salary2)) {
            System.out.println("✅ ТЕСТ ПРОЙДЕН! Значения совпадают");
        } else {
            throw new AssertionError("❌ ТЕСТ ПРОВАЛЕН! Значения не совпадают");
        }

        return this;
    }

    public String getSqlQuery() {
        return SQL_QUERY;
    }
}