package ch.supsi.connectfour.frontend.controller;

import ch.supsi.connectfour.frontend.view.AboutViewInterface;

public class AboutController implements AboutControllerInterface {
    private boolean inizialized=false;

    private AboutViewInterface aboutView;

    //constructor
    public AboutController(AboutViewInterface aboutView) {
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
