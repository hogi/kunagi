package scrum.client;

import ilarkesto.core.scope.Scope;
import scrum.client.workspace.ProjectWorkspaceWidgets;

public class ScrumJs {

	private static final ComponentManager cm = ComponentManager.get();

	static native void initialize() /*-{
									$wnd.scrum = new Object();
									$wnd.scrum.showEntityByReference = function(reference) {
									@scrum.client.ScrumJs::showEntityByReference(Ljava/lang/String;)(reference);
									}
									}-*/;

	public static void showEntityByReference(String reference) {
		Scope.get().getComponent(ProjectWorkspaceWidgets.class).showEntityByReference(reference);
	}

	public static String createShowEntityByReferenceLink(String reference, String entityLabel) {
		String labelString = "";
		if (entityLabel != null) {
			entityLabel = entityLabel.replace("'", "`");
			entityLabel = entityLabel.replace("\"", "`");
			labelString = " title='" + entityLabel + "'";
		}
		return "<a onclick='window.scrum.showEntityByReference(\"" + reference + "\")'" + labelString + ">" + reference
				+ "</a>";
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
