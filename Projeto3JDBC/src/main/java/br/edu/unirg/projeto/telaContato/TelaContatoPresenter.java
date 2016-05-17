/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.unirg.projeto.telaContato;

import br.edu.unirg.projeto.bean.Contato;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
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
    private Integer indexTableContato;
    private Contato contato;
    private ObservableList<Contato> contatos;
    private String registroId;

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
                    default:
                        texto = "-------------";
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
                    default:
                        intContato = null;
                        break;
                }
                return intContato;
            }
        });
        contato = null;
        indexTableContato = null;
    }

    public void loadContato(Contato contatoSelected, Integer indexTable,
            ObservableList<Contato> contatos, String registroId) {
        this.contato = contatoSelected;
        this.indexTableContato = indexTable;
        this.registroId = registroId;
        this.contatos = contatos;
        if (contato == null) {
            return;
        } else { //carregar contato na tela para edição
            choiceTipo.setValue(contato.getTipo());
            checkPref.setSelected(contato.isPreferencial());
            textContato.setText(contato.getDescricao());
            choiceTipo.requestFocus();
        }

    }

    @FXML
    private void cancelarContato(ActionEvent event) {
        checkPref.getScene().getWindow().hide();
    }

    @FXML
    private void confirmarContato(ActionEvent event) {
        if (contato == null) { //caso seja um contato NOVO
            contato = new Contato();
            indexTableContato = null;
        }
        contato.setTipo(choiceTipo.getValue());
        contato.setPreferencial(checkPref.isSelected());
        contato.setDescricao(textContato.getText());
        contato.setRegistro_id(registroId);
        if (indexTableContato == null) {
            contatos.add(contato); //inserir na tabela
        } else {
            contatos.set(indexTableContato, contato); //atualizar na tabela
        }
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmação");
        alert.setHeaderText("Contato registrado!");
        alert.setContentText("Operação[ok].");
        alert.show();
        choiceTipo.getScene().getWindow().hide(); //fechando a tela de contato.
    }

}
