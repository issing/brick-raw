package net.isger.raw;

/**
 * 加工品
 * 
 * @author issing
 *
 */
public interface Artifact {

    public Raw getRaw();

    public Object use(String id, Object... args);

}
