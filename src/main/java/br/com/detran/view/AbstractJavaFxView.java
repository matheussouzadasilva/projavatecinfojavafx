package br.com.detran.view;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;

public abstract class AbstractJavaFxView {

    protected final Stage stage = new Stage();
    private boolean initialized;

    protected AbstractJavaFxView(String title) {
        stage.setTitle(title);
        FxViewSupport.applyIcon(stage);
    }

    protected void loadFXML(String fxmlPath) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlPath));
            loader.setController(this);
            Parent root = loader.load();
            stage.setScene(new Scene(root));
        } catch (IOException e) {
            e.printStackTrace();
            FxViewSupport.error("Erro ao carregar FXML: " + fxmlPath);
        }
    }

    protected abstract void initialize();

    private void ensureInitialized() {
        if (!initialized) {
            initialize();
            initialized = true;
        }
    }

    public void show() {
        ensureInitialized();
        stage.show();
    }

    public Stage getStage() {
        ensureInitialized();
        return stage;
    }
}