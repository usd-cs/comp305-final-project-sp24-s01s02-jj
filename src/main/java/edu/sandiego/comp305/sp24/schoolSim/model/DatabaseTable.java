package edu.sandiego.comp305.sp24.schoolSim.model;

import java.util.List;

public interface DatabaseTable {
    int PAGE_SIZE = 20;

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

    /**
     * Retrieves a page of database items according to the provided page number.
     *
     * @param pageNumber The page of items to retrieve.
     * @return The list of database items on that page.
     */
    List<DatabaseItem> getAllPaged(int pageNumber);

}
