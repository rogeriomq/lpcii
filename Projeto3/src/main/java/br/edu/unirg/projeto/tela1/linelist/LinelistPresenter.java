/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.unirg.projeto.tela1.linelist;

import br.edu.unirg.projeto.bean.Agenda;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;

/**
 *
 * @author rogerio
 */
public class LinelistPresenter implements Initializable {

    private ResourceBundle resources = null;
    
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.resources = resources;
    }
    
    public void loadObjAgenda(Agenda agenda) {
        
    }
    
}
