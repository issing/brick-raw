package net.isger.raw;

import java.util.ArrayList;
import java.util.List;

/**
 * 探测器滚轮
 * 
 * @author issing
 */
public class ProberMulticaster implements Prober {

    private Prober prev;

    private Prober next;

    protected ProberMulticaster(Prober prev, Prober next) {
        this.prev = prev;
        this.next = next;
    }

    /**
     * 货架原料探测
     */
    public List<Object> probe(Shelf shelf, String name) {
        List<Object> result = new ArrayList<Object>();
        result.addAll(prev.probe(shelf, name));
        result.addAll(next.probe(shelf, name));
        return result;
    }

    /**
     * 添加探测器
     * 
     * @param prev
     * @param next
     * @return
     */
    public static Prober add(Prober prev, Prober next) {
        if (prev == null)
            return next;
        if (next == null)
            return prev;
        return new ProberMulticaster(prev, next);
    }

    /**
     * 移除探测器
     * 
     * @param prev
     * @param next
     * @return
     */
    public static Prober remove(Prober prev, Prober next) {
        if (prev == next || prev == null) {
            return null;
        } else if (prev instanceof ProberMulticaster) {
            return ((ProberMulticaster) prev).remove(next);
        } else {
            return prev;
        }
    }

    /**
     * 移除探测器
     * 
     * @param prober
     * @return
     */
    protected Prober remove(Prober prober) {
        if (prober == prev)
            return next;
        if (prober == next)
            return prev;
        Prober a = remove(prev, prober);
        Prober b = remove(next, prober);
        if (a == prev && b == next) {
            return this;
        }
        return add(a, b);
    }

}
