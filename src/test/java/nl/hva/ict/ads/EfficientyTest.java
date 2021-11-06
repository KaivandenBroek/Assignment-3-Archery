package nl.hva.ict.ads;

import org.apache.commons.lang3.time.StopWatch;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Timer;

class EfficientyTest {
    protected Sorter<Archer> sorter = new ArcherSorter();
    protected List<Archer> fewArchers;
    protected List<Archer> manyArchers;
    protected List<Archer> testArchers;
    protected List<Archer> testArchers2;
    protected List<Archer> testArchers3;
    protected long duration;
    protected long duration2;
    Timer timer = new Timer();
    StopWatch stopwatch = new StopWatch();
    protected int maxArchers = 5000000;
    protected int maxTimeInMilis = 20000;
    protected Comparator<Archer> scoringScheme = Archer::compareByHighestTotalScoreWithLeastMissesAndLowestId;

    @BeforeEach
    void setup() {

    }

    @Test
    void test() {
        // create archers
        int amountarchers = 100;

        while (amountarchers < maxArchers) {

            ChampionSelector championSelector = new ChampionSelector(1L);
            testArchers = new ArrayList<Archer>(championSelector.enrollArchers(amountarchers));

            long startTime3 = System.currentTimeMillis();
            while ((System.currentTimeMillis() - startTime3) < 100) {

                long startTime = System.currentTimeMillis();

                sorter.selInsSort(testArchers, scoringScheme);

                long endTime = System.currentTimeMillis();
                duration = (endTime - startTime);

                System.out.println("insertion sort - amount archers: " + amountarchers
                        + " duration test in miliseconds: " + duration);
            }

            amountarchers += amountarchers * 2;
        }

        System.out.println("Test done");

        System.gc();
    }

}
