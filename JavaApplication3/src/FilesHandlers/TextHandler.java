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

//word
import org.apache.poi.xwpf.usermodel.XWPFDocument;

//excel
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 *
 * @author arikzil
 */
public class TextHandler extends BaseHandler {

    public TextHandler(String dir) {
        workingDirectory = dir;
        this.fileFormats = new String[]{"txt"};

    }

//    public String[] getFileContent(String FileName){return}
    public String getRow(String fileName, int row) {

        return "";
    }

    public void setRow(String fileName, int row, String newRow) {

    }

//    public String getColumn(String row){}
    
    
    
}
