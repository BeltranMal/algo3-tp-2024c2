public class EmisorLaser {
    private Coordenada posicion;
    private String direccion;


    public EmisorLaser(Coordenada posicion, String direccion) {
        this.posicion = posicion;
        this.direccion = direccion;
    }

    public Coordenada getPosicion() {
        return posicion;
    }

    public String getDireccion() {
        return direccion;
    }

    public void disparar(Coordenada posicion, String direccion) {

    }
}
