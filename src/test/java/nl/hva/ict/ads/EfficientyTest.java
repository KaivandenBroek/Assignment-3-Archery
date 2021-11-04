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
        // creat archers
        // 3 unordered lists for each test

        // keep track of time
        // test insertion sort

        // keep track of time
        // test quick sort

        System.gc();
    }

}
