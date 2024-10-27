import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Polyline;
import javafx.scene.shape.Rectangle;
import java.util.ArrayList;

public class TableroRender {
    private final LaserGame game;

    public TableroRender(LaserGame game) {
        this.game = game;
    }

    public VBox crearTablero(Nivel nivel, VBox tableroRender) {
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
                    int finalJ = j, finalI = i;
                    celda.setOnMouseClicked(_ -> game.manejarClickCelda(finalI, finalJ, tableroLogico, this));
                    gridPane.add(celda, j, i);
                }
            }
        }
        tableroRender.getChildren().add(gridPane);
        dibujarEmisorObjetivo(gridPane, nivel);
        dibujarLaser(gridPane, nivel);
        return tableroRender;
    }
    private void dibujarEmisorObjetivo(GridPane gridPane, Nivel nivel) {
        Pane circlePane = new Pane();
        circlePane.setMouseTransparent(true);
        gridPane.add(circlePane, 0, 0, gridPane.getColumnCount(), gridPane.getRowCount());

        for (Elemento elementoEspecial : nivel.elementosEspeciales()) {
            Circle circulo = new Circle(5);
            Coordenada pos = elementoEspecial.getPosicion();
            circulo.setFill(elementoEspecial.puedeSerGolpeadoPorLaser() ? Color.TRANSPARENT : Color.RED);
            circulo.setStroke(elementoEspecial.puedeSerGolpeadoPorLaser() ? Color.RED : Color.TRANSPARENT);
            circulo.setLayoutX(pos.y * 25);
            circulo.setLayoutY(pos.x * 25);
            circlePane.getChildren().add(circulo);
        }
    }

    private void dibujarLaser(GridPane gridPane, Nivel nivel) {
        nivel.reestablecerLasers();
        Pane laserPane = new Pane();
        laserPane.setMouseTransparent(true);
        gridPane.add(laserPane, 0, 0, gridPane.getColumnCount(), gridPane.getRowCount());
        ArrayList<Laser> laseresCopy = new ArrayList<>(nivel.getLaseresActivos());
        int cantidadLaseres = laseresCopy.size();

        for (Laser laser : laseresCopy) {
            dibujarRuta(laser, laserPane, nivel);
        }
        while (cantidadLaseres != nivel.getLaseresActivos().size()) {
            laseresCopy = new ArrayList<>(nivel.getLaseresActivos());
            for (Laser laser : laseresCopy) {
                dibujarRuta(laser, laserPane, nivel);
            }
            cantidadLaseres++;
        }
    }

    private void dibujarRuta(Laser laser, Pane laserPane, Nivel nivel) {
        ArrayList<Coordenada> laserPath = laser.moverLaser();
        nivel.objetivosAlcanzados(laserPath);
        Polyline polyline = new Polyline();
        polyline.setStroke(Color.RED);
        polyline.setStrokeWidth(2);

        for (Coordenada coordenada : laserPath) {
            double x = coordenada.y * 25;
            double y =  coordenada.x * 25;
            polyline.getPoints().addAll(x, y);
        }
        laserPane.getChildren().add(polyline);
    }

    private Paint asignarColor(Elemento elemento) {
        if (elemento instanceof BloqueOpacoFijo) return Color.BLACK;
        if (elemento instanceof BloqueOpacoMovil) return Color.GRAY;
        if (elemento instanceof BloqueEspejo) return Color.STEELBLUE;
        if (elemento instanceof BloqueVidrio) return Color.AZURE;
        if (elemento instanceof BloqueCristal) return Color.TURQUOISE;
        return Color.LIGHTGRAY;
    }
}
