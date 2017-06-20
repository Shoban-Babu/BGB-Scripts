/**
 * 
 */
package bg.framework.app.functional.page.Slingshot;

import java.util.ArrayList;
import java.util.Properties;

import bg.framework.app.functional.entities.UserProfile;
import bg.framework.app.functional.page.common.BasePage;
import bg.framework.app.functional.util.PropertyLoader;
import bg.framework.app.functional.util.Report;

/**
 * @author 292238
 *
 */
public class ConsumptionGraphPage extends BasePage{
	private final static String File_AccPage = "resources/Slingshot/EnergyConsumption.properties";
    private static Properties pageProperties = new PropertyLoader(File_AccPage).load();
    
	public void verifyEnergyConsumptionPage(UserProfile userProfile){
		if(browser.getChildElementsCountByXpath(pageProperties.getProperty("EnergyConsumption.NoOfAccountDisplayed"))>0){
		int accountsDisplayed = browser.getChildElementsCountByXpath(pageProperties.getProperty("EnergyConsumption.NoOfAccountDisplayed"));
		Report.updateTestLog(accountsDisplayed+" no of accounts displayed.", "PASS");
		//for(int i=0; i<accountsDisplayed; i++){
			int j=3;
		String mSNNumber = browser.getTextByXpath(pageProperties.getProperty("EnergyConsumption.MeterSerialNumber").replace("traverse", j+""));
		if(accountsDisplayed != 0){
			verifyAndClickWithXpath(pageProperties.getProperty("EnergyConsumption.FirstAccount").replace("traverse", j+""), "Meter selected for: "+mSNNumber);
		}
		//}
		}
		else{
			if(browser.isElementVisibleWithXpath(pageProperties.getProperty("EnergyConsumption.SearchField"))){
				verifyAndSelectDropDownBox(pageProperties.getProperty("EnergyConsumption.optionsToSearch"), "Search by", "Meter Point Reference Number");
				verifyAndInputByXpath(pageProperties.getProperty("EnergyConsumption.keywordToSearch"), "Search Keyword", userProfile.getMprn());
				verifyAndClickWithXpath(pageProperties.getProperty("EnergyConsumption.SearchButton"), "Search button");
				browser.wait(getWaitTime());
			}else{
			Report.updateTestLog("No device exist for this a account", "FAIL");}
		}
	}
	public void clickContinueButton(){
		verifyAndClickWithXpath(pageProperties.getProperty("EnergyConsumption.ContinueButton"), "Continue button");
		browser.wait(getWaitTime());
		verifyPageTitle(pageProperties.getProperty("EnergyConsumption.EnergyConsumptionPageTitle"));
	}
	
	public void veifyEnergyConsumptionPage(){
		verifyIsElementVisibleWithXpath(pageProperties.getProperty("EnergyConsumption.AccountSection"), "Account details ");
		verifyIsElementVisibleWithXpath(pageProperties.getProperty("EnergyConsumption.MeterDetails"), "Meter details ");
		if(browser.isElementVisibleWithXpath(pageProperties.getProperty("EnergyConsumption.GraphView"))){
		verifyIsElementVisibleWithXpath(pageProperties.getProperty("EnergyConsumption.GraphView"), "Consumption Graph");
		}
		else{
			Report.updateTestLog("Consumption data is not available for the given period ", "FAIL");
			//browser.close();
		}
	}
	public void verifyGraph(){
		if(browser.isElementVisibleWithXpath(pageProperties.getProperty("EnergyConsumption.GraphView"))){
		verifyIsElementVisibleWithXpath(pageProperties.getProperty("EnergyConsumption.GraphView"), "Consumption Graph");
		}
		else{
			Report.updateTestLog("Consumption graph is displayed", "FAIL");
		}
	}
	
	public void verifyTableView(){
		verifyAndClickWithXpath(pageProperties.getProperty("EnergyConsumption.TableviewOption"), "Table view option");		
		Report.updateTestLog("Consumption data displayed in table format", browser.isElementVisibleWithXpath(pageProperties.getProperty("EnergyConsumption.Tableview"))?"PASS":"FAIL");
		
		}
	public void clickAndVerifyComparisionYear(){
		verifyAndClickWithXpath(pageProperties.getProperty("EnergyConsumption.OptionsButton"), "Options button");
		verifyAndClickWithXpath(pageProperties.getProperty("EnergyConsumption.OptionsButton"), "Options button");
		verifyAndClickWithXpath(pageProperties.getProperty("EnergyConsumption.ComparisionBox"), "Comparision box");
		verifyAndClickWithXpath(pageProperties.getProperty("EnergyConsumption.ShowUseButton"), "Show use button");
		browser.wait(getWaitTime());
		verifyTableValues();
	}
	
	public void selectDate(){
		int dateValues = browser.getChildElementsCountByXpath(pageProperties.getProperty("EnergyConsumption.DateDropdown"));
		System.out.println("dateValues#############"+dateValues);
		browser.getTextFromDropBox(pageProperties.getProperty("EnergyConsumption.DateDropdown"), "Date dropdown");
		ArrayList<String> valuesFromDate = browser.getFromDropBox(pageProperties.getProperty("EnergyConsumption.DateDropdown"), "Date dropdown");
		verifyAndSelectDropDownBox(pageProperties.getProperty("EnergyConsumption.DateDropdown"), "Date dropdown", valuesFromDate.get(0));
		verifyTableValues();
	}
	
	public void verifyTableValues(){
		if(browser.isElementVisibleWithXpath(pageProperties.getProperty("EnergyConsumption.TableviewOption"))){
			String usageType = browser.getTextByXpath(pageProperties.getProperty("EnergyConsumption.Usagetype"));
			Report.updateTestLog("This consumption usage type is: "+usageType, "PASS");
			int rowCount = browser.getChildElementsCountByXpath(pageProperties.getProperty("EnergyConsumption.RowCount"));
			System.out.println("RowCount *********** "+ rowCount);
			String months = browser.getTextByXpath(pageProperties.getProperty("EnergyConsumption.MonthlyData"));
			Report.updateTestLog("Consumption graph is displayed by comparing previous years consumption ", "Done");
			for(int i=1;i<rowCount;i++){
				//System.out.println(pageProperties.getProperty("EnergyConsumption.Columnvalue").replaceFirst("col", i+""));
			//System.out.println(browser.getTextByXpath(pageProperties.getProperty("EnergyConsumption.Columnvalue").replaceFirst("col", i+"")));
			String columnValues = browser.getTextByXpath(pageProperties.getProperty("EnergyConsumption.Columnvalue").replaceFirst("col", i+""));
			Report.updateTestLog("Consumption graph is displayed for last 12 months "+columnValues, "PASS");
			}
			
		}
	}
	public void verifyLinkNavigations(){
		//verifyAndClickWithXpath(pageProperties.getProperty("EnergyConsumption.CancelLink"), "Cancel link");
		//Report.updateTestLog(browser.getTitle()+" page displayed", (browser.getTitle().equalsIgnoreCase("Account Summary"))?"PASS":"FAIL");
		//verifyAndClickWithXpath(pageProperties.getProperty("EnergyConsumption.BackLink"), "Back link");
		//Report.updateTestLog(browser.getTitle()+" page displayed", (browser.getTitle().equalsIgnoreCase("Account Summary"))?"PASS":"FAIL");
	
	}
	public void validateSelectAnotherMeter(){
		if(browser.isElementVisibleWithXpath(pageProperties.getProperty("EnergyConsumption.SelectAnotherMeter"))){
		verifyAndClickWithXpath(pageProperties.getProperty("EnergyConsumption.SelectAnotherMeter"), "Select another meter link");
		if(browser.getTitle().equals("Your energy consumption")){
			Report.updateTestLog("Your energy consumption page is dispalyed as expected.", "PASS");
			verifyConsumptionLinks();
			clickContinueButton();
		}
	}
		else{
			Report.updateTestLog("Select another meter option not available", "DONE");
		}
	}
	public void verifyConsumptionLinks(){
		ArrayList<String> list = new ArrayList<String>();
		list.add("EnergyConsumption.SMR");
		list.add("Submit meter read");
		list.add("EnergyConsumption.Billing");
		list.add("Billing");
		list.add("EnergyConsumption.ContactUs");
		list.add("Contact us - Business");
		/*list.add("EnergyConsumption.DD");
		list.add("Amend Direct Debit");
		list.add("EnergyConsumption.Payments");
		list.add("Payments");
		list.add("EnergyConsumption.EC");
		list.add("Your energy consumption");*/
		for (int i=0;i<list.size();i=i+2) {
			verifyLinksandTitle(list.get(i),list.get(i+1));
		}
		
	}
	
	private void verifyLinksandTitle(String link, String title) {
		String linkName = link.substring(link.indexOf('.') + 1, link.length());
		if(browser.isElementVisibleWithXpath(pageProperties.getProperty(link))) {
			Report.updateTestLog(linkName + " Link Exist", "PASS");
			browser.clickWithXpath(pageProperties.getProperty(link));
			browser.wait(2000);	
			verifyPageTitle(title);
			browser.browserBack();
			browser.wait(2000);	
		} 
		else {
			Report.updateTestLog(linkName + " Link not Exist", "FAIL");
		}
	}
	
public void verifyConsumptionPodsLink(){
	ArrayList<String> list = new ArrayList<String>();
	list.add("EnergyConsumption.ViewYourAccount");
	list.add("Account overview");
	list.add("EnergyConsumption.PodSMR");
	list.add("Submit meter read");
	list.add("EnergyConsumption.ViewBill");
	list.add("View bill");
	
	for (int i=0;i<6;i=i+2) {		
		verifyLinksandTitle(list.get(i),list.get(i+1));
	}
}
public void verifyConsumptionLinks_Morethan3Meter(){
	ArrayList<String> list = new ArrayList<String>();
	
	list.add("EnergyConsumption.Billing");
	list.add("Billing");
	list.add("EnergyConsumption.ContactUs");
	list.add("Contact us - Business");
	list.add("EnergyConsumption.Payments");
	list.add("Payments");
	list.add("EnergyConsumption.EC");
	list.add("Your energy consumption");
	for (int i=0;i<list.size();i=i+2) {
		verifyLinksandTitle(list.get(i),list.get(i+1));
	}
	
}
	}

