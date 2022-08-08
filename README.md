GodotUniversalIntentPlugin for Godot 3.2.2+
====================================
____________________________________


Android plugin for Godot 3.2.2 and above.  

Proof of concept project.
You can create your own android intents and get the result.

As of this time this plugin can only create the most basic intents.

**Godot example included:**

* QR code reader
* Web search
* Dial phone number
* Open GPS location

[Example scenes](./GodotIntentExamples/example_scenes/)

See GodotExample for more info (Godot 3.4.4).

Donation
========

I accept donation in form of your code examples or your time to expand this plugin.

Installation
============

Follow these instructions for android custom build, [ official documentation](https://docs.godotengine.org/en/stable/getting_started/workflow/export/android_custom_build.html "documentation").

1. If exists, unzip the precompiled release zip in the release folder to your android plugin folder:
*release/godotuniversalintentplugin_for_godot_[your Godot version].zip* to *[your godot project]/android/plugins/*

2. Activate plugin in Godot by enable "Project" -> "Export" -> "Options", "Use Custom Build" and "Godot Universal Intent" plugin

Generate plugin .aar file
-------------------------

If there is no GodotUniversalIntentPlugin release for your Godot version, you need to generate new plugin .aar file.  
Follow these instruction: [ official documentation](https://docs.godotengine.org/en/stable/tutorials/plugins/android/android_plugin.html "documentation").

In short follow these steps:

1. Download [ AAR library for Android plugins](https://godotengine.org/download/windows "Godot download").

2. Copy .aar file to *GodotUniversalIntentPlugin/godot-lib.release/* and rename it to *godot-lib.release.aar*

3. Compile the project:

	Open command window and *cd* into *GodotUniversalIntentPlugin* and run command below
	
	* Windows:
	
		gradlew.bat assembleRelease
		
	* Linux:
	
		./gradlew assembleRelease
	
4. Copy the newly created .aar file to your plugin directory:

*/GodotUniversalIntentPlugin/godotuniversalintent/build/outputs/aar/GodotUniversalIntent.release.aar* to *[your godot project]/android/plugins/*

(don't forget to also copy *GodotUniversalIntent.gdap* from any release zip to *[your godot project]/android/plugins/*)


# Plugin API

			
Methods
-------

***intent(intent as string)***  
Create the intent

***putExtra(extra_option as string, value as string)***  
Add aditional option to the intent.

***addFlag(flag as string)***  
Add aditional flag to the intent.

***addCategory(category as string)***

***setType(type as string)***
Set an explicit MIME data type.

***setData(data as string)***

Emitted signals
---------------

***on_main_activity_result***  
Returns a Dictionary with some of the intent data

***error***  
Returns any error as string

Example code
============
Open cordinates in google map:

```python

extends Control

var plugin
var plugin_name = "GodotUniversalIntent"


# Called when the node enters the scene tree for the first time.
func _ready():
	if Engine.has_singleton(plugin_name):
		plugin = Engine.get_singleton(plugin_name)
	else:
		print("Could not load plugin: ", plugin_name)

	if plugin:
		plugin.connect("on_main_activity_result", self, "_on_main_activity_result")
		plugin.connect("error", self, "_on_error")

func _on_main_activity_result(result):
	print("RESULT:", result)
	
func _on_error(e):
	""" Plugin has returned som error """
	print(e)

func _on_ButtonShowGPSPosition_pressed():
	if plugin:
		# Create a new intent
		plugin.intent("android.intent.action.VIEW") # Same as Intent.ACTION_VIEW
		
		# This Android code snippet will open up a dialog which allow you to pick a map app to show the address you passed in the intent.
		#var uri = "geo:0,0?q=replace+this+with+an+address"
		
		# This Android code snippet will launch the google map with direction to the address you passed in.
		#var uri = "google.navigation:q=replace+this+with+an+address"
		
		# Passing in the latitude,longitude to the map app.
		var uri = "geo:-21.805149,-49.089977"
		plugin.setData(uri)
		
		# It's now time to start the activity, when finished "on_main_activity_result" signal is emited
		# "android.intent.action.VIEW" does not return any data and on_main_activity_result will not be called
		plugin.startActivityForResult()
	else:
		print(plugin_name, " plugin not loaded!")
```

