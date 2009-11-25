package scrum.client.calendar;

import ilarkesto.gwt.client.Date;
import ilarkesto.gwt.client.TableBuilder;
import scrum.client.common.AScrumWidget;

import com.google.gwt.user.client.ui.Widget;

public class YearSelectorListWidget extends AScrumWidget {

	private int selectedYear = Date.today().getYear();
	private int from = selectedYear - 1;
	private int to = from + 4;

	private Date firstVisibleDate = Date.today();
	private Date lastVisibleDate = Date.today();

	@Override
	protected Widget onInitialization() {
		return null;
	}

	@Override
	protected void onUpdate() {
		TableBuilder tb = new TableBuilder();
		for (int year = from; year <= to; year++) {
			tb.add(new YearSelectorWidget(year, year == selectedYear, (new Date(year, 1, 1).isBetween(firstVisibleDate,
				lastVisibleDate, true))));
		}
		replaceContent(tb.createTable());
		super.onUpdate();
	}

	public int getSelectedYear() {
		return selectedYear;
	}

	public void setSelectedYear(int selectedYear) {
		this.selectedYear = selectedYear;
		this.from = selectedYear - 1;
		this.to = from + 4;
	}

	public void setVisibleRange(Date firstDate, Date lastDate) {
		this.firstVisibleDate = firstDate;
		this.lastVisibleDate = lastDate;
	}

}
