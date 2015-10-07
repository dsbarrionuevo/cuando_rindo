package com.labsis.cuandorindo;

import android.app.Application;
import android.content.Context;

/**
 * Creado por fedea on 07/10/2015.
 */
public class MiApp extends Application {

    private static MiApp instance;

    public MiApp() {
        instance = this;

    }

    public static Context getContext() {
        return instance;
    }
}
