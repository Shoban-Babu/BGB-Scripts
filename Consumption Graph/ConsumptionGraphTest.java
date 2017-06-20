/**
 * 
 */
package bg.framework.app.functional.test.Slingshot;

import static bg.framework.app.functional.entities.FunctionalCategory.Tetris;

import org.testng.annotations.Test;

import bg.framework.app.functional.action.common.LoginAction;
import bg.framework.app.functional.entities.UserProfile;
import bg.framework.app.functional.test.common.TestBase;
import bg.framework.app.functional.util.Report;
import bg.framework.app.functional.util.TestDataHelper;

/**
 * @author 292238
 *
 */
public class ConsumptionGraphTest extends TestBase{
	
	//TC_CG_001:Verify whether registered customer is able to perform consumption graph journey from Account summary page.	
	//TC_CG_004:Verify whether consumption landing page is displayed when navigating from Account summary	
	//TC_CG_008:Verify whether monthly consumption data is displayed in table format, when view option is selected as table.
	@Test(groups={Tetris})
	public void verifyConsumptionGraphJourney(){
		Report.createTestLogHeader("ConsumptionGraphTest", "Verifies whether consumption data is displayed in table format");
		UserProfile userProfile = new TestDataHelper().getUserProfile("BGBLessthanFiveAccounts");
		new LoginAction()
		.bgbLoginUser()
		.bgbloginDetails(userProfile)
		.clickAndVerifyManageAccountLink(userProfile)
		.clickEnergyConsumptionLink(userProfile)
		.verifyEnergyConsumptionPage(userProfile)
		.clickContinueButton()
		.veifyEnergyConsumptionPage()
		.verifyGraph()
		.verifyTableView()
		.clickAndVerifyComparisionYear()
		.validateSelectAnotherMeter();
		//.verifyConsumptionLinks();
		//.verifyConsumptionPodsLink();			
	}
	//TC_CG_013:Verify whether customer can request consumption graph to be displayed by comparing previous years consumption by selecting 'Compare with last year' checkbox for more than 3 meters.
	//TC_CG_040:Verify the LHN and link navigations in consumption landing page for customers having  more than 3 meters for electricity customers.
	//TC_CG_042:Verify the LHN and link navigations in consumption landing page for customers having  more than 3 meters for gas customers.
	@Test(groups={Tetris})
	public void verifyConsumptionDataMorethan3Meter(){
		Report.createTestLogHeader("ConsumptionGraphTest", "Verifies consumption data and link navigations in consumption landing page for customers having  more than 3 meters");
		UserProfile userProfile = new TestDataHelper().getUserProfile("Morethan3Gas");
		new LoginAction().bgbLoginUser()
		.bgbloginDetails(userProfile)
		.clickAndVerifyManageAccountLink(userProfile)
		.clickEnergyConsumptionLink(userProfile)
		.verifyEnergyConsumptionPage(userProfile)		
		.verifyConsumptionLinks_Morethan3Meter();
					
	}
	//TC_CG_011,12,13:Verify whether customer can request consumption graph to be displayed by comparing previous years consumption.
	//TC_CG_035: Verify the link navigations in the breadcrumb in consumption graph landing page.
	//TC_CG_040: Verify the LHN and link navigations in consumption landing page for customers having  more than 3 meters for electricity customers.
	//TC_CG_042: Verify the LHN and link navigations in consumption landing page for customers having  more than 3 meters for gas customers.
	@Test(groups={Tetris})
	public void verifyCompareOption(){
		Report.createTestLogHeader("ConsumptionGraphTest", "Verifies Consumption Graph page compare option");
		UserProfile userProfile = new TestDataHelper().getUserProfile("BGBLessthanFiveAccounts");
		new LoginAction().bgbLoginUser()
		.bgbloginDetails(userProfile)
		.clickAndVerifyManageAccountLink(userProfile)
		.clickEnergyConsumptionLink(userProfile)
		.verifyEnergyConsumptionPage(userProfile)
		.clickContinueButton()
		.veifyEnergyConsumptionPage()
		.verifyTableView()
		.clickAndVerifyComparisionYear()
		.validateSelectAnotherMeter()
		//.verifyConsumptionLinks()
		//.clickContinueButton()
		.verifyConsumptionPodsLink();		
			}
	/*
	@Test(groups={Tetris})
	public void verifyConsumptionOnDateRange(){
		Report.createTestLogHeader("ConsumptionGraphTest", "Verifies Consumption Graph page date range selection");
		UserProfile userProfile = new TestDataHelper().getUserProfile("BGBLessthanFiveAccounts");
		new LoginAction().bgbLoginUser()
		.bgbloginDetails(userProfile)
		.clickAndVerifyManageAccountLink(userProfile)
		.clickEnergyConsumptionLink()
		.verifyEnergyConsumptionPage(userProfile)
		.clickContinueButton()
		.veifyEnergyConsumptionPage()
		.selectDateAndValidateGraph();	
		
	}*/
	//TC_CG_096:Verify the link navigations in the breadcrumb in consumption graph page.
	//TC_CG_097:Verify the "Back to your account" button functionality in the consumption graph page.
	//TC_CG_098:Verify the "Select another meter" button functionality in the consumption graph page.
	//TC_CG_099:Verify the link navigations in the pods in the consumption graph page.
	//TC_CG_100:Verify the functionality of "Show use" button in the consumption graph page.
	@Test(groups={Tetris})
	public void verifyLinkNavigations(){
		Report.createTestLogHeader("ConsumptionGraphTest", "Verifies the link navigations in the pods in the consumption graph page");
		UserProfile userProfile = new TestDataHelper().getUserProfile("BGBLessthanFiveAccounts");
		new LoginAction().bgbLoginUser()
		.bgbloginDetails(userProfile)
		.clickAndVerifyManageAccountLink(userProfile)
		.clickEnergyConsumptionLink(userProfile)
		.verifyEnergyConsumptionPage(userProfile)
		.clickContinueButton()
		.veifyEnergyConsumptionPage()
		//.verifyConsumptionLinks()
		//.clickContinueButton()
		.verifyConsumptionPodsLink();	
		
	}
}
