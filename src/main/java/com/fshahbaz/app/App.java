/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fshahbaz.app;

import com.fshahbaz.my_dvd_library.controller.DVD_Library_Controller;
import com.fshahbaz.my_dvd_library.dao.DVD_LibraryDaoImpl;
import com.fshahbaz.my_dvd_library.ui.DVD_Library_View;
import com.fshahbaz.my_dvd_library.ui.UserIO;
import com.fshahbaz.my_dvd_library.ui.UserIOConsoleImpl;
import com.fshahbaz.my_dvd_library.dao.DVD_LibraryDao;

/**
 *
 * @author farhanshahbaz
 */
public class App {
    public static void main(String[]  args){
        UserIO myIo = new UserIOConsoleImpl();
        DVD_Library_View myView = new DVD_Library_View(myIo);
        DVD_LibraryDao myDao = new DVD_LibraryDaoImpl();
        //Instantiating our controller and calling the run method
        DVD_Library_Controller controller = new DVD_Library_Controller(myDao, myView);
        controller.run();
    }
}


