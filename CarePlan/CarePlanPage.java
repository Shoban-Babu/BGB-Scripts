package bg.framework.app.functional.page.Slingshot;

import java.util.Properties;
import java.text.DecimalFormat;

import bg.framework.app.functional.util.OnlineDBConnector;
import bg.framework.app.functional.util.PropertyLoader;
import bg.framework.app.functional.util.Report;
import bg.framework.app.functional.page.common.BasePage;
import bg.framework.app.functional.common.ApplicationConfig;
import bg.framework.app.functional.entities.UserProfile;
import bg.framework.app.functional.entities.Slinshot.CarePlanProfile;
import com.thoughtworks.xstream.*;
import com.thoughtworks.xstream.io.*;
import com.thoughtworks.xstream.io.xml.*;
import java.util.Random;

public class CarePlanPage extends BasePage {

	private final static String File_RegPage = "resources/Slingshot/Careplan.properties";
	private static Properties carePlanProperties = new PropertyLoader(File_RegPage).load();
	
	float monthlyPriceInput1;
	float monthlyPricewithVATInput1;
	float annualPriceInput1;
	float annualPricewithVATInput1;
	   public static String strEmail;
	   public static String strnumber;
	
	public static float totalannualPriceWithVAT=0;
	public static float totalmonthlyPriceWithVAT=0;
	public static float totalannualPriceWithoutVAT=0;
	public static float totalmonthlyPriceWithoutVAT=0;
	
	public static float monthlyPriceWithLandlordPrice1;
	public static float annualPriceWithLandlordPrice1;
	public static float monthlyPriceWithLandlordPriceWithVAT1;
	public static float annualPriceWithLandlordPriceWithVAT1;
	
	public static String refrence="";
	public static String siteid="";

	//public static String postCodes;

	/*****************************************************************Contact Details Page************************************************************************************/
	public void contactDetails (UserProfile userProfile, String BusinessCustomer) {

		if (BusinessCustomer=="yes") {

			verifyAndClickWithXpath(carePlanProperties.getProperty("Careplan.BusinessCusttomerYes"), "Yes,Im Business Customer");

		}
		else if (BusinessCustomer=="No"){

			verifyAndClickWithXpath(carePlanProperties.getProperty("Careplan.BusinessCusttomerNo"), "No,Im Not Business Cusomter");
		}
		verifyAndSelectDropDownBox(carePlanProperties.getProperty("Careplan.BusinessCusttomerTitle"), "Title", "Mr");
		verifyAndInputByXpath(carePlanProperties.getProperty("Careplan.BusinessCusttomerFirstname"),"First Name", userProfile.getFirstName().trim());
		verifyAndInputByXpath(carePlanProperties.getProperty("Careplan.BusinessCusttomerSurname"),"Sur Name", userProfile.getLastName().trim());
		verifyAndInputByXpath(carePlanProperties.getProperty("Careplan.BusinessCusttomerBusinessname"),"Business Name", userProfile.getbusinessname().trim());
		Random random = new Random ();
		strEmail="automation_digital105"+ random.nextInt(1000)+"@bgdigitaltest.co.uk";
		//strnumber="071478536"+random.nextInt(100)+"51";
		verifyAndInputByXpath(carePlanProperties.getProperty("Careplan.BusinessCusttomerEmailAddress"),"Email Address", strEmail.trim());
		verifyAndInputByXpath(carePlanProperties.getProperty("Careplan.BusinessCusttomerTelephone"),"Telephone",userProfile.getMobileNumber().trim());
		verifyAndClickWithXpath(carePlanProperties.getProperty("Careplan.BusinessCusttomerContinue"), "Continue");

		Report.updateTestLog("Contact Details are filled with vaild value", "WARN");
		
		
		browser.wait(10000);

	}
	
	public void contactDetailsNew (UserProfile userProfile) {

		verifyAndSelectDropDownBox(carePlanProperties.getProperty("Careplan.BusinessCusttomerTitle"), "Title", "Mr");
		verifyAndInputByXpath(carePlanProperties.getProperty("Careplan.BusinessCusttomerFirstname"),"First Name", userProfile.getFirstName().trim());
		verifyAndInputByXpath(carePlanProperties.getProperty("Careplan.BusinessCusttomerSurname"),"Sur Name", userProfile.getLastName().trim());
		verifyAndInputByXpath(carePlanProperties.getProperty("Careplan.BusinessCusttomerBusinessname"),"Business Name", userProfile.getbusinessname().trim());
		Random random = new Random ();
		strEmail="automation_digital105"+ random.nextInt(1000)+"@bgdigitaltest.co.uk";
		//strnumber="071478536"+random.nextInt(100)+"51";
		verifyAndInputByXpath(carePlanProperties.getProperty("Careplan.BusinessCusttomerEmailAddress"),"Email Address", strEmail.trim());
		verifyAndInputByXpath(carePlanProperties.getProperty("Careplan.BusinessCusttomerTelephone"),"Telephone",userProfile.getMobileNumber().trim());
		verifyAndClickWithXpath(carePlanProperties.getProperty("Careplan.BusinessCusttomerContinue"), "Continue");

		Report.updateTestLog("Contact Details are filled with vaild value", "WARN");
		
		
		browser.wait(10000);

	}

	/******************************************************************************Business Details page************************************************************************************/
	public void businessDetails (UserProfile userProfile,String GasCustomer){

		if (GasCustomer=="yes") {

			verifyAndClickWithXpath(carePlanProperties.getProperty("Careplan.GasSupplyYes"), "Yes,I have Business Customer");
			verifyAndInputByXpath(carePlanProperties.getProperty("Careplan.BusinessCusttomerPostcode"),"Post Code", userProfile.getPostCode().trim());
			browser.wait(1000);
			verifyAndClickWithXpath(carePlanProperties.getProperty("Careplan.BusinessCusttomerFindAddress"), "Find Address");
			browser.wait(10000);
			verifyAndSelectDropDownBoxbyindex(carePlanProperties.getProperty("Careplan.BusinessCusttomerFindAddressdropdown"),2);	
			verifyAndClickWithXpath(carePlanProperties.getProperty("Careplan.BusinessCusttomerConfirmAddress"), "Confirm Address");
			browser.wait(2000);
			if (browser.isElementVisibleWithXpath(carePlanProperties.getProperty("Careplan.GoodnewsSection"))){
				Report.updateTestLog("Good News Section displayed Sucessfully", "WARN");
			}
			else {
				Report.updateTestLog("Good News Section not displayed", "Fail");

			}

		}
		else if (GasCustomer=="No"){

			verifyAndClickWithXpath(carePlanProperties.getProperty("Careplan.GasSupplyNo"), "No,I Dont Business Cusomter");

			if (browser.isElementVisibleWithXpath(carePlanProperties.getProperty("Careplan.GasSupplyNoErrorMessage"))){
				Report.updateTestLog("Error Messsage displayed Sucessfully", "WARN");
			}
			else {
				Report.updateTestLog("Error Messsage not displayed", "Fail");

			}


		}

	}
	/**************************************************************************landlord Check*****************************************************************************************************/
	public void landlordCheck (String landlord){
		if (landlord=="yes") {

			verifyAndClickWithXpath(carePlanProperties.getProperty("Careplan.BusinessCusttomerlandlordYes"), "Yes,Im landlord");

		}
		else if (landlord=="No"){

			verifyAndClickWithXpath(carePlanProperties.getProperty("Careplan.BusinessCusttomelandlordNo"), "No,Im Not landlord");
		}
		verifyAndClickWithXpath(carePlanProperties.getProperty("Careplan.BusinessCusttomerlandlordContinue"), "Continue");
		Report.updateTestLog("Appliance Details section is displayed", "WARN");
	} 

	/***********************************************************************************Post Code Validation************************************************/	

	public void landlordCheckNew (CarePlanProfile carePlanProfile1){
		
			verifyAndClickWithXpath(carePlanProperties.getProperty("Careplan.Selectlandlord"), "Landlord is selected");
			browser.wait(1000);
			verifyAndClickWithXpath(carePlanProperties.getProperty("Careplan.LandlordGasSelect"), "select");
			browser.selectfromDropBoxByXpath(carePlanProperties.getProperty("Careplan.LandLordApplianceSize"),carePlanProfile1.getApplianceSize());
			verifyAndClickWithXpath(carePlanProperties.getProperty("Careplan.LandLordContinueButton"), "Continue");

		}
		

	public void postcodeValidation (){


		String[] postCodes = {
		"PA36 4AB"};

		verifyAndClickWithXpath(carePlanProperties.getProperty("Careplan.GasSupplyYes"), "Yes,I have Business Customer");

		for (String postcode:postCodes){

			verifyAndInputByXpath(carePlanProperties.getProperty("Careplan.BusinessCusttomerPostcode"),"post Code entered",postcode);
			Report.updateTestLog("****************************"+postcode+"******************************", "PASS");

			browser.wait(1000);
			verifyAndClickWithXpath(carePlanProperties.getProperty("Careplan.BusinessCusttomerFindAddress"), "Find Address");
			browser.wait(4000);
			verifyAndSelectDropDownBoxbyindex(carePlanProperties.getProperty("Careplan.BusinessCusttomerFindAddressdropdown"),1);
			verifyAndClickWithXpath(carePlanProperties.getProperty("Careplan.BusinessCusttomerConfirmAddress"), "Confirm Address");
			browser.wait(3000);
			if (browser.isElementVisibleWithXpath(carePlanProperties.getProperty("Careplan.GoodnewsSection"))){
				Report.updateTestLog("Good News Section displayed Sucessfully", "WARN");
				verifyAndClickWithXpath(carePlanProperties.getProperty("Careplan.BusinessCusttomerEditCustomer"), "Edit Customer");
			}
			else if (browser.isElementVisibleWithXpath(carePlanProperties.getProperty("Careplan.BusinessCustomerNocoverage"))){
				Report.updateTestLog("Non Coverge Area Error Message Displayed", "WARN");
			}
			else {
				Report.updateTestLog("Address is not selected", "FAIL");

			}
			browser.wait(2000);


		}






	}
	/***********************************************************************************navigation Deeplink************************************************/		
	public void navigateToCareplan () {

		browser.open(ApplicationConfig.APP_BG_URL+"/business/energy-services/maintenance/appliance-service-options");
		browser.wait(2000);
		System.out.println("zzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzz");
		//verifyAndClickWithXpath(carePlanProperties.getProperty("Careplan.GAQ"), "GAQ Clicked");
		System.out.println("zzzzzzzzzzzzzzzzzzzzzzzzsdgblnzzzzzzzzzzzzzzzzzzzzzzzzzz");
		//verifyAndClickWithXpath(carePlanProperties.getProperty("Careplan.GAQ"), "GAQ Clicked");
		verifyAndClickWithXpath(carePlanProperties.getProperty("Careplan.GAQNew"), "GAQ Clicked");

	}
	/***********************************************************************************Post Codes Non mainland************************************************/		

	public void postcodeValidationNonMainLand (){


		String[] postCodes = {"HS8 5JH","KW12 6UJ","AB53 4AA","PH30 4AA"};

		verifyAndClickWithXpath(carePlanProperties.getProperty("Careplan.GasSupplyYes"), "Yes,I have Business Customer");

		for (String postcode:postCodes){

			verifyAndInputByXpath(carePlanProperties.getProperty("Careplan.BusinessCusttomerPostcode"),"post Code entered",postcode);
			Report.updateTestLog("****************************"+postcode+"******************************", "PASS");

			browser.wait(1000);
			verifyAndClickWithXpath(carePlanProperties.getProperty("Careplan.BusinessCusttomerFindAddress"), "Find Address");
			browser.wait(10000);
			verifyAndSelectDropDownBoxbyindex(carePlanProperties.getProperty("Careplan.BusinessCusttomerFindAddressdropdown"),1);	
			verifyAndClickWithXpath(carePlanProperties.getProperty("Careplan.BusinessCusttomerConfirmAddress"), "Confirm Address");
			browser.wait(2000);
			if (browser.isElementVisibleWithXpath(carePlanProperties.getProperty("Careplan.BusinessCustomerNocoverage"))){
				Report.updateTestLog("Non Coverge Area Error Message Displayed", "WARN");
			}
			else {
				Report.updateTestLog("Non Coverge Area Error Message Not Displayed", "Fail");

			}
			browser.wait(2000);
			//verifyAndClickWithXpath(carePlanProperties.getProperty("Careplan.BusinessCusttomerEditCustomer"), "Edit Customer");

		}

	}


	public void yesIHaveGasSupply	(UserProfile userProfile) {

		
		browser.wait(10000);
		browser.wait(10000);
		
		verifyAndClickWithXpath(carePlanProperties.getProperty("Careplan.GasSupplyYes"), "Yes,I have Gas Supply");
		verifyAndInputByXpath(carePlanProperties.getProperty("Careplan.BusinessCusttomerPostcode"),"Post Code", userProfile.getPostCode().trim());
		browser.wait(1000);
		verifyAndClickWithXpath(carePlanProperties.getProperty("Careplan.BusinessCusttomerFindAddress"), "Find Address");
		browser.wait(10000);
		browser.wait(10000);
		verifyAndSelectDropDownBoxbyindex(carePlanProperties.getProperty("Careplan.BusinessCusttomerFindAddressdropdown"),1);	
		verifyAndClickWithXpath(carePlanProperties.getProperty("Careplan.BusinessCusttomerConfirmAddress"), "Confirm Address");
		browser.wait(2000);
		browser.wait(10000);
		if (browser.isElementVisibleWithXpath(carePlanProperties.getProperty("Careplan.GoodnewsSection"))){
			Report.updateTestLog("Good News Section displayed Sucessfully", "WARN");
		}
		else {
			Report.updateTestLog("Good News Section not displayed", "Fail");

		}
	}
	
public void yesIHaveGasSupplyNew(UserProfile userProfile) {

		
		browser.wait(10000);
		browser.wait(10000);
		
		//verifyAndClickWithXpath(carePlanProperties.getProperty("Careplan.GasSupplyYes"), "Yes,I have Gas Supply");
		verifyAndInputByXpath(carePlanProperties.getProperty("Careplan.BusinessCusttomerPostcode"),"Post Code", userProfile.getPostCode().trim());
		browser.wait(1000);
		verifyAndClickWithXpath(carePlanProperties.getProperty("Careplan.BusinessCusttomerFindAddress"), "Find Address");
		browser.wait(10000);
		browser.wait(10000);
		verifyAndSelectDropDownBoxbyindex(carePlanProperties.getProperty("Careplan.BusinessCusttomerFindAddressdropdown"),1);	
		verifyAndClickWithXpath(carePlanProperties.getProperty("Careplan.BusinessCusttomerConfirmAddress"), "Confirm Address");
		browser.wait(2000);
		browser.wait(10000);
		Report.updateTestLog("Address is selected Sucessfully", "WARN");
		/*if (browser.isElementVisibleWithXpath(carePlanProperties.getProperty("Careplan.GoodnewsSection"))){
			Report.updateTestLog("Good News Section displayed Sucessfully", "WARN");
		}
		else {
			Report.updateTestLog("Good News Section not displayed", "Fail");

		}*/
	}

	public void heatingAndhotWaterApplicances (CarePlanProfile carePlanProfile) {

		verifyAndClickWithXpath(carePlanProperties.getProperty("Careplan.BusinessCustomerHeatingAndHotWaterAppliances"), "Heating And Hot Water Appliances ");
		browser.selectfromDropBoxByXpath(carePlanProperties.getProperty("Careplan.BusinessCustomerHAWAppliances") ,carePlanProfile.getAppliance()  );
		//verifyAndClickWithXpath(carePlanProperties.getProperty("Careplan.BusinessCustomerHAWAppliances"), "Appliance Type");
		browser.selectfromDropBoxByXpath(carePlanProperties.getProperty("Careplan.BusinessCustomerHAWApplianceSize") ,carePlanProfile.getApplianceSize());
		//verifyAndClickWithXpath(carePlanProperties.getProperty("Careplan.BusinessCustomerHAWApplianceSize"), "Appliance Size");
		verifyAndClickWithXpath(carePlanProperties.getProperty("Careplan.BusinessCustomerAppliancesContinue"), "Continue");
		productRadioButtonSelection(carePlanProfile);

		for (int i=1;i<=9;i++){
			browser.selectfromDropBoxByXpath(carePlanProperties.getProperty("Careplan.BusinessCustomerHAWApplianceQuantity"),i+"");
			Report.updateTestLog("**********************"+"Quantity "+i+"***********", "WARN");
			validtePrice(carePlanProfile, i);
		}	
	}


	public void productRadioButtonSelection(CarePlanProfile carePlanProfile) {

		if (carePlanProfile.getCareplanOptions().contains("Careplan 1 - service only")){
			if(browser.isElementVisibleWithXpath(carePlanProperties.getProperty("Careplan.CarePlan1without79"))){
			verifyAndClickWithXpath(carePlanProperties.getProperty("Careplan.CarePlan1without79"), "CarePlan 1");
			}
			else if (browser.isElementVisibleWithXpath(carePlanProperties.getProperty("Careplan.CarePlan1Appliance"))) {
				verifyAndClickWithXpath(carePlanProperties.getProperty("Careplan.CarePlan1Appliance"), "CarePlan 1");
				
			}
			Report.updateTestLog("**********************"+"CarePlan 1"+"***************", "Pass");
			
		}
		
		
		else if  (carePlanProfile.getCareplanOptions().contains("Careplan 1 - service only - existing customer discount")){
			if(browser.isElementVisibleWithXpath(carePlanProperties.getProperty("Careplan.CarePlan1without79"))){
			verifyAndClickWithXpath(carePlanProperties.getProperty("Careplan.CarePlan1without79"), "CarePlan 1");
			}
			else if (browser.isElementVisibleWithXpath(carePlanProperties.getProperty("Careplan.CarePlan1Appliance"))){
				verifyAndClickWithXpath(carePlanProperties.getProperty("Careplan.CarePlan1Appliance"), "CarePlan 1");
				
			}
			Report.updateTestLog("**********************"+"CarePlan 1"+"***************", "Pass");
		
		}
		
		
		else if (carePlanProfile.getCareplanOptions().contains("Careplan 2 - service and breakdown incl labour")){
			if(browser.isElementVisibleWithXpath(carePlanProperties.getProperty("Careplan.CarePlan2"))){
			verifyAndClickWithXpath(carePlanProperties.getProperty("Careplan.CarePlan2"), "CarePlan 2");
		}
		else if (browser.isElementVisibleWithXpath(carePlanProperties.getProperty("Careplan.CarePlan2Appliance"))){
			verifyAndClickWithXpath(carePlanProperties.getProperty("Careplan.CarePlan2Appliance"), "CarePlan 2");
			
		}
		
			Report.updateTestLog("**********************"+"CarePlan 2"+"***************", "Pass");
		}
	
		else if (carePlanProfile.getCareplanOptions().contains("Careplan 2s - service and breakdown incl labour and system cover")){
			if(browser.isElementVisibleWithXpath(carePlanProperties.getProperty("Careplan.CarePlan2s"))){
			verifyAndClickWithXpath(carePlanProperties.getProperty("Careplan.CarePlan2s"), "CarePlan 2s");
			}
			else if (browser.isElementVisibleWithXpath(carePlanProperties.getProperty("Careplan.CarePlan2sAppliance"))){
				verifyAndClickWithXpath(carePlanProperties.getProperty("Careplan.CarePlan2sAppliance"), "CarePlan 2s");
			Report.updateTestLog("**********************"+"CarePlan 2s"+"***************", "Pass");
		}
		}
		
		
		else if (carePlanProfile.getCareplanOptions().contains("Careplan 3 - service and breakdown incl labour amp parts")){
			if(browser.isElementVisibleWithXpath(carePlanProperties.getProperty("Careplan.CarePlan3"))){
				
			
			verifyAndClickWithXpath(carePlanProperties.getProperty("Careplan.CarePlan3"), "CarePlan 3");
			}
			else if (browser.isElementVisibleWithXpath(carePlanProperties.getProperty("Careplan.CarePlan3Appliance"))) {
				verifyAndClickWithXpath(carePlanProperties.getProperty("Careplan.CarePlan3Appliance"), "CarePlan 3");
			}
			Report.updateTestLog("**********************"+"CarePlan 3"+"***************", "Pass");
		}
		
		
		else if(carePlanProfile.getCareplanOptions().contains("Careplan 3s - service and breakdown incl labour amp parts and system cover")){
			if(browser.isElementVisibleWithXpath(carePlanProperties.getProperty("Careplan.CarePlan3s"))){
			verifyAndClickWithXpath(carePlanProperties.getProperty("Careplan.CarePlan3s"), "CarePlan 3s");
			}
			else if (browser.isElementVisibleWithXpath(carePlanProperties.getProperty("Careplan.CarePlan3sAppliance"))){
				verifyAndClickWithXpath(carePlanProperties.getProperty("Careplan.CarePlan3sAppliance"), "CarePlan 3s");	
			}
			Report.updateTestLog("**********************"+"CarePlan 3s"+"***************", "Pass");
		}
		
		
		verifyAndClickWithXpath(carePlanProperties.getProperty("Careplan.SelectCarePlan"), "Care Plan Selected");

	}
	
	public void productRadioButtonSelectionNew(CarePlanProfile carePlanProfile) {
		browser.wait(getWaitTime());
		System.out.println("++++++++++++++++++++++++++************************");
		if (carePlanProfile.getCareplanOptions().contains("Careplan 1 - service only")){
			System.out.println("VDGFWERGFWERFWERFW************************");
			browser.wait(getWaitTime());
			verifyAndClickWithXpath(carePlanProperties.getProperty("careplan.Careplan.CarePlan1New"), "CarePlan 1");
			System.out.println("<<<<<<<<<<<<<<<<<<<<>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
			browser.wait(getWaitTime());	
			Report.updateTestLog("**********************"+"CarePlan 1"+"***************", "Pass");
			
		}
				
		else if  (carePlanProfile.getCareplanOptions().contains("Careplan 1 - service only - existing customer discount")){
			
			verifyAndClickWithXpath(carePlanProperties.getProperty("careplan.Careplan.CarePlan1New"), "CarePlan 1");
			
		/*	if(browser.isElementVisibleWithXpath(carePlanProperties.getProperty("careplan.Careplan.CarePlan1New"))){
			verifyAndClickWithXpath(carePlanProperties.getProperty("careplan.Careplan.CarePlan1New"), "CarePlan 1");
			}
			else if (browser.isElementVisibleWithXpath(carePlanProperties.getProperty("Careplan.CarePlan1Appliance"))){
				verifyAndClickWithXpath(carePlanProperties.getProperty("Careplan.CarePlan1Appliance"), "CarePlan 1");
				
			}*/
			Report.updateTestLog("**********************"+"CarePlan 1"+"***************", "Pass");
		
		}
		
		
		else if (carePlanProfile.getCareplanOptions().contains("Careplan 2 - service and breakdown incl labour")){
			
			System.out.println("doneeeeeeeeeeeeee");
			browser.wait(getWaitTime());
			browser.wait(5000);
			verifyAndClickWithXpath(carePlanProperties.getProperty("careplan.Careplan.CarePlan2New"), "CarePlan 2");
			browser.wait(getWaitTime());
			Report.updateTestLog("**********************"+"CarePlan 2"+"***************", "Pass");
		}
	
		else if (carePlanProfile.getCareplanOptions().contains("Careplan 2s - service and breakdown incl labour and system cover")){
			
			System.out.println("doneeeeeeeeeeeeee12");
			browser.wait(getWaitTime());
			browser.wait(5000);
			verifyAndClickWithXpath(carePlanProperties.getProperty("Careplan.CarePlan2sNew"), "CarePlan 2s");
			
			Report.updateTestLog("**********************"+"CarePlan 2s"+"***************", "Pass");
			
		/*	if(browser.isElementVisibleWithXpath(carePlanProperties.getProperty("Careplan.CarePlan2sNew"))){
			verifyAndClickWithXpath(carePlanProperties.getProperty("Careplan.CarePlan2sNew"), "CarePlan 2s");
			}
			else if (browser.isElementVisibleWithXpath(carePlanProperties.getProperty("Careplan.CarePlan2sAppliance"))){
				verifyAndClickWithXpath(carePlanProperties.getProperty("Careplan.CarePlan2sAppliance"), "CarePlan 2s");
			Report.updateTestLog("**********************"+"CarePlan 2s"+"***************", "Pass");
		}*/
		}
		
		
		else if (carePlanProfile.getCareplanOptions().contains("Careplan 3 - service and breakdown incl labour amp parts")){
			
			
			browser.wait(getWaitTime());
			browser.wait(5000);
			verifyAndClickWithXpath(carePlanProperties.getProperty("Careplan.CarePlan3New"), "CarePlan 3");
			Report.updateTestLog("**********************"+"CarePlan 3"+"***************", "Pass");
		}
		
		
		else if(carePlanProfile.getCareplanOptions().contains("Careplan 3s - service and breakdown incl labour amp parts and system cover")){
			
			browser.wait(getWaitTime());
			
			browser.wait(5000);
			verifyAndClickWithXpath(carePlanProperties.getProperty("Careplan.CarePlan3sNew"), "CarePlan 3s");
			Report.updateTestLog("**********************"+"CarePlan 3s"+"***************", "Pass");
		}
		
		
		verifyAndClickWithXpath(carePlanProperties.getProperty("Careplan.SelectCarePlan"), "Confirm Selection");

	}
	public void validtePrice (CarePlanProfile carePlanProfile,int i) {
		String monthlyPrice="";
		String monthlyPricewithVAT="";
		String annualPrice="";
		String annualPricewithVAT="";
		DecimalFormat df=new DecimalFormat("0.00");
	

		float monthlyPriceInput;
		float monthlyPricewithVATInput;
		float annualPriceInput;
		float annualPricewithVATInput;


		if (browser.isElementVisibleWithXpath(carePlanProperties.getProperty("CarePlan.CareplanMonthlyPrice"))){
			monthlyPrice=browser.getTextByXpath(carePlanProperties.getProperty("CarePlan.CareplanMonthlyPrice"));
			Report.updateTestLog("Monthly Price= "+monthlyPrice, "PASS");
			System.out.println("Monthly="+monthlyPrice);
			monthlyPriceInput = ((Float.valueOf(carePlanProfile.getMonthlyPriceNET())) * i);
			monthlyPriceInput1 = Float.parseFloat(df.format(monthlyPriceInput));
			System.out.println("monthly Price calulated"+ monthlyPriceInput1);
			if (monthlyPrice.contains(monthlyPriceInput1+"")){
				Report.updateTestLog("Montly Price Matches with Input and Application Expected : " + monthlyPriceInput1 + " Displayed : " + monthlyPrice , "PASS");
			}
			else {
				Report.updateTestLog("Montly Price displayed wrongly Expected : " + monthlyPriceInput1 + " Displayed : " + monthlyPrice  , "FAIL");
			}
		}
		if (browser.isElementVisibleWithXpath(carePlanProperties.getProperty("CarePlan.CareplanMonthlyPricewithVAT"))){
			monthlyPricewithVAT=browser.getTextByXpath(carePlanProperties.getProperty("CarePlan.CareplanMonthlyPricewithVAT"));
			Report.updateTestLog("Monthly Price With VAT= "+monthlyPricewithVAT, "PASS");
			System.out.println("Monthly with vat="+monthlyPricewithVAT);
			monthlyPricewithVATInput =(( Float.valueOf(carePlanProfile.getMonthlyPriceVAT() ))*i);
			monthlyPricewithVATInput1 = Float.parseFloat(df.format(monthlyPricewithVATInput));
			System.out.println("Monthly Price with Vat calulated"+monthlyPricewithVATInput1);
			if (monthlyPricewithVAT.contains(monthlyPricewithVATInput1+"")){
				Report.updateTestLog("Montly Price with VAT Matches with Input and Application Expected : " + monthlyPricewithVATInput1 + " Displayed : " + monthlyPricewithVAT , "PASS");
			}
			else {
				Report.updateTestLog("Montly Price with VAT displayed wrongly Expected : " + monthlyPricewithVATInput1 + " Displayed : " + monthlyPricewithVAT , "FAIL");
			}
		}
		if (browser.isElementVisibleWithXpath(carePlanProperties.getProperty("CarePlan.CareplanAnnualPrice"))){
			annualPrice=browser.getTextByXpath(carePlanProperties.getProperty("CarePlan.CareplanAnnualPrice"));
			Report.updateTestLog("Annual Price= "+annualPrice, "PASS");
			System.out.println("Annualy="+annualPrice);
			annualPriceInput =(( Float.valueOf(carePlanProfile.getAnnualPriceNET()))*i);
			annualPriceInput1 = Float.parseFloat(df.format(annualPriceInput));
			System.out.println("Annual Price calulated"+annualPriceInput1);
			if (annualPrice.contains(annualPriceInput1+"")){
				Report.updateTestLog("Annual Price Matches with Input and Application Expected : " + annualPriceInput1 + " Displayed : " + annualPrice , "PASS");
			}
			else {
				Report.updateTestLog("Annual Price displayed wrongly Expected : " + annualPriceInput1 + " Displayed : " + annualPrice , "FAIL");
			}
		}
		if (browser.isElementVisibleWithXpath(carePlanProperties.getProperty("CarePlan.CareplanAnnualPricewithVAT"))){
			annualPricewithVAT=browser.getTextByXpath(carePlanProperties.getProperty("CarePlan.CareplanAnnualPricewithVAT"));
			Report.updateTestLog("Annual Price With VAT= "+annualPricewithVAT, "PASS");
			System.out.println("Annualy with vat="+annualPricewithVAT);
			annualPricewithVATInput =(( Float.valueOf(carePlanProfile.getAnnualPriceVAT()))*i);
			annualPricewithVATInput1 = Float.parseFloat(df.format(annualPricewithVATInput));
			System.out.println("Annual Price with VAT calculated"+annualPricewithVATInput1);
			if (annualPricewithVAT.contains(annualPricewithVATInput1+"")){
				Report.updateTestLog("Annual Price with VAT Matches with Input and Application Expected : " + annualPricewithVATInput1 + " Displayed : " + annualPricewithVAT , "PASS");
			}
			else {
				Report.updateTestLog("Annual Price with VAT displayed wrongly Expected : " + annualPricewithVATInput1 + " Displayed : " + annualPricewithVAT , "FAIL");
			}	
		}




	}
	
	public void selectingCustomerDetails(CarePlanProfile carePlanProfile,UserProfile userProfile){
		browser.wait(2000);
		if(carePlanProfile.getExistingBGBcustomer().contains("yes")){
			verifyAndClickWithXpath(carePlanProperties.getProperty("Careplan.BusinessCusttomerYes"), "Yes,Im Business Customer");

		}
		else if (carePlanProfile.getExistingBGBcustomer().contains("No")){

			verifyAndClickWithXpath(carePlanProperties.getProperty("Careplan.BusinessCusttomerNo"), "No,Im Not Business Cusomter");

		}
		verifyAndSelectDropDownBox(carePlanProperties.getProperty("Careplan.BusinessCusttomerTitle"), "Title", "Mr");
		verifyAndInputByXpath(carePlanProperties.getProperty("Careplan.BusinessCusttomerFirstname"),"First Name", userProfile.getFirstName().trim());
		verifyAndInputByXpath(carePlanProperties.getProperty("Careplan.BusinessCusttomerSurname"),"Sur Name", userProfile.getLastName().trim());
		verifyAndInputByXpath(carePlanProperties.getProperty("Careplan.BusinessCusttomerBusinessname"),"Business Name", userProfile.getbusinessname().trim());
		Random random = new Random ();
		strEmail="automation_digitaltest"+ random.nextInt(1000)+"@bgdigitaltest.co.uk";
		strnumber="07124536"+random.nextInt(100)+"5";
		verifyAndInputByXpath(carePlanProperties.getProperty("Careplan.BusinessCusttomerEmailAddress"),"Email Address",strEmail.trim());
		verifyAndInputByXpath(carePlanProperties.getProperty("Careplan.BusinessCusttomerTelephone"),"Telephone",strnumber.trim() );
		verifyAndClickWithXpath(carePlanProperties.getProperty("Careplan.BusinessCusttomerContinue"), "Continue");

		Report.updateTestLog("Contact Details are filled with vaild value", "WARN");
	}
	
///////// Care Plan New chnages ////////////////////////////////////////////////////////////////////////////	
	public void selectingCustomerDetailsNew(CarePlanProfile carePlanProfile,UserProfile userProfile){
		browser.wait(2000);
		/*if(carePlanProfile.getExistingBGBcustomer().contains("yes")){
			verifyAndClickWithXpath(carePlanProperties.getProperty("Careplan.BusinessCusttomerYes"), "Yes,Im Business Customer");

		}
		else if (carePlanProfile.getExistingBGBcustomer().contains("No")){

			verifyAndClickWithXpath(carePlanProperties.getProperty("Careplan.BusinessCusttomerNo"), "No,Im Not Business Cusomter");

		}*/
		verifyAndSelectDropDownBox(carePlanProperties.getProperty("Careplan.BusinessCusttomerTitle"), "Title", "Mr");
		verifyAndInputByXpath(carePlanProperties.getProperty("Careplan.BusinessCusttomerFirstname"),"First Name", userProfile.getFirstName().trim());
		verifyAndInputByXpath(carePlanProperties.getProperty("Careplan.BusinessCusttomerSurname"),"Sur Name", userProfile.getLastName().trim());
		verifyAndInputByXpath(carePlanProperties.getProperty("Careplan.BusinessCusttomerBusinessname"),"Business Name", userProfile.getbusinessname().trim());
		Random random = new Random ();
		strEmail="automation_digitaltest"+ random.nextInt(1000)+"@bgdigitaltest.co.uk";
		strnumber="07124536"+random.nextInt(100)+"5";
		verifyAndInputByXpath(carePlanProperties.getProperty("Careplan.BusinessCusttomerEmailAddress"),"Email Address",strEmail.trim());
		verifyAndInputByXpath(carePlanProperties.getProperty("Careplan.BusinessCusttomerTelephone"),"Telephone",strnumber.trim() );
		browser.wait(1000);
		verifyAndClickWithXpath(carePlanProperties.getProperty("Careplan.BusinessCusttomerContinue"), "Continue");

		Report.updateTestLog("Contact Details are filled with vaild value", "WARN");
	}
	public void pricingVerification(CarePlanProfile carePlanProfile,UserProfile userProfile,String landlord){

		selectingCustomerDetails(carePlanProfile,userProfile);
		yesIHaveGasSupply(userProfile);
		landlordCheck(landlord);
		appliancesPricing(carePlanProfile);
		verifyingPricingForDifferentQuatity(carePlanProfile);
	}
	///////////////////// CarePlan new Chnages in October 20th Release ///////////////////////////////////////
	
	public void pricingVerificationNew(CarePlanProfile carePlanProfile,UserProfile userProfile,String landlord){

		selectingCustomerDetailsNew(carePlanProfile,userProfile);
		yesIHaveGasSupplyNew(userProfile);
		//landlordCheck(landlord);
		appliancesPricingNew(carePlanProfile);
		verifyingPricingForDifferentQuatity(carePlanProfile);
	}
	
	public void pricingVerificationForLGSRandPGSR(CarePlanProfile carePlanProfile,UserProfile userProfile,String Landlord){
		selectingCustomerDetails(carePlanProfile,userProfile);
		yesIHaveGasSupply(userProfile);
		if(Landlord.contains("yes")){
			verifyAndClickWithXpath(carePlanProperties.getProperty("Careplan.BusinessCusttomerlandlordYes"), "Yes,Im landlord");
		}
		else if(Landlord.contains("no")){
			verifyAndClickWithXpath(carePlanProperties.getProperty("Careplan.BusinessCusttomelandlordNo"), "No,Im Not landlord");
		}
		verifyAndClickWithXpath(carePlanProperties.getProperty("Careplan.BusinessCusttomerlandlordContinue"), "Continue");
		Report.updateTestLog("Appliance Details section is displayed", "WARN");
		appliancesPricing(carePlanProfile);
	}
	
	public void selectingLGSRorPGSR(CarePlanProfile carePlanProfile1){
		if(carePlanProfile1.getApplianceCategory().contains("LGSR")){
			verifyAndClickWithXpath(carePlanProperties.getProperty("Careplan.BusinessCusttomerlandlordYes"), "Yes,Im landlord");
		}
		else if(carePlanProfile1.getApplianceCategory().contains("PGSR")){
			verifyAndClickWithXpath(carePlanProperties.getProperty("Careplan.BusinessCusttomelandlordNo"), "No,Im Not landlord");
		}
		verifyAndClickWithXpath(carePlanProperties.getProperty("Careplan.BusinessCusttomerlandlordContinue"), "Continue");
		Report.updateTestLog("Appliance Details section is displayed", "WARN");
		
	}


	public void appliancesPricing(CarePlanProfile carePlanProfile) {
		// Selecting Appliance category //
		selectingApplianceCategory(carePlanProfile);
		// Selecting Appliance type and Appliance size //
		browser.selectfromDropBoxByXpath(carePlanProperties.getProperty("Careplan.BusinessCustomerHAWAppliances") ,carePlanProfile.getAppliance());
		if(browser.isElementVisibleWithXpath(carePlanProperties.getProperty("Careplan.BusinessCustomerHAWApplianceSize"))){
			browser.selectfromDropBoxByXpath(carePlanProperties.getProperty("Careplan.BusinessCustomerHAWApplianceSize") ,carePlanProfile.getApplianceSize());
		}
		verifyAndClickWithXpath(carePlanProperties.getProperty("Careplan.BusinessCustomerAppliancesContinue"), "Continue");
		// Selecting Care plan Options //
		productRadioButtonSelection(carePlanProfile);
	}
	
	public void appliancesPricingNew(CarePlanProfile carePlanProfile) {
		// Selecting Appliance category //
		browser.wait(1000);
		selectingApplianceCategoryNew(carePlanProfile);
		// Selecting Appliance type and Appliance size //
		//browser.selectfromDropBoxByXpath(carePlanProperties.getProperty("Careplan.BusinessCustomerHAWAppliances") ,carePlanProfile.getAppliance());
		// Selecting Appliance size //
		if(browser.isElementVisibleWithXpath(carePlanProperties.getProperty("Careplan.BusinessCustomerHAWApplianceSize"))){
			browser.selectfromDropBoxByXpath(carePlanProperties.getProperty("Careplan.BusinessCustomerHAWApplianceSize") ,carePlanProfile.getApplianceSize());
		}
		browser.wait(getWaitTime());
		verifyAndClickWithXpath(carePlanProperties.getProperty("Careplan.BusinessCustomerAppliancesContinue"), "See Careplans");
		// Selecting Care plan Options //
		//productRadioButtonSelection(carePlanProfile);
		productRadioButtonSelectionNew(carePlanProfile);
	}
	
	// Selecting different quantity //	
	public void verifyingPricingForDifferentQuatity(CarePlanProfile carePlanProfile){
		browser.wait(5000);
		System.out.println("im n quantiity");
		for (int i=1;i<=9;i++){
			browser.selectfromDropBoxByXpath(carePlanProperties.getProperty("Careplan.BusinessCustomerHAWApplianceQuantity"),i+"");
			Report.updateTestLog("**********************"+"Quantity "+i+"***********", "WARN");
			// Validating prices for different products //
			validtePrice(carePlanProfile, i);
			System.out.println("im n quantiity outttt");
		}	
	}
	
	// validating pricing for LGSR and PGSR for different quantity ///
	
	public void verifyingPricingForDifferentQuatityForLGSRandPGSR(CarePlanProfile carePlanProfile, CarePlanProfile carePlanProfile1){
		for (int i=1;i<=9;i++){
			browser.selectfromDropBoxByXpath(carePlanProperties.getProperty("Careplan.BusinessCustomerHAWApplianceQuantity"),i+"");
			Report.updateTestLog("**********************"+"Quantity "+i+"***********", "WARN");
			// Validating prices for different products //
			validtePrice(carePlanProfile, i);
			PricingVerificationAfterAddingLGSRandPGSR(carePlanProfile1);
			browser.clickLinkWithXpath(carePlanProperties.getProperty("CarePlan.removePGSRSSection"));
			
		}	
	}
	public void verifyingPricingForDifferentQuatityForLGSRandPGSRNew(CarePlanProfile carePlanProfile, CarePlanProfile carePlanProfile1){
		for (int i=1;i<=9;i++){
			browser.selectfromDropBoxByXpath(carePlanProperties.getProperty("Careplan.BusinessCustomerHAWApplianceQuantity"),i+"");
			Report.updateTestLog("**********************"+"Quantity "+i+"***********", "WARN");
			// Validating prices for different products //
			validtePrice(carePlanProfile, i);
			PricingVerificationAfterAddingLGSRandPGSRNew(carePlanProfile1);
			browser.clickLinkWithXpath(carePlanProperties.getProperty("CarePlan.removePGSRSSection"));
			
		}	
	}
	public void appliancesPricingForLGSRandPGSRMultipleProducts(CarePlanProfile carePlanProfile) {
		// Selecting Appliance category //
		selectingApplianceCategory(carePlanProfile);
		// Selecting Appliance type and Appliance size //
		browser.selectfromDropBoxByXpath(carePlanProperties.getProperty("Careplan.BusinessCustomerHAWAppliances") ,carePlanProfile.getAppliance());
		if(browser.isElementVisibleWithXpath(carePlanProperties.getProperty("Careplan.BusinessCustomerHAWApplianceSize"))){
			browser.selectfromDropBoxByXpath(carePlanProperties.getProperty("Careplan.BusinessCustomerHAWApplianceSize") ,carePlanProfile.getApplianceSize());
		}
		verifyAndClickWithXpath(carePlanProperties.getProperty("Careplan.BusinessCustomerAppliancesContinue"), "Continue");
		// Selecting Care plan Options //
		productRadioButtonSelection(carePlanProfile);	
		Random random = new Random ();
		int i = random.nextInt(9);
		System.out.println("jjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjj" + i);
		browser.selectfromDropBoxByXpath(carePlanProperties.getProperty("Careplan.BusinessCustomerHAWApplianceQuantity"),i+"");
		validtePrice(carePlanProfile, i);
	//	verifyAndClickWithXpath(carePlanProperties.getProperty("Careplan.BusinessCustomerAppliancesContinue"), "Continue");
	}

	
	public void PricingVerificationAfterAddingLGSRandPGSR(CarePlanProfile carePlanProfile){
		if((carePlanProfile.getApplianceCategory().contains("LGSR")) || (carePlanProfile.getApplianceCategory().contains("PGSR"))){
		// checking the check box //
		browser.wait(5000);
		System.out.println("jjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjj" + carePlanProfile.getAppliance());
		if(carePlanProfile.getApplianceCategory().contains("LGSR")){
			verifyAndClickWithXpath(carePlanProperties.getProperty("Careplan.LGSRCheckBox"), "LGSR check box");
		}
		else if(carePlanProfile.getApplianceCategory().contains("PGSR")){
			verifyAndClickWithXpath(carePlanProperties.getProperty("Careplan.PGSRCheckBox"), "PGSR check box");
		}
		
		browser.selectfromDropBoxByXpath(carePlanProperties.getProperty("Careplan.LandLordApplianceSize"),carePlanProfile.getApplianceSize());
		verifyAndClickWithXpath(carePlanProperties.getProperty("Careplan.LandLordContinueButton"), "Continue");
		
		String annualPrice = browser.getTextByXpath(carePlanProperties.getProperty("Careplan.LandLordAnnualPriceWithoutVAT"));
		String monthlyPrice = browser.getTextByXpath(carePlanProperties.getProperty("Careplan.LandLordMonthlyPriceWithoutVAT"));
		
		if (annualPrice.contains(carePlanProfile.getAnnualPriceNET())){
			Report.updateTestLog("Annual Price of application and expected for Landlord matches Expected : " + carePlanProfile.getAnnualPriceNET() + " Displayed : " + annualPrice, "WARN");
		}
		else{
			Report.updateTestLog("Annual Price of application and expected for Landlord are not as expected - Expected : " + carePlanProfile.getAnnualPriceNET() + " Displayed : " + annualPrice, "FAIL");
		}
		if(browser.isElementVisibleWithXpath(carePlanProperties.getProperty("Careplan.LandLordMonthlyPriceWithoutVAT"))){
		if (monthlyPrice.contains(carePlanProfile.getMonthlyPriceNET())){
			Report.updateTestLog("Monthly Price of application and expected for Landlord matches Expected : " + carePlanProfile.getMonthlyPriceNET() + " Displayed : " + monthlyPrice, "WARN");
		}
		else{
			Report.updateTestLog("Monthly Price of application and expected for Landlord are not as expected - Expected : " + carePlanProfile.getMonthlyPriceNET() + " Displayed : " + monthlyPrice, "FAIL");
		}
		}
		
		 monthlyPriceWithLandlordPrice1 = monthlyPriceInput1 + (Float.valueOf(carePlanProfile.getMonthlyPriceNET()));
		 annualPriceWithLandlordPrice1 = annualPriceInput1 + (Float.valueOf(carePlanProfile.getAnnualPriceNET()));
		 monthlyPriceWithLandlordPriceWithVAT1 = monthlyPricewithVATInput1 + (Float.valueOf(carePlanProfile.getMonthlyPriceVAT()));
		 annualPriceWithLandlordPriceWithVAT1 = annualPricewithVATInput1 + (Float.valueOf(carePlanProfile.getAnnualPriceVAT()));
		
		DecimalFormat df=new DecimalFormat("0.00");
		String monthlyPriceWithLandlordPrice = df.format(monthlyPriceWithLandlordPrice1);
		String annualPriceWithLandlordPrice = df.format(annualPriceWithLandlordPrice1);
		String monthlyPriceWithLandlordPriceWithVAT = df.format(monthlyPriceWithLandlordPriceWithVAT1);
		String annualPriceWithLandlordPriceWithVAT = df.format(annualPriceWithLandlordPriceWithVAT1);
		
		if(browser.isElementVisibleWithXpath(carePlanProperties.getProperty("CarePlan.CareplanMonthlyPrice"))){
			String DisplayedMonthlyPrice = browser.getTextByXpath(carePlanProperties.getProperty("CarePlan.CareplanMonthlyPrice"));
			if(DisplayedMonthlyPrice.contains(monthlyPriceWithLandlordPrice+"")){
				Report.updateTestLog("Monthly Price with Landlord Price :: matches Expected : " + monthlyPriceWithLandlordPrice + " Displayed : " + DisplayedMonthlyPrice, "WARN");
			}
			else{
				Report.updateTestLog("Monthly Price with Landlord Price :: not matches Expected : " + monthlyPriceWithLandlordPrice + " Displayed : " + DisplayedMonthlyPrice, "FAIL");
			}
		}
		
		if(browser.isElementVisibleWithXpath(carePlanProperties.getProperty("CarePlan.CareplanMonthlyPricewithVAT"))){
			String DisplayedMonthlyPriceVAT = browser.getTextByXpath(carePlanProperties.getProperty("CarePlan.CareplanMonthlyPricewithVAT"));
			if(DisplayedMonthlyPriceVAT.contains(monthlyPriceWithLandlordPriceWithVAT+"")){
				Report.updateTestLog("Monthly Price VAT with Landlord Price :: matches Expected : " + monthlyPriceWithLandlordPriceWithVAT + " Displayed : " + DisplayedMonthlyPriceVAT, "WARN");
			}
			else{
				Report.updateTestLog("Monthly Price VAT with Landlord Price :: not matches Expected : " + monthlyPriceWithLandlordPriceWithVAT + " Displayed : " + DisplayedMonthlyPriceVAT, "FAIL");
			}
		}
		if(browser.isElementVisibleWithXpath(carePlanProperties.getProperty("CarePlan.CareplanAnnualPrice"))){
			String DisplayedAnnualPrice = browser.getTextByXpath(carePlanProperties.getProperty("CarePlan.CareplanAnnualPrice"));
			if(DisplayedAnnualPrice.contains(annualPriceWithLandlordPrice+"")){
				Report.updateTestLog("Annual Price with Landlord Price :: matches Expected : " + annualPriceWithLandlordPrice + " Displayed : " + DisplayedAnnualPrice, "WARN");
			}
			else{
				Report.updateTestLog("Annual Price with Landlord Price :: not matches Expected : " + annualPriceWithLandlordPrice + " Displayed : " + DisplayedAnnualPrice, "FAIL");
			}
		}
		
		if(browser.isElementVisibleWithXpath(carePlanProperties.getProperty("CarePlan.CareplanAnnualPricewithVAT"))){
			String DisplayedAnnualPriceVAT = browser.getTextByXpath(carePlanProperties.getProperty("CarePlan.CareplanAnnualPricewithVAT"));
			if(DisplayedAnnualPriceVAT.contains(annualPriceWithLandlordPriceWithVAT+"")){
				Report.updateTestLog("Annual Price VAT with Landlord Price :: matches Expected : " + annualPriceWithLandlordPriceWithVAT + " Displayed : " + DisplayedAnnualPriceVAT, "WARN");
			}
			else{
				Report.updateTestLog("Annual Price VAT with Landlord Price :: not matches Expected : " + annualPriceWithLandlordPriceWithVAT + " Displayed : " + DisplayedAnnualPriceVAT, "FAIL");
			}
		}
		
		}
	
		
	}
	
	public void PricingVerificationAfterAddingLGSRandPGSRNew(CarePlanProfile carePlanProfile){
		if((carePlanProfile.getApplianceCategory().contains("LGSR")) || (carePlanProfile.getApplianceCategory().contains("PGSR"))){
		// checking the check box //
		browser.wait(5000);
		System.out.println("jjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjj" + carePlanProfile.getAppliance());
		if(carePlanProfile.getApplianceCategory().contains("LGSR")){
			verifyAndClickWithXpath(carePlanProperties.getProperty("Careplan.SelectLGSRNew"), "LGSR button");
			browser.wait(1000);
			verifyAndClickWithXpath(carePlanProperties.getProperty("Careplan.LGSRSelectNew"), "confirm LGSR");
		}
		else if(carePlanProfile.getApplianceCategory().contains("PGSR")){
			verifyAndClickWithXpath(carePlanProperties.getProperty("Careplan.SelectPGSRNew"), "PGSR button");
			browser.wait(1000);
			verifyAndClickWithXpath(carePlanProperties.getProperty("Careplan.PGSRSelectNew"), "confirm PGSR");
		}
		
		browser.selectfromDropBoxByXpath(carePlanProperties.getProperty("Careplan.LandLordApplianceSize"),carePlanProfile.getApplianceSize());
		verifyAndClickWithXpath(carePlanProperties.getProperty("Careplan.LandLordContinueButton"), "Continue");
		
		String annualPrice = browser.getTextByXpath(carePlanProperties.getProperty("Careplan.LandLordAnnualPriceWithoutVAT"));
		String monthlyPrice = browser.getTextByXpath(carePlanProperties.getProperty("Careplan.LandLordMonthlyPriceWithoutVAT"));
		
		if (annualPrice.contains(carePlanProfile.getAnnualPriceNET())){
			Report.updateTestLog("Annual Price of application and expected for Landlord matches Expected : " + carePlanProfile.getAnnualPriceNET() + " Displayed : " + annualPrice, "WARN");
		}
		else{
			Report.updateTestLog("Annual Price of application and expected for Landlord are not as expected - Expected : " + carePlanProfile.getAnnualPriceNET() + " Displayed : " + annualPrice, "FAIL");
		}
		if(browser.isElementVisibleWithXpath(carePlanProperties.getProperty("Careplan.LandLordMonthlyPriceWithoutVAT"))){
		if (monthlyPrice.contains(carePlanProfile.getMonthlyPriceNET())){
			Report.updateTestLog("Monthly Price of application and expected for Landlord matches Expected : " + carePlanProfile.getMonthlyPriceNET() + " Displayed : " + monthlyPrice, "WARN");
		}
		else{
			Report.updateTestLog("Monthly Price of application and expected for Landlord are not as expected - Expected : " + carePlanProfile.getMonthlyPriceNET() + " Displayed : " + monthlyPrice, "FAIL");
		}
		}
		
		 monthlyPriceWithLandlordPrice1 = monthlyPriceInput1 + (Float.valueOf(carePlanProfile.getMonthlyPriceNET()));
		 annualPriceWithLandlordPrice1 = annualPriceInput1 + (Float.valueOf(carePlanProfile.getAnnualPriceNET()));
		 monthlyPriceWithLandlordPriceWithVAT1 = monthlyPricewithVATInput1 + (Float.valueOf(carePlanProfile.getMonthlyPriceVAT()));
		 annualPriceWithLandlordPriceWithVAT1 = annualPricewithVATInput1 + (Float.valueOf(carePlanProfile.getAnnualPriceVAT()));
		
		DecimalFormat df=new DecimalFormat("0.00");
		String monthlyPriceWithLandlordPrice = df.format(monthlyPriceWithLandlordPrice1);
		String annualPriceWithLandlordPrice = df.format(annualPriceWithLandlordPrice1);
		String monthlyPriceWithLandlordPriceWithVAT = df.format(monthlyPriceWithLandlordPriceWithVAT1);
		String annualPriceWithLandlordPriceWithVAT = df.format(annualPriceWithLandlordPriceWithVAT1);
		
		if(browser.isElementVisibleWithXpath(carePlanProperties.getProperty("CarePlan.CareplanMonthlyPrice"))){
			String DisplayedMonthlyPrice = browser.getTextByXpath(carePlanProperties.getProperty("CarePlan.CareplanMonthlyPrice"));
			if(DisplayedMonthlyPrice.contains(monthlyPriceWithLandlordPrice+"")){
				Report.updateTestLog("Monthly Price with Landlord Price :: matches Expected : " + monthlyPriceWithLandlordPrice + " Displayed : " + DisplayedMonthlyPrice, "WARN");
			}
			else{
				Report.updateTestLog("Monthly Price with Landlord Price :: not matches Expected : " + monthlyPriceWithLandlordPrice + " Displayed : " + DisplayedMonthlyPrice, "FAIL");
			}
		}
		
		if(browser.isElementVisibleWithXpath(carePlanProperties.getProperty("CarePlan.CareplanMonthlyPricewithVAT"))){
			String DisplayedMonthlyPriceVAT = browser.getTextByXpath(carePlanProperties.getProperty("CarePlan.CareplanMonthlyPricewithVAT"));
			if(DisplayedMonthlyPriceVAT.contains(monthlyPriceWithLandlordPriceWithVAT+"")){
				Report.updateTestLog("Monthly Price VAT with Landlord Price :: matches Expected : " + monthlyPriceWithLandlordPriceWithVAT + " Displayed : " + DisplayedMonthlyPriceVAT, "WARN");
			}
			else{
				Report.updateTestLog("Monthly Price VAT with Landlord Price :: not matches Expected : " + monthlyPriceWithLandlordPriceWithVAT + " Displayed : " + DisplayedMonthlyPriceVAT, "FAIL");
			}
		}
		if(browser.isElementVisibleWithXpath(carePlanProperties.getProperty("CarePlan.CareplanAnnualPrice"))){
			String DisplayedAnnualPrice = browser.getTextByXpath(carePlanProperties.getProperty("CarePlan.CareplanAnnualPrice"));
			if(DisplayedAnnualPrice.contains(annualPriceWithLandlordPrice+"")){
				Report.updateTestLog("Annual Price with Landlord Price :: matches Expected : " + annualPriceWithLandlordPrice + " Displayed : " + DisplayedAnnualPrice, "WARN");
			}
			else{
				Report.updateTestLog("Annual Price with Landlord Price :: not matches Expected : " + annualPriceWithLandlordPrice + " Displayed : " + DisplayedAnnualPrice, "FAIL");
			}
		}
		
		if(browser.isElementVisibleWithXpath(carePlanProperties.getProperty("CarePlan.CareplanAnnualPricewithVAT"))){
			String DisplayedAnnualPriceVAT = browser.getTextByXpath(carePlanProperties.getProperty("CarePlan.CareplanAnnualPricewithVAT"));
			if(DisplayedAnnualPriceVAT.contains(annualPriceWithLandlordPriceWithVAT+"")){
				Report.updateTestLog("Annual Price VAT with Landlord Price :: matches Expected : " + annualPriceWithLandlordPriceWithVAT + " Displayed : " + DisplayedAnnualPriceVAT, "WARN");
			}
			else{
				Report.updateTestLog("Annual Price VAT with Landlord Price :: not matches Expected : " + annualPriceWithLandlordPriceWithVAT + " Displayed : " + DisplayedAnnualPriceVAT, "FAIL");
			}
		}
		
		}
	
		
	}
	
	public void pricingVerificationForLGSRAndPGSR(CarePlanProfile carePlanProfile){
		if((carePlanProfile.getApplianceCategory().contains("LGSR")) || (carePlanProfile.getApplianceCategory().contains("PGSR"))){
			// checking the check box //
			browser.wait(5000);
			System.out.println("jjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjj" + carePlanProfile.getAppliance());
			if(carePlanProfile.getApplianceCategory().contains("LGSR")){
				verifyAndClickWithXpath(carePlanProperties.getProperty("Careplan.LGSRCheckBox"), "LGSR check box");
			}
			else if(carePlanProfile.getApplianceCategory().contains("PGSR")){
				verifyAndClickWithXpath(carePlanProperties.getProperty("Careplan.PGSRCheckBox"), "PGSR check box");
			}
			
			browser.selectfromDropBoxByXpath(carePlanProperties.getProperty("Careplan.LandLordApplianceSize"),carePlanProfile.getApplianceSize());
			verifyAndClickWithXpath(carePlanProperties.getProperty("Careplan.LandLordContinueButton"), "Continue");
			
			String annualPrice = browser.getTextByXpath(carePlanProperties.getProperty("Careplan.LandLordAnnualPriceWithoutVAT"));
			String monthlyPrice = browser.getTextByXpath(carePlanProperties.getProperty("Careplan.LandLordMonthlyPriceWithoutVAT"));
			
			if (annualPrice.contains(carePlanProfile.getAnnualPriceNET())){
				Report.updateTestLog("Annual Price of application and expected for Landlord matches Expected : " + carePlanProfile.getAnnualPriceNET() + " Displayed : " + annualPrice, "WARN");
			}
			else{
				Report.updateTestLog("Annual Price of application and expected for Landlord are not as expected - Expected : " + carePlanProfile.getAnnualPriceNET() + " Displayed : " + annualPrice, "FAIL");
			}
			
			if (monthlyPrice.contains(carePlanProfile.getMonthlyPriceNET())){
				Report.updateTestLog("Monthly Price of application and expected for Landlord matches Expected : " + carePlanProfile.getMonthlyPriceNET() + " Displayed : " + monthlyPrice, "WARN");
			}
			else{
				Report.updateTestLog("Monthly Price of application and expected for Landlord are not as expected - Expected : " + carePlanProfile.getMonthlyPriceNET() + " Displayed : " + monthlyPrice, "FAIL");
			}
			
			 monthlyPriceWithLandlordPrice1 = monthlyPriceInput1 + (Float.valueOf(carePlanProfile.getMonthlyPriceNET()));
			 annualPriceWithLandlordPrice1 = annualPriceInput1 + (Float.valueOf(carePlanProfile.getAnnualPriceNET()));
			 monthlyPriceWithLandlordPriceWithVAT1 = monthlyPricewithVATInput1 + (Float.valueOf(carePlanProfile.getMonthlyPriceVAT()));
			 annualPriceWithLandlordPriceWithVAT1 = annualPricewithVATInput1 + (Float.valueOf(carePlanProfile.getAnnualPriceVAT()));
		}
	}
	
	public void pricingVerificationForLGSRAndPGSRNew(CarePlanProfile carePlanProfile){
		if((carePlanProfile.getApplianceCategory().contains("LGSR")) || (carePlanProfile.getApplianceCategory().contains("PGSR"))){
			// checking the check box //
			browser.wait(5000);
			System.out.println("jjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjj" + carePlanProfile.getAppliance());
			if(carePlanProfile.getApplianceCategory().contains("LGSR")){
				verifyAndClickWithXpath(carePlanProperties.getProperty("Careplan.Selectlandlord"), "Select LGSR");
				browser.wait(1000);
				verifyAndClickWithXpath(carePlanProperties.getProperty("Careplan.LandlordGasSelect"), "Select");
			}
			else if(carePlanProfile.getApplianceCategory().contains("PGSR")){
				verifyAndClickWithXpath(carePlanProperties.getProperty("Careplan.SelectPGSR"), "Select PGSR");
				browser.wait(1000);
				verifyAndClickWithXpath(carePlanProperties.getProperty("Careplan.PGSRSelect"), "Select");
			}
			
			browser.selectfromDropBoxByXpath(carePlanProperties.getProperty("Careplan.LandLordApplianceSize"),carePlanProfile.getApplianceSize());
			verifyAndClickWithXpath(carePlanProperties.getProperty("Careplan.LandLordContinueButton"), "Continue");
			
			String annualPrice = browser.getTextByXpath(carePlanProperties.getProperty("Careplan.LandLordAnnualPriceWithoutVAT"));
			String monthlyPrice = browser.getTextByXpath(carePlanProperties.getProperty("Careplan.LandLordMonthlyPriceWithoutVAT"));
			
			if (annualPrice.contains(carePlanProfile.getAnnualPriceNET())){
				Report.updateTestLog("Annual Price of application and expected for Landlord matches Expected : " + carePlanProfile.getAnnualPriceNET() + " Displayed : " + annualPrice, "WARN");
			}
			else{
				Report.updateTestLog("Annual Price of application and expected for Landlord are not as expected - Expected : " + carePlanProfile.getAnnualPriceNET() + " Displayed : " + annualPrice, "FAIL");
			}
			
			if (monthlyPrice.contains(carePlanProfile.getMonthlyPriceNET())){
				Report.updateTestLog("Monthly Price of application and expected for Landlord matches Expected : " + carePlanProfile.getMonthlyPriceNET() + " Displayed : " + monthlyPrice, "WARN");
			}
			else{
				Report.updateTestLog("Monthly Price of application and expected for Landlord are not as expected - Expected : " + carePlanProfile.getMonthlyPriceNET() + " Displayed : " + monthlyPrice, "FAIL");
			}
			
			 monthlyPriceWithLandlordPrice1 = monthlyPriceInput1 + (Float.valueOf(carePlanProfile.getMonthlyPriceNET()));
			 annualPriceWithLandlordPrice1 = annualPriceInput1 + (Float.valueOf(carePlanProfile.getAnnualPriceNET()));
			 monthlyPriceWithLandlordPriceWithVAT1 = monthlyPricewithVATInput1 + (Float.valueOf(carePlanProfile.getMonthlyPriceVAT()));
			 annualPriceWithLandlordPriceWithVAT1 = annualPricewithVATInput1 + (Float.valueOf(carePlanProfile.getAnnualPriceVAT()));
		}
	}
	
	
	
	public void selectingApplianceCategory(CarePlanProfile carePlanProfile){
		browser.wait(5000);
	//	System.out.println("xxxxxxxxxxxxxxxxxxxxxxxxx" + carePlanProfile.getApplianceCategory());
		if(carePlanProfile.getApplianceCategory().contains("Heating and hotwater appliances")){
			browser.wait(5000);
			verifyAndClickWithXpath(carePlanProperties.getProperty("Careplan.BusinessCustomerHeatingAndHotWaterAppliances"), "Heating And Hot Water Appliances ");
		}
		else if(carePlanProfile.getApplianceCategory().contains("Catering equipment")){
			System.out.println("xxxxxxxxxxxxxxxxxxxxxxxxx");
			verifyAndClickWithXpath(carePlanProperties.getProperty("Careplan.BusinessCustomerCateringequipment"), "Catering equipment");
		}
		else if(carePlanProfile.getApplianceCategory().contains("Laundry")){
			verifyAndClickWithXpath(carePlanProperties.getProperty("Careplan.BusinessCustomerLaundry"), "Customer Laundry Appliances");
		}
	}

	public void selectingApplianceCategoryNew(CarePlanProfile carePlanProfile){
		browser.wait(5000);
		//System.out.println("xxxxxxxxxxxxxxxxxxxxxxxxx" + carePlanProfile.getAppliance());
		if(carePlanProfile.getAppliance().contains("Boiler")){
			browser.wait(5000);
			System.out.println("NEWWWWWW010101010101010101WWWWWWWWWWWWW");
			verifyAndClickWithXpath(carePlanProperties.getProperty("careplan.Boiler1"), "Boiler");
		}
		else if(carePlanProfile.getAppliance().contains("Full heating system")){
			System.out.println("HELLLLLLLLLLLLLLO");
			verifyAndClickWithXpath(carePlanProperties.getProperty("careplan.Fullheatingsystem"), "Full heating system");
		}
		else if(carePlanProfile.getAppliance().contains("Storage water heater")){
			verifyAndClickWithXpath(carePlanProperties.getProperty("careplan.Storagewaterheater"), "Storage water heater");
		}
		else if(carePlanProfile.getAppliance().contains("Suspended Warm Air Heater")){
			verifyAndClickWithXpath(carePlanProperties.getProperty("careplan.SuspendedWarmAirHeater"), "Suspended Warm Air Heater");
		}
	}
	
	
public void validtePriceforMultipleproductsWithLGSRorPGSR() {
		
		String monthlyPrice="";
		String monthlyPricewithVAT="";
		String annualPrice="";
		String annualPricewithVAT="";

		totalmonthlyPriceWithVAT = totalmonthlyPriceWithVAT +monthlyPriceWithLandlordPriceWithVAT1;
		totalmonthlyPriceWithoutVAT = totalmonthlyPriceWithoutVAT + monthlyPriceWithLandlordPrice1;
		totalannualPriceWithoutVAT = totalannualPriceWithoutVAT + annualPriceWithLandlordPrice1;
		totalannualPriceWithVAT= totalannualPriceWithVAT +  annualPriceWithLandlordPriceWithVAT1;
		
		System.out.println("hhhhhhhhhh" + totalmonthlyPriceWithVAT  + monthlyPriceWithLandlordPriceWithVAT1);
		System.out.println("hhhhhhhhhh" + totalmonthlyPriceWithoutVAT + monthlyPriceWithLandlordPrice1);
		System.out.println("hhhhhhhhhh" + totalannualPriceWithoutVAT + annualPriceWithLandlordPrice1);
		System.out.println("hhhhhhhhhh" + totalannualPriceWithVAT + annualPriceWithLandlordPriceWithVAT1);
		
		
		DecimalFormat df=new DecimalFormat("0.00");
		String totalmonthlyPriceWithVAT1= df.format(totalmonthlyPriceWithVAT)+"";
		String totalmonthlyPriceWithoutVAT1= df.format(totalmonthlyPriceWithoutVAT)+"";
		String totalannualPriceWithoutVAT1= df.format(totalannualPriceWithoutVAT)+"";
		String totalannualPriceWithVAT1= df.format(totalannualPriceWithVAT)+"";
		
		if (browser.isElementVisibleWithXpath(carePlanProperties.getProperty("CarePlan.CareplanMonthlyPrice"))){
			monthlyPrice=browser.getTextByXpath(carePlanProperties.getProperty("CarePlan.CareplanMonthlyPrice"));
			Report.updateTestLog("Monthly Price= "+monthlyPrice, "PASS");
			System.out.println("Monthly="+monthlyPrice);
			
			
			
		if (monthlyPrice.contains(totalmonthlyPriceWithoutVAT1+"")){
				Report.updateTestLog("Montly Price Matches with Input and Application::Expected : " + totalmonthlyPriceWithoutVAT1 + " Displayed : " + monthlyPrice , "WARN");
			}
			else {
				Report.updateTestLog("Montly Price displayed wrongly::Expected : " + totalmonthlyPriceWithoutVAT1 + " Displayed : " + monthlyPrice  , "FAIL");
			}
		}
		if (browser.isElementVisibleWithXpath(carePlanProperties.getProperty("CarePlan.CareplanMonthlyPricewithVAT"))){
			monthlyPricewithVAT=browser.getTextByXpath(carePlanProperties.getProperty("CarePlan.CareplanMonthlyPricewithVAT"));
			Report.updateTestLog("Monthly Price With VAT= "+monthlyPricewithVAT, "PASS");
			System.out.println("Monthly with vat="+monthlyPricewithVAT);
	
		if (monthlyPricewithVAT.contains(totalmonthlyPriceWithVAT1+"")){
				Report.updateTestLog("Montly Price with VAT Matches with Input and Application::Expected : " + totalmonthlyPriceWithVAT1 + " Displayed : " + monthlyPricewithVAT , "WARN");
			}
			else {
				Report.updateTestLog("Montly Price with VAT displayed wrongly::Expected : " + totalmonthlyPriceWithVAT1 + " Displayed : " + monthlyPricewithVAT , "FAIL");
			}
		}
		if (browser.isElementVisibleWithXpath(carePlanProperties.getProperty("CarePlan.CareplanAnnualPrice"))){
			annualPrice=browser.getTextByXpath(carePlanProperties.getProperty("CarePlan.CareplanAnnualPrice"));
			Report.updateTestLog("Annual Price= "+annualPrice, "PASS");
			System.out.println("Annualy="+annualPrice);
		
			if (annualPrice.contains(totalannualPriceWithoutVAT1+"")){
				Report.updateTestLog("Annual Price Matches with Input and Application::Expected : " + totalannualPriceWithoutVAT1 + " Displayed : " + annualPrice , "WARN");
			}
			else {
				Report.updateTestLog("Annual Price displayed wrongly::Expected : " + totalannualPriceWithoutVAT1 + " Displayed : " + annualPrice , "FAIL");
			}
		}
		if (browser.isElementVisibleWithXpath(carePlanProperties.getProperty("CarePlan.CareplanAnnualPricewithVAT"))){
			annualPricewithVAT=browser.getTextByXpath(carePlanProperties.getProperty("CarePlan.CareplanAnnualPricewithVAT"));
			Report.updateTestLog("Annual Price With VAT= "+annualPricewithVAT, "PASS");
			System.out.println("Annualy with vat="+annualPricewithVAT);
			
			if (annualPricewithVAT.contains(totalannualPriceWithVAT1+"")){
				Report.updateTestLog("Annual Price with VAT Matches with Input and Application::Expected : " + totalannualPriceWithVAT1 + " Displayed : " + annualPricewithVAT , "WARN");
			}
			else {
				Report.updateTestLog("Annual Price with VAT displayed wrongly::Expected : " + totalannualPriceWithVAT1 + " Displayed : " + annualPricewithVAT , "FAIL");
			}			
			
		}

	}

	public void validtePriceforMultipleproducts(CarePlanProfile carePlanProfile) {
		
		String monthlyPrice="";
		String monthlyPricewithVAT="";
		String annualPrice="";
		String annualPricewithVAT="";


		/*loat monthlyPriceInput;
		float monthlyPricewithVATInput;
		float annualPriceInput;
		float annualPricewithVATInput;
*/
		totalmonthlyPriceWithVAT = totalmonthlyPriceWithVAT + (Float.valueOf(carePlanProfile.getMonthlyPriceVAT()));
		totalmonthlyPriceWithoutVAT = totalmonthlyPriceWithoutVAT +  (Float.valueOf(carePlanProfile.getMonthlyPriceNET()));
		totalannualPriceWithoutVAT = totalannualPriceWithoutVAT +  (Float.valueOf(carePlanProfile.getAnnualPriceNET()));
		totalannualPriceWithVAT= totalannualPriceWithVAT + (Float.valueOf(carePlanProfile.getAnnualPriceVAT()));
		
		DecimalFormat df=new DecimalFormat("0.00");
		
		System.out.println("added"+totalmonthlyPriceWithVAT);
		System.out.println("added"+totalmonthlyPriceWithoutVAT);
		System.out.println("added"+totalannualPriceWithoutVAT);
		System.out.println("added"+totalannualPriceWithVAT);
		
		
		String totalmonthlyPriceWithVAT1= df.format(totalmonthlyPriceWithVAT)+"";
		String totalmonthlyPriceWithoutVAT1= df.format(totalmonthlyPriceWithoutVAT)+"";
		String totalannualPriceWithoutVAT1= df.format(totalannualPriceWithoutVAT)+"";
		String totalannualPriceWithVAT1= df.format(totalannualPriceWithVAT)+"";
		
		
		System.out.println("N1="+totalmonthlyPriceWithVAT1);
		System.out.println("N2="+totalmonthlyPriceWithoutVAT1);
		System.out.println("N3="+totalannualPriceWithoutVAT1);
		System.out.println("N4="+totalannualPriceWithVAT1);
		
		
		
		if (browser.isElementVisibleWithXpath(carePlanProperties.getProperty("CarePlan.CareplanMonthlyPrice"))){
			monthlyPrice=browser.getTextByXpath(carePlanProperties.getProperty("CarePlan.CareplanMonthlyPrice"));
			Report.updateTestLog("Monthly Price= "+monthlyPrice, "PASS");
			System.out.println("Monthly="+monthlyPrice);
			
			
			
		if (monthlyPrice.contains(totalmonthlyPriceWithoutVAT1+"")){
				Report.updateTestLog("Montly Price Matches with Input and Application::Expected : " + totalmonthlyPriceWithoutVAT1 + " Displayed : " + monthlyPrice , "WARN");
			}
			else {
				Report.updateTestLog("Montly Price displayed wrongly::Expected : " + totalmonthlyPriceWithoutVAT1 + " Displayed : " + monthlyPrice  , "FAIL");
			}
		}
		if (browser.isElementVisibleWithXpath(carePlanProperties.getProperty("CarePlan.CareplanMonthlyPricewithVAT"))){
			monthlyPricewithVAT=browser.getTextByXpath(carePlanProperties.getProperty("CarePlan.CareplanMonthlyPricewithVAT"));
			Report.updateTestLog("Monthly Price With VAT= "+monthlyPricewithVAT, "PASS");
			System.out.println("Monthly with vat="+monthlyPricewithVAT);
	
		if (monthlyPricewithVAT.contains(totalmonthlyPriceWithVAT1+"")){
				Report.updateTestLog("Montly Price with VAT Matches with Input and Application::Expected : " + totalmonthlyPriceWithVAT1 + " Displayed : " + monthlyPricewithVAT , "WARN");
			}
			else {
				Report.updateTestLog("Montly Price with VAT displayed wrongly::Expected : " + totalmonthlyPriceWithVAT1 + " Displayed : " + monthlyPricewithVAT , "FAIL");
			}
		}
		if (browser.isElementVisibleWithXpath(carePlanProperties.getProperty("CarePlan.CareplanAnnualPrice"))){
			annualPrice=browser.getTextByXpath(carePlanProperties.getProperty("CarePlan.CareplanAnnualPrice"));
			Report.updateTestLog("Annual Price= "+annualPrice, "PASS");
			System.out.println("Annualy="+annualPrice);
		
			if (annualPrice.contains(totalannualPriceWithoutVAT1+"")){
				Report.updateTestLog("Annual Price Matches with Input and Application::Expected : " + totalannualPriceWithoutVAT1 + " Displayed : " + annualPrice , "WARN");
			}
			else {
				Report.updateTestLog("Annual Price displayed wrongly::Expected : " + totalannualPriceWithoutVAT1 + " Displayed : " + annualPrice , "FAIL");
			}
		}
		if (browser.isElementVisibleWithXpath(carePlanProperties.getProperty("CarePlan.CareplanAnnualPricewithVAT"))){
			annualPricewithVAT=browser.getTextByXpath(carePlanProperties.getProperty("CarePlan.CareplanAnnualPricewithVAT"));
			Report.updateTestLog("Annual Price With VAT= "+annualPricewithVAT, "PASS");
			System.out.println("Annualy with vat="+annualPricewithVAT);
			
			if (annualPricewithVAT.contains(totalannualPriceWithVAT1+"")){
				Report.updateTestLog("Annual Price with VAT Matches with Input and Application::Expected : " + totalannualPriceWithVAT1 + " Displayed : " + annualPricewithVAT , "WARN");
			}
			else {
				Report.updateTestLog("Annual Price with VAT displayed wrongly::Expected : " + totalannualPriceWithVAT1 + " Displayed : " + annualPricewithVAT , "FAIL");
			}			
			
		}

	}
	
	public void pricingVerificationforMultipleproducts(CarePlanProfile carePlanProfile){
		validtePriceforMultipleproducts(carePlanProfile);
	}
	
	public void addAnotherAppliance() {
		if(browser.isElementVisibleWithXpath(carePlanProperties.getProperty("CarePlan.CareplanAddAnotherAppliance"))){
		verifyAndClickWithXpath(carePlanProperties.getProperty("CarePlan.CareplanAddAnotherAppliance"), "Add Another Appliance");
		}
		browser.wait(2000);
		
	}

	public void bespokeVerification(CarePlanProfile carePlanProfile,UserProfile userProfile,String landlord){
		
		browser.wait(2000);
		if(carePlanProfile.getExistingBGBcustomer().contains("Yes")){
			verifyAndClickWithXpath(carePlanProperties.getProperty("Careplan.BusinessCusttomerYes"), "Yes,Im Business Customer");	
		}
		else if (carePlanProfile.getExistingBGBcustomer().contains("No")){	
			verifyAndClickWithXpath(carePlanProperties.getProperty("Careplan.BusinessCusttomerNo"), "No,Im Not Business Cusomter");	
		}
		verifyAndSelectDropDownBox(carePlanProperties.getProperty("Careplan.BusinessCusttomerTitle"), "Title", "Mr");
		 verifyAndInputByXpath(carePlanProperties.getProperty("Careplan.BusinessCusttomerFirstname"),"First Name", userProfile.getFirstName().trim());
		 verifyAndInputByXpath(carePlanProperties.getProperty("Careplan.BusinessCusttomerSurname"),"Sur Name", userProfile.getLastName().trim());
		 verifyAndInputByXpath(carePlanProperties.getProperty("Careplan.BusinessCusttomerBusinessname"),"Business Name", userProfile.getbusinessname().trim());
		 verifyAndInputByXpath(carePlanProperties.getProperty("Careplan.BusinessCusttomerEmailAddress"),"Email Address", userProfile.getEmail().trim());
		 verifyAndInputByXpath(carePlanProperties.getProperty("Careplan.BusinessCusttomerTelephone"),"Telephone", userProfile.getMobileNumber().trim());
		 verifyAndClickWithXpath(carePlanProperties.getProperty("Careplan.BusinessCusttomerContinue"), "Continue");
		 Report.updateTestLog("Contact Details are filled with vaild value", "WARN");
		 yesIHaveGasSupply(userProfile);
		 landlordCheck(landlord);
		 bespokeErrorpricing(carePlanProfile);

			}
public void bespokeVerificationNew(CarePlanProfile carePlanProfile,UserProfile userProfile){
		
		browser.wait(2000);
		verifyAndSelectDropDownBox(carePlanProperties.getProperty("Careplan.BusinessCusttomerTitle"), "Title", "Mr");
		 verifyAndInputByXpath(carePlanProperties.getProperty("Careplan.BusinessCusttomerFirstname"),"First Name", userProfile.getFirstName().trim());
		 verifyAndInputByXpath(carePlanProperties.getProperty("Careplan.BusinessCusttomerSurname"),"Sur Name", userProfile.getLastName().trim());
		 verifyAndInputByXpath(carePlanProperties.getProperty("Careplan.BusinessCusttomerBusinessname"),"Business Name", userProfile.getbusinessname().trim());
		 verifyAndInputByXpath(carePlanProperties.getProperty("Careplan.BusinessCusttomerEmailAddress"),"Email Address", userProfile.getEmail().trim());
		 verifyAndInputByXpath(carePlanProperties.getProperty("Careplan.BusinessCusttomerTelephone"),"Telephone", userProfile.getMobileNumber().trim());
		 verifyAndClickWithXpath(carePlanProperties.getProperty("Careplan.BusinessCusttomerContinue"), "Continue");
		 Report.updateTestLog("Contact Details are filled with vaild value", "WARN");
		 //yesIHaveGasSupply(userProfile);
		 yesIHaveGasSupplyNew(userProfile);
		 bespokeErrorpricingNew(carePlanProfile);

			}
	
	public void bespokeErrorpricing(CarePlanProfile carePlanProfile) {
		// Selecting Appliance category //
		selectingApplianceCategory(carePlanProfile);
		// Selecting Appliance type and Appliance size //
		browser.selectfromDropBoxByXpath(carePlanProperties.getProperty("Careplan.BusinessCustomerHAWAppliances") ,carePlanProfile.getAppliance());
		if(browser.isElementVisibleWithXpath(carePlanProperties.getProperty("Careplan.BusinessCustomerHAWApplianceSize"))){
		browser.selectfromDropBoxByXpath(carePlanProperties.getProperty("Careplan.BusinessCustomerHAWApplianceSize") ,carePlanProfile.getApplianceSize());
		}
		verifyAndClickWithXpath(carePlanProperties.getProperty("Careplan.BusinessCustomerAppliancesContinue"), "Continue");
		// Selecting Care plan Options //
		carepPlanbespkeErrorcheck(carePlanProfile);
	
		}
	public void bespokeErrorpricingNew(CarePlanProfile carePlanProfile) {
		// Selecting Appliance category //
		//selectingApplianceCategory(carePlanProfile);
		selectingApplianceCategoryNew(carePlanProfile);
		// Selecting Appliance type and Appliance size //
		if(browser.isElementVisibleWithXpath(carePlanProperties.getProperty("Careplan.BusinessCustomerHAWApplianceSize"))){
		browser.selectfromDropBoxByXpath(carePlanProperties.getProperty("Careplan.BusinessCustomerHAWApplianceSize") ,carePlanProfile.getApplianceSize());
		}
		verifyAndClickWithXpath(carePlanProperties.getProperty("Careplan.BusinessCustomerAppliancesContinue"), "Continue");
		browser.wait(getWaitTime());
		// Selecting Care plan Options //
		carepPlanbespkeErrorcheck(carePlanProfile);
	
		}
public void carepPlanbespkeErrorcheck(CarePlanProfile carePlanProfile) {
	browser.wait(getWaitTime());
		if (carePlanProfile.getCareplanOptions().contains("Careplan 1 product - service only")){
			if (browser.isElementVisibleWithXpath(carePlanProperties.getProperty("Careplan.bespokecareplan1"))){
				Report.updateTestLog("**********************"+"Bespoke Error message displayed"+"***************", "WARN");		
				}
			else{
				Report.updateTestLog("**********************"+"Bespoke Error message is not displayed"+"***************", "FAIL");	
			}
		}
		else if  (carePlanProfile.getCareplanOptions().contains("Careplan 1 - service only")){
			if (browser.isElementVisibleWithXpath(carePlanProperties.getProperty("Careplan.bespokecareplan2"))){
				Report.updateTestLog("**********************"+"Bespoke Error message displayed"+"***************", "WARN");		
				}
			else{
				Report.updateTestLog("**********************"+"Bespoke Error message is not displayed"+"***************", "FAIL");	
			}
				
		}
		else if (carePlanProfile.getCareplanOptions().contains("Careplan 2 - service and breakdown incl labour")){
			if (browser.isElementVisibleWithXpath(carePlanProperties.getProperty("Careplan.bespokecareplan3"))){
				Report.updateTestLog("**********************"+"Bespoke Error message displayed"+"***************", "WARN");		
				}
			else{
				Report.updateTestLog("**********************"+"Bespoke Error message is not displayed"+"***************", "FAIL");	
			}
	    }
		else if (carePlanProfile.getCareplanOptions().contains("Careplan 2s - service and breakdown incl labour and system cover")){
			if (browser.isElementVisibleWithXpath(carePlanProperties.getProperty("Careplan.bespokecareplan2s"))){
				Report.updateTestLog("**********************"+"Bespoke Error message displayed"+"***************", "WARN");		
				}
			else{
				Report.updateTestLog("**********************"+"Bespoke Error message is not displayed"+"***************", "FAIL");	
			}	
	    }
		else if (carePlanProfile.getCareplanOptions().contains("Careplan 3 - service and breakdown incl labour amp parts")){
			if (browser.isElementVisibleWithXpath(carePlanProperties.getProperty("Careplan.bespokecareplan3"))){
				Report.updateTestLog("**********************"+"Bespoke Error message displayed"+"***************", "WARN");		
				}
			else{
				Report.updateTestLog("**********************"+"Bespoke Error message is not displayed"+"***************", "FAIL");	
			}		
	
	    }
		else if(carePlanProfile.getCareplanOptions().contains("Careplan 3s - service and breakdown incl labour amp parts and system cover")){
			if (browser.isElementVisibleWithXpath(carePlanProperties.getProperty("Careplan.bespokecareplan3s"))){
				Report.updateTestLog("**********************"+"Bespoke Error message displayed"+"***************", "WARN");		
				}
			else{
				Report.updateTestLog("**********************"+"Bespoke Error message is not displayed"+"***************", "FAIL");	
			}
	    }

		
	}

//////////////////////////////////////////////////////////////////////////////////Continue To Order//////////////////////////////////////////////////////////////////////////////	
public void continueToOrder(){
	verifyAndClickWithXpath(carePlanProperties.getProperty("CarePlan.CareplanContuinueToOrder"), "Continue To Order");

}

public void continueToOrderNew(){
	
	browser.wait(2000);
	System.out.println("FWEFDWEFWEFWEFWEFWE");
	Report.updateTestLog("Prcing Verification is completed", "WARN");
	verifyAndClickWithXpath(carePlanProperties.getProperty("CarePlan.CareplanPleaseConfirm"),"British Gas Agreement Check box");
	verifyAndClickWithXpath(carePlanProperties.getProperty("CarePlan.CareplanTermsAndCondition"),"select Terms and Condition Check box");
	browser.wait(2000);
	verifyAndClickWithXpath(carePlanProperties.getProperty("Careplan.PlaceOrder"), "Order Now");

}
//////////////////////////////////////////////////////////////////////////////////your Order//////////////////////////////////////////////////////////////////////////////		
public void yourOrder(){
	if (browser.isElementVisibleWithXpath(carePlanProperties.getProperty("CarePlan.CareplanAddressSection"))){
		Report.updateTestLog("Address Section  Displayed in the Your Details Page", "WARN");
	}
	else {
		Report.updateTestLog("Address Section  not Displayed in the Your Details Page", "FAIL");
	}
	
	verifyAndClickWithXpath(carePlanProperties.getProperty("CarePlan.CareplanPleaseConfirm"), "Please Confirm");
	verifyAndClickWithXpath(carePlanProperties.getProperty("CarePlan.CareplanTermsAndCondition"), "Terms and Condition");
	verifyAndClickWithXpath(carePlanProperties.getProperty("CarePlan.CareplanMarketingCheck"), "Marketing Check ");
	verifyAndClickWithXpath(carePlanProperties.getProperty("CarePlan.CareplanPlaceYourorder"), "place your Order");


}

//////////////////////////////////////////////////////////////////////////////////Confirmation page//////////////////////////////////////////////////////////////////////////////			





public void ConfirmationPage(UserProfile userProfile){
	continueToOrder();
	yourOrder();
	
	browser.wait(10000);
	String referenceNumber="";
	if (browser.isElementVisibleWithXpath(carePlanProperties.getProperty("CarePlan.CareplanConfirmation"))){
		Report.updateTestLog("Confirmation Page is Displayed", "WARN");
	}
	else {
		Report.updateTestLog("Confirmation page is not Displayed", "FAIL");
	}
	
	referenceNumber=browser.getTextByXpath(carePlanProperties.getProperty("CarePlan.CareplanReferencenumber"));
	Report.updateTestLog("******************"+referenceNumber+"******************", "WARN");
	System.out.println("reference number="+referenceNumber);
	String[] parts = referenceNumber.split(": ");
	
	refrence=parts[1];
	System.out.println("answer"+refrence);
	
	browser.wait(2000);
	customerDetailsAuditVerification(userProfile);
	siteDetailsAuditVerification();
	marketingConsentAuditVerification();
	

}
public void ConfirmationPageNew(UserProfile userProfile){
	continueToOrderNew();
	//yourOrder();
	
	browser.wait(10000);
	String referenceNumber="";
	if (browser.isElementVisibleWithXpath(carePlanProperties.getProperty("CarePlan.CareplanConfirmation"))){
		Report.updateTestLog("Confirmation Page is Displayed", "WARN");
	}
	else {
		Report.updateTestLog("Confirmation page is not Displayed", "FAIL");
	}
	
	referenceNumber=browser.getTextByXpath(carePlanProperties.getProperty("CarePlan.CareplanReferencenumber"));
	Report.updateTestLog("******************"+referenceNumber+"******************", "WARN");
	System.out.println("reference number="+referenceNumber);
	String[] parts = referenceNumber.split(": ");
	
	refrence=parts[1];
	System.out.println("answer"+refrence);
	
	browser.wait(2000);
	customerDetailsAuditVerification(userProfile);
	siteDetailsAuditVerification();
	marketingConsentAuditVerification();
	

}

public void addAnotherSite() {
	browser.wait(2000) ;
	verifyAndClickWithXpath(carePlanProperties.getProperty("CarePlan.CareplanAddAnotherSite"), "Add Another Site");
	
	System.out.println("im in add another site");
	browser.wait(2000) ;
}
//////////////////////////////////////////////////////////////database verification////////////////////////////////////////////////////////////////////////////////////////
public void customerDetailsAuditVerification(UserProfile userProfile) {
	System.out.println("sssssssssssssssssssssssss");
	
	 OnlineDBConnector dbFunctions =new OnlineDBConnector();
	 String Date=dbFunctions.DBsysdateDdmonyyhhmi();
	 System.out.println(" "+Date  );
	 
	 String Result=dbFunctions.verifyAuditEntryForCareplanCustomerdetails(Date,strEmail);
	 if (refrence.equals(Result)){
		 Report.updateTestLog("Customer ID entry made sucessful in BGB_TA_CAREPLAN_CUST_DETAILS table ", "PASS");
		 
	 }
	 else {
		 Report.updateTestLog("Audit Entry Was Not Expected", "FAIL");
	 }
	
}

public void siteDetailsAuditVerification() {
	
	 OnlineDBConnector dbFunctions =new OnlineDBConnector();
	 String Date=dbFunctions.DBsysdateDdmonyyhhmi();
	 System.out.println(" "+Date  );
	 
	 String Result=dbFunctions.verifyAuditEntryForCareplanSiteAddress(refrence);
	 if (!Result.equals(null)){
		 Report.updateTestLog("Customer ID entry made sucessful in BGB_TA_CAREPLAN_SITE_INVOICE table ", "PASS");
		 
	 }
	 else {
		 Report.updateTestLog("Audit Entry Was Not Expected", "FAIL");
	 }
	 siteid=Result;
	 System.out.println("fetch="+siteid);
	 
	 String[] ProductID = dbFunctions.verifyProductID(siteid);
	 
	 System.out.println("hhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhh" + ProductID);
	
}
public void marketingConsentAuditVerification() {
	
    OnlineDBConnector dbFunctions =new OnlineDBConnector();
    String Date=dbFunctions.DBsysdateDdmonyyhhmi();
    //System.out.println(" "+Date  );
    String Count="21";
    String Result=dbFunctions.verifyAuditEntryForCareplanMultipleCount(siteid);
    if (Count.equals(Result)){
          Report.updateTestLog("Multiple entry made sucessful in BGB_TA_CAREPLAN_SITE_PRODUCTS table ", "PASS");
          
     }
    else {
          Report.updateTestLog("Audit Entry Was Not Expected", "FAIL");
    }
    

	
}


	
}









