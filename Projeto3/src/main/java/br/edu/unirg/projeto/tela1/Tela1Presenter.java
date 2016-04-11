/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.unirg.projeto2.tela1;

import br.edu.unirg.projeto2.tela2.Tela2View;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.stage.Stage;

/**
 *
 * @author man1gold
 */
public class Tela1Presenter implements Initializable {

    private ResourceBundle resources = null;
    @FXML
    private ListView<String> listviewNomes;
    @FXML
    private Button btAdd;
    @FXML
    private Button btEdit;
    @FXML
    private Button btRemove;
    
    private Tela2View tela2View;
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.resources = resources;
    }
@FXML
    private void addNome(ActionEvent event) {
        try {
            tela2View = new Tela2View();
            tela2View.getRealPresenter().setIndexToEdit(null);
            Scene scene = new Scene(tela2View.getView());
            Stage stage = new Stage();
            stage.setTitle("Cadastro Alunos");
            stage.setScene(scene);
            stage.show();

        } catch (Exception ex) {
            Logger.getLogger(Tela1Presenter.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void editarNome(ActionEvent event) {
        if (listviewNomes.getSelectionModel().getSelectedItem() != null) {
            try {
                tela2View = new Tela2View();
                tela2View.getRealPresenter().setIndexToEdit(listviewNomes.getSelectionModel().getSelectedIndex());
                tela2View.getRealPresenter().getTextNome().setText(listviewNomes.getSelectionModel().getSelectedItem());
                Scene scene = new Scene(tela2View.getView());
                Stage stage = new Stage();
                stage.setTitle("Cadastro Alunos[Edit]");
                stage.setScene(scene);
                stage.show();

            } catch (Exception ex) {
                Logger.getLogger(Tela1Presenter.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @FXML
    private void removerNome(ActionEvent event) {
        if (listviewNomes.getSelectionModel().getSelectedItem() != null) {
            listviewNomes.getItems().remove(listviewNomes.getSelectionModel().getSelectedIndex());
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Resultado");
            alert.setContentText("Nome removido com sucesso");
            alert.show();
        }
    }

    public ListView<String> getListviewNomes() {
        return listviewNomes;
    }
    
}
