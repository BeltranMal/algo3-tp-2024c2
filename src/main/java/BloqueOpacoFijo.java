public class BloqueOpacoFijo extends Bloque implements interactuarConLaser {

    public BloqueOpacoFijo(Coordenada coordenada) {
        super(new Piso(coordenada),"opacoFijo");
    }

    @Override
    public void movimientoLaser(Laser laser) {
        laser.setDireccion(laser.getDireccion());
    }

}
