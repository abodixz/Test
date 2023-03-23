import java.util.ArrayList;
import java.util.List;

public class Body {
    private List<Element> elements;
    private int indentLevel; // holder styr på innrykket

    private Body() {
        this.elements = new ArrayList<>();
        this.indentLevel = 0;
    }

    public static Body create() {
        return new Body();
    }

    public Body addElement(Element element) {
        this.elements.add(element);
        return this;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("<body>\n"); // legger til linjeskift og starter kroppen
        indentLevel++;

        for (Element element : elements) {
            sb.append(getIndentation());
            sb.append(element.toString());
        }

        indentLevel--;
        sb.append(getIndentation());
        sb.append("</body>"); // avslutter kroppen
        sb.append("\n"); // legger til linjeskift
        return sb.toString();
    }

    private String getIndentation() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < indentLevel; i++) {
            sb.append("    "); // bruker 4 mellomrom per innrykk
        }
        return sb.toString();
    }
}
