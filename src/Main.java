public class Main {

    public static void main(String[] args) {

        Webly app = Webly.create("index.html");

        Element body = Element.create("body");
        Element h1 = Element.create("h1").attribute("id", "title").with("Abdykkag");


        Element p = Element.create("p").attribute("id", "text").with("hei verdenb");
        Element div = Element.create("div").attribute("id", "container");
        div.addElement(h1);
        div.addElement(p);




        p.setStyle("background-color", "blue");
        h1.setStyle("background-color", "red");


        app.add(div);
        app.start(3000);
    }
}