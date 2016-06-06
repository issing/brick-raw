package net.isger.raw;

/**
 * 加工品
 * 
 * @author issing
 *
 */
public interface Artifact {

    public <T> T transform(Class<T> type);

}
