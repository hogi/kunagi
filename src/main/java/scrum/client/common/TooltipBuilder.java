package scrum.client.common;

import java.util.ArrayList;
import java.util.List;

public class TooltipBuilder {

	private String mainTooltip;
	private List<String> remarks = new ArrayList<String>();

	public static String NOT_A_TEAM_MEMBER = "You are not a Team Member.";
	public static String NOT_A_SCRUM_MASTER = "You are not a Scrum Master.";
	public static String NOT_A_PRODUCT_OWNER = "You are not a Product Owner.";
	public static String NOT_ANYTHING = "You are neither a Product Owner nor a Scrum Master nor a Team Member.";
	public static String NOT_TEAM_MEMBER_NOR_PRODUCT_OWNER = "You are neither Team Member nor Product Owner.";
	public static String NOT_AN_ADMIN = "You are not an Admin.";

	public TooltipBuilder(String mainTooltip) {
		this.mainTooltip = mainTooltip;
	}

	public void addRemark(String remark) {
		remarks.add(remark);
	}

	public String getTooltip() {
		StringBuilder sb = new StringBuilder();
		sb.append(mainTooltip);

		if (remarks.size() > 0) {

			if (remarks.size() == 1) {
				sb.append("\n\nDisabled for the following reason:");
			} else {
				sb.append("\n\nDisabled for the following reasons:");
			}

			for (String element : remarks) {
				sb.append("\n    - ");
				sb.append(element);
			}

		}
		return sb.toString();

	}

}
