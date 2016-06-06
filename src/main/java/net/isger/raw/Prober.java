package net.isger.raw;

import java.util.List;

/**
 * 探测器
 * 
 * @author issing
 *
 */
public interface Prober {

    public List<Object> probe(Shelf shelf, String name);

}
