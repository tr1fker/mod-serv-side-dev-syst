package GSON.core;

import GSONExample.model.Lesson;
import com.fatboyindustrial.gsonjavatime.Converters;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Modifier;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class WriteExample {
    public static void main(String[] args) {
        Gson gson = Converters
                .registerLocalTime(new GsonBuilder())
                .excludeFieldsWithModifiers(Modifier.STATIC, Modifier.TRANSIENT)
                .setPrettyPrinting()
                .create();

        List<Lesson> lessons = createLessonsArray();

        final String jsonData = gson.toJson(lessons);
        try (FileWriter writer = new FileWriter("D:\\Study\\Семестр5\\MSSDS\\laboratory-work-3\\lab-work-3\\src\\main\\java\\GSON\\lessons.json")) {
            writer.write(jsonData);
            System.out.println("Массив уроков успешно записан в файл!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static List<Lesson> createLessonsArray() {
        List<Lesson> lessons = new ArrayList<>();

        Lesson lesson1 = new Lesson();
        lesson1.setSubject("Математика");
        lesson1.setDuration(LocalTime.of(0, 45));
        lesson1.setTopics(List.of("Дискриминант", "Логарифмы", "Теорема Пифагора"));
        lessons.add(lesson1);

        Lesson lesson2 = new Lesson();
        lesson2.setSubject("Физика");
        lesson2.setDuration(LocalTime.of(1, 0));
        lesson2.setTopics(List.of("Законы Ньютона", "Термодинамика", "Электричество"));
        lessons.add(lesson2);

        Lesson lesson3 = new Lesson();
        lesson3.setSubject("Информатика");
        lesson3.setDuration(LocalTime.of(1, 30));
        lesson3.setTopics(List.of("Программирование", "Алгоритмы", "Базы данных"));
        lessons.add(lesson3);

        Lesson lesson4 = new Lesson("Химия", LocalTime.of(0, 50),
                List.of("Периодическая таблица", "Химические реакции", "Органическая химия"));
        lessons.add(lesson4);

        return lessons;
    }
}