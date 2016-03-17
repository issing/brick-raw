package net.isger.raw;

import java.io.File;
import java.io.FileFilter;

import net.isger.util.Files;

public class JarDepot extends FileDepot {

    private FileFilter FILTER;

    protected JarDepot() {
        FILTER = new FileFilter() {
            public boolean accept(File file) {
                return file.isFile() && Files.isJar(file.getAbsolutePath());
            }
        };
    }

    protected boolean isMound(File file) {
        return super.isMound(file) || FILTER.accept(file);
    }

    protected void addPath(File file) {
        if (file.isDirectory()) {
            for (File path : file.listFiles(FILTER)) {
                super.addPath(path);
            }
        } else {
            super.addPath(file);
        }
    }

    protected Shelf createShelf(String path) {
        return new JarShelf(path);
    }

}
