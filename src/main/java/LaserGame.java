import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Polyline;
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
            levelButton.setOnAction(_ -> {
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
        tableroRender.setStyle("-fx-padding: 5;");
        nivel = new Nivel(levelName);
        crearTablero();

    }

    private void crearTablero() {
        tableroRender.getChildren().clear();
        tableroRender.setDisable(false);

        Tablero tableroLogico = nivel.devolverTablero();
        GridPane gridPane = new GridPane();
        gridPane.setHgap(1);
        gridPane.setVgap(1);

        for (int i = 0; i < tableroLogico.getFilas(); i++) {
            for (int j = 0; j < tableroLogico.getColumnas(); j++) {
                Elemento elemento = tableroLogico.obtenerElemento(new Coordenada(i, j));
                if (elemento != null) {
                    Rectangle celda = new Rectangle(50, 50);

                    celda.setFill(asignarColor(elemento));

                    int finalJ = j;
                    int finalI = i;

                    celda.setOnMouseClicked(_ -> manejarClickCelda(finalI, finalJ, tableroLogico));
                    gridPane.add(celda, j, i);
                }
            }
        }
        tableroRender.getChildren().add(gridPane);
        dibujarEmisorObjetivo(gridPane);
        dibujarLaser(gridPane);
    }

    private void dibujarEmisorObjetivo(GridPane gridPane) {
        Pane circlePane = new Pane();
        circlePane.setMouseTransparent(true);
        gridPane.add(circlePane, 0, 0, gridPane.getColumnCount(), gridPane.getRowCount());

        for (Elemento elementoEspecial : nivel.elementosEspeciales()) {
            Circle circulo = new Circle(5);
            Coordenada pos = elementoEspecial.getPosicion();

            if (!elementoEspecial.puedeSerGolpeadoPorLaser()) {
                EmisorLaser emisor = (EmisorLaser) elementoEspecial;
                circulo.setFill(Color.RED);
                emisor.dispararLaser(nivel);
            }
            else {
                Objetivo objetivo = (Objetivo) elementoEspecial;
                circulo.setStroke(Color.RED);
                circulo.setFill(Color.TRANSPARENT);
                objetivo.setCompleto(false);
            }
            circulo.setLayoutX(pos.y *  25);
            circulo.setLayoutY(pos.x *  25);
            circlePane.getChildren().add(circulo);
        }
    }

    private void dibujarLaser(GridPane gridPane) {
        Pane laserPane = new Pane();
        laserPane.setMouseTransparent(true);
        gridPane.add(laserPane, 0, 0, gridPane.getColumnCount(), gridPane.getRowCount());

        ArrayList<Laser> laseresCopy = new ArrayList<>(nivel.getLaseresActivos());
        int cantidadLaseres = laseresCopy.size();

        for (Laser laser : laseresCopy) {
            dibujarRuta(laser, laserPane);
        }

        while (cantidadLaseres != nivel.getLaseresActivos().size()) {
            laseresCopy = new ArrayList<>(nivel.getLaseresActivos());
            for (Laser laser : laseresCopy) {
                dibujarRuta(laser, laserPane);
            }
            cantidadLaseres++;
        }
    }

        private void dibujarRuta(Laser laser, Pane laserPane) {
            ArrayList<Coordenada> laserPath = laser.moverLaser();
            nivel.objetivosAlcanzados(laserPath);
            Polyline polyline = new Polyline();
            polyline.setStroke(Color.RED);
            polyline.setStrokeWidth(2);

            for (Coordenada coordenada : laserPath) {
                double x = coordenada.y * 25;
                double y = coordenada.x * 25;
                polyline.getPoints().addAll(x, y);
            }
            laserPane.getChildren().add(polyline);
        }

    private void manejarClickCelda(int i, int j, Tablero tablero_logico) {
        Coordenada coordenadaActual = new Coordenada(i, j);
        Elemento elementoActual = tablero_logico.obtenerElemento(coordenadaActual);

        if (elementoActual.puedeSerGolpeadoPorLaser()) {

            if (elementoActual.movible() && posicionSeleccionada == null ) {
                posicionSeleccionada = coordenadaActual;
            }
        }
        else if (posicionSeleccionada != null) {
            nivel.limpiarLaseres();
            Bloque bloque = (Bloque) tablero_logico.obtenerElemento(posicionSeleccionada);
            tablero_logico.actualizarTablero(coordenadaActual, bloque);
            crearTablero();
            posicionSeleccionada = null;
        }

        juegoGanado();

    }

    private void juegoGanado() {
        if (nivel.juegoGanado()) {
            tableroRender.setDisable(true);
            tableroRender.setStyle("-fx-background-color: lightgreen; -fx-padding: 10;");
            Rectangle clip = new Rectangle(500, 500);
            tableroRender.setClip(clip);
        }
    }

    private Paint asignarColor(Elemento elemento) {
        if (elemento instanceof BloqueOpacoFijo) {
            return Color.BLACK;
        } else if (elemento instanceof BloqueOpacoMovil) {
            return Color.GRAY;
        } else if (elemento instanceof BloqueEspejo) {
            return Color.STEELBLUE;
        }
        else if (elemento instanceof BloqueVidrio) {
            return Color.AZURE;
        }
        else if (elemento instanceof BloqueCristal) {
            return Color.TURQUOISE;
        }
        return Color.LIGHTGRAY;
    }

    public static void main(String[] args) {
        launch();
    }
}
