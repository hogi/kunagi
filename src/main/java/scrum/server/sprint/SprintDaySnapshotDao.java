package scrum.server.sprint;

import ilarkesto.base.time.Date;
import ilarkesto.fp.Predicate;

import java.util.LinkedList;
import java.util.List;

public class SprintDaySnapshotDao extends GSprintDaySnapshotDao {

	public SprintDaySnapshot getSprintDaySnapshot(final Sprint sprint, final Date date, boolean autoCreate) {
		SprintDaySnapshot snapshot = getEntity(new Predicate<SprintDaySnapshot>() {

			public boolean test(SprintDaySnapshot e) {
				return e.isSprint(sprint) && e.isDate(date);
			}
		});

		if (autoCreate && snapshot == null) {
			snapshot = newEntityInstance();
			snapshot.setSprint(sprint);
			snapshot.setDate(date);
			saveEntity(snapshot);
		}

		return snapshot;
	}

	public List<SprintDaySnapshot> getSprintDaySnapshots(Sprint sprint) {
		List<SprintDaySnapshot> ret = new LinkedList<SprintDaySnapshot>();
		Date date = sprint.getBegin();
		Date end = sprint.getEnd();
		SprintDaySnapshot previousSnapshot = null;
		while (date.isBeforeOrSame(end) && date.isPastOrToday()) {
			SprintDaySnapshot snapshot = getSprintDaySnapshot(sprint, date, false);
			if (snapshot == null) {
				snapshot = new SprintDaySnapshot();
				snapshot.setSprint(sprint);
				snapshot.setDate(date);
				if (previousSnapshot != null) {
					snapshot.setRemainingWork(previousSnapshot.getRemainingWork());
					snapshot.setBurnedWork(previousSnapshot.getBurnedWork());
				}
			}
			ret.add(snapshot);
			previousSnapshot = snapshot;
			date = date.nextDay();
		}
		return ret;
	}
}
