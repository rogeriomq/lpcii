/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.unirg.projeto1;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author man1gold
 */
public class Tela1Controller implements Initializable {

    @FXML
    private ListView<String> listviewNomes;
    @FXML
    private Button btAdd;
    @FXML
    private Button btEdit;
    @FXML
    private Button btRemove;

    private Tela2Controller tela2Controller;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

    @FXML
    private void addNome(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/Tela2.fxml"));
            Parent root = loader.load();
            tela2Controller = loader.getController();
            tela2Controller.setIndexToEdit(null);
            Scene scene = new Scene(root);
            scene.getStylesheets().add("/styles/Styles.css");
            Stage stage = new Stage();
            stage.setTitle("Castro Alunos");
            stage.setScene(scene);
            stage.show();

        } catch (IOException ex) {
            Logger.getLogger(Tela1Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void editarNome(ActionEvent event) {
        if (listviewNomes.getSelectionModel().getSelectedItem() != null) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/Tela2.fxml"));
                Parent root = loader.load();
                tela2Controller = loader.getController();
                tela2Controller.setIndexToEdit(listviewNomes.getSelectionModel().getSelectedIndex());
                tela2Controller.getTextNome().setText(listviewNomes.getSelectionModel().getSelectedItem());
                Scene scene = new Scene(root);
                scene.getStylesheets().add("/styles/Styles.css");
                Stage stage = new Stage();
                stage.setTitle("Castro Alunos");
                stage.setScene(scene);
                stage.show();

            } catch (IOException ex) {
                Logger.getLogger(Tela1Controller.class.getName()).log(Level.SEVERE, null, ex);
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
