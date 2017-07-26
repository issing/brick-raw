package net.isger.raw;

import java.lang.reflect.Type;

/**
 * 加工品
 * 
 * @author issing
 *
 */
public interface Artifact {

    public <T> T transform(Class<T> type);

    public Object transform(Type type);

}
