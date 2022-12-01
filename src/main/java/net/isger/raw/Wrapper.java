package net.isger.raw;

import net.isger.util.Named;

/**
 * 包装器接口
 * 
 * @author issing
 */
public interface Wrapper extends Named {

    /**
     * 原料包装
     * 
     * @param raw
     * @return
     */
    public Artifact wrap(Raw raw);

}
