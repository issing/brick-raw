package net.isger.raw;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;

import net.isger.util.Strings;

/**
 * 资源网址原料
 * 
 * @author issing
 */
public class UrlRaw implements Raw {

    /** 资源网址 */
    private URL url;

    public UrlRaw(URL url) {
        this.url = url;
    }

    /**
     * 资源网址
     */
    public URL getSource() {
        return url;
    }

    /**
     * 资源输入流
     */
    public InputStream getInputStream() throws IOException {
        return url.openConnection().getInputStream();
    }

    /**
     * 资源输出流
     */
    public OutputStream getOutputStream() throws IOException {
        return url.openConnection().getOutputStream();
    }

    public boolean equals(Object instance) {
        boolean result = instance instanceof UrlRaw;
        if (result) {
            result = url.equals(((UrlRaw) instance).url);
        }
        return result;
    }

    public String toString() {
        return Strings.empty(url);
    }

}
