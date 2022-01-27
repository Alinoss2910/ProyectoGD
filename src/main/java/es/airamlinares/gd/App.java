package es.airamlinares.gd;

import java.util.Random;
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
import javafx.scene.shape.Shape;
import javafx.stage.Stage;
import javafx.util.Duration;


/**
 * JavaFX App
 */
public class App extends Application {
    Group groupPincho;
    Group groupPincho2;
    Group groupPincho3;
    Group groupCubo;
    int posPinX = 590;
    int posPinX2 = 790;
    int posPinX3 = 840;    
    final int POSYPIN = 430;
    int posXJug = 100;
    int posYJug = 435;
    int velSalto;
    
    int cantPinchos;

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
        groupPincho2 = new Group();
        groupPincho3 = new Group();
        Random random = new Random();
        //Cargar imagen del panel principal
        Image img = new Image(getClass().getResourceAsStream("/images/fondo1.jpg"));
        //Imagen del pincho
        Image imgPincho = new Image(getClass().getResourceAsStream("/images/pincho.png"));
        //Imagen Jugador
        Image imgCubo = new Image(getClass().getResourceAsStream("/images/cubo.png"));
        ImageView imgView = new ImageView(img);
        ImageView imgViewPin = new ImageView(imgPincho);
        ImageView imgViewPin2 = new ImageView(imgPincho);
        ImageView imgViewPin3 = new ImageView(imgPincho);
        ImageView imgViewCubo = new ImageView(imgCubo);
        imgViewPin.setLayoutX(0);
        imgViewPin.setLayoutY(0);
        imgViewCubo.setLayoutX(0);
        imgViewCubo.setLayoutY(0);
        //Poner la imagen de fondo
        paneRoot.getChildren().add(imgView);
        //Define objeto pincho
        Rectangle pincho = new Rectangle(15, 15, 20, 20);
        pincho.setFill(Color.TRANSPARENT);
        groupPincho.getChildren().add(pincho);
        groupPincho.getChildren().add(imgViewPin);
        //Pinchos de 2
        Rectangle pincho2 = new Rectangle(15, 15, 20, 20);
        pincho2.setFill(Color.TRANSPARENT);
        groupPincho2.getChildren().add(pincho2);
        groupPincho2.getChildren().add(imgViewPin2);
        
        Rectangle pincho3 = new Rectangle(15, 15, 20, 20);
        pincho3.setFill(Color.TRANSPARENT);
        groupPincho3.getChildren().add(pincho3);
        groupPincho3.getChildren().add(imgViewPin3);
        //Define Jugador        
        Rectangle jugador = new Rectangle(0, 0, 50, 50);
        jugador.setFill(Color.TRANSPARENT);
        groupCubo.getChildren().add(jugador);
        groupCubo.getChildren().add(imgViewCubo);
        //Añade a la pantalla los grupos
        paneRoot.getChildren().add(groupPincho);
        paneRoot.getChildren().add(groupPincho2);
        paneRoot.getChildren().add(groupPincho3);
        paneRoot.getChildren().add(groupCubo);
        //Posicion inicial de los pinchos
        groupPincho.setLayoutY(POSYPIN);
        groupPincho.setLayoutX(posPinX);
        groupPincho2.setLayoutY(POSYPIN);
        groupPincho2.setLayoutX(posPinX2);
        groupPincho3.setLayoutY(POSYPIN);
        groupPincho3.setLayoutX(posPinX3);
        groupCubo.setLayoutY(posYJug);
        groupCubo.setLayoutX(posXJug);
        //Time line para que salgan los pinchos en distinto orden aleatoriamente
        Timeline aleatorioPincho = new Timeline(
                new KeyFrame(Duration.seconds(1), (ActionEvent ae) -> {
                    //Genera numero aleatorio entre 0 y 1
                    if(posPinX <= 0 && posPinX2 <= 0 && posPinX3 <= 0) {
                        cantPinchos = random.nextInt(2);
                        System.out.println(cantPinchos);
                        System.out.println("hola");
                    }
                })
        );
        //Timeline de movimiento pincho
        Timeline movPincho = new Timeline(
                new KeyFrame(Duration.seconds(0.017), (ActionEvent ae) -> {
                    //Cambiar poscision pincho
                    posPinX -= 5;
                    groupPincho.setLayoutX(posPinX);
                    posPinX2 -= 5;
                    groupPincho2.setLayoutX(posPinX2);
                    posPinX3 -= 5;
                    groupPincho3.setLayoutX(posPinX3);
                    switch(cantPinchos) {
                        case 0:                            
                            if(posPinX == -400) {
                                posPinX = 690;
                            }
                            if(posPinX2 == -200) {
                                posPinX2 = 890;
                            }                            
                            if(posPinX3 == -150) {
                                posPinX3 = 940;
                            }
                            break;
                        case 1:
                            if(posPinX == -150) {
                                posPinX = 740;
                            }
                            if(posPinX2 == -350) {
                                posPinX2 = 790;
                            }
                            if(posPinX3 == -400) {
                                posPinX3 = 990;
                            }
                            break;
                    }
                    
                    Shape shapeColision = Shape.intersect(jugador, pincho);
                    boolean colisionVacia = shapeColision.getBoundsInLocal().isEmpty();
                    if(colisionVacia == false) {
                        //Detecta colision
                        posPinX = 690;
                        groupPincho.setLayoutX(posPinX);
                        System.out.println(posPinX);
                        posPinX2 = 890;
                        groupPincho2.setLayoutX(posPinX2);
                        System.out.println(posPinX2);
                        posPinX3 = 940;
                        groupPincho3.setLayoutX(posPinX3);
                        System.out.println(posPinX3);
                    }
                    Shape shapeColision2 = Shape.intersect(jugador, pincho2);
                    boolean colisionVacia2 = shapeColision2.getBoundsInLocal().isEmpty();
                    if(colisionVacia2 == false) {
                        //Detecta colision
                        posPinX = 690;
                        groupPincho.setLayoutX(posPinX);
                        System.out.println(posPinX);
                        posPinX2 = 890;
                        groupPincho2.setLayoutX(posPinX2);
                        System.out.println(posPinX2);
                        posPinX3 = 940;
                        groupPincho3.setLayoutX(posPinX3);
                        System.out.println(posPinX3);
                    }
                    Shape shapeColision3 = Shape.intersect(jugador, pincho3);
                    boolean colisionVacia3 = shapeColision3.getBoundsInLocal().isEmpty();
                    if(colisionVacia3 == false) {
                        //Detecta colision
                        posPinX = 690;
                        groupPincho.setLayoutX(posPinX);
                        System.out.println(posPinX);
                        posPinX2 = 890;
                        groupPincho2.setLayoutX(posPinX2);
                        System.out.println(posPinX2);
                        posPinX3 = 940;
                        groupPincho3.setLayoutX(posPinX3);
                        System.out.println(posPinX3);
                    }
                })
        );
        movPincho.setCycleCount(Timeline.INDEFINITE);
        movPincho.play();
        //Salto del jugador
        scene.setOnKeyPressed((KeyEvent event) -> {
            if(event.getCode() == KeyCode.UP && posYJug == 435) { //Si pulsas arriba el jugador salta
                velSalto = -4;
            }
        });
        Timeline movJug = new Timeline(
                new KeyFrame(Duration.seconds(0.017), (ActionEvent ae) -> {
                    posYJug += velSalto;
                    groupCubo.setLayoutY(posYJug);
                    //groupCubo.setLayoutX(posXJug);
                    if(posYJug <= 330) {
                        velSalto = 4;
                    }
                    if(posYJug == 435) {
                        velSalto = 0;
                    }
                })
        );
        movJug.setCycleCount(Timeline.INDEFINITE);
        movJug.play();
    }

    public static void main(String[] args) {
        launch();
    }

}