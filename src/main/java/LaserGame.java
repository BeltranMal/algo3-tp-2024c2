import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
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
        VBox Niveles = generarBotonesNivel();
        tableroRender = new VBox();
        root.getChildren().addAll(Niveles, tableroRender);

        Scene scene = new Scene(root, 600, 600);
        stage.setScene(scene);
        stage.show();
    }

    private VBox generarBotonesNivel() {

        VBox niveles = new VBox(10);
        String[] levels = {"level1", "level2", "level3", "level4", "level5", "level6"};

        for (String level : levels) {
            Button levelButton = new Button(level);
            levelButton.setOnAction(e -> {
                try {
                    generarNivel(level);
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            });
            niveles.getChildren().add(levelButton);
        }

        return niveles;
    }

    private void generarNivel(String levelName) throws IOException {

        nivel = new Nivel(levelName);
        crearTablero();

    }

    private void crearTablero() {
        tableroRender.getChildren().clear();
        Tablero tableroLogico = nivel.devolverTablero();
        GridPane gridPane = new GridPane();
        gridPane.setHgap(1);
        gridPane.setVgap(1);

        for (int i = 0; i < tableroLogico.getFilas(); i++) {
            for (int j = 0; j < tableroLogico.getColumnas(); j++) {
                Elemento elemento = tableroLogico.obtenerElemento(new Coordenada(i, j));

                if (elemento != null) {
                    Rectangle celda = new Rectangle(50, 50);

                    if (elemento instanceof Bloque) {
                        celda.setFill(asignarColor((Bloque) elemento));

                    } else if (elemento instanceof Piso) {
                        celda.setFill(Color.LIGHTGRAY);
                    }

                    int finalJ = j;
                    int finalI = i;

                    celda.setOnMouseClicked(e -> manejarClickCelda(finalI, finalJ, tableroLogico));
                    gridPane.add(celda, j, i);
                }

            }
        }
        tableroRender.getChildren().add(gridPane);
        dibujarEmisorObjetivo(gridPane);
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


    private void dibujarEmisorObjetivo(GridPane gridPane) {

        for (Elemento elementoEspecial : nivel.emisoresyObjetivos()) {
            Circle circulo = new Circle(5);
            Coordenada grafica = elementoEspecial.getPosicion().convertirCoordenada();

            if (elementoEspecial instanceof EmisorLaser emisor) {
                circulo.setFill(Color.RED);
                dibujarLaser(emisor, gridPane);
            }
            else if (elementoEspecial instanceof Objetivo objetivo) {

                circulo.setStroke(Color.RED);
                circulo.setFill(Color.TRANSPARENT);
            }
            gridPane.add(circulo, grafica.y , grafica.x);
        }
    }

    private void dibujarLaser(EmisorLaser emisor, GridPane gridPane) {
        Laser laser = new Laser(emisor, nivel.devolverTablero());
        ArrayList<String> direcciones = laser.getDireccion();
        ArrayList<Coordenada> laserPath = laser.moverLaser();

        for (int i = 0; i < laserPath.size(); i++) {
            Coordenada coordenada = laserPath.get(i);

            String direccion = direcciones.get(i);
            Line l = switch (direccion) {
                case "NW", "SE" -> new Line(0, 0, 50, 50);
                case "SW", "NE" -> new Line(0, 50, 50, 0);
                default -> null;
            };

            if (l != null) {
                l.setStroke(Color.RED);
                l.setStrokeWidth(2);
                gridPane.add(l, coordenada.y, coordenada.x);
            }
        }
    }

    private void manejarClickCelda(int i, int j, Tablero tablero_logico) {
        Coordenada coordenadaActual = new Coordenada(i, j);
        Elemento elementoActual = tablero_logico.obtenerElemento(coordenadaActual);

        if (posicionSeleccionada == null) {

            if (elementoActual instanceof Bloque) {
                if (((Bloque) elementoActual).noEsFijo()) {
                    posicionSeleccionada = coordenadaActual;
                }
            }
        }
        else {
            if (elementoActual instanceof Piso && ((Piso) elementoActual).estaVacio()) {
                Bloque bloque = (Bloque) tablero_logico.obtenerElemento(posicionSeleccionada);
                tablero_logico.actualizarTablero(coordenadaActual, bloque);

                crearTablero();

            }
            posicionSeleccionada = null;
        }

        if (nivelTerminado()) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Juego Terminado");
            alert.setHeaderText("Felicidades, has ganado el juego");
            alert.showAndWait();
        }
    }


    private boolean nivelTerminado() {
        return nivel.juegoGanado();
    }

    public static void main(String[] args) {
        launch();

    }
}
