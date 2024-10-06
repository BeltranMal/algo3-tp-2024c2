public class EmisorLaser extends Elemento {
    private final String direccion;


    public EmisorLaser(Coordenada posicion, String direccion, Tablero tablero) {
        super(posicion);
        this.direccion = direccion;
        dispararLaser(tablero);
    }

    @Override
    public void ubicarElemento(Tablero tablero) {
        tablero.agregarElemento(this, this.getPosicion());
    }

    public String getDireccion() {
        return direccion;
    }

    public void dispararLaser(Tablero tablero) {
        Laser laser = new Laser(this, tablero);
        laser.moverLaser();
    }


}
