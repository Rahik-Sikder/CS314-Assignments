/* CS 314 STUDENTS: FILL IN THIS HEADER.
 *
 * Student information for assignment:
 *
 *  On my honor, RAHIK N SIKDER, this programming assignment is my own work
 *  and I have not provided this code to any other student.
 *
 *  UTEID: rns2359
 *  email address: sikder.rahik@utexas.edu
 *  TA name: Casey, Assistant of Teachers
 */

import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;

/**
 *
 * Ranks football teams based on statistics generated from
 * a graph formed by those teams. An edge exists between
 * team A and team B if team A beat team B. The weight of the
 * edge is proportional to the inverse of the margin of victory.
 *
 * The number of other team is connected to AND more importantly
 * the average weight of the paths from a team to the other teams
 * are used to determine the best team.
 *
 */
public class FootballRanker {

    // only include teams in final rankings with this many wins
    private static final int MIN_WINS = 7;

    // only rank teams with a significant number of transitive wins.
    private static final int MIN_TRANSITIVE_WINS = 100;

    // for output
    private static final int PADDING = 20;

    // to adjust output
    private static final DecimalFormat ourFormatter = new DecimalFormat("0.0000");

    // used to adjust scores to number scores winning team won by,
    private static final int TD_PLUS = 8;

    // fields in games results data files
    private static final int HOME_TEAM = 1;
    private static final int HOME_SCORE = 2;
    private static final int AWAY_TEAM = 3;
    private static final int AWAY_SCORE = 4;

    private Graph teamsAndResults;
    private Map<String, FootballRecord> records;
    private List<String> actualRanks;

    /**
     * Create a new FootballRanker based on the given files.
     * Format of file is:
     * <br>
     * Date,Home Team Name,Home Team Points,Away Team Name,Away Team Points,Location
     * if neutral.
     * <br>
     * results that are ties are ignored
     * <br>
     * pre: gameResults != null, actualRanks != null, files with the given names
     * are in the local directory.
     * 
     * @param gameResults The name of the file with the results from games.
     * @param actualRanks The name of the file with the final end of season
     *                    rankings.
     */
    public FootballRanker(String gameResults, String actualRanks) {
        if (gameResults == null || actualRanks == null) {
            throw new IllegalArgumentException("Violation of precondition. " +
                    "File names may not be null.");
        }
        records = new HashMap<>();
        teamsAndResults = buildGraph(gameResults);
        this.actualRanks = getActual(actualRanks);

    }

    /**
     * Calculate top teams based solely on average path length of unweighted
     * shortest paths.
     * 
     * @param showResults if this is true output results to standard output
     * @return the root mean square error of the top 25 teams based on this
     *         calculation
     *         rounded to 1 decimal place.
     */
    public double doUnweighted(boolean showResults) {
        if (showResults) {
            System.out.print("\n\n ***** RESULTS BASED ON UNWEIGHTED WINS *****");
        }
        teamsAndResults.findAllPaths(false);
        TreeSet<AllPathsInfo> paths = teamsAndResults.getAllPaths();
        paths = rankByScore(paths);
        if (showResults) {
            showResults(paths);
        }
        return printRootMeanSquareError(actualRanks, paths, showResults);
    }

    /**
     * Calculate top teams based on average path length of weighted shortest paths.
     * This takes into account how easily teams won their games.
     * 
     * @param showResults if this is true output results to standard output
     * @return the root mean square error of the top 25 teams based on this
     *         calculation
     *         rounded to 1 decimal place.
     */
    public double doWeighted(boolean showResults) {
        if (showResults) {
            System.out.print("\n\n ***** RESULTS BASED ON WEIGHTED WINS *****");

        }
        teamsAndResults.findAllPaths(true);
        TreeSet<AllPathsInfo> paths = teamsAndResults.getAllPaths();
        paths = rankByScore(paths);
        if (showResults) {
            showResults(paths);
        }
        return printRootMeanSquareError(actualRanks, paths, showResults);
    }

    /**
     * Calculate top teams based on average path length of weighted shortest paths
     * with an adjustment for the teams winning percentage.
     * 
     * @param showResults if this is true output results to standard output
     * @return the root mean square error of the top 25 teams based on this
     *         calculation
     *         rounded to 1 decimal place.
     */
    public double doWeightedAndWinPercentAdjusted(boolean showResults) {
        if (showResults) {
            System.out.print("\n\n ***** RESULTS BASED ON WEIGHTED WINS ADJUSTED BY WIN PERCENTAGE *****");
        }
        teamsAndResults.findAllPaths(true);
        TreeSet<AllPathsInfo> paths = teamsAndResults.getAllPaths();
        paths = rankByScoreAdjustWinPercentage(paths);
        if (showResults) {
            showResults(paths);
        }
        return printRootMeanSquareError(actualRanks, paths, showResults);
    }

    // show results of calculations
    private void showResults(Set<AllPathsInfo> paths) {
        System.out.println("\n\n***** TOP TEAMS *****");
        int rank = 1;
        for (AllPathsInfo team : paths) {
            System.out.println("predicted rank: "
                    + (rank++) + ", " + team);
        }
    }

    private final int ONE_DEC_ROUNDING = 10;

    /*
     * Calculate difference between predicted results based on graph and actual
     * results of
     * poll data. return the root mean square error of difference between acutal
     * poll results and
     * predicted results from graph.
     * If showResults is true print results to standard output.
     */
    private double printRootMeanSquareError(List<String> humanRanks,
            TreeSet<AllPathsInfo> paths, boolean showResults) {
        if (showResults) {
            System.out.println("\n\n ***** PREDICTED VS. ACTUAL RESULTS *****");
        }

        // First want to calculate the summation
        // Keep a sum variable outside the loop
        double summation = 0;

        // Iterate through each rank in the AP rank
        for (int rankIndex = 0; rankIndex < humanRanks.size(); rankIndex++) {
            int humanRank = rankIndex + 1;
            String team = humanRanks.get(rankIndex);

            // Get the corresponding computing rank
            int computedRank = getRankFromTreeSet(paths, team);

            // If team doesn't exist in computed set...
            if (computedRank == -1) {
                // Rank is alr offset by 1 thru 0 based indexing, so just add 1 to size
                computedRank = paths.size() + 1;
            }

            // Subtract computed from human
            double temp = humanRank - computedRank;

            // Square that
            temp *= temp;

            // Add to summation
            summation += temp;

            // Print to output
            if (showResults)
                printRankComparison(team, humanRank, computedRank);
        }

        // Divide the summation by total teams
        double result = summation / humanRanks.size();

        // Find sqrt
        result = Math.sqrt(result);

        // Print to output
        if (showResults) {
            System.out.println("Root Mean Square Error: " + String.format("%,.4f", result));

        }

        return (double) Math.round(result * ONE_DEC_ROUNDING) / ONE_DEC_ROUNDING;
    }

    /**
     * Returns index of tgt in paths
     * 
     * @param paths
     * @param tgt
     * @return
     */
    private int getRankFromTreeSet(TreeSet<AllPathsInfo> paths, String tgt) {

        Iterator<AllPathsInfo> iter = paths.iterator();
        int index = 1;

        while (iter.hasNext()) {
            String nextTeam = iter.next().getName();
            if (nextTeam.equals(tgt)) {
                return index;
            }
            index++;
        }
        return -1;
    }

    /**
     * Prints out the current Team, its human rank, and its computed rank
     * @param team
     * @param actualRank
     * @param predictedRank
     */
    private void printRankComparison(String team, int actualRank, int predictedRank) {
        // I forgot String.format was a thing when I wrote my previous printTestResults :sob:
        // Much more concise
        System.out.print(String.format("%-20s", team));
        System.out.println(" - actual rank: " + actualRank + " predicted rank: " + predictedRank);
    }

    public void processRequests() {
        Scanner in = new Scanner(System.in);
        do {
            System.out.print("\nEnter starting team: ");
            String start = in.nextLine().trim();
            if (teamsAndResults.containsVertex(start)) {
                teamsAndResults.dijkstra(start);
                // teamsAndResults.findUnweightedShortestPath(start);
                do {
                    System.out.println("Finding paths from " + start);
                    System.out.print("\nEnter ending team: ");
                    String end = in.nextLine().trim();
                    if (teamsAndResults.containsVertex(end)) {
                        teamsAndResults.printPath(end);
                    } else {
                        System.out.println("\n" + end + " is not a team");
                    }
                } while (another(in, "Do you want to find another path from " + start + "?"));
            } else {
                System.out.println("\n" + start + " is not a team");
            }
        } while (another(in, "Do you want to look for more paths between teams?"));
    }

    /**
     * Show the longest path in the graph based on games played.
     */
    public void showLongest() {
        System.out.println();
        System.out.println(teamsAndResults.getLongestPath());
    }

    private boolean another(Scanner in, String prompt) {
        System.out.println(prompt);
        System.out.print("Enter y for yes, anything else for no: ");
        String answer = in.nextLine().trim().toLowerCase();
        System.out.println();
        return answer != null && answer.length() > 0 && answer.charAt(0) == 'y';
    }

    private List<String> getActual(String filename) {
        List<String> result = new ArrayList<>();
        try {
            Scanner sc = new Scanner(new File(filename));
            while (sc.hasNext()) {
                String temp = sc.nextLine().trim();
                result.add(temp);

            }
            sc.close();
        } catch (IOException e) {
            System.out.println("Error reading from file: " + e);
            System.out.println("Returning null");
            result = null;
        }
        return result;
    }

    private TreeSet<AllPathsInfo> rankByScoreAdjustWinPercentage(Set<AllPathsInfo> paths) {

        TreeSet<AllPathsInfo> result = new TreeSet<>(new AveCostComparator());
        for (AllPathsInfo teamPaths : paths) {
            FootballRecord team = records.get(teamPaths.getName());
            if (team.getWins() > MIN_WINS
                    && teamPaths.getNumPaths() > MIN_TRANSITIVE_WINS) {

                double winPercent = team.winPercent();
                AllPathsInfo copy = AllPathsInfo.makeCopy(teamPaths);
                copy.adjustAveCost(1.0 / winPercent);
                result.add(copy);
            }
        }
        return result;
    }

    private TreeSet<AllPathsInfo> rankByScore(Set<AllPathsInfo> paths) {

        TreeSet<AllPathsInfo> result = new TreeSet<>(new AveCostComparator());
        for (AllPathsInfo teamPaths : paths) {
            FootballRecord team = records.get(teamPaths.getName());
            if (team.getWins() > MIN_WINS
                    && teamPaths.getNumPaths() > MIN_TRANSITIVE_WINS) {

                boolean added = result.add(teamPaths);
                assert added : "Should have added this team. " + teamPaths;
            }
        }
        return result;
    }

    private Graph buildGraph(String gameResults) {
        Graph g = new Graph();
        try {
            Scanner sc = new Scanner(new File(gameResults));
            while (sc.hasNext()) {
                String temp = sc.nextLine();
                String[] line = temp.trim().split(",");
                int scoreDiff = Integer.parseInt(line[HOME_SCORE]) - Integer.parseInt(line[AWAY_SCORE]);
                if (scoreDiff != 0) {
                    addTeam(records, line[1]);
                    addTeam(records, line[3]);
                    addEdge(g, line, records, scoreDiff);
                } else {
                    // prints out ties
                    // System.out.println(Arrays.toString(line));
                }
            }
            sc.close();
        } catch (IOException e) {
            System.out.println("Error reading from file: " + e);
            System.out.println("Returning null");
            g = null;
        }
        return g;
    }

    private void addEdge(Graph g, String[] data,
            Map<String, FootballRecord> recordsMap, int scoreDiff) {

        int scoresWonBy = (scoreDiff / TD_PLUS);
        if (scoresWonBy == 0) {
            scoresWonBy = 1;
        }
        double cost = Math.abs(1.0 / scoresWonBy);

        if (scoreDiff > 0) {
            g.addEdge(data[HOME_TEAM], data[AWAY_TEAM], cost);
            recordsMap.get(data[HOME_TEAM]).win();
            recordsMap.get(data[AWAY_TEAM]).lose();
        } else {
            g.addEdge(data[3], data[HOME_TEAM], cost);
            recordsMap.get(data[AWAY_TEAM]).win();
            recordsMap.get(data[HOME_TEAM]).lose();
        }
    }

    private void addTeam(Map<String, FootballRecord> recordsMap, String name) {
        if (!recordsMap.containsKey(name)) {
            recordsMap.put(name, new FootballRecord(name));
        }
    }

    private static class AveCostComparator implements Comparator<AllPathsInfo> {

        public int compare(AllPathsInfo a, AllPathsInfo b) {
            int result = (a.getAveCost() < b.getAveCost()) ? -1 : (a.getAveCost() == b.getAveCost()) ? 0 : 1;
            if (result == 0) {
                result = a.getName().compareTo(b.getName());
            }
            return result;
        }
    }

    /**
     * Show all vertices (teams) in the graph, mostly for debugging.
     */
    public void showAll() {
        teamsAndResults.showAll();
    }
}
