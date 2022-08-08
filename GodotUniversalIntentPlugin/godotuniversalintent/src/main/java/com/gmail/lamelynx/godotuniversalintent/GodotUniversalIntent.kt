package com.gmail.lamelynx.godotuniversalintent

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.util.Log
import androidx.collection.ArraySet
import org.godotengine.godot.Dictionary
import org.godotengine.godot.Godot
import org.godotengine.godot.plugin.GodotPlugin
import org.godotengine.godot.plugin.SignalInfo


/**
 * Author: Lamelynx
 * Mail: lamelynx@gmail.com
 */
private const val TAG: String = "godot"
private const val REQUEST_ID = 1099

class GodotUniversalIntent(activity: Godot) : GodotPlugin(activity) {
    var mIntent: Intent? = null
    var mResult: Intent? = null

    init {
        Log.d(TAG, "init GodotUniversalIntent")
    }

    override fun getPluginName(): String {
        // Plugin name
        return "GodotUniversalIntent"
    }

    override fun getPluginMethods(): List<String> {
        // Available plugin functions to use in Godot

        return listOf(
            "intent",
            "setData",
            "putExtra",
            "addFlags",
            "setType",
            "addCategory",
            "startActivityForResult"
        )
    }

    override fun getPluginSignals(): Set<SignalInfo> {
        val signals: MutableSet<SignalInfo> = ArraySet()
        signals.add(SignalInfo("on_main_activity_result", Dictionary::class.java))
        signals.add(SignalInfo("error", String::class.java))

        return signals
    }

    /* TODO onMainActivityResult() is deprecated.
        min SDK 21 (godot min SDK 19), To be implemented at a later date
    var resultLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            // There are no request codes
            val data: Intent? = data.data
            }
        }
    }*/


    fun intent(type:String){
        /**
         * Create intent
         */
        Log.d(TAG, "Call - intent($type)")

        /** TODO onMainActivityResult() is deprecated.
             min SDK 21 (godot is min SDK 19), To be implemented at a later date
        val intent = Intent(Intent.ACTION_OPEN_DOCUMENT).apply {
            addCategory(Intent.CATEGORY_OPENABLE)
        }
        resultLauncher.launch(intent)
        */

        mIntent = Intent(type)
        mResult = null
   }

// TODO Godot plugin can't handle two functions with the same name with different arguments
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
        val uri:Uri = Uri.parse(data)
        mIntent?.data = uri
    }

    fun putExtra(extra:String, value:String){
        Log.d(TAG, "Call - putExtra($extra, $value)")
        mIntent?.putExtra(extra, value)
    }

    fun addFlags(flag:Int){
        Log.d(TAG, "Call - addFlags($flag)")
        mIntent?.addFlags(flag)
    }

    fun setType(type:String){
        Log.d(TAG, "Call - setType($type)")
        mIntent?.type = type
    }

    fun addCategory(category:String){
        Log.d(TAG, "Call - addCategory($category)")
        mIntent?.addCategory(category)
    }

    fun startActivityForResult() {
        activity?.startActivityForResult(mIntent, REQUEST_ID)
        mIntent = null
    }

    override fun onMainActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        /**
         * This will be called by Godot class
         */
        if (requestCode == REQUEST_ID && resultCode == Activity.RESULT_OK && data != null) {
            Log.d(TAG, "Received result:" + data.data.toString())

            // Store the result if any function need more advanced feature
            mResult = data

            val ret = Dictionary()

            ret["data"] = data.data.toString()

            val extras = Dictionary()
            if (data.extras != null) {
                val keys: Set<String> = data.extras!!.keySet()
                for (key in keys) {
                    extras[key] = data.extras!!.get(key)
                }
            }
            ret["extras"] = extras

            val tmp = data.clipData
            val clipData = mutableListOf<String>()
            if (tmp != null) {
                (0 until tmp.itemCount).forEach { i ->
                    val uri = tmp.getItemAt(i)?.uri
                    if (uri != null){
                       clipData.add(uri.toString())
                    }
                }
            }
            ret["clipData"] = clipData

            emitSignal("on_main_activity_result", ret)
        }
    }
}