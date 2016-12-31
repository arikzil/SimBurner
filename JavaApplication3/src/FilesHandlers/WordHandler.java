/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FilesHandlers;

import java.io.FileInputStream;
import org.apache.poi.xwpf.extractor.XWPFWordExtractor;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 *
 * @author arikzil
 */
public class WordHandler extends BaseHandler {

    public WordHandler(String dir) {
        workingDirectory = dir;
        this.fileFormats = new String[]{"doc", "docx"};

    }

    private static int countOccurrences(String haystack, char needle) {
        int count = 0;
        for (int i = 0; i < haystack.length(); i++) {
            if (haystack.charAt(i) == needle) {
                count++;
            }
        }
        return count;
    }

    public String[] getDocContentByLine(String docName) throws IOException {
        XWPFDocument docx = new XWPFDocument(new FileInputStream(workingDirectory.concat(docName)));

        //using XWPFWordExtractor Class
        XWPFWordExtractor we = new XWPFWordExtractor(docx);
        String content = we.getText();

        int total = countOccurrences(content, '\n');
        String[] res = new String[total];
        int latest = 0;
        String row = "";

        for (int i = 0; i < content.length(); i++) {
            if (content.charAt(i) == '\n') {
                res[latest] = row;
                row = "";
                latest++;
            } else {
                row = row.concat("" + content.charAt(i));

            }

        }

        return res;

    }

    public String getDocLine(String docName, int row) throws IOException {

        return getDocContentByLine(docName)[row];
    }

    //TODO:finish this
    public void changeLine(String docName, int row, String newLine) throws Exception {

        String[] strArr = getDocContentByLine(docName);
        StringBuilder strBuilder = new StringBuilder();

        for (int i = 0; i < strArr.length; i++) {

            if (row == i + 1) {
                System.out.println("s s s s");

                strBuilder.append(newLine);
            } else {
                strBuilder.append(strArr[i]);
            }
            strBuilder.append("\n");

        }

        String content = strBuilder.toString();

        System.out.println(content);

        // Blank Document
        XWPFDocument document = new XWPFDocument();

        // Write the Document in file system
        FileOutputStream out = new FileOutputStream(new File(workingDirectory.concat(docName)));

        // create Paragraph
        XWPFParagraph paragraph = document.createParagraph();
        XWPFRun run = paragraph.createRun();
        run.setText(content);

        document.write(out);
        out.close();
        System.out.println("It was updated succesfully");
    }

}
