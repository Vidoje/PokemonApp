package com.taurunium.pokemon.java.activities;

import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.squareup.picasso.Picasso;
import com.taurunium.pokemon.R;
import com.taurunium.pokemon.java.models.PokemonDetails;
import com.taurunium.pokemon.java.models.Stat;
import com.taurunium.pokemon.java.models.Type;
import com.taurunium.pokemon.kotlin.utils.Constants;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class PokemonDetailsActivity extends AppCompatActivity {

    private PokemonDetails pokemonDetails;

    private LinearLayout llPokemonTypes, llPokemonStats;
    private TextView tvPokemonName;
    private ImageView ivPokemonImage;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pokemon_details);

        int value = Integer.parseInt(getIntent().getExtras().get("num").toString());

        StringRequest stringRequest = new StringRequest(Request.Method.GET, Constants.Companion.getBASE_URL() + "pokemon/" + value, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);

                    pokemonDetails = new PokemonDetails();
                    String name = jsonObject.get("name").toString();
                    int id = Integer.parseInt(jsonObject.get("id").toString());

                    ArrayList<Type> types = new ArrayList<>();
                    JSONArray jsonArrayType = jsonObject.getJSONArray("types");
                    if (jsonArrayType.length() > 0) {
                        for (int i = 0; i < jsonArrayType.length(); i++) {
                            JSONObject jsonObject1 = jsonArrayType.getJSONObject(i);
                            JSONObject jsonObjectType = jsonObject1.getJSONObject("type");
                            String typeName = jsonObjectType.get("name").toString();

                            Type type = new Type();
                            type.setName(typeName);
                            types.add(type);
                        }
                    }

                    ArrayList<Stat> stats = new ArrayList<>();
                    JSONArray jsonArrayStats = jsonObject.getJSONArray("stats");
                    if (jsonArrayStats.length() > 0) {
                        for (int i = 0; i < jsonArrayStats.length(); i++) {
                            JSONObject jsonObject1 = jsonArrayStats.getJSONObject(i);
                            int baseStat = jsonObject1.getInt("base_stat");

                            JSONObject jsonObjectType = jsonObject1.getJSONObject("stat");
                            String typeName = jsonObjectType.get("name").toString();

                            Stat stat = new Stat();
                            stat.setName(typeName);
                            stat.setBaseStat(baseStat);
                            stats.add(stat);
                        }
                    }

                    pokemonDetails.setName(name);
                    pokemonDetails.setTypes(types);
                    pokemonDetails.setId(id);
                    pokemonDetails.setStats(stats);

                    generateUI();

                } catch (Exception e) {

                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("Error", "error: " + error);
            }
        });

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

    private void generateUI() {
        ivPokemonImage = (ImageView) findViewById(R.id.ivPokemonImage);
        tvPokemonName = (TextView) findViewById(R.id.tvPokemonName);
        llPokemonTypes = (LinearLayout) findViewById(R.id.llPokemonTypes);
        llPokemonStats = (LinearLayout) findViewById(R.id.llPokemonStats);

        Picasso.get().load(Constants.Companion.getBASE_IMAGE_URL() + "/" + pokemonDetails.getId() + ".png").into(ivPokemonImage);

        tvPokemonName.setText(pokemonDetails.getName());

        if (!pokemonDetails.getTypes().isEmpty()) {
            for (int i = 0; i < pokemonDetails.getTypes().size(); i++) {
                TextView textView = new TextView(this);
                textView.setText(pokemonDetails.getTypes().get(i).getName());
                textView.setTextColor(getResources().getColor(R.color.black));
                textView.setGravity(Gravity.CENTER_HORIZONTAL);

                llPokemonTypes.addView(textView);
            }
        }

        if (!pokemonDetails.getStats().isEmpty()) {
            for (int i = 0; i < pokemonDetails.getStats().size(); i++) {
                TextView textView = new TextView(this);
                textView.setText(pokemonDetails.getStats().get(i).getName() + ":" + pokemonDetails.getStats().get(i).getBaseStat());
                textView.setTextColor(getResources().getColor(R.color.black));
                textView.setGravity(Gravity.CENTER_HORIZONTAL);

                llPokemonStats.addView(textView);
            }
        }
    }
}
