package screens;

import static com.codeborne.selenide.Selenide.*;
import static io.appium.java_client.AppiumBy.*;

public class SearchScreen {

    public void openSearch() {
        $(accessibilityId("Search Wikipedia")).click();
    }

    public void searchFor(String query) {
        $(id("org.wikipedia.alpha:id/search_src_text")).sendKeys(query);
    }

    public void openFirstResult() {
        $$(id("org.wikipedia.alpha:id/page_list_item_title"))
                .first()
                .click();
    }
}
