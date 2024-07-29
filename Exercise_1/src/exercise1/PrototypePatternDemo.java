package exercise1;


interface Prototype {
    Prototype clone();
}

// Concrete Prototype
class Document implements Prototype {
    private String content;

    public Document(String content) {
        this.content = content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getContent() {
        return content;
    }

    @Override
    public Prototype clone() {
        return new Document(this.content);
    }
}

// Main class
public class PrototypePatternDemo {
    public static void main(String[] args) {
        Document original = new Document("Original Content");
        Document clone1 = (Document) original.clone();
        Document clone2 = (Document) original.clone();
        
        clone1.setContent("Cloned Content 1");
        clone2.setContent("Cloned Content 2");
        
        System.out.println("Original: " + original.getContent());
        System.out.println("Clone 1: " + clone1.getContent());
        System.out.println("Clone 2: " + clone2.getContent());
    }
}
