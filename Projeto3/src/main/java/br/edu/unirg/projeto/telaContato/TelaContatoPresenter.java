/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.unirg.projeto.telaContato;

import br.edu.unirg.projeto.bean.Contato;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.util.StringConverter;

/**
 *
 * @author rogerio
 */
public class TelaContatoPresenter implements Initializable {

    private ResourceBundle resources = null;
    private Integer indexTable;
    private Contato contato;

    @FXML
    private ChoiceBox<Integer> choiceTipo;
    @FXML
    private CheckBox checkPref;
    @FXML
    private TextField textContato;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.resources = resources;
        choiceTipo.getItems().addAll(Contato.EMAIL, Contato.FONE_CELULAR,
                Contato.FONE_COMERCIAL, Contato.FONE_RESIDENCIAL, Contato.SKYPE);
        choiceTipo.setConverter(new StringConverter<Integer>() {
            @Override
            public String toString(Integer object) {
                String texto = "------------";
                switch (object) {
                    case Contato.EMAIL:
                        texto = "Email";
                        break;
                    case Contato.FONE_CELULAR:
                        texto = "Telefone Celular";
                        break;
                    case Contato.FONE_COMERCIAL:
                        texto = "Telefone Comercial";
                        break;
                    case Contato.FONE_RESIDENCIAL:
                        texto = "Telefone Residencial";
                        break;
                    case Contato.SKYPE:
                        texto = "Skype";
                        break;
                    default: texto = "-------------";
                    break;
                }
                return texto;
            }

            @Override
            public Integer fromString(String string) {
                Integer intContato;
                switch (string) {
                    case "Email":
                        intContato = Contato.EMAIL;
                        break;
                    case "Telefone Celular":
                        intContato = Contato.FONE_CELULAR;
                        break;
                    case "Telefone Comercial":
                        intContato = Contato.FONE_COMERCIAL;
                        break;
                    case "Telefone Residencial":
                        intContato = Contato.FONE_RESIDENCIAL;
                        break;
                    case "Skype":
                        intContato = Contato.SKYPE;
                        break;
                    default: intContato = null;
                    break;
                }
                return intContato;
            }
        });
        contato = null;
        indexTable = null;
    }

    public void loadContato(Contato contatoSelected, int indexTable) {
        this.contato = contatoSelected;
        this.indexTable = indexTable;
        if (contato == null) {
            return;
        }
    }

    @FXML
    private void cancelarContato(ActionEvent event) {
        checkPref.getScene().getWindow().hide();
    }

    @FXML
    private void confirmarContato(ActionEvent event) {
    }

}
