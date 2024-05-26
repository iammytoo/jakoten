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

        String[] keys = variable.split("\\.");
        Object value = context;
        
        for (String key : keys) {
            if (value instanceof Map) {
                value = ((Map<?, ?>) value).get(key);
                if (!(value instanceof Map)) {
                    break;
                }
            } else {
                return null;
            }
        }
        
        return value != null ? value.toString() : "null";
    }
}