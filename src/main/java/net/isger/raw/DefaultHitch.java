package net.isger.raw;

/**
 * 默认仓库挂接
 * 
 * @author issing
 */
public class DefaultHitch {

    private DefaultHitch() {
    }

    /**
     * 仓库挂接
     * 
     * @param source
     */
    public static void hitch(Object source) {
        if (!(source instanceof Depository)) {
            return;
        }
        Depository.addDepot(ClassLoaderDepot.getInstance());
        Depository.addDepot(new FileDepot());
        Depository.addDepot(new JarDepot());
    }

}
