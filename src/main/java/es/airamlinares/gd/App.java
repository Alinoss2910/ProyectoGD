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
    int posPinX = 690;
    int posPinX2 = 990;
    int posPinX3 = 1040;    
    final int POSYPIN = 430;
    int posXJug = 100;
    int posYJug = 435;
    int velSalto;    
    int cantPinchos = 15;
    ImageView imgViewPin;
    ImageView imgViewPin2;
    ImageView imgViewPin3;
    ImageView imgViewCubo;

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
        //Llama al metodo de cargar las imagenes
        cargarImgs(paneRoot);
        Rectangle pincho = crearPincho1(paneRoot);
        Rectangle pincho2 = crearPincho2(paneRoot);
        Rectangle pincho3 = crearPincho3(paneRoot);
        Rectangle jugador = crearJugador(paneRoot);
        posInic();
        saltoJug(scene);
        movPinchos(pincho, pincho2, pincho3, jugador);
        salidaPinchosAleatorio(random);
    }

    public static void main(String[] args) {
        launch();
    }
    private void cargarImgs(Pane paneRoot) {
        //Cargar imagen del panel principal
        Image img = new Image(getClass().getResourceAsStream("/images/fondo1.jpg"));
        ImageView imgView = new ImageView(img);
        //Poner la imagen de fondo
        paneRoot.getChildren().add(imgView);
        //Imagen del pincho
        Image imgPincho = new Image(getClass().getResourceAsStream("/images/pincho.png"));
        Image imgPinchoV = new Image(getClass().getResourceAsStream("/images/pinchoV.png"));
        Image imgPinchoR = new Image(getClass().getResourceAsStream("/images/pinchoR.png"));
        imgViewPin = new ImageView(imgPincho);
        imgViewPin2 = new ImageView(imgPinchoR);
        imgViewPin3 = new ImageView(imgPinchoV);        
        //Imagen Jugador
        Image imgCubo = new Image(getClass().getResourceAsStream("/images/cubo.png"));        
        imgViewCubo = new ImageView(imgCubo);
        imgViewPin.setLayoutX(0);
        imgViewPin.setLayoutY(0);
        imgViewCubo.setLayoutX(0);
        imgViewCubo.setLayoutY(0);        
    }
    private Rectangle crearPincho1(Pane paneRoot) {
        //Define objeto pincho
        Rectangle pincho = new Rectangle(15, 15, 20, 20);
        pincho.setFill(Color.TRANSPARENT);
        groupPincho.getChildren().add(pincho);
        groupPincho.getChildren().add(imgViewPin);
        //Añade a la pantalla el grupo
        paneRoot.getChildren().add(groupPincho);
        return pincho;
    }
    private Rectangle crearPincho2(Pane paneRoot) {
       //Pincho 2
        Rectangle pincho2 = new Rectangle(15, 15, 20, 20);
        pincho2.setFill(Color.TRANSPARENT);
        groupPincho2.getChildren().add(pincho2);
        groupPincho2.getChildren().add(imgViewPin2);
        //Añade a la pantalla el grupo
        paneRoot.getChildren().add(groupPincho2);
        return pincho2;
    }
    private Rectangle crearPincho3(Pane paneRoot) {
        //Pincho 3
        Rectangle pincho3 = new Rectangle(15, 15, 20, 20);
        pincho3.setFill(Color.TRANSPARENT);
        groupPincho3.getChildren().add(pincho3);
        groupPincho3.getChildren().add(imgViewPin3);
        //Añade a la pantalla el grupo
        paneRoot.getChildren().add(groupPincho3);
        return pincho3;
    }
    private Rectangle crearJugador(Pane paneRoot) {
        //Define Jugador        
        Rectangle jugador = new Rectangle(0, 0, 50, 50);
        jugador.setFill(Color.TRANSPARENT);
        groupCubo.getChildren().add(jugador);
        groupCubo.getChildren().add(imgViewCubo);
        //Añade grupo a la pantalla
        paneRoot.getChildren().add(groupCubo);
        return jugador;
    }
    private void posInic() {
        //Posicion inicial de los pinchos y jugador
        groupPincho.setLayoutY(POSYPIN);
        groupPincho.setLayoutX(posPinX);
        groupPincho2.setLayoutY(POSYPIN);
        groupPincho2.setLayoutX(posPinX2);
        groupPincho3.setLayoutY(POSYPIN);
        groupPincho3.setLayoutX(posPinX3);
        groupCubo.setLayoutY(posYJug);
        groupCubo.setLayoutX(posXJug);
    }
    private void saltoJug(Scene scene) {
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
    private void colPin1(Rectangle jugador, Rectangle pincho) {
        Shape shapeColision = Shape.intersect(jugador, pincho);
            boolean colisionVacia = shapeColision.getBoundsInLocal().isEmpty();
            if(colisionVacia == false) {
                //Detecta colision
                posPinX = 690;
                groupPincho.setLayoutX(posPinX);
                posPinX2 = 990;
                groupPincho2.setLayoutX(posPinX2);
                posPinX3 = 1040;
                groupPincho3.setLayoutX(posPinX3);
            }
    }
    private void colPin2(Rectangle jugador, Rectangle pincho2) {
        Shape shapeColision2 = Shape.intersect(jugador, pincho2);
            boolean colisionVacia2 = shapeColision2.getBoundsInLocal().isEmpty();
            if(colisionVacia2 == false) {
                //Detecta colision
                posPinX = 690;
                groupPincho.setLayoutX(posPinX);
                posPinX2 = 990;
                groupPincho2.setLayoutX(posPinX2);
                posPinX3 = 1040;
                groupPincho3.setLayoutX(posPinX3);
            }
    }
    private void colPin3(Rectangle jugador, Rectangle pincho3) {
        Shape shapeColision3 = Shape.intersect(jugador, pincho3);
            boolean colisionVacia3 = shapeColision3.getBoundsInLocal().isEmpty();
            if(colisionVacia3 == false) {
                //Detecta colision
                posPinX = 690;
                groupPincho.setLayoutX(posPinX);
                posPinX2 = 990;
                groupPincho2.setLayoutX(posPinX2);
                posPinX3 = 1040;
                groupPincho3.setLayoutX(posPinX3);
            }
    }
    private void movPinchos(Rectangle jugador, Rectangle pincho, Rectangle pincho2, Rectangle pincho3 ) {
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
                    colPin1(jugador, pincho);
                    colPin2(jugador, pincho2);
                    colPin3(jugador, pincho3);
                })
        );
        movPincho.setCycleCount(Timeline.INDEFINITE);
        movPincho.play();
    }
    private void salidaPinchosAleatorio(Random random) {
        //Time line para que salgan los pinchos en distinto orden aleatoriamente
        Timeline aleatorioPincho = new Timeline(
                new KeyFrame(Duration.seconds(0.017), (ActionEvent ae) -> {
                    //Genera numero aleatorio entre 0 y 1
                    if(cantPinchos == 15){
                        if(posPinX < 0 && posPinX2 < 0 && posPinX3 < 0) {
                        cantPinchos = random.nextInt(9);
                        System.out.println("Entra en if de numero aleatorio");
                        }
                    }
                    if(cantPinchos == 0 | cantPinchos == 3 | cantPinchos == 6) {
                        
                        cantPinchos = 15;
                        posPinX = 690;
                        posPinX2 = 990; 
                        posPinX3 = 1040;
                        System.out.println("Picho solo primero");
                    }
                    if(cantPinchos == 1 | cantPinchos == 4 | cantPinchos == 7) {
                        cantPinchos = 15;
                        posPinX = 690;
                        posPinX2 = 740;
                        posPinX3 = 1040;
                        System.out.println("Picho de 2 primero");
                    }
                    if(cantPinchos == 2 | cantPinchos == 5 | cantPinchos == 8) {
                        cantPinchos = 15;
                        posPinX = 690;
                        posPinX2 = 990;
                        posPinX3 = 1290;
                        System.out.println("Los 3 pinchos separados");
                    }
                })
        );
        aleatorioPincho.setCycleCount(Timeline.INDEFINITE);
        aleatorioPincho.play();
    }
}