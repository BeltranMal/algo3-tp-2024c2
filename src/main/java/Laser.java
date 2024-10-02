public class Laser {
    private Coordenada posicion;
    private String direccion;
    private final Tablero tablero;

    public Laser(EmisorLaser origen, Tablero tablero){
        this.posicion = origen.getPosicion();
        this.direccion = origen.getDireccion();
        this.tablero = tablero;
    }

    public void setPosicion(Coordenada posicion){
        this.posicion = posicion;
    }

    public void setDireccion(String direccion){
        this.direccion = direccion;
    }

    public void moverLaser(){
        Coordenada posicionAnterior = this.posicion;
        while (tablero.posicionValida(this.posicion) && posicionAnterior != this.posicion) {

            Elemento elemento = tablero.obtenerElemento(this.posicion);
            posicionAnterior = this.posicion;

            if (elemento instanceof Bloque bloque) {
                bloque.movimientoLaser(this);
            }

            setPosicion(cambioPosicion(posicionAnterior, direccion));
        }
    }

    public Coordenada cambioPosicion(Coordenada posicionElemento, String direccion){
        return switch (direccion) {
            case "NE" -> new Coordenada(posicionElemento.x + 1, posicionElemento.y - 1);
            case "SE" -> new Coordenada(posicionElemento.x + 1, posicionElemento.y + 1);
            case "SW" -> new Coordenada(posicionElemento.x - 1, posicionElemento.y + 1);
            case "NW" -> new Coordenada(posicionElemento.x - 1, posicionElemento.y - 1);
            case "H" -> new Coordenada(posicionElemento.x, posicionElemento.y + 2);
            default -> this.posicion;
        };
    }

    public String getDireccion() {
        return direccion;
    }
    public String reflejarDireccion(String direccion) {
        // reflejar el láser según la dirección
        return switch (direccion) {
            case "NE" -> "SW";
            case "SE" -> "NW";
            case "SW" -> "NE";
            case "NW" -> "SE";
            default -> direccion;
        };
    }
}
