package scrum.client.risks;

public class RiskLabeler {

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

	public static String getPriorityLabel(int impact, int probability) {
		if (probability > 80) {
			if (impact > 80) return "severe";
			if (impact > 60) return "severe";
			if (impact > 40) return "high";
			if (impact > 20) return "significant";
			return "moderate";
		}
		if (probability > 60) {
			if (impact > 80) return "very high";
			if (impact > 60) return "high";
			if (impact > 40) return "significant";
			if (impact > 20) return "moderate";
			return "moderate";

		}
		if (probability > 40) {
			if (impact > 80) return "high";
			if (impact > 60) return "significant";
			if (impact > 40) return "moderate";
			if (impact > 20) return "moderate";
			return "low";
		}
		if (probability > 20) {
			if (impact > 80) return "significant";
			if (impact > 60) return "moderate";
			if (impact > 40) return "moderate";
			if (impact > 20) return "low";
			return "very low";
		}
		if (impact > 80) return "moderate";
		if (impact > 60) return "moderate";
		if (impact > 40) return "low";
		if (impact > 20) return "very low";
		return "very low";
	}

}
