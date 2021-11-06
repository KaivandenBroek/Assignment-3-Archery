package nl.hva.ict.ads;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

class EfficientyTest {
    protected Sorter<Archer> sorter = new ArcherSorter();
    protected List<Archer> fewArchers;
    protected List<Archer> manyArchers;
    protected List<Archer> testArchers;
    protected List<Archer> testArchers2;
    protected List<Archer> testArchers3;
    protected long duration;
    protected long duration2;
    protected int maxArchers = 5000000;
    protected Comparator<Archer> scoringScheme = Archer::compareByHighestTotalScoreWithLeastMissesAndLowestId;

    @BeforeEach
    void setup() {
        // create archers
        ChampionSelector championSelector = new ChampionSelector(1L);
        fewArchers = new ArrayList(championSelector.enrollArchers(23));
        manyArchers = new ArrayList(championSelector.enrollArchers(250));
    }

    @Test
    void test() {
        // create archers
        // 3 unordered lists for each test
        int amountarchers = 100;

        while (amountarchers < maxArchers) {

            ChampionSelector championSelector = new ChampionSelector(1L);
            testArchers = new ArrayList(championSelector.enrollArchers(amountarchers));
            testArchers2 = testArchers;

            // long start = System.currentTimeMillis();
            // long end = start + 20000;
            while (System.currentTimeMillis() < System.currentTimeMillis() + 20000) {
                // keep track of time
                long startTime = System.currentTimeMillis();

                sorter.selInsSort(testArchers, scoringScheme);

                long endTime = System.currentTimeMillis();
                duration = (endTime - startTime);

                System.out.println("insertion sort - amount archers: " + amountarchers
                        + " duration test in miliseconds: " + duration);
            }

            // long start2 = System.currentTimeMillis();
            // long end2 = start2 + 20000;
            // while (System.currentTimeMillis() < end2) {
            // // keep track of time
            // long startTime = System.currentTimeMillis();

            // sorter.quickSort(testArchers2, scoringScheme);

            // long endTime = System.currentTimeMillis();
            // duration2 = (endTime - startTime);

            // System.out.println(
            // "quicksort - amount archers: " + amountarchers + " duration test in
            // miliseconds: " + duration2);
            // }

            amountarchers += amountarchers * 2;

        }

        System.out.println("Test done");

        System.gc();
    }

}
