/*
*  RecipeDetail : Has 2 MODE:
*                 Mode 1 - Empty textbox to enter new Recipe
*                        - with Add, Clear, and Back button
*                 Mode 2 - Loaded selected item from ListView in MainActivity
*                        - with Edit, Save, Delete and Cancel button
* */

package mdpcw3.myrecipev1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class RecipeDetail extends AppCompatActivity {

    //Global Variables
    private EditText txtTitle, txtIns;
    private Button btnBackCancel, btnClearDelete, btnEditSaveSubmit;
    private boolean isNewRecipe, isViewRecipe;

    //This method initialize settings and items
    private void init(){
        Log.d("MyRecipe","MainActivity: Initializing settings and items");
        txtTitle = findViewById(R.id.txtTitle);
        txtIns = findViewById(R.id.txtIns);

        btnBackCancel = findViewById(R.id.btnBackCancel);
        btnClearDelete = findViewById(R.id.btnClearDelete);
        btnEditSaveSubmit = findViewById(R.id.btnEditSaveSubmit);

        //TODO : get bundle/intent
        //isNewRecipe = get;
        //isViewRecipe = get;
        //txtTitle.setText();
        //txtIns.setText();

        //button state

    }

    //This method initialize events
    private void setEvents(){
        Log.d("MyRecipe","RecipeDetail: Initializing events and listeners");

        //onClick Listener for btnBackCancel
        btnBackCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //TODO
            }
        });

        //onClick Listener for btnClearDelete
        btnClearDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //TODO
            }
        });

        //onClick Listener for btnEditSaveSubmit
        btnEditSaveSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //TODO
            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_detail);
        Log.d("MyRecipe","@RecipeDetail onCreate()");

        //Initialising variables and setting up events
        init();
        setEvents();

    }

    @Override
    protected void onStart(){
        super.onStart();
        Log.d("MyRecipe","@RecipeDetail onStart()");
    }

    @Override
    protected void onRestart(){
        super.onRestart();
        Log.d("MyRecipe","@RecipeDetail onRestart()");
    }

    @Override
    protected void onResume(){
        super.onResume();
        Log.d("MyRecipe","@RecipeDetail onResume()");
    }

    @Override
    protected void onPause(){
        super.onPause();
        Log.d("MyRecipe","@RecipeDetail onPause()");
    }

    @Override
    protected void onStop(){
        super.onStop();
        Log.d("MyRecipe","@RecipeDetail onStop()");
    }

    @Override
    protected void onDestroy(){
        super.onDestroy();
        Log.d("MyRecipe","@RecipeDetail onDestroy()");
    }
}
