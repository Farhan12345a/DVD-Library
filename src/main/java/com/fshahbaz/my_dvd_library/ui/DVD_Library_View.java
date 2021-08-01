/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fshahbaz.my_dvd_library.ui;

import com.fshahbaz.my_dvd_library.dto.DVD;
import java.util.List;
import java.util.Scanner;
import java.util.ArrayList;

//This class handles all the UI logic.
public class DVD_Library_View {
    
    //DEPENDENCY INJECTION BELOW
    //private UserIO io = new UserIOConsoleImpl();
    private UserIO io;
    public DVD_Library_View(UserIO io) {
        this.io = io;
    }
    
    //Prints the menu option, gets called in Controller
    public int printMenuAndGetSelection() {
        io.print("Main Menu");
        io.print("1. Add DVD to the Collection");
        io.print("2. Remove a DVD from the collection");
        io.print("3. Edit information for an existing DVD");
        io.print("4. List the DVDs");
        io.print("5. Display information for particular DVD");
        io.print("6. Search for DVD");
        io.print("7. Exit");

        return io.readInt("Please select from the"
                    + " above choices.", 1, 7);
    }
    
    public DVD getDVDInfo() {
        String movieTitle = io.readString("Please enter Movie Title");
        //IF TIME FORMAT DATE
        String releaseDate = io.readString("Please enter the Release Date");
        String mpaaRating = io.readString("Please enter MPAA Rating");
        String directorName = io.readString("Please enter Director's Name");
        String studio = io.readString("Please enter the Studio");
        String userRating = io.readString("Please enter User Rating or any additional notes on movie");
        DVD currentDVD = new DVD(movieTitle);
        currentDVD.setReleaseDate(releaseDate);
        currentDVD.setMpaaRating(mpaaRating);
        currentDVD.setDirectorName(directorName);
        currentDVD.setStudio(studio);
        currentDVD.setUserRating(userRating);
        return currentDVD;
    }
    
    public void displayCreateDVDBanner() {
        io.print("=== Add DVD ===");
    }
    
    public void displayCreateSuccessBanner() {
        io.readString("DVD successfully added.  Please hit enter to continue");
    }
    
    public void displayDVDList(List<DVD> dvdList) {
        boundary();
        for (DVD currentDVD : dvdList) {
            String studentInfo = String.format("Title: %s\n"
                    + "Release Date: %s\n"
                    + "MPAA Rating: %s\n"
                    + "Director's Name: %s\n"
                    + "Studio: %s\n"
                    + "User Rating: %s",
                  currentDVD.getTitle(),
                  currentDVD.getReleaseDate(),
                  currentDVD.getMpaaRating(),
                  currentDVD.getDirectorName(),
                  currentDVD.getStudio(),
                  currentDVD.getUserRating());
                  
            io.print(studentInfo);
            boundary();
        }
        io.readString("Please hit enter to continue.");
    }
    
    public void displayDisplayAllBanner() {
        io.print("=== Display All DVD's ===");
    }
    
    public void displayDisplayDVDBanner() {
        io.print("=== Display DVD ===");
    }

    public String getDVDTitleChoice() {
        return io.readString("Please enter the DVD Title: ");
    }

    public void displayDVD(DVD currentDVD) {
        if (currentDVD != null) {
            boundary();
            io.print("Title: " + currentDVD.getTitle());
            io.print("Release Date: " + currentDVD.getReleaseDate());
            io.print("MPAA Rating: " + currentDVD.getMpaaRating());
            io.print("Director's Name: " + currentDVD.getDirectorName());
            io.print("Studio: " + currentDVD.getStudio());
            io.print("User Rating: " + currentDVD.getUserRating());
            boundary();
        } else {
            io.print("No such DVD.");
        }
        io.readString("Please hit enter to continue.");
    }
    
    
    public void displayRemoveDVDBanner(){
        io.print("=== Remove DVD ===");
    }

    public void displayRemoveResult(DVD dvd) {
        if(dvd != null){
          io.print("DVD successfully removed.");
        }else{
          io.print("No such DVD.");
        }
        io.readString("Please hit enter to continue.");
    }
    
    public void displayEditDVDBanner () {
        io.print("=== Edit Student ===");
    }
    
    public boolean displayEditResult(DVD dvd) {
        if(dvd != null){
          io.print(dvd.getTitle() + " Selected");
          return true;
        }
          io.print("No such DVD.");
          io.readString("Please hit enter to continue.");
          return false;
    }
    
    public String getDVDEditTitleChoice() {
        return io.readString("Please enter the DVD Title You want to Edit: ");
        //String title = io.readString("Please enter the DVD Title You want to Edit: ");
    }
    
    public void editMenu(){
        io.print("=== Edit Options ===");
        io.print("1. Edit Release Date");
        io.print("2. Edit MPAA Rating");
        io.print("3. Edit Director's Name");
        io.print("4. Edit Studio");
        io.print("5. Edit User Rating");
        
    }
    
    public int editHelper(){
        int editChoice = 0;
        int input = io.readInt("Please enter which movie option you want to change: ");
        if (input == 1){
            io.print("Release Date");
            editChoice = 1;
        }else if (input == 2){
            io.print("MPAA Rating");
            editChoice = 2;
        }else if (input == 3){
            io.print("Director's Name");
            editChoice = 3;
        }else if (input == 4){
            io.print("Studio");
            editChoice = 4;
        }else if (input == 5){
            io.print("User Rating");
            editChoice = 5;
            //CHANGE!!!!!
        }
        
        return editChoice;
    }
    
    public String getEditValue(int editChoice){
        String replace = null;
        if(editChoice == 1){
            replace = io.readString("What would you like the new Release Date to be?");
        }else if(editChoice == 2){
            replace = io.readString("What would you like the new MPAA Rating to be?");
        }else if(editChoice == 3){
            replace = io.readString("What would you like the new Director's Name to be?");
        }else if(editChoice == 4){
            replace = io.readString("What would you like the new Studio Name to be?");
        }else if(editChoice == 5){
            replace = io.readString("What would you like the new User Rating to be?");
            //CHANGE!!!!!
        }
        return replace;
        
    }
    
    public void displaySuccessEdit(){
        io.print("DVD successfully edited.  Please hit enter to continue");
    }
    
    public void displaySearchBanner() {
        io.print("=== Search Student ===");
    }

    public void searchDvdResult(DVD dvd) {
        if (dvd != null) {
            io.print("DVD is stored in system");
            boundary();
        } else {
            io.print("DVD is NOT stored in system");
        }
    }
    
    public void displayExitBanner(){
        io.print("Good Bye!!!");
    }

    public void displayUnknownCommandBanner(){
        io.print("Unknown Command!!!");
    }
    
    public void displayErrorMessage(String errorMsg) {
        io.print("=== ERROR ===");
        io.print(errorMsg);
    }
    
    public void boundary(){
        io.print("=======================");
    }
    
    
    
    
    
}
