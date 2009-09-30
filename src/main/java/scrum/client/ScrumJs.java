package scrum.client;

public class ScrumJs {

	static native void initialize() /*-{
		$wnd.scrum = new Object();
		$wnd.scrum.showEntityByReference = function(reference) {
			@scrum.client.ScrumJs::showEntityByReference(Ljava/lang/String;)(reference);
		}
	}-*/;

	public static void showEntityByReference(String reference) {
		ScrumGwtApplication.get().showEntityByReference(reference);
	}

	public static native String regexTextToHtml(String text) /*-{

		// escape html
		text = text.replace( /&/g , "&amp;" );
		text = text.replace( /</g , "&lt;" );
		text = text.replace( />/g , "&gt;" );
		
		// create links for entity references (r5 or t23)
		text = text.replace( /\b((r|t)\d+)\b/g , "<a onclick='window.scrum.showEntityByReference(\"$1\")'>" + "$1"  + "</a>" );
		
		return text;
	}-*/;

}
