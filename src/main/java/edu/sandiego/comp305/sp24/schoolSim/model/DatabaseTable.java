package edu.sandiego.comp305.sp24.schoolSim.model;

import java.util.List;

public interface DatabaseTable {

    /**
     * Return the name of this table.
     *
     * @return The name of this table in the database as a string.
     */
    String getTableName();

    /**
     * Get the number of rows in this table.
     *
     * @return Number of rows in this table.
     */
    long getCountTableRows();

    /**
     * Get a list of the columns in this table.
     *
     * @return A list of the columns in this table as a string.
     */
    List<String> getColumnNames();

}
