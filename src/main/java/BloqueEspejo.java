public class BloqueEspejo extends Bloque {

    public BloqueEspejo(Piso piso) {
        super(piso, "espejo");
    }

    @Override
    public void movimientoLaser(Laser laser) {
        System.out.println("direccion laser antes de espejo: " + laser.getDireccion());


        laser.setDireccion(laser.reflejarDireccion(laser.getDireccion().getLast(), this.getPosicion()));
        Coordenada nuevaCoordenada = laser.cambioPosicion(this.getPosicion(), laser.getDireccion().getLast());


        System.out.println("nueva posicion laser despues de espejo: " + nuevaCoordenada.x + " " + nuevaCoordenada.y);
        System.out.println("direccion laser despues de espejo: " + laser.getDireccion());
        // /(X +1) * 2 - 1 -> hay que revertir esto
        laser.setPosicion(nuevaCoordenada);

        //laser.moverLaser();

    }
}
