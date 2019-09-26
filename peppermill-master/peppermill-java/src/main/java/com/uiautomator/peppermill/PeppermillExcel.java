package com.uiautomator.peppermill;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.FormulaEvaluator;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.FileOutputStream;

public class PeppermillExcel {

    private static int CurrentRow = 1;
    private static XSSFRow row;
    private static XSSFSheet sh;
    private static XSSFWorkbook wb;

    public PeppermillReporter reporter;

    /**
     * Current excel sheet to retrieve data
     *
     * @param filename
     * @param sheetname
     * @return
     */
    private void setCurrentSheet(String filename, String sheetname) {
        try {
            FileInputStream fs = new FileInputStream(filename);
            wb = new XSSFWorkbook(fs);
            // TODO Add validation for sheet existence.
            sh = wb.getSheet(sheetname);
            fs.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Get's the used row count in current sheet
     *
     * @return
     */
    public int getRowCount() {
        try {
            return sh.getPhysicalNumberOfRows();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    /**
     * Get's used header count
     *
     * @return
     */
    public int getColumnCount() {
        try {
            row = sh.getRow(0);
            return (int) row.getLastCellNum();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    /**
     * Get's value from a given column in the sheet.
     *
     * @param columnvalue
     * @return
     */
    public String getValue(String columnvalue) {
        try {
            int rownum = getCurrentRow();
            row = sh.getRow(rownum);
            XSSFRow toprow = sh.getRow(0);
            FormulaEvaluator evaluator = wb.getCreationHelper().createFormulaEvaluator();
            for (int columnnum = 0; columnnum < toprow.getLastCellNum(); columnnum++) {

                DataFormatter formatter = new DataFormatter();
                Cell cell = toprow.getCell(columnnum);
                String columnname = formatter.formatCellValue(cell);

                if (columnvalue.equals(columnname)) {
                    Cell cell2 = row.getCell(columnnum);
                    String value = formatter.formatCellValue(cell2, evaluator);
                    reporter.event("INFO", "Read Value from data sheet", "Value '" + value + "' is read from data table column '" + columnname + "'");
                    return value;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        reporter.event("INFO", "Read Value from data sheet", "Column '" + columnvalue + "' is not found in sheet '" + sh.getSheetName());
        return "No Column Found";
    }

    /**
     * Load/set the current test data file source with path.
     *
     * @param fileName
     * @param sheetName
     */
    public void loadDataSheet(String fileName, String sheetName) {
        try {
            String testDataLocation = PeppermillGlobals.testdatapath;
            setCurrentSheet((testDataLocation + fileName), sheetName);
            reporter.event("INFO", "Load data sheet", "Test data sheet '" + sheetName + "' is loaded from '" + testDataLocation);
            setCurrentRow(1);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Write's a value to a cell
     *
     * @param filename
     * @param Sheetname
     * @param rownum
     * @param columnvalue
     * @param txttowrite
     * @return
     * @throws Exception
     */
    public String setValue(String filename, String Sheetname, int rownum, String columnvalue, String txttowrite) throws Exception {
        row = sh.getRow(rownum);
        XSSFRow toprow = sh.getRow(0);
        for (int columnnum = 0; columnnum < toprow.getLastCellNum(); columnnum++) {

            DataFormatter formatter = new DataFormatter();
            Cell cell = toprow.getCell(columnnum);
            String columnname = formatter.formatCellValue(cell);

            if (columnvalue.equals(columnname)) {
                Cell cells = row.getCell(columnnum);
                cells.setCellValue(txttowrite);
                FileOutputStream fos = new FileOutputStream(PeppermillGlobals.testdatapath + "" + filename + "");
                wb.write(fos);
                System.out.println(txttowrite);
                fos.close();
                break;
            }
        }
        return txttowrite;
    }

    /**
     * Get's current row of current sheet
     *
     * @return
     */
    public int getCurrentRow() {
        return CurrentRow;
    }

    /**
     * Set's current row for the sheet
     *
     * @param currentRow
     */
    public void setCurrentRow(int currentRow) {
        reporter.event("INFO", "setCurrentRow for data sheet", "Datatable current row is set to - " + currentRow);
        CurrentRow = currentRow;
    }
}
