
import controllers.MenuController;

import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException {


        MenuController menuController = new MenuController();
        menuController.start();
    }
}