package org.iit.mmp;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Utilities {
	public static String selectFutureDate(int nofDays)
	{
	      Calendar cal = Calendar.getInstance();
	      System.out.println(cal.getTime());
	      cal.add(Calendar.DAY_OF_MONTH, nofDays);
	      
	      SimpleDateFormat sdf = new SimpleDateFormat("d/MMMMM/YYYY");//10/October/2021
	      String date = sdf.format(cal.getTime());
	      System.out.println(date);
	      return date;
	}
}
