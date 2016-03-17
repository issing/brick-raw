package net.isger.raw;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;

public class BaseRaw implements Raw {

    private URL url;

    public BaseRaw(URL url) {
        this.url = url;
    }

    public Object getSource() {
        return url;
    }

    public InputStream getInputStream() throws IOException {
        return url.openConnection().getInputStream();
    }

    public OutputStream getOutputStream() throws IOException {
        return url.openConnection().getOutputStream();
    }

    public boolean equals(Object instance) {
        boolean result = instance instanceof BaseRaw;
        if (result) {
            result = url.equals(((BaseRaw) instance).url);
        }
        return result;
    }

}
