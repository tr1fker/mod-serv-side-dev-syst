package GSONExample.core;

import GSONExample.model.Lesson;
import com.fatboyindustrial.gsonjavatime.Converters;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Modifier;
import java.time.LocalTime;
import java.util.List;

public class WriteExample {
    public static void main(String[] args) {
        Gson gson = Converters
                .registerLocalTime(new GsonBuilder())
                .excludeFieldsWithModifiers(Modifier.STATIC, Modifier.TRANSIENT)
                .setPrettyPrinting()
                .create();
        Lesson lesson = createLesson();

        final String jsonData = gson.toJson(lesson);
        try (FileWriter writer = new FileWriter("D:\\Study\\Семестр5\\MSSDS\\laboratory-work-3\\lab-work-3\\src\\main\\java\\GSONExample\\lesson.json")) {
            writer.write(jsonData);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static Lesson createLesson() {
        Lesson lesson = new Lesson();

        lesson.setSubject("Математика");
        lesson.setDuration(LocalTime.of(0, 45));
        lesson.setTopics(List.of("Дискриминант", "Логарифмы", "Теорема Пифагора"));

        return lesson;
    }
}