package edu.sandiego.comp305.sp24.schoolSim.controller;

import edu.sandiego.comp305.sp24.schoolSim.model.DatabaseItem;
import edu.sandiego.comp305.sp24.schoolSim.model.DatabaseTable;
import org.springframework.web.util.HtmlUtils;

import java.util.List;

class TableVisualizer {
    public static String generateTableStatbox(DatabaseTable table) {
        StringBuilder box = new StringBuilder("<article class=\"message is-info cell\">");
        box.append("<div class=\"message-header\">");
        box.append(table.getTableName());
        box.append("</div>");
        box.append("<div class=\"message-body\">Number of Rows: ");
        box.append(table.getCountTableRows());
        box.append("</div></article>");
        return box.toString();
    }

    public static String generateTableView(DatabaseTable table, List<DatabaseItem> items) {
        List<String> headers = table.getColumnNames();
        StringBuilder tableHTML = new StringBuilder("<table class=\"table\"><thead>");
        tableHTML.append(TableVisualizer.rowWrap(true, headers));
        tableHTML.append("</thead><tbody>");
        for (DatabaseItem item : items) {
            tableHTML.append(TableVisualizer.rowWrap(false,item.getStringList()));
        }
        tableHTML.append("</tbody></table>");
        return tableHTML.toString();
    }

    private static StringBuilder rowWrap(boolean isHeader, List<String> items) {
        StringBuilder tableRow = new StringBuilder("<tr>");
        // First item will always be row header
        // .getFirst() introduced JDK21. Removed for compatibility :(
        TableVisualizer.addRowItem(tableRow, "<th>", items.get(0));
        // All other items
        String openTag = TableVisualizer.getOpenTag(isHeader);
        for (int i = 1; i < items.size(); i++) {
            TableVisualizer.addRowItem(tableRow, openTag, items.get(i));
        }
        tableRow.append("</tr>");
        return tableRow;
    }

    private static String getOpenTag(boolean isHeader){
        return isHeader ? "<th>" : "<td>";
    }

    private static String getCloseTag(String openTag){
        // These are the only two tags supported for rows for this project.
        return openTag.equals("<th>") ? "</th>" : "</td>";
    }

    private static void addRowItem(StringBuilder tableRow, String tag, String item) {
        tableRow.append(tag);
        // Do not allow xss or anything similar
        tableRow.append(HtmlUtils.htmlEscape(item));
        tableRow.append(TableVisualizer.getCloseTag(tag));
    }

}