package CommonUtilities;

	import org.apache.poi.ss.usermodel.*;
	import org.apache.poi.xssf.usermodel.XSSFWorkbook;

	import java.io.FileInputStream;
	import java.io.IOException;
	import java.util.HashMap;
	import java.util.Map;

	public class ExcelUtil {
	    private String excelFilePath;

	    public ExcelUtil(String excelFilePath) {
	        this.excelFilePath = excelFilePath;
	    }

	    public Map<String, String> getTestData(String testcaseID) {
	        Map<String, String> data = new HashMap<>();
	        try (FileInputStream fis = new FileInputStream(excelFilePath);
	             Workbook workbook = new XSSFWorkbook(fis)) {

	            Sheet sheet = workbook.getSheetAt(0);
	            int lastRowNum = sheet.getLastRowNum();

	            for (int i = 1; i <= lastRowNum; i++) {
	                Row row = sheet.getRow(i);
	                Cell idCell = row.getCell(0);

	                if (idCell.getStringCellValue().equals(testcaseID)) {
	                    data.put("firstName", row.getCell(1).getStringCellValue());
	                    data.put("lastName", row.getCell(2).getStringCellValue());
	                    data.put("address", row.getCell(3).getStringCellValue());
	                    data.put("city", row.getCell(4).getStringCellValue());
	                    data.put("state", row.getCell(5).getStringCellValue());
	                    data.put("zipCode", row.getCell(6).getStringCellValue());
	                    data.put("phone", row.getCell(7).getStringCellValue());
	                    data.put("ssn", row.getCell(8).getStringCellValue());
	                    data.put("username", row.getCell(9).getStringCellValue());
	                    data.put("password", row.getCell(10).getStringCellValue());
	                    data.put("confirmPassword", row.getCell(11).getStringCellValue());
	                    break;
	                }
	            }
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	        return data;
	    }
	}

}
