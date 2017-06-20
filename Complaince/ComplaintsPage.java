package bg.framework.app.functional.page.Slingshot;

import bg.framework.app.functional.entities.UserProfile;
import bg.framework.app.functional.page.common.BasePage;
import bg.framework.app.functional.page.common.GlobalErrorMessages;
import bg.framework.app.functional.util.PropertyLoader;
import bg.framework.app.functional.util.Report;
import bg.framework.app.functional.util.TestDataHelper;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;



public class ComplaintsPage extends BasePage {
	private final static String FILE_NAME = "resources/Slingshot/Complaints.Properties";
    private static Properties pageProperties = new PropertyLoader(FILE_NAME).load();

    public void ComplaintsFindmore() {
    	browser.wait(getWaitTime());
    	Report.updateTestLog("Help & Advice Page ","WARN");
    	verifyAndClickWithXpath(pageProperties.getProperty("Complaints.findmore"), "Find Out More Link");
    	browser.wait(getWaitTime());
    }
    
    public void selectGasandEelctComplaint() {
    	browser.wait(getWaitTime());
    	Report.updateTestLog("compalints Page ","WARN");
    	verifyAndClickWithXpath(pageProperties.getProperty("Complaints.Complainttype"), "Gas & Electricity Link");
    	browser.wait(getWaitTime());
    }
    
    public void selectCarePlanComplaint() {
    	browser.wait(getWaitTime());
    	Report.updateTestLog("compalints Page ","WARN");
    	verifyAndClickWithXpath(pageProperties.getProperty("Complaints.selectcareplan"), "CarePlan Link");
    	browser.wait(getWaitTime());
    	Report.updateTestLog("CarePlan Page", "WARN");
    }
    
    public void selectBoilerInstallation() {
    	browser.wait(getWaitTime());
    	Report.updateTestLog("compalints Page ","WARN");
    	verifyAndClickWithXpath(pageProperties.getProperty("Complaints.selectboilerinstallation"), "Boiler Installation Link");
    	browser.wait(getWaitTime());
    	Report.updateTestLog("Boiler Installation Page", "WARN");
    }
    
    public void RaiseGasElectComplaint(UserProfile userprofile)
    {
    	browser.wait(getWaitTime());
    	System.out.println("+++++++++++++Inside Gas and Electricity Complaints+++++++++++++++++++++++++++");
    	List<String> complaintList = getComplaintList();
    	System.out.println("complaints");
    	int iteration=1;
        for (int i = 1; i<complaintList.size();i++) {
            browser.wait(getWaitTime());
            if(browser.isElementVisible(pageProperties.getProperty("Complaints.SelectComplaintID"))){
            	
            	browser.selectfromDropBox("id", pageProperties.getProperty("Complaints.SelectComplaintID"), complaintList.get(i));
            	browser.wait(getWaitTime());
            	Report.updateTestLog("*********** Iteration : '" + iteration + "' selected ***********", "Done");
            	
            	String category = complaintList.get(i);
            	System.out.println(category);
            	Report.updateTestLog("I'd like to complain about :" +category, "Done");
            	
            	SubmitForm(userprofile,"OAM");  
            	GoBacktoComplaintpage();
            	//selectGasandEelctComplaint();
            	iteration=iteration+1;
            }
        }    	
    }
    
    private List<String> getComplaintList() {
    	System.out.println("************************************");
        return browser.getFromDropBox("id", pageProperties.getProperty("Complaints.SelectComplaintID"));
    }
    
    private void SubmitForm(UserProfile userprofile, String user) {
    	
    	if(user.equals("Anonymous"))
    	{
    	
    	//verifyAndSelectDropDownBox(pageProperties.getProperty("Complaints.title"),"Title", userprofile.getTitle());
    	//browser.selectfromDropBox("id", pageProperties.getProperty("Complaints.title"), userprofile.getTitle());
    	//verifyAndInputByXpath(pageProperties.getProperty("Complaints.firstname"), "First Name", userprofile.getFirstName());
    	//verifyAndInputByXpath(pageProperties.getProperty("Complaints.surname"), "Sur Name", userprofile.getLastName());
    	//verifyAndInputByXpath(pageProperties.getProperty("Complaints.email"), "Email", userprofile.getEmail());
    	//verifyAndInputByXpath(pageProperties.getProperty("Complaints.businessname"), "Business Name", userprofile.getbusinessname());
    	//verifyAndInputByXpath(pageProperties.getProperty("Complaints.telephonenumber"), "Telephone", userprofile.getPhoneNumber());
    	verifyAndInputByXpath(pageProperties.getProperty("Complaints.postcode"), "Posting Code", userprofile.getPostCode());
    	verifyAndClickWithXpath(pageProperties.getProperty("Complaints.findaddress"), "Find address");
    	browser.wait(getWaitTime());
    	/*Select select = new Select(browser.getElementByXpath(pageProperties.getProperty("Complaints.selectaddress")));
		select.selectByIndex(3);*/
    	Select select = new Select(browser.getElementByXpath(pageProperties.getProperty("Complaints.selectaddress")));
		select.selectByIndex(3);
    	/*ArrayList<String> contact = browser.getFromDropBox("id", pageProperties.getProperty("Complaints.selectaddress"));
		verifyAndSelectDropDownBox(pageProperties.getProperty("Complaints.selectaddress"), "Preferred Contact ", contact.get(2));*/
    	
		verifyAndClickWithXpath(pageProperties.getProperty("Complaints.confirmbutton"), "Confirm address");
		verifyAndInputByXpath(pageProperties.getProperty("Complaints.complaintdetail"), "Complaint Detail", "Testing");
		Report.updateTestLog("Enter the details successfully ","WARN");
		verifyAndClickWithXpath(pageProperties.getProperty("Complaints.submitbutton"), "submit button");
		browser.wait(getWaitTime());
    }
    	
    
    	else if(user.equals("OAM"))
    	{
    		if (browser.isElementVisibleWithXpathenable(pageProperties.getProperty("Complaints.AccountNumber")))
			{
		browser.clearValue(pageProperties.getProperty("Complaints.AccountNumber"));
		browser.input(pageProperties.getProperty("Complaints.AccountNumber"), userprofile.getAccNumber());
		Report.updateTestLog(userprofile.getAccNumber() + " Account Number Entered", "DONE");
			}
	else
	{
		Report.updateTestLog(userprofile.getAccNumber()+"Account Number Disabled", "Done");
	}
    		verifyAndInputByXpath(pageProperties.getProperty("Complaints.postcode"), "Posting Code", userprofile.getPostCode());
    		
    		verifyAndClickWithXpath(pageProperties.getProperty("Complaints.findaddress"), "Find address");
    		
    		browser.wait(5000);
    		
    		Select select = new Select(browser.getElementByXpath(pageProperties.getProperty("Complaints.selectaddress")));
    		select.selectByIndex(10);
    		
    		verifyAndClickWithXpath(pageProperties.getProperty("Complaints.confirmbutton"), "Confirm address");
    		browser.wait(3000);
    		
    		verifyAndInputByXpath(pageProperties.getProperty("Complaints.complaintdetail"), "Complaint Detail", "Testing1");
    		
    		Report.updateTestLog("Enter the details successfully ","WARN");
    		
    		browser.wait(3000);
    		
    		verifyAndClickWithXpath(pageProperties.getProperty("Complaints.submitbutton"), "submit button");
    		//verifyAndClickWithCSS(pageProperties.getProperty("Complaints.submitbutton"), "submit button");
    		
    		
    		
    	}
    }
    	
    
    
    public void GoBacktoComplaintpage() {
    	String refno = null;
    	browser.wait(getWaitTime());    
    	
    	if(browser.isElementVisibleWithXpath(pageProperties.getProperty("Complaints.confirmation")))
    	{
    		 refno = browser.getElementByXpath(".//*[@id='primary-content-hygiene']/div/div[1]/div/p").getText();
    		 System.out.println(refno);
    		Report.updateTestLog("Refrence Number is:" +refno, "Done");
    	}
    	
    	else
    	{
    		Report.updateTestLog("Refrence Number is not displayed", "Fail");
    	}
    	
    	if(browser.isElementVisibleWithXpath(pageProperties.getProperty("Complaints.survey")))
    	{
    		verifyAndClickWithXpath(pageProperties.getProperty("Complaints.Feedbackoption"),"Feed back Option Clicked");
    		verifyAndClickWithXpath(pageProperties.getProperty("Complaints.send"),"Feed back Submitted");
    	}
    	
    	
    	Report.updateTestLog("Compalints Register Confirmation Page ","WARN");
    	
    	browser.browserBack();
    	browser.wait(getWaitTime());
    }
    
    public void selectquery() {
    	browser.wait(getWaitTime());
    	Report.updateTestLog("compalints Page ","WARN");
    	//verifyAndClickWithXpath(pageProperties.getProperty("Complaints.gotquery"), "I've got a query Link");
    	verifyAndClickWithXpath(pageProperties.getProperty("Complaints.gotquerynew"), "I've got a query Link");
    	browser.wait(getWaitTime());
    	Report.updateTestLog("I've got Query Page", "WARN");
    }
	

}
