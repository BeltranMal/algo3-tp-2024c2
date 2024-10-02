import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class Main extends Application {
    @Override
    public void start(Stage stage) throws Exception {

        Nivel nivel = new Nivel("level1");

        var label = new Label("Hola mundo!");
        var scene = new Scene(new StackPane(label), 640, 480);
        stage.setScene(scene);
        stage.show();
    }
}


// crar la barra de los niveles y el bloque del t


/*

    | level1      |      tablero          |
    | level2      |      tablero          |
    | level3      |      tablero          |
    | level4      |      tablero          |
    | level5      |      tablero          |
    | level6      |      tablero          |



Paso 1 -> crear esta intefaz
Paso 2 -> crear la claase nivel segun el lavel -> los lelvelx son botones que se tocan y ahi agarramos el label
Paso 3 -> crear una clase en nivel que sea devolver tablero y mostrarlo en tablero

Paso 3.5 -> crear un metodo que sea juego ganado -> usar objetivos y su estado ?   -> cuando se gana el juego bloquear . alert???
Paso 4-> flujo de juego en nivel -> hasta que no se gana o se toca otro boton no termina


Guiarse con juego de la vida


 Hbox -> 1 vbox con los niveles y en la otra el tablero -> Vbox?

   */



