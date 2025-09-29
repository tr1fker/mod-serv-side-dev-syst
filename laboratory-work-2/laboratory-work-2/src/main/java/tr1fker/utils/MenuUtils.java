package tr1fker.utils;

import java.util.List;
/**
 * Класс MenuUtils описывает методы, общие для классов меню
 */
public class MenuUtils {
    public static String getHeader(final String title, final List<String> options) {
        StringBuilder header = new StringBuilder("\n\n[" + title + "]\n\n");
        for (int i = 0; i < options.size(); i++) {
            header.append(i + 1).append(") ").append(options.get(i)).append(".\n");
        }
        header.append("0) Выход.\n").append("\nВыберите: ");
        return header.toString();
    }
}
