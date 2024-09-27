public class Bloque extends Elemento {
    private String tipo;

    public Bloque(Coordenada posicion, String tipo) {
        super(posicion); // llama al constructor de elementos
        this.tipo = tipo;
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



}