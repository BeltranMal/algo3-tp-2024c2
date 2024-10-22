import javafx.util.Pair;
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

    // Norte una menos en x y sur una mas
    private static final Map<Direccion, Pair<Integer, Integer>> coordenadasMapHorizontal = Map.of(
            NE, new Pair<>(-1, 0),
            NW, new Pair<>(-1, 0),
            SW, new Pair<>(1, 0),
            SE, new Pair<>(1, 0)

    );

    private static final Map<Direccion, Pair<Integer, Integer>> coordenadasMapVertical = Map.of(
            NE, new Pair<>(0, 1),
            NW, new Pair<>(0, -1),
            SW, new Pair<>(0, -1),
            SE, new Pair<>(0, 1)

    );

    public Coordenada sigPosicion(Coordenada coordenada) {
        int x = coordenadasMapHorizontal.get(this).getKey();
        int y = coordenadasMapVertical.get(this).getValue();
        return new Coordenada(coordenada.x + x, coordenada.y + y);
    }

    public Coordenada PosBloque(Coordenada coordenada, boolean horizontal) {
        Pair<Integer, Integer> delta;
        if (horizontal) {
            delta = coordenadasMapHorizontal.get(this);
            return new Coordenada(coordenada.x + delta.getKey(), coordenada.y + delta.getValue());
        }
        delta = coordenadasMapVertical.get(this);

        return new Coordenada(coordenada.x + delta.getKey(), coordenada.y + delta.getValue());
    }

    public Direccion calcularDireccion(boolean horizontal) {

        if (horizontal) {
            return reflejoHorizontal.get(this);
        }
        return reflejoVertical.get(this);
    }
}




