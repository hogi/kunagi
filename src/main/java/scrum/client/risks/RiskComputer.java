package scrum.client.risks;

import java.util.LinkedHashMap;
import java.util.Map;

public class RiskComputer {

	private static Map<String, String> probabilities;
	private static Map<String, String> impacts;

	public static synchronized Map<String, String> getProbabilities() {
		if (probabilities == null) {
			probabilities = new LinkedHashMap<String, String>();
			probabilities.put("20", getProbabilityLabel(20));
			probabilities.put("40", getProbabilityLabel(40));
			probabilities.put("60", getProbabilityLabel(60));
			probabilities.put("80", getProbabilityLabel(80));
			probabilities.put("100", getProbabilityLabel(100));
		}
		return probabilities;
	}

	public static synchronized Map<String, String> getImpacts() {
		if (impacts == null) {
			impacts = new LinkedHashMap<String, String>();
			impacts.put("20", getImpactLabel(20));
			impacts.put("40", getImpactLabel(40));
			impacts.put("60", getImpactLabel(60));
			impacts.put("80", getImpactLabel(80));
			impacts.put("100", getImpactLabel(100));
		}
		return impacts;
	}

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

}
