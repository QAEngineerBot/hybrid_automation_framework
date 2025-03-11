package ui.utility;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import ui.pojos.User;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ExcelReaderUtility {
	public static Iterator<User> readExcelFile(String fileName) {

		File xlsxFile = new File(System.getProperty("user.dir") + "//testData"+ fileName);
		// To read XLSX File -> XSSFWorkbook -> it creates reference
		XSSFWorkbook xssfWorkbook = null;
		List<User> userList = null;
		Row row;
		Cell emailAddressCell;
		Cell passwordCell;
		User user;
		Iterator<Row> rowIterator;
		XSSFSheet xssfSheet;
		try {
			xssfWorkbook = new XSSFWorkbook(xlsxFile);
			userList = new ArrayList<User>();

			// Get the sheet
			xssfSheet = xssfWorkbook.getSheet("LoginTestData");
			// Read the sheet Iterator used here
			rowIterator = xssfSheet.iterator();

			// read data from iterator

			while (rowIterator.hasNext()) {
				row = rowIterator.next();

				emailAddressCell = row.getCell(0);
				passwordCell = row.getCell(1);
				user = new User(emailAddressCell.toString(), passwordCell.toString());
				userList.add(user);
				xssfWorkbook.close();
			}

		} catch (InvalidFormatException | IOException e) {

			e.printStackTrace();
		}
		return userList.iterator();
	}
}
