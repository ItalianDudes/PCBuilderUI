package it.italiandudes.pcbuilderui.client.javafx.controllers;

import it.italiandudes.pcbuilderui.client.javafx.Client;
import javafx.fxml.FXML;

public final class ControllerSceneLoading {

    //Initialize
    @FXML
    private void initialize() {
        Client.getStage().setResizable(true);
    }
}