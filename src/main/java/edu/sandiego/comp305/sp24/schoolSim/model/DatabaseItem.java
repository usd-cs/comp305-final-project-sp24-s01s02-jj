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
     * Get mappings of column names to object values. The number of keys should match
     * getParentTable().getColumnNames().size(). There should be one mapping for every
     * entry in that same list to a string representation of the matching attribute.
     *
     * @return A mapping of this item's elements as strings to their column in the table
     */
    Map<String, String> getStringMap();
}
