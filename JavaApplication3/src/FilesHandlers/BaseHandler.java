/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FilesHandlers;

import java.util.*;
import java.io.*;

/**
 *
 * @author arikzil
 */
public abstract class BaseHandler {

    protected String workingDirectory;
    protected String[] fileFormats;

    /**
     * Used for getting the working directory
     *
     * @return The current directory
     */
    public String getDirectory() {

        return this.workingDirectory;
    }

    /**
     * Used for getting the supported list of file formats
     *
     * @return A list of all the avaliable types of file formats
     */
    public String[] getFileFormats() {
        return this.fileFormats;
    }

    /**
     * Changes The working directory
     *
     * @param newDirectory represent the new working directory
     */
    public void setDirectory(String newDirectory) {
        this.workingDirectory = newDirectory;
    }

    /**
     * Used for getting all the items in the current working directory.
     *
     * @return List of all the sphreadSheets of thhe current working directory
     */
    public String[] getSuitableDirContent() {
        File folder = new File(workingDirectory);
        File[] listOfRawFiles = folder.listFiles();
        List<String> dirList = new ArrayList<>();
        String[] listFiles;

        // get list of files
        for (File listOfRawFile : listOfRawFiles) {

            if (listOfRawFile.isFile()) {

                for (String fileFormat : this.getFileFormats()) {
                    if (((String) listOfRawFile.getName()).contains(fileFormat)) {
                        dirList.add((String) listOfRawFile.getName());

                    }

                }

            }
        }
        listFiles = dirList.toArray(new String[dirList.size()]);

        // get file from suitable format
        //   LinkedList files;
        //   String[] dirFile =
        //   for (int i = 0; i < fileFormats.size(); i++) {
        //   }
        return dirList.toArray(new String[dirList.size()]);

    }

  

}
