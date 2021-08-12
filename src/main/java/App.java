import javafx.application.Application;
import javafx.stage.Stage;
import util.HibernateUtil;

public class App extends Application {
    public static void main(String[] args) {
        HibernateUtil.getSessionFactory();
    }
    @Override
    public void start(Stage stage) throws Exception {

    }
}
