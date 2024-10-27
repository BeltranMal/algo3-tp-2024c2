import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import java.io.IOException;


public class LaserGame extends Application {
    private Nivel nivel;
    private VBox tableroRender = new VBox();
    Coordenada posicionSeleccionada;

    @Override
    public void start(Stage stage){
        HBox root = new HBox(10);
        VBox Niveles = generarBotonesNivel();
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
        TableroRender tablero = new TableroRender(this);
        tableroRender = tablero.crearTablero(nivel, tableroRender);

    }
    public void manejarClickCelda(int i, int j, Tablero tablero_logico, TableroRender tableroGrafico) {

        Coordenada coordenadaActual = new Coordenada(i, j);
        Elemento elementoActual = tablero_logico.obtenerElemento(coordenadaActual);
        if (elementoActual.puedeSerGolpeadoPorLaser() && elementoActual.movible() && posicionSeleccionada == null ) {
            posicionSeleccionada = coordenadaActual;
        }
        else if (posicionSeleccionada != null) {
            Bloque bloque = (Bloque) tablero_logico.obtenerElemento(posicionSeleccionada);
            tablero_logico.actualizarTablero(coordenadaActual, bloque);
            tableroRender = tableroGrafico.crearTablero(nivel, tableroRender);
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

    public static void main(String[] args) {
        launch();
    }
}
