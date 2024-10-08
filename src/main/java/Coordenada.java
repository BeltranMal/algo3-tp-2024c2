public class Coordenada {

    public final int x;
    public final int y;


    public Coordenada(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Coordenada ubicarImpar(){
        int x = (this.x + 1) * 2 -1;
        int y = (this.y + 1) * 2 - 1;
        return new Coordenada(x, y);
    }

    public Coordenada convertirCoordenada(){

        int x,y;
        if (this.x % 2 != 0) {
            x = this.x / 2;
        }
        else {

            x = Math.max((this.x / 2 - 1), 0);
        }

        if (this.y % 2 != 0) {
            y = this.y / 2;
        }
        else {

            y = Math.max((this.y / 2 - 1), 0);
        }

        return new Coordenada(x, y);
    }
}
