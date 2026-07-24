package br.com.detran.view;

import controller.MultaController;
import java.util.ArrayList;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import model.Multa;

public class GuiMulta extends AbstractJavaFxView {

    @FXML private TextField jtInfracao;
    @FXML private TextField jtPontos;
    @FXML private TextField jtPenalidade;
    @FXML private TextField jtValor;
    @FXML private TableView<Multa> jTableMulta;
    @FXML private TableColumn<Multa, String> c1;
    @FXML private TableColumn<Multa, String> c2;
    @FXML private TableColumn<Multa, String> c3;
    @FXML private TableColumn<Multa, String> c4;
    @FXML private TableColumn<Multa, String> c5;

    public GuiMulta() {
        super("Cadastro multa");
    }

    @Override
    protected void initialize() {
        loadFXML("/view/GuiMulta.fxml");
        c1.setCellValueFactory(v -> new SimpleStringProperty(Integer.toString(v.getValue().getIdmulta())));
        c2.setCellValueFactory(v -> new SimpleStringProperty(v.getValue().getInfracao()));
        c3.setCellValueFactory(v -> new SimpleStringProperty(Integer.toString(v.getValue().getPontos())));
        c4.setCellValueFactory(v -> new SimpleStringProperty(v.getValue().getPenalidade()));
        c5.setCellValueFactory(v -> new SimpleStringProperty(Double.toString(v.getValue().getValor())));
    }

    @FXML
    private void atualizarTabela() {
        try {
            MultaController mc = new MultaController();
            ArrayList<Multa> multas = mc.buscarTodasMultas();
            jTableMulta.setItems(FXCollections.observableArrayList(multas));
        } catch (Exception e) {
            FxViewSupport.error("Erro! " + e.getMessage());
        }
    }

    @FXML
    private void handleLimpar() {
        limpar();
        jTableMulta.getItems().clear();
    }

    private void limpar() {
        jtInfracao.clear();
        jtPontos.clear();
        jtPenalidade.clear();
        jtValor.clear();
    }

    @FXML
    private void cadastrar() {
        try {
            if (!jtInfracao.getText().isEmpty() && !jtPontos.getText().isEmpty() && !jtPenalidade.getText().isEmpty() && !jtValor.getText().isEmpty()) {
                Multa m = new Multa();
                m.setInfracao(jtInfracao.getText());
                m.setPontos(Integer.parseInt(jtPontos.getText()));
                m.setPenalidade(jtPenalidade.getText());
                m.setValor(Double.parseDouble(jtValor.getText()));
                MultaController mc = new MultaController();
                mc.cadastrarMulta(m);
                FxViewSupport.info("Multa Cadastrada com Sucesso!");
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