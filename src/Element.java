import java.util.*;

public class Element {
    private final String tag;
    private String text;
    private final Map<String, String> attributes;
    private final Map<String, String> style;
    private final List<Element> children;

    public Element(String tag, String text) {
        this.tag = tag;
        this.text = text;
        this.attributes = new HashMap<>();
        this.style = new HashMap<>();
        this.children = new ArrayList<>();
    }

    public void addElement(Element child) {
        children.add(child);
    }

    public String getTag() {
        return tag;
    }

    public void setStyle(String property, String value) {
        style.put(property, value);
    }

    public Map<String, String> getStyle() {
        return style;
    }

    public void addAttribute(String name, String value) {
        attributes.put(name, value);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("<").append(tag);
        for (Map.Entry<String, String> attribute : attributes.entrySet()) {
            sb.append(" ").append(attribute.getKey()).append("=\"").append(attribute.getValue()).append("\"");
        }

        // Generate the "style" attribute if the style map is not empty
        if (!style.isEmpty()) {
            sb.append(" style=\"");
            for (Map.Entry<String, String> property : style.entrySet()) {
                sb.append(property.getKey()).append(": ").append(property.getValue()).append("; ");
            }
            sb.append("\"");
        }

        sb.append(">");
        for (Element child : children) {
            sb.append("\n" + "  ").append(child.toString());
        }
        sb.append(text).append(" "+ "\n"+"</").append(tag).append(">");
        return sb.toString();
    }

    public Map<String, String> getAttributes() {
        return attributes;
    }

    public String getAttribute(String name) {
        return attributes.get(name);
    }

    public Element attribute(String name, String value) {
        attributes.put(name, value);
        return this;
    }


    public Element style(String property, String value) {
        style.put(property, value);
        return this;
    }

    public static Element create(String tag) {
        return new Element(tag, "");
    }
    public Element with(String text) {
        this.text = text;
        return this;
    }

}
