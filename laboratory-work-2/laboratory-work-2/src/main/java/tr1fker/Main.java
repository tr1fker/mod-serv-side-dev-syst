package tr1fker;

import tr1fker.connection.ConnectionManager;
import tr1fker.menus.MainMenu;
import java.util.logging.Logger;
public class Main {
    private static final Logger logger = Logger.getLogger(Main.class.getName());
    public static void main(String[] args) {
        if (ConnectionManager.getConnection() == null) { // установка соединения с БД
            return;
        }
        try {
            new MainMenu().print();
        } catch (Exception e) {
            logger.severe("Ошибка при обработке главного меню: " + e.getMessage());
        }
        ConnectionManager.closeConnection();
    }
}