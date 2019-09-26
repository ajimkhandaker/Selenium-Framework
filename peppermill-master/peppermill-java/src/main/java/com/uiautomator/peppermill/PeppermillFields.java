package com.uiautomator.peppermill;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.util.Arrays;
import java.util.List;

/**
 * Provides neccessery methods to perform action on AUT(Application under test)
 */
public class PeppermillFields {

    /**
     * WebDriver type place holder to be assigned with user created WebDriver instance.
     */
    public WebDriver driver;

    /**
     * PeppermillReporter type place holder to be assigned with user created PeppermillReporter instance.
     */
    public PeppermillReporter reporter;
    public PeppermillWait Wait;
    public PeppermillActions actions;

    /**
     * Sets a value to element
     *
     * @param element   The element
     * @param fieldName Element label
     * @param Value     Text to send
     */
    public void setValue(WebElement element, String fieldName, String Value) {
        try {
            Wait.syncHandler(driver, element);
            actions.Fire(driver).moveToElement(element);
            element.sendKeys(Value);
            reporter.event("PASS", "Enter value in - " + fieldName, Value + " - is entered");
        } catch (Exception e) {
            reporter.event("FAIL", "Set Value Exception", e.getMessage() + "*****" + Arrays.toString(e.getStackTrace()));
            //e.printStackTrace();
        }
    }

    /**
     * Check's text for given element
     *
     * @param element
     * @param fieldName
     * @param Value
     * @return
     */
    public boolean verifyText(WebElement element, String fieldName, String Value) {
        Wait.syncHandler(driver, element);
        if (element.getText().trim().toLowerCase().contains(Value.trim().toLowerCase())) {
            reporter.event("PASS", "Verify value in - " + fieldName, Value + " - is displayed");
            return true;
        } else {
            reporter.event("FAIL", "Verify value in - " + fieldName, Value + " - is NOT displayed");
            return false;
        }
    }

    /**
     * Check's attribute value of the given element
     *
     * @param element
     * @param fieldName
     * @param attributeName
     * @param Value
     * @return
     */
    public boolean verifyAttribute(WebElement element, String fieldName, String attributeName, String Value) {
        Wait.syncHandler(driver, element);
        String attbText = element.getAttribute(attributeName);
        if (attbText.trim().toLowerCase().contains(Value.trim().toLowerCase())) {
            reporter.event("PASS", "Verify attribute value in - " + fieldName, Value + " - is displayed");
            return true;
        } else {
            reporter.event("FAIL", "Verify attribute value in - " + fieldName, Value + " - is NOT displayed [| Actual value  - '" + attbText + "'");
            return false;
        }
    }

    /**
     * Send Keyboard operations
     *
     * @param element
     * @param fieldName
     * @param Value
     */
    public void sendKeyBoardOperation(WebElement element, String fieldName, String Value) {
        Wait.syncHandler(driver, element);
        switch (Value.trim().toLowerCase()) {
            case "tab":
                element.sendKeys(Keys.TAB);
                break;
            case "pagedown":
                element.sendKeys(Keys.PAGE_DOWN);
                break;
            case "pageup":
                element.sendKeys(Keys.PAGE_UP);
                break;
            case "enter":
                element.sendKeys(Keys.ENTER);
                break;
            default:
                break;
        }
    }

    /**
     * Select's a value from list/combo element
     *
     * @param element
     * @param fieldName
     * @param ItemValue
     */
    public void selectListByText(WebElement element, String fieldName, String ItemValue) {
        Wait.syncHandler(driver, element);
        boolean blnFound = false;
        Select oSelect = new Select(element);
        try {
            oSelect.selectByVisibleText(ItemValue);
            reporter.event("PASS", "Select " + fieldName + " Value - " + ItemValue, "is Selected");
        } catch (Exception e) {
            reporter.event("FAIL", "Select " + fieldName + " Value - " + oSelect.getFirstSelectedOption().getText(), "is NOT Selected");
        }
    }

    public void selectListByValue(WebElement element, String fieldName, String ItemValue) {
        Wait.syncHandler(driver, element);
        boolean blnFound = false;
        Select oSelect = new Select(element);
        try {
            oSelect.selectByValue(ItemValue);
            reporter.event("PASS", "Select " + fieldName + " Value - " + ItemValue, "is Selected");
        } catch (Exception e) {
            reporter.event("FAIL", "Select " + fieldName + " Value - " + oSelect.getAllSelectedOptions().toString(), "is NOT Selected");
        }
    }

    /**
     * Verify Selected value from list/combo element
     *
     * @param element
     * @param fieldName
     * @param ItemValue
     */
    public void verifySelectedListByText(WebElement element, String fieldName, String ItemValue) {
        Wait.syncHandler(driver, element);
        boolean blnFound = false;
        Select oSelect = new Select(element);
        List<WebElement> elementCount = oSelect.getAllSelectedOptions();
        int iSize = elementCount.size();
        for (WebElement oelement : elementCount) {
            String sValue = oelement.getText();
            String sText = oelement.getAttribute("Value");
            if (sValue.trim().toLowerCase().contains(ItemValue.trim().toLowerCase())) {
                reporter.event("PASS", "verifySelectedList " + fieldName + " Value - " + sValue, "is Selected");
                blnFound = true;
                break;
            }
        }
        if (!blnFound) {
            reporter.event("FAIL", "verifySelectedList " + fieldName + " Value - " + ItemValue, "is NOT Selected");
        }

    }

    /**
     * Select's a value from list/combo element by index
     *
     * @param element
     * @param fieldName
     * @param ItemIndex
     */
    public void selectListByIndex(WebElement element, String fieldName, int ItemIndex) {
        try {
            Wait.syncHandler(driver, element);
            Select oSelect = new Select(element);
            List<WebElement> elementCount = oSelect.getOptions();
            oSelect.selectByIndex(ItemIndex);
            String sValue = elementCount.get(ItemIndex).getText();
            reporter.event("PASS", "Select " + fieldName + " Value - " + sValue, "is Selected");
        } catch (Exception e) {
            reporter.event("FAIL", "Select " + fieldName + " Index as - " + ItemIndex, "is NOT Selected");
            reporter.event("FAIL", "ERROR REPORT", Arrays.toString(e.getStackTrace()));
        }
    }

    /**
     * Click's element
     *
     * @param element
     * @param fieldName
     */
    public void clickIt(WebElement element, String fieldName) {
        Wait.syncHandler(driver, element);
        actions.Fire(driver).moveToElement(element).click().build().perform();
        reporter.event("PASS", "Click - " + fieldName, fieldName + " - is Clicked");
    }

    /**
     * Clears the text
     *
     * @param element
     */
    public void clear(WebElement element) {
        try {
            Wait.syncHandler(driver, element);
            element.clear();
        } catch (Exception e) {
            System.out.println(e.getCause().toString());
        }
    }

    /**
     * Check's whether the given element is displayed.
     *
     * @param element
     * @return
     */
    public boolean isDisplayed(WebElement element) {
        try {
            return element.isDisplayed();
        } catch (Exception e) {
            System.out.println(e.getCause().toString());
            return false;
        }
    }

    /**
     * Get cell data of table element
     *
     * @param element
     * @param rowIndex
     * @param colIndex
     * @return
     */
    public String tblGetCellData(WebElement element, int rowIndex, int colIndex) {
        Wait.syncHandler(driver, element);
        if (tblCheckForRecords(element)) {
            WebElement allRows = element.findElements(By.xpath(".//tbody/tr")).get(rowIndex);
            return allRows.findElements(By.xpath(".//td")).get(colIndex).getText();
        }
        return PeppermillGlobals.tblCurrentNoRecordsText;
    }

    /**
     * Get all data from the element with replaced new line, tab and spaces.
     *
     * @param element
     * @return
     */
    public String getAllText(WebElement element) {
        Wait.syncHandler(driver, element);
        String cellData = element.getText();
        cellData = cellData.replace("\n", "");
        cellData = cellData.replace("\t", "");
        cellData = cellData.replaceAll(" ", "");
        return cellData;
    }

    /**
     * Verify all data from the element with replaced new line, tab and spaces.
     *
     * @param element
     * @param textValue
     * @return
     */
    public boolean verifyAllText(WebElement element, String textValue) {
        Wait.syncHandler(driver, element);
        String cellData = element.getText();
        cellData = cellData.replace("\n", "");
        cellData = cellData.replace("\t", "");
        cellData = cellData.replaceAll(" ", "");

        textValue = textValue.replace("\n", "");
        textValue = textValue.replace("\t", "");
        textValue = textValue.replaceAll(" ", "");

        if (textValue.trim().toLowerCase().equals(cellData.trim().toLowerCase())) {
            reporter.event("PASS", "Verify Cell value - " + textValue, "\n value - '" + textValue + "' is displayed'");
        } else {
            reporter.event("FAIL", "Verify Cell value - " + textValue, "\n value - '" + cellData + "' is displayed'");
            return false;
        }
        return true;
    }

    /**
     * Verify cell data of table element
     *
     * @param element
     * @param rowIndex
     * @param colIndex
     * @param cellValue
     * @return
     */
    public String tblVerifyCellData(WebElement element, int rowIndex, int colIndex, String cellValue) {
        Wait.syncHandler(driver, element);
        if (tblCheckForRecords(element)) {
            WebElement allRows = element.findElements(By.xpath(".//tbody/tr")).get(rowIndex);
            String cellData = allRows.findElements(By.xpath(".//td")).get(colIndex).getText();
            if (cellValue.trim().toLowerCase().equals(cellData.trim().toLowerCase())) {
                reporter.event("PASS", "Verify Cell value - " + cellValue, "value - '" + cellValue + "' is displayed in row - '" + rowIndex + "'");
            } else {
                reporter.event("FAIL", "Verify Cell value - " + cellValue, "value - '" + cellData + "' is displayed in row - '" + rowIndex + "'");
            }
            return cellData;
        }
        return "";
    }

    /**
     * Get column index of table element
     *
     * @param element
     * @param ColumnName
     * @return
     */
    public int tblGetColumnIndex(WebElement element, String ColumnName) {
        Wait.syncHandler(driver, element);
        List<WebElement> allHeaders = element.findElements(By.tagName("th"));
        int i = 0;
        for (WebElement header : allHeaders) {
            if (header.getText().trim().equals(ColumnName)) {
                return i;
            }
            i = i + 1;
        }
        return 0;
    }

    /**
     * Get row index of table element
     *
     * @param element
     * @param rowValue
     * @return
     */
    public int tblGetRowIndex(WebElement element, String rowValue) {
        Wait.syncHandler(driver, element);
        List<WebElement> allRows = element.findElements(By.xpath(".//tbody/tr"));
        int i = 0;
        for (WebElement row : allRows) {
            if (row.getText().trim().contains(rowValue)) {
                return i;
            }
            i = i + 1;
        }
        return 0;
    }

    /**
     * Get row count of table element
     *
     * @param element
     * @return
     */
    public int tblGetRowCount(WebElement element) {
        Wait.syncHandler(driver, element);
        if (tblCheckForRecords(element)) {
            List<WebElement> allRows = element.findElements(By.xpath(".//tbody/tr"));
            return allRows.size();
        }
        return 0;
    }

    /**
     * Check for any records available in a table.
     *
     * @param element
     * @return
     */
    private boolean tblCheckForRecords(WebElement element) {
        Wait.syncHandler(driver, element);
        PeppermillGlobals.tblCurrentNoRecordsText = "";
        String tblText = element.getText();
        for (String text : PeppermillGlobals.tblNoRecordsText) {
            if (tblText.trim().toLowerCase().contains(text)) {
                PeppermillGlobals.tblCurrentNoRecordsText = text;
                reporter.event("INFO", "Check for number of rows", "There are currently no records to display.");
                return false; // retrun false for no results found in the table.
            }
        }
        return true;
    }

    /**
     * @param element
     * @param rowIndex
     * @param colIndex
     */
    public void tblClickCellLink(WebElement element, int rowIndex, int colIndex) {
        Wait.syncHandler(driver, element);
        WebElement table = element;
        if (tblCheckForRecords(table)) {
            WebElement allRows = element.findElements(By.xpath(".//tbody/tr")).get(rowIndex);
            WebElement allCols = allRows.findElements(By.xpath(".//td")).get(colIndex);
            clickIt(allCols.findElement(By.xpath(".//a")), "Table Cell - " + colIndex + " in row - " + rowIndex);
        }
    }

    /**
     * @param element
     * @param ColumnName
     * @return
     */
    public boolean tblVerifyHeaderCellData(WebElement element, String ColumnName) {
        Wait.syncHandler(driver, element);
        List<WebElement> allHeaders = element.findElements(By.tagName("th"));
        int i = 0;
        for (WebElement header : allHeaders) {
            String headertext = header.getText();
            if (headertext.trim().equals(ColumnName)) {
                reporter.event("PASS", "Verify Column value - " + ColumnName, "value - '" + headertext + "' is displayed in cell - '" + i + "' [index starts from '0']");
                return true;
            }
            i = i + 1;
        }
        reporter.event("FAIL", "Verify Column value - " + ColumnName, "value - '" + ColumnName + "' is NOT displayed in cell - '" + i + "' [index starts from '0']");
        return false;
    }
}
