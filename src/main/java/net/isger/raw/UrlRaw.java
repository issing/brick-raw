package net.isger.raw;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;

public class UrlRaw implements Raw {

    private URL url;

    public UrlRaw(URL url) {
        this.url = url;
    }

    public URL getSource() {
        return url;
    }

    public InputStream getInputStream() throws IOException {
        return url.openConnection().getInputStream();
    }

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

}
