import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        System.out.println("Hello, World!");
        Nivel nivel = new Nivel("level1");

    }
}



// clase bloque -> moverBloque como abstract

// Clase elemento -> superclase -> bloque (tambien superclase) , objetivo y laser , piso
// tablero -> celda
// nivel -> atributo tablero y elementos
// laser -> direccion -> arriba, abajo, izquierda, derecha
// coordenada -> x, y ???



// cada bloque una clase diferente -> dif comportamiento laser -> laser una interfaz -> cada bloque implementa la interfaz laser

