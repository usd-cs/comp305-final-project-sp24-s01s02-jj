package edu.sandiego.comp305.sp24.schoolSim.model;

import java.util.List;
import java.util.Map;

public interface DatabaseItem {

    /**
     * Get an instance of an object representing the table containing this item. Used
     * to grab column names needed to properly make a string map.
     *
     * @return An instance of the table containing this item.
     */
    DatabaseTable getParentTable();

    /**
     * Return a list of strings representing the values of this item. The order should be consistent
     * across items and match the order of getParentTable().getColumnNames().
     *
     * @return A mapping of this item's elements as strings to their column in the table
     */
    List<String> getStringList();
}
