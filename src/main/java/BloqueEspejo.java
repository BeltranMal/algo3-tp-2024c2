public class BloqueEspejo extends Bloque implements Laser{

    public BloqueEspejo(Coordenada posicion) {
        super(posicion, "espejo");
    }
/*
    private String reflejarDireccion(String direccion) {
        // reflejar el láser según la dirección
        switch (direccion) {
            case "N":
                return "S";
            case "S":
                return "N";
            case "E":
                return "W";
            case "W":
                return "E";
            case "NE":
                return "SW";
            case "SE":
                return "NW";
            case "SW":
                return "NE";
            case "NW":
                return "SE";
            default:
                return direccion;
        }

 */


    @Override
    public void interacccionLaser() {
        // refleja el laser

    }
}
