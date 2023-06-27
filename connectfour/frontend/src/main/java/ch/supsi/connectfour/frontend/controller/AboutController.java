package ch.supsi.connectfour.frontend.controller;

import ch.supsi.connectfour.frontend.view.AboutView;
import javafx.stage.Stage;

public class AboutController {
    private boolean inizialized=false;

    private AboutView aboutView;

    //constructor
    public AboutController( AboutView aboutView) {
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
