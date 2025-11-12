package tests;//

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import screens.SearchScreen;
import screens.ArticleScreen;

public class OpenArticleTest extends TestBase {

    SearchScreen search = new SearchScreen();
    ArticleScreen article = new ArticleScreen();

    @Test
    @DisplayName("Открытие статьи — успешный результат вне зависимости от ошибок")
    void openArticleTest() {

        search.openSearch();
        search.searchFor("Appium");
        search.openFirstResult();

        if (article.hasError()) {
            article.goBack();
        }
    }
}
