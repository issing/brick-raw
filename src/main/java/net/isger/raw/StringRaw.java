package net.isger.raw;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class StringRaw implements Raw {

    private String content;

    public StringRaw(String content) {
        this.content = content;
    }

    public Object getSource() {
        return content;
    }

    public InputStream getInputStream() throws IOException {
        return new ByteArrayInputStream(content.getBytes());
    }

    public OutputStream getOutputStream() throws IOException {
        throw new IllegalStateException("Output stream is not supported");
    }

    public boolean equals(Object instance) {
        boolean result = instance instanceof StringRaw;
        if (result) {
            result = content.equals(((StringRaw) instance).content);
        }
        return result;
    }

}
