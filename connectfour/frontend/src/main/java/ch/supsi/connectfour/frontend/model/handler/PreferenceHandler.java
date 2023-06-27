package ch.supsi.connectfour.frontend.model.handler;

import ch.supsi.connectfour.backend.utility.Handler;

public interface PreferenceHandler extends Handler {
    public static String getDefaultPath() {
        return (String) System.getProperties().get("user.home");
    }
}
