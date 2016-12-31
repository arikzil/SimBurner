/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Test;

import FilesHandlers.WordHandler;

/**
 *
 * @author arikzil
 */
public class Tester {

    public static void main(String[] args) throws Exception {
        WordHandler wordHandler = new WordHandler("/home/arikzil/Desktop/Projects/work/dd/");
        String document = "resume_ariel_heb.docx";
        
        wordHandler.changeLine(document, 4, "dfdsddddddddddddddddddddddddddddddddddddddddddddddddddddddddddsf");

    }
}
