package com.example.eventreminderapp


import android.annotation.SuppressLint
import android.content.Context
import android.content.SharedPreferences

class SharedPrefUtil
/**
 * Constructor prevents any other class from instantiating.
 */
private constructor() {
    private val preferences: SharedPreferences
    private val editor: SharedPreferences.Editor

    init {
        preferences = context!!.getSharedPreferences(preferenceName, Context.MODE_PRIVATE)
        editor = preferences.edit()
        editor.commit()

    }

    /**
     * Write boolean preferences.
     *
     * @param preferenceName  The unique name of preference.
     * @param preferenceValue The value to save in preference.
     */
    fun write(preferenceName: String, preferenceValue: Boolean) {

        editor.putBoolean(preferenceName, preferenceValue)
        editor.apply()
    }

    /**
     * Read boolean preferences
     *
     * @param preferenceName The unique name of preference.
     * @param defaultValue   The value if there is no saved one.
     * @return The value of saved preference.
     */
    fun read(preferenceName: String, defaultValue: Boolean): Boolean {
        return preferences.getBoolean(preferenceName, defaultValue)
    }


    /**
     * Write string preferences.
     *
     * @param preferenceName  The unique name of preference.
     * @param preferenceValue The value to save in preference.
     */
    fun write(preferenceName: String, preferenceValue: String) {
        editor.putString(preferenceName, preferenceValue)
        editor.apply()
    }

    /**
     * Read string preferences.
     *
     * @param preferenceName The unique name of preference.
     * @return The value of saved preference.
     */
    fun read(preferenceName: String, defaultValue: String): String? {
        return preferences.getString(preferenceName, defaultValue)
    }

    /**
     * Write integer preferences.
     *
     * @param preferenceName  The unique name of preference.
     * @param preferenceValue The value to save in preference.
     */

    fun write(preferenceName: String, preferenceValue: Int) {

        editor.putInt(preferenceName, preferenceValue)
        editor.apply()
    }

    /**
     * Read string preferences.
     *
     * @param preferenceName The unique name of preference.
     * @return The value of saved preference.
     */

    fun read(preferenceName: String, defaultValue: Int): Int {

        return preferences.getInt(preferenceName, defaultValue)
    }

    /**
     * Remove one or more preference from shared preferences.
     *
     * @param preferencesNames Name of preference(s) you want to remove
     */
    fun remove(vararg preferencesNames: String) {
        for (preferenceName in preferencesNames) {
            editor.remove(preferenceName)
            editor.apply()
        }
    }

    companion object {
        @SuppressLint("StaticFieldLeak")
        private var context: Context? = null
        @SuppressLint("StaticFieldLeak")
        private var instance: SharedPrefUtil? = null
        private var preferenceName: String? = null

        /**
         * * Make sure that there is only one SharedPrefUtil instance.
         *
         * @param context The android Context instance.
         * @return Returns only one instance of SharedPrefUtil.
         */
        fun getInstance(context: Context): SharedPrefUtil {

            SharedPrefUtil.context = context
            SharedPrefUtil.preferenceName = context.packageName
            if (instance == null) {
                instance = SharedPrefUtil()
            }
            return instance as SharedPrefUtil
        }
    }


}
