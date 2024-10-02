public class Objetivo extends Elemento {

    public Objetivo(Coordenada posicion) {
        super(posicion);
    }

    @Override
    public void ubicarElemento(Tablero tablero) {
        tablero.agregarElemento(this, this.getPosicion());
    }

    /*
    si laser en la misma posicion que objetivo entonces Completo = True -> metodo publico
    si todos los objetivos = true entonces gano -> se haria en nivel
     */

}
