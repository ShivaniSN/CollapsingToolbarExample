package apps.com.fstackomdb;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Activity_MovieList extends AppCompatActivity{

    ImageView imageViewBack;
    RecyclerView recyclerViewMovies;

    String stringSearch = "";
    List<List_Movies> usersList = new ArrayList<List_Movies>();
    private Adapter_RecyclerMovie pgAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movielist);

        imageViewBack = (ImageView)findViewById(R.id.imageViewBack);
        recyclerViewMovies = (RecyclerView)findViewById(R.id.recycler_viewMovies);
        pgAdapter = new Adapter_RecyclerMovie(usersList,Activity_MovieList.this);

        stringSearch = getIntent().getStringExtra("search_string");
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerViewMovies.setLayoutManager(mLayoutManager);
        recyclerViewMovies.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        recyclerViewMovies.setItemAnimator(new DefaultItemAnimator());
        recyclerViewMovies.setAdapter(pgAdapter);

        imageViewBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Activity_MovieList.this, Activity_Main.class);
                startActivity(intent);
            }
        });

        getMoviesListVolleyRequest();

        recyclerViewMovies.addOnItemTouchListener(new RecyclerTouchListener(Activity_MovieList.this, new RecyclerTouchListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Intent intent = new Intent(Activity_MovieList.this, Activity_MovieDetails.class);
                intent.putExtra("movieid",usersList.get(position).getStringMovieId());
                startActivity(intent);
            }
        }));
    }

    public void getMoviesListVolleyRequest(){
        StringRequest stringRequest = new StringRequest(Request.Method.GET,
                Constants.MOVIES_LIST_API + stringSearch + "&apikey=" + Constants.stringAPIKey,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject json = new JSONObject(response);

                            if (json.has("Search")) {
                                JSONArray searchItemsArray = new JSONArray(json.getString("Search"));

                                for (int i =0 ;i<searchItemsArray.length();i++){
                                    JSONObject itemObj = searchItemsArray.getJSONObject(i);
                                    List_Movies searchList = new List_Movies();

                                    searchList.setStringMovieId(itemObj.getString("imdbID"));
                                    searchList.setStringMovieName(itemObj.getString("Title"));
                                    searchList.setStringMovieYear(itemObj.getString("Year"));
                                    searchList.setStringMovieImage(itemObj.getString("Poster"));
                                    usersList.add(searchList);
                                }
                                pgAdapter.notifyDataSetChanged();
                            }else {
                                Toast.makeText(Activity_MovieList.this,json.getString("error_msg"), Toast.LENGTH_SHORT).show();
                            }
                        }catch (JSONException je){
                            je.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(Activity_MovieList.this, error.toString(), Toast.LENGTH_SHORT).show();
                    }
                }){
            @Override
            protected Map<String,String> getParams(){
                Map<String,String> params = new HashMap<String, String>();
//                 params.put("s",stringSearch);
//                params.put("apikey",Constants.stringAPIKey);
                return params;
            }
        };
        stringRequest.setRetryPolicy(new DefaultRetryPolicy(
                20000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(Activity_MovieList.this, Activity_Main.class);
        startActivity(intent);
    }
}
