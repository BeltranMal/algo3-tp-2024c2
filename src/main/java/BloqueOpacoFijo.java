public class BloqueOpacoFijo extends Bloque {

    public BloqueOpacoFijo(Coordenada coordenada) {
        super(new Piso(coordenada),"opacoFijo");
    }

    @Override
    public void movimientoLaser(Laser laser) {
        laser.setDireccion(laser.getDireccion());
    }

}
