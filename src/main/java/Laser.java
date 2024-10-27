import java.util.ArrayList;

public class Laser {
    private Coordenada posicion;
    private Direccion direccion;
    private final Nivel nivel;
    ArrayList<Coordenada> ruta = new ArrayList<>();

    public Laser(Coordenada posicion, Direccion direccion, Nivel nivel) {

        this.posicion = posicion;
        this.direccion = direccion;
        this.nivel = nivel;

        ruta.add(posicion);
    }

    public void setPosicion(Coordenada posicion) {
        this.posicion = posicion;
    }

    private boolean nuevaPosicionValida(Coordenada nuevaPosicion) {
        Tablero tablero = nivel.devolverTablero();
        return (nuevaPosicion.x >= 0 && nuevaPosicion.x <= tablero.getFilas() && nuevaPosicion.y >= 0 && nuevaPosicion.y <= tablero.getColumnas());
    }

    private Elemento buscarBloque() {
        Tablero tablero = nivel.devolverTablero();
        Coordenada ubicacion = direccion.PosBloque(posicion, posicion.x % 2 == 0);
        return tablero.obtenerElemento(ubicacion);
    }

    public ArrayList<Coordenada> moverLaser() {

        while (nuevaPosicionValida(posicion)) {
            ruta.add(posicion);
            Elemento elemento = buscarBloque();

            if (elemento != null && elemento.puedeSerGolpeadoPorLaser()) {
                Bloque bloque = (Bloque) elemento;
                bloque.interaccionLaser(this);
            } else {
                posicion = continuarMovimiento();
            }
        }
        return ruta;
    }

    private Coordenada continuarMovimiento() {
        return direccion.sigPosicion(posicion);
    }

    public Direccion getDireccion() {
        return direccion;
    }

    public boolean golpeHorizontal(Coordenada posicionBloque) {
        int difX = Math.abs(posicion.x - posicionBloque.x);
        int difY = Math.abs(posicion.y - posicionBloque.y);
        return difY == 0 && difX == 1;
    }

    public void reflejarMovimiento(Coordenada posicionBloque) {
        boolean horizontal = golpeHorizontal(posicionBloque);
        direccion = direccion.calcularDireccion(horizontal);
        posicion = continuarMovimiento();
    }

    public Coordenada getPosicion() {
        return posicion;
    }

    public Nivel getNivel() {
        return nivel;
    }

    public void agregarANivel() {
        nivel.agregarLaser(this);
    }
}