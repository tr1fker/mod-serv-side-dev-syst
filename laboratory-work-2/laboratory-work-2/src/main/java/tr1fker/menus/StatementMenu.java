package tr1fker.menus;

import tr1fker.statement.action.StatementAction;
import tr1fker.utils.InputManager;
import tr1fker.utils.MenuUtils;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class StatementMenu {
    private static final Logger logger = Logger.getLogger(StatementMenu.class.getName());
    private final StatementAction statementAction = new StatementAction();
    public void print() {
        List<String> options = new ArrayList<>();
        options.add("Очистить все таблицы");
        options.add("Просмотреть все записи");
        options.add("Добавить дефолтную информацию");
        options.add("Просмотреть отсортированных авторов по имени и фамилии");
        options.add("Просмотреть книги изданные после определённого года, отсортировав их по году публикации");
        options.add("Обновить название случайной книги, находящейся в определённом магазине");
        options.add("Просмотреть книги с именами авторов начинающиеся на определённую букву");
        final String header = MenuUtils.getHeader("Меню Statement", options);
        while (true) {
            System.out.print(header);
            try {
                switch (InputManager.getNextInt()) {
                    case 1: {
                        statementAction.deleteAllInfo();
                        break;
                    }
                    case 2: {
                        statementAction.getAllInfo();
                        break;
                    }
                    case 3: {
                        statementAction.addDefaultInfo();
                        break;
                    }
                    case 4: {
                        statementAction.getAuthorsInfSrtFNLN();
                        break;
                    }
                    case 5: {
                        statementAction.getBooksSrtYByYear();
                        break;
                    }
                    case 6: {
                        statementAction.updateNameRandBookByShop();
                        break;
                    }
                    case 7: {
                        statementAction.getAllBooksByFLAuth();
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
                logger.severe("Ошибка при обработке меню Statement: " + e.getMessage());
            }
        }
    }
}