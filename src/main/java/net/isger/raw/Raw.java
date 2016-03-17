package net.isger.raw;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * 原料
 * 
 * @author issing
 * 
 */
public interface Raw {

    public Object getSource();

    public InputStream getInputStream() throws IOException;

    public OutputStream getOutputStream() throws IOException;

}
