package net.isger.raw;

import java.net.URL;
import java.util.jar.JarInputStream;

import net.isger.util.Files;

public class JarShelf extends FileShelf {

    protected JarShelf(String path) {
        super(path);
    }

    public URL getResource(String name) {
        URL result = null;
        String path = getPath();
        JarInputStream jis = null;
        try {
            jis = Files.openJarIS(path);
            result = new URL("jar:file:" + path + "!/"
                    + Files.get(jis, name).getName());
        } catch (Exception e) {
        } finally {
            Files.close(jis);
        }
        return result;
    }
}
