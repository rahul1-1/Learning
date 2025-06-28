import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

abstract  class DocumentElement {
    public abstract String render();
}

class TextElement extends DocumentElement {
    private String text;
    public TextElement(String text){
        this.text= text;
    }

    @Override
    public String render(){
        return text;
    }
}

class ImageElement  extends DocumentElement {
    private String path;

    public ImageElement(String path){
        this.path = path;
    }

    @Override()
    public String render(){
        return  "<img src=\""+path  +"\" />";
    }
}

class Document {
    private List<DocumentElement> elements = new ArrayList<>();

    public void addElement(DocumentElement element){
        elements.add(element);
    }

    public String render(){
        StringBuilder res = new StringBuilder();
        for(DocumentElement ele:elements){
            res.append(ele.render()).append("\n");
        }
        return res.toString();
    }

    public void removeElement(int index) {
        if (index >= 0 && index < elements.size()) {
            elements.remove(index);
        } else {
            System.out.println("Invalid index");
        }
    }
}

interface StorageService {
    void save(Document doc);
}

class FileStorageService implements StorageService {
    @Override
    public void save(Document doc){
         try (FileWriter writer = new FileWriter("document.txt", true)) { 
            writer.write(doc.render());
            writer.write(System.lineSeparator()); 
            System.out.println("Document saved to document.txt\n");
        } catch (IOException e) {
            System.out.println("Error saving document: " + e.getMessage());
        }
    }
}

class MongoDBStorageService implements StorageService {
    @Override
    public void save(Document doc) {
        System.out.println("Document saved to MongoDB:\n" );
    }
}

// Manages multiple storage services and saves document to all of them
class StorageManager {
    private List<StorageService> storageServices = new ArrayList<>();

    public void addStorageService(StorageService storage) {
        storageServices.add(storage);
    }

    public void saveDocument(Document doc) {
        for (StorageService storage : storageServices) {
            storage.save(doc);
        }
    }
}
class DocumentEditor {
     private Document doc;
     private StorageManager storageManager;

    public DocumentEditor(Document doc) {
        this.doc = doc;
        this.storageManager = new StorageManager();
    }

     public void addStorage(StorageService storage) {
        storageManager.addStorageService(storage);
    }

    public void addText(String text) {
        doc.addElement(new TextElement(text));
    }

    public void addImage(String imagePath) {
        doc.addElement(new ImageElement(imagePath));
    }

    public void removeElement(int index) {
        doc.removeElement(index);
    }

    public String renderDocument() {
        return doc.render();
    }

    public void saveDocument() {
        storageManager.saveDocument(doc);
    }

    public void addCompositeElement(CompositeElement composite) {
    doc.addElement(composite);
}
}

class CompositeElement extends DocumentElement {
    private List<DocumentElement> children = new ArrayList<>();

    public void addChild(DocumentElement child) {
        children.add(child);
    }

    @Override
    public String render() {
        StringBuilder sb = new StringBuilder();
        for (DocumentElement child : children) {
            sb.append(child.render()).append("\n");
        }
        return sb.toString();
    }
}

public class DocumentEditorApp {
    public static void main(String[] args) {
        Document doc = new Document();
        DocumentEditor editor = new DocumentEditor(doc);
        
        editor.addStorage(new FileStorageService());
        editor.addStorage(new MongoDBStorageService());

        editor.addText("Hello World");
        editor.addText("Document Editor Example");
        editor.addImage("image.png");

        System.out.println("Document Content:\n" + editor.renderDocument());

        editor.removeElement(1);

        System.out.println("After Removal:\n" + editor.renderDocument());

        editor.saveDocument();

        System.out.println("---------------------------    NEW DOCUMENT   -------------------------------");

        Document doc1 = new Document();
        DocumentEditor editor1 = new DocumentEditor(doc1);

        editor1.addStorage(new FileStorageService());

        editor1.saveDocument();

        CompositeElement table = new CompositeElement();
        CompositeElement row1 = new CompositeElement();
        row1.addChild(new TextElement("Cell 1"));
        row1.addChild(new TextElement("Cell 2"));
        table.addChild(row1);
        editor1.addCompositeElement(table);
        
        editor1.addText("Hello World New Document");
        editor1.addText("Document Editor Example");
        editor1.addImage("New_Document_Image.png");

        table.render();
        System.out.println("Document Content:\n" + editor1.renderDocument());

        editor1.removeElement(1);

        System.out.println("After Removal:\n" + editor1.renderDocument());


    }
}