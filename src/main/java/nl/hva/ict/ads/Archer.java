package nl.hva.ict.ads;

public class Archer {
    public static int MAX_ARROWS = 3;
    public static int MAX_ROUNDS = 10;

    private final int id; // Once assigned a value is not allowed to change.
    private String firstName;
    private String lastName;

    static int amountArchers;
    private int totalScore;
    private int missesAmount;

    // add instance variable(s) to track the scores per round per arrow
    private int[][] pointsHolder = new int[11][3];

    /**
     * Constructs a new instance of Archer and assigns a unique id to the instance.
     * Each new instance should be assigned a number that is 1 higher than the last
     * one assigned. The first instance created should have ID 135788;
     *
     * @param firstName the archers first name.
     * @param lastName  the archers surname.
     */
    public Archer(String firstName, String lastName) {
        // initialise the new archer
        this.firstName = firstName;
        this.lastName = lastName;
        // generate and assign an new unique id
        this.id = generateId();
        // initialise the scores of the archer
        this.totalScore = 0;
        this.missesAmount = 0;
    }

    /**
     * Registers the points for each of the three arrows that have been shot during
     * a round.
     *
     * @param round  the round for which to register the points. First round has
     *               number 1.
     * @param points the points shot during the round, one for each arrow.
     */
    public void registerScoreForRound(int round, int[] points) {
        // register the points into the archer's data structure for scores.
        for (int i = 0; i < 3; i++) {
            pointsHolder[round - 1][i] = points[i];
            if (points[i] == 0) {
                // keep track of misses when points are 0
                missesAmount++;
            }
        }
    }

    /**
     * Calculates/retrieves the total score of all arrows across all rounds
     * 
     * @return
     */
    public int getTotalScore() {
        // calculate/get the total score that the archer has earned across all
        // arrows of all registered rounds
        totalScore = 0;
        for (int i = 0; i < 11; i++) {
            for (int j = 0; j < 3; j++) {
                totalScore = totalScore + pointsHolder[i][j];
            }
        }

        return totalScore;
    }

    /**
     * compares the scores/id of this archer with the scores/id of the other archer
     * according to the scoring scheme: highest total points -> least misses ->
     * earliest registration The archer with the lowest id has registered first
     * 
     * @param other the other archer to compare against
     * @return negative number, zero or positive number according to Comparator
     *         convention
     */
    public int compareByHighestTotalScoreWithLeastMissesAndLowestId(Archer other) {
        // compares the scores/misses/id of this archer with the other archer
        // and return the result according to Comparator conventions
        if (this.getTotalScore() < other.getTotalScore()) // total score
            return +1;
        if (this.getTotalScore() > other.getTotalScore())
            return -1;
        if (missesAmount > other.missesAmount) // misses
            return +1;
        if (missesAmount < other.missesAmount)
            return -1;
        if (this.getId() > other.getId()) // id
            return +1;
        if (this.getId() < other.getId())
            return -1;
        return 0; // default
    }

    public int getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    // provide a toSting implementation to format archers nicely
    public String toString() {
        return getId() + " (" + getTotalScore() + ") " + getFirstName() + " " + getLastName();
    }

    private int generateId() {
        int id = 135787 + amountArchers++;
        return id;
    }
}
