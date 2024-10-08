public class EmisorLaser extends Elemento {
    private final String direccion;


    public EmisorLaser(Coordenada posicion, String direccion) {
        super(posicion);
        this.direccion = direccion;
    }

    @Override
    public void ubicarElemento(Tablero tablero) {
        // no va en el tablero
    }

    public String getDireccion() {
        return direccion;
    }

}
