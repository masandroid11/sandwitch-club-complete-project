package com.udacity.sandwichclub.utils;

import android.util.Log;

import com.udacity.sandwichclub.model.Sandwich;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class JsonUtils {

    private static Sandwich sandwichData;

    public static Sandwich parseSandwichJson(String json) {
        try {
            JSONObject root = new JSONObject(json);
            JSONObject nameObject = root.getJSONObject("name");

            String mainName = nameObject.getString("mainName");
            JSONArray alsoKnownAsArray = nameObject.getJSONArray("alsoKnownAs");

            String placeOfOrigin = root.getString("placeOfOrigin");
            String description = root.getString("description");
            String image = root.getString("image");

            JSONArray ingredientsArray = root.getJSONArray("ingredients");

            //conversion from JSONArray to List<>
            List<String> alsoKnownAs = jsonArrayToList(alsoKnownAsArray);
            List<String> ingredients = jsonArrayToList(ingredientsArray);

            sandwichData = new Sandwich(mainName,
                    alsoKnownAs,
                    placeOfOrigin,
                    description,
                    image,
                    ingredients);


        }catch (JSONException e){
            Log.e("JsonUtils","Problem parsing JSON",e);
        }


        return sandwichData;
    }
    //to convert JSONArray to List<String>
    private static List<String> jsonArrayToList(JSONArray array) throws JSONException{
        List<String> list = new ArrayList<String>();
        if (array != null){
            for (int i = 0; i < array.length();i++){
                list.add(array.getString(i));
            }
            return list;
        }
        return null;
    }
}
