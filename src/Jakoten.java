package src;

import java.util.*;

import src.lib.Parser;
import src.lib.Renderer;
import src.lib.nodes.Node;

public class Jakoten {
    public String render(String template, Map<String, Object> context){
        List<Node> nodes = Parser.parse(template);
        String result = Renderer.render(nodes,context);

        return result;
    }
}
