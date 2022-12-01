package net.isger.raw;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import net.isger.util.Helpers;
import net.isger.util.Reflects;

/**
 * 类加载器仓库
 * 
 * @author issing
 */
public final class ClassLoaderDepot extends AbstractDepot implements Shelf {

    /** 仓库实例 */
    private static final Depot INSTANCE;

    private final ClassLoader loader;

    static {
        INSTANCE = new ClassLoaderDepot();
    }

    private ClassLoaderDepot() {
        loader = Reflects.getClassLoader(this);
        super.mount(this);
    }

    /**
     * 获取仓库
     * 
     * @return
     */
    public static Depot getInstance() {
        return INSTANCE;
    }

    /**
     * 获取资源
     */
    public Object getResource(String name) {
        List<URL> result = new ArrayList<URL>();
        try {
            Helpers.add(result, loader.getResources(name));
        } catch (IOException e) {
        }
        return result;
    }

    /**
     * 空挂载
     */
    public void mount(Object describe) {
    }

    /**
     * 创建原料
     */
    @SuppressWarnings("unchecked")
    protected List<Raw> createRaws(Object resource) {
        List<Raw> raws = new ArrayList<Raw>();
        for (URL url : (List<URL>) resource) {
            Helpers.add(raws, createRaw(url));
        }
        return raws;
    }

    /**
     * 创建
     * 
     * @param url
     * @return
     */
    protected Raw createRaw(URL url) {
        return url == null ? null : new UrlRaw(url);
    }

    /**
     * 空卸载
     */
    public void unmount(Object describe) {
    }

    public String toString() {
        return loader.getClass().getName();
    }

}
