package net.isger.raw;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import net.isger.util.Reflects;

class BaseShelf implements Shelf {

    private ClassLoader loader;

    BaseShelf() {
        loader = Reflects.getClassLoader(this);
    }

    public List<URL> getResource(String name) {
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

}
