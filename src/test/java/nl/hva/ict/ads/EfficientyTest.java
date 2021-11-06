package nl.hva.ict.ads;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

class EfficientyTest {
    final static int MAX_ARCHERS = 5000000;
    final static int MAX_MILLISECONDS = 20000; // 20 seconds

    protected Sorter<Archer> sorter = new ArcherSorter();
    protected List<Archer> archers1;
    protected Comparator<Archer> scoringScheme = Archer::compareByHighestTotalScoreWithLeastMissesAndLowestId;

    @BeforeEach
    void setup() {

    }

    @Test
    void timeTest() {
        ChampionSelector championSelector = new ChampionSelector(1L);
        int archerAmount = 100;
        boolean collectionsSortRunning = true;
        boolean selSortRunning = true;
        boolean quickSortRunning = true;
        Stopwatch tracker1;
        Stopwatch tracker2;
        Stopwatch tracker3;
        double time1 = 0;
        double time2 = 0;
        double time3 = 0;

        while (archerAmount < MAX_ARCHERS) {
            archers1 = new ArrayList<Archer>(championSelector.enrollArchers(archerAmount));
            List<Archer> archers2 = new ArrayList<>(archers1);
            List<Archer> archers3 = new ArrayList<>(archers1);

            System.out.println("Number of archers: " + archerAmount);

            if (collectionsSortRunning) {
                tracker1 = new Stopwatch();
                Collections.sort(archers1, scoringScheme);
                time1 = tracker1.elapsedTime();
                System.out.println("Collections sort: " + time1);
                if (tracker1.elapsedTime() >= MAX_MILLISECONDS) {
                    System.out.println("Collections sort timed out");
                    collectionsSortRunning = false;
                }
            }

            if (selSortRunning) {
                tracker2 = new Stopwatch();
                sorter.selInsSort(archers2, scoringScheme);
                time2 = tracker2.elapsedTime();
                System.out.printf("Insertionsort: %.2f \n", time2);
                if (tracker2.elapsedTime() >= MAX_MILLISECONDS) {
                    System.out.println("Insertion sort timed out");
                    selSortRunning = false;
                }
            }

            if (quickSortRunning) {
                tracker3 = new Stopwatch();
                sorter.quickSort(archers3, scoringScheme);
                time3 = tracker3.elapsedTime();
                System.out.printf("Quicksort: %.2f \n", time3);
                if (tracker3.elapsedTime() >= MAX_MILLISECONDS) {
                    System.out.println("Quicksort timed out");
                    quickSortRunning = false;
                }

            }

            System.out.println();
            archerAmount *= 2;
        }
        // we will not be asserting Collections.sort since it could outperform our maxes
        assertFalse(selSortRunning);
        assertFalse(quickSortRunning);

        System.gc();
    }
}
