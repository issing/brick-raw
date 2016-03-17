package net.isger.raw;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import net.isger.util.Reflects;

public class BaseDepot extends AbstractDepot {

    private static final Depot DEPOT;

    private ClassLoader loader;

    static {
        DEPOT = new BaseDepot();
    }

    private BaseDepot() {
        loader = Reflects.getClassLoader(this);
        super.mount(new Shelf() {
            public Object getResource(String name) {
                List<URL> result = new ArrayList<URL>();
                try {
                    Enumeration<URL> urls = loader.getResources(name);
                    while (urls.hasMoreElements()) {
                        result.add(urls.nextElement());
                    }
                } catch (IOException e) {
                }
                return result;
            }

            public String toString() {
                return loader.getClass().getName();
            }
        });
    }

    public static Depot getInstance() {
        return DEPOT;
    }

    public void mount(Object stack) {
    }

    @SuppressWarnings("unchecked")
    protected List<Raw> createRaws(Object resource) {
        List<Raw> raws = new ArrayList<Raw>();
        List<URL> urls = (List<URL>) resource;
        for (URL url : urls) {
            raws.add(createRaw(url));
        }
        return raws;
    }

    protected Raw createRaw(URL url) {
        return url == null ? null : new BaseRaw(url);
    }

    public void unmount(Object stack) {
    }

}
