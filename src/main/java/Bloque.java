public abstract class Bloque extends Elemento {
    private String tipo;

    public Bloque(Coordenada posicion, String tipo) {
        super(posicion); // llama al constructor de elementos
        this.tipo = tipo;
    }

    public abstract void moverBloque(Coordenada posicion);



}