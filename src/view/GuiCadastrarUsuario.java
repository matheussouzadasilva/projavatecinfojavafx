/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * GuiCadastrarUsuario.java
 *
 * Created on 05/05/2011, 20:50:35
 */

package view;

import controller.UsuarioController;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import model.Usuario;
import util.Criptografia;

/**
 * @version 2.4
 * @author Matheus Souza
 * @since 02/05/2011
 */

public class GuiCadastrarUsuario extends AbstractJavaFxView {

    @FXML private TextField jtNome;
    @FXML private PasswordField jpfSenha;
    @FXML private ComboBox<String> jcbTipo;

    public GuiCadastrarUsuario() {
        super("Cadastro de Usuário");
    }

    @Override
    protected void initialize() {
        loadFXML("/resources/fxml/GuiCadastrarUsuario.fxml");
        jcbTipo.getItems().addAll("Administrador", "Comum");
        jcbTipo.getSelectionModel().selectFirst();
    }

    @FXML
    private void handleCadastrar() {
        try {
            if (!jpfSenha.getText().isEmpty() && !jtNome.getText().isEmpty()) {
                Usuario u = new Usuario();
                UsuarioController uc = new UsuarioController();
                u.setLogin(jtNome.getText());
                u.setSenha(Criptografia.criptografa(jpfSenha.getText()));
                u.setTipo(jcbTipo.getSelectionModel().getSelectedItem());
                uc.cadastrarUsuario(u);
                FxViewSupport.info("Usuário cadastrado com sucesso!");
            } else {
                FxViewSupport.error("Preencha Todos Os Campos!!!");
            }

        } catch (Exception e) {
            FxViewSupport.error("Erro! "+e.getMessage());
        }
    }
}
