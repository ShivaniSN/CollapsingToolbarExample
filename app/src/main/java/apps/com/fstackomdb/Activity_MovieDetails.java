package apps.com.fstackomdb;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

public class Activity_MovieDetails extends AppCompatActivity{

    private CollapsingToolbarLayout collapsingToolbarLayout = null;
    Toolbar toolbar;
    ActionBar actionBar;
    TextView textViewDirector,textViewReleaseDate,textViewStoryline;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_moviedetail);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        actionBar = getSupportActionBar();
        collapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
        textViewDirector = (TextView)findViewById(R.id.tv_director);
        textViewReleaseDate = (TextView)findViewById(R.id.tv_rdate);
        textViewStoryline = (TextView)findViewById(R.id.tv_storyline);

        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        collapsingToolbarLayout.setTitle("Movie");
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(Activity_MovieDetails.this, Activity_MovieList.class);
        startActivity(intent);
    }
}
