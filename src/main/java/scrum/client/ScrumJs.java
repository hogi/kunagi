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
		
		// create links for entity references: "req5" or "tsk23"
		text = text.replace( /\b((req|tsk|iss|qlt|rsk|imp)\d+)\b/g , "<a onclick='window.scrum.showEntityByReference(\"$1\")'>" + "$1"  + "</a>" );
		
		// create links for wiki pages: "[Start]" or "[MyPage]"
		text = text.replace( /(\[(\w+)\])/g , "<a onclick='window.scrum.showEntityByReference(\"$1\")'>" + "$2"  + "</a>" );
		
		return text;
	}-*/;

}
