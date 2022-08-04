package com.gmail.lamelynx.godotuniversalintent

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.collection.ArraySet
import org.godotengine.godot.Dictionary
import org.godotengine.godot.Godot
import org.godotengine.godot.plugin.GodotPlugin
import org.godotengine.godot.plugin.SignalInfo


/**
 * Author: Lamelynx
 * Mail: lamelynx@gmail.com
 */


class GodotUniversalIntent(activity: Godot) : GodotPlugin(activity) {

    private val TAG: String = "godot"
    private val REQUEST_ID = 1099

    var currentIntent: Intent? = null

    init {
        Log.d(TAG, "init GodotUniversalIntent")
    }

    override fun getPluginName(): String {
        // Plugin name
        return "GodotUniversalIntent"
    }

    override fun getPluginMethods(): List<String> {
        // Available plugin functions to use in Godot

        return listOf("intent", "setData", "putExtra", "addFlags", "startActivityForResult")
    }

    override fun getPluginSignals(): Set<SignalInfo> {
        val signals: MutableSet<SignalInfo> = ArraySet()
        signals.add(SignalInfo("on_main_activity_result", Dictionary::class.java))
        signals.add(SignalInfo("error", String::class.java))

        return signals
    }

    fun intent(type:String){
        /**
         * Create intent
         */
        Log.d(TAG, "Call - intent($type)")

        currentIntent = Intent(type)
   }

// TODO Godot plugon cant handle two functions with the same name?
//  Use setData(uri) instead.
    /*fun intent(type:String, data:String){
        /**
         * Create intent
         */

        Log.d(TAG, "Call - intent($type, $data)")

        val uri:Uri = Uri.parse(action)
        currentIntent = Intent(type, uri)
    }*/
    //fun putExtra(extra:String, value:Boolean){
    //    currentIntent?.putExtra(extra, value)
    //}

    fun setData(data:String){
        Log.d(TAG, "Call - setData($data)")
        val uri:Uri = Uri.parse(data)  // TODO Uri.parse() i deprecated
        currentIntent?.setData(uri)
    }

    fun putExtra(extra:String, value:String){
        Log.d(TAG, "Call - putExtra($extra, $value)")
        currentIntent?.putExtra(extra, value)
    }

    fun addFlags(flag:Int){
        Log.d(TAG, "Call - addFlags($flag)")
        currentIntent?.addFlags(flag)
    }

    fun setType(type:String){
        Log.d(TAG, "Call - setType($type)")
        currentIntent?.setType(type)
    }

    fun addCategory(category:String){
        Log.d(TAG, "Call - addCategory($category)")
        currentIntent?.addCategory(category)
    }

    fun startActivityForResult() {
        activity?.startActivityForResult(currentIntent, REQUEST_ID)
    }

    @RequiresApi(Build.VERSION_CODES.KITKAT)
    override fun onMainActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        /**
         * This will be called by Godot class
         */
        if (requestCode == REQUEST_ID && resultCode == Activity.RESULT_OK && data != null) {
            Log.d(TAG, "Received result:" + data.data.toString())

            val extras = Dictionary()
            val keys: Set<String> = data.extras!!.keySet()
            for (key in keys) {
                extras[key] = data.extras!!.get(key)
            }
            Log.d(TAG, "Received result:" + extras.toString())
            emitSignal("on_main_activity_result", extras)
        }
    }
}