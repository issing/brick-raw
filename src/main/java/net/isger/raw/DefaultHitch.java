package net.isger.raw;

public class DefaultHitch {

    public static void hitch(Object source) {
        if (!(source instanceof Depository)) {
            return;
        }
        Depository.addDepot(ClassLoaderDepot.getInstance());
        Depository.addDepot(new FileDepot());
        Depository.addDepot(new JarDepot());
    }

}
