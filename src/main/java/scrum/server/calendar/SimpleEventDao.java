package scrum.server.calendar;

import ilarkesto.base.time.Date;
import ilarkesto.base.time.Time;
import ilarkesto.fp.Predicate;
import scrum.server.project.Project;

public class SimpleEventDao extends GSimpleEventDao {

	public SimpleEvent getSimpleEventByNumber(final int number, final Project project) {
		return getEntity(new Predicate<SimpleEvent>() {

			public boolean test(SimpleEvent t) {
				return t.isNumber(number) && t.isProject(project);
			}
		});
	}

	@Override
	public SimpleEvent newEntityInstance() {
		SimpleEvent event = super.newEntityInstance();
		event.setDate(Date.today());
		return event;
	}

	public SimpleEvent postEvent(Project project, String label, Date date, Time time, Integer duration) {
		SimpleEvent event = newEntityInstance();
		event.setProject(project);
		event.setLabel(label);
		event.setDate(date);
		event.setTime(time);
		event.setDuration(duration);
		saveEntity(event);
		return event;
	}

	public void createTestEvent(Project project, int variant) {
		switch (variant) {
			case 1:
				postEvent(project, "Review Meeting", Date.inDays(10), null, null);
				break;
			case 2:
				postEvent(project, "Punishment Execution", Date.inDays(2), new Time(9, 0), 360);
				break;
			default:
				postEvent(project, "Party", Date.inDays(2), new Time(8, 0), 30);
				break;
		}
	}

}