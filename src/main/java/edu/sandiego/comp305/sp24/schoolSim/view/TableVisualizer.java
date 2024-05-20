package edu.sandiego.comp305.sp24.schoolSim.view;

import edu.sandiego.comp305.sp24.schoolSim.model.DatabaseItem;
import edu.sandiego.comp305.sp24.schoolSim.model.DatabaseTable;
import org.springframework.web.util.HtmlUtils;

import java.util.List;

public class TableVisualizer {
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
        StringBuilder tableHTML = new StringBuilder("<table class=\"table is-fullwidth is-striped\"><thead>");
        tableHTML.append(TableVisualizer.rowWrap(true, headers, table.getTableName()));
        tableHTML.append("</thead><tbody>");
        for (DatabaseItem item : items) {
            tableHTML.append(TableVisualizer.rowWrap(false,item.getStringList(), table.getTableName()));
        }
        tableHTML.append("</tbody></table>");
        return tableHTML.toString();
    }

    public static String generateErrorContent(String message) {
        StringBuilder builder = new StringBuilder("<div class=\"notification is-danger\"><button class=\"delete\"></button>");
        builder.append(message);
        builder.append("</div>");
        return builder.toString();
    }

    private static String generateDeletionPath(String tableName, String id) {
        String prefix = switch (tableName) {
            case "Person", "Student", "Employee", "Faculty", "Alumni" -> "/person/";
            default -> "#";
        };
        if (prefix.equals("#")) {
            // Room and Building and Department are Read Only.
            return "#";
        } else {
            return prefix+id+"?type="+tableName.toLowerCase();
        }
    }

    private static StringBuilder rowWrap(boolean isHeader, List<String> items, String tableName) {
        StringBuilder tableRow = new StringBuilder("<tr>");
        // First item will always be row header
        // .getFirst() introduced JDK21. Removed for compatibility :(
        TableVisualizer.addRowItem(tableRow, "<th class=\"is-size-7\">", items.get(0));
        // All other items
        String openTag = TableVisualizer.getOpenTag(isHeader);
        for (int i = 1; i < items.size(); i++) {
            TableVisualizer.addRowItem(tableRow, openTag, items.get(i));
        }
        String deletePath = generateDeletionPath(tableName, items.get(0));
        if (!deletePath.equals("#")) {
            tableRow.append(openTag);
            if (!isHeader) {
                tableRow.append("<a href=\"");
                tableRow.append(TableVisualizer.generateDeletionPath(tableName, items.get(0)));
                tableRow.append("\" class=\"button is-small is-danger is-rounded\">x</a>");
            } else {
                tableRow.append("Delete");
            }
            tableRow.append(getCloseTag(openTag));
        }
        tableRow.append("</tr>");
        return tableRow;
    }

    private static String getOpenTag(boolean isHeader){
        return isHeader ? "<th class=\"is-size-7\">" : "<td class=\"is-size-7\">";
    }

    private static String getCloseTag(String openTag){
        // These are the only two tags supported for rows for this project.
        return openTag.equals(getOpenTag(true)) ? "</th>" : "</td>";
    }

    private static void addRowItem(StringBuilder tableRow, String tag, String item) {
        tableRow.append(tag);
        // Do not allow xss or anything similar
        tableRow.append(HtmlUtils.htmlEscape(item));
        tableRow.append(TableVisualizer.getCloseTag(tag));
    }

}