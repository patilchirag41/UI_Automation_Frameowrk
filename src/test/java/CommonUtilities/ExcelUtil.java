package CommonUtilities;

import com.codoid.products.fillo.Recordset;

import io.cucumber.java.Before;
import io.cucumber.java.Scenario;

public class ExcelUtil {
	public static String sheetName = null;
	public static String InputDataWorkbook = null;
	public static String InputDataSheetPath = null;

	public static Recordset getTestData(String TestCaseID) {
		InputDataWorkbook = CommonMethods.getProperty("config", "InputDataWorkbook");
		InputDataSheetPath = "src/test/resources/Test Data/" + InputDataWorkbook + ".xlsx";
		if (TestCaseID.startsWith("Registration")) {
			sheetName = CommonMethods.getProperty("config", "sheetName_Registration");
		} else
			System.err.println("INVALID SHEET NAME");

		String strQuery = "Select * from " + sheetName + " WHERE TestCaseID = '" + TestCaseID+"'";
		System.out.println(strQuery);
		Recordset Recordset = CommonMethods.createExcelRecordset(strQuery, InputDataSheetPath);
		return Recordset;
	}
}
