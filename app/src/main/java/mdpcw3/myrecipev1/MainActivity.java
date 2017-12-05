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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    //Global Variables
    private EditText txtSearch;
    private Button btnSearch, btnExit, btnAdd;
    private ListView listView;

    //This method initialize settings and items
    private void init(){
        Log.d("MyRecipe","=MainActivity: Initializing settings and items");
        txtSearch = findViewById(R.id.txtSearch);

        btnSearch = findViewById(R.id.btnSearch);
        btnExit = findViewById(R.id.btnExit);
        btnAdd = findViewById(R.id.btnAdd);

        listView = findViewById(R.id.listView);
        listView.setBackgroundColor(getResources().getColor(R.color.smoothblue));
    }

    //This method initialize events
    private void setEvents(){
        Log.d("MyRecipe","=MainActivity: Initializing events and listeners");
        //Search method for Search Button
        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!txtSearch.getText().toString().equals("")){
                    search();
                }else{
                    Log.d("MyRecipe","=MainActivity: Empty searchbox");
                    Toast.makeText(getApplicationContext(),"Empty searchbox",Toast.LENGTH_SHORT).show();
                }
            }
        });

        //Exit method for Exit button
        btnExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        //Add method for Add button
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {newRecipe();}
        });

        //ListView event Listener
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {viewRecipe(i);}
        });
    }

    //This method display the database in the listView
    public void display(){
        Log.d("MyRecipe","=MainActivity: Retrieving recipe from from database");
        DBHelper dbHelper = new DBHelper(this,null,null,1);
        ArrayList<Recipe> recipeArrayList = dbHelper.display();

        if (recipeArrayList!=null){
            List<String> title = new ArrayList<String>();
            for (int i= 0; i<recipeArrayList.size(); i++){
                title.add(recipeArrayList.get(i).get_title());
            }
            ArrayAdapter<String> adapter =
                    new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,title);
            listView.setAdapter(adapter);
        }else{
            Toast.makeText(getApplicationContext(),
                    "\""+txtSearch.getText().toString()+"\" no item in Database",Toast.LENGTH_SHORT).show();
            Log.d("MyRecipe","=MainActivity: Empty database");
        }

    }

    //This method refine the display of the database in the listView
    public void search(){
        Log.d("MyRecipe","=MainActivity: Filtering/Search");
        DBHelper dbHelper = new DBHelper(this,null,null,1);
        Recipe recipe = dbHelper.findRecipe(txtSearch.getText().toString());

        if (recipe != null){
            //creating a string array for the recipe
            //https://android--code.blogspot.my/2015/08/android-listview-add-items.html
            List<String> result = new ArrayList<String>();
            result.add(recipe.get_title());
            ArrayAdapter<String> adapter =
                    new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,result);
            listView.setAdapter(adapter);
        }else{
            Toast.makeText(getApplicationContext(),
                    "\""+txtSearch.getText().toString()+"\" not found",Toast.LENGTH_SHORT).show();
        }
    }

    //This method launch RecipeDetail as new Intent, with passing some variable
    public void newRecipe(){
        Log.d("MyRecipe","=MainActivity: Launching RecipeDetail--> New Recipe");
        Intent intent = new Intent(getApplicationContext(),RecipeDetail.class);
        intent.putExtra("isNewRecipe",true);
        startActivity(intent);
    }

    //This method view the selected recipe
    public void viewRecipe(int position){
        Log.d("MyRecipe","=MainActivity: Launching RecipeDetail--> View Recipe : "+listView.getItemAtPosition(position));
        //^implement search()
        DBHelper dbHelper = new DBHelper(this,null,null,1);
        Recipe recipe = dbHelper.findRecipe(listView.getItemAtPosition(position).toString());

        if (recipe != null){
            Intent intent = new Intent(getApplicationContext(),RecipeDetail.class);
            intent.putExtra("isNewRecipe",false);
            intent.putExtra("id",recipe.get_id());
            intent.putExtra("title",recipe.get_title());
            intent.putExtra("recipe",recipe.get_recipe());
            startActivity(intent);
        }else{
            Log.d("MyRecipe","=MainActivity: Error viewing item");
            Toast.makeText(getApplicationContext(),"Error viewing item",Toast.LENGTH_SHORT).show();
        }
    }


    //Activity Lifecycle onCreate()
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d("MyRecipe","=MainActivity onCreate()");

        //Initialising variables and setting up events
        init();
        setEvents();
    }

    //Activity Lifecycle onStart()
    @Override
    protected void onStart(){
        super.onStart();
        Log.d("MyRecipe","=MainActivity onStart()");
    }

    //Activity Lifecycle onRestart()
    @Override
    protected void onRestart(){
        super.onRestart();
        Log.d("MyRecipe","=MainActivity onRestart()");
    }

    //Activity Lifecycle onResume()
    @Override
    protected void onResume(){
        super.onResume();
        Log.d("MyRecipe","=MainActivity onResume()");
        //displaying database into listview
        display();
        Log.d("MyRecipe","=MainActivity DATABASE display()");
    }

    //Activity Lifecycle onPause()
    @Override
    protected void onPause(){
        super.onPause();
        Log.d("MyRecipe","=MainActivity onPause()");
    }

    //Activity Lifecycle onStop()
    @Override
    protected void onStop(){
        super.onStop();
        Log.d("MyRecipe","=MainActivity onStop()");
    }

    //Activity Lifecycle onDestroy()
    @Override
    protected void onDestroy(){
        super.onDestroy();
        Log.d("MyRecipe","=MainActivity onDestroy()");
    }

}
