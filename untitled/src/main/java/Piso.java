public class Piso extends Elemento {
    private Bloque bloque;

    public Piso(Coordenada coordenada) {
        super(coordenada);
        this.bloque = null;
    }

    public boolean estaVacio() {
        return bloque == null;
    }

    public boolean colocarBloque(Bloque nuevoBloque) {
        if (estaVacio()) {
            this.bloque = nuevoBloque;
            return true;
        }
        return false;
    }

    public Bloque removerBloque() {
        Bloque bloqueRemovido = this.bloque;
        this.bloque = null;
        return bloqueRemovido;
    }
}

