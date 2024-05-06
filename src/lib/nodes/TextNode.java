package src.lib.nodes;

import java.util.Map;

public class TextNode extends Node {
    private String text;

    public TextNode(String text) {
        this.text = text;
    }

    @Override
    public
    String render(Map<String, Object> context) {
        return text;
    }
}