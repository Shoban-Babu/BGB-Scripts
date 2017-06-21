/**
 * 
 */
package bg.framework.app.functional.action.tetris;

import bg.framework.app.functional.entities.UserProfile;
import bg.framework.app.functional.page.tetris.CopyBillRequestPage;
import bg.framework.app.functional.page.tetris.EnergyAdvisorToolPage;
import bg.framework.app.functional.page.tetris.LargeBusinessElectricityCallBackPage;
import bg.framework.app.functional.page.tetris.StatementOfAccountPage;

/**
 * @author 292238
 *
 */
public class EnergyAdvisorToolAction {
	public EnergyAdvisorToolAction openEnergyAdvisorToolPage(String strJourney){
		EnergyAdvisorToolPage energyAdvisorPage = new EnergyAdvisorToolPage();
		energyAdvisorPage.openEnergyAdvisorToolPage(strJourney);
		return this;	
	}
	public EnergyAdvisorToolAction verifyAndEntervalues(UserProfile userProfile){
		EnergyAdvisorToolPage energyAdvisorPage = new EnergyAdvisorToolPage();
		energyAdvisorPage.verifyAndEnterBackGroundPage(userProfile);
		energyAdvisorPage.heatingAndAirConditioningPage();
		energyAdvisorPage.selectHeatingControl();
		energyAdvisorPage.verifySeprateWaterHeatingSystem();
		energyAdvisorPage.verifyLightingType();
		energyAdvisorPage.verifyBuildingControl();
		energyAdvisorPage.verifyRenewableAndCorbonEnergy();
		energyAdvisorPage.verifyEnergyAdvisor();		
		return this;	
	}
	public EnergyAdvisorToolAction verifyResultAndRetrivalPage(UserProfile userProfile){
		EnergyAdvisorToolPage energyAdvisorPage = new EnergyAdvisorToolPage();
		final String refNumber = energyAdvisorPage.verifyThankYouPage();	
		energyAdvisorPage.verifyRetrivalPage(userProfile,refNumber);
		energyAdvisorPage.verifyThankYouPage();	
		return this;	
	}
	public EnergyAdvisorToolAction verifyDBDetails(UserProfile userProfile){
		EnergyAdvisorToolPage energyAdvisorPage = new EnergyAdvisorToolPage();
		energyAdvisorPage.verifyAuditTable(userProfile);
		return this;	
	}
	public EnergyAdvisorToolAction validateFieldsInEAT(UserProfile userProfile){
		EnergyAdvisorToolPage energyAdvisorPage = new EnergyAdvisorToolPage();
		energyAdvisorPage.verifyFieldValidation(1);
		energyAdvisorPage.verifyAndEnterBackGroundPage(userProfile);
		energyAdvisorPage.verifyFieldValidation(2);
		energyAdvisorPage.heatingAndAirConditioningPage();
		energyAdvisorPage.selectHeatingControl();
		energyAdvisorPage.verifySeprateWaterHeatingSystem();
		energyAdvisorPage.verifyFieldValidation(3);
		energyAdvisorPage.verifyLightingType();
		energyAdvisorPage.verifyFieldValidation(4);
		energyAdvisorPage.verifyBuildingControl();
		energyAdvisorPage.verifyFieldValidation(5);
		energyAdvisorPage.verifyRenewableAndCorbonEnergy();
		energyAdvisorPage.verifyFieldValidation(6);
		energyAdvisorPage.verifyEnergyAdvisor();		
		return this;	
	}
}
