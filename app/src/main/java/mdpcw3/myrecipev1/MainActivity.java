/*
*  MainActivity : Displays collection of available Recipe
*                 Has Search function
*                 Has Exit button
*                 Can Add new recipe via NEW RECIPE button
* */

package mdpcw3.myrecipev1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    //Global Variables

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

}
