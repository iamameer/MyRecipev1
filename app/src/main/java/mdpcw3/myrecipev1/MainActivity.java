/*
*  MainActivity : Displays collection of available Recipe
*                 Has Search function
*                 Has Exit button
*                 Can Add new recipe via NEW RECIPE button
* */

package mdpcw3.myrecipev1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    //Global Variables
    private EditText txtSearch;
    private Button btnSearch, btnExit, btnAdd;
    private ListView listView;
    private boolean isNewRecipe, isViewRecipe;

    //This method initialize settings and items
    private void init(){
        Log.d("MyRecipe","MainActivity: Initializing settings and items");
        txtSearch = findViewById(R.id.txtSearch);

        btnSearch = findViewById(R.id.btnSearch);
        btnExit = findViewById(R.id.btnExit);
        btnAdd = findViewById(R.id.btnAdd);

        listView = findViewById(R.id.listView);

        isNewRecipe = false;
        isViewRecipe = false;
    }

    //This method initialize events
    private void setEvents(){
        Log.d("MyRecipe","MainActivity: Initializing events and listeners");
        //Search method for Search Button
        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //TODO
            }
        });

        //Exit method for Exit button
        btnExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //TODO
            }
        });

        //Add method for Add button
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),RecipeDetail.class);
                startActivity(intent);
            }
        });
    }

    //This method display the database in the listView
    public void display(){
        Log.d("MyRecipe","MainActivity: Retrieving data from database");
        //TODO listView
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d("MyRecipe","=MainActivity onCreate()");

        //Initialising variables and setting up events
        init();
        setEvents();

    }

    @Override
    protected void onStart(){
        super.onStart();
        Log.d("MyRecipe","=MainActivity onStart()");
    }

    @Override
    protected void onRestart(){
        super.onRestart();
        Log.d("MyRecipe","=MainActivity onRestart()");
    }

    @Override
    protected void onResume(){
        super.onResume();
        Log.d("MyRecipe","=MainActivity onResume()");
    }

    @Override
    protected void onPause(){
        super.onPause();
        Log.d("MyRecipe","=MainActivity onPause()");
    }

    @Override
    protected void onStop(){
        super.onStop();
        Log.d("MyRecipe","=MainActivity onStop()");
    }

    @Override
    protected void onDestroy(){
        super.onDestroy();
        Log.d("MyRecipe","=MainActivity onDestroy()");
    }

}
