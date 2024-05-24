package src.lib.nodes;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ForLoopNode extends Node {
    public String variable;
    public String collection;
    public List<Node> loopBody;

    public ForLoopNode(String variable, String collection, List<Node> loopBody) {
        this.variable = variable;
        this.collection = collection;
        this.loopBody = loopBody;
    }

    @Override
    public
    String render(Map<String, Object> context) {
        Object collectionObject = context.get(collection);
        if (collectionObject instanceof Iterable) {
            StringBuilder sb = new StringBuilder();
            for (Object item : (Iterable<?>) collectionObject) {
                Map<String, Object> newContext = new HashMap<>(context);
                newContext.put(variable, item);
                loopBody.forEach(node -> sb.append(node.render(newContext)));
            }
            return sb.toString();
        }
        return "";
    }
}