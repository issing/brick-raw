package net.isger.raw;

import java.util.List;

import net.isger.util.Named;

/**
 * 仓库接口
 * 
 * @author issing
 */
public interface Depot extends Named {

    /**
     * 挂载仓位
     * 
     * @param describe
     */
    public void mount(Object describe);

    /**
     * 搜索原料
     * 
     * @param name
     * @param prober
     * @return
     */
    public List<Raw> search(String name, Prober prober);

    /**
     * 卸载仓位
     * 
     * @param describe
     */
    public void unmount(Object describe);

}
