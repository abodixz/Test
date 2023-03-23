public class Main {
    public static void main(String[] args) {
        Element h1 = Element.create("h1").with("Text").attribute("id", "title");
        Element p = Element.create("p").with("Tohlhlhgjlhiht");
        Element div = Element.create("div").attribute("classe", "container");
        Element title = Element.create("h2").with("hei verden");
        Element text = Element.create("p").with("hei verden");
        div.addElement(title).addElement(text);
        Body body = Body.create().addElement(h1).addElement(p).addElement(div);
        System.out.println(body);

    }
}