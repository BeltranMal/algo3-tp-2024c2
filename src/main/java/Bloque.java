public class Bloque extends Elemento {
    private String tipo;
    private Piso piso;

    public Bloque(Piso piso, String tipo) {
        super(piso.getPosicion()); // llama al constructor de elementos
        this.tipo = tipo;
        this.piso = new Piso(piso.getPosicion());
    }

    public Coordenada moverBloque(Coordenada posicion_nueva){
        Coordenada posicion_antigua = this.getPosicion();
        if (!tipo.equals("opacoFijo")){
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

    @Override
    public void ubicarElemento(Tablero tablero) {
        piso.colocarBloque(this);
        tablero.agregarElemento(this, piso.getPosicion());
    }

}
