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

public class RecipeDetail extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_detail);
    }
}
