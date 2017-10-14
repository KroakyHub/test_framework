package com.kroakyhub.testfrog;

import com.kroakyhub.testfrog.helper.ExcelHelper;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        ExcelHelper helper = new ExcelHelper("C://Users//E6430//Desktop//GitHub//roostify//roostify-test-configuration.xlsx");
        helper.writeCellValue("Testcase", 2, "Status", "Poochi");
        helper.setFont("Testcase", 2, "Status", "green", false, false);
        helper.setCellColour("Testcase", 2, "Status", "Green");
    }
}
