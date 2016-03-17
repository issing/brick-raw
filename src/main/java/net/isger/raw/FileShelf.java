package net.isger.raw;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

public class FileShelf implements Shelf {

    private String path;

    protected FileShelf(String path) {
        this.path = path;
    }

    public String getPath() {
        return path;
    }

    public URL getResource(String name) {
        URL result = null;
        File file = new File(path, name);
        try {
            if (file.isFile() && file.exists())
                result = new URL("file:" + file.getAbsolutePath());
        } catch (MalformedURLException e) {
        }
        return result;
    }

    public boolean equals(Object instance) {
        boolean result = instance instanceof FileShelf;
        if (result) {
            result = path.equalsIgnoreCase(((FileShelf) instance).path);
        }
        return result;
    }

    public String toString() {
        return this.path;
    }

}
