package escript.ast.nodes.function.payload;

public class ReturnPayload<E> {
    private E payload;

    public ReturnPayload(E value) {
        this.payload = value;
    }

    public E getPayload() {
        return payload;
    }

    @Override
    public String toString() {
        return "return";
    }
}
