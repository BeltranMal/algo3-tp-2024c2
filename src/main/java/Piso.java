public class Piso extends Elemento{

    public Piso(Coordenada coordenada) {
        super(coordenada);
    }

    @Override
    public boolean puedeSerGolpeadoPorLaser() {
        return false;
    }
}



