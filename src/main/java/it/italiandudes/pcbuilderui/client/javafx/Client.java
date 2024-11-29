package it.italiandudes.pcbuilderui.client.javafx;

import it.italiandudes.idl.common.Logger;
import it.italiandudes.pcbuilderui.client.javafx.components.SceneController;
import it.italiandudes.pcbuilderui.client.javafx.scenes.SceneMainMenu;
import it.italiandudes.pcbuilderui.client.javafx.utils.JFXDefs;
import it.italiandudes.pcbuilderui.common.DBManager;
import it.italiandudes.pcbuilderui.common.Defs;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.input.Clipboard;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;


public final class Client extends Application {

    // Attributes
    private static Clipboard SYSTEM_CLIPBOARD = null;
    private static Stage STAGE = null;
    private static SceneController SCENE = null;

    // JavaFX Application Main
    @Override
    public void start(Stage stage) {
        SYSTEM_CLIPBOARD = Clipboard.getSystemClipboard();
        STAGE = stage;
        stage.setTitle(JFXDefs.AppInfo.NAME);
        stage.getIcons().add(JFXDefs.AppInfo.LOGO);
        SCENE = Objects.requireNonNull(SceneMainMenu.getScene());
        stage.setScene(new Scene(SCENE.getParent()));
        stage.getScene().getStylesheets().clear();
        stage.getScene().getStylesheets().add(Defs.Resources.get(JFXDefs.Resources.CSS.CSS_THEME).toExternalForm());
        stage.show();
        stage.setX((JFXDefs.SystemGraphicInfo.SCREEN_WIDTH - stage.getWidth()) / 2);
        stage.setY((JFXDefs.SystemGraphicInfo.SCREEN_HEIGHT - stage.getHeight()) / 2);
        stage.getScene().getWindow().addEventHandler(WindowEvent.WINDOW_CLOSE_REQUEST, event -> exit());

        // Fullscreen Event Listener
        stage.addEventFilter(KeyEvent.KEY_PRESSED, (KeyEvent event) -> {
            if (event.getCode() == KeyCode.F11) {
                if (!stage.isFullScreen()) {
                    if (!stage.isMaximized()) {
                        stage.setMaximized(true);
                    }
                    stage.setFullScreen(true);
                }
            } else if (event.getCode() == KeyCode.ESCAPE) {
                if (stage.isFullScreen()) stage.setFullScreen(false);
            }
        });

        // Notice into the logs that the application started Successfully
        Logger.log("Application started successfully!");
    }

    // Start Methods
    public static void start(String[] args) {
        launch(args);
    }

    // Methods
    public static void exit() {
        Logger.log("Exit Method Called, exiting Java process...");
        Platform.runLater(() -> STAGE.hide());
        DBManager.closeConnection();
        Logger.close();
        Platform.exit();
        System.exit(0);
    }
    @NotNull
    public static Stage getStage() {
        return STAGE;
    }
    @NotNull
    public static SceneController getScene() {
        return SCENE;
    }
    public static void setScene(@NotNull final SceneController newScene) {
        SCENE = newScene;
        STAGE.getScene().setRoot(newScene.getParent());
    }
    @NotNull
    public static Clipboard getSystemClipboard() {
        return SYSTEM_CLIPBOARD;
    }
    @NotNull
    public static Stage initPopupStage(SceneController sceneController) {
        Stage popupStage = new Stage();
        popupStage.getIcons().add(JFXDefs.AppInfo.LOGO);
        popupStage.setTitle(JFXDefs.AppInfo.NAME);
        popupStage.initOwner(STAGE);
        popupStage.initModality(Modality.WINDOW_MODAL);
        popupStage.setScene(new Scene(sceneController.getParent()));
        popupStage.getScene().getStylesheets().clear();
        popupStage.getScene().getStylesheets().add(Defs.Resources.get(JFXDefs.Resources.CSS.CSS_THEME).toExternalForm());
        return popupStage;
    }
}
