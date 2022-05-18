package expression;

public class Const implements Name {
    int num;

    public Const(int num) {
        this.num = num;
    }

    @Override
    public int evaluate(int x) {
        return num;
    }

    @Override
    public int evaluate(int x, int y, int z) {
        return num;
    }

    @Override
    public String toString() {
        return String.valueOf(num);
    }

    @Override
    public int hashCode() {
        return num;
    }

    @Override
    public boolean equals(Object o){
        if (o instanceof Const){
            return ((Const) o).num == this.num;
        } return false;
    }
}
