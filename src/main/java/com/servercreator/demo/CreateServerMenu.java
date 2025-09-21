package com.servercreator.demo;


import com.google.gson.*;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import javax.net.ssl.HttpsURLConnection;
import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

//clase que define el menu de crear server
public class CreateServerMenu {
     //variable para almacenar la ventana
     private Stage primaryStage;

     //constructor que recibe la ventana y y la guarda para su uso en la clase
     public CreateServerMenu(Stage primaryStage) {
        this.primaryStage = primaryStage;
     }

     public void show() throws Exception {
        // declaracion de boton de regresar y estilo css
        Button regresar = new Button("Regresar");
        regresar.getStyleClass().add("boton-personalizado");

        //funcion de regresar a main menu
        regresar.setOnAction(e -> {
            MainMenu mainMenu = new MainMenu(primaryStage);
            mainMenu.show();
        });

        // div para alinear el boton de regresar
        HBox botonregresar = new HBox(regresar);
        botonregresar.setPadding(new Insets(15, 0, 0,15));

        //introduccion de nombre de servidor nuevo
        Label tituloServer = new Label("Nombre del Servidor:");
        TextField nombreServer = new TextField();

        //estilos css para el texto y textfield
        tituloServer.getStyleClass().add("texto");
        nombreServer.getStyleClass().add("texto-area");

        //creacion de combobox de versiones y asignacion de contenido + estilo
        ComboBox<String> versiones = new ComboBox<>();
        versiones.getItems().addAll(obtenerVersiones());
        versiones.setValue("Versiones...");
        versiones.getStyleClass().add("combobox-perso");

        //caja para alinear label y textbox
        HBox serverNameBox = new HBox(15, tituloServer, nombreServer, versiones);
        serverNameBox.setAlignment(Pos.CENTER);

        //nombre de forge y imagen relacionada, propiedades de la imagen
        Label forgeName = new Label("Forge");
        ImageView forge = new ImageView(new Image(getClass().getResourceAsStream("/images/forgelogo.png")));
        forge.setFitHeight(150);
        forge.setFitWidth(150);
        forgeName.getStyleClass().add("texto");

        // div para alinear nombre e imagen y centrarlos
        VBox forgeBox = new VBox(15);
        forgeBox.setAlignment(Pos.CENTER);
        forgeBox.getChildren().addAll(forgeName, forge);
        forgeBox.getStyleClass().add("caja-click");
        forgeBox.setUserData("NeoForge"); //asignacion de datos para facilitar escritura

         //nombre de neoforge y imagen relacionada, propiedades de tamaño de la imagen
        Label neoForgeName = new Label("NeoForge");
        ImageView neoForge = new ImageView(new Image(getClass().getResourceAsStream("/images/neoforged.png")));
        neoForge.setFitHeight(150);
        neoForge.setFitWidth(150);
        neoForgeName.getStyleClass().add("texto");

        // div para alinear nombre e imagen y centrarlos
        VBox neoForgeBox = new VBox(15);
        neoForgeBox.setAlignment(Pos.CENTER);
        neoForgeBox.getChildren().addAll(neoForgeName, neoForge);
        neoForgeBox.getStyleClass().add("caja-click");
        neoForgeBox.setUserData("NeoForge"); //asignacion de datos para facilitar escritura

        //nombre de fabric y imagen relacionada, propiedades de la imagen
        Label fabricName = new Label("Fabric");
        ImageView fabric = new ImageView(new Image(getClass().getResourceAsStream("/images/fabriclogo.png")));
        fabric.setFitHeight(150);
        fabric.setFitWidth(150);
        fabricName.getStyleClass().add("texto");

        // div para alinear nombre e imagen y centrarlos
        VBox fabricBox = new VBox(15);
        fabricBox.setAlignment(Pos.CENTER);
        fabricBox.getChildren().addAll(fabricName, fabric);
        fabricBox.getStyleClass().add("caja-click");
        fabricBox.setUserData("Fabric"); //asignacion de datos para facilitar escritura

        //nombre de vanilla y imagen relacionada, propiedades de la imagen
        Label vanillaName = new Label("Vanilla");
        ImageView vanilla = new ImageView(new Image(getClass().getResourceAsStream("/images/vanilla.png")));
        vanilla.setFitHeight(150);
        vanilla.setFitWidth(150);
        vanillaName.getStyleClass().add("texto");

        // div para alinear nombre e imagen y centrarlos
        VBox vanillaBox = new VBox(15);
        vanillaBox.setAlignment(Pos.CENTER);
        vanillaBox.getChildren().addAll(vanillaName, vanilla);
        vanillaBox.getStyleClass().add("caja-click");
        vanillaBox.setUserData("Vanilla"); //asignacion de datos para facilitar escritura

         //funcionamiento de tipo buton radio
        //creamos una lista con las VBoxes
        List<VBox> opciones = List.of(forgeBox, neoForgeBox, fabricBox, vanillaBox);
        StringProperty serverType = new SimpleStringProperty("no");//Eliminamos referencia atomica y pasamos a stringproperty
        for (VBox opcion : opciones) {
            //asignamos onclick para asignar estilos o eliminarlos
            opcion.setOnMouseClicked(event -> {
                for(VBox box : opciones) {
                    box.getStyleClass().remove("caja-click-seleccionada");
                }
                if (!opcion.getStyleClass().contains("caja-click-seleccionada")) {
                    opcion.getStyleClass().add("caja-click-seleccionada");
                    serverType.set(opcion.getUserData().toString());
                }
            });
        }

        // div para alinear de manera horizontal las imagenes
        HBox imagenes = new HBox(15);
        imagenes.setAlignment(Pos.CENTER);
        imagenes.setPadding(new Insets(15));
        imagenes.getChildren().addAll(forgeBox, neoForgeBox, fabricBox, vanillaBox);

        // creacion de boton de crear y estilo
        Button crear =  new Button("Crear Servidor");
        crear.getStyleClass().add("boton-aceptar");

        // bloqueo del boton crear para evitar fallos al crear la carpeta y serverSpecs.ini
        crear.disableProperty().bind(
                nombreServer.textProperty().isEmpty()
                        .or(versiones.valueProperty().isEqualTo("Versiones..."))
                        .or(serverType.isEqualTo("no"))  // falta validacion para tipo
        );

        crear.setOnAction(e -> {
            //ruta de la carpeta servidor y creacion de carpeta de servidor
            String ruta = System.getProperty("user.home") + File.separator + "McatSc" + File.separator + "Servers" + File.separator + nombreServer.getText();
            File server = new File(ruta);

            //verificacion de existencia y advertencia de si ya existe
            if (server.exists()) {
                Alert warning = new Alert(Alert.AlertType.WARNING);
                warning.setTitle("Advertencia");
                warning.setHeaderText("El servidor ya existe");
                warning.setContentText("Se encontro un archivo con el mismo nombre");
                warning.showAndWait();
            }

            //caso contrario crea la carpeta y alerta de servidor creado con exito
            else{
                //creacion de carpeta
                server.mkdir();

                // inicializacion de .ini
                Properties props =new Properties();

                //valores del serverSpecs.ini
                props.setProperty("NombreServer", nombreServer.getText());
                props.setProperty("Version", versiones.getValue());
                props.setProperty("TipoServer", serverType.get());

                // creacion de archivo serverSpecs.ini en carpeta creada
                File specsfile = new File(server, "serverSpecs.ini");

                // intento de creacion de archivo de especificaciones y manejo de error
                try(FileWriter writer = new FileWriter(specsfile)){
                    props.store(writer,"Especificaciones del servidor");
                } catch (IOException ex) {
                    System.out.println(ex);
                }

                // Alerta cuando el server se crea exitosamente
                Alert success = new Alert(Alert.AlertType.INFORMATION);
                success.setTitle("Servidor");
                success.setHeaderText("Servidor creado");
                success.setContentText("Servidor creado con exito");
                success.showAndWait();
            }
        });


        // Caja de crear para alinear a la derecha y bordes
        HBox crearBox = new HBox(crear);
        crearBox.setAlignment(Pos.CENTER_RIGHT);
        crearBox.setPadding(new Insets(0, 15, 15, 15));

        // div para alinear la caja de servername y las imagenes
        VBox centrarBox = new VBox(15);
        centrarBox.setAlignment(Pos.CENTER);
        centrarBox.getChildren().addAll(serverNameBox, imagenes, crearBox);

        // contenedor general
        BorderPane root = new BorderPane();
        root.setTop(botonregresar);
        root.setCenter(centrarBox);
        root.getStyleClass().add("fondo");

        //creacion de la escena con el div y el tamaño, asignacion de color del fondo llamado al css y inicializacion de escena
        Scene escena = new Scene(root,680,380);
        escena.getStylesheets().add(getClass().getResource("/styles/estilos.css").toExternalForm());
        primaryStage.setScene(escena);
        primaryStage.setMinHeight(380);
        primaryStage.setMinWidth(680);
    }

    //metodo que busca versiones de minecraft en la api oficial, retorna una lista
    public static List<String> obtenerVersiones() throws Exception {
         //url de la pagina, conexion y metodo de conexion para recopilar la info en json
         URL url = new URL("https://piston-meta.mojang.com/mc/game/version_manifest_v2.json\n");
         HttpsURLConnection connection = (HttpsURLConnection) url.openConnection();
         connection.setRequestMethod("GET");

         //obtencion de resultados en formato json y creacion de array con los alementos versions
         JsonElement root = JsonParser.parseReader(new InputStreamReader(connection.getInputStream()));
         JsonArray versiones = root.getAsJsonObject().get("versions").getAsJsonArray();

         //creacion de la lista que se retorna (vacia)
         List<String> lista = new ArrayList<>();

         //obtencion de versiones y  filtrado por tipo y obtencion de id
         for (JsonElement version : versiones) {
              String type = version.getAsJsonObject().get("type").getAsString();
              if (type.equals("release")) {
                   String id = version.getAsJsonObject().get("id").getAsString();
                   lista.add(id);
              }
         }
         //retorno de elementos filtrados para su uso
         return lista;
    }
}
