package net.isger.raw;

import java.io.File;
import java.io.FileFilter;

import net.isger.util.Files;

/**
 * 档案仓库
 * 
 * @author issing
 */
public class JarDepot extends FileDepot {

    private static final FileFilter FILTER;

    static {
        FILTER = new FileFilter() {
            public boolean accept(File file) {
                return file.isFile() && Files.isJar(file.getAbsolutePath());
            }
        };
    }

    protected JarDepot() {
    }

    /**
     * 挂载档案
     */
    protected boolean isMound(File file) {
        return super.isMound(file) || FILTER.accept(file);
    }

    /**
     * 挂载档案
     */
    protected void mount(File file) {
        if (file.isDirectory()) {
            for (File path : file.listFiles(FILTER)) {
                super.mount(path);
            }
        } else {
            super.mount(file);
        }
    }

    /**
     * 创建档案货架
     */
    protected FileShelf createShelf(String path) {
        return new JarShelf(path);
    }

}
