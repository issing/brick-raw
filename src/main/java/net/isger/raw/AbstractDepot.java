package net.isger.raw;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.isger.util.Callable;
import net.isger.util.Helpers;

public abstract class AbstractDepot implements Depot {

    private static final Logger LOG;

    private static final Callable<List<Object>> NOP;

    private List<Shelf> shelves;

    static {
        LOG = LoggerFactory.getLogger(AbstractDepot.class);
        NOP = new Callable<List<Object>>() {
            private List<Object> EMPTY = Collections
                    .unmodifiableList(new ArrayList<Object>());

            public List<Object> call(Object... args) {
                return EMPTY;
            }
        };
    }

    protected AbstractDepot() {
        shelves = new ArrayList<Shelf>();
    }

    public void mount(Object describe) {
        if (describe instanceof Shelf && !(shelves.contains(describe))) {
            if (LOG.isDebugEnabled()) {
                LOG.info("Achieve shelf [{}]", describe);
            }
            shelves.add((Shelf) describe);
        }
    }

    public final List<Raw> search(String name, final Prober prober) {
        Callable<List<Object>> callable = prober == null ? NOP
                : new Callable<List<Object>>() {
                    public List<Object> call(Object... args) {
                        return prober.probe((Shelf) args[0], (String) args[1]);
                    }
                };
        List<Raw> result = new ArrayList<Raw>();
        for (Shelf stack : shelves) {
            result = createRaws(stack.getResource(name));
            for (Object resource : callable.call(stack, name)) {
                result = Helpers.getMerge(result, createRaws(resource));
            }
        }
        return result;
    }

    protected abstract List<Raw> createRaws(Object resource);

    public void unmount(Object describe) {
        if (describe instanceof Shelf) {
            shelves.remove(describe);
        }
    }

}
