package net.isger.raw;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * 文件仓库
 * 
 * @author issing
 */
public class FileDepot extends AbstractDepot {

    /**
     * 挂载文件仓位
     */
    public final void mount(Object describe) {
        File file;
        get: {
            if (describe instanceof File) {
                file = (File) describe;
                break get;
            } else if (describe instanceof URL) {
                describe = decode(((URL) describe).getFile());
            }
            file = new File(describe.toString());
        }
        if (file.exists() && isMound(file)) {
            mount(file);
        }
    }

    /**
     * 挂载文件仓位
     * 
     * @param file
     */
    protected void mount(File file) {
        String path;
        try {
            path = file.getCanonicalPath();
        } catch (IOException e) {
            return;
        }
        super.mount(createShelf(path));
    }

    /**
     * 创建文件仓架
     * 
     * @param path
     * @return
     */
    protected FileShelf createShelf(String path) {
        return new FileShelf(path);
    }

    /**
     * 解码文件路径
     * 
     * @param path
     * @return
     */
    protected String decode(String path) {
        return path.replaceAll("(%20)", " ");
    }

    /**
     * 挂载文件检测
     * 
     * @param file
     * @return
     */
    protected boolean isMound(File file) {
        return file.isDirectory();
    }

    /**
     * 创建文件原料
     */
    protected List<Raw> createRaws(Object resource) {
        List<Raw> result = new ArrayList<Raw>();
        URL url = (URL) resource;
        if (url != null) {
            result.add(new UrlRaw(url));
        }
        return result;
    }
}
