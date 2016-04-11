/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.unirg.projeto.tela2;

import br.edu.unirg.projeto.MainApp;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;

/**
 *
 * @author man1gold
 */
public class Tela2Presenter implements Initializable {

    private ResourceBundle resources = null;
    @FXML
    private TextField textNome;
    private Integer indexToEdit;
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.resources = resources;
       
    }

   @FXML
    private void confirmarNome(ActionEvent event) {
        String nome = textNome.getText();
        if(indexToEdit == null) {
            //MainApp.getTela1View().getRealPresenter().getListviewNomes().getItems().add(nome);
        } else {
            //MainApp.getTela1View().getRealPresenter().getListviewNomes().getItems().set(indexToEdit, nome);
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
