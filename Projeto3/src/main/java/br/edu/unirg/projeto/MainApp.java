package br.edu.unirg.projeto2;

import br.edu.unirg.projeto2.tela1.Tela1View;
import com.airhacks.afterburner.injection.Injector;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class MainApp extends Application {
    private static Tela1View tela1View;
    @Override
    public void start(Stage stage) throws Exception {
        tela1View = new Tela1View();
        Scene scene = new Scene(tela1View.getView());
        stage.setTitle("Cadastro de alunos");
        stage.setScene(scene);
        stage.show();
    }
    
    
    @Override
    public void stop() throws Exception {
        Injector.forgetAll();
    }

    public static Tela1View getTela1View() {
        return tela1View;
    }
    /**
     * The main() method is ignored in correctly deployed JavaFX application.
     * main() serves only as fallback in case the application can not be
     * launched through deployment artifacts, e.g., in IDEs with limited FX
     * support. NetBeans ignores main().
     *
     * @param args the command line arguments
     */
    
    
    public static void main(String[] args) {
        launch(args);
    }

}
