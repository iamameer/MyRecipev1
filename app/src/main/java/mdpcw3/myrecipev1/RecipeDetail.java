/*
*  RecipeDetail : Has 2 MODE:
*                 Mode 1 - Empty textbox to enter new Recipe
*                        - with Add, Clear, and Back button
*                 Mode 2 - Loaded selected item from ListView in MainActivity
*                        - with Edit, Save, Delete and Cancel button
*
*  Methods      : init(), setEvents(), add(), delete(), save(), clear(),
*                 modeNew(), modeEdit(), modeView(),
*
* */

package mdpcw3.myrecipev1;

import android.graphics.Color;
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
    private boolean isNewRecipe;
    private int id;
    private String tempTitle, tempIns;

    //This method initialize settings and items
    private void init(){
        Log.d("MyRecipe","@RecipeDetail: Initializing settings and items");
        txtTitle = findViewById(R.id.txtTitle);
        txtIns = findViewById(R.id.txtIns);

        btnBackCancel = findViewById(R.id.btnBackCancel);
        btnClearDelete = findViewById(R.id.btnClearDelete);
        btnEditSaveAdd = findViewById(R.id.btnEditSaveAdd);

        btnEditSaveAdd.setBackgroundColor(getResources().getColor(R.color.hologreen));
        btnClearDelete.setBackgroundColor(getResources().getColor(R.color.holoblue));
        btnBackCancel.setBackgroundColor(getResources().getColor(R.color.holoorange));

        isNewRecipe = getIntent().getBooleanExtra("isNewRecipe",false);

        Log.d("MyRecipe","@RecipeDetail: isNewRecipe: "+isNewRecipe);
        if (isNewRecipe){
            modeNew();
        }else{
            id = getIntent().getIntExtra("id",0);
            txtTitle.setText(getIntent().getStringExtra("title"));
            txtIns.setText(getIntent().getStringExtra("recipe"));
            modeView();
        }

    }

    //This method initialize events
    private void setEvents(){
        Log.d("MyRecipe","@RecipeDetail: Initializing events and listeners");

        //onClick Listener for btnBackCancel
        btnBackCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (btnBackCancel.getText().toString().equals("Back")){
                    Log.d("MyRecipe","@RecipeDetail: btnBack()");
                        finish();
                }else if (btnBackCancel.getText().toString().equals("Cancel")){
                    Log.d("MyRecipe","@RecipeDetail: btnCancel()");
                    if(isNewRecipe){
                        finish();
                        Log.d("MyRecipe","@RecipeDetail: No new recipe added");
                    }else {
                        txtTitle.setText(tempTitle);
                        txtIns.setText(tempIns);
                        modeView();
                    }
                }
            }
        });

        //onClick Listener for btnClearDelete
        btnClearDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (btnClearDelete.getText().toString().equals("Clear")){
                    Log.d("MyRecipe","@RecipeDetail: btnClear()");
                    clear();
                }else if (btnClearDelete.getText().toString().equals("Delete")){
                    Log.d("MyRecipe","@RecipeDetail: btnDelete()");
                    delete();
                }
            }
        });

        //onClick Listener for btnEditSaveSubmit
        btnEditSaveAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (btnEditSaveAdd.getText().toString().equals("Edit")){
                    Log.d("MyRecipe","@RecipeDetail: btnEdit()");
                    modeEdit();
                    tempTitle = txtTitle.getText().toString();
                    tempIns = txtIns.getText().toString();
                }else if (btnEditSaveAdd.getText().toString().equals("Save")){
                    Log.d("MyRecipe","@RecipeDetail: btnSave()");
                    if (tempTitle.equals(txtTitle.getText().toString()) && tempIns.equals(txtIns.getText().toString())){
                        Log.d("MyRecipe","@RecipeDetail: No changes detected!");
                        Toast.makeText(getApplicationContext(),"No changes detected",Toast.LENGTH_SHORT).show();
                    }else{
                        save(id);
                    }
                }else if (btnEditSaveAdd.getText().toString().equals("Add")){
                    Log.d("MyRecipe","@RecipeDetail: btnAdd()");
                    add();
                }
            }
        });
    }

    //This method add the new recipe into the database
    public void add(){
        Log.d("MyRecipe","@RecipeDetail: Added new Recipe into the database");
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
    public void save(int id){
        Log.d("MyRecipe","@RecipeDetail: Updating recipe");
        DBHelper dbHelper = new DBHelper(this,null,null,1);

        boolean result = dbHelper.updateRecipe(id,txtTitle.getText().toString(),txtIns.getText().toString());

        if (result){
            Log.d("MyRecipe","@RecipeDetail: Successfully updated recipe");
            Toast.makeText(getApplicationContext(),"Recipe successfully updated",Toast.LENGTH_SHORT).show();
        }else{
            Log.d("MyRecipe","@RecipeDetail: Error updating recipe");
            Toast.makeText(getApplicationContext(),"Error updating recipe",Toast.LENGTH_SHORT).show();
        }
        modeView();
    }

    //This method simply clear the EditText
    public void clear(){
        Log.d("MyRecipe","@RecipeDetail: clear()");
        txtIns.setText("");
        txtTitle.setText("");
    }

    //This method goes into new mode
    public void modeNew(){
        Log.d("MyRecipe","@RecipeDetail: Entering new mode");
        txtTitle.setText("");
        txtIns.setText("");
        txtTitle.setEnabled(true);
        txtIns.setEnabled(true);

        btnEditSaveAdd.setText(R.string.submit);
        btnClearDelete.setText(R.string.clear);
        btnBackCancel.setText(R.string.cancel);
    }

    //This method goes into edit mode
    public void modeEdit(){
        Log.d("MyRecipe","@RecipeDetail: Entering editing mode");
        txtTitle.setEnabled(true);
        txtIns.setEnabled(true);

        txtTitle.setBackgroundColor(Color.WHITE);
        txtIns.setBackgroundColor(Color.WHITE);

        btnEditSaveAdd.setText(R.string.save);
        btnClearDelete.setText(R.string.clear);
        btnBackCancel.setText(R.string.cancel);
    }

    //This method goes into read/view mode
    public void modeView(){
        Log.d("MyRecipe","@RecipeDetail: Entering viewing mode");
        txtTitle.setEnabled(false);
        txtIns.setEnabled(false);

        txtTitle.setBackgroundColor(getResources().getColor(R.color.smoothgreen));
        txtIns.setBackgroundColor(getResources().getColor(R.color.smoothgreen));

        btnEditSaveAdd.setText(R.string.edit);
        btnClearDelete.setText(R.string.delete);
        btnBackCancel.setText(R.string.back);
    }

    //Activity Lifecycle onCreate()
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_detail);
        Log.d("MyRecipe","@RecipeDetail onCreate()");

        //Initialising variables and setting up events
        init();
        setEvents();

    }

    //Activity Lifecycle onStart()
    @Override
    protected void onStart(){
        super.onStart();
        Log.d("MyRecipe","@RecipeDetail onStart()");
    }

    //Activity Lifecycle onRestart()
    @Override
    protected void onRestart(){
        super.onRestart();
        Log.d("MyRecipe","@RecipeDetail onRestart()");
    }

    //Activity Lifecycle onResume()
    @Override
    protected void onResume(){
        super.onResume();
        Log.d("MyRecipe","@RecipeDetail onResume()");
    }

    //Activity Lifecycle onPause()
    @Override
    protected void onPause(){
        super.onPause();
        Log.d("MyRecipe","@RecipeDetail onPause()");
    }

    //Activity Lifecycle onStop()
    @Override
    protected void onStop(){
        super.onStop();
        Log.d("MyRecipe","@RecipeDetail onStop()");
    }

    //Activity Lifecycle onDestroy()
    @Override
    protected void onDestroy(){
        super.onDestroy();
        Log.d("MyRecipe","@RecipeDetail onDestroy()");
    }
}
