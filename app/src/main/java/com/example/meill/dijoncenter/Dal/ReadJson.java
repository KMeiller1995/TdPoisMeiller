package com.example.meill.dijoncenter.Dal;


import com.example.meill.dijoncenter.Models.Lieu;
import com.example.meill.dijoncenter.Models.Location;
import com.example.meill.dijoncenter.Models.Position;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;

/**
 * Created by meill on 08/09/2017.
 */
public class ReadJson {
    private String myurl= "https://my-json-server.typicode.com/lpotherat/pois/pois";

    public ArrayList<Lieu> read() throws IOException, JSONException {
        ArrayList<Lieu> lieus = new ArrayList<Lieu>();

            URL url = new URL(myurl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.connect();
            InputStream inputStream = connection.getInputStream();
            String result = InputStreamOperations.InputStreamToString(inputStream);

            //Récupère le tableau d'objets qui nous concernent
            JSONArray array =  new JSONArray(result);

            //Pour tous les objets on récupère les infos
            for (int i = 0; i < array.length(); i++) {
                // On récupère un objet JSON du tableau
                JSONObject obj = new JSONObject(array.getString(i));
                // On fait le lien Lieu - Objet JSON
                Lieu lieu = new Lieu();

                lieu.setId(UUID.fromString(obj.getString("id")));
                lieu.setType(obj.getString("type"));
                lieu.setName(obj.getString("name"));
                //Location
                Location location = new Location();
                location.setAdress(obj.getJSONObject("location").getString("adress"));
                location.setCity(obj.getJSONObject("location").getString("city"));
                location.setPostalCode(obj.getJSONObject("location").getString("postalCode"));
                //position
                Position position = new Position();
                position.setLat(obj.getJSONObject("location").getJSONObject("position").getDouble("lat"));
                position.setLon(obj.getJSONObject("location").getJSONObject("position").getDouble("lon"));
                location.setPosition(position);
                lieu.setLocation(location);
                //add lieus
                lieus.add(lieu);
            }
            return lieus;
    }

    public Lieu readById(UUID id) throws IOException, JSONException {

        URL url = new URL(myurl +"/"+ id);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.connect();
        InputStream inputStream = connection.getInputStream();
        String result = InputStreamOperations.InputStreamToString(inputStream);

        //Récupère le tableau d'objets qui nous concernent
        //JSONArray array =  new JSONArray(result);

        //Pour tous les objets on récupère les infos
        //for (int i = 0; i < array.length(); i++) {

            // On récupère un objet JSON du tableau
            JSONObject obj = new JSONObject(result);
            // On fait le lien Lieu - Objet JSON
            Lieu lieu = new Lieu();

            lieu.setId(UUID.fromString(obj.getString("id")));
            lieu.setType(obj.getString("type"));
            lieu.setName(obj.getString("name"));
                //Location
            Location location = new Location();
            location.setAdress(obj.getJSONObject("location").getString("adress"));
            location.setCity(obj.getJSONObject("location").getString("city"));
            location.setPostalCode(obj.getJSONObject("location").getString("postalCode"));
                //position
            Position position = new Position();
            position.setLat(obj.getJSONObject("location").getJSONObject("position").getDouble("lat"));
            position.setLon(obj.getJSONObject("location").getJSONObject("position").getDouble("lon"));
            location.setPosition(position);
            lieu.setLocation(location);

            return lieu;


    }


    public static class InputStreamOperations {

        /**
         * @param in : buffer with the php result
         * @param bufSize : size of the buffer
         * @return : the string corresponding to the buffer
         */
        public static String InputStreamToString (InputStream in, int bufSize) {
            final StringBuilder out = new StringBuilder();
            final byte[] buffer = new byte[bufSize];
            try {
                for (int ctr; (ctr = in.read(buffer)) != -1;) {
                    out.append(new String(buffer, 0, ctr));
                }
            } catch (IOException e) {
                throw new RuntimeException("Cannot convert stream to string", e);
            }
            // On retourne la chaine contenant les donnees de l'InputStream
            return out.toString();
        }

        /**
         * @param in : buffer with the php result
         * @return : the string corresponding to the buffer
         */
        public static String InputStreamToString (InputStream in) {
            // On appelle la methode precedente avec une taille de buffer par defaut
            return InputStreamToString(in, 1024);
        }

    }
}
