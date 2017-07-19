package adventureworks.transformations;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;

import adventureworks.DAO.DwhSourceAccess;
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
private DwhSourceAccess SourceDao;
	
	
	
public HashMap<String, Long> initDimension(){
	HashMap<String, Long> mapCount= new HashMap<>();
	Long dayCounter=0L;
	Long monthCounter=0L;
	Long yearCounter=0L;
//Timestamp earliestDate= SourceDao.getEarliestDate();	
DateTimeFormatter formatterYear = DateTimeFormatter.ofPattern("yyyy");
DateTimeFormatter formatterMonth = DateTimeFormatter.ofPattern("yyyy-MM");
DateTimeFormatter formatterDay = DateTimeFormatter.ofPattern("yyyy-MM-dd");
DateTimeFormatter formatterWeek = DateTimeFormatter.ofPattern("yyyy-ww");
Timestamp startTimestamp= SourceDao.getEarliestDate();
Timestamp endTimestamp =new  Timestamp(System.currentTimeMillis());
LocalDate start = startTimestamp.toLocalDateTime().toLocalDate();
LocalDate end = endTimestamp.toLocalDateTime().toLocalDate();
List<LocalDate> totalDates = new ArrayList<LocalDate>();

while (!start.isAfter(end)) {
  totalDates.add(start);
  start = start.plusYears(1);
}	
for(LocalDate date : totalDates){
	Year year = new Year(date.format(formatterYear));
	LocalDate firstDay = date.with(firstDayOfYear()); // 2015-01-01
	LocalDate lastDay = date.with(lastDayOfYear()); // 2015-12-31
	Timestamp now= new Timestamp(System.currentTimeMillis());
	year.setModfiedDate(now );
	
	year= (Year) this.targetDao.persistObject(year);
	yearCounter++;
	
	//System.out.println(date.format(formatterYear));
	while (!firstDay.isAfter(lastDay)) {
		 Month m= new Month(year.getYearId(),firstDay.format(formatterMonth) );
		 now= new Timestamp(System.currentTimeMillis());
		 m.setModfiedDate(now);
		 m = this.targetDao.persistMonth(m);
		 monthCounter++;
		 // System.out.println(firstDay.format(formatterMonth));
		  firstDay = firstDay.plusMonths(1);	 
		  LocalDate firstDayMonth = firstDay.with(firstDayOfMonth()); // 2015-01-01
			LocalDate lastDayMonth = firstDay.with(lastDayOfMonth()); // 2015-12-31
		  while (!firstDayMonth.isAfter(lastDayMonth)) {
			Day d = new  Day(1L, m.getMonthId(), firstDayMonth.format(formatterDay));
			d.setTimeinMilis(new Timestamp(firstDayMonth.atStartOfDay(ZoneId.systemDefault()).toInstant().toEpochMilli()));
			 now= new Timestamp(System.currentTimeMillis());
			 d.setModfiedDate(now);
			this.targetDao.persistDay(d);
			dayCounter++;
			 // System.out.println(firstDayMonth.format(formatterDay));
			  firstDayMonth = firstDayMonth.plusDays(1);
			}	  
		}
	}
mapCount.put("Day", dayCounter);
mapCount.put("Month", monthCounter);
mapCount.put("Year", yearCounter);
return mapCount;
}	
	
	
public HashMap<String,Long> update(){
	Timestamp latetsDateinDwh =this.targetDao.getLatestEtlMeta().getEtlJobRun_Date();
	LocalDate latest= latetsDateinDwh.toLocalDateTime().toLocalDate();
	LocalDate now = LocalDate.now();	
	if(now.isAfter(latest)){
		

		
		//TODO
	}
	return new HashMap<>();
}	
	
	
//	public static void main(String[] args){
//TimeDimensionTransformations dim= new TimeDimensionTransformations();
//dim.initDimension();
//	}
//	
}
