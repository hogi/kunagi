package scrum.client.common;

import java.util.ArrayList;
import java.util.List;

public class TooltipBuilder {

	private String mainTooltip;
	private List<String> remarks = new ArrayList<String>();

	public static String NOT_TEAM = "You are not Team Member.";
	public static String NOT_SCRUMMASTER = "You are not ScrumMaster.";
	public static String NOT_PRODUCT_OWNER = "You are not Product Owner.";
	public static String NOT_SCRUMTEAM = "You are neither Product Owner nor ScrumMaster nor Team Member.";
	public static String NOT_TEAM_NOR_PRODUCT_OWNER = "You are neither Team Member nor Product Owner.";
	public static String NOT_ADMIN = "You are not Project Admin.";
	public static String NOT_SYS_ADMIN = "You are not System Admin.";

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
				sb.append(" Disabled for the following reason:");
			} else {
				sb.append(" Disabled for the following reasons:");
			}

			for (String element : remarks) {
				sb.append(" ").append(element);
			}

		}
		return sb.toString();

	}

}
