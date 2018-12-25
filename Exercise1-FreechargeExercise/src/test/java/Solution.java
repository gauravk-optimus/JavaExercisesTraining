import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class Solution {

	public static void main(String[] args) throws IOException {
		File file = new File(
				"D://Automation//workspace//Exercise1-FreechargeExercise//Test.xlsx");
		FileInputStream fs = new FileInputStream(file);
		Workbook wb = new XSSFWorkbook(fs);
		Sheet sh = wb.getSheet("Sheet1");
		int rowCount = sh.getLastRowNum() - sh.getFirstRowNum() + 1;

		for (int i = 0; i < rowCount; i++) {
			Row row = sh.getRow(i);
			for(int j = 0; j<row.getLastCellNum(); j++){
				System.out.print(row.getCell(j).getStringCellValue()+"|");
			}System.out.println();
		}
	}
}