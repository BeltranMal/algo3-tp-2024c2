public class EmisorLaser extends Elemento {
    private final Direccion direccion;

    public EmisorLaser(Coordenada posicion, Direccion direccion) {
        super(posicion);
        this.direccion = direccion;

    }
    @Override
    public boolean puedeSerGolpeadoPorLaser() {
        return false;
    }
    @Override
    public void ubicarElemento(Tablero tablero) {
        // no va en el tablero
    }

    public void dispararLaser(Nivel nivel) {
        Laser laser = new Laser(this.getPosicion(), direccion, nivel);
        laser.agregarANivel();
    }
}
