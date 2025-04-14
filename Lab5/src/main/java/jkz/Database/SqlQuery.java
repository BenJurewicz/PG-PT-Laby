package jkz.Database;

import jkz.Logging.Log;

import java.util.Arrays;
import java.util.List;

public class SqlQuery {
	private String sqlQuery;
	private List<?> result;

	public SqlQuery(String sqlQuery) {
		this.sqlQuery = sqlQuery;
	}

	public SqlQuery(String sqlQuery, List<?> result) {
		this.sqlQuery = sqlQuery;
		this.result = result;
	}

	public void prettyPrint(String... colNames) {
		if (result == null || result.isEmpty()) {
			Log.println("No results found.");
			return;
		}

		int[] colWidths = calcColWidth(result, colNames);
		int colWidth = calcColSum(colWidths);

		printQuery(sqlQuery);
		printSeparator(colWidth);
		printColNames(colNames, colWidths, colWidth, sqlQuery);
		printSeparator(colWidth);
		printRows(result, colWidths);
		printSeparator(colWidth);
	}

	public String getQuery() {
		return sqlQuery;
	}

	public List<?> getResult() {
		return result;
	}

	private static int[] calcColWidth(List<?> result, String... colNames) {
		int[] colWidths = new int[colNames.length];
		for (int i = 0; i < colNames.length; i++) {
			colWidths[i] = colNames[i].length();
		}

		for (Object row : result) {
			if (row instanceof Object[]) {
				Object[] columns = (Object[]) row;
				int limit = Math.min(columns.length, colWidths.length);
				for (int i = 0; i < limit; i++) {
					colWidths[i] = Math.max(colWidths[i], columns[i].toString().length());
				}
			} else {
				colWidths[0] = Math.max(colWidths[0], row.toString().length());
			}
		}

		return colWidths;
	}

	private static int calcColSum(int[] colWidths) {
		return Arrays.stream(colWidths).sum() + colWidths.length * 4;
	}

	private static void printSeparator(int sepLen) {
		Log.println("-".repeat(sepLen));
	}

	private static void printQuery(String sqlQuery) {
		Log.println("Query:", sqlQuery);
	}

	private static void printColNames(String[] colNames, int[] colWidths, int colWidth, String sqlQuery) {
		for (int i = 0; i < colNames.length; i++) {
			Log.print(String.format("%-" + colWidths[i] + "s\t", colNames[i]));
		}
		Log.println();
	}

	public static void printRows(List<?> result, int[] colWidths) {
		for (Object row : result) {
			if (row instanceof Object[] columns) {
				int limit = Math.min(columns.length, colWidths.length); // Ensure no out-of-bounds access
				for (int i = 0; i < limit; i++) {
					Log.print(String.format("%-" + colWidths[i] + "s\t", columns[i]));
				}
				Log.println();
			} else {
				Log.println(String.format("%-" + colWidths[0] + "s", row.toString()));
			}
		}
	}
}
