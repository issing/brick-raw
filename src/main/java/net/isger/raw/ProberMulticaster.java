package net.isger.raw;

import java.util.ArrayList;
import java.util.List;

public class ProberMulticaster implements Prober {

    private Prober a;

    private Prober b;

    protected ProberMulticaster(Prober a, Prober b) {
        this.a = a;
        this.b = b;
    }

    public List<Object> probe(Shelf shelf, String name) {
        List<Object> result = new ArrayList<Object>();
        result.addAll(a.probe(shelf, name));
        result.addAll(b.probe(shelf, name));
        return result;
    }

    public static Prober add(Prober a, Prober b) {
        if (a == null)
            return b;
        if (b == null)
            return a;
        return new ProberMulticaster(a, b);
    }

    public static Prober remove(Prober a, Prober b) {
        if (a == b || a == null) {
            return null;
        } else if (a instanceof ProberMulticaster) {
            return ((ProberMulticaster) a).remove(b);
        } else {
            return a;
        }
    }

    protected Prober remove(Prober prober) {
        if (prober == a)
            return b;
        if (prober == b)
            return a;
        Prober a2 = remove(a, prober);
        Prober b2 = remove(b, prober);
        if (a2 == a && b2 == b) {
            return this;
        }
        return add(a2, b2);
    }

}
