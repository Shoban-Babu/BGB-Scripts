/**
 * 
 */
package bg.framework.app.functional.action.Slingshot;

import bg.framework.app.functional.entities.UserProfile;
import bg.framework.app.functional.page.Slingshot.ConsumptionGraphPage;

/**
 * @author 292238
 *
 */
public class ConsumptionGraphAction {

	public ConsumptionGraphAction verifyEnergyConsumptionPage(UserProfile userProfile){
		ConsumptionGraphPage energyConsumptionPage = new ConsumptionGraphPage();
		energyConsumptionPage.verifyEnergyConsumptionPage(userProfile);
		return new ConsumptionGraphAction();
	}
	public ConsumptionGraphAction clickContinueButton(){
		ConsumptionGraphPage energyConsumptionPage = new ConsumptionGraphPage();
		energyConsumptionPage.clickContinueButton();
		return this;
	}
	public ConsumptionGraphAction veifyEnergyConsumptionPage(){
		ConsumptionGraphPage energyConsumptionPage = new ConsumptionGraphPage();
		energyConsumptionPage.veifyEnergyConsumptionPage();
		return this;
	}
	public ConsumptionGraphAction verifyGraph(){
		ConsumptionGraphPage energyConsumptionPage = new ConsumptionGraphPage();
		energyConsumptionPage.verifyGraph();
		return this;
	}
	public ConsumptionGraphAction verifyTableView(){
		ConsumptionGraphPage energyConsumptionPage = new ConsumptionGraphPage();
		energyConsumptionPage.verifyTableView();
		return this;
	}
	public ConsumptionGraphAction clickAndVerifyComparisionYear(){
		ConsumptionGraphPage energyConsumptionPage = new ConsumptionGraphPage();
		energyConsumptionPage.clickAndVerifyComparisionYear();
		return this;
	}
	public ConsumptionGraphAction selectDateAndValidateGraph(){
		ConsumptionGraphPage energyConsumptionPage = new ConsumptionGraphPage();
		energyConsumptionPage.selectDate();
		return this;
	}
	public ConsumptionGraphAction validateSelectAnotherMeter(){
		ConsumptionGraphPage energyConsumptionPage = new ConsumptionGraphPage();
		energyConsumptionPage.validateSelectAnotherMeter();
		return this;
	}
	public ConsumptionGraphAction verifyConsumptionLinks(){
		ConsumptionGraphPage energyConsumptionPage = new ConsumptionGraphPage();
		energyConsumptionPage.verifyConsumptionLinks();
		return this;
	}
	public ConsumptionGraphAction verifyConsumptionPodsLink(){
		ConsumptionGraphPage energyConsumptionPage = new ConsumptionGraphPage();
		energyConsumptionPage.verifyConsumptionPodsLink();
		return this;
	}
	public ConsumptionGraphAction verifyConsumptionLinks_Morethan3Meter(){
		ConsumptionGraphPage energyConsumptionPage = new ConsumptionGraphPage();
		energyConsumptionPage.verifyConsumptionLinks_Morethan3Meter();
		return this;
	}
	
	
}
