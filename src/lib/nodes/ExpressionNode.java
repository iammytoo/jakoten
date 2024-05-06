package src.lib.nodes;

import java.util.Map;

public class ExpressionNode extends Node {
    private String variable;

    public ExpressionNode(String variable) {
        this.variable = variable;
    }

    @Override
    public
    String render(Map<String, Object> context) {
        Object value = context.get(variable);
        return value != null ? value.toString() : "null";
    }
}