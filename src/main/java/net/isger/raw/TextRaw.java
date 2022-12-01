package net.isger.raw;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import net.isger.util.Asserts;

/**
 * 文本原料
 * 
 * @author issing
 */
public class TextRaw implements Raw {

    /** 文本内容 */
    private String content;

    public TextRaw(String content) {
        this.content = content;
    }

    /**
     * 文本源
     */
    public Object getSource() {
        return content;
    }

    /**
     * 文本输入流
     */
    public InputStream getInputStream() throws IOException {
        return new ByteArrayInputStream(content.getBytes());
    }

    /**
     * 文本输出流
     */
    public OutputStream getOutputStream() throws IOException {
        throw Asserts.state("Output stream is not supported");
    }

    public boolean equals(Object instance) {
        boolean result = instance instanceof TextRaw;
        if (result) {
            result = content.equals(((TextRaw) instance).content);
        }
        return result;
    }

    public String toString() {
        return content;
    }

}
