package Cache


import Models.FromTask4
import android.content.Context
import android.content.SharedPreferences
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

object MySharedPreference {
    private const val NAME = "forCache"
    private const val MODE = Context.MODE_PRIVATE
    private lateinit var preferences: SharedPreferences

    fun init(context: Context){
        preferences = context.getSharedPreferences(NAME, MODE)
    }
    private inline fun SharedPreferences.edit(operations:(SharedPreferences.Editor)-> Unit){
        val editor = edit()
        operations(editor)
        editor.apply()
    }


    var myObject:ArrayList<FromTask4>
        get() = Gson().fromJson(preferences.getString("phone","[]")!!,object : TypeToken<ArrayList<FromTask4>>(){}.type)
        set(value) = preferences.edit{
            it.putString("phone", Gson().toJson(value))
        }


}