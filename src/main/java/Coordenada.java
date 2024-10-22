public class Coordenada {

    public final int x;
    public final int y;


    public Coordenada(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Coordenada ubicarImpar(){
        int x = (this.x + 1) * 2 - 1;
        int y = (this.y + 1) * 2 - 1;
        return new Coordenada(x, y);
    }
}
