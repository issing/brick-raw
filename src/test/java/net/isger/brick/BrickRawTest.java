package net.isger.brick;

import java.util.List;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import net.isger.raw.Depository;
import net.isger.raw.Prober;
import net.isger.raw.Raw;
import net.isger.raw.SuffixProber;

/**
 * 原料仓测试
 * 
 * @author issing
 */
public class BrickRawTest extends TestCase {

    public BrickRawTest(String name) {
        super(name);
    }

    public static Test suite() {
        return new TestSuite(BrickRawTest.class);
    }

    public void testRaw() {
        Prober prober = SuffixProber.create("json", "css");
        List<Raw> raws = Depository.getRaws("raw", prober);
        System.out.println(raws);
        assertTrue(raws.size() >= 1);
        raws = Depository.getRaws("stylesheet", prober);
        System.out.println(raws);
        assertTrue(raws.size() >= 1);
    }
}
