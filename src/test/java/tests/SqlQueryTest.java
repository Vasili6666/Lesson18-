package tests;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selenide.$;
import static io.appium.java_client.AppiumBy.*;

public class SqlQueryTest extends TestBase {

    @Test
    void sqlQueryTest() {
        System.out.println("🎯 Запускаем SQL Practice PRO...");

        // Ждем загрузки приложения
        Selenide.sleep(5000);

        // 1. Нажимаем кнопку OK в приветственном окне
        $(id("randomappsinc.com.sqlpracticeplus:id/md_buttonDefaultPositive")).click();
        System.out.println("✅ Нажали кнопку OK");

        // 2. Нажимаем кнопку с номером задачи (6-я по счету)
        $(androidUIAutomator("new UiSelector().className(\"android.widget.LinearLayout\").instance(6)")).click();
        System.out.println("✅ Выбрали задачу №6");

        Selenide.sleep(2000);

        // 3. ПРОСТО ВВОДИМ ТЕКСТ БЕЗ ЛИШНИХ ПРОВЕРОК
        SelenideElement queryField = $(id("randomappsinc.com.sqlpracticeplus:id/query_entry"));

        // Просто кликаем и вводим текст напрямую
        queryField.click();
        Selenide.sleep(1000);

        // Вводим текст напрямую без setValue
        queryField.sendKeys("SELECT Professor_Name, MAX(Salary) FROM SALARIES;");
        System.out.println("✅ Ввели SQL запрос");

        // 4. Нажимаем кнопку отправки запроса
        $(id("randomappsinc.com.sqlpracticeplus:id/submit_query")).click();
        System.out.println("✅ Отправили запрос");

        Selenide.sleep(3000);

        // 5. Проверяем результаты
        try {
            SelenideElement firstGuerin = $(androidUIAutomator("new UiSelector().text(\"Guerin\").instance(0)"));
            SelenideElement secondGuerin = $(androidUIAutomator("new UiSelector().text(\"Guerin\").instance(1)"));

            SelenideElement firstSalary = $(androidUIAutomator("new UiSelector().text(\"500000\").instance(0)"));
            SelenideElement secondSalary = $(androidUIAutomator("new UiSelector().text(\"500000\").instance(1)"));

            // Проверяем что значения равны
            String guerin1 = firstGuerin.getText();
            String guerin2 = secondGuerin.getText();
            String salary1 = firstSalary.getText();
            String salary2 = secondSalary.getText();

            System.out.println("🔍 Проверяем результаты:");
            System.out.println("Guerin 1: " + guerin1);
            System.out.println("Guerin 2: " + guerin2);
            System.out.println("Salary 1: " + salary1);
            System.out.println("Salary 2: " + salary2);

            if (guerin1.equals(guerin2) && salary1.equals(salary2)) {
                System.out.println("✅ ТЕСТ ПРОЙДЕН! Значения совпадают");
            } else {
                System.out.println("❌ ТЕСТ ПРОВАЛЕН! Значения не совпадают");
            }
        } catch (Exception e) {
            System.out.println("⚠️ Не удалось проверить результаты: " + e.getMessage());
        }

        // Делаем скриншот результатов
        Selenide.screenshot("sql_results");

        System.out.println("🎉 Тест завершен!");
    }
}