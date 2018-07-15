package apps.com.fstackomdb;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class Activity_MovieDetails extends AppCompatActivity{

    private CollapsingToolbarLayout collapsingToolbarLayout = null;
    Toolbar toolbar;
    ActionBar actionBar;
    ImageView imageViewPoster;
    TextView textViewDirector,textViewReleaseDate,textViewStoryline,textViewDuration;

    String stringMovieId = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_moviedetail);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        actionBar = getSupportActionBar();
        collapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
        textViewDirector = (TextView)findViewById(R.id.tv_director);
        textViewReleaseDate = (TextView)findViewById(R.id.tv_rdate);
        textViewDuration = (TextView)findViewById(R.id.tv_duration);
        textViewStoryline = (TextView)findViewById(R.id.tv_storyline);
        imageViewPoster = (ImageView)findViewById(R.id.iv_Movie);

        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        stringMovieId = getIntent().getStringExtra("movieid");

        getMoviesDetailsVolleyRequest();
    }

    public void getMoviesDetailsVolleyRequest(){
        StringRequest stringRequest = new StringRequest(Request.Method.GET,
                Constants.MOVIES_DETAILS_API + stringMovieId + "&apikey=" + Constants.stringAPIKey,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject json = new JSONObject(response);

                            if (json.has("Title")) {
                                collapsingToolbarLayout.setTitle(json.getString("Title") +
                                        "(" + json.getString("Year") + ")");
                                textViewDuration.setText(json.getString("Runtime") + "|" + json.getString("Genre"));
                                textViewDirector.setText(json.getString("Director"));
                                json.getString("imdbRating");
                                textViewReleaseDate.setText(json.getString("Released"));
                                textViewStoryline.setText(json.getString("Plot"));
                                Picasso.with(Activity_MovieDetails.this).load(json.getString("Poster")).into(imageViewPoster);
                            }else {
                                Toast.makeText(Activity_MovieDetails.this,json.getString("error_msg"), Toast.LENGTH_SHORT).show();
                            }
                        }catch (JSONException je){
                            je.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(Activity_MovieDetails.this, error.toString(), Toast.LENGTH_SHORT).show();
                    }
                }){
            @Override
            protected Map<String,String> getParams(){
                Map<String,String> params = new HashMap<String, String>();
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
        Intent intent = new Intent(Activity_MovieDetails.this, Activity_MovieList.class);
        startActivity(intent);
    }
}
