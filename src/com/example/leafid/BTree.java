package com.example.leafid;

import java.util.ArrayList;

//Manages a question and follow-up questions. Identifies with resultID.
abstract class BTree {
    protected String resultID, query;

    public String getResultID() {
        return resultID;
    }

    public String getQuery() {
        return query;
    }

    public static BTree initialize() {
        ArrayList<BTree> firstQuestions = new ArrayList<BTree>();
        firstQuestions.add(new Query(
                        "",
                        "Does the tree bear cones and have leaves that are needle-like?"));
        return new Query("", "");
    }
}

class Query extends BTree {
    private ArrayList<BTree> children;

    public Query(String resultID, String query) {
        this.resultID = resultID;
        this.query = query;
    }

    public void populateChildren() {
        // TODO: Query database for children with parentID of resultID
    }

    public ArrayList<String> getChildQueries() {
        ArrayList<String> result = new ArrayList<String>();
        for (BTree b : children) {
            result.add(b.getQuery());
        }
        return result;
    }

    // Gets corresponding BTree based on user choice.
    public BTree getResponse(String choice) {
        String fullResultID = resultID + choice;
        for (BTree b : children) {
            if (b.getResultID().equals(fullResultID))
                if (b instanceof Query)
                    ((Query) b).populateChildren();
            return b;

        }
        return null;
    }
}

// Manages a question and a TreeCursor to display if needed.
class Answer extends BTree {
    TreeCursor tree;

    public Answer(String resultID, String query, TreeCursor tree) {
        this.resultID = resultID;
        this.query = query;
        this.tree = tree;
    }

}

class TreeCursor {
    // Contains the row id of the tree in database.
    int databaseIndex;

    public TreeCursor(int _databaseIndex) {
        databaseIndex = _databaseIndex;
    }
}

/*
 * class myLeaves { public void main() { TreeType EasternWhitePine = new
 * TreeType("Eastern White Pine", "Pinus strobus", true); TreeType PitchPine =
 * new TreeType("Pitch Pine", "Pinus rigida", true); TreeType LongleafPine = new
 * TreeType("Longleaf Pine", "Pinus palustris", true); TreeType LoblollyPine =
 * new TreeType("Loblolly Pine", "Pinus taeda", true); TreeType JackPine = new
 * TreeType("Jack Pine / Scrub Pine", "Pinus banksiana", true); TreeType
 * ScotchPine = new TreeType("Scotch Pine", "Pinus sylvestris", true); TreeType
 * PonderosaPine = new TreeType("Ponderosa Pine", "Pinus ponderosa", true);
 * TreeType SlashPine = new TreeType("Slash Pine", "Pinus elliottii", true);
 * TreeType ShortleafPine = new TreeType("Shortleaf Pine", "Pinus echinata",
 * true);
 * 
 * 
 * ArrayList<BTree> at1array = new ArrayList<BTree>(); ArrayList<BTree>
 * at2c1array = new ArrayList<BTree>(); ArrayList<BTree> at3c1array = new
 * ArrayList<BTree>(); ArrayList<BTree> at3c2array = new ArrayList<BTree>();
 * ArrayList<BTree> at4c1array = new ArrayList<BTree>(); ArrayList<BTree>
 * at4c2array = new ArrayList<BTree>(); ArrayList<BTree> at4c3array = new
 * ArrayList<BTree>(); ArrayList<BTree> at5c1array = new ArrayList<BTree>();
 * ArrayList<BTree> at5c2array = new ArrayList<BTree>(); ArrayList<BTree>
 * at5c3array = new ArrayList<BTree>(); ArrayList<BTree> at5c4array = new
 * ArrayList<BTree>(); ArrayList<BTree> at5c5array = new ArrayList<BTree>();
 * ArrayList<BTree> at5c6array = new ArrayList<BTree>(); ArrayList<BTree>
 * at5c7array = new ArrayList<BTree>(); ArrayList<BTree> at5c8array = new
 * ArrayList<BTree>();
 * 
 * /***************************** Tier 5**************************
 */
/*
 * // Needles clustered in groups of 3 // Parent: at4c1 at5c1array.add(new
 * Leaf(PitchPine)); at5c2array.add(new Leaf(LongleafPine)); at5c3array.add(new
 * Leaf(LoblollyPine)); BTree at5c1 = new Node(
 * "Are the needles 3\"-5\" long, somewhat twisted, often sprouting in tufts from the trunk; cones 2\"-3 1/2\" long?"
 * , at5c1array); BTree at5c2 = new
 * Node("Are the needles 8\"-18\" long, cones 6\"-10\" long?", at5c2array);
 * BTree at5c3 = new Node("Are the needles 6\"-9\" long, cones 3\"-6\" long?",
 * at5c3array);
 * 
 * // Needles clustered in groups of 2 // Parent: at4c2 at5c4array.add(new
 * Leaf(JackPine)); at5c5array.add(new Leaf(ScotchPine)); BTree at5c4 = new
 * Node(
 * "Are the needles mostly 1\" long, yellow-green, and widely spreading bunches?"
 * , at5c4array); BTree at5c5 = new Node(
 * "Are the needles 1 1/2\"- 4\" long, blue-green to yellow-green, and twisted?"
 * , at5c5array);
 * 
 * // Needles clustered in groups of 2 and 3 on the same tree // Parent: at4c3
 * at5c4array.add(new Leaf(PonderosaPine)); at5c4array.add(new Leaf(SlashPine));
 * at5c4array.add(new Leaf(ShortleafPine)); BTree at5c6 = new Node(
 * "Are the needles 5\"-10\" long, cones 3\"-6\" long? (Native to the Great Plains)"
 * , at5c6array); BTree at5c7 = new Node(
 * "Are the needles 7\"-10\" long, cones 3\"-6\" long? (Native to southeastern states along coastal plain)"
 * , at5c7array); BTree at5c8 = new
 * Node("Are the needles 3\"-5\" long, cones 1 1/2\"-2 1/2\" long?",
 * at5c8array);
 * 
 * /***************************** Tier 4**************************
 */

// Needles clustered in groups of 2 or 3, and the cone scales thick and often
// tipped with spines?
// Parent: at3c2
/*
 * at4c1array.add(at5c1); at4c1array.add(at5c2); at4c1array.add(at5c3);
 * at4c2array.add(at5c4); at4c2array.add(at5c5); BTree at4c1 = new
 * Node("Are the needles clustered in groups of 3?", at4c1array); BTree at4c2 =
 * new Node("Are the needles clustered in groups of 2?", at4c2array); BTree
 * at4c3 = new
 * Node("Are the needles clustered in groups of 2 and 3 on the same tree?",
 * at4c3array);
 * 
 * /***************************** Tier 3**************************
 */

// EVERGREEN with needles arranged in clusters of 2-5
// Parent: at2c1
/*
 * at3c1array.add(new Leaf(EasternWhitePine)); at3c2array.add(at4c1);
 * at3c2array.add(at4c2); at3c2array.add(at4c3); BTree at3c1 = new Node(
 * "Are the needles clustered in groups of 5 and the cones long with thin scales?"
 * , at3c1array); BTree at3c2 = new Node(
 * "Are the needles clustered in groups of 2 or 3, and the cone scales thick and often tipped with spines?"
 * , at3c2array);
 * 
 * BTree at2c1 = new
 * Node("Are the trees evergreen with needles in clusters of 2-5?", at2c1array);
 * BTree at2c2 = new
 * Node("Are the trees evergreen with needles arranged singly?", x); BTree at2c3
 * = new Node(
 * "Are the trees deciduous with needles arranged in clusters on short branches?"
 * , x); BTree at2c4 = new Node(
 * "Are the trees deciduous with needles of uneven length flattened along the twig?"
 * , x);
 * 
 * at1array.add(at2c1); BTree at1c1 = new
 * Node("Does the tree bear cones and have needle-like leaves?", at1array);
 * 
 * 
 * 
 * 
 * 
 * }
 */
