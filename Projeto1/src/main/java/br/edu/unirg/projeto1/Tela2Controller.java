/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.unirg.projeto1;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author man1gold
 */
public class Tela2Controller implements Initializable {

    @FXML
    private TextField textNome;
    private Integer indexToEdit;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void confirmarNome(ActionEvent event) {
        String nome = textNome.getText();
        if(indexToEdit == null) {
            MainApp.getTela1Controller().getListviewNomes().getItems().add(nome);
        } else {
            MainApp.getTela1Controller().getListviewNomes().getItems().set(indexToEdit, nome);
        }
        textNome.getScene().getWindow().hide();
    }

    @FXML
    private void cancelarSair(ActionEvent event) {
        textNome.getScene().getWindow().hide();
    }

    public void setIndexToEdit(Integer indexToEdit) {
        this.indexToEdit = indexToEdit;
    }

    public TextField getTextNome() {
        return textNome;
    }
    
    
    
    
}
