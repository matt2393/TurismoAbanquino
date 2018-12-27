package com.ejemplo.usuariio.turismoabanquino;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

public class VolleySingleton {
    private static VolleySingleton volley;
    private RequestQueue cola;
    private static Context context;

    private VolleySingleton(Context context) {
        VolleySingleton.context = context;
        cola = getCola();
    }

    public static synchronized VolleySingleton getInstance(Context context) {
        if (volley == null)
            volley = new VolleySingleton(context);

        return volley;
    }

    private RequestQueue getCola() {
        if (cola == null) {
            cola = Volley.newRequestQueue(context.getApplicationContext());
        }

        return cola;
    }

    public void addColaRequest(Request request) {
        getCola().add(request);
    }
}
