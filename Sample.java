import java.util.HashMap;
import java.util.List;
import java.util.Map;

import src.Jakoten;

public class Sample {
    public static void main(String[] args) {
        Map<String, Object> context = new HashMap<>();
        context.put("items", List.of("apple", "banana", "cherry"));
        context.put("showSection", true);
        context.put("hoge", "fuga");

        String template = "Here are some items:\n<%= hoge %>\n<% for item in items %>\n- <%= item %>\n<% endfor %>\n" +
                          "<% if showSection %>\nThis section is visible.<% endif %>";

        Jakoten engine = new Jakoten();
        String result = engine.render(template, context);
        System.out.println(result);
    }
}
