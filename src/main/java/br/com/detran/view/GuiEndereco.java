package br.com.detran.view;

import br.com.detran.controller.CondutorController;
import br.com.detran.controller.EnderecoController;
import java.util.ArrayList;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import br.com.detran.model.Condutor;
import br.com.detran.model.Endereco;

public class GuiEndereco extends AbstractJavaFxView {

    @FXML private TextField jtEndereco;
    @FXML private TextField jtLogradouro;
    @FXML private TextField jtNumero;
    @FXML private TextField jtComplemento;
    @FXML private TextField jtBairro;
    @FXML private TextField jtCep;
    @FXML private ComboBox<String> jcbEstado;
    @FXML private ComboBox<String> jcbCidade;
    @FXML private ComboBox<Condutor> jcbCondutor;
    @FXML private TableView<Endereco> jTableEndereco;
    @FXML private TableColumn<Endereco, String> c1, c2, c3, c4, c5, c6, c7, c8, c9, c10;

    public GuiEndereco() {
        super("Cadastro de Endereco");
    }

    @Override
    protected void initialize() {
        loadFXML("/view/GuiEndereco.fxml");
        jcbEstado.setItems(FXCollections.observableArrayList("SP", "RJ", "MG", "PR", "SC", "RS"));
        jcbCidade.setItems(FXCollections.observableArrayList("Sao Paulo", "Rio de Janeiro", "Belo Horizonte", "Curitiba", "Florianopolis", "Porto Alegre"));
        preencheCombo();

        c1.setCellValueFactory(v -> new SimpleStringProperty(Integer.toString(v.getValue().getIdendereco())));
        c2.setCellValueFactory(v -> new SimpleStringProperty(v.getValue().getEndereco()));
        c3.setCellValueFactory(v -> new SimpleStringProperty(v.getValue().getLogradouro()));
        c4.setCellValueFactory(v -> new SimpleStringProperty(Integer.toString(v.getValue().getNumero())));
        c5.setCellValueFactory(v -> new SimpleStringProperty(v.getValue().getComplemento()));
        c6.setCellValueFactory(v -> new SimpleStringProperty(v.getValue().getBairro()));
        c7.setCellValueFactory(v -> new SimpleStringProperty(v.getValue().getEstado()));
        c8.setCellValueFactory(v -> new SimpleStringProperty(v.getValue().getCidade()));
        c9.setCellValueFactory(v -> new SimpleStringProperty(Integer.toString(v.getValue().getCep())));
        c10.setCellValueFactory(v -> new SimpleStringProperty(v.getValue().getCondutor() == null ? "" : v.getValue().getCondutor().getNome()));
    }

    private void preencheCombo() {
        try {
            CondutorController cc = new CondutorController();
            ArrayList<Condutor> condutores = cc.buscarTodosCondutores();
            jcbCondutor.setItems(FXCollections.observableArrayList(condutores));
        } catch (Exception e) {
            FxViewSupport.error("Erro! " + e.getMessage());
        }
    }

    @FXML
    private void atualizarTabela() {
        try {
            EnderecoController ec = new EnderecoController();
            ArrayList<Endereco> enderecos = ec.buscarTodosEnderecos();
            jTableEndereco.setItems(FXCollections.observableArrayList(enderecos));
        } catch (Exception e) {
            FxViewSupport.error("Erro! " + e.getMessage());
        }
    }

    @FXML
    private void handleLimpar() {
        limpar();
        jTableEndereco.getItems().clear();
    }

    private void limpar() {
        jtEndereco.clear();
        jtLogradouro.clear();
        jtNumero.clear();
        jtComplemento.clear();
        jtBairro.clear();
        jtCep.clear();
    }

    @FXML
    private void cadastrar() {
        try {
            if (!jtEndereco.getText().isEmpty() && !jtLogradouro.getText().isEmpty() && !jtNumero.getText().isEmpty() && !jtComplemento.getText().isEmpty() && !jtBairro.getText().isEmpty() && !jtCep.getText().isEmpty()) {
                Endereco e = new Endereco();
                e.setEndereco(jtEndereco.getText());
                e.setLogradouro(jtLogradouro.getText());
                e.setNumero(Integer.parseInt(jtNumero.getText()));
                e.setComplemento(jtComplemento.getText());
                e.setBairro(jtBairro.getText());
                e.setEstado(jcbEstado.getSelectionModel().getSelectedItem());
                e.setCidade(jcbCidade.getSelectionModel().getSelectedItem());
                e.setCep(Integer.parseInt(jtCep.getText()));
                e.setCondutor(jcbCondutor.getSelectionModel().getSelectedItem());
                EnderecoController ec = new EnderecoController();
                ec.cadastrarEndereco(e);
                FxViewSupport.info("Endereco Cadastrado com Sucesso!");
                limpar();
                atualizarTabela();
            } else {
                FxViewSupport.error("Preencha Todos Os Campos!!!");
            }
        } catch (NumberFormatException nfe) {
            FxViewSupport.error("Dados Invalidos!\n" + nfe.getMessage());
        } catch (Exception e) {
            FxViewSupport.error("Erro! " + e.getMessage());
        }
    }
}