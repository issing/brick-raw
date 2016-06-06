package net.isger.raw;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import net.isger.util.Helpers;
import net.isger.util.Strings;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 抽象仓库类
 * 
 * @author issing
 *
 */
public abstract class AbstractDepot implements Depot {

    private static final Logger LOG;

    private static final Prober NOP;

    private String name;

    private List<Shelf> shelves;

    static {
        LOG = LoggerFactory.getLogger(AbstractDepot.class);
        NOP = new Prober() {
            private final List<Object> EMPTY = Collections
                    .unmodifiableList(new ArrayList<Object>());

            public List<Object> probe(Shelf shelf, String name) {
                return EMPTY;
            }
        };
    }

    protected AbstractDepot() {
        shelves = new ArrayList<Shelf>();
    }

    /**
     * 仓库名称
     */
    public String name() {
        if (Strings.isEmpty(name)) {
            name = Helpers.getAliasName(this.getClass(), "Depot$");
        }
        return name;
    }

    /**
     * 挂载货架
     */
    public void mount(Object describe) {
        if (describe instanceof Shelf && !(shelves.contains(describe))) {
            if (LOG.isDebugEnabled()) {
                LOG.info("Achieve shelf [{}]", describe);
            }
            shelves.add((Shelf) describe);
        }
    }

    /**
     * 搜索原料
     */
    public final List<Raw> search(String name, Prober prober) {
        if (prober == null) {
            prober = NOP;
        }
        List<Raw> result = new ArrayList<Raw>();
        for (Shelf shelf : shelves) {
            Helpers.add(result, createRaws(shelf.getResource(name)));
            for (Object resource : prober.probe(shelf, name)) {
                Helpers.add(result, createRaws(resource));
            }
        }
        return result;
    }

    /**
     * 创建原料
     * 
     * @param resource
     * @return
     */
    protected abstract List<Raw> createRaws(Object resource);

    /**
     * 卸载货架
     */
    public void unmount(Object describe) {
        if (describe instanceof Shelf) {
            shelves.remove(describe);
        }
    }

}
