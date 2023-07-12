package ch.supsi.connectfour.frontend.controller;

import ch.supsi.connectfour.frontend.view.WriteAboutView;

public class AboutController implements WriteAboutController{
    private boolean inizialized=false;

    private WriteAboutView aboutView;

    //constructor
    public AboutController(WriteAboutView aboutView) {
        this.aboutView = aboutView;
    }

    //static

    //private

    //public
    public void initializeExplicit() {
        this.inizialized=true;
    }
    public void showAbout(){
        aboutView.showAbout();
    }

}
