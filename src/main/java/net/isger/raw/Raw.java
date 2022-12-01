package net.isger.raw;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * 原料
 * 
 * @author issing
 */
public interface Raw {

    /**
     * 源
     * 
     * @return
     */
    public Object getSource();

    /**
     * 输入流
     * 
     * @return
     * @throws IOException
     */
    public InputStream getInputStream() throws IOException;

    /**
     * 输出流
     * 
     * @return
     * @throws IOException
     */
    public OutputStream getOutputStream() throws IOException;

}
