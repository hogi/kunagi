package scrum.client.risks;

import ilarkesto.gwt.client.LabelProvider;

public class RiskComputer {

	public static String getProbabilityLabel(int probability) {
		if (probability > 80) return "very likely";
		if (probability > 60) return "likely";
		if (probability > 40) return "possible";
		if (probability > 20) return "unlikely";
		return "very unlikely";
	}

	public static String getImpactLabel(int impact) {
		if (impact > 80) return "extreme";
		if (impact > 60) return "substantial";
		if (impact > 40) return "medium";
		if (impact > 20) return "minor";
		return "negligible";
	}

	public static int getPriority(int impact, int probability) {
		if (probability > 80) {
			if (impact > 80) return 4;
			if (impact > 60) return 4;
			if (impact > 40) return 2;
			if (impact > 20) return 1;
			return 0;
		}
		if (probability > 60) {
			if (impact > 80) return 3;
			if (impact > 60) return 2;
			if (impact > 40) return 1;
			if (impact > 20) return 0;
			return 0;

		}
		if (probability > 40) {
			if (impact > 80) return 2;
			if (impact > 60) return 1;
			if (impact > 40) return 0;
			if (impact > 20) return 0;
			return -1;
		}
		if (probability > 20) {
			if (impact > 80) return 1;
			if (impact > 60) return 0;
			if (impact > 40) return 0;
			if (impact > 20) return -1;
			return -2;
		}
		if (impact > 80) return 0;
		if (impact > 60) return 0;
		if (impact > 40) return -1;
		if (impact > 20) return -2;
		return -2;
	}

	public static String getPriorityLabel(int impact, int probability) {
		int priority = getPriority(impact, probability);
		if (priority >= 4) return "severe";
		if (priority == 3) return "very high";
		if (priority == 2) return "high";
		if (priority == 1) return "significant";
		if (priority == 0) return "moderate";
		if (priority == -1) return "low";
		return "very low"; // priority <= -2
	}

	public static LabelProvider<Integer> IMPACT_LABEL_PROVIDER = new LabelProvider<Integer>() {

		public String getLabel(Integer impact) {
			if (impact == null) return null;
			return getImpactLabel(impact);
		}
	};

	public static LabelProvider<Integer> PROBABILITY_LABEL_PROVIDER = new LabelProvider<Integer>() {

		public String getLabel(Integer probability) {
			if (probability == null) return null;
			return getProbabilityLabel(probability);
		}
	};

}
