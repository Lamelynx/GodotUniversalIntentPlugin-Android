extends Control

"""
Author:
	Lamelynx
	
Example code for reading a QR code on android android device.

Permission that need to be set in the android export tab:
	None
"""

"""
Java code:
	
try {

	Intent intent = new Intent("com.google.zxing.client.android.SCAN");
	intent.putExtra("SCAN_MODE", "QR_CODE_MODE"); // "PRODUCT_MODE for bar codes

	startActivityForResult(intent, 0);

} catch (Exception e) {

	Uri marketUri = Uri.parse("market://details?id=com.google.zxing.client.android");
	Intent marketIntent = new Intent(Intent.ACTION_VIEW,marketUri);
	startActivity(marketIntent);

}

@Override
protected void onActivityResult(int requestCode, int resultCode, Intent data) {           
	super.onActivityResult(requestCode, resultCode, data);
	if (requestCode == 0) {

		if (resultCode == RESULT_OK) {
			String contents = data.getStringExtra("SCAN_RESULT");
		}
		if(resultCode == RESULT_CANCELED){
			//handle cancel
		}
	}
}
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
	find_child("QRCode").text = result["extras"]["SCAN_RESULT"]
	
func _on_error(e):
	""" Plugin has returned some error """
	print("ERROR:", e)


func _on_ReadQRCode_pressed():
	""" Create read QR code intent """

	if plugin:
		# Create a new intent
		plugin.intent("com.google.zxing.client.android.SCAN")
		
		# Add extra options to the intent
		plugin.putExtra("SCAN_MODE", "QR_CODE_MODE") # "PRODUCT_MODE for bar codes

		# It's now time to start the activity, when finished "on_main_activity_result" signal is emited
		plugin.startActivityForResult()
	else:
		print(plugin_name, " plugin not loaded!")


func _on_Back_pressed():
	get_tree().change_scene_to_file("res://Main.tscn")


func _on_Button_pressed():
	pass # Replace with function body.
