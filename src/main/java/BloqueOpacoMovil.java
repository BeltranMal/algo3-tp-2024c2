public class BloqueOpacoMovil extends Bloque implements interactuarConLaser {
    public BloqueOpacoMovil(Coordenada posicion) {
        super(new Piso(posicion), "opacoMovil");
    }

    @Override
    public void movimientoLaser(Laser laser) {
        laser.setDireccion(laser.getDireccion());
    }
}
