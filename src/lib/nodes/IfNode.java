package src.lib.nodes;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class IfNode extends Node {
    private String condition;
    private List<Node> trueBranch;

    public IfNode(String condition, List<Node> trueBranch) {
        this.condition = condition;
        this.trueBranch = trueBranch;
    }

    @Override
    public
    String render(Map<String, Object> context) {
        Object value = context.get(condition);
        if (Boolean.TRUE.equals(value)) {
            return trueBranch.stream().map(node -> node.render(context)).collect(Collectors.joining());
        }
        return "";
    }
}