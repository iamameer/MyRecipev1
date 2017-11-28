/*
*  RecipeDetail : Has 2 MODE:
*                 Mode 1 - Empty textbox to enter new Recipe
*                        - with Add, Clear, and Back button
*                 Mode 2 - Loaded selected item from ListView in MainActivity
*                        - with Edit, Save, Delete and Cancel button
* */

package mdpcw3.myrecipev1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class RecipeDetail extends AppCompatActivity {

    //Global Variables
    private EditText txtTitle, txtIns;
    private Button btnBackCancel, btnClearDelete, btnEditSaveAdd;
    private boolean isNewRecipe, isViewRecipe, viewMode, editMode;

    //This method initialize settings and items
    private void init(){
        Log.d("MyRecipe","@RecipeDetail: Initializing settings and items");
        txtTitle = findViewById(R.id.txtTitle);
        txtIns = findViewById(R.id.txtIns);

        btnBackCancel = findViewById(R.id.btnBackCancel);
        btnClearDelete = findViewById(R.id.btnClearDelete);
        btnEditSaveAdd = findViewById(R.id.btnEditSaveAdd);

        isNewRecipe = getIntent().getBooleanExtra("isNewRecipe",false);
        isViewRecipe = getIntent().getBooleanExtra("isViewRecipe",false);
        Log.d("MyRecipe","@RecipeDetail: isNewRecipe: "+isNewRecipe+"|| isViewRecipe: "+isViewRecipe);
        //txtTitle.setText();
        //txtIns.setText();

        //button state

    }

    //This method initialize events
    private void setEvents(){
        Log.d("MyRecipe","@RecipeDetail: Initializing events and listeners");

        //onClick Listener for btnBackCancel
        btnBackCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*if (isNewRecipe){finish();}
                if (isViewRecipe){
                    if(viewMode)
                    finish();
                }*/
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
        btnEditSaveAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //TODO
            }
        });
    }

    //This method add the new recipe into the database
    public void add(){
        Log.d("MyRecipe","@RecipeDetail: Added new Recipe into the database");
        //TODO btnAdd ?
        DBHelper dbHelper = new DBHelper(this,null,null,1);

        String ins = txtIns.getText().toString();
        Recipe recipe = new Recipe(txtTitle.getText().toString(),ins);

        try {
            dbHelper.addRecipe(recipe);
            Log.d("MyRecipe","@RecipeDetail: Successfully added a new recipe");
            Toast.makeText(getApplicationContext(),"New recipe successfully added",Toast.LENGTH_SHORT).show();
        }catch (Exception e){
            Log.d("MyRecipe",e.toString());
            Log.d("MyRecipe","@RecipeDetail: Error adding new recipe");
            Toast.makeText(getApplicationContext(),"Error adding new recipe",Toast.LENGTH_SHORT).show();
            txtIns.setText("");
            txtTitle.setText("");
        }
        finish();
    }

    //This method delete the selected recipe from the database
    public void delete(){
        Log.d("MyRecipe","@RecipeDetail: Deleting a Recipe from the database");
        //TODO btnDelete ?
        DBHelper dbHelper = new DBHelper(this,null,null,1);

        boolean result = dbHelper.deleteRecipe(txtTitle.getText().toString());

            if (result){
                Log.d("MyRecipe","@RecipeDetail: Successfully deleted recipe");
                Toast.makeText(getApplicationContext(),"Recipe successfully deleted",Toast.LENGTH_SHORT).show();
            }else{
                Log.d("MyRecipe","@RecipeDetail: Error deleting recipe");
                Toast.makeText(getApplicationContext(),"Error deleting new recipe",Toast.LENGTH_SHORT).show();
            }
        finish();
    }

    //This method update the selected recipe
    public void save(){
        Log.d("MyRecipe","@RecipeDetail: Updating recipe");
        //TODO btnSave ?
    }

    //This method simply clear the EditText
    public void clear(){
        Log.d("MyRecipe","@RecipeDetail: Updating recipe");
        //TODO btnCear
    }

    //This method goes into edit mode
    public void modeEdit(){
        Log.d("MyRecipe","@RecipeDetail: Entering editing mode");
        //TODO !isViewMode
    }

    //This method goes into read/view mode
    public void modeView(){
        Log.d("MyRecipe","@RecipeDetail: Entering viewing mode");
        //TODO isViewMode
    }

    /*
    *  Activity Lifecycle
    * */
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
