package com.servercreator.demo;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import java.io.File;


public class pruebas extends Application {
    @Override
    public void start(Stage primaryStage) {
        ImageView imagen = new ImageView(new Image("file:C:/Users/msand/tuyu sin fondo.png"));
        imagen.setPreserveRatio(true);
        imagen.setSmooth(true);


        TextField serverip = new TextField("Ip");
        Button boton = new Button("Abrir archivo");
        Button boton2 = new Button("buscar");
        Button boton3 = new Button("Eliminar archivo");

        FileChooser archivo = new FileChooser();
        archivo.setTitle("abrir archivo");
        archivo.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Archivos de texto", "*.properties")
        );
        archivo.setInitialDirectory(new File(System.getProperty("user.home") + "/downloads"));

        boton.setOnAction(e -> {File file = archivo.showOpenDialog(primaryStage);});

        HBox layout = new HBox(imagen);
        HBox.setHgrow(imagen, Priority.ALWAYS);
        layout.getChildren().addAll(boton,serverip,boton2);

        VBox root = new VBox(10);
        root.getChildren().addAll(layout, boton3);

        BorderPane container = new BorderPane();
        container.setCenter(root);

        BorderPane.setMargin(root, new Insets(10));

        //crear escena
        Scene escena = new Scene(container); //formato de la ventana, y tamaño
        imagen.fitWidthProperty().bind(escena.widthProperty());
        imagen.fitHeightProperty().bind(escena.heightProperty());

        //configurar la ventana principal
        primaryStage.setTitle("MCatSc - seleccion de archivos"); //nombre de la ventana
        primaryStage.setScene(escena); //escena que abrimos
        primaryStage.getIcons().add(new Image("file:C:/Users/msand/tuyu sin fondo.png"));
        primaryStage.show(); //mostrar la escena
    }

    public static void main(String[] args) {
        launch(args);
    }
}

