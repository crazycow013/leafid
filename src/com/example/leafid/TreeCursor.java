package com.example.leafid;

public class TreeCursor {
    // String of answers to queries, e.g. "abacdda"
    // Answer to question 1 is "a", 2 is "b", etc.
    String resultID;
    // Contains the row id of the tree in database.
    int databaseIndex;
    public TreeCursor(String _resultID, int _databaseIndex){
        resultID = _resultID;
        databaseIndex = _databaseIndex;
    }
}
