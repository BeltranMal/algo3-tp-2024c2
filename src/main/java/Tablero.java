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
        int x = coordenada.x;
        int y = coordenada.y;
        return grilla[x][y];
    }

    public boolean movimientoValido(Coordenada coordenada){

        Elemento elemento = obtenerElemento(coordenada);

        int x = elemento.getPosicion().x;
        int y = elemento.getPosicion().y;

        if (x >= 0 && x < filas && y >= 0 && y < columnas) {

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
                    System.out.print("celda_vacia");
                } else {
                    System.out.print(elemento);
                }
            }
            System.out.println();
        }
    }
}