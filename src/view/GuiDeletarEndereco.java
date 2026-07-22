package view;

import controller.EnderecoController;
import java.util.ArrayList;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import model.Endereco;

public class GuiDeletarEndereco extends AbstractJavaFxView {

    @FXML private ToggleGroup jbgOpcao;
    @FXML private RadioButton jrCodigo;
    @FXML private RadioButton jrNumero;
    @FXML private TextField jtDeletar;
    @FXML private TableView<Endereco> jTableEndereco;
    @FXML private TableColumn<Endereco, String> c1, c2, c3, c4, c5, c6, c7, c8, c9, c10;

    public GuiDeletarEndereco() {
        super("Deletar Endereco");
    }

    @Override
    protected void initialize() {
        loadFXML("/resources/fxml/GuiDeletarEndereco.fxml");
        
        jrCodigo.setUserData("codigo");
        jrNumero.setUserData("numero");

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
    private void deletar() {
        try {
            EnderecoController ec = new EnderecoController();
            Endereco e = new Endereco();
            String op = jbgOpcao.getSelectedToggle().getUserData().toString();

            if ("codigo".equals(op)) {
                e.setIdendereco(Integer.parseInt(jtDeletar.getText()));
                if (ec.buscarEnderecoCodigo(e)) {
                    ec.deletarEnderecoCodigo(e);
                } else {
                    throw new Exception("Endereco nao Existente!");
                }
            } else {
                e.setNumero(Integer.parseInt(jtDeletar.getText()));
                if (ec.buscarEnderecoNumero(e)) {
                    ec.deletarEnderecoNumero(e);
                } else {
                    throw new Exception("Endereco nao Existente!");
                }
            }

            FxViewSupport.info("Endereco Excluido com Sucesso!");
            atualizarTabela();
        } catch (Exception e) {
            FxViewSupport.error("Erro! " + e.getMessage());
        }
    }
}