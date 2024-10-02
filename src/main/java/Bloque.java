public abstract class Bloque extends Elemento {
    private final String bloque;
    private Piso piso;

    public Bloque(Piso piso, String tipo) {
        super(piso.getPosicion()); // llama al constructor de elementos
        this.bloque = tipo;
        this.piso = piso;
    }

    public Coordenada moverBloque(Coordenada posicion_nueva){
        Coordenada posicion_antigua = this.getPosicion();
        if (!bloque.equals("opacoFijo")){
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

    public Piso getPiso(){
        return this.piso;
    }

    public abstract void movimientoLaser(Laser laser);

    @Override
    public void ubicarElemento(Tablero tablero) {
        piso.colocarBloque(this);
        tablero.agregarElemento(this, piso.getPosicion());
    }

}
