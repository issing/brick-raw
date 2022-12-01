package net.isger.raw;

import net.isger.util.Helpers;
import net.isger.util.Strings;

/**
 * 抽象封套类
 * 
 * @author issing
 */
public abstract class AbstractWrapper implements Wrapper {

    private String name;

    public String name() {
        if (Strings.isEmpty(name)) {
            name = Helpers.getAliasName(this.getClass(), "Wrapper$");
        }
        return name;
    }

}
