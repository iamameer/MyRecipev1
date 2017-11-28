 /*
 * RecipeContract.java:  Class Contract for the database table
 */

package mdpcw3.myrecipev1;

import android.provider.BaseColumns;

public final class RecipeContract {
    // To prevent someone from accidentally instantiating the contract class,
    // make the constructor private.
    private RecipeContract() {
    }

    /* Inner class that defines the table contents */
    public static class RecipeEntry implements BaseColumns {
        public static final String DATABASE_NAME = "myRecipe.db";
        public static final String TABLE_NAME = "recipe";
        public static final String COLUMN_NAME_ID = "_id";
        public static final String COLUMN_NAME_TITLE = "title";
        public static final String COLUMN_NAME_RECIPE = "recipe";
    }
}