package net.isger.raw;

import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.isger.util.Callable;
import net.isger.util.Files;
import net.isger.util.Helpers;
import net.isger.util.Reflects;
import net.isger.util.Strings;
import net.isger.util.hitch.Director;

/**
 * 原料保管仓
 * 
 * @author issing
 */
public final class Depository {

    /** 仓库包属性键 */
    private static final String KEY_DEPOSITORY = "brick.raw.depository";

    /** 仓库默认路径 */
    private static final String DEPOSITORY_PATH = "net/isger/raw";

    /** 配置文件 */
    private static final String CONFIG_DEPOTSITORY = "depository.properties";

    /** 仓库类配置 */
    private static final String KEY_DEPOT_CLASSES = "depot.classes";

    /** 包装类配置 */
    private static final String KEY_WRAPPER_CLASSES = "wrapper.classes";

    private static final Logger LOG;

    private static final Depository INSTANCE;

    /** 仓库集合 */
    private Map<String, Depot> depots;

    private Map<String, Wrapper> wrappers;

    static {
        LOG = LoggerFactory.getLogger(Depository.class);
        INSTANCE = new Depository();
        final Callable<Object> depotCall = new Callable<Object>() {
            public Object call(Object... args) {
                Object instance = Reflects.newInstance((String) args[0]);
                addDepot((Depot) instance);
                return instance;
            }
        };
        final Callable<Object> wrapperCall = new Callable<Object>() {
            public Object call(Object... args) {
                Object instance = Reflects.newInstance((String) args[0]);
                addWrapper((Wrapper) instance);
                return instance;
            }
        };
        final Callable<Object> mountCall = new Callable<Object>() {
            public Object call(Object... args) {
                mount(args[0]);
                return null;
            }
        };
        new Director() {
            protected String directPath() {
                return directPath(KEY_DEPOSITORY, DEPOSITORY_PATH);
            }

            protected void directAttach(String path) {
                path = Files.toPath(path, CONFIG_DEPOTSITORY);
                for (URL url : Reflects.getResources(this, path)) {
                    Properties props = Helpers.getProperties(false, url);
                    Strings.each(getTokenizer(props, KEY_DEPOT_CLASSES), depotCall);
                    Strings.each(getTokenizer(props, KEY_WRAPPER_CLASSES), wrapperCall);
                }
            }

            protected void directInflux() {
                /* 挂载当前工作路径 */
                URL url = Reflects.getResource(INSTANCE, "/");
                if (url == null) {
                    mount("./");
                } else {
                    mount(url);
                }
                /* 挂载系统工作环境 */
                Strings.each(getSystemTokenizer("java.class.path"), mountCall);
                Strings.each(getSystemTokenizer("java.lib.path"), mountCall);
                Strings.each(getSystemTokenizer("user.dir"), mountCall);
            }
        }.direct(INSTANCE);
    }

    private Depository() {
        depots = new Hashtable<String, Depot>();
        wrappers = new Hashtable<String, Wrapper>();
    }

    /**
     * 添加仓库
     * 
     * @param depot
     */
    public static void addDepot(Depot depot) {
        String name = depot.name();
        if (LOG.isDebugEnabled()) {
            LOG.info("Achieve depot [{}]", name);
        }
        depot = INSTANCE.depots.put(name, depot);
        if (depot != null && LOG.isDebugEnabled()) {
            LOG.warn("(!) Discard depot [{}]", depot);
        }
    }

    /**
     * 所有仓库
     * 
     * @return
     */
    public static Map<String, Depot> getDepots() {
        return Collections.unmodifiableMap(INSTANCE.depots);
    }

    /**
     * 获取仓库
     * 
     * @param name
     * @return
     */
    public static Depot getDepot(String name) {
        return INSTANCE.depots.get(name);
    }

    /**
     * 添加封套
     * 
     * @param wrapper
     */
    public static void addWrapper(Wrapper wrapper) {
        String name = wrapper.name();
        if (LOG.isDebugEnabled()) {
            LOG.info("Achieve wrapper [{}]", name);
        }
        wrapper = INSTANCE.wrappers.put(name, wrapper);
        if (wrapper != null && LOG.isDebugEnabled()) {
            LOG.warn("(!) Discard wrapper [{}]", wrapper);
        }
    }

    /**
     * 所有封套
     * 
     * @return
     */
    public static Map<String, Wrapper> getWrappers() {
        return Collections.unmodifiableMap(INSTANCE.wrappers);
    }

    /**
     * 获取封套
     * 
     * @param name
     * @return
     */
    public static Wrapper getWrapper(String name) {
        return INSTANCE.wrappers.get(name);
    }

    /**
     * 挂载仓位
     * 
     * @param describe
     */
    public static void mount(Object describe) {
        for (Depot depot : INSTANCE.depots.values()) {
            depot.mount(describe);
        }
    }

    /**
     * 获取原料
     * 
     * @param name
     * @return
     */
    public static Raw getRaw(String name) {
        return getRaw(name, null);
    }

    /**
     * 获取原料
     * 
     * @param name
     * @param prober
     * @return
     */
    public static Raw getRaw(String name, Prober prober) {
        Raw raw = null;
        List<Raw> raws;
        for (Depot depot : INSTANCE.depots.values()) {
            raws = depot.search(name, prober);
            if (raws.size() > 0) {
                raw = raws.get(0);
                break;
            }
        }
        return raw;
    }

    /**
     * 获取原料
     * 
     * @param name
     * @return
     */
    public static List<Raw> getRaws(String name) {
        return getRaws(name, null);
    }

    /**
     * 获取原料
     * 
     * @param name
     * @param prober
     * @return
     */
    public static List<Raw> getRaws(String name, Prober prober) {
        List<Raw> result = new ArrayList<Raw>();
        for (Depot depot : INSTANCE.depots.values()) {
            Helpers.add(result, depot.search(name, prober));
        }
        return result;
    }

    /**
     * 原料加工
     * 
     * @param name
     * @return
     */
    public static Artifact getArtifact(String name) {
        return getArtifact(name, null);
    }

    /**
     * 原料加工
     * 
     * @param name
     * @param prober
     * @return
     */
    public static Artifact getArtifact(String name, Prober prober) {
        Artifact artifact = null;
        for (Raw raw : getRaws(name, prober)) {
            artifact = getArtifact(raw);
            if (artifact != null) {
                break;
            }
        }
        return artifact;
    }

    /**
     * 原料加工
     * 
     * @param raw
     * @return
     */
    public static Artifact getArtifact(Raw raw) {
        Artifact artifact = null;
        for (Wrapper wrapper : INSTANCE.wrappers.values()) {
            artifact = wrapper.wrap(raw);
            if (artifact != null) {
                break;
            }
        }
        return artifact;
    }

    /**
     * 原料加工
     * 
     * @param name
     * @return
     */
    public static List<Artifact> getArtifacts(String name) {
        return getArtifacts(name, null);
    }

    /**
     * 原料加工
     * 
     * @param name
     * @param prober
     * @return
     */
    public static List<Artifact> getArtifacts(String name, Prober prober) {
        Artifact artifact;
        List<Artifact> artifacts = new ArrayList<Artifact>();
        for (Raw raw : getRaws(name, prober)) {
            artifact = getArtifact(raw);
            if (artifact != null) {
                artifacts.add(artifact);
            }
        }
        return artifacts;
    }

    /**
     * 卸载仓位
     * 
     * @param describe
     */
    public static void unmount(Object describe) {
        for (Depot depot : INSTANCE.depots.values()) {
            depot.unmount(describe);
        }
    }

}
