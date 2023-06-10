extends Control

"""
Author:
	Lamelynx
	
Example code for open a GPS position on android device.

Permission that need to be set in the android export tab:
	None
"""


var plugin
var plugin_name = "GodotUniversalIntent"


# Called when the node enters the scene tree for the first time.
func _ready():
	if Engine.has_singleton(plugin_name):
		plugin = Engine.get_singleton(plugin_name)
	else:
		print("Could not load plugin: ", plugin_name)

	if plugin:
		plugin.connect("on_main_activity_result", Callable(self, "_on_main_activity_result"))
		plugin.connect("error", Callable(self, "_on_error"))

func _on_main_activity_result(result):
	print("RESULT:", result)
	
func _on_error(e):
	""" Plugin has returned som error """
	print(e)


func _on_Back_pressed():
	get_tree().change_scene_to_file("res://Main.tscn")


func _on_Button_pressed():
	if plugin:
		# Create a new intent
		plugin.intent("android.intent.action.VIEW") # Same as Intent.ACTION_VIEW
		
		# This Android code snippet will open up a dialog which allow you to pick a map app to show the address you passed in the intent.
		#var uri = "geo:0,0?q=replace+this+with+an+address"
		
		# This Android code snippet will launch the google map with direction to the address you passed in.
		#var uri = "google.navigation:q=replace+this+with+an+address"
		
		# Passing in the latitude,longitude to the map app.
		#var uri = "geo:-21.805149,-49.089977"
		
		var uri = "geo:" + find_child("LineEdit").text
		plugin.setData(uri)
		
		# It's now time to start the activity, when finished "on_main_activity_result" signal is emited
		plugin.startActivityForResult()
	else:
		print(plugin_name, " plugin not loaded!")

