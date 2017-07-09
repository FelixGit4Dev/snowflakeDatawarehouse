package adventureworks.transformations;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;

import adventureworks.DAO.DwhTargetAcess;
import adventureworks.entity.dimensions.time.Day;
import adventureworks.entity.dimensions.time.Month;
import adventureworks.entity.dimensions.time.Year;
import adventureworks.transformations.meta.Transformation;
import static java.time.temporal.TemporalAdjusters.firstDayOfYear;
import static java.time.temporal.TemporalAdjusters.lastDayOfYear;
import static java.time.temporal.TemporalAdjusters.lastDayOfMonth;
import static java.time.temporal.TemporalAdjusters.firstDayOfMonth;;
@Named
@ApplicationScoped
public class TimeDimensionTransformations implements Transformation{

	
@Inject
private DwhTargetAcess targetDao;
@Inject
private DwhTargetAcess SourceDao;
	
	
	
public void initDimension(){
//Timestamp earliestDate= SourceDao.getEarliestDate();	
DateTimeFormatter formatterYear = DateTimeFormatter.ofPattern("yyyy");
DateTimeFormatter formatterMonth = DateTimeFormatter.ofPattern("yyyy MM");
DateTimeFormatter formatterDay = DateTimeFormatter.ofPattern("yyyy MM dd");
DateTimeFormatter formatterWeek = DateTimeFormatter.ofPattern("yyyy ww");
String s = "2010-05-01";
String e = "2015-05-10";
LocalDate start = LocalDate.parse(s);
LocalDate start2 = LocalDate.parse(s);
LocalDate start3 = LocalDate.parse(s);
LocalDate start4 = LocalDate.parse(s);
LocalDate end = LocalDate.parse(e);
List<LocalDate> totalDates = new ArrayList<LocalDate>();
List<LocalDate> totalDatesMonth = new ArrayList<LocalDate>();
List<LocalDate> totalDatesDays = new ArrayList<LocalDate>();
List<LocalDate> totalDatesWeek = new ArrayList<LocalDate>();
while (!start.isAfter(end)) {
  totalDates.add(start);
  start = start.plusYears(1);
}	
for(LocalDate date : totalDates){
	Year year = new Year(date.format(formatterYear));
	LocalDate firstDay = date.with(firstDayOfYear()); // 2015-01-01
	LocalDate lastDay = date.with(lastDayOfYear()); // 2015-12-31
	//year= this.targetDao.persistYear(year);
	
	System.out.println(date.format(formatterYear));
	while (!firstDay.isAfter(lastDay)) {
		 Month m= new Month(year.getYearId(),firstDay.format(formatterMonth) );
		 // m = this.targetDao.persistMonth(m);
		  System.out.println(firstDay.format(formatterMonth));
		  firstDay = firstDay.plusMonths(1);	 
		  LocalDate firstDayMonth = firstDay.with(firstDayOfMonth()); // 2015-01-01
			LocalDate lastDayMonth = firstDay.with(lastDayOfMonth()); // 2015-12-31
		  while (!firstDayMonth.isAfter(lastDayMonth)) {
			Day d = new  Day(1L, m.getMonthId(), firstDayMonth.format(formatterDay)); 
			//this.targetDao.persistDay(d);
			  System.out.println(firstDayMonth.format(formatterDay));
			  firstDayMonth = firstDayMonth.plusDays(1);
			}	  
		}
	}
}	
	
	
public void update(){
	Timestamp latetsDateinDwh =this.targetDao.getLatestDate();
	LocalDate latest= latetsDateinDwh.toLocalDateTime().toLocalDate();
	LocalDate now = LocalDate.now();	
	if(now.isAfter(latest)){

		
		//TODO
	}
}	
	
	
//	public static void main(String[] args){
//TimeDimensionTransformations dim= new TimeDimensionTransformations();
//dim.initDimension();
//	}
//	
}
