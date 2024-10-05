public class Objetivo extends Elemento {
    private boolean completo = false;

    public Objetivo(Coordenada posicion) {
        super(posicion);
    }

    @Override
    public void ubicarElemento(Tablero tablero) {
        tablero.agregarElemento(this, this.getPosicion());
    }

    public boolean isCompleto() {
        return completo;
    }

    public void setCompleto(boolean completo, Laser laser) {
        if (laser.getPosicion().equals(this.getPosicion())) {
            this.completo = true;
        }

    }

    /*
    si laser en la misma posicion que objetivo entonces Completo = True -> metodo publico
    si todos los objetivos = true entonces gano -> se haria en nivel
     */

}
