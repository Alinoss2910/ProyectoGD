package es.airamlinares.gd;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
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
    Group groupCubo;
    int posPinX = 590;
    final int POSYPIN = 430;
    int posXJug = 100;
    int posYJug = 435;
    int velSalto;

    @Override
    public void start(Stage stage) {
        //Panel Principal
        Pane paneRoot = new Pane();
        var scene = new Scene(paneRoot, 640, 480);
        stage.setResizable(false);
        stage.setTitle("Geometry Dash");
        stage.setScene(scene);
        stage.show();
        //Definir grupos
        groupPincho = new Group();
        groupCubo = new Group();
        //Cargar imagen del panel principal
        Image img = new Image(getClass().getResourceAsStream("/images/fondo1.jpg"));
        //Imagen del pincho
        Image imgPincho = new Image(getClass().getResourceAsStream("/images/pincho.png"));
        //Imagen Jugador
        Image imgCubo = new Image(getClass().getResourceAsStream("/images/cubo.png"));
        ImageView imgView = new ImageView(img);
        ImageView imgViewPin = new ImageView(imgPincho);
        ImageView imgViewCubo = new ImageView(imgCubo);
        imgViewPin.setLayoutX(0);
        imgViewPin.setLayoutY(0);
        imgViewCubo.setLayoutX(0);
        imgViewCubo.setLayoutY(0);
        //Poner la imagen de fondo
        paneRoot.getChildren().add(imgView);
        //Define objeto pincho
        Rectangle pincho = new Rectangle(15, 15, 20, 20);
        pincho.setFill(Color.BLACK);
        
        groupPincho.getChildren().add(pincho);
        groupPincho.getChildren().add(imgViewPin);
        //Define Jugador
        Rectangle jugador = new Rectangle(0, 0, 50, 50);
        jugador.setFill(Color.BLACK);
        groupCubo.getChildren().add(jugador);
        //groupCubo.getChildren().add(imgViewCubo);
        //Añade a la pantalla los grupos
        paneRoot.getChildren().add(groupPincho);
        paneRoot.getChildren().add(groupCubo);
        //Posicion inicial del pincho
        groupPincho.setLayoutY(POSYPIN);
        groupPincho.setLayoutX(posPinX);
        groupCubo.setLayoutY(posYJug);
        groupCubo.setLayoutX(posXJug);
        //Timeline de movimiento pincho
        Timeline movPincho = new Timeline(
                new KeyFrame(Duration.seconds(0.017), (ActionEvent ae) -> {
                    //Cambiar poscision pincho
                    posPinX -= 5;
                    groupPincho.setLayoutX(posPinX);
                    if(posPinX == -50) {
                        posPinX = 690;
                    }
                })
        );
        movPincho.setCycleCount(Timeline.INDEFINITE);
        movPincho.play();
        //Salto del jugador
        scene.setOnKeyPressed((KeyEvent event) -> {
            if(event.getCode() == KeyCode.UP && posYJug == 435) { //Si pulsas arriba el jugador salta
                velSalto = -1;
                    }
        });
        Timeline movJug = new Timeline(
                new KeyFrame(Duration.seconds(0.005), (ActionEvent ae) -> {
                    posYJug += velSalto;
                    groupCubo.setLayoutY(posYJug);
                    groupCubo.setLayoutX(posXJug);
                    if(posYJug <= 330) {
                        velSalto = 1;
                    }
                    if(posYJug == 435) {
                        velSalto = 0;
                    }
                    /*if(posXJug +50 >= posPinX && posYJug >= POSYPIN ){
                        posPinX = 690;
                        System.out.println("HOLA");
                    }*/
                })
            );
        movJug.setCycleCount(Timeline.INDEFINITE);
        movJug.play();
    }

    public static void main(String[] args) {
        launch();
    }

}