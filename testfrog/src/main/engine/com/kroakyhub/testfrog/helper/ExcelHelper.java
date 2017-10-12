package com.kroakyhub.testfrog.helper;

import java.io.FileInputStream;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelHelper {
	
	public String path;
	FileInputStream fis;
	XSSFWorkbook workbook;
	XSSFSheet sheet;
	XSSFRow row;
	XSSFCell cell;

	public ExcelHelper(String path) {
		this.path = path;
		try {
			fis = new FileInputStream(path);
			workbook = new XSSFWorkbook(fis);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public String getCellData(String sheetName, int rowNum, String colName) {
		try {
			int col_Num = 0;
			int index = workbook.getSheetIndex(sheetName);
			sheet = workbook.getSheetAt(index);
			XSSFRow row = sheet.getRow(0);
			for (int i = 0; i < row.getLastCellNum(); i++) {
				if (row.getCell(i).getStringCellValue().equals(colName)) {
					col_Num = i;
				}
			}
			row = sheet.getRow(rowNum - 1);
			XSSFCell cell = row.getCell(col_Num);
			if (cell.getCellType() == Cell.CELL_TYPE_STRING) {
				return cell.getStringCellValue();
			} else if (cell.getCellType() == Cell.CELL_TYPE_NUMERIC) {
				return String.valueOf(cell.getNumericCellValue());
			} else if (cell.getCellType() == Cell.CELL_TYPE_BOOLEAN) {
				return String.valueOf(cell.getBooleanCellValue());
			} else if (cell.getCellType() == Cell.CELL_TYPE_BLANK) {
				return "";
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}
	
	public String getCellData(String sheetName, int rowNum, int colNum) {
		try {
			int index = workbook.getSheetIndex(sheetName);
			sheet = workbook.getSheetAt(index);
			XSSFRow row = sheet.getRow(0);
			row = sheet.getRow(rowNum - 1);
			XSSFCell cell = row.getCell(colNum-1);
			if (cell.getCellType() == Cell.CELL_TYPE_STRING) {
				return cell.getStringCellValue();
			} else if (cell.getCellType() == Cell.CELL_TYPE_NUMERIC) {
				return String.valueOf(Math.round(cell.getNumericCellValue()));
			} else if (cell.getCellType() == Cell.CELL_TYPE_BOOLEAN) {
				return String.valueOf(cell.getBooleanCellValue());
			} else if (cell.getCellType() == Cell.CELL_TYPE_BLANK) {
				return "";
			}
			

		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}


	public int getRowCount(String sheetName) {
		try {
			int index = workbook.getSheetIndex(sheetName);
			if (index == -1) {
				return 0;
			} else {
				sheet = workbook.getSheetAt(index);
				int number = sheet.getLastRowNum() + 1;
				return number;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	public int getColumnCount(String sheetName) {
		try {
			int index = workbook.getSheetIndex(sheetName);
			if (index == -1) {
				return 0;
			} else {
				sheet = workbook.getSheet(sheetName);
				row = sheet.getRow(0);
				return row.getLastCellNum();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}
	
	public Set<String> getUniqueColumnItem(String sheetName, String colName){
		try {
			
			Set<String> uniqueItems = new HashSet<String>(); 
			int col_Num = 0;
			int rowNum;
			String cellValue = null;
			int index = workbook.getSheetIndex(sheetName);
			sheet = workbook.getSheetAt(index);
			XSSFRow row = sheet.getRow(0);
			for (int i = 0; i < row.getLastCellNum(); i++) {
				if (row.getCell(i).getStringCellValue().equals(colName)) {
					col_Num = i;
				}
			}
			
			int rowCount = getRowCount(sheetName);
			
			for(rowNum = 2 ; rowNum <= rowCount ; rowNum++){
				row = sheet.getRow(rowNum - 1);
				XSSFCell cell = row.getCell(col_Num);
				if (cell.getCellType() == Cell.CELL_TYPE_STRING) {
					cellValue = cell.getStringCellValue();
				} else if (cell.getCellType() == Cell.CELL_TYPE_NUMERIC) {
					cellValue = String.valueOf(cell.getNumericCellValue());
				} else if (cell.getCellType() == Cell.CELL_TYPE_BOOLEAN) {
					cellValue = String.valueOf(cell.getBooleanCellValue());
				} else if (cell.getCellType() == Cell.CELL_TYPE_BLANK) {
					cellValue = "";
				}
				uniqueItems.add(cellValue);
			}
		
			return uniqueItems;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
		
	}

}
