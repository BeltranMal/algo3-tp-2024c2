public abstract class Bloque extends Elemento {

    public Bloque(Coordenada posicion) {
        super(posicion);
    }

    public Coordenada moverBloque(Coordenada posicionNueva){
        Coordenada posicionAntigua = this.getPosicion();
        if (movible()){
            this.setPosicion(posicionNueva);
        }
        return posicionAntigua;
    }

    public abstract void interaccionLaser(Laser laser);

    @Override
    public boolean movible() {
        return true;
    }
}

