package scrum.client.common;

public class Logger {

	public static void DEBUG(Object... strings) {
		StringBuilder sb = new StringBuilder();
		boolean first = true;
		for (Object s : strings) {
			if (first) {
				first = false;
			} else {
				sb.append(" ");
			}
			sb.append(s);
		}
		System.out.println(sb);
	}

}
