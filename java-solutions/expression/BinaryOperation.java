package expression;

import java.util.Objects;

public abstract class BinaryOperation implements Name {
    Name first;
    Name second;
    protected char sign;

    public abstract int count(int first, int second);

    public BinaryOperation(Name name1, Name name2) {
        first = name1;
        second = name2;
    }

    @Override
    public int evaluate(int x) {
        return count(first.evaluate(x), second.evaluate(x));
    }

    @Override
    public int evaluate(int x, int y, int z) {
        return count(first.evaluate(x, y, z), second.evaluate(x, y, z));
    }

    @Override
    public int hashCode() {
        return Objects.hash(first, second, getClass());
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof BinaryOperation ) {
            BinaryOperation aboba = (BinaryOperation) o;
            return Objects.equals(aboba.first, first) && Objects.equals(aboba.second, second) && Objects.equals(aboba.getClass(), getClass());
        }
        return false;

    }

    @Override
    public String toString() {

        StringBuilder sb = new StringBuilder();
        sb.append("(").append(first).append(" ").append(sign).append(" ").append(second).append(")");
        return sb.toString();
    }

}
