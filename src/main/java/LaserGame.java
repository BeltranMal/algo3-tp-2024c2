import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import java.io.IOException;


public class LaserGame extends Application {
    private Nivel nivel;
    private VBox tableroRender;
    private Coordenada posicionSeleccionada;

    @Override
    public void start(Stage stage){
        HBox root = new HBox(10);

        VBox Niveles = GenerarBotonesNivel();
        tableroRender = new VBox();

        root.getChildren().addAll(Niveles, tableroRender);

        Scene scene = new Scene(root, 800, 600);
        stage.setScene(scene);
        stage.show();
    }

    private VBox GenerarBotonesNivel() {

        VBox Niveles = new VBox(10);
        String[] levels = {"level1", "level2", "level3", "level4", "level5", "level6"};

        for (String level : levels) {
            Button levelButton = new Button(level);
            levelButton.setOnAction(e -> {
                try {
                    GenerarNivel(level);
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            });
            Niveles.getChildren().add(levelButton);
        }

        return Niveles;
    }

    private void GenerarNivel(String levelName) throws IOException {

        nivel = new Nivel(levelName);
        CrearTablero();

    }

    private void CrearTablero() {
        tableroRender.getChildren().clear();
        Tablero tablero_logico = nivel.devolverTablero();
        GridPane gridPane = new GridPane();
        gridPane.setHgap(2);
        gridPane.setVgap(2);
        // hacer linea negra entre celdas


        for (int i = 0; i < tablero_logico.getFilas(); i++) {
            for (int j = 0; j < tablero_logico.getColumnas(); j++) {
                Elemento elemento = tablero_logico.obtenerElemento(new Coordenada(i, j));

                if (elemento instanceof EmisorLaser) {

                    Circle emisorLaser = new Circle(5);
                    emisorLaser.setFill(Color.RED);

                    gridPane.add(emisorLaser, j/2, i/2 );

                } else if (elemento instanceof Objetivo) {


                    Circle objetivo = new Circle(5);
                    objetivo.setStroke(Color.RED);
                    objetivo.setStrokeWidth(2);
                    objetivo.setFill(Color.TRANSPARENT);


                    gridPane.add(objetivo, j/2 , i/2);

                } else if (elemento != null) {
                    Rectangle celda = new Rectangle(50, 50);  // Ajustar tamaño

                    if (elemento instanceof Bloque) {
                        celda.setFill(Color.DARKGREEN);

                    } else if (elemento instanceof Piso) {
                        celda.setFill(Color.LIGHTGRAY);
                      //  System.out.println("piso en " + i + " " + j);
                    }

                    int finalJ = j;
                    int finalI = i;

                    celda.setOnMouseClicked(e -> manejarClickCelda(finalI, finalJ, tablero_logico));
                    gridPane.add(celda, j, i);
                }
            }
        }
        tableroRender.getChildren().add(gridPane);
    }

    private void manejarClickCelda(int i, int j, Tablero tablero_logico) {
        Coordenada coordenadaActual = new Coordenada(i, j);
        Elemento elementoActual = tablero_logico.obtenerElemento(coordenadaActual); // -> funciona bien

        if (posicionSeleccionada == null) {

            if (elementoActual instanceof Bloque) {
                if (((Bloque) elementoActual).NoEsFijo()) {
                    posicionSeleccionada = coordenadaActual;
                    // hacer notass que esta seleccionada

                }

            }
        }
        else {
            if (elementoActual instanceof Piso && ((Piso) elementoActual).estaVacio()) {
                Bloque bloque = (Bloque) tablero_logico.obtenerElemento(posicionSeleccionada);
                tablero_logico.actualizarTablero(coordenadaActual, bloque);

                CrearTablero(); // Redibujar el tablero

            }

            posicionSeleccionada = null; // Reiniciar la selección
        }
    }


    private boolean nivelTerminado() {


            return false;
    }




    public static void main(String[] args) {
        launch();

    }
}




/*

 paso 3 -> crear un metodo que sea juego ganado -> usar objetivos y su estado ?   -> cuando se gana el juego bloquear . alert???
Paso 4-> flujo de juego en nivel -> hasta que no se gana o se toca otro boton no termina

paso 5 -> colores correctos bloque -> buffer ?
paso 6 -> aparecer laser
paso 7 > mover  laser


   */



