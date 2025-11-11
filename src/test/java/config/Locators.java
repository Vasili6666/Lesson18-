package config;

public class Locators {

    private static final String platform = System.getProperty("platform", "android").toLowerCase();

    // Локатор кнопки поиска
    public static String searchButton() {
        return platform.equals("android") ? "Search Wikipedia" : "Search Wikipedia";
    }

    // Поле ввода запроса
    public static String searchField() {
        return platform.equals("android") ? "org.wikipedia.alpha:id/search_src_text" : "Search Wikipedia";
    }

    // Первый результат поиска
    public static String firstResult() {
        return platform.equals("android") ? "org.wikipedia.alpha:id/page_list_item_title" : "//*[@name='Selenide']";
    }

    // Локатор ошибки
    public static String errorText() {
        return platform.equals("android") ? "org.wikipedia.alpha:id/error_text" : "Error"; // пример для iOS
    }

    // Кнопка закрытия ошибки
    public static String goBackButton() {
        return platform.equals("android") ? "org.wikipedia.alpha:id/go_back_button" : "Back";
    }
}
