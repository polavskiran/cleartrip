package utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import pages.BaseClass;

public class ExcelUtil extends BaseClass {

	Workbook testData = null;
	ArrayList<String> dataList = new ArrayList<String>();

	ExcelUtil() {

		String dataPath = System.getProperty("user.dir") + "//src//test//resources//TestData.xlsx";
		try {
			File dataFile = new File(dataPath);
			FileInputStream inputStream = new FileInputStream(dataFile);

			if (dataPath.endsWith("xlsx"))
				testData = new XSSFWorkbook(inputStream);
			else if (dataPath.endsWith("xls"))
				testData = new HSSFWorkbook(inputStream);

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public ArrayList<String> readTestData(String sheetName,String testCaseName) throws Exception {

		int numOfSheets = testData.getNumberOfSheets();

		for (int i = 0; i < numOfSheets; i++) {
			System.out.println("Sheet Name: " + testData.getSheetName(i));

			if (testData.getSheetName(i).equalsIgnoreCase(sheetName)) {
				Sheet sheet = testData.getSheetAt(i);

				Iterator<Row> sheetRow = sheet.rowIterator();
				Row headerRow = sheet.getRow(0); // Assuming first row is header row
				Iterator<Cell> headerCell = headerRow.cellIterator();

				int col = 0;
				int k = 0;

				while (headerCell.hasNext()) {
					Cell cellValue = headerCell.next();
					if (cellValue.getStringCellValue().equalsIgnoreCase(testCaseName)) {
						col = k;
					}

					k++;
				}

				System.out.println("Travel Class is at column: " + col);

				// logic to read row data for a specific column.
				while (sheetRow.hasNext()) {
					Row row = sheetRow.next();
					String cellText = row.getCell(col).getStringCellValue();
					if (cellText.equalsIgnoreCase("Mumbai")) {
						Iterator<Cell> dataCell = row.cellIterator();
						while (dataCell.hasNext()) {
							Cell value = dataCell.next();
							CellType cellType = value.getCellType();
							switch (cellType) {
							case STRING:
								dataList.add(value.getStringCellValue());
								break;
								
							case NUMERIC:
									dataList.add(String.valueOf(value.getNumericCellValue()));
								break;
								
							case BOOLEAN:
								dataList.add(String.valueOf(value.getNumericCellValue()));
								break;

							default:
								break;
							}
						}
					}
				}
			}
		}

		return dataList;
	}

	public static void main(String[] args) throws Exception {

		ExcelUtil util = new ExcelUtil();
	}
}