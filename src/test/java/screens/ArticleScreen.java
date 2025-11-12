package screens;

import static com.codeborne.selenide.Selenide.*;
import static io.appium.java_client.AppiumBy.*;

public class ArticleScreen {

    public boolean hasError() {
        return $(id("org.wikipedia.alpha:id/error_text")).exists();
    }

    public void goBack() {
        $(id("org.wikipedia.alpha:id/go_back_button")).click();
    }
}
