package net.isger.raw;

import java.util.List;

import net.isger.util.Named;

/**
 * 仓库接口
 * 
 * @author issing
 *
 */
public interface Depot extends Named {

    /**
     * 挂载
     * 
     * @param describe
     */
    public void mount(Object describe);

    /**
     * 搜索
     * 
     * @param name
     * @param prober
     * @return
     */
    public List<Raw> search(String name, Prober prober);

    /**
     * 卸载
     * 
     * @param describe
     */
    public void unmount(Object describe);

}
