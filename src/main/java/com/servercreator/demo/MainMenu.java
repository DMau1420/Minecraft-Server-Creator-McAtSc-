package com.servercreator.demo;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import javafx.scene.control.Button;

//clase que define el MainMenu
public class MainMenu {
    //variable para almacenar la ventana
    private Stage primaryStage;

    //constructor que recibe la ventana y y la guarda para su uso en la clase
    public MainMenu(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

    public void show(){
        //implementacion de la fuente tipo minecraft y creada como familia
        Font minecraftFont = Font.loadFont(getClass().getResourceAsStream("/fonts/Minecraft_font.ttf"), 16);
        String fontFamily = minecraftFont != null ? minecraftFont.getFamily() : "Courier New";

        //declaracion de titulo, su estilo css y un fondo brillante
        Label titulo = new Label("McatSc");
        titulo.setStyle("-fx-font-family: '" + fontFamily + "'; -fx-font-size: 24; -fx-font-weight: bold; -fx-text-fill: #FFFFFF;");
        titulo.setEffect(new javafx.scene.effect.DropShadow(5, Color.GRAY));

        //declaracion del boton abrir y vinculacion con estilo de botones
        Button abrir = new Button("Abrir");
        abrir.getStyleClass().add("boton-personalizado");

        //funcionamiento del boton abrir para redirigir a CreateServerMenu
        abrir.setOnAction(e -> {
            ServerListMenu serverListMenu = new ServerListMenu(primaryStage);
            serverListMenu.show();
        });

        //declaracion del boton crear y vinculacion con estilo de botones
        Button crear = new Button("Crear nuevo");
        crear.getStyleClass().add("boton-personalizado");
        //funcionamiento del boton crear, redireccion a CreateServerMenu
        crear.setOnAction(e -> {
            CreateServerMenu createServerMenu = new CreateServerMenu(primaryStage);
            try {
                createServerMenu.show();
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
        });

        //declaracion de datos del creador con sus estilos ccs y efecto de fondo
        Label nombre = new Label("Creado por: \nMauricio Sandoval");
        nombre.setStyle("-fx-font-size: 13; -fx-text-fill: #63b1d0; -fx-text-alignment: center;");
        nombre.setEffect(new javafx.scene.effect.DropShadow(5, Color.ALICEBLUE));

        // div que contiene botones en horizontal para acomodo y centrado
        HBox botones = new HBox(15, abrir, crear);
        botones.setAlignment(Pos.CENTER);

        // div vertical para acomodo, centrado, bordes y color del background de color gris
        VBox texto = new VBox(10, titulo, botones, nombre);
        texto.setAlignment(Pos.CENTER);
        texto.setPadding(new Insets(20));

        //layout que contiene lo anterior este es usado para centrar de manera absoluta
        BorderPane layout = new BorderPane();
        layout.setCenter(texto);
        BorderPane.setAlignment(texto, Pos.CENTER);
        layout.getStyleClass().add("fondo");

        //creacion de escena con layout y tamaño de la ventana
        Scene escena = new Scene(layout,350,200);

        //vinculacion a estilos de css
        escena.getStylesheets().add(getClass().getResource("/styles/estilos.css").toExternalForm());

        // atributos de la ventana como la escena que corresponde, el titulo de la ventana, ancho y alto minimos, icono de la app y atributo mostrar
        primaryStage.setScene(escena);
        primaryStage.setMinWidth(300);
        primaryStage.setMinHeight(250);

        primaryStage.show();
    }

}
