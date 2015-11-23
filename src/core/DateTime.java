package core;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

public class DateTime {

	/**
	 * Get current date
	 * 
	 * @return
	 */
	public String getDate() {
		Date d = new Date();
		DateFormat df = new SimpleDateFormat("HH:mm:ss yyyy/MM/dd");
		DateFormat r_date = new SimpleDateFormat("yyyy_MM_dd");
		df.setTimeZone(TimeZone.getTimeZone("Europe/Kiev"));

		return r_date.format(d);
	}

	/**
	 * Get current time
	 * 
	 * @return
	 */
	public String getTime() {
		Date r_d = new Date();
		DateFormat df = new SimpleDateFormat("HH:mm:ss yyyy/MM/dd");
		DateFormat time = new SimpleDateFormat("HH-mm-ss");
		df.setTimeZone(TimeZone.getTimeZone("Europe/Kiev"));

		return time.format(r_d);
	}

	/**
	 * Get current date-time
	 * 
	 * @return
	 */
	public String getDateTime() {
		Date r_dt = new Date();
		DateFormat df = new SimpleDateFormat("HHmmssyyMMdd");
		df.setTimeZone(TimeZone.getTimeZone("Europe/Kiev"));

		return df.format(r_dt);
	}

	/**
	 * Get current date-time
	 * 
	 * @return
	 */
	public String getYear() {
		Date r_y = new Date();
		DateFormat df = new SimpleDateFormat("yyyy");
		df.setTimeZone(TimeZone.getTimeZone("Europe/Kiev"));

		return df.format(r_y);
	}

}
