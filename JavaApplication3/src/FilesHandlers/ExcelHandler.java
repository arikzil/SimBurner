/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FilesHandlers;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 * Created by arikzil on 23/12/16.
 *
 * @Descripton Used for handling excel files.
 */
public class ExcelHandler extends BaseHandler {

    @SuppressWarnings("empty-statement")
    public ExcelHandler(String dir) {
        workingDirectory = dir;
        this.fileFormats = new String[]{"xlsx", "xls"};

    }

    /**
     * used for getting the content of the selected file
     *
     * @param file The name of the file to display
     * @param sheet The sheet number
     * @return The content of given sheet
     * @throws java.io.FileNotFoundException
     */
    public ArrayList<String[]> getFileCtBySheet(String file, int sheet) throws FileNotFoundException, IOException {
        ArrayList<String[]> list = new ArrayList<String[]>();
        ArrayList<String> row = new ArrayList<>();
        File selectedFile = new File(this.workingDirectory.concat(file));
        FileInputStream inputStream = new FileInputStream(selectedFile);

        XSSFWorkbook workbook = new XSSFWorkbook(inputStream);
        Sheet firstSheet = workbook.getSheetAt(sheet);
        Iterator<Row> iterator = firstSheet.iterator();

        while (iterator.hasNext()) {
            Row nextRow = iterator.next();
            Iterator<Cell> cellIterator = nextRow.cellIterator();

            while (cellIterator.hasNext()) {
                Cell cell = cellIterator.next();

                switch (cell.getCellType()) {
                    case Cell.CELL_TYPE_STRING:
                        row.add((String) cell.getStringCellValue());
                        break;
                    case Cell.CELL_TYPE_BOOLEAN:
                        row.add("" + cell.getBooleanCellValue());
                        break;
                    case Cell.CELL_TYPE_NUMERIC:
                        row.add("" + cell.getNumericCellValue());
                        break;
                }

            }
            list.add(row.toArray(new String[list.size()]));
            row = new ArrayList<>();
        }

        workbook.close();
        inputStream.close();

        return list;
    }

    /**
     * @param row The row to return from the given file
     * @param file The selected file for data extraction
     * @param sheet The sheet number
     * @return the current row
     * @throws java.io.IOException
     */
    public String[] getRowByNum(int row, String file, int sheet) throws IOException {
        return getFileCtBySheet(file, sheet).get(row - 1);
    }

    public String getSpecificCol(String file, int sheet, int row, int col) throws IOException {
        return getFileCtBySheet(file, sheet).get(row - 1)[col];

    }

    public String[] getColByNum(int col, String file, int sheet) throws IOException {
        ArrayList<String[]> sheetRows = getFileCtBySheet(file, sheet);
        ArrayList<String> resRow = new ArrayList<>();

        for (int i = 0; i < sheetRows.size(); i++) {
            try {
                resRow.add(sheetRows.get(i)[col]);
            } catch (ArrayIndexOutOfBoundsException e) {
                resRow.add("");
            }

        }

        return resRow.toArray(new String[resRow.size()]);

    }

    /**
     * returns the content of a row by a give row.If the row is 0 then it will
     * return
     *
     * @param col The column that is used to check the suitable file.If the
     * given column is 0 then it will check every column
     * @return the current row
     */
    public String getRowByColumn(int col) {
        return "";
    }

    public String[] getRowByVal(String file, int sheet, String value) throws IOException {
        ArrayList<String[]> sheetRows = getFileCtBySheet(file, sheet);
        ArrayList<String> resRow = new ArrayList<>();

        for (int i = 0; i < sheetRows.size(); i++) {
            for (int j = 0; j < sheetRows.get(i).length; j++) {
                if (value.equals(sheetRows.get(i)[j])) {
                    return sheetRows.get(i);
                }
            }

        }
           
        System.out.println("A row with the given value doesnt exists");
        return null;

    }

}
