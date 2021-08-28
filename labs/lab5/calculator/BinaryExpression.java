abstract class BinaryExpression implements Expression {
    private final Expression lft;
    private final Expression rht;
    private final String operator;

    public BinaryExpression(Expression lft, Expression rht, String operator){
        this.lft = lft;
        this.rht = rht;
        this.operator = operator;
    }

    public String toString()
    {
        return "(" + lft + " " + operator + " " + rht + ")";
    }

    protected abstract double _applyOperator(double lft, double rht);

    public double evaluate(Bindings bindings)
    {
        double lftEval = lft.evaluate(bindings);
        double rhtEval = rht.evaluate(bindings);
        return _applyOperator(lftEval, rhtEval);
    }
}

