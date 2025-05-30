#include <iostream>
#include <fstream>
#include <vector>
#include <string>

using namespace std;

// ---------- Document Elements ----------

class DocumentElement {
public:
    virtual string render() const = 0;
    virtual ~DocumentElement() {}
};

class TextElement : public DocumentElement {
private:
    string text;

public:
    TextElement(const string& text) : text(text) {}

    string render() const override {
        return text;
    }
};

class ImageElement : public DocumentElement {
private:
    string imagePath;

public:
    ImageElement(const string& imagePath) : imagePath(imagePath) {}

    string render() const override {
        return "<img src=\"" + imagePath + "\" />";
    }
};

// ---------- Document ----------

class Document {
private:
    vector<DocumentElement*> elements;

public:
    ~Document() {
        for (auto e : elements) {
            delete e;
        }
    }

    void addElement(DocumentElement* element) {
        elements.push_back(element);
    }

    void removeElement(int index) {
        if (index >= 0 && index < elements.size()) {
            delete elements[index];                 // Free memory
            elements.erase(elements.begin() + index); // Remove from vector
        } else {
            cout << "Invalid index" << endl;
        }
    }

    string render() const {
        string result;
        for (auto element : elements) {
            result += element->render() + "\n";
        }
        return result;
    }
};

// ---------- Storage Services ----------

class StorageService {
public:
    virtual void save(const Document& doc) const = 0;
    virtual ~StorageService() {}
};

class FileStorageService : public StorageService {
public:
    void save(const Document& doc) const override {
        ofstream file("document.txt",ios::app); // Open file in append mode 
        if (file.is_open()) {
            file << doc.render();
            file.close();
            cout << "Document saved to document.txt" << endl;
        } else {
            cout << "Error opening file for writing." << endl;
        }
    }
};

class MongoDBStorageService : public StorageService {
public:
    void save(const Document& doc) const override {
        cout << "Document saved to MongoDB" << endl;
    }
};

// ---------- Storage Manager ----------

class StorageManager {
private:
    vector<StorageService*> storageServices;

public:
    ~StorageManager() {
        for (auto s : storageServices) {
            delete s;
        }
    }

    void addStorageService(StorageService* storage) {
        storageServices.push_back(storage);
    }

    void saveDocument(const Document& doc) const {
        for (auto storage : storageServices) {
            storage->save(doc);
        }
    }
};

// ---------- Document Editor ----------

class DocumentEditor {
private:
    Document* doc;
    StorageManager* storageManager;
   
public:
     DocumentEditor(Document* doc) : doc(doc), storageManager(new StorageManager()) {}

    void addStorage(StorageService* storage) {
            storageManager->addStorageService(storage);
   }

    void addText(const string& text) {
        doc->addElement(new TextElement(text));
    }

    void addImage(const string& imagePath) {
        doc->addElement(new ImageElement(imagePath));
    }

    void removeElement(int index) {
        doc->removeElement(index);
    }

    string renderDocument() const {
        return doc->render();
    }

    void saveDocument() const {
        storageManager->saveDocument(*doc);
    }
   
};

// ---------- Main ----------

int main() {
    Document* doc = new Document();  
    DocumentEditor* editor = new DocumentEditor(doc);
    
    editor->addStorage(new FileStorageService());
    editor->addStorage(new MongoDBStorageService());
    editor->addText("Hello World ");
    editor->addText("Document Editor Example");
    editor->addImage("image.png");

    cout << "Document Content:\n" << editor->renderDocument() << endl;

    editor->removeElement(1); // Remove "Document Editor Example"

    cout << "\nAfter Removal:\n" << editor->renderDocument() << endl;

    editor->saveDocument();


    cout<<" ------------------------------------------------------------- "<<endl;


     Document* doc1 = new Document();
    
  
    DocumentEditor* editor1 = new DocumentEditor(doc1);
    
    editor1->addStorage(new FileStorageService());
    // editor1->addStorage(new MongoDBStorageService());
    editor1->addText("Hello World New Document");
    editor1->addText("Document Editor Example");
    editor1->addImage("New_Document_Image.png");

    cout << "Document Content:\n" << editor1->renderDocument() << endl;

    editor1->removeElement(1); // Remove "Document Editor Example"

    cout << "\nAfter Removal:\n" << editor1->renderDocument() << endl;

    editor1->saveDocument();

    delete editor;
    delete doc;
    delete editor1;
    delete doc1;

    return 0;
}
