package view;

import controller.AutomovelController;
import controller.CondutorController;
import java.util.ArrayList;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import model.Automovel;
import model.Condutor;

public class GuiCondutor extends AbstractJavaFxView {

    @FXML private TextField jtNome;
    @FXML private TextField jtSobrenome;
    @FXML private TextField jtRg;
    @FXML private TextField jtCpf;
    @FXML private TextField jtDatanascimento;
    @FXML private TextField jtCnh;
    @FXML private ComboBox<Automovel> jcbMulta;
    @FXML private TableView<Condutor> jTableCondutor;
    @FXML private ToggleGroup bgSexo;
    @FXML private RadioButton jrbMasculino;
    @FXML private RadioButton jrbFeminino;
    @FXML private TableColumn<Condutor, String> c1, c2, c3, c4, c5, c6, c7, c8, c9;

    public GuiCondutor() {
        super("Cadastro de Condutor");
    }

    @Override
    protected void initialize() {
        loadFXML("/resources/fxml/GuiCondutor.fxml");
        preencheCombo();

        jrbMasculino.setUserData("masculino");
        jrbFeminino.setUserData("feminino");

        c1.setCellValueFactory(v -> new SimpleStringProperty(Integer.toString(v.getValue().getIdcondutor())));
        c2.setCellValueFactory(v -> new SimpleStringProperty(v.getValue().getSexo()));
        c3.setCellValueFactory(v -> new SimpleStringProperty(v.getValue().getNome()));
        c4.setCellValueFactory(v -> new SimpleStringProperty(v.getValue().getSobrenome()));
        c5.setCellValueFactory(v -> new SimpleStringProperty(Integer.toString(v.getValue().getRg())));
        c6.setCellValueFactory(v -> new SimpleStringProperty(Integer.toString(v.getValue().getCfp())));
        c7.setCellValueFactory(v -> new SimpleStringProperty(v.getValue().getDatanascimento()));
        c8.setCellValueFactory(v -> new SimpleStringProperty(Integer.toString(v.getValue().getCnh())));
        c9.setCellValueFactory(v -> new SimpleStringProperty(v.getValue().getAutomovel() == null ? "" : v.getValue().getAutomovel().getPlaca()));
    }

    private void preencheCombo() {
        try {
            AutomovelController mc = new AutomovelController();
            ArrayList<Automovel> automoveis = mc.buscarTodosAutomoveis();
            jcbMulta.setItems(FXCollections.observableArrayList(automoveis));
        } catch (Exception e) {
            FxViewSupport.error("Erro! " + e.getMessage());
        }
    }

    @FXML
    private void atualizarTabela() {
        try {
            CondutorController cc = new CondutorController();
            ArrayList<Condutor> condutores = cc.buscarTodosCondutores();
            jTableCondutor.setItems(FXCollections.observableArrayList(condutores));
        } catch (Exception e) {
            FxViewSupport.error("Erro! " + e.getMessage());
        }
    }

    @FXML
    private void handleLimpar() {
        limpar();
        jTableCondutor.getItems().clear();
    }

    private void limpar() {
        jtNome.clear();
        jtSobrenome.clear();
        jtRg.clear();
        jtCpf.clear();
        jtDatanascimento.clear();
        jtCnh.clear();
        bgSexo.selectToggle(null);
    }

    @FXML
    private void cadastrar() {
        try {
            if (!jtNome.getText().isEmpty() && !jtSobrenome.getText().isEmpty() && !jtRg.getText().isEmpty() && !jtCpf.getText().isEmpty() && !jtDatanascimento.getText().isEmpty() && !jtCnh.getText().isEmpty() && bgSexo.getSelectedToggle() != null) {
                Condutor c = new Condutor();
                c.setSexo(bgSexo.getSelectedToggle().getUserData().toString());
                c.setNome(jtNome.getText());
                c.setSobrenome(jtSobrenome.getText());
                c.setRg(Integer.parseInt(jtRg.getText()));
                c.setCfp(Integer.parseInt(jtCpf.getText()));
                c.setDatanascimento(jtDatanascimento.getText());
                c.setCnh(Integer.parseInt(jtCnh.getText()));
                c.setAutomovel(jcbMulta.getSelectionModel().getSelectedItem());
                CondutorController cc = new CondutorController();
                cc.cadastrarCondutor(c);
                FxViewSupport.info("Condutor Cadastrado com Sucesso!");
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