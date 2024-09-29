public class EmisorLaser extends Elemento {
    private final String direccion;


    public EmisorLaser(Coordenada posicion, String direccion) {
        super(posicion);
        this.direccion = direccion;
        //dispararLaser();
    }

    @Override
    public void ubicarElemento(Tablero tablero) {
        tablero.agregarElemento(this, this.getPosicion());
    }

    public String getDireccion() {
        return direccion;
    }
/*
    public void dispararLaser() {
        Laser laser = new Laser(this, tablero);
        laser.moverLaser();
    }

 */
}
