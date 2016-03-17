package net.isger.raw;

import java.util.List;

public interface Depot {

    public void mount(Object describe);

    public List<Raw> search(String name, Prober prober);

    public void unmount(Object describe);

}
