package tr1fker.menus;

import tr1fker.utils.InputManager;
import tr1fker.utils.MenuUtils;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class MainMenu {
    private static final Logger logger = Logger.getLogger(MainMenu.class.getName());
    private final StatementMenu statementMenu = new StatementMenu();
    private final PreparedStatementMenu preparedStatementMenu = new
            PreparedStatementMenu();
    public void print() {
        List<String> options = new ArrayList<>();
        options.add("Работать с Statement");
        options.add("Работать с PreparedStatement");
        final String header = MenuUtils.getHeader("Main menu", options);
        while (true) {
            System.out.print(header);
            try {
                switch (InputManager.getNextInt()) {
                    case 1: {
                        statementMenu.print();
                        break;
                    }
                    case 2: {
                        preparedStatementMenu.print();
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
                logger.severe("Ошибка при обработке главного меню: " + e.getMessage());
            }
        }
    }
}