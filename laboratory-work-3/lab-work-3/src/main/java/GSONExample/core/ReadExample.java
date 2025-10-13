package GSONExample.core;


import GSONExample.model.Lesson;
import com.fatboyindustrial.gsonjavatime.Converters;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;

public class ReadExample {
    public static void main(String[] args) {
        Gson gson = Converters
                .registerLocalTime(new GsonBuilder())
                .create();

        try (Reader reader = new FileReader("D:\\Study\\Семестр5\\MSSDS\\laboratory-work-3\\lab-work-3\\src\\main\\java\\GSONExample\\lesson.json")) {
            Lesson lesson = gson.fromJson(reader, Lesson.class);
            System.out.println(lesson);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}