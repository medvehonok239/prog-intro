package expression;

class Add extends BinaryOperation {
    public Add(Name name1, Name name2) {
        super(name1, name2);
        sign = '+';
    }

    @Override
    public int count(int first, int second) {
        return first + second;
    }

}
