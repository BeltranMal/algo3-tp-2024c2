public class Tablero {

    private final Elemento[][] grilla;
    private final int filas;
    private final int columnas;

    public Tablero(int filas, int columnas) {
        this.filas = filas * 2 + 1;
        this.columnas = columnas * 2 + 1;
        this.grilla = new Elemento[this.filas][this.columnas];
    }

    // set on click -> el elemento donde se hizo click es nuestro bloque -> obtenereLEMENTO -> se usa en parte grafica -> se usa en la parte logica

    public Elemento obtenerElemento(Coordenada coordenada){
        int x = (coordenada.x + 1) * 2 -1;
        int y = (coordenada.y + 1) * 2 -1;
        return grilla[x][y];
    }

    public boolean posicionValida(Coordenada coordenada){
        int x = coordenada.x;
        int y = coordenada.y;
        return (x >= 0 && x < filas && y >= 0 && y < columnas);
    }

    public boolean movimientoValido(Coordenada coordenada){

        Elemento elemento = obtenerElemento(coordenada);

        if (posicionValida(elemento.getPosicion())) {

            return (elemento instanceof Piso && ((Piso) elemento).estaVacio());
        }
        return false;
    }


    public void agregarElemento(Elemento elemento, Coordenada coordenada){
        int x = coordenada.x;
        int y = coordenada.y;
        grilla[x][y] = elemento;
    }


    public void actualizarTablero(Coordenada coordenadaNueva, Bloque bloque){ // para mover el bloque

        if (movimientoValido(coordenadaNueva)){

            Coordenada antigua_posicion = bloque.moverBloque(coordenadaNueva);

            if(!antigua_posicion.equals(new Coordenada(-1, -1))){
                Piso piso = (Piso) obtenerElemento(coordenadaNueva);
                piso.colocarBloque(bloque);
                agregarElemento(bloque, coordenadaNueva);

                Piso pisoNuevo = new Piso(bloque.getPosicion());
                pisoNuevo.removerBloque();
                agregarElemento(pisoNuevo, antigua_posicion);
            }
        }
    }



    public void inicializarGrilla(Elemento elemento) {
        if (elemento != null) {
            elemento.ubicarElemento(this);
        }
    }

    public void imprimirTablero(){
        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                Elemento elemento = grilla[i][j];
                if (elemento == null) {
                    System.out.print(" ");

                } else {
                    System.out.print(elemento.getClass().getSimpleName());
                }
            }
            System.out.println();
        }
    }
}