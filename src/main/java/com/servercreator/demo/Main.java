package com.servercreator.demo;

import javafx.application.Application;
import javafx.scene.image.Image;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;

public class Main  extends Application {
    @Override
    public void start(Stage primaryStage){
        //obtencion de carpeta usuarios
        String rutaApp = System.getProperty("user.home") + File.separator + "McatSc";
        File carpetaPrincipal = new File(rutaApp);

        //verificar si existe la carpeta, sino la creamos
        if(!carpetaPrincipal.exists()){
            boolean crear = carpetaPrincipal.mkdir();
        }

        //instanciamos la stage
        MainMenu mainMenu = new MainMenu(primaryStage);
        //titulo de la app
        primaryStage.setTitle("MCatSc");
        //agregamos icono
        primaryStage.getIcons().add(new Image(getClass().getResourceAsStream("/images/deepslate_tiles.png")));
        //la mostramos
        mainMenu.show();
    }
    public static void main(String[] args) {
        launch(args);
    }
}