/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.unirg.projeto.tela1.linelist;

import br.edu.unirg.projeto.bean.Registro;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

/**
 *
 * @author rogerio
 */
public class LinelistPresenter implements Initializable {

    private ResourceBundle resources = null;
    @FXML
    private Label labelNomeSobrenome;
    @FXML
    private Label labelApelido;
    @FXML
    private Label labelEndereco;
    @FXML
    private Button btDell;
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.resources = resources;
    }
    
    public void loadObjAgenda(Registro agenda) {
        labelNomeSobrenome.setText(agenda.getNome() + " " + agenda.getSobrenome());
        labelApelido.setText(agenda.getApelido());
        labelEndereco.setText(agenda.getEndereco());
    }

}
