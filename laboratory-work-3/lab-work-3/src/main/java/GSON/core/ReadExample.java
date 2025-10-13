package GSON.core;

import GSONExample.model.Lesson;
import com.fatboyindustrial.gsonjavatime.Converters;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.lang.reflect.Type;
import java.time.LocalTime;
import java.util.List;

public class ReadExample {
    public static void main(String[] args) {
        Gson gson = Converters
                .registerLocalTime(new GsonBuilder())
                .create();

        try (Reader reader = new FileReader("D:\\Study\\Семестр5\\MSSDS\\laboratory-work-3\\lab-work-3\\src\\main\\java\\GSON\\lessons.json")) {

            Type lessonsListType = new TypeToken<List<Lesson>>(){}.getType();

            List<Lesson> lessons = gson.fromJson(reader, lessonsListType);

            printLessonsInfo(lessons);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void printLessonsInfo(List<Lesson> lessons) {
        System.out.println("=== ИНФОРМАЦИЯ ОБ УРОКАХ ===\n");

        if (lessons == null || lessons.isEmpty()) {
            System.out.println("Уроки не найдены!");
            return;
        }

        System.out.println("Всего уроков: " + lessons.size());
        System.out.println("=" .repeat(50));

        for (int i = 0; i < lessons.size(); i++) {
            Lesson lesson = lessons.get(i);
            System.out.println("УРОК " + (i + 1) + ":");
            System.out.println("  Предмет: " + lesson.getSubject());
            System.out.println("  Продолжительность: " + formatDuration(lesson.getDuration()));
            System.out.println("  Темы: " + String.join(", ", lesson.getTopics()));

            // Дополнительная информация
            System.out.println("  Количество тем: " + lesson.getTopics().size());
            System.out.println("-".repeat(30));
        }

        // Статистика
        printStatistics(lessons);
    }

    private static String formatDuration(LocalTime duration) {
        if (duration == null) return "Не указано";
        return duration.getHour() + " ч " + duration.getMinute() + " мин";
    }

    private static void printStatistics(List<Lesson> lessons) {
        System.out.println("\n=== СТАТИСТИКА ===");

        int totalTopics = 0;
        int totalMinutes = 0;

        for (Lesson lesson : lessons) {
            if (lesson.getTopics() != null) {
                totalTopics += lesson.getTopics().size();
            }
            if (lesson.getDuration() != null) {
                totalMinutes += lesson.getDuration().getHour() * 60 + lesson.getDuration().getMinute();
            }
        }

        System.out.println("Общее количество тем: " + totalTopics);
        System.out.println("Общая продолжительность: " + (totalMinutes / 60) + " ч " + (totalMinutes % 60) + " мин");
        System.out.println("Среднее количество тем на урок: " + (double) totalTopics / lessons.size());
        System.out.println("Средняя продолжительность урока: " + (totalMinutes / lessons.size()) + " мин");
    }
}