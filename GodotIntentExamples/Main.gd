extends Control


# Called when the node enters the scene tree for the first time.
func _ready():
	pass
	
func _on_OpenReadQRCode_pressed():
	get_tree().change_scene_to_file("res://example_scenes/ReadQRCode.tscn")


func _on_OpenWebSearch_pressed():
	get_tree().change_scene_to_file("res://example_scenes/WebSearch.tscn")


func _on_OpenDialPhoneNumber_pressed():
	get_tree().change_scene_to_file("res://example_scenes/DialPhoneNumber.tscn")


func _on_OpenGPSPosition_pressed():
	get_tree().change_scene_to_file("res://example_scenes/OpenGPSPosition.tscn")
