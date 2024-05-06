package src.lib;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import src.lib.nodes.ExpressionNode;
import src.lib.nodes.ForLoopNode;
import src.lib.nodes.IfNode;
import src.lib.nodes.Node;
import src.lib.nodes.TextNode;

public class Parser {
    private static final Pattern TAG_PATTERN = Pattern.compile("(<%=\\s*(.*?)\\s*%>)|(<%\\s*for\\s+(\\w+)\\s+in\\s+(\\w+)\\s*%>\\s*)|(<%\\s*if\\s+(.*?)\\s*%>\\s*)|(<%\\s*end(for|if)\\s*%>\\s*)", Pattern.DOTALL);
    
    public static List<Node> parse(String template) {
        List<Node> nodes = new ArrayList<>();
        Matcher matcher = TAG_PATTERN.matcher(template);
        int lastEnd = 0;
        Stack<List<Node>> stack = new Stack<>();
        Stack<String> forVariables = new Stack<>();
        Stack<String> forCollections = new Stack<>();
        Stack<String> ifConditions = new Stack<>();
        stack.push(nodes);

        while (matcher.find()) {
            if (matcher.start() > lastEnd) {
                stack.peek().add(new TextNode(template.substring(lastEnd, matcher.start())));
            }

            if (matcher.group(1) != null) { // Expression
                stack.peek().add(new ExpressionNode(matcher.group(2)));
            } else if (matcher.group(3) != null) { // For loop
                String variable = matcher.group(4);
                String collection = matcher.group(5);
                forVariables.push(variable);
                forCollections.push(collection);
                List<Node> loopBody = new ArrayList<>();
                stack.push(loopBody);
            } else if (matcher.group(6) != null) { // If condition
                String condition = matcher.group(7);
                ifConditions.push(condition);
                List<Node> ifBody = new ArrayList<>();
                stack.push(ifBody);
            } else if (matcher.group(8) != null) { // End tag
                List<Node> finished = stack.pop();
                if (!stack.isEmpty()) {
                    if (matcher.group().contains("endfor")) {
                        String variable = forVariables.pop();
                        String collection = forCollections.pop();
                        stack.peek().add(new ForLoopNode(variable, collection, finished));
                    } else if (matcher.group().contains("endif")) {
                        String condition = ifConditions.pop();
                        stack.peek().add(new IfNode(condition, finished));
                    }
                }
            }

            lastEnd = matcher.end();
        }

        if (lastEnd < template.length()) {
            stack.peek().add(new TextNode(template.substring(lastEnd)));
        }
        System.out.println(nodes);
        return nodes;
    }
}
