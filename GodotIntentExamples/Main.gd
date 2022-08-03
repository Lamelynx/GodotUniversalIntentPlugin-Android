extends Node2D


# Called when the node enters the scene tree for the first time.
func _ready():
	pass

func _on_OpenReadQRCode_pressed():
	get_tree().change_scene("res://example_scenes/ReadQRCode.tscn")


func _on_OpenWebSearch_pressed():
	get_tree().change_scene("res://example_scenes/WebSearch.tscn")
