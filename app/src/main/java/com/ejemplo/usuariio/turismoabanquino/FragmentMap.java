package com.ejemplo.usuariio.turismoabanquino;

import android.Manifest;
import android.content.IntentSender;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.location.Location;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.toolbox.JsonObjectRequest;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.common.api.ResolvableApiException;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.LocationSettingsRequest;
import com.google.android.gms.location.LocationSettingsResponse;
import com.google.android.gms.location.LocationSettingsStatusCodes;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;
import com.google.android.gms.tasks.Task;
import com.google.maps.android.PolyUtil;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

public class FragmentMap extends Fragment implements OnMapReadyCallback {
    public final static String TAG = "FragmentMap";


    private final static String GDirecOrigen = "https://maps.googleapis.com/maps/api/directions/json?origin=";
    private final static String GDirecDestino = "&destination=";
    private final static String GDirecAlternativas = "&alternatives=";
    private final static String GDirecModo = "&mode=";
    private final static String GDirecKey = "&key=";

    private final static String ROUTES = "routes";
    private final static String POLYLINE = "overview_polyline";
    private final static String POINTS = "points";

    public final static String MODO_CAMINATA = "walking";
    public final static String MODO_CONDUCCION = "driving";
    public final static String MODO_BICICLETA = "bicycling";
    private static final int CAMBIAR_CONFIG = 54321;

    private int tipo;
    private LugaresVo lugaresVo;
    private LocationRequest locationRequest;
    private LocationCallback locationCallback;

    private GoogleMap mMap;

    private List<LatLng> puntos;
    private ArrayList<Polyline> rutas;

    private ArrayList<LugaresVo> lugares;

    private Spinner spinner;
    private Marker marker;

    private Task<LocationSettingsResponse> result;
    private LocationSettingsRequest settingsRequest;
    private Location loc;

    public static FragmentMap newInstance(int tipo, LugaresVo lugaresVo, ArrayList<LugaresVo> lugaresVos) {
        Bundle bn = new Bundle();
        bn.putInt("TIPO", tipo);
        bn.putParcelable("LUGAR", lugaresVo);
        bn.putParcelableArrayList("LUGARES", lugaresVos);
        FragmentMap fr = new FragmentMap();
        fr.setArguments(bn);
        return fr;
    }

    private MapView mapView;
    private boolean trazado;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_map, container, false);

        mapView = view.findViewById(R.id.map_view);
        spinner = view.findViewById(R.id.spinner_lugares_map);
        tipo = getArguments().getInt("TIPO", 0);
        lugaresVo = getArguments().getParcelable("LUGAR");


        rutas=new ArrayList<>();
        trazado=false;

        locationRequest = new LocationRequest()
                .setInterval(2000)
                .setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);


        locationCallback = new LocationCallback() {
            @Override
            public void onLocationResult(LocationResult locationResult) {
                Log.e("LOC","Localizacion");
                loc = locationResult.getLastLocation();

                if(tipo==1 && !trazado) {
                    trazar(lugaresVo);
                    trazado=false;
                }

            }
        };
        settingsRequest = new LocationSettingsRequest.Builder()
                .addLocationRequest(locationRequest)
                .setAlwaysShow(true)
                .build();


        result = LocationServices.getSettingsClient(getActivity())
                .checkLocationSettings(settingsRequest);

        locationState();

        if (tipo == 0) {
            lugares = getArguments().getParcelableArrayList("LUGARES");
            ArrayList<String> lugs = new ArrayList<>();
            for (LugaresVo ll : lugares) {
                lugs.add(ll.getNombre());
            }

            spinner.setVisibility(View.VISIBLE);
            ArrayAdapter<String> adapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_spinner_item, lugs);
            spinner.setAdapter(adapter);

            spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    lugaresVo=lugares.get(position);
                    trazar(lugaresVo);
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });

        }
        else spinner.setVisibility(View.GONE);

        mapView.getMapAsync(this);
        mapView.onCreate(savedInstanceState);

        return view;
    }

    private void trazar(LugaresVo lugaresVo) {
        if(marker!=null)
             marker.remove();
        marker = mMap.addMarker(new MarkerOptions()
                .position(lugaresVo.toLatLng())
                .title(lugaresVo.getNombre())
                .snippet(lugaresVo.getInfo()));
        if (ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        ruta();

    }

    private void ruta(){
        if(loc!=null) {
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(
                    new LatLng(loc.getLatitude(), loc.getLongitude()), 17));
            trazarRuta(new LatLng(loc.getLatitude(), loc.getLongitude()),
                    marker.getPosition(),
                    MODO_CAMINATA, true, getString(R.string.api_key_google_maps));
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        mapView.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        mapView.onPause();
    }

    @Override
    public void onStart() {
        super.onStart();
        mapView.onStart();
    }

    @Override
    public void onStop() {
        super.onStop();
        mapView.onStop();
    }

    @Override
    public void onDestroyView() {
        if(locationCallback!=null){
            LocationServices.getFusedLocationProviderClient(getActivity())
                    .removeLocationUpdates(locationCallback);
        }
        super.onDestroyView();
        mapView.onDestroy();

    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mapView.onLowMemory();
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        this.mMap = googleMap;
        mMap.getUiSettings().setZoomControlsEnabled(true);
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(
                new LatLng(-13.638348, -72.889676), 15));
        if (ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        mMap.setMyLocationEnabled(true);

        LocationServices.getFusedLocationProviderClient(getActivity())
                .requestLocationUpdates(locationRequest, locationCallback, null);

    }




    public void trazarRuta(LatLng origen, LatLng destino,
                           String modo, boolean alternativas, String ApiKey) {

        String url = GDirecOrigen + origen.latitude + "," + origen.longitude
                + GDirecDestino + destino.latitude + "," + destino.longitude
                + GDirecAlternativas + alternativas + GDirecModo + modo
                + GDirecKey + ApiKey;

        for (int i = 0; i < rutas.size(); i++) {
            rutas.remove(i).remove();
            i--;
        }

        rutas = new ArrayList<>();

        JsonObjectRequest peticionRuta = new JsonObjectRequest(Request.Method.GET, url, null,
                response -> {
                    Log.e("LOC","trazando");
                    try {
                        int i, colorPolyline;
                        JSONArray jsonArray = response.getJSONArray(ROUTES);
                        for (i = jsonArray.length() - 1; i >= 0; i--) {


                            if (i == 0)
                                colorPolyline = Color.rgb(238, 118, 56);
                            else
                                colorPolyline = Color.rgb(163, 163, 163);
                            puntos = PolyUtil.decode(jsonArray.getJSONObject(i)
                                    .getJSONObject(POLYLINE)
                                    .get(POINTS).toString());
                            rutas.add(mMap.addPolyline(
                                    new PolylineOptions().addAll(puntos).color(colorPolyline)));
                        }
                        // dialogLoad.dismiss();

                        // caminata.setVisibility(View.VISIBLE);
                        // auto.setVisibility(View.VISIBLE);
                        // activar.setVisibility(View.VISIBLE);
                    } catch (JSONException jex) {
                        mensajeError("Intente nuevamente");
                        // dialogLoad.dismiss();
                    }
                },
                error -> {
                    mensajeError("Error en la conexion");
                    //dialogLoad.dismiss();
                }
        );

        VolleySingleton.getInstance(getActivity()).addColaRequest(peticionRuta);
    }
    private void mensajeError(String mess) {
        Toast.makeText(getActivity(),
                mess, Toast.LENGTH_LONG).show();
    }


    private void locationState() {
        result.addOnCompleteListener(task -> {
            try {
                task.getResult(ApiException.class);
            } catch (ApiException e) {
                switch (e.getStatusCode()) {
                    case LocationSettingsStatusCodes.RESOLUTION_REQUIRED:
                        ResolvableApiException resolvable = (ResolvableApiException) e;
                        try {
                            resolvable.startResolutionForResult(
                                    getActivity(),
                                    CAMBIAR_CONFIG);
                        } catch (IntentSender.SendIntentException e1) {

                            //  abrirOpcionesRegion();
                            mensajeError("Ocurrio un error en la ubicacion.");
                        } catch (ClassCastException e1) {
                            // abrirOpcionesRegion();
                            mensajeError("Ocurrio un error en la ubicacion.");
                        }
                        break;
                    case LocationSettingsStatusCodes.SETTINGS_CHANGE_UNAVAILABLE:
                        mensajeError("Ocurrio un error en la ubicacion.");
                        break;
                }
            }
        });
    }
}
