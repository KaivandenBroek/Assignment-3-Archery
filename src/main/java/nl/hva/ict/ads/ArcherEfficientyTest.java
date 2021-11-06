package nl.hva.ict.ads;

import java.util.Comparator;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

public class ArcherEfficientyTest {

    private static Sorter<Archer> sorter = new ArcherSorter();
    private static Comparator<Archer> scoringScheme = Archer::compareByHighestTotalScoreWithLeastMissesAndLowestId;
    private static int amountArchers = 100;
    private static int MaxArchers = 5000000;
    private static long maxTimeInMilis = 20000;
    private static List<Archer> archers1;
    private static List<Archer> archers2;
    private static List<Archer> archers3;
    private static boolean running1 = true;
    private static boolean running2 = true;
    private static boolean running3 = true;

    public static void main(String[] args) {

        while (amountArchers < MaxArchers) {
            ChampionSelector championSelector = new ChampionSelector(1L);

            // generate archers
            archers1 = new ArrayList<Archer>(championSelector.enrollArchers(amountArchers));
            archers2 = archers1;
            archers3 = archers1;

            System.out.println("amount archers: " + amountArchers);

            if (running1) {
                Stopwatch tracker = new Stopwatch();
                Collections.sort(archers1, scoringScheme);
                long time1 = tracker.elapsedTime();
                System.out.println("time elapsed sort: " + time1);
                if (tracker.elapsedTime() >= maxTimeInMilis) {
                    System.out.println("timed out");
                    running1 = false;
                }

            }

            if (running2) {
                Stopwatch tracker = new Stopwatch();
                sorter.selInsSort(archers2, scoringScheme);
                long time2 = tracker.elapsedTime();
                System.out.println("time elapsed insertionsort: " + time2);
                if (tracker.elapsedTime() >= maxTimeInMilis) {
                    System.out.println("timed out");
                    running2 = false;
                }

            }

            if (running3) {
                Stopwatch tracker = new Stopwatch();
                sorter.quickSort(archers3, scoringScheme);
                long time3 = tracker.elapsedTime();
                System.out.println("time elapsed quicksort: " + time3);
                if (tracker.elapsedTime() >= maxTimeInMilis) {
                    System.out.println("timed out");
                    running3 = false;
                }

            }

            amountArchers = +amountArchers * 2;
        }

        System.out.println("Finished!");

    }

}
