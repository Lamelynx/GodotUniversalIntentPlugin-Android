GodotUniversalIntentPlugin for Godot 3.2.2+
====================================
____________________________________


Android plugin for Godot 3.2.2 and above.  

Proof of concept project.
You can create your own android intents and get the result.

As of this time this plugin can only create the most basic intents.

*Godot example included:*

* QR code reader
* Web search

Please help me to add aditional examples if you are using this plugin.

See GodotExample for more info (Godot 3.4.4).

Installation
============

Follow these instructions for android custom build, [ official documentation](https://docs.godotengine.org/en/stable/getting_started/workflow/export/android_custom_build.html "documentation").

1. If exists, unzip the precompiled release zip in the release folder to your android plugin folder:
*release/godotuniversalintentplugin_for_godot_[your Godot version].zip* to *[your godot project]/android/plugins/*

2. Activate plugin in Godot by enable "Project" -> "Export" -> "Options", "Use Custom Build" and "Godot Get Image" plugin

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

***intent()***  
create a intent

***putExtra(option as string, value as string)***  
Add aditional option to the intent.

***addFlag(flag as string)***  
Add aditional flag to the intent.


Emitted signals
---------------

***on_main_activity_result***  
Returns a Dictionary with intent extra data

***error***  
Returns any error as string
