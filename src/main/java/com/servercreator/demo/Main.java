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
        File carpetaServer = new File(rutaApp + File.separator + "Servers");
        File carpetaRecursos =  new File(rutaApp + File.separator + "recursos");

        //verificar si existe la carpeta, sino la creamos
        if(!carpetaPrincipal.exists()){
            boolean crear = carpetaPrincipal.mkdir();
        }
        if(!carpetaServer.exists()){
            boolean crearServer = carpetaServer.mkdir();
        }
        if(!carpetaRecursos.exists()){
            boolean crearServer = carpetaServer.mkdir();
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