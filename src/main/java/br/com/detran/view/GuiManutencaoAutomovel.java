package br.com.detran.view;

import br.com.detran.controller.AutomovelController;
import br.com.detran.controller.MultaController;
import java.util.ArrayList;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import br.com.detran.model.Automovel;
import br.com.detran.model.Multa;

public class GuiManutencaoAutomovel extends AbstractJavaFxView {

    @FXML private TableView<Automovel> jTableAutomovel;
    @FXML private TextField jtCodigo;
    @FXML private TextField jtMarca;
    @FXML private TextField jtModelo;
    @FXML private TextField jtAno;
    @FXML private TextField jtPlaca;
    @FXML private ComboBox<Multa> jcbMulta;
    @FXML private TableColumn<Automovel, String> c1, c2, c3, c4, c5, c6;

    public GuiManutencaoAutomovel() {
        super("Manutencao de Automovel");
    }

    @Override
    protected void initialize() {
        loadFXML("/view/GuiManutencaoAutomovel.fxml");
        preencheCombo();

        c1.setCellValueFactory(v -> new SimpleStringProperty(Integer.toString(v.getValue().getIdautomovel())));
        c2.setCellValueFactory(v -> new SimpleStringProperty(v.getValue().getMarca()));
        c3.setCellValueFactory(v -> new SimpleStringProperty(v.getValue().getModelo()));
        c4.setCellValueFactory(v -> new SimpleStringProperty(Integer.toString(v.getValue().getAno())));
        c5.setCellValueFactory(v -> new SimpleStringProperty(v.getValue().getPlaca()));
        c6.setCellValueFactory(v -> new SimpleStringProperty(v.getValue().getMulta() == null ? "" : v.getValue().getMulta().getInfracao()));
    }

    private void preencheCombo() {
        try {
            MultaController mc = new MultaController();
            ArrayList<Multa> multas = mc.buscarTodasMultas();
            jcbMulta.setItems(FXCollections.observableArrayList(multas));
        } catch (Exception e) {
            FxViewSupport.error("Erro! " + e.getMessage());
        }
    }

    @FXML
    private void inserirTodosAutomoveisNaTabela() {
        try {
            AutomovelController ac = new AutomovelController();
            ArrayList<Automovel> automoveis = ac.buscarTodosAutomoveis();
            jTableAutomovel.setItems(FXCollections.observableArrayList(automoveis));
        } catch (Exception e) {
            FxViewSupport.error("Erro! " + e.getMessage());
        }
    }

    @FXML
    private void carregarSelecionado() {
        Automovel a = jTableAutomovel.getSelectionModel().getSelectedItem();
        if (a == null) {
            FxViewSupport.error("Voce nao selecionou nenhum Automovel");
            return;
        }
        jtCodigo.setText(Integer.toString(a.getIdautomovel()));
        jtMarca.setText(a.getMarca());
        jtModelo.setText(a.getModelo());
        jtAno.setText(Integer.toString(a.getAno()));
        jtPlaca.setText(a.getPlaca());
        jcbMulta.getSelectionModel().select(a.getMulta());
    }

    @FXML
    private void salvarAlteracoes() {
        try {
            Automovel a = new Automovel();
            a.setIdautomovel(Integer.parseInt(jtCodigo.getText()));
            a.setMarca(jtMarca.getText());
            a.setModelo(jtModelo.getText());
            a.setAno(Integer.parseInt(jtAno.getText()));
            a.setPlaca(jtPlaca.getText());
            a.setMulta(jcbMulta.getSelectionModel().getSelectedItem());
            AutomovelController ac = new AutomovelController();
            ac.atualizarAutomovelCodigo(a);
            FxViewSupport.info("Automovel Atualizado com Sucesso!");
            inserirTodosAutomoveisNaTabela();
        } catch (Exception e) {
            FxViewSupport.error("Erro! " + e.getMessage());
        }
    }

    @FXML
    private void deletarSelecionado() {
        try {
            Automovel a = jTableAutomovel.getSelectionModel().getSelectedItem();
            if (a == null) {
                throw new Exception("Voce nao selecionou nenhum Automovel");
            }
            AutomovelController ac = new AutomovelController();
            if (ac.deletarAutomovelCodigo(a)) {
                FxViewSupport.info("Automovel Excluido com Sucesso!");
                inserirTodosAutomoveisNaTabela();
            } else {
                FxViewSupport.error(ac.erros);
            }
        } catch (Exception e) {
            FxViewSupport.error("Erro! " + e.getMessage());
        }
    }

    @FXML
    private void handleLimpar() {
        jTableAutomovel.getItems().clear();
    }
}