package net.isger.raw;

import java.util.List;

/**
 * 探测器
 * 
 * @author issing
 */
public interface Prober {

    /**
     * 货架原料探测
     * 
     * @param shelf
     * @param name
     * @return
     */
    public List<Object> probe(Shelf shelf, String name);

}
