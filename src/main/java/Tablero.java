public class Tablero {

    private final Elemento[][] grilla;
    private final int filas;
    private final int columnas;

    public Tablero(int filas, int columnas) {

        this.filas = filas;
        this.columnas = columnas;
        this.grilla = new Elemento[filas][columnas];
    }

    // set on click -> el elemento donde se hizo click es nuestro bloque -> obtenereLEMENTO -> se usa en parte grafica -> se usa en la parte logica

    public Elemento obtenerElemento(Coordenada coordenada){
        int x = coordenada.getCoordenada().get(0);
        int y = coordenada.getCoordenada().get(1);
        return grilla[x][y];
    }

    public boolean movimientoValido(Coordenada coordenada){

        Elemento elemento = obtenerElemento(coordenada);

        int x = elemento.getPosicion().getCoordenada().get(0);
        int y = elemento.getPosicion().getCoordenada().get(1);

        if (x >= 0 && x < filas && y >= 0 && y < columnas) {
            return (elemento instanceof Piso && ((Piso) elemento).estaVacio());
        }
        return false;
    }


    public void actualizarTablero(Coordenada coordenadaNueva, Bloque bloque){ // para mover el bloque

        if (movimientoValido(coordenadaNueva)){

            Coordenada antigua_posicion = bloque.moverBloque(coordenadaNueva);

            if(!antigua_posicion.equals(new Coordenada(-1, -1))){

                int x_nuevo = coordenadaNueva.getCoordenada().get(0);
                int y_nuevo = coordenadaNueva.getCoordenada().get(1);
                Piso piso = (Piso) obtenerElemento(coordenadaNueva);
                piso.colocarBloque(bloque);

                grilla[x_nuevo][y_nuevo] = bloque;

                int x_antigua = antigua_posicion.getCoordenada().get(0);
                int y_antigua = antigua_posicion.getCoordenada().get(1);

                Piso pisoNuevo = new Piso(bloque.getPosicion());
                pisoNuevo.removerBloque();
                grilla[x_antigua][y_antigua] = pisoNuevo;
            }
        }
    }



    public void inicializarGrilla(Elemento elemento) {

        // agregar validaciones
        Coordenada posicion = elemento.getPosicion();

        Piso pisoNuevo = new Piso(posicion);
        if (elemento instanceof Bloque) {
            pisoNuevo.colocarBloque((Bloque) elemento);
            int x = posicion.getCoordenada().get(0);
            int y = posicion.getCoordenada().get(1);
            grilla[x][y] = elemento;
        }
        else if (elemento instanceof Piso){
            int x = posicion.getCoordenada().get(0);
            int y = posicion.getCoordenada().get(1);
            grilla[x][y] = elemento;
        }
    }

    public void imprimirTablero(){
        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                Elemento elemento = grilla[i][j];
                if (elemento == null) {
                    System.out.print(" ");
                } else {
                    System.out.print(elemento);
                }
            }
            System.out.println();
        }
    }
}