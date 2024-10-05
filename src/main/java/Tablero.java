public class Tablero {

    private final Elemento[][] grilla;


    public Tablero(int filas, int columnas) {
        filas = filas * 2 + 1;
        columnas = columnas * 2 + 1;
        this.grilla = new Elemento[filas][columnas];
    }




    public Elemento obtenerElemento(Coordenada coordenada){

        int x = coordenada.x;
        int y = coordenada.y;
        if (x % 2 == 0 || y % 2 == 0) {

            Elemento elemento = grilla[x][y];
            if((elemento instanceof Objetivo || elemento instanceof EmisorLaser)) {
                System.out.println("se agarro un emisor laser en " + x + " " + y);
                return elemento;
            }
        }

        // para agarrar el centro del bloque
        x = (x + 1) * 2 - 1;
        y = (y + 1) * 2 - 1;
        if (x >= 0 && x < grilla.length && y >= 0 && y < grilla[0].length) {
            return grilla[x][y];
        }
        return null;
    }

    public boolean posicionValida(Coordenada coordenada){
        int x = coordenada.x;
        int y = coordenada.y;
        return (x >= 0 && x < grilla.length && y >= 0 && y < grilla[0].length);
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


    public void actualizarTablero(Coordenada coordenadaNueva, Bloque bloque) {// para mover el bloque'


        if (movimientoValido(coordenadaNueva)) {

            Coordenada antigua_posicion = bloque.moverBloque(coordenadaNueva);

            if (!antigua_posicion.equals(new Coordenada(-1, -1))) {
                Piso piso = (Piso) obtenerElemento(coordenadaNueva);
                piso.colocarBloque(bloque);

                int x = (coordenadaNueva.x + 1) * 2 - 1;
                int y = (coordenadaNueva.y + 1) * 2 - 1;
                grilla[x][y] = bloque;


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

    public int getFilas() {
        return grilla.length;
    }

    public int getColumnas() {
        return grilla[0].length;
    }
    public void imprimirTablero(){
        for (int i = 0; i < getFilas(); i++) {
            for (int j = 0; j < getColumnas(); j++) {
                Elemento elemento = obtenerElemento(new Coordenada(i, j));
                if (elemento != null) {
                    System.out.print(elemento.getClass().getSimpleName());
                } else {
                    System.out.print(". ");
                }
            }
            System.out.println();
        }
    }
}