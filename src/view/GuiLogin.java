/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * GuiLogin.java
 *
 * Created on 02/05/2011, 20:42:00
 */

package view;

import controller.UsuarioController;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.Usuario;
import util.Criptografia;

import java.io.IOException;

/**
 * @version 2.4
 * @author Matheus Souza
 * @since 02/05/2011
 */
public class GuiLogin extends Application {

    private int numeroTentativas;
    @FXML private TextField jtUsuario;
    @FXML private PasswordField jpfSenha;
    @FXML private ComboBox<String> jcbTipo;
    @FXML private Label jlDica;
    private Stage stage;

    @FXML
    public void initialize() {
        jcbTipo.getItems().addAll("Administrador", "Comum");
        jcbTipo.getSelectionModel().selectFirst();
    }

    @FXML
    private void processoLogar(){

    if (!jtUsuario.getText().isEmpty() && !jpfSenha.getText().isEmpty()) {
        try {
            Usuario u = new Usuario();

         /* Campo de Senha: Criamos uma nova String
            e colocamos dentro dessa String o
            vetor de char[] que o campo de senha retorna */
            u.setLogin(jtUsuario.getText());
            u.setSenha(Criptografia.criptografa(jpfSenha.getText()));

            //u.setSenha(new String(jpfSenha.getPassword())); //sem criptografia
            u.setTipo(jcbTipo.getSelectionModel().getSelectedItem());

            UsuarioController uc = new UsuarioController();
         /* Chamando método validarSenha() e enviando
            o objeto com os dados do login para verificação
            no banco de dados */
            int opcaoMenu = uc.validarSenha(u);

            if (opcaoMenu != -1) {
                MenuPrincipal mp = new MenuPrincipal();
                mp.montaMenu(opcaoMenu);
                mp.show();
                stage.hide();

            } else {
                this.numeroTentativas++;
                if(this.numeroTentativas>=3){
                    FxViewSupport.error("Login inválido. Encerrando após 3 tentativas.");
                    Platform.exit();
                }else{
                    jlDica.setText(this.numeroTentativas+"ª tentativa. Login inválido, tente novamente !");
                }
            }

            } catch (Exception e) {
                FxViewSupport.error("Erro! "+e.getMessage());
            }

        } else {
            FxViewSupport.error("você não Preencheu todos os campos");
        }  

    }

    @FXML
    private void handleCadastrarSe() {
        new GuiMeCadastrar().show();
    }

    /**
    * @param args the command line arguments
    */
    public static void main(String args[]) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        this.stage = primaryStage;
        primaryStage.setTitle("Detran 2.4");
        FxViewSupport.applyIcon(primaryStage);
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/resources/fxml/GuiLogin.fxml"));
            loader.setController(this);
            Parent root = loader.load();
            primaryStage.setScene(new Scene(root, 600, 320));
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
            FxViewSupport.error("Erro ao carregar FXML de Login");
        }
    }

}
