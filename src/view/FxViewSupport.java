package view;

import javafx.scene.control.Alert;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public final class FxViewSupport {

    private FxViewSupport() {
    }

    public static void applyIcon(Stage stage) {
        try {
            stage.getIcons().add(new Image(FxViewSupport.class.getResourceAsStream("/icones/icone.png")));
        } catch (Exception ignore) {
        }
    }

    public static void info(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    public static void error(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setHeaderText("Erro");
        alert.setContentText(message);
        alert.showAndWait();
    }
}