import java.util.ArrayList;
import java.util.List;

interface IFileSystemItem {

    void ls(int indent);

    void openAll(int indent);

    int getSize();

    IFileSystemItem cd(String name);

    String getName();

    boolean isFolder();
}

class File implements IFileSystemItem {
    private String name;
    private int size;

    public File(String name, int sz) {
        this.name = name;
        this.size = sz;
    }

    @Override
    public void ls(int indent) {
        String indentSpaces = " ".repeat(indent);
        System.out.println(indentSpaces + name);
    }

    @Override
    public void openAll(int indent) {
        String indentSpaces = " ".repeat(indent);
        System.out.println(indentSpaces + name);
    }

    @Override
    public int getSize() {
        return size;
    }

    @Override
    public IFileSystemItem cd(String name) {
        return null; // Files do not have children
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public boolean isFolder() {
        return false; // This is a file, not a folder
    }
}

class Folder implements IFileSystemItem {
    String name;
    List<IFileSystemItem> items;

    public Folder(String name) {
        this.name = name;
        this.items = new ArrayList<>();
    }

    public void add(IFileSystemItem item) {
        items.add(item);
    }

    @Override
    public void ls(int indent) {
        String indentSpaces = " ".repeat(indent);

        for (IFileSystemItem item : items) {
            if (item.isFolder()) {
                System.out.println(indentSpaces + "+" + item.getName());
            } else {
                System.out.println(indentSpaces + item.getName());
            }
        }
    }

    @Override
    public void openAll(int indent) {
        String indentSpaces = " ".repeat(indent);
        System.out.println(indentSpaces + "+ " + name);
        for (IFileSystemItem item : items) {
            item.openAll(indent + 4);
        }
    }

    @Override
    public int getSize() {
        int totalSize = 0;
        for (IFileSystemItem item : items) {
            totalSize += item.getSize();
        }
        return totalSize;
    }

    @Override
    public IFileSystemItem cd(String name) {
        for (IFileSystemItem item : items) {
            if (item.isFolder() && item.getName().equals(name)) {
                return item;
            }
        }
        return null;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public boolean isFolder() {
        return true;
    }
}

public class FileSystem {
    public static void main(String[] args) {

        Folder root = new Folder("root");
        root.add(new File("file1.txt", 1));
        root.add(new File("file2.txt", 1));

        Folder docs = new Folder("docs");
        docs.add(new File("resume.pdf", 1));
        docs.add(new File("notes.txt", 1));
        root.add(docs);

        Folder images = new Folder("images");
        images.add(new File("photo.jpg", 1));
        root.add(images);

        System.out.println("================= Listing files in root ===================");
        root.ls(0);

        System.out.println("================= Listing files in docs ===================");
        docs.ls(0);

        System.out.println("================= Opening all files in root ===================");
        root.openAll(0);

        System.out.println("================= cd docs ===================");
        IFileSystemItem cwd = root.cd("docs");
        if (cwd != null) {
            cwd.ls(0);
        } else {
            System.out.println("\nCould not cd into docs\n");
        }

        System.out.println("================= root folder ===================");
        System.out.println(root.getSize());
    }
}