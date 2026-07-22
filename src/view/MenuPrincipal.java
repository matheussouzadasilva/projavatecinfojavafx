/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * MenuPrincipal.java
 *
 * Created on 02/05/2011, 20:24:20
 */

package view;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.MenuItem;
import javafx.stage.Stage;

import java.io.IOException;


/**
 * @version 2.4
 * @author Matheus Souza
 * @since 02/05/2011
 */
public class MenuPrincipal {

private boolean flagGuiMulta=false,flagGuiEndereco=false,flagGuiCondutor=false;
private boolean flagGuiAutomovel=false,flagGuiUsuario=false,flagGuiDeletarCondutor=false;
private boolean flagGuiDeletarEndereco=false,flagGuiManutencaoMulta=false,flagGuiManutencaoAutomovel=false;
private final Stage stage = new Stage();
@FXML private MenuItem jmiUsuario;

    public MenuPrincipal() {
        stage.setTitle("Detran 2.4");
        FxViewSupport.applyIcon(stage);
        
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/resources/fxml/MenuPrincipal.fxml"));
            loader.setController(this);
            Parent root = loader.load();
            stage.setScene(new Scene(root, 1000, 670));
            stage.setOnCloseRequest(evt -> Platform.exit());
        } catch (IOException e) {
            e.printStackTrace();
            FxViewSupport.error("Erro ao carregar FXML do Menu Principal");
        }
    }

    @FXML
    private void handleCadastrarUsuario() {
        open(new GuiCadastrarUsuario(), () -> flagGuiUsuario = false, () -> flagGuiUsuario = true, () -> flagGuiUsuario);
    }
    
    @FXML
    private void handleCadastrarMulta() {
        open(new GuiMulta(), () -> flagGuiMulta = false, () -> flagGuiMulta = true, () -> flagGuiMulta);
    }

    @FXML
    private void handleCadastrarAutomovel() {
        open(new GuiAutomovel(), () -> flagGuiAutomovel = false, () -> flagGuiAutomovel = true, () -> flagGuiAutomovel);
    }

    @FXML
    private void handleCadastrarCondutor() {
        open(new GuiCondutor(), () -> flagGuiCondutor = false, () -> flagGuiCondutor = true, () -> flagGuiCondutor);
    }

    @FXML
    private void handleCadastrarEndereco() {
        open(new GuiEndereco(), () -> flagGuiEndereco = false, () -> flagGuiEndereco = true, () -> flagGuiEndereco);
    }

    @FXML
    private void handleDeletarCondutor() {
        open(new GuiDeletarCondutor(), () -> flagGuiDeletarCondutor = false, () -> flagGuiDeletarCondutor = true, () -> flagGuiDeletarCondutor);
    }

    @FXML
    private void handleDeletarEndereco() {
        open(new GuiDeletarEndereco(), () -> flagGuiDeletarEndereco = false, () -> flagGuiDeletarEndereco = true, () -> flagGuiDeletarEndereco);
    }

    @FXML
    private void handleManutencaoMulta() {
        open(new GuiManutencaoMulta(), () -> flagGuiManutencaoMulta = false, () -> flagGuiManutencaoMulta = true, () -> flagGuiManutencaoMulta);
    }

    @FXML
    private void handleManutencaoAutomovel() {
        open(new GuiManutencaoAutomovel(), () -> flagGuiManutencaoAutomovel = false, () -> flagGuiManutencaoAutomovel = true, () -> flagGuiManutencaoAutomovel);
    }

    @FXML
    private void handleLogarOutro() {
        new GuiLogin().start(new Stage());
        stage.hide();
    }

    @FXML
    private void handleSair() {
        Platform.exit();
    }

    private void open(AbstractJavaFxView view, Runnable resetFlag, Runnable setFlag, Flag flagState) {
        if (flagState.isOpen()) {
            return;
        }
        setFlag.run();
        view.show();
        view.getStage().setOnHidden(evt -> resetFlag.run());
    }

    public void montaMenu(int opcao){
        if(opcao == 1){
            this.menuAdministrador();
        }else if(opcao == 2){
            this.menuUsuarioComun();
        }else{
            Platform.exit();
        }
    }

    private void menuAdministrador(){ }

    private void menuUsuarioComun(){
        if (jmiUsuario != null) {
            jmiUsuario.setVisible(false);
        }
    }
    public void show() {
        stage.show();
    }

    interface Flag {
        boolean isOpen();
    }
}