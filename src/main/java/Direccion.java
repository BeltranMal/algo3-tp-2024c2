import java.util.Map;

public enum Direccion {
    NE, SE, SW, NW;

    private static final Map<Direccion, Direccion> reflejoHorizontal = Map.of(
            NE, SE,
            SE, NE,
            SW, NW,
            NW, SW
    );

    private static final Map<Direccion, Direccion> reflejoVertical = Map.of(
            NE, NW,
            SE, SW,
            SW, SE,
            NW, NE
    );
    private static final Map<Direccion, Coordenada> coordenadasMapHorizontal = Map.of(
            NE, new Coordenada(-1, 0),
            NW, new Coordenada(-1, 0),
            SW, new Coordenada(1, 0),
            SE, new Coordenada(1, 0)

    );

    private static final Map<Direccion, Coordenada> coordenadasMapVertical = Map.of(
            NE, new Coordenada(0, 1),
            NW, new Coordenada(0, -1),
            SW, new Coordenada(0, -1),
            SE, new Coordenada(0, 1)

    );

    public Coordenada sigPosicion(Coordenada coordenada) {
        int x = coordenadasMapHorizontal.get(this).x;
        int y = coordenadasMapVertical.get(this).y;
        return new Coordenada(coordenada.x + x, coordenada.y + y);
    }

    public Coordenada PosBloque(Coordenada coordenada, boolean horizontal) {
        Coordenada delta;
        if (horizontal) {
            delta = coordenadasMapHorizontal.get(this);
            return new Coordenada(coordenada.x + delta.x, coordenada.y + delta.y);
        }
        delta = coordenadasMapVertical.get(this);

        return new Coordenada(coordenada.x + delta.x, coordenada.y + delta.y);
    }

    public Direccion calcularDireccion(boolean horizontal) {

        if (horizontal) {
            return reflejoHorizontal.get(this);
        }
        return reflejoVertical.get(this);
    }
}




