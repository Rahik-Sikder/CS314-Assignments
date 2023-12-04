/*  Student information for assignment:
*
*  On my honor, RAHIK N SIKDER, this programming assignment is my own work
*  and I have not provided this code to any other student.
*
*  UTEID: rns2359
*  email address: sikder.rahik@utexas.edu
*  Number of slip days I am using:
*/

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

/**
 * A collection of NameRecords.
 * Stores NameRecord objects and provides methods to select
 * NameRecords based on various criteria.
 */
public class Names {

    // Instance Variables
    private int baseDecade;
    private int numDecades;
    private ArrayList<NameRecord> recordArray;

    /**
     * Construct a new Names object based on the data source the Scanner
     * sc is connected to. Assume the first two lines in the
     * data source are the base year and number of decades to use.
     * Any lines without the correct number of decades are discarded
     * and are not part of the resulting Names object.
     * Any names with ranks of all 0 are discarded and not
     * part of the resulting Names object.
     * 
     * <br>
     * pre: scanner != null
     * <br>
     * 
     * @param scanner Is connected to a data file with baby names
     *                and positioned at the start of the data source.
     */
    public Names(Scanner scanner) {
        if (scanner == null) {
            throw new IllegalArgumentException("Violation of precondition: " +
                    "scanner != null");
        }
        baseDecade = Integer.parseInt(scanner.nextLine());
        numDecades = Integer.parseInt(scanner.nextLine());

        recordArray = new ArrayList<NameRecord>();
        while (scanner.hasNextLine()) {
            String nameData = scanner.nextLine();
            String[] nameDataArray = nameData.split("\\s+");
            if (nameDataArray.length == numDecades + 1 && atLeastOneRank(nameDataArray)) {
                recordArray.add(new NameRecord(nameDataArray, baseDecade, numDecades));
            }
        }
        Collections.sort(recordArray);
    }

    /**
     * Returns an ArrayList of NameRecord objects that contain a
     * given substring, ignoring case. The names must be in sorted order based
     * on the names of the NameRecords.
     * 
     * <br>
     * pre: partialName != null, partialName.length() > 0
     * <br>
     * 
     * @param partialName != null, partialName.length() > 0
     * @return an ArrayList of NameRecords whose names contains
     *         partialName. If there are no NameRecords that meet this
     *         criteria returns an empty list.
     */
    public ArrayList<NameRecord> getMatches(String partialName) {

        // Check preconditions
        if (partialName == null || partialName.length() <= 0) {
            throw new IllegalArgumentException("Violation of precondition: " +
                    "partialName != null, partialName.length() > 0");
        }

        // use lower case name, because case shouldn't matter
        String lowerCasePartial = partialName.toLowerCase();

        ArrayList<NameRecord> output = new ArrayList<NameRecord>();

        for (NameRecord record : recordArray) {
            String curName = record.getName().toLowerCase();
            if (curName.contains(lowerCasePartial)) {
                output.add(record);
            }
        }

        return output;
    }

    /**
     * Returns an ArrayList of Strings of names that have been ranked in the
     * top 1000 or better for every decade. The Strings must be in sorted
     * order based on the name of the NameRecords.
     * 
     * @return A list of the names that have been ranked in the top
     *         1000 or better in every decade. The list is in sorted ascending
     *         order. If there are no NameRecords that meet this
     *         criteria returns an empty list.
     */
    public ArrayList<String> rankedEveryDecade() {

        ArrayList<String> output = new ArrayList<String>();

        for (NameRecord record : recordArray) {

            if (record.isAlwaysRanked()) {
                output.add(record.getName());
            }
        }

        return output;

    }

    /**
     * Returns an ArrayList of Strings of names that have been ranked in the
     * top 1000 or better in exactly one decade. The Strings must be in sorted
     * order based on the name of the NameRecords.
     * 
     * @return A list of the names that have been ranked in the top
     *         1000 or better in exactly one decade. The list is in sorted ascending
     *         order. If there are no NameRecords that meet this
     *         criteria returns an empty list.
     */
    public ArrayList<String> rankedOnlyOneDecade() {

        ArrayList<String> output = new ArrayList<String>();

        for (NameRecord record : recordArray) {
            if (record.isOnlyRankedOnce()) {
                output.add(record.getName());
            }
        }

        return output;

    }

    /**
     * Returns an ArrayList of Strings of names that have been getting more
     * popular every decade. The Strings must be in sorted
     * order based on the name of the NameRecords.
     * 
     * @return A list of the names that have been getting more popular in
     *         every decade. The list is in sorted ascending
     *         order. If there are no NameRecords that meet this
     *         criteria returns an empty list.
     */
    public ArrayList<String> alwaysMorePopular() {

        ArrayList<String> output = new ArrayList<String>();

        for (NameRecord record : recordArray) {
            if (record.isAlwaysImproving()) {
                output.add(record.getName());
            }
        }

        return output;

    }

    /**
     * Returns an ArrayList of Strings of names that have been getting less
     * popular every decade. The Strings must be in sorted order based
     * on the name of the NameRecords.
     * 
     * @return A list of the names that have been getting less popular in
     *         every decade. The list is in sorted ascending
     *         order. If there are no NameRecords that meet this
     *         criteria returns an empty list.
     */
    public ArrayList<String> alwaysLessPopular() {

        ArrayList<String> output = new ArrayList<String>();

        for (NameRecord record : recordArray) {
            if (record.isAlwaysDeclining()) {
                output.add(record.getName());
            }
        }

        return output;

    }

    /**
     * Return the NameRecord in this Names object that matches the given String
     * ignoring case.
     * 
     * <br>
     * pre: name != null
     * <br>
     * 
     * @param name The name to search for.
     * @return The name record with the given name or null if no NameRecord in this
     *         Names object contains the given name.
     */
    public NameRecord getName(String name) {

        // Check preconditions
        if (name == null) {
            throw new IllegalArgumentException("Violation of precondition: " +
                    "name != null");
        }
        // Binary Search for name
        int low = 0;
        int high = recordArray.size() - 1;

        // Use lower case name, because case shouldn't matter
        String lowerCaseName = name.toLowerCase();

        while (low <= high) {
            int mid = low + (high - low) / 2;

            String curName = recordArray.get(mid).getName().toLowerCase();
            int comparision = lowerCaseName.compareTo(curName);
            if (comparision == 0) {
                return recordArray.get(mid);
                // name is alphabetically before
            } else if (comparision < 0) {
                high = mid - 1;

                // name is alphabetically after
            } else if (comparision > 0) {
                low = mid + 1;
            }
        }

        return null;
    }

    /**
     * Given a decade, returns a list of names that only showed up on or past
     * that decade. Decades are always rounded upwards.
     * 
     * <br>
     * pre: decade >= baseDecade, 
     *      decade <= baseDecade + numDecades * NameRecord.YEARS_PER_DECADE
     * <br>
     * 
     * @param decade on or after which names first showed up.
     *               decade is passed as an actual year, not the index
     * @return a list of names that have no rankings before decade
     */
    public ArrayList<NameRecord> startedInDecade(int decade) {

        // Check preconditions
        if (decade < baseDecade || decade > baseDecade + numDecades * NameRecord.YEARS_PER_DECADE) {
            throw new IllegalArgumentException("Violation of precondition: " +
                    "decade >= baseDecade, decade <= baseDecade + numDecades * NameRecord.YEARS_PER_DECADE");
        }

        // Decade is in written form, need to convert into an index starting from
        // the base year before passing to NameRecord
        // The NameRecord.YEARS_PER_DECADE - 1 rounds the given year upwards
        // as integer division always rounds down
        int indexDecade = ((decade + NameRecord.YEARS_PER_DECADE - 1) - baseDecade) / NameRecord.YEARS_PER_DECADE;

        ArrayList<NameRecord> output = new ArrayList<NameRecord>();

        for (NameRecord record : recordArray) {

            if (record.startedInDecade(indexDecade)) {
                output.add(record);
            }
        }

        return output;
    }

    /**
     * Checks to see if the array has at least one non 0 ranking.
     * Returns a boolean that's true if at least one of the rankings of the
     * name data array is not 0.
     * 
     * <br>
     * pre: nameDataArray != null
     * <br>
     * 
     * @param nameDataArray array containing decade ranking data for a
     *                      specific name
     * @return a boolean that's true if at least one non 0 ranking exists,
     *         otherwise returns false.
     */
    private boolean atLeastOneRank(String[] nameDataArray) {

        // Check preconditions
        if (nameDataArray == null) {
            throw new IllegalArgumentException("Violation of precondition: " +
                    "nameDataArray != null");
        }

        // Starting at 1 because first element is the name
        for (int i = 1; i < nameDataArray.length; i++) {
            if (Integer.parseInt(nameDataArray[i]) != 0) {
                return true;
            }
        }
        return false;
    }

}