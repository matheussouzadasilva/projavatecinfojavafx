/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * GuiAutomovel.java
 *
 * Created on 02/05/2011, 22:21:05
 */

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

/**
 * @version 2.4
 * @author Matheus Souza
 * @since 02/05/2011
 * @exception NumberFormatException
 */

public class GuiAutomovel extends AbstractJavaFxView {

    @FXML private TextField jtMarca;
    @FXML private TextField jtModelo;
    @FXML private TextField jtAno;
    @FXML private TextField jtPlaca;
    @FXML private ComboBox<Multa> jcbMulta;
    @FXML private TableView<Automovel> jTableAutomovel;
    @FXML private TableColumn<Automovel, String> c1;
    @FXML private TableColumn<Automovel, String> c2;
    @FXML private TableColumn<Automovel, String> c3;
    @FXML private TableColumn<Automovel, String> c4;
    @FXML private TableColumn<Automovel, String> c5;
    @FXML private TableColumn<Automovel, String> c6;

    public GuiAutomovel() {
        super("Cadastro Automovel");
    }

    @Override
    protected void initialize() {
        loadFXML("/view/GuiAutomovel.fxml");
        preencheCombo();

        c1.setCellValueFactory(v -> new SimpleStringProperty(Integer.toString(v.getValue().getIdautomovel())));
        c2.setCellValueFactory(v -> new SimpleStringProperty(v.getValue().getMarca()));
        c3.setCellValueFactory(v -> new SimpleStringProperty(v.getValue().getModelo()));
        c4.setCellValueFactory(v -> new SimpleStringProperty(Integer.toString(v.getValue().getAno())));
        c5.setCellValueFactory(v -> new SimpleStringProperty(v.getValue().getPlaca()));
        c6.setCellValueFactory(v -> new SimpleStringProperty(v.getValue().getMulta() == null ? "" : v.getValue().getMulta().getInfracao()));
    }

    @FXML
    private void AtualizarTabela() {
        try {
            AutomovelController tc = new AutomovelController();
            ArrayList<Automovel> tb = tc.buscarTodosAutomoveis();
            jTableAutomovel.setItems(FXCollections.observableArrayList(tb));
        } catch (Exception e) {
            FxViewSupport.error("Erro! " + e.getMessage());
        }
    }

    @FXML
    private void handleLimpar() {
        Limpar();
        jTableAutomovel.getItems().clear();
    }

    public void Limpar() {
        jtMarca.clear();
        jtModelo.clear();
        jtAno.clear();
        jtPlaca.clear();
    }

    private void preencheCombo() {
        try {
            MultaController mc = new MultaController();
            ArrayList<Multa> aAux = mc.buscarTodasMultas();
            jcbMulta.setItems(FXCollections.observableArrayList(aAux));
        } catch (Exception e) {
            FxViewSupport.error("Erro! " + e.getMessage());
        }
    }

    @FXML
    private void cadastrar() {
        try {
            if (!jtMarca.getText().isEmpty() && !jtModelo.getText().isEmpty() && !jtAno.getText().isEmpty() && !jtPlaca.getText().isEmpty()) {
                Automovel m = new Automovel();
                m.setMarca(jtMarca.getText());
                m.setModelo(jtModelo.getText());
                m.setAno(Integer.parseInt(jtAno.getText()));
                m.setPlaca(jtPlaca.getText());
                m.setMulta(jcbMulta.getSelectionModel().getSelectedItem());
                AutomovelController ac = new AutomovelController();
                ac.cadastrarAutomovel(m);
                FxViewSupport.info("Automovel Cadastrado com Sucesso!");
                Limpar();
                AtualizarTabela();
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
