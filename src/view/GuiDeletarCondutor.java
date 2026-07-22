package view;

import controller.CondutorController;
import java.util.ArrayList;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import model.Condutor;

public class GuiDeletarCondutor extends AbstractJavaFxView {

    @FXML private ToggleGroup jbgOpcao;
    @FXML private RadioButton jrCodigo;
    @FXML private RadioButton jrCnh;
    @FXML private TextField jtDeletar;
    @FXML private TableView<Condutor> jTableCondutor;
    @FXML private TableColumn<Condutor, String> c1, c2, c3, c4, c5, c6, c7, c8;

    public GuiDeletarCondutor() {
        super("Deletar Condutor");
    }

    @Override
    protected void initialize() {
        loadFXML("/resources/fxml/GuiDeletarCondutor.fxml");
        
        jrCodigo.setUserData("codigo");
        jrCnh.setUserData("cnh");

        c1.setCellValueFactory(v -> new SimpleStringProperty(Integer.toString(v.getValue().getIdcondutor())));
        c2.setCellValueFactory(v -> new SimpleStringProperty(v.getValue().getNome()));
        c3.setCellValueFactory(v -> new SimpleStringProperty(v.getValue().getSobrenome()));
        c4.setCellValueFactory(v -> new SimpleStringProperty(Integer.toString(v.getValue().getRg())));
        c5.setCellValueFactory(v -> new SimpleStringProperty(Integer.toString(v.getValue().getCfp())));
        c6.setCellValueFactory(v -> new SimpleStringProperty(v.getValue().getDatanascimento()));
        c7.setCellValueFactory(v -> new SimpleStringProperty(Integer.toString(v.getValue().getCnh())));
        c8.setCellValueFactory(v -> new SimpleStringProperty(v.getValue().getAutomovel() == null ? "" : v.getValue().getAutomovel().getPlaca()));
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
    private void deletar() {
        try {
            CondutorController cc = new CondutorController();
            Condutor c = new Condutor();
            String op = jbgOpcao.getSelectedToggle().getUserData().toString();

            if ("codigo".equals(op)) {
                c.setIdcondutor(Integer.parseInt(jtDeletar.getText()));
                if (cc.buscarCondutorCodigo(c)) {
                    if (!cc.deletarCondutorCodigo(c)) {
                        FxViewSupport.error(cc.erros);
                        return;
                    }
                } else {
                    throw new Exception("Condutor nao Existente!");
                }
            } else {
                c.setCnh(Integer.parseInt(jtDeletar.getText()));
                if (cc.buscarCondutorCnh(c)) {
                    if (!cc.deletarCondutorCnh(c)) {
                        FxViewSupport.error(cc.erros);
                        return;
                    }
                } else {
                    throw new Exception("Condutor nao Existente!");
                }
            }

            FxViewSupport.info("Condutor Excluido com Sucesso!");
            atualizarTabela();
        } catch (Exception e) {
            FxViewSupport.error("Erro! " + e.getMessage());
        }
    }
}