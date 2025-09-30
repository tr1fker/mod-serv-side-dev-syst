package tr1fker.menus;

import tr1fker.prepared_statement.action.PreparedStatementAction;
import tr1fker.utils.InputManager;
import tr1fker.utils.MenuUtils;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class PreparedStatementMenu {
    private static final Logger logger =
            Logger.getLogger(PreparedStatementMenu.class.getName());
    private final PreparedStatementAction preparedStatementAction = new
            PreparedStatementAction();
    public void print() {
        List<String> options = new ArrayList<>();
        options.add("Добавить студента");
        options.add("Обновить студента");
        options.add("Удалить студента");
        options.add("Найти по id");
        options.add("Найти по имени");
        options.add("Найти по фамилии");
        options.add("Найти по городу");
        options.add("Найти по email");
        options.add("Найти по возрасту");
        options.add("Отобразить всех студентов");
        options.add("Добавить дефолтную информацию");
        options.add("Очистить все таблицы");
        options.add("Просмотреть все записи");
        final String header = MenuUtils.getHeader("Меню PreparedStatement", options);
        while (true) {
            System.out.print(header);
            try {
                switch (InputManager.getNextInt()) {
                    case 1: {
                        preparedStatementAction.insertStudent();
                        break;
                    }
                    case 2: {
                        preparedStatementAction.updateStudent();
                        break;
                    }
                    case 3: {
                        preparedStatementAction.deleteStudent();
                        break;
                    }
                    case 4: {
                        preparedStatementAction.getStudentById();
                        break;
                    }
                    case 5: {
                        preparedStatementAction.getStudentsByFirstName();
                        break;
                    }
                    case 6: {
                        preparedStatementAction.getStudentsByLastName();
                        break;
                    }
                    case 7: {
                        preparedStatementAction.getStudentsByCity();
                        break;
                    }
                    case 8: {
                        preparedStatementAction.getStudentsByEmail();
                        break;
                    }
                    case 9: {
                        preparedStatementAction.getStudentsByAge();
                        break;
                    }
                    case 10: {
                        preparedStatementAction.getAllStudents();
                        break;
                    }
                    case 11: {
                        preparedStatementAction.addDefaultInfStudyGroupsSs();
                        break;
                    }
                    case 12: {
                        preparedStatementAction.deleteAllInfo();
                        break;
                    }
                    case 13: {
                        preparedStatementAction.getAllInfo();
                        break;
                    }
                    case 0: {
                        return;
                    }
                    default: {
                        System.out.println("Такого варианта нет!");
                        break;
                    }
                }
            } catch (Exception e) {
                logger.severe("Ошибка при обработке меню PreparedStatement: " +
                        e.getMessage());
            }
        }
    }
}