import java.util.HashMap;
import java.util.Map;

public class Element {
    private String tag;
    private String content;
    private Map<String, String> attributes;

    private Element(String tag) {
        this.tag = tag;
        this.attributes = new HashMap<>();
    }

    public static Element create(String tag) {
        return new Element(tag);
    }

    public Element with(String content) {
        this.content = content;
        return this;
    }

    public Element attribute(String key, String value) {
        this.attributes.put(key, value);
        return this;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("<").append(tag);
        for (Map.Entry<String, String> entry : attributes.entrySet()) {
            sb.append(" ").append(entry.getKey()).append("=\"").append(entry.getValue()).append("\"");
        }
        if (content != null) {
            sb.append(">").append(content).append("</").append(tag).append(">");
        } else {
            sb.append("/>");
        }
        sb.append("\n"); // legger til linjeskift
        return sb.toString();
    }

    public Element addElement(Element element) {
        this.content += element.toString();
        return this;
    }



}


