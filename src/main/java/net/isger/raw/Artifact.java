package net.isger.raw;

import java.lang.reflect.Type;

/**
 * 加工品
 * 
 * @author issing
 *
 */
public interface Artifact {

    /**
     * 目标对象转换
     * 
     * @param <T>
     * @param type
     * @return
     */
    public <T> T transform(Class<T> type);

    /**
     * 目标对象转换
     * 
     * @param type
     * @return
     */
    public Object transform(Type type);

}
