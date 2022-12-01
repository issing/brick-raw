package net.isger.raw;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * 文件仓架
 * 
 * @author issing
 */
public class FileShelf implements Shelf {

    /** 文件路径 */
    private String path;

    protected FileShelf(String path) {
        this.path = path;
    }

    /**
     * 获取路径
     * 
     * @return
     */
    public String getPath() {
        return path;
    }

    /**
     * 文件资源路径
     */
    public URL getResource(String name) {
        URL result = null;
        File file = new File(path, name);
        try {
            if (file.isFile() && file.exists()) {
                result = new URL("file:" + file.getAbsolutePath());
            }
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
