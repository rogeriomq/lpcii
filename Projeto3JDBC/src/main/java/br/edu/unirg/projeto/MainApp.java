package br.edu.unirg.projeto;

import br.edu.unirg.projeto.bean.Registro;
import br.edu.unirg.projeto.conexao.ConexaoDB;
import br.edu.unirg.projeto.conexao.dao.RegistroDAO;
import br.edu.unirg.projeto.tela1.Tela1View;
import com.airhacks.afterburner.injection.Injector;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.stage.Stage;
import static javafx.application.Application.launch;


public class MainApp extends Application {
    
    private static Tela1View tela1View;
    
    private static ObservableList<Registro> agendaList;
    @Override
    public void start(Stage stage) throws Exception {
        RegistroDAO registroDAO = new RegistroDAO();
        
        tela1View = new Tela1View();
        Scene scene = new Scene(tela1View.getView());
        stage.setTitle("|_AGENDA - REGISTROS_|");
        stage.setScene(scene);
        stage.show();
       
        getAgendaList().addAll(registroDAO.findAllRegistros());
        /*
        Contato email = new Contato();
        email.setDescricao("rogerio.mq@gmail.com");
        email.setPreferencial(false);
        email.setTipo(Contato.EMAIL);
        
        Contato celular = new Contato();
        celular.setDescricao("6392272369");
        celular.setPreferencial(true);
        celular.setTipo(Contato.FONE_CELULAR);
        
        Registro rogerio = new Registro();
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
        
        Registro x = new Registro();
        x.setId(UUID.randomUUID().toString());
        x.setNome("XXXXXXXXXXXXXXXXXXXx");
        x.setSobrenome("M. de Queiroz");
        x.setApelido("xxxxxx");
        x.setEndereco("xxxxx xxxxxxxxxxx xxxxxxxxxxxx xxxxxxxxxx");
        x.setAniversario(
            Date.from(LocalDate.of(1985, Month.SEPTEMBER, 26).atStartOfDay().atZone(ZoneId.systemDefault()).toInstant())
        );
        x.setContatos(new ArrayList<>());
       
        
        getAgendaList().add(rogerio);
        getAgendaList().add(x);
        */
    }
    
    
    @Override
    public void stop() throws Exception {
        Injector.forgetAll();
    }

    public static Tela1View getTela1View() {
        return tela1View;
    }

    public static ObservableList<Registro> getAgendaList() {
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
