package edu.sandiego.comp305.sp24.schoolSim.controller;

import edu.sandiego.comp305.sp24.schoolSim.model.DatabaseItem;
import edu.sandiego.comp305.sp24.schoolSim.model.DatabaseTable;

import java.util.List;

class TableVisualizer {
        public static String generateTableView(DatabaseTable table, List<DatabaseItem> items) {
            List<String> headers = table.getColumnNames();
            StringBuilder tableHTML = new StringBuilder("<table class=\"table\">\n\t<thead>\n\t");
            tableHTML.append(TableVisualizer.rowWrap(true, headers));
            tableHTML.append("</thead>\n<tbody>\n");
            for (DatabaseItem item : items) {
                tableHTML.append(TableVisualizer.rowWrap(false,item.getStringList()));
            }
            tableHTML.append("</tbody>\n");
            return tableHTML.toString();
        }

        private static StringBuilder rowWrap(boolean isHeader, List<String> items) {
            StringBuilder tableRow = new StringBuilder("<tr>\n");
            // First item will always be row header
            tableRow.append("\t<th>");
            tableRow.append(items.getFirst());
            tableRow.append("</th>\n");
            // All other items
            String openTag, closeTag;
            if (isHeader){
                openTag = "<th>";
                closeTag = "</th>\n";
            } else {
                openTag = "<td>";
                closeTag = "</td>\n";
            }
            for (int i = 1; i < items.size(); i++) {
                tableRow.append("\t");
                tableRow.append(openTag);
                tableRow.append(items.get(i));
                tableRow.append(closeTag);
            }
            tableRow.append("</tr>\n");
            return tableRow;
        }

}