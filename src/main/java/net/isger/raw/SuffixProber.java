package net.isger.raw;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import net.isger.util.Files;

public class SuffixProber implements Prober {

    private List<String> suffixes;

    public SuffixProber() {
        suffixes = new Vector<String>();
    }

    public static SuffixProber create(String... suffixes) {
        SuffixProber prober = new SuffixProber();
        for (String suffix : suffixes) {
            prober.addSuffix(suffix);
        }
        return prober;
    }

    public void addSuffix(String suffix) {
        String name = Files.getCanonical(suffix);
        if (!(name == null || suffix.contains(File.separator) || suffixes
                .contains(suffix))) {
            suffixes.add(suffix);
        }
    }

    public List<Object> probe(Shelf stack, String name) {
        List<Object> result = new ArrayList<Object>();
        for (String suffix : suffixes) {
            result.add(stack.getResource(name + "." + suffix));
        }
        return result;
    }

}
