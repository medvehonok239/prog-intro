package expression;

import java.util.Objects;

public class Variable implements Name{
    String name;

    public Variable(String name) {
        this.name = name;
    }

    @Override
    public int evaluate(int x) {
        return x;
    }

    @Override
    public int evaluate(int x, int y, int z) {
        switch (name){
            case "x":
                return x;
            case "y":
                return y;
            case "z":
                return z;
            default:
                break;
        }
        return 0;
    }

    @Override
    public String toString() {
        return name;
    }

    @Override
    public int hashCode(){
        return name.hashCode();
    }

    @Override
    public boolean equals(Object o){
        if (o instanceof Variable){
            return ((Variable) o).name.equals(this.name);
        } return false;
    }
}
