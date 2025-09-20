package com.servercreator.demo;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.File;

public class ServerListMenu {
    private Stage primaryStage;

    public ServerListMenu(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }
    public void show(){
        Button button = new Button("Regresar");
        button.getStyleClass().add("boton-personalizado");

        Label title = new Label("Server List");

        Label serverName = new Label("Server Name:");

        Label response = new Label();

        String ruta = "C:/Users/msand/McatSc";
        File carpeta = new File(ruta);
        if (!carpeta.exists()) {
            boolean creada = carpeta.mkdir();
            if (creada) {
                response.setText("Carpeta creada");
            }
            else  {
                response.setText("Carpeta inexistente");
            }}
        else{
                response.setText("Carpeta ya exite");
            }
        HBox titleBox = new HBox(20,serverName,response);
        VBox root = new VBox(15,button,title,titleBox);

        Scene escena = new Scene(root);
        escena.setFill(Color.valueOf("#2D2D2D"));
        escena.getStylesheets().add(getClass().getResource("/styles/estilos.css").toExternalForm());
        primaryStage.setScene(escena);

        //pendiente de hacer todo porque no hace nada

    }
}
