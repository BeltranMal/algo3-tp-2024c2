public class Laser {
    private Coordenada posicion;
    private Coordenada posicionAnterior;
    private String direccion;
    private final Tablero tablero;

    public Laser(EmisorLaser origen, Tablero tablero){
        this.posicion = origen.getPosicion();
        this.direccion = origen.getDireccion();
       // this.destino =  posicion;
        this.tablero = tablero;
    }

    public Coordenada getPosicion(){
        return posicion;
    }

    public void setPosicion(Coordenada posicion){
        this.posicion = posicion;
    }

    public void setDireccion(String direccion){
        this.direccion = direccion;
    }

    public void moverLaser(){

        //destino = descifrarPosFinal();
        boolean a = true;
     //   System.out.println("posicion inicial: " + this.posicion.x + " " + this.posicion.y);

        while (tablero.posicionValida(this.posicion)) {
            posicionAnterior = this.posicion;
            Elemento elemento = tablero.obtenerElemento(this.posicion);


            if (elemento instanceof Bloque bloque) {
           //    System.out.println("se encontro en " + posicion.x + " " + posicion.y);
           //     System.out.println("bloque" + bloque + " " + bloque.getPosicion().x + " " + bloque.getPosicion().y);
                bloque.movimientoLaser(this);
            //    System.out.println("nueva posicion laser: " + posicion.x + " " + posicion.y);

                if (bloque instanceof BloqueOpacoMovil){
                  //  System.out.println("cargas mas el laser");
                    break;
                }
            }
            else {
                setPosicion(continuarMovimiento());
               //System.out.println("nueva posicion laser final moviemento laser : " + posicion.x + " " + posicion.y);
            }

        }
    }

    private Coordenada continuarMovimiento(){
        Coordenada posLas = this.posicion;
        return switch (direccion) {
            case "NE" -> new Coordenada((posLas.x + 1), posLas.y - 1);
            case "SE" -> new Coordenada(posLas.x + 1, posLas.y + 1);
            case "SW" -> new Coordenada(posLas.x - 1, posLas.y + 1);
            case "NW" -> new Coordenada(posLas.x - 1, posLas.y - 1);
            case "H" -> new Coordenada(posLas.x, posLas.y + 1);
            default -> this.posicion;
        };

    }

    public Coordenada cambioPosicion(Coordenada posicionElemento, String direccion){
        return switch (direccion) {
            case "NE" -> new Coordenada((posicionElemento.x + 1), posicionElemento.y - 1);
            case "SE" -> new Coordenada(posicionElemento.x + 1, posicionElemento.y + 1);
            case "SW" -> new Coordenada(posicionElemento.x - 1, posicionElemento.y + 1);
            case "NW" -> new Coordenada(posicionElemento.x - 1, posicionElemento.y - 1);
            case "H" -> new Coordenada(posicionElemento.x, posicionElemento.y + 1);
            default -> this.posicion;
        };
    }

    public String getDireccion() {
        return direccion;
    }
    public String reflejarDireccion(String direccion, Coordenada posicionBloque){
        int x_bloque = posicionBloque.x;
        int y_bloque = posicionBloque.y;
        // si pegamos al costado estamos a una fila y a dos columnas de distancia
        // si pegamos arriba estamos a dos filas y una columna de distancia

        int x_laser = (this.posicion.x +1) * 2 - 1; // 5 , 3
        int y_laser = (this.posicion.y +1) * 2 - 1; // 3 , 5


        int x_prueba = (posicionAnterior.x +1) * 2 - 1;
        int y_prueba = (posicionAnterior.y +1) * 2 - 1;


        int dif_x = Math.abs(posicionAnterior.x - x_bloque); // si esto es 1 >=-> ppegamos al costado si es 2 pegamos arriba
        int dif_y = Math.abs(posicionAnterior.y - y_bloque);


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