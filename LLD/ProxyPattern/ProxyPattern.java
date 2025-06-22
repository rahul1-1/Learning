// The common interface for all document types
interface Document {
    void display();
}

class RemoteDocument implements Document {
    private String fileName;

    public RemoteDocument(String fileName) {
        this.fileName = fileName;
        connectToRemote();
        download();
    }

    private void connectToRemote() {
        System.out.println("[RemoteProxy] Connecting to remote document server...");
    }

    private void download() {
        System.out.println("[RemoteProxy] Downloading document: " + fileName);
    }

    public void display() {
        System.out.println("[RemoteProxy] Displaying remote document: " + fileName);
    }
}

class VirtualDocumentProxy implements Document {
    private String fileName;
    private RemoteDocument realDocument;

    public VirtualDocumentProxy(String fileName) {
        this.fileName = fileName;
    }

    public void display() {
        if (realDocument == null) {
            System.out.println("[VirtualProxy] Lazy-loading remote document: " + fileName);
            realDocument = new RemoteDocument(fileName);
        }
        realDocument.display();
    }
}

class ProtectionProxy implements Document {
    private Document document;
    private String userRole;

    public ProtectionProxy(Document document, String userRole) {
        this.document = document;
        this.userRole = userRole;
    }

    public void display() {
        if ("ADMIN".equalsIgnoreCase(userRole)) {
            System.out.println("[ProtectionProxy] Access granted for role: " + userRole);
            document.display();
        } else {
            System.out.println("[ProtectionProxy] Access denied for role: " + userRole);
        }
    }
}

public class ProxyPattern {
    public static void main(String[] args) {

        System.out.println("\n--- USER trying to access ---");
        Document doc1 = new ProtectionProxy(
                new VirtualDocumentProxy("confidential.pdf"),
                "USER"
        );
        doc1.display();  // Access Denied

        System.out.println("\n--- ADMIN accessing ---");
        Document doc2 = new ProtectionProxy(
                new VirtualDocumentProxy("admin-report.pdf"),
                "ADMIN"
        );
        doc2.display();  // Lazy load, connect remotely, and display
    }
}