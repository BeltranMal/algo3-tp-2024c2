public class Piso extends Elemento {
    private Bloque bloque;

    public Piso(Coordenada coordenada) {
        super(coordenada);
        this.bloque = null;
    }

    public boolean estaVacio() {
        return bloque == null;
    }

    public void colocarBloque(Bloque nuevoBloque) {
        this.bloque = nuevoBloque;
    }

    public void removerBloque() {
        this.bloque = null;
    }
}



