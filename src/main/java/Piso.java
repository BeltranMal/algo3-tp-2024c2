public class Piso extends Elemento{
    private Bloque bloque;

    public Piso(Coordenada coordenada) {
        super(coordenada);
        this.bloque = null;
    }

    @Override
    public void ubicarElemento(Tablero tablero) {
        tablero.agregarElemento(this, this.getPosicion());
    }

    public boolean estaVacio() {
        return bloque == null;
    }

    public void colocarBloque(Bloque nuevoBloque) {
        this.bloque = nuevoBloque;
        nuevoBloque.setPiso(this);
    }

    public void removerBloque() {
        if (this.bloque != null) {
            this.bloque.setPiso(null);
            this.bloque = null;
        }
    }

}



