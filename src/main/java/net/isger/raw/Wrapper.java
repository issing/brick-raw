package net.isger.raw;

import net.isger.util.Named;

/**
 * 封套接口
 * 
 * @author issing
 *
 */
public interface Wrapper extends Named {

    public Artifact wrap(Raw raw);

}
