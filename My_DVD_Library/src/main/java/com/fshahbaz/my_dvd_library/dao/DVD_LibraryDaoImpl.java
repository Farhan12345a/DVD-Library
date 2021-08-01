package com.fshahbaz.my_dvd_library.dao;

import com.fshahbaz.my_dvd_library.dto.DVD;
import com.fshahbaz.my_dvd_library.ui.DVD_Library_View;
import com.fshahbaz.my_dvd_library.ui.UserIO;
import com.fshahbaz.my_dvd_library.ui.UserIOConsoleImpl;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.ArrayList;

public class DVD_LibraryDaoImpl implements DVD_LibraryDao{

    private Map<String, DVD> dvds = new HashMap<>();
    
    //FILE NAME****
    public static final String ROSTER_FILE = "dvd_data.txt";
    public static final String DELIMITER = "::";
    
    @Override
    public DVD addDVD(String title, DVD dvd) throws DvdDaoException {
        loadRoster();
        DVD newDVD = dvds.put(title, dvd);
        writeRoster();
        return newDVD;
    }

    @Override
    public DVD removeDVD(String title) throws DvdDaoException{
        loadRoster();
        DVD removedDVD = dvds.remove(title);
        writeRoster();
        return removedDVD;
    }

    @Override
    public int editDVD(DVD editDVD, int choice, String replace) throws DvdDaoException{
        loadRoster();
        int result = 0;
        DVD edit = editDVD;
  
        //Problem dvds (map) isnt being changed
        if(choice == 1){
            edit.setReleaseDate(replace);
            dvds.replace(edit.getTitle(), edit);
            result = 1;
        }else if(choice == 2){
            edit.setMpaaRating(replace);
            dvds.replace(edit.getTitle(), edit);
            result = 2;
        }else if(choice == 3){
            edit.setDirectorName(replace);
            dvds.replace(edit.getTitle(), edit);
            result = 3;
        }else if(choice == 4){
            edit.setStudio(replace);
            dvds.replace(edit.getTitle(), edit);
            result = 4;
        }else if(choice == 5){
            edit.setUserRating(replace);
            dvds.replace(edit.getTitle(), edit);
            result = 5;
            //CHANGE!!!
        }
        
        writeRoster();
        return result;
    }
    

    @Override
    public List<DVD> getAllDVD() throws DvdDaoException {
        loadRoster();
        return new ArrayList<DVD>(dvds.values());
    }

    @Override
    public DVD searchDvd(String title) throws DvdDaoException {
        return dvds.get(title);
    }

    @Override
    public DVD getDVD(String title) throws DvdDaoException {
        loadRoster();
        return dvds.get(title);
    }
    
    private DVD unmarshallDVD(String dvdAsText){
        String[] dvdTokens = dvdAsText.split(DELIMITER);
        String title = dvdTokens[0];
        
        DVD dvdFromFile = new DVD(title);
        dvdFromFile.setReleaseDate(dvdTokens[1]);
        dvdFromFile.setMpaaRating(dvdTokens[2]);
        dvdFromFile.setDirectorName(dvdTokens[3]);
        dvdFromFile.setStudio(dvdTokens[4]);
        //CHANGE
        dvdFromFile.setUserRating(dvdTokens[5]);
    
        // We have now created a DVD! Return it!
        return dvdFromFile;
    }
    
    private void loadRoster() throws DvdDaoException {
        Scanner scanner;

        try {
            // Create Scanner for reading the file
            scanner = new Scanner(
                    new BufferedReader(
                            new FileReader(ROSTER_FILE)));
        } catch (FileNotFoundException e) {
            throw new DvdDaoException(
                    "-_- Could not load roster data into memory.", e);
        }
        // currentLine holds the most recent line read from the file
        String currentLine;
        // currentStudent holds the most recent student unmarshalled
        DVD currentDVD;
        // Go through ROSTER_FILE line by line, decoding each line into a 
        // Student object by calling the unmarshallStudent method.
        // Process while we have more lines in the file
        while (scanner.hasNextLine()) {
            // get the next line in the file
            currentLine = scanner.nextLine();
            // unmarshall the line into a Student
            currentDVD = unmarshallDVD(currentLine);

            // We are going to use the student id as the map key for our student object.
            // Put currentStudent into the map using student id as the key
            dvds.put(currentDVD.getTitle(), currentDVD);
        }
        // close scanner
        scanner.close();
    }
    
    private String marshallDVD(DVD aDVD){
    
    String dvdAsText = aDVD.getTitle() + DELIMITER;

    dvdAsText += aDVD.getReleaseDate() + DELIMITER;

    dvdAsText += aDVD.getMpaaRating() + DELIMITER;

    dvdAsText += aDVD.getDirectorName() + DELIMITER;
    
    dvdAsText += aDVD.getStudio() + DELIMITER;
    
    dvdAsText += aDVD.getUserRating();
   
    return dvdAsText;
}
    
private void writeRoster() throws DvdDaoException {
    PrintWriter out;

    try {
        out = new PrintWriter(new FileWriter(ROSTER_FILE));
    } catch (IOException e) {
        throw new DvdDaoException(
                "Could not save student data.", e);
    }

    String dvdAsText;
    List<DVD> dvdList = this.getAllDVD();
    for (DVD currentDVD : dvdList) {
        // turn a DVD into a String
        dvdAsText = marshallDVD(currentDVD);
        // write the DVD object to the file
        out.println(dvdAsText);
        // force PrintWriter to write line to the file
        out.flush();
    }
    // Clean up
    out.close();
}
    
}
