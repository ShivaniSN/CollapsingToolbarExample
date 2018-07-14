package apps.com.fstackomdb;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Activity_Main extends AppCompatActivity {

    EditText editTextSearch;
    Button buttonSearch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextSearch = (EditText)findViewById(R.id.et_search);
        buttonSearch = (Button)findViewById(R.id.btn_search);

        buttonSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int cnt = 0;

                if (editTextSearch.getText().length() < 2){
                    Toast.makeText(Activity_Main.this,"Search string missing", Toast.LENGTH_SHORT).show();
                    cnt ++;
                }

                if (cnt == 0) {
                    Intent intent = new Intent(Activity_Main.this, Activity_MovieList.class);
                    intent.putExtra("search_string",editTextSearch.getText().toString());
                    startActivity(intent);
                }
            }
        });
    }
}
