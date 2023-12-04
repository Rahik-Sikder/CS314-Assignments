import java.util.ArrayList;

public class NameRecord implements Comparable<NameRecord> {

    // Instance Variables
    private String name;
    private int baseDecade;
    private int numDecades;
    private ArrayList<Integer> decadeRanks;

    // Class Constants
    public static final int YEARS_PER_DECADE = 10; // public so Names can access
    private static final int WORST_RANKING = 1001;

    /**
     * Constructer that takes in and stores the base decade and number
     * of decades of the current NameRecord.
     * The name and decade ranks are parsed from the String nameData
     * 
     * <br>
     * pre: nameDataArray != null, numDecades > 0
     * <br>
     * 
     * @param nameDataArray Array containing the name and it's corresponding ranks
     * @param baseDecade    The base decade for name ranks
     * @param numDecades    The total number of decade ranks
     */
    public NameRecord(String[] nameDataArray, int baseDecade, int numDecades) {

        // Check preconditions
        if (nameDataArray == null || numDecades <= 0) {
            throw new IllegalArgumentException("Violation of precondition: " +
                    "nameDataArray != null, numDecades > 0");
        }

        this.baseDecade = baseDecade;
        this.numDecades = numDecades;
        this.name = nameDataArray[0];
        decadeRanks = new ArrayList<Integer>();

        for (int i = 1; i < nameDataArray.length; i++) {
            // parseInt(Strings s) - returns primitive int
            // preferred instead:
            // valueOf(String s) - returns Integer object
            decadeRanks.add(Integer.valueOf(nameDataArray[i]));
        }
        // System.out.println("Verify Variables: ");
        // System.out.println("NameData " + nameData);
        // System.out.println("ArrayList: " + decadeRanks.toString());
    }

    /**
     * 
     * @return name for this NameRecord
     */
    public String getName() {
        return name;
    }

    /**
     * 
     * @return base decade (decade of the first rank) for this NameRecord
     */
    public int getBaseDecade() {
        return baseDecade;
    }

    /**
     * 
     * @return total number of decades for this NameRecord including the
     *         years it is unranked
     */
    public int getNumDecades() {
        return numDecades;
    }

    /**
     * Given a year starting from the base decade at 0,
     * returns the NameRecords rank for that given decade.
     * <br>
     * pre: 0 <= decade < numDecades
     * <br>
     * 
     * @param decade indexed from 0 with 0 being the base decade
     * @return NameRecords rank for a given decade
     */
    public int getRank(int decade) {

        // Check preconditions
        if (decade < 0 || decade >= numDecades) {
            throw new IllegalArgumentException("Violation of precondition: "
                    + " decade >= 0, decade < numDecades - 1");
        }

        return decadeRanks.get(decade);
    }

    /**
     * Returns an integer of the NameRecord's best decade. The lower
     * the value of the decade rank, the higher the rank. A rank of
     * 0 indicates that it was not in the top 1000 names
     * 
     * @return an int representing the NameRecord's best decade
     */
    public int getBestDecade() {

        int bestDecade = 0;
        int bestRank = WORST_RANKING; // Init with worst possible rank

        for (int curDecade = 0; curDecade < numDecades; curDecade++) {

            // Rank is better when closer to 1
            if (decadeRanks.get(curDecade) < bestRank && decadeRanks.get(curDecade) != 0) {
                bestDecade = curDecade;
                bestRank = decadeRanks.get(curDecade);
            }
        }

        return baseDecade + bestDecade * YEARS_PER_DECADE;
    }

    /**
     * 
     * @return number of decades this name has been ranked in the top 1000
     */
    public int numTimesRanked() {

        int numTimesRanked = 0;

        for (int curDecade = 0; curDecade < numDecades; curDecade++) {
            if (decadeRanks.get(curDecade) > 0) {
                numTimesRanked++;
            }
        }

        return numTimesRanked;
    }

    /**
     * 
     * @return true if this name has been ranked in the top 1000 in every decade,
     *         false if otherwise.
     */
    public boolean isAlwaysRanked() {
        return numTimesRanked() == numDecades;
    }

    /**
     * 
     * @return true if this name has been ranked in the top 1000 in only one decade,
     *         false if otherwise.
     */
    public boolean isOnlyRankedOnce() {
        return numTimesRanked() == 1;
    }

    /**
     * 
     * @return true if this name has been getting more popular every decade,
     *         false if otherwise.
     */
    public boolean isAlwaysImproving() {

        for (int curDecade = 1; curDecade < numDecades; curDecade++) {

            int curRank = decadeRanks.get(curDecade);
            int prevRank = decadeRanks.get(curDecade - 1);

            // Replacing unranked (0) with WORST_RANKING
            curRank = curRank == 0 ? WORST_RANKING : curRank;
            prevRank = prevRank == 0 ? WORST_RANKING : prevRank;

            // Ranks cannot be greater than or equal to the previous decade
            if (curRank >= prevRank) {
                return false;
            }
        }
        return true;
    }

    /**
     * 
     * @return true if this name has been getting less popular every decade,
     *         false if otherwise.
     */
    public boolean isAlwaysDeclining() {

        for (int curDecade = 1; curDecade < numDecades; curDecade++) {

            int curRank = decadeRanks.get(curDecade);
            int prevRank = decadeRanks.get(curDecade - 1);

            // Replacing unranked (0) with WORST_RANKING
            curRank = curRank == 0 ? WORST_RANKING : curRank;
            prevRank = prevRank == 0 ? WORST_RANKING : prevRank;

            // Ranks cannot be less than or equal to the previous decade
            if (curRank <= prevRank) {
                return false;
            }
        }

        return true;
    }

    /**
     * Given a year starting from the base decade at 0,
     * returns true if there are no occurances of the
     * NameRecord before the year and the name occurs
     * on that decade
     * 
     * <br>
     * pre: 0 <= decade < numDecades
     * <br>
     * 
     * @param decade indexed from 0 with 0 being the base decade
     * @return true if name started on that decade,
     *         false if otherwise.
     */
    public boolean startedInDecade(int decade) {

        // Check preconditions
        if (decade < 0 || decade >= numDecades) {
            throw new IllegalArgumentException("Violation of precondition: "
                    + " decade >= 0, decade < numDecades - 1");
        }

        // If name doesn't occur on decade it didn't start on it
        if (decadeRanks.get(decade) == 0) {
            return false;
        }

        // Goes through every rank from the years before decade
        for (int i = 0; i < decade; i++) {

            if (decadeRanks.get(i) > 0) {

                return false;
            }
        }

        return true;
    }

    @Override
    /**
     * Overrides toString
     * 
     * @return a String containing the NameRecord's name
     *         and all of the decades with their corresponding
     *         rank starting from the base decade.
     */
    public String toString() {

        StringBuilder output = new StringBuilder(name);
        output.append("\n");

        for (int curDecade = 0; curDecade < numDecades; curDecade++) {

            // Real Decade is found by counting up by 10 curDecade times
            // from the base decade
            int realDecade = baseDecade + curDecade * YEARS_PER_DECADE;
            output.append(realDecade + ": " + decadeRanks.get(curDecade));
            output.append("\n");
        }

        return output.toString();
    }

    /**
     * 
     * @param record != null
     * @return a negative integer, zero, or a positive integer as the NameRecord
     *         is less than, equal to, or greater than the specified object.
     */
    public int compareTo(NameRecord record) {

        // Check preconditions
        if (record == null) {
            throw new IllegalArgumentException("Violation of precondition: "
                    + " decade >= 0, decade < numDecades - 1");
        }

        NameRecord other = record;
        return name.compareTo(other.name);
    }

}
