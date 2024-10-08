import java.util.ArrayList;

public class Laser {
    private Coordenada posicion;
    private Coordenada posicionAnterior;
    private ArrayList<String> direccion = new ArrayList<>();
    private final Tablero tablero;


    public Laser(EmisorLaser origen, Tablero tablero) {

        this.posicion = origen.getPosicion().convertirCoordenada();
        direccion.add(origen.getDireccion());
        this.tablero = tablero;
        this.posicionAnterior = origen.getPosicion().convertirCoordenada();
    }


    public void setPosicion(Coordenada posicion) {
        this.posicion = posicion;
    }

    public void setDireccion(String direccionElemento) {
        direccion.add(direccionElemento);
    }

    private boolean nuevaPosicionValida(Coordenada nuevaPosicion) {
        Coordenada ubicacion = new Coordenada(nuevaPosicion.x, nuevaPosicion.y).ubicarImpar();
        return tablero.posicionValida(ubicacion);
    }

    private Elemento buscarBloque() {
        int[] dx = {-1, 1, 0, 0};
        int[] dy = {0, 0, 1, -1};
        Coordenada[] direcciones = {
                new Coordenada(posicion.x + dx[0], posicion.y + dy[0]),
                new Coordenada(posicion.x + dx[1], posicion.y + dy[1]),
                new Coordenada(posicion.x + dx[2], posicion.y + dy[2]),
                new Coordenada(posicion.x + dx[3], posicion.y + dy[3])
        };

        for (Coordenada coordenada : direcciones) {
            if (nuevaPosicionValida(coordenada) && tablero.obtenerElemento(coordenada) instanceof Bloque) {
                return tablero.obtenerElemento(coordenada);
            }
        }

        return tablero.obtenerElemento(this.posicion);
    }

    public ArrayList<Coordenada> moverLaser() {
        ArrayList<Coordenada> ruta = new ArrayList<>();
        ruta.add(posicionAnterior);
        boolean movimiento = true;

        while (movimiento) {

            if (tablero.posicionValida(this.posicion)) {
                Elemento elemento = buscarBloque();

                if (elemento instanceof Bloque bloque) {
                    direccion.add(this.direccion.getLast());
                    bloque.movimientoLaser(this);

                    if (nuevaPosicionValida(posicion)) {
                        ruta.add(this.posicion);
                        direccion.add(this.direccion.getLast());
                        posicionAnterior = this.posicion;
                    } else {
                        movimiento = false;
                    }
                } else if (elemento instanceof Objetivo objetivo) {
                    objetivo.setCompleto();

                } else {
                    Coordenada nuevaPosicion = continuarMovimiento();
                    if (nuevaPosicionValida(nuevaPosicion)) {

                        ruta.add(this.posicion);
                        this.posicion = nuevaPosicion;
                        ruta.add(this.posicion);
                        direccion.add(this.direccion.getLast());
                        posicionAnterior = this.posicion;

                    } else {
                        movimiento = false;
                    }
                }
            } else {
                movimiento = false;
            }
        }

        return ruta;
    }

    private Coordenada continuarMovimiento() {

        String direccion = this.direccion.getLast();
        return switch (direccion) {
            case "NE" -> new Coordenada((posicion.x - 1), posicion.y + 1);
            case "SE" -> new Coordenada(posicion.x + 1, posicion.y + 1);
            case "SW" -> new Coordenada(posicion.x + 1, posicion.y - 1);
            case "NW" -> new Coordenada(posicion.x - 1, posicion.y - 1);
            case "H" -> new Coordenada(posicion.x, posicion.y + 1);
            default -> this.posicion;
        };
    }

    public Coordenada cambioPosicion(Coordenada posicionElemento, String direccion) {
        int norte = (posicionElemento.x + 1) / 2 - 1;
        int este = (posicionElemento.y + 1) / 2 - 1;
        int sur = norte + 1;
        int oeste = este - 1;

        return switch (direccion) {
            case "NE" -> new Coordenada((norte - 1), este + 1);
            case "SE" -> new Coordenada(sur, este + 1);
            case "SW" -> new Coordenada(sur, oeste);
            case "NW" -> new Coordenada(norte - 1, oeste);
            case "H" -> new Coordenada(posicionElemento.x, posicionElemento.y + 2);
            default -> this.posicion;
        };
    }

    public ArrayList<String> getDireccion() {
        return direccion;
    }


    public String reflejarDireccion(String direccion, Coordenada posicionBloque) {

        int x_bloque = (posicionBloque.x + 1) / 2 - 1;
        int y_bloque = (posicionBloque.y + 1) / 2 - 1;
        int dif_x = Math.abs(posicionAnterior.x - x_bloque);
        int dif_y = Math.abs(posicionAnterior.y - y_bloque);

        if (dif_y == 0) {

            posicionAnterior = new Coordenada(x_bloque, y_bloque);
            return switch (direccion) {
                case "NE" -> "SE";
                case "SE" -> "NE";
                case "SW" -> "NW";
                case "NW" -> "SW";
                default -> "H";
            };
        } else if (dif_x == 1 && dif_y == 1) {

            posicionAnterior = new Coordenada(x_bloque, y_bloque);
            return switch (direccion) {
                case "NE" -> "NW";
                case "SE" -> "SW";
                case "SW" -> "SE";
                case "NW" -> "NE";
                default -> "H";
            };
        }

        return "H";
    }
}
