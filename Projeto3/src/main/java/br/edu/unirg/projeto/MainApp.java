package br.edu.unirg.projeto;

import br.edu.unirg.projeto.bean.Agenda;
import br.edu.unirg.projeto.bean.Contato;
import br.edu.unirg.projeto.tela1.Tela1View;
import com.airhacks.afterburner.injection.Injector;
import com.sun.xml.internal.messaging.saaj.packaging.mime.util.UUEncoderStream;
import java.sql.Date;
import java.time.Instant;
import java.time.LocalDate;
import java.time.Month;
import java.time.ZoneId;
import java.time.temporal.TemporalField;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.UUID;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class MainApp extends Application {
    
    private static Tela1View tela1View;
    
    private static ObservableList<Agenda> agendaList;
    @Override
    public void start(Stage stage) throws Exception {
        tela1View = new Tela1View();
        Scene scene = new Scene(tela1View.getView());
        stage.setTitle("Cadastro de alunos");
        stage.setScene(scene);
        stage.show();

        Contato email = new Contato();
        email.setDescricao("rogerio.mq@gmail.com");
        email.setPreferencial(true);
        email.setTipo(Contato.EMAIL);
        
        Contato celular = new Contato();
        celular.setDescricao("6392272369");
        celular.setPreferencial(true);
        celular.setTipo(Contato.FONE_CELULAR);
        
        Agenda rogerio = new Agenda();
        rogerio.setId(UUID.randomUUID().toString());
        rogerio.setNome("Rogério");
        rogerio.setSobrenome("M. de Queiroz");
        rogerio.setApelido("lindao");
        rogerio.setEndereco("AV. E N. 1221, QD.138A, Nova Fronteira");
        rogerio.setAniversario(
            Date.from(LocalDate.of(1985, Month.SEPTEMBER, 26).atStartOfDay().atZone(ZoneId.systemDefault()).toInstant())
        );
        rogerio.setContatos(new ArrayList<>());
        rogerio.getContatos().add(email);
        rogerio.getContatos().add(celular);
        
        getAgendaList().add(rogerio);
    }
    
    
    @Override
    public void stop() throws Exception {
        Injector.forgetAll();
    }

    public static Tela1View getTela1View() {
        return tela1View;
    }

    public static ObservableList<Agenda> getAgendaList() {
        if(agendaList == null) {
            agendaList = FXCollections.observableArrayList();
        }
        return agendaList;
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
