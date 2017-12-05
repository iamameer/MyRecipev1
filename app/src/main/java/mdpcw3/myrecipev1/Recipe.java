/*
 * Recipe.java : Basic abstract class to create Recipe as object
 */


package mdpcw3.myrecipev1;

public class Recipe {
    private int _id;
    private String _title;
    private String _recipe;

    public Recipe(){}

    public Recipe(int id, String title, String recipe){
        this._id = id;
        this._title = title;
        this._recipe = recipe;
    }

    public Recipe(String title, String recipe){
        this._title = title;
        this._recipe = recipe;
    }

    //return the id
    public int get_id() {
        return this._id;
    }

    //set the id
    public void set_id(int _id) {
        this._id = _id;
    }

    //return the title
    public String get_title() {
        return this._title;
    }

    //set the title
    public void set_title(String _title) {this._title = _title;}

    //return the recipe
    public String get_recipe() {
        return this._recipe;
    }

    //set the recipe
    public void set_recipe(String _recipe) {
        this._recipe = _recipe;
    }
}


