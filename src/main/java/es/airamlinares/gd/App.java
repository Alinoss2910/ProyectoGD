package es.airamlinares.gd;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;


/**
 * JavaFX App
 */
public class App extends Application {
    Group groupPincho;
    int posPinX = 590;
    final int POSYPIN = 430;

    @Override
    public void start(Stage stage) {
        //Panel Principal
        Pane paneRoot = new Pane();
        var scene = new Scene(paneRoot, 640, 480);
        stage.setResizable(false);
        stage.setTitle("Geometry Dash");
        stage.setScene(scene);
        stage.show();
        //Definir grupo pinchos
        groupPincho = new Group();
        //Cargar imagen del panel principal
        Image img = new Image(getClass().getResourceAsStream("/images/fondo1.jpg"));
        //Imagen del pincho
        Image imgPincho = new Image(getClass().getResourceAsStream("/images/pincho.png"));
        ImageView imgView = new ImageView(img);
        ImageView imgViewPin = new ImageView(imgPincho);
        //Poner la imagen de fondo
        paneRoot.getChildren().add(imgView);
        Rectangle pincho = new Rectangle(posPinX, POSYPIN, 50, 50);
        pincho.setFill(Color.TRANSPARENT);
        groupPincho.getChildren().add(pincho);
        groupPincho.getChildren().add(imgViewPin);
        //Añade a la pantalla el grupo
        paneRoot.getChildren().add(groupPincho);
        //Posicion inicial del pincho
        groupPincho.setLayoutY(POSYPIN);
        groupPincho.setLayoutX(posPinX);
        //Timeline de movimiento pincho
        Timeline movPincho = new Timeline(
                new KeyFrame(Duration.seconds(0.005), (ActionEvent ae) -> {
                    //Cambiar poscision pincho
                    posPinX -= 1;
                    groupPincho.setLayoutX(posPinX);
                    if(posPinX == -50) {
                        posPinX = 690;
                    }
                })
        );
        movPincho.setCycleCount(Timeline.INDEFINITE);
        movPincho.play();
        
        
    }

    public static void main(String[] args) {
        launch();
    }

}