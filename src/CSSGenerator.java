import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

public class CSSGenerator {
    private CSSGenerator() {
    }

    static public CSSGenerator CreateCSSGenerator() {
        return new CSSGenerator();
    }

    public static void cssWriter(Body body) {
        // Opprett en ArrayList for å lagre CSS-reglene
        ArrayList<String> cssRules = new ArrayList<>();

        // Gå gjennom hvert element i body og legg til CSS-regler
        for (Element element : body.getElements()) {
            // Opprett en CSS-regel for dette elementet
            String selector = "";

            // Legg til ID i selector hvis det finnes
            String id = element.getAttribute("id");
            if (id != null) {
                selector += "#" + id;
            }

            // Legg til klasse i selector hvis det finnes
            String klass = element.getAttribute("class");
            if (klass != null) {
                if (selector.isEmpty()) {
                    selector += "." + klass;
                } else {
                    selector += "." + klass.replaceAll("\\s+", ".");
                }
            }

            // Hvis det ikke finnes ID eller klasse, legg til tag name i selector
            if (selector.isEmpty()) {
                selector += element.getTag();
            }

            String rule = "{\n";

            // Legg til alle stilreglene til regelen
            for (Map.Entry<String, String> styleRule : element.getStyle().entrySet()) {
                rule += "  " + styleRule.getKey() + ": " + styleRule.getValue() + ";\n";
            }

            rule += "}";

            cssRules.add(selector + " " + rule);
        }

        // Skriv CSS-reglene til fil
        try {
            FileWriter writer = new FileWriter("style.css");
            for (String cssRule : cssRules) {
                writer.write(cssRule + "\n");
            }
            writer.close();
            System.out.println("CSS generated and stored in style.css");
        } catch (IOException e) {
            System.out.println("Error writing CSS to file: " + e.getMessage());
        }
    }
}