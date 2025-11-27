package helpers;

import io.appium.java_client.android.AndroidDriver;
import io.qameta.allure.Attachment;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

public class VideoAttach {

    @Attachment(value = "Video recording", type = "video/mp4")
    public static byte[] attachVideo() {
        try {
            AndroidDriver driver = (AndroidDriver) getWebDriver();

            System.out.println("🛑 Останавливаем запись видео...");

            // Останавливаем запись и получаем видео в base64
            String videoBase64 = driver.stopRecordingScreen();

            if (videoBase64 == null || videoBase64.isEmpty()) {
                System.out.println("❌ Видео не было записано (пустой результат)");
                return new byte[0];
            }

            System.out.println("📹 Получены данные видео, размер base64: " + videoBase64.length());

            // Декодируем base64 в байты
            byte[] videoData = java.util.Base64.getDecoder().decode(videoBase64);

            if (videoData.length == 0) {
                System.out.println("❌ Видео записалось как пустой файл");
                return new byte[0];
            }

            // Сохраняем видео в файл для диагностики
            String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd_HH-mm-ss"));
            saveVideoToFile(videoData, "test-video-" + timestamp);

            System.out.println("✅ Видео успешно прикреплено (" + videoData.length + " bytes)");
            return videoData;

        } catch (Exception e) {
            System.out.println("❌ Не удалось прикрепить видео: " + e.getMessage());
            e.printStackTrace();
            return new byte[0];
        }
    }

    private static void saveVideoToFile(byte[] videoData, String fileName) {
        try {
            Path videoPath = Paths.get("build/videos/" + fileName + ".mp4");
            Files.createDirectories(videoPath.getParent());
            Files.write(videoPath, videoData);
            System.out.println("💾 Видео сохранено в: " + videoPath.toAbsolutePath());
        } catch (Exception e) {
            System.out.println("⚠️ Не удалось сохранить видео в файл: " + e.getMessage());
        }
    }
}