package adventureworks.Util.xml;

import java.io.StringReader;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import adventureworks.Util.xml.entity.IndividualSurvey;


@ApplicationScoped
@Named
public class XmlParser {

	
	private static String xmlPrepend="<?xml version=\"1.0\" encoding=\"UTF-8\"?>";
	private static JAXBContext jaxbContext;

	public static IndividualSurvey umarshal(String xml) {
		  try {
			  StringReader reader= new StringReader(xml.replaceAll("xmlns=\"http://schemas.microsoft.com/sqlserver/2004/07/adventure-works/IndividualSurvey\"", ""));
		  
if(XmlParser.jaxbContext==null){
			  jaxbContext = JAXBContext.newInstance(IndividualSurvey.class);}


				Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
				IndividualSurvey customer = (IndividualSurvey) jaxbUnmarshaller.unmarshal(reader);
				return customer;
		      } catch (JAXBException e) {
			e.printStackTrace();
			return null;
		      }
	
}
	
	
public static void main (String[] args){
IndividualSurvey suvry =umarshal("<IndividualSurvey xmlns=\"http://schemas.microsoft.com/sqlserver/2004/07/adventure-works/IndividualSurvey\" ><TotalPurchaseYTD>100.22</TotalPurchaseYTD>"+
"<TotalPurchaseYTD>100.22</TotalPurchaseYTD>"
+"<DateFirstPurchase>2003-10-23Z</DateFirstPurchase>"
+"<BirthDate>1970-07-27Z</BirthDate>"
+"<MaritalStatus>M</MaritalStatus>"
+ "<YearlyIncome>25001-50000</YearlyIncome>"
+ "<Gender>F</Gender>"
+"<TotalChildren>1</TotalChildren>"
+"<NumberChildrenAtHome>1</NumberChildrenAtHome>"
+"<Education>Bachelors </Education>"
+"<Occupation>Skilled Manual</Occupation>"
+"<HomeOwnerFlag>1</HomeOwnerFlag>"
+"<NumberCarsOwned>1</NumberCarsOwned>"
+"<CommuteDistance>2-5 Miles</CommuteDistance>"
+"</IndividualSurvey>");	
System.out.println(suvry);
}
	
}