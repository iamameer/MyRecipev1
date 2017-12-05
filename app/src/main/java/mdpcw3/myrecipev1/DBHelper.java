/*
 * DBHelper.java  : Handle the DB with queries operation
 *
 * Methods        : addRecipe(), findRecipe(), deleteRecipe(), updateRecipe(), display()
 *
 */

package mdpcw3.myrecipev1;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.util.ArrayList;

import mdpcw3.myrecipev1.provider.MyContentProvider;

public class DBHelper extends SQLiteOpenHelper {

    //Global variables
    private ContentResolver myCR;

    //The database version.
    public static final int DATABASE_VERSION = 1;


    public DBHelper(Context context,String name,SQLiteDatabase.CursorFactory factory, int version) {
        super(context, RecipeContract.RecipeEntry.DATABASE_NAME, null, DATABASE_VERSION);
        myCR = context.getContentResolver();
    }

    //this method initializes the table
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String SQL_CREATE_ENTRIES = "CREATE TABLE " +
                RecipeContract.RecipeEntry.TABLE_NAME + "(" +
                RecipeContract.RecipeEntry.COLUMN_NAME_ID + " INTEGER PRIMARY KEY, " + //TODO AutoIncrement?
                RecipeContract.RecipeEntry.COLUMN_NAME_TITLE + " TEXT," +
                RecipeContract.RecipeEntry.COLUMN_NAME_RECIPE + " TEXT " + ")";
        sqLiteDatabase.execSQL(SQL_CREATE_ENTRIES);
    }

    //this method detect any upgrade version
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+ RecipeContract.RecipeEntry.TABLE_NAME);
        onCreate(sqLiteDatabase);
    }

    //this method add a new recipe into the database, respective column
    public void addRecipe(Recipe recipe){
        ContentValues values = new ContentValues();
        values.put(RecipeContract.RecipeEntry.COLUMN_NAME_TITLE,recipe.get_title());
        values.put(RecipeContract.RecipeEntry.COLUMN_NAME_RECIPE,recipe.get_recipe());

        myCR.insert(MyContentProvider.CONTENT_URI,values);
    }

    //this method return a Recipe as object
    public Recipe findRecipe(String title){
        String[] projection = {
                RecipeContract.RecipeEntry.COLUMN_NAME_ID,
                RecipeContract.RecipeEntry.COLUMN_NAME_TITLE,
                RecipeContract.RecipeEntry.COLUMN_NAME_RECIPE};

        String selection = "title = \"" +title+"\"";

        Cursor cursor = myCR.query(
                MyContentProvider.CONTENT_URI,
                projection,
                selection,
                null,
                null);

        Recipe recipe = new Recipe();

        try {
            if (cursor.moveToFirst()){
                cursor.moveToFirst();
                recipe.set_id(Integer.parseInt(cursor.getString(0)));
                recipe.set_title(cursor.getString(1));
                recipe.set_recipe(cursor.getString(2));
                cursor.close();
            }else{
                recipe = null;
            }
        }catch (Exception e){
            Log.d("MyRecipe",e.toString());
        }
        return  recipe;
    }

    //this method deletes the specified recipe
    public boolean deleteRecipe(String title){
        boolean result = false;
        String selection = "title = \"" + title+ "\"";

        int rowsDeleted = myCR.delete(MyContentProvider.CONTENT_URI,selection,null);

        if (rowsDeleted >0){result = true;}
        return result;
    }

    //this method update the current recipe
    public boolean updateRecipe(int id,String title, String text){
        boolean result = false;
        String selection = "_id = \""+id+" \"";
        ContentValues values = new ContentValues();
        values.put(RecipeContract.RecipeEntry.COLUMN_NAME_TITLE,title);
        values.put(RecipeContract.RecipeEntry.COLUMN_NAME_RECIPE,text);

        int rowsUpdated = myCR.update(MyContentProvider.CONTENT_URI,values,selection,null);

        if(rowsUpdated>0){result=true;}
        return result;
    }

    //this method returns array of Recipe(object) to be displayed
    public ArrayList<Recipe> display(){
        String[] projection = {
                RecipeContract.RecipeEntry.COLUMN_NAME_ID,
                RecipeContract.RecipeEntry.COLUMN_NAME_TITLE,
                RecipeContract.RecipeEntry.COLUMN_NAME_RECIPE};

        Cursor cursor = myCR.query(
                MyContentProvider.CONTENT_URI,projection,null,null,null);

        ArrayList<Recipe> result = new ArrayList<Recipe>();
        try{
            if(cursor.moveToFirst()){
                Log.d("MyRecipe","cursor : inside IF");
                do{
                    Recipe recipe = new Recipe();
                    Log.d("MyRecipe","cursor: inside DO");
                    recipe.set_id(Integer.parseInt(cursor.getString(0)));
                    recipe.set_title(cursor.getString(1));
                    recipe.set_recipe(cursor.getString(2));
                    result.add(recipe);
                }while(cursor.moveToNext());
            }cursor.close();
        }catch (Exception e){
            Log.d("MyRecipe",e.toString());
        }
        return result;
    }
}