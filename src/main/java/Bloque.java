public abstract class Bloque extends Elemento {
    private final String bloque;
    private Piso piso;

    public Bloque(Piso piso, String bloque) {
        super(piso.getPosicion()); // llama al constructor de elementos
        this.bloque = bloque;
        this.piso = piso;
    }

    public Coordenada moverBloque(Coordenada posicion_nueva){
        Coordenada posicion_antigua = this.getPosicion();
        if (NoEsFijo()){
            int x = (posicion_nueva.x + 1) * 2 - 1;
            int y = (posicion_nueva.y + 1) * 2 - 1;
            posicion_nueva = new Coordenada(x, y);
            this.setPosicion(posicion_nueva);

        }
        else{
            return new Coordenada(-1,-1);
        }
        return posicion_antigua;
    }

    public void setPiso(Piso piso){
        this.piso = piso;
    }

    public abstract void movimientoLaser(Laser laser);

    @Override
    public void ubicarElemento(Tablero tablero) {
        piso.colocarBloque(this);
        tablero.agregarElemento(this, piso.getPosicion());
    }

    public boolean NoEsFijo() {
        return !bloque.equals("opacoFijo");
    }
}
