import java.util.ArrayList;

public class Laser {
    private Coordenada posicion;
    private final Coordenada posicionAnterior;
    private ArrayList<String> direccion = new ArrayList<>();
    private final Tablero tablero;


    public Laser(EmisorLaser origen, Tablero tablero){
        this.posicion = origen.getPosicion();
        direccion.add(origen.getDireccion());
        this.tablero = tablero;
        int x =( origen.getPosicion().x + 1) / 2 - 1;
        int y = Math.max(((origen.getPosicion().y + 1) / 2 - 1), 0);
        this.posicionAnterior = new Coordenada(x, y);
    }

    public Coordenada getPosicion(){
        return posicion;
    }

    public void setPosicion(Coordenada posicion){
        this.posicion = posicion;
    }

    public void setDireccion(String direccion1){
        direccion.add(direccion1);
    }

    public ArrayList<Coordenada> moverLaser(){
        ArrayList<Coordenada> ruta = new ArrayList<>();

        System.out.println("direcciones " + direccion);
        ruta.add(posicion);
        System.out.println("agregue ruta numero: " + ruta.size());
        boolean movimiento = true;
        while (movimiento){
            if (tablero.posicionValida(this.posicion)) {
                System.out.println("posiciion inicial: " + posicion.x + " " + posicion.y);

                Elemento elemento = tablero.obtenerElemento(this.posicion);


                if (elemento instanceof Bloque bloque) {
                    bloque.movimientoLaser(this);
                    ruta.add(posicion);
                    System.out.println("agregue ruta numero: " + ruta.size());
                    System.out.println(bloque);
                } else if (elemento instanceof Objetivo objetivo) {
                    objetivo.setCompleto();

                } else {
                    setPosicion(continuarMovimiento());
                    if (tablero.posicionValida(this.posicion)) {
                        ruta.add(posicion);
                        System.out.println("agregue ruta numero: " + ruta.size());
                        direccion.add(this.direccion.getLast());
                        System.out.println("agregue direccion numero: " + direccion.size());
                        System.out.println("posiciion aaaaaaaaa: " + posicion.x + " " + posicion.y);
                    }
                }
            }
            else {
                movimiento = false;
            }
        }
        System.out.println("cantidad de direcciones: " + direccion.size());
        System.out.println("cantidad de posiciones: " + ruta.size());
        return ruta;
    }

    private Coordenada continuarMovimiento(){
        Coordenada posLas = this.posicion;
        String direccion = this.direccion.getLast();
        return switch (direccion) {
            case "NE" -> new Coordenada((posLas.x - 1), posLas.y + 1);
            case "SE" -> new Coordenada(posLas.x + 1, posLas.y + 1);
            case "SW" -> new Coordenada(posLas.x + 1, posLas.y - 1);
            case "NW" -> new Coordenada(posLas.x - 1, posLas.y - 1);
            case "H" -> new Coordenada(posLas.x, posLas.y + 1);
            default -> this.posicion;
        };

    }

    public Coordenada cambioPosicion(Coordenada posicionElemento, String direccion) {
        int norte = (posicionElemento.x + 1) /2 -1 ;
        int este = (posicionElemento.y + 1) / 2 - 1;
        int sur = norte + 1;
        int oeste = este - 1;



        return switch (direccion) {
            case "NE" -> new Coordenada((norte - 1), este + 1);
            case "SE" -> new Coordenada(sur, este + 1);
            case "SW" -> new Coordenada(sur, oeste );
            case "NW" -> new Coordenada(norte - 1, oeste);
            case "H" -> new Coordenada(posicionElemento.x, posicionElemento.y + 2);
            default -> this.posicion;
        };
    }

    public ArrayList<String> getDireccion() {
        return direccion;
    }

    public String reflejarDireccion(String direccion, Coordenada posicionBloque) {
        int x_bloque = (posicionBloque.x + 1) /2 - 1;
        int y_bloque = (posicionBloque.y + 1) / 2 - 1; // 3,5

     // si pegamos al costado estamos a una fila y a dos columnas de distancia
        // si pegamos arriba estamos a dos filas y una columna de distancia


        System.out.println("posicion anterior: " + posicionAnterior.x + " " + posicionAnterior.y);
        System.out.println("posicion bloque: " + x_bloque + " " + y_bloque);
        int dif_x = Math.abs(posicionAnterior.x - x_bloque); // si esto es 1 >=-> ppegamos al costado si es 2 pegamos arriba
        int dif_y = Math.abs(posicionAnterior.y - y_bloque);

        // pasarla a una funcion booleana para ver si vamos a pegar en un bloque o no

        System.out.println("dif x: " + dif_x);
        System.out.println("dif y: " + dif_y);

        if (dif_x == 2 && dif_y == 1){
            return switch (direccion) {
                case "NE" -> "SE";
                case "SE" -> "NE";
                case "SW" -> "NW";
                case "NW" -> "SW";
                default -> "H";
            };
        }
        else if (dif_x == 1 && dif_y == 2){
            return switch (direccion) {
                case "NE" -> "NW";
                case "SE" -> "SW";
                case "SW" -> "SE";
                case "NW" -> "NE";
                default -> "H";
            };
        }

        // reflejar el láser según la dirección
    return "H";
    }

    }


// error en logica. si viene el emisor del noroeste se comporta como cambio posicion. sino hay que hacer un metodo que contenga la direccion anterior para que si va al noroeste cuando venga del sureste reste en las filas y sume en las columnas .