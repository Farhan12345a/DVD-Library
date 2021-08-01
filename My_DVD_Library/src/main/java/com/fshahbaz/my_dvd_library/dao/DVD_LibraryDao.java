/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fshahbaz.my_dvd_library.dao;

import com.fshahbaz.my_dvd_library.dto.DVD;
import java.util.List;

/**
 *
 * @author farhanshahbaz
 */
public interface DVD_LibraryDao {
    
    /**
     * Adds the given DVD to the roster and associates it with the given
     * title. 
     *
     * @param title title associated with DVD
     * @param dvd dvd 
     * @return the Student object previously associated with the given  
     * student id if it exists, null otherwise
     */
    DVD addDVD(String title, DVD dvd) throws DvdDaoException;

    /**
     * Removes DVD from collection, the title associated with DVD
     * Returns the student object that is being removed or null if
     * there is no student associated with the given id
     */
    DVD removeDVD(String title) throws DvdDaoException;
    
    /**
     * *****STEP 4!!!***
     * MAYBE USE WITH getDVD??*****
     * SIMILAR TO getDVD***
     * Gets the specific DVD and allows user to EDIT THE INFORMATION
     * 
     */
    public int editDVD(DVD editDVD, int choice, String replace) throws DvdDaoException;
    
    /**
     * Returns a List of all DVD's in collection.
     */
    List<DVD> getAllDVD() throws DvdDaoException;
    
    /**
     * *****STEP 4!!!
     * Displays all information for particular movie based on title****
     */
    DVD searchDvd(String title) throws DvdDaoException;

    /**
     * Returns DVD object associated with DVD title name
     * Returns null if no such DVD exists
     *
     */
    //searchDVD(String title) **ALT
    DVD getDVD(String title) throws DvdDaoException;

    
}
