public abstract class Bloque extends Elemento {
    private final String bloque;
    private Piso piso;

    public Bloque(Piso piso, String bloque) {
        super(piso.getPosicion());
        this.bloque = bloque;
        this.piso = piso;
    }

    public Coordenada moverBloque(Coordenada posicionNueva){
        Coordenada posicionAntigua = this.getPosicion();
        if (noEsFijo()){
            posicionNueva = posicionNueva.ubicarImpar();
            this.setPosicion(posicionNueva);

        }
        else{
            return new Coordenada(-1,-1);
        }
        return posicionAntigua;
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

    public boolean noEsFijo() {
        return !bloque.equals("opacoFijo");
    }
}
