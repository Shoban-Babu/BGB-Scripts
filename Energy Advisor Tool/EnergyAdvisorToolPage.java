/**
 * 
 */
package bg.framework.app.functional.page.tetris;

import java.util.ArrayList;
import java.util.Properties;

import bg.framework.app.functional.common.ApplicationConfig;
import bg.framework.app.functional.entities.UserProfile;
import bg.framework.app.functional.page.common.BasePage;
import bg.framework.app.functional.page.common.TetrisErrorMessages;
import bg.framework.app.functional.util.OnlineDBConnector;
import bg.framework.app.functional.util.PropertyLoader;
import bg.framework.app.functional.util.Report;

/**
 * @author 292238
 *
 */
public class EnergyAdvisorToolPage extends BasePage{
	private final static String FILE_NAME = "resources/tetris/EnergyAdvisorToolPage.properties";
	private static Properties pageProperties = new PropertyLoader(FILE_NAME).load();

	//This methods opens the energy advisor tool page
	public void openEnergyAdvisorToolPage(String strJourney){
		browser.open(ApplicationConfig.APP_BG_URL+strJourney+pageProperties.getProperty("EnergyAdvisorTool.URL"));
		browser.wait(getWaitTime());
	}

	public void verifyAndEnterBackGroundPage(UserProfile userProfile){
		verifyAndInputById(pageProperties.getProperty("EnergyAdvisorTool.companyname"), "Company name", userProfile.getCompanyName());
		verifyAndInputById(pageProperties.getProperty("EnergyAdvisorTool.email"), "Email", userProfile.getEmail());
		ArrayList<String> businessArea = browser.getFromDropBox("id", pageProperties.getProperty("EnergyAdvisorTool.businessArea"));
		//for(int i =1; i<businessArea.size(); i++){
		//verifyAndSelectDropDownBox(pageProperties.getProperty("EnergyAdvisorTool.businessArea"), "Business area", businessArea.get((businessArea.size())));
		verifyAndSelectDropDownBox(pageProperties.getProperty("EnergyAdvisorTool.businessArea"), "Business area", "Restaurants");

		if(browser.isElementVisibleWithXpath(pageProperties.getProperty("EnergyAdvisorTool.BusinessAreaOthers"))){
			verifyAndInputByXpath(pageProperties.getProperty("EnergyAdvisorTool.BusinessAreaOthers"), "Business area others", "Manufacturers");
		}
		//}
		System.out.println("BUSINESS AREA CONTENT : "+businessArea);
		//verifyAndSelectDropDownBox(pageProperties.getProperty(""), "Business area", "");
		ArrayList<String> FloorArea = browser.getFromDropBox("id", pageProperties.getProperty("EnergyAdvisorTool.floorArea"));		
		verifyAndSelectDropDownBox(pageProperties.getProperty("EnergyAdvisorTool.floorArea"), "Floor area", FloorArea.get(2));
		if(browser.isElementVisibleWithXpath(pageProperties.getProperty("EnergyAdvisorTool.FuelField"))){
			int fuelCount = browser.getChildElementsCountByXpath(pageProperties.getProperty("EnergyAdvisorTool.FuelCount"));			
			verifyAndSelectCheckBoxByXpath(pageProperties.getProperty("EnergyAdvisorTool.FuelSelect"+1), "Fuel");
			verifyAndInputByXpath(pageProperties.getProperty("EnergyAdvisorTool.elecSpend"), "Annual consumption", "1000");
			browser.selectfromDropBoxByXpath(pageProperties.getProperty("EnergyAdvisorTool.SpendUnit"), "kWh");		
		}
		verifyAndClickWithXpath(pageProperties.getProperty("EnergyAdvisorTool.backgroundcontinue"), "Continue");
	}

	public void heatingAndAirConditioningPage(){
		if(browser.isElementVisibleWithXpath(pageProperties.getProperty("EnergyAdvisorTool.HeatingMethodTitle"))){
			verifyAndSelectCheckBoxByID(pageProperties.getProperty("EnergyAdvisorTool.isCentralHeating"), "Central heating");
			verifyAndSelectCheckBoxByID(pageProperties.getProperty("EnergyAdvisorTool.isStorageHeaters"), "Storage heaters");
			verifyAndSelectCheckBoxByID(pageProperties.getProperty("EnergyAdvisorTool.isWarmAir"), "Warm air system");
			verifyAndSelectCheckBoxByID(pageProperties.getProperty("EnergyAdvisorTool.isSuspendedHeaters"), "Suspended heaters");
			verifyAndSelectCheckBoxByID(pageProperties.getProperty("EnergyAdvisorTool.isNoHeating"), "No heating");			
		}
		if(browser.isElementVisibleWithXpath(pageProperties.getProperty("EnergyAdvisorTool.FuelType"))){
			String[] fuelType = {"Electricity","Gas","Gas","Oil","Lpg","Coal","Wood","Renewable","Other"};
			for(int i=0; i < fuelType.length; i++){
				verifyAndSelectCheckBoxByID(pageProperties.getProperty("EnergyAdvisorTool.HeatingSystem")+fuelType[i],fuelType[i]);				
			}
			ArrayList<String> heatingSystemAge = browser.getFromDropBox("id", pageProperties.getProperty("EnergyAdvisorTool.HeatingSystemAge"));
			verifyAndSelectDropDownBox(pageProperties.getProperty("EnergyAdvisorTool.HeatingSystemAge"), "Heating system age", heatingSystemAge.get(2));

		}
	}
	public void selectHeatingControl(){
		String[] heatingControl = {"TimeClock","Thermostat","Radiator","BMS","None","DontKnow"};
		for(int i=0; i < heatingControl.length; i++){
			verifyAndSelectCheckBoxByID(pageProperties.getProperty("EnergyAdvisorTool.HeatingSystem")+heatingControl[i],heatingControl[i]);				
		}	
	}
	public void verifySeprateWaterHeatingSystem(){		
		ArrayList<String> heatingSystem = browser.getFromDropBox("id", pageProperties.getProperty("EnergyAdvisorTool.SeprateWaterHeatingSystem"));
		verifyAndSelectDropDownBox(pageProperties.getProperty("EnergyAdvisorTool.SeprateWaterHeatingSystem"), "Seprate Heating system", heatingSystem.get(1));
		//if(browser.isElementVisibleWithXpath(pageProperties.getProperty("EnergyAdvisorTool.FuelType"))){
		String[] fuelType = {"Electricity","Gas","Gas","Oil","LPG","Coal","Wood","Renewable","Other"};
		for(int i=0; i < fuelType.length; i++){
			verifyAndSelectCheckBoxByID(pageProperties.getProperty("EnergyAdvisorTool.SeprateHeatingSystem")+fuelType[i],fuelType[i]);				
		}
		//}
		selectWaterHeatingSystem();
		selectHotWaterControl();
		selectLastVerified();
		selectAirConditioningOption();
	}

	public void selectWaterHeatingSystem(){		
		ArrayList<String> heatingSystem = browser.getFromDropBox("id", pageProperties.getProperty("EnergyAdvisorTool.WaterHeatingSystemAge"));
		verifyAndSelectDropDownBox(pageProperties.getProperty("EnergyAdvisorTool.WaterHeatingSystemAge"), "Water Heating system age", heatingSystem.get(1));
	}
	public void selectHotWaterControl(){		
		ArrayList<String> hotWaterControl = browser.getFromDropBox("id", pageProperties.getProperty("EnergyAdvisorTool.HotWaterControlAvailablity"));
		verifyAndSelectDropDownBox(pageProperties.getProperty("EnergyAdvisorTool.HotWaterControlAvailablity"), "Hot Water Control", hotWaterControl.get(1));
	}
	public void selectLastVerified(){		
		ArrayList<String> lastVerified = browser.getFromDropBox("id", pageProperties.getProperty("EnergyAdvisorTool.LastBoilerServiceTime"));
		verifyAndSelectDropDownBox(pageProperties.getProperty("EnergyAdvisorTool.LastBoilerServiceTime"), "Last Boiler Service Time", lastVerified.get(1));
	}
	public void selectAirConditioningOption(){		
		ArrayList<String> airConditionAvailability = browser.getFromDropBox("id", pageProperties.getProperty("EnergyAdvisorTool.AirConditionAvailablity"));
		verifyAndSelectDropDownBox(pageProperties.getProperty("EnergyAdvisorTool.AirConditionAvailablity"), "Air Condition Availablity", airConditionAvailability.get(1));
		if(browser.isElementVisibleWithXpath(pageProperties.getProperty("EnergyAdvisorTool.InstallationPeriod"))){
			ArrayList<String> installationPeriod = browser.getFromDropBox("id", pageProperties.getProperty("EnergyAdvisorTool.InstallationPeriodId"));
			verifyAndSelectDropDownBox(pageProperties.getProperty("EnergyAdvisorTool.InstallationPeriodId"), "Installation Period", installationPeriod.get(1));
		}
		verifyAndClickWithXpath(pageProperties.getProperty("EnergyAdvisorTool.Airconditioningcontinue"), "Airconditioningcontinue");
	}

	public void verifyLightingType(){
		if(browser.isElementVisibleWithXpath(pageProperties.getProperty("EnergyAdvisorTool.LightingType"))){
			String[] lightingType = {"fluorescent","lowEnergy","highBay","outsideLighting","highFrequency","tungsten","dontKnow"};
			for(int i=0; i < lightingType.length; i++){
				verifyAndSelectCheckBoxByID(lightingType[i],lightingType[i]);				
			}	
			ArrayList<String> lightingRefurbishment = browser.getFromDropBox("id", pageProperties.getProperty("EnergyAdvisorTool.LastLightingRefurbishment"));
			verifyAndSelectDropDownBox(pageProperties.getProperty("EnergyAdvisorTool.LastLightingRefurbishment"), "Lighting Refurbishment", lightingRefurbishment.get(1));
			ArrayList<String> lightLeftOn = browser.getFromDropBox("id", pageProperties.getProperty("EnergyAdvisorTool.lightLeftOn"));
			verifyAndSelectDropDownBox(pageProperties.getProperty("EnergyAdvisorTool.lightLeftOn"), "Light LeftOn", lightLeftOn.get(1));
			ArrayList<String> lightLeftOnInDaylight = browser.getFromDropBox("id", pageProperties.getProperty("EnergyAdvisorTool.lightLeftOnInDaylight"));
			verifyAndSelectDropDownBox(pageProperties.getProperty("EnergyAdvisorTool.lightLeftOnInDaylight"), "Light LeftOn In Day light", lightLeftOnInDaylight.get(1));
			ArrayList<String> inspectionPeriod = browser.getFromDropBox("id", pageProperties.getProperty("EnergyAdvisorTool.inspectionPeriod"));
			verifyAndSelectDropDownBox(pageProperties.getProperty("EnergyAdvisorTool.inspectionPeriod"), "Inspection Period", inspectionPeriod.get(1));
			ArrayList<String> portableAppliancesPresence = browser.getFromDropBox("id", pageProperties.getProperty("EnergyAdvisorTool.portableAppliancesPresence"));
			verifyAndSelectDropDownBox(pageProperties.getProperty("EnergyAdvisorTool.portableAppliancesPresence"), "Portable Appliances Presence", portableAppliancesPresence.get(1));
			verifyAndClickWithXpath(pageProperties.getProperty("EnergyAdvisorTool.Lightingcontinue"), "Lighting continue");
			browser.wait(getWaitTime());
		}
	}
	public void verifyBuildingControl(){
		verifyPageTitle(pageProperties.getProperty("EnergyAdvisorTool.BuildingControlTitle"));
		ArrayList<String> bmsAvailablity = browser.getFromDropBox("id", pageProperties.getProperty("EnergyAdvisorTool.bmsAvailablity"));
		verifyAndSelectDropDownBox(pageProperties.getProperty("EnergyAdvisorTool.bmsAvailablity"), "Bms Availablity", bmsAvailablity.get(1));
		ArrayList<String> installationAge = browser.getFromDropBox("id", pageProperties.getProperty("EnergyAdvisorTool.installationAge"));
		verifyAndSelectDropDownBox(pageProperties.getProperty("EnergyAdvisorTool.installationAge"), "installation Age", installationAge.get(1));
		ArrayList<String> fullyOptimised = browser.getFromDropBox("id", pageProperties.getProperty("EnergyAdvisorTool.fullyOptimised"));
		verifyAndSelectDropDownBox(pageProperties.getProperty("EnergyAdvisorTool.fullyOptimised"), "Fully Optimised", fullyOptimised.get(1));
		ArrayList<String> regularlyMaintained = browser.getFromDropBox("id", pageProperties.getProperty("EnergyAdvisorTool.regularlyMaintained"));
		verifyAndSelectDropDownBox(pageProperties.getProperty("EnergyAdvisorTool.regularlyMaintained"), "Regularly Maintained", regularlyMaintained.get(1));
		verifyAndClickWithXpath(pageProperties.getProperty("EnergyAdvisorTool.buildingcontinue"), "Building continue");
		browser.wait(getWaitTime());
	}

	public void verifyRenewableAndCorbonEnergy(){
		verifyPageTitle(pageProperties.getProperty("EnergyAdvisorTool.RenewableAndCorbonEnergyTitle"));
		ArrayList<String> renewableEnergyInterest = browser.getFromDropBox("id", pageProperties.getProperty("EnergyAdvisorTool.renewableEnergyInterest"));
		verifyAndSelectDropDownBox(pageProperties.getProperty("EnergyAdvisorTool.renewableEnergyInterest"), "Renewable Energy Interest", renewableEnergyInterest.get(1));
		ArrayList<String> roofSpaceAvailablity = browser.getFromDropBox("id", pageProperties.getProperty("EnergyAdvisorTool.roofSpaceAvailablity"));
		verifyAndSelectDropDownBox(pageProperties.getProperty("EnergyAdvisorTool.roofSpaceAvailablity"), "Roof Space Availablity", roofSpaceAvailablity.get(1));
		verifyAndClickWithXpath(pageProperties.getProperty("EnergyAdvisorTool.renewablecontinue"), "Renewable continue");
		browser.wait(getWaitTime());
	}
	public void verifyEnergyAdvisor(){
		verifyPageTitle(pageProperties.getProperty("EnergyAdvisorTool.EnergyAdvisorTitle"));
		ArrayList<String> buildingBuiltPeriod = browser.getFromDropBox("id", pageProperties.getProperty("EnergyAdvisorTool.buildingBuiltPeriod"));
		verifyAndSelectDropDownBox(pageProperties.getProperty("EnergyAdvisorTool.buildingBuiltPeriod"), "Building Built Period", buildingBuiltPeriod.get(1));
		ArrayList<String> cavityWallFitted = browser.getFromDropBox("id", pageProperties.getProperty("EnergyAdvisorTool.cavityWallFitted"));
		verifyAndSelectDropDownBox(pageProperties.getProperty("EnergyAdvisorTool.cavityWallFitted"), "Cavity Wall Fitted", cavityWallFitted.get(1));
		ArrayList<String> storageTanksInsulated = browser.getFromDropBox("id", pageProperties.getProperty("EnergyAdvisorTool.storageTanksInsulated"));
		verifyAndSelectDropDownBox(pageProperties.getProperty("EnergyAdvisorTool.storageTanksInsulated"), "Storage Tanks Insulated", storageTanksInsulated.get(1));		
		verifyAndClickWithXpath(pageProperties.getProperty("EnergyAdvisorTool.insulationcontinue"), "Insulation continue");
		browser.wait(getWaitTime());
	}
	public void verifyFieldValidation(int i){
		switch(i){		
		case 1:
			verifyAndClickWithXpath(pageProperties.getProperty("EnergyAdvisorTool.backgroundcontinue"), "Continue");
			String backgroundErrormsg[] = TetrisErrorMessages.Tetris_BackgroundErrorMsg;
			errorMessageComparison(backgroundErrormsg);
			break;
		case 2:
			verifyAndClickWithXpath(pageProperties.getProperty("EnergyAdvisorTool.Airconditioningcontinue"), "Airconditioningcontinue");
			String heatingErrormsg[] = TetrisErrorMessages.Tetris_HeatingSectionErrorMsg;
			errorMessageComparison(heatingErrormsg);
			break;
		case 3:
			verifyAndClickWithXpath(pageProperties.getProperty("EnergyAdvisorTool.Lightingcontinue"), "Lighting continue");
			String lightingErrormsg[] = TetrisErrorMessages.Tetris_LightingSectionErrorMsg;
			errorMessageComparison(lightingErrormsg);
			break;
		case 4:
			verifyAndClickWithXpath(pageProperties.getProperty("EnergyAdvisorTool.buildingcontinue"), "Building continue");
			String buildingControlErrormsg[] = TetrisErrorMessages.Tetris_BuildingControlErrorMsg;
			errorMessageComparison(buildingControlErrormsg);
			break;
		case 5:
			verifyAndClickWithXpath(pageProperties.getProperty("EnergyAdvisorTool.renewablecontinue"), "Renewable continue");
			String renewableErrormsg[] = TetrisErrorMessages.Tetris_RenewableErrorMsg;
			errorMessageComparison(renewableErrormsg);
			break;
		case 6:
			verifyAndClickWithXpath(pageProperties.getProperty("EnergyAdvisorTool.insulationcontinue"), "Insulation continue");
			String insulationErrormsg[] = TetrisErrorMessages.Tetris_InsulationErrorMsg;
			errorMessageComparison(insulationErrormsg);			
			break;
		}
	}
	//Capture the error message displayed and verify the actual and error message displayed by making a call to the verifyErrorMessage method
	public void errorMessageComparison(final String expectedErrorMsg[]) {
		try{
			for(int i=0;i<expectedErrorMsg.length; i++){
				if(browser.isElementVisibleWithXpath(pageProperties.getProperty("EnergyAdvisorTool.ErrorMsg"))){
					final String displayedErrorMsg = browser.getTextByXpath(pageProperties.getProperty("EnergyAdvisorTool.ErrorMsg"));
					verifyErrorMessage(displayedErrorMsg, expectedErrorMsg[i]);
				}
				else{
					Report.updateTestLog("Error message is not displayed for this scenario", "FAIL");
				}
			}
		}
		catch(Exception e){
		}
	}
	//Validation for Inappropriate data for the fields displayed in Forgot Email Compare the error message displayed in the application against the Expected data  	 
	public void verifyErrorMessage(final String displayedErrorMsg, final String expectedErrorMsg) {

		if (displayedErrorMsg.trim().contains(expectedErrorMsg.trim())) {
			Report.updateTestLog("Error message validation. Expected message:" +expectedErrorMsg, "PASS");
		} else {
			Report.updateTestLog("Error message validation. Expected message:" +expectedErrorMsg, "FAIL");
		}
	}

	public void verifyEditFunctionality(){
		verifyAndClickWithXpath(pageProperties.getProperty("EnergyAdvisorTool.EditLink"), "Edit link");		
		if (browser.isElementVisibleWithXpath(pageProperties.getProperty("EnergyAdvisorTool.BackgroundLink"))){
			Report.updateTestLog("Edit link working as expected", "PASS");
		}
		else{
			Report.updateTestLog("Edit link working as expected", "FAIL");
		}
	}
	public String verifyThankYouPage(){
		verifyIsTextPresent(pageProperties.getProperty("EnergyAdvisorTool.Resultpage"));
		String refNumber = browser.getTextByXpath(pageProperties.getProperty("EnergyAdvisorTool.ReferenceNumber"));
		Report.updateTestLog("Your reference number is "+refNumber, "DONE");
		return refNumber;
	}
	public void verifyRetrivalPage(UserProfile userProfile, String refNumber){
		verifyAndClickWithXpath(pageProperties.getProperty("EnergyAdvisorTool.EnergyAdvisorLink"), "Energy advisor link");
		verifyAndInputByXpath(pageProperties.getProperty("EnergyAdvisorTool.Email"), "Email address", userProfile.getEmail());
		verifyAndInputByXpath(pageProperties.getProperty("EnergyAdvisorTool.RefNumber"), "Reference number", refNumber);
		verifyAndClickWithXpath(pageProperties.getProperty("EnergyAdvisorTool.SubmitButton"), "Submit");
		
	}
	public void verifyAuditTable(UserProfile userProfile){	
		OnlineDBConnector dbFunctions = new OnlineDBConnector();
		String date=dbFunctions.DBsysdateDdmmyyhhmi();
		//String[] auditType = dbFunctions.getAuditEventTypeId(date, userProfile.getEmail());
		//String data = dbFunctions.getAuditType(auditType[0]);	
	//	Report.updateTestLog("Audit id is made in audit table as expected. Audit id: "+auditType[0]+ " Audit event type is"+data, data.equalsIgnoreCase("BGBUSINESS_ENERGY_ADVICECUSTOMER_MAIL_SENT_SUCCESS")?"PASS":"FAIL");
	
	}
}
