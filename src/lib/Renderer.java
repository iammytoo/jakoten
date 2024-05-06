package src.lib;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import src.lib.nodes.Node;

public class Renderer {
    public static String render(List<Node> nodes, Map<String, Object> context) {
        return nodes.stream()
                    .map(node -> node.render(context))
                    .collect(Collectors.joining());
    }
}
