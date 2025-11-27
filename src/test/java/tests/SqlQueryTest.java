package tests;

import helpers.Attach;
import io.qameta.allure.Allure;
import org.junit.jupiter.api.Test;
import screens.SqlPracticeScreen;

import static com.codeborne.selenide.Selenide.screenshot;

public class SqlQueryTest extends TestBase {

    @Test
    void sqlQueryTest() {
        Allure.description("Тест проверяет выполнение SQL запроса в приложении SQL Practice PRO");

        SqlPracticeScreen sqlScreen = new SqlPracticeScreen();

        sqlScreen
                .clickOkButton()
                .selectTask()
                .enterSqlQuery()
                .submitQuery()
                .verifyResults();

        screenshot("sql_query_results");
        Attach.screenshotAs("SQL Query Results");
        Attach.pageSource();

        System.out.println("🎉 Тест успешно завершен!");
    }
}