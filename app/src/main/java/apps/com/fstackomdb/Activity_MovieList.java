package apps.com.fstackomdb;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

public class Activity_MovieList extends AppCompatActivity{

    ImageView imageViewBack;
    RecyclerView recyclerViewMovies;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movielist);

        imageViewBack = (ImageView)findViewById(R.id.imageViewBack);
        recyclerViewMovies = (RecyclerView)findViewById(R.id.recycler_viewMovies);

        imageViewBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Activity_MovieList.this, Activity_MovieDetails.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(Activity_MovieList.this, Activity_Main.class);
        startActivity(intent);
    }
}
