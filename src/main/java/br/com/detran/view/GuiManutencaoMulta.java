package br.com.detran.view;

import br.com.detran.controller.MultaController;
import java.util.ArrayList;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import br.com.detran.model.Multa;

public class GuiManutencaoMulta extends AbstractJavaFxView {

    @FXML private TableView<Multa> jTableMulta;
    @FXML private TextField jtCodigo;
    @FXML private TextField jtInfracao;
    @FXML private TextField jtPontos;
    @FXML private TextField jtPenalidade;
    @FXML private TextField jtValor;
    @FXML private TableColumn<Multa, String> c1, c2, c3, c4, c5;

    public GuiManutencaoMulta() {
        super("Manutencao de Multa");
    }

    @Override
    protected void initialize() {
        loadFXML("/view/GuiManutencaoMulta.fxml");

        c1.setCellValueFactory(v -> new SimpleStringProperty(Integer.toString(v.getValue().getIdmulta())));
        c2.setCellValueFactory(v -> new SimpleStringProperty(v.getValue().getInfracao()));
        c3.setCellValueFactory(v -> new SimpleStringProperty(Integer.toString(v.getValue().getPontos())));
        c4.setCellValueFactory(v -> new SimpleStringProperty(v.getValue().getPenalidade()));
        c5.setCellValueFactory(v -> new SimpleStringProperty(Double.toString(v.getValue().getValor())));
    }

    @FXML
    private void inserirTodosMultasNaTabela() {
        try {
            MultaController mc = new MultaController();
            ArrayList<Multa> multas = mc.buscarTodasMultas();
            jTableMulta.setItems(FXCollections.observableArrayList(multas));
        } catch (Exception e) {
            FxViewSupport.error("Erro! " + e.getMessage());
        }
    }

    @FXML
    private void carregarSelecionado() {
        Multa m = jTableMulta.getSelectionModel().getSelectedItem();
        if (m == null) {
            FxViewSupport.error("Voce nao selecionou nenhuma Multa");
            return;
        }
        jtCodigo.setText(Integer.toString(m.getIdmulta()));
        jtInfracao.setText(m.getInfracao());
        jtPontos.setText(Integer.toString(m.getPontos()));
        jtPenalidade.setText(m.getPenalidade());
        jtValor.setText(Double.toString(m.getValor()));
    }

    @FXML
    private void salvarAlteracoes() {
        try {
            Multa m = new Multa();
            m.setIdmulta(Integer.parseInt(jtCodigo.getText()));
            m.setInfracao(jtInfracao.getText());
            m.setPontos(Integer.parseInt(jtPontos.getText()));
            m.setPenalidade(jtPenalidade.getText());
            m.setValor(Double.parseDouble(jtValor.getText()));
            MultaController mc = new MultaController();
            mc.atualizarMultaCodigo(m);
            FxViewSupport.info("Multa Atualizada com Sucesso!");
            inserirTodosMultasNaTabela();
        } catch (Exception e) {
            FxViewSupport.error("Erro! " + e.getMessage());
        }
    }

    @FXML
    private void deletarSelecionado() {
        try {
            Multa m = jTableMulta.getSelectionModel().getSelectedItem();
            if (m == null) {
                throw new Exception("Voce nao selecionou nenhuma Multa");
            }
            MultaController mc = new MultaController();
            if (mc.deletarMultaCodigo(m)) {
                FxViewSupport.info("Multa Excluida com Sucesso!");
                inserirTodosMultasNaTabela();
            } else {
                FxViewSupport.error(mc.erros);
            }
        } catch (Exception e) {
            FxViewSupport.error("Erro! " + e.getMessage());
        }
    }

    @FXML
    private void handleLimpar() {
        jTableMulta.getItems().clear();
    }
}