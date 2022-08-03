extends Control

"""
Author:
	Lamelynx
	
Example code for do a web search on android device.

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
		plugin.connect("on_main_activity_result", self, "_on_main_activity_result")
		plugin.connect("error", self, "_on_error")

func _on_main_activity_result(result):
	print("RESULT:", result)
	#find_node("QRCode").text = result["??"]
	
func _on_error(e):
	""" Plugin has returned som error """
	print(e)


func _on_Back_pressed():
	get_tree().change_scene("res://Main.tscn")


func _on_Button_pressed():
	if plugin:
		# Create a new intent
		plugin.intent("android.intent.action.WEB_SEARCH") # Same as Intent.ACTION_WEB_SEARCH
		
		# Add extra options to the 
		var string = find_node("LineEdit").text
		print(string)
		plugin.putExtra("query", string) # Same as 

		# It's now time to start the activity, when finished "on_main_activity_result" signal is emited
		plugin.startActivityForResult()
	else:
		print(plugin_name, " plugin not loaded!")
