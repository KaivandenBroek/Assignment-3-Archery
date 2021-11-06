package nl.hva.ict.ads;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class EfficientyTest {
    protected Sorter<Archer> sorter = new ArcherSorter();
    protected List<Archer> fewArchers;
    protected List<Archer> manyArchers;
    protected List<Archer> testArchers;
    protected List<Archer> testArchers2;
    protected List<Archer> testArchers3;
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
        testArchers = new ArrayList(ChampionSelector.enrollArchers(100));
        testArchers2 = new ArrayList(ChampionSelector.enrollArchers(100));
        testArchers3 = new ArrayList(ChampionSelector.enrollArchers(100));

        while(testArchers <= maxArchers){
            
        }
        
        // keep track of time
        long startTime = System.nanoTime();
        test();
        long endTime = System.nanoTime();
        long duration = (endTime - startTime);
        // test insertion sort

        // keep track of time
        // test quick sort

        System.gc();
    }

}
