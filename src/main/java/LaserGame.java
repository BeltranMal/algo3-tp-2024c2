import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;


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

        Scene scene = new Scene(root, 300, 400);
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
        ArrayList<Elemento> oye = nivel.EyO();



        for (int i = 0; i < tablero_logico.getFilas(); i++) {
            for (int j = 0; j < tablero_logico.getColumnas(); j++) {
                Elemento elemento = tablero_logico.obtenerElemento(new Coordenada(i, j));


                if (elemento != null && !(elemento instanceof EmisorLaser) && !(elemento instanceof Objetivo)) {
                    Rectangle celda = new Rectangle(50, 50);  // Ajustar tamaño

                    if (elemento instanceof Bloque) {
                        celda.setFill(asignarColor((Bloque) elemento));

                    } else if (elemento instanceof Piso) {
                        celda.setFill(Color.LIGHTGRAY);

                    }

                    int finalJ = j;
                    int finalI = i;

                    celda.setOnMouseClicked(e -> manejarClickCelda(finalI, finalJ, tablero_logico));
                    gridPane.add(celda, j, i);
                }
            }
        }
        dibujarEmObj(oye,gridPane);
        MoverLaser(gridPane);
        tableroRender.getChildren().add(gridPane);
    }

    private Paint asignarColor(Bloque elemento) {
        if (elemento instanceof BloqueOpacoFijo) {
            return Color.BLACK;
        } else if (elemento instanceof BloqueOpacoMovil) {
            return Color.GRAY;
        } else if (elemento instanceof BloqueEspejo) {
            return Color.STEELBLUE;
        }
        return Color.TURQUOISE;

    }

    private void dibujarEmObj(ArrayList<Elemento> oye, GridPane gridPane) {
        for (Elemento elemento : oye) {
            if (elemento instanceof EmisorLaser emisor) {
                Circle e = new Circle(5);  // Ajustar tamaño
                e.setFill(Color.RED);
                gridPane.add(e, emisor.getPosicion().y / 2, emisor.getPosicion().x / 2);
            } else if (elemento instanceof Objetivo objetivo) {
                Circle o = new Circle(5);
                o.setStroke(Color.RED);
                o.setStrokeWidth(2);
                o.setFill(Color.TRANSPARENT);
                int x = objetivo.getPosicion().x;
                int y = objetivo.getPosicion().y;
                //   int offsetX = x % 2 == 0 ? -5 : 5; // Adjust for border placement
            //    int offsetY = y % 2 == 0 ? -5 : 5; // Adjust for border placement
            //    o.setTranslateX(offsetX);
            //    o.setTranslateY(offsetY);
                gridPane.add(o, y / 2 , x / 2);
            }
        }
    }



    private void MoverLaser(GridPane gridPane) {
        ArrayList<Elemento> oye = nivel.EyO();
        for (Elemento e : oye) {
            if (e instanceof EmisorLaser) {
                dibujarLaser((EmisorLaser) e, gridPane);
            }
        }
    }

    private void dibujarLaser(EmisorLaser emisor, GridPane gridPane) {

        Laser laser = new Laser(emisor,nivel.devolverTablero());
        ArrayList<String> direcciones = laser.getDireccion();
        ArrayList<Coordenada> laserPath = laser.moverLaser();
        Line l = new Line(0, 0, 50, 0);  // cuando cambia de direccion hacer un 0,50,50,0
        for (int i = 0; i < laserPath.size(); i++) {
            Coordenada c = laserPath.get(i);
            System.out.println(c.x + " " + c.y);
            String direccion = direcciones.get(i);
            switch (direccion) {
                case "NW", "SE" -> {
                    l = new Line(0, 0, 50, 50);  // cuando cambia de direccion hacer un 0,50,50,0
                    l.setStroke(Color.RED);
                    l.setStrokeWidth(2);
                    gridPane.add(l, c.y / 2, c.x / 2);
                }
                case "SW", "NE" -> {
                    l = new Line(0, 50, 50, 0);  // cuando cambia de direccion hacer un 0,50,50,0
                    l.setStroke(Color.RED);
                    l.setStrokeWidth(2);
                    gridPane.add(l, c.y / 2, c.x / 2);
                }
            }
        }

    }

    private void manejarClickCelda(int i, int j, Tablero tablero_logico) {
        Coordenada coordenadaActual = new Coordenada(i, j);
        Elemento elementoActual = tablero_logico.obtenerElemento(coordenadaActual);

        if (posicionSeleccionada == null) {

            if (elementoActual instanceof Bloque) {
                if (((Bloque) elementoActual).NoEsFijo()) {
                    posicionSeleccionada = coordenadaActual;
                    // hacer notar que esta seleccionada

                }

            }
        }
        else {
            if (elementoActual instanceof Piso && ((Piso) elementoActual).estaVacio()) {
                Bloque bloque = (Bloque) tablero_logico.obtenerElemento(posicionSeleccionada);
                tablero_logico.actualizarTablero(coordenadaActual, bloque);

                CrearTablero();

            }

            posicionSeleccionada = null; // Reiniciar la selección
        }
    }


    private boolean nivelTerminado() {
        return nivel.juegoGanado();
    }




    public static void main(String[] args) {
        launch();

    }
}




/*

Paso 3 -> crear un metodo que sea juego ganado -> usar objetivos y su estado ?   -> cuando se gana el juego bloquear . alert???
Paso 4-> flujo de juego en nivel -> hasta que no se gana o se toca otro boton no termina

Paso 5 -> colores correctos bloque -> buffer ?
Paso 6 -> aparecer laser
Paso 7 > mover  laser


   */



