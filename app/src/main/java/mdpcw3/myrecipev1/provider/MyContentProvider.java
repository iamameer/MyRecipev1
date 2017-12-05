/*
 * MyContentProvider: ContentProvider class to interact with database
 *
 * Methods:         : delete(), insert(), query(), update()
 */

package mdpcw3.myrecipev1.provider;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;
import android.text.TextUtils;

import mdpcw3.myrecipev1.DBHelper;
import mdpcw3.myrecipev1.RecipeContract;

public class MyContentProvider extends ContentProvider {

    //Global Variables
    private DBHelper myDB;

    private static final String AUTHORITY =
            "mdpcw3.myrecipe1.provider.MyContentProvider";
    private static final String RECIPE_TABLE = "recipe";
    public static final Uri CONTENT_URI =
            Uri.parse("content://" + AUTHORITY + "/" + RECIPE_TABLE);

    public static final int RECIPE = 1;
    public static final int RECIPE_ID = 2;

    private static final UriMatcher sURIMatcher = new UriMatcher(UriMatcher.NO_MATCH);

    static {
        sURIMatcher.addURI(AUTHORITY, RECIPE_TABLE, RECIPE);
        sURIMatcher.addURI(AUTHORITY, RECIPE_TABLE + "/#",
                RECIPE_ID);
    }

    //empty constructor
    public MyContentProvider() {
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        //Handle requests to delete selected row.
        int uriType = sURIMatcher.match(uri);
        SQLiteDatabase sqlDB = myDB.getWritableDatabase();
        int rowsDeleted = 0;

        switch (uriType) {
            case RECIPE:
                rowsDeleted = sqlDB.delete(RecipeContract.RecipeEntry.TABLE_NAME,
                        selection,
                        selectionArgs);
                break;

            case RECIPE_ID:
                String id = uri.getLastPathSegment();
                if (TextUtils.isEmpty(selection)) {
                    rowsDeleted = sqlDB.delete(RecipeContract.RecipeEntry.TABLE_NAME,
                            RecipeContract.RecipeEntry.COLUMN_NAME_ID + "=" + id,
                            null);
                } else {
                    rowsDeleted = sqlDB.delete(RecipeContract.RecipeEntry.TABLE_NAME,
                            RecipeContract.RecipeEntry.COLUMN_NAME_ID + "=" + id
                                    + " and " + selection,
                            selectionArgs);
                }
                break;
            default:
                throw new IllegalArgumentException("Unknown URI: " + uri);
        }
        getContext().getContentResolver().notifyChange(uri, null);
        return rowsDeleted;
        //throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public String getType(Uri uri) {
        // Handle requests for the MIME type of the data
        // at the given URI.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {
        //Handle requests to insert a new row.
        int uriType = sURIMatcher.match(uri);

        SQLiteDatabase sqlDB = myDB.getWritableDatabase();

        long id = 0;
        switch (uriType) {
            case RECIPE:
                id = sqlDB.insert(RecipeContract.RecipeEntry.TABLE_NAME, null, values);
                break;
            default:
                throw new IllegalArgumentException("Unknown URI: " + uri);
        }
        getContext().getContentResolver().notifyChange(uri, null);
        return Uri.parse(RecipeContract.RecipeEntry.TABLE_NAME + "/" + id);
    }

    @Override
    public boolean onCreate() {
        //Initialize your content provider on startup.
        myDB = new DBHelper(getContext(), null, null, 1);
        return false;
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection,
                        String[] selectionArgs, String sortOrder) {
        //Handle query requests from clients.
        SQLiteQueryBuilder queryBuilder = new SQLiteQueryBuilder();
        queryBuilder.setTables(RecipeContract.RecipeEntry.TABLE_NAME);

        int uriType = sURIMatcher.match(uri);

        switch (uriType) {
            case RECIPE_ID:
                queryBuilder.appendWhere(RecipeContract.RecipeEntry.COLUMN_NAME_ID + "="
                        + uri.getLastPathSegment());
                break;
            case RECIPE:
                break;
            default:
                throw new IllegalArgumentException("Unknown URI");
        }

        Cursor cursor = queryBuilder.query(myDB.getReadableDatabase(),
                projection, selection, selectionArgs, null, null, sortOrder);
        cursor.setNotificationUri(getContext().getContentResolver(), uri);
        return cursor;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection,
                      String[] selectionArgs) {
        // Handle requests to update selected row.
        int uriType = sURIMatcher.match(uri);
        SQLiteDatabase sqlDB = myDB.getWritableDatabase();
        int rowsUpdated = 0;

        switch (uriType) {
            case RECIPE:
                rowsUpdated =
                        sqlDB.update(RecipeContract.RecipeEntry.TABLE_NAME,
                                values,
                                selection,
                                selectionArgs);
                break;
            case RECIPE_ID:
                String id = uri.getLastPathSegment();
                if (TextUtils.isEmpty(selection)) {
                    rowsUpdated =
                            sqlDB.update(RecipeContract.RecipeEntry.TABLE_NAME,
                                    values,
                                    RecipeContract.RecipeEntry.COLUMN_NAME_ID + "=" + id,
                                    null);
                } else {
                    rowsUpdated =
                            sqlDB.update(RecipeContract.RecipeEntry.TABLE_NAME,
                                    values,
                                    RecipeContract.RecipeEntry.COLUMN_NAME_ID + "=" + id
                                            + " and "
                                            + selection,
                                    selectionArgs);
                }
                break;
            default:
                throw new IllegalArgumentException("Unknown URI: " + uri);
        }
        getContext().getContentResolver().notifyChange(uri, null);
        return rowsUpdated;
    }
}
