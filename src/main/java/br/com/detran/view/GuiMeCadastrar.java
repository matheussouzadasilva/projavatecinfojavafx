/*
 * GuiMeCadastrar.java
 *
 * Created on 05/05/2011, 20:50:35
 */
package br.com.detran.view;

import br.com.detran.controller.UsuarioController;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import br.com.detran.model.Usuario;
import br.com.detran.util.Criptografia;

/**
 * Classe GuiMeCadastrar da camada de visao
 * 
 * @version 2.4
 * @author Matheus Souza da Silva
 * @since 05/05/2011
 */
public class GuiMeCadastrar extends AbstractJavaFxView {

    @FXML private TextField jtUsuario;
    @FXML private PasswordField jpfSenha;
    @FXML private PasswordField jpfComfirmacao;
    @FXML private RadioButton jrbAdministrador;

    public GuiMeCadastrar() {
        super("Cadastro de Usuário");
    }

    @Override
    protected void initialize() {
        loadFXML("/view/GuiMeCadastrar.fxml");
    }

    @FXML
    private void handleCadastrar() {
        String senha = jpfSenha.getText();
        String senhaconfirmada = jpfComfirmacao.getText();
        try {
            if (!jtUsuario.getText().isEmpty() && !senha.isEmpty() && !senhaconfirmada.isEmpty()) {
                if (senha.equals(senhaconfirmada)) {
                    UsuarioController uc = new UsuarioController();
                    Usuario u = new Usuario();
                    u.setLogin(jtUsuario.getText());
                    u.setSenha(Criptografia.criptografa(senha));
                    u.setTipo(jrbAdministrador.isSelected() ? "Administrador" : "Comum");
                    uc.cadastrarUsuario(u);
                    FxViewSupport.info("Usuário cadastrado com sucesso!");
                    jtUsuario.clear();
                    jpfComfirmacao.clear();
                    jpfSenha.clear();
                } else {
                    FxViewSupport.error("As duas senhas não são iguais!");
                }
            } else {
                FxViewSupport.error("você não Preencheu todos os campos");
            }
        } catch (Exception e) {
            FxViewSupport.error("Erro! " + e.getMessage());
        }
    }
}