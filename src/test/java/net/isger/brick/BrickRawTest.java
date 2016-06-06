package net.isger.brick;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import net.isger.raw.Depository;
import net.isger.raw.Prober;
import net.isger.raw.Raw;
import net.isger.raw.SuffixProber;

public class BrickRawTest extends TestCase {

    public BrickRawTest(String testName) {
        super(testName);
    }

    public static Test suite() {
        return new TestSuite(BrickRawTest.class);
    }

    public void testRaw() {
        Prober prober = SuffixProber.create("json", "css");
        for (Raw raw : Depository.getRaws("brick-core", prober)) {
            System.out.println(raw.getSource());
        }
        for (Raw raw : Depository.getRaws("stylesheet", prober)) {
            System.out.println(raw.getSource());
        }
        assertTrue(true);
    }
}
