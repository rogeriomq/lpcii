/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.unirg.projeto.tela2;

import br.edu.unirg.projeto.MainApp;
import br.edu.unirg.projeto.bean.Registro;
import br.edu.unirg.projeto.bean.Contato;
import br.edu.unirg.projeto.conexao.dao.RegistroDAO;
import br.edu.unirg.projeto.telaContato.TelaContatoView;
import java.net.URL;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.UUID;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.CheckBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 *
 * @author man1gold
 */
public class Tela2Presenter implements Initializable {

    private ResourceBundle resources = null;
    private Integer indexToEdit;
    private String idAgenda;
    private TelaContatoView telaContatoView;
    @FXML
    private TextField textNome;
    @FXML
    private TextField textSobrenome;
    @FXML
    private TextField textApelido;
    @FXML
    private DatePicker pickerNasc;
    @FXML
    private TableView<Contato> tableContatos;
    @FXML
    private TableColumn<Contato, Integer> tcTipo;
    @FXML
    private TableColumn<Contato, String> tcDesc;
    @FXML
    private TableColumn<Contato, Boolean> tcPref;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.resources = resources;
        setupTableContatos();
    }

    private void setupTableContatos() {
        tcTipo.setCellValueFactory(new PropertyValueFactory<>("tipo"));
        tcTipo.setCellFactory((TableColumn<Contato, Integer> param) -> {
            return new TableCell<Contato, Integer>() {
                @Override
                protected void updateItem(Integer item, boolean empty) {
                    super.updateItem(item, empty);
                    setAlignment(Pos.CENTER);
                    if (item != null && !empty) {
                        System.out.println("Tipo = " + item);
                        switch (item) {
                            case Contato.EMAIL:
                                setText("@");
                                break;
                            case Contato.FONE_CELULAR:
                                setText("Cel");
                                break;
                            case Contato.FONE_COMERCIAL:
                                setText("Job");
                                break;
                            case Contato.FONE_RESIDENCIAL:
                                setText("Home");
                                break;
                            case Contato.SKYPE:
                                setText("Skype");
                                break;
                        }
                    } else {
                        setText(null);
                    }
                }
            };
        });
        tcDesc.setCellValueFactory(new PropertyValueFactory<>("descricao"));
        tcPref.setCellValueFactory(new PropertyValueFactory<>("preferencial"));
        tcPref.setCellFactory((TableColumn<Contato, Boolean> param) -> {
            return new TableCell<Contato, Boolean>() {
                @Override
                public void updateItem(Boolean item, boolean empty) {
                    super.updateItem(item, empty);
                    CheckBox checkBox = new CheckBox();
                    checkBox.setDisable(true);
                    if (item != null && !empty) {
                        checkBox.setSelected(item);
                        setGraphic(checkBox);
                    } else {
                        setGraphic(null);
                    }

                }

            };
        });
    }

    @FXML
    private void confirmar(ActionEvent event) {
        Registro registro = new Registro();
        if(idAgenda != null) {
            registro.setId(idAgenda);
        }
        registro.setContatos(new ArrayList<>());
        registro.setNome(textNome.getText());
        registro.setSobrenome(textSobrenome.getText());
        registro.setApelido(textApelido.getText());
        if (pickerNasc.getValue() != null) {
            Instant instant = pickerNasc.getValue().atStartOfDay().atZone(ZoneId.systemDefault()).toInstant();
            registro.setAniversario(Date.from(instant));
        }
        registro.getContatos().addAll(tableContatos.getItems());

        RegistroDAO dao = new RegistroDAO();
        dao.saveOrUpdate(registro);
        
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Informação");
        alert.setContentText("Registro gravado com sucesso!");
        alert.showAndWait();
        MainApp.getTela1View().getRealPresenter()
                .getListviewNomes()
                .getSelectionModel().clearSelection();
        this.idAgenda = null;
        this.indexToEdit = null;
        textNome.getScene().getWindow().hide();
    }

    @FXML
    private void cancelarSair(ActionEvent event) {
        textNome.getScene().getWindow().hide();
    }

    @FXML
    private void addContato(ActionEvent event) {
        telaContatoView = new TelaContatoView();
        telaContatoView.getRealPresenter().tela2Presenter = this;
        Stage st = new Stage(StageStyle.UTILITY);
        st.setTitle("Contato[Novo]");
        st.setScene(new Scene(telaContatoView.getView()));
        st.initOwner(textNome.getScene().getWindow());
        st.initModality(Modality.APPLICATION_MODAL);
        st.show();
        
    }

    @FXML
    private void editContato(ActionEvent event) {
        if (tableContatos.getSelectionModel().getSelectedItem() != null) {
            int indexTable = tableContatos.getSelectionModel().getSelectedIndex();
            Contato contatoSelected = tableContatos.getSelectionModel().getSelectedItem();
            telaContatoView = new TelaContatoView();
            telaContatoView.getRealPresenter().tela2Presenter = this;
            telaContatoView.getRealPresenter().loadContato(contatoSelected, indexTable);
            Stage st = new Stage(StageStyle.UTILITY);
            st.setTitle("Contato[Edit]");
            st.setScene(new Scene(telaContatoView.getView()));
            st.initOwner(textNome.getScene().getWindow());
            st.initModality(Modality.APPLICATION_MODAL);
            st.show();
        }
    }

    @FXML
    private void removeContato(ActionEvent event) {
    }

    public void loadAgenda(Registro agenda, Integer indexToEdit) {
        this.indexToEdit = indexToEdit;
        this.idAgenda = null;
        if (agenda == null) {
            return;
        }
        this.idAgenda = agenda.getId();

        textNome.setText(agenda.getNome());
        textSobrenome.setText(agenda.getSobrenome());
        textApelido.setText(agenda.getApelido());
        if (agenda.getAniversario() != null) {
            Instant instant = Instant.ofEpochMilli(agenda.getAniversario().getTime());
            LocalDate ld = LocalDateTime.ofInstant(instant, ZoneId.systemDefault()).toLocalDate();
            pickerNasc.setValue(ld);
        }
//        tableContatos.setItems(FXCollections.observableArrayList(agenda.getContatos()));
        textNome.requestFocus();
    }
    
    public TableView<Contato> getTableContatos() {
        return tableContatos;
    }

}
