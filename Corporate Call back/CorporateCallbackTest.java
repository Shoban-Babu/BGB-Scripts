/**
 * 
 */
package bg.framework.app.functional.test.tetris;

import static bg.framework.app.functional.entities.FunctionalCategory.AccountSummary;
import static bg.framework.app.functional.entities.FunctionalCategory.Regression;
import static bg.framework.app.functional.entities.FunctionalCategory.Slingshot;

import org.testng.annotations.Test;

import bg.framework.app.functional.action.common.LoginAction;
import bg.framework.app.functional.action.tetris.CorporateCallbackAction;
import bg.framework.app.functional.entities.UserProfile;
import bg.framework.app.functional.test.common.TestBase;
import bg.framework.app.functional.util.Report;
import bg.framework.app.functional.util.TestDataHelper;

/**
 * @author 292238
 *
 */
public class CorporateCallbackTest extends TestBase{
	@Test(groups = {Slingshot})
	public void verifyFixedPricePlan() {
		Report.createTestLogHeader("Corporate Call back journey", "Verifies the corporate call back journey for Fixed Price Plan");
		UserProfile userProfile = new TestDataHelper().getUserProfile("tetris-contactUs");
		final String strPage ="Fixed Price Plan";
		new CorporateCallbackAction()
		.verifyAndClick(strPage)
		.verifyCallBackAction(userProfile,strPage);
		
}
	@Test(groups = {Slingshot})
	public void verifyFlex247() {
		Report.createTestLogHeader("Corporate Call back journey", "Verifies the corporate call back journey for Flex 247");
		UserProfile userProfile = new TestDataHelper().getUserProfile("tetris-contactUs");
		final String strPage ="Flex 24/7";
		new CorporateCallbackAction()
		.verifyAndClick(strPage)
		.verifyCallBackAction(userProfile,strPage);
		
}
	@Test(groups = {Slingshot})
	public void verifyFullFlexibility() {
		Report.createTestLogHeader("Corporate Call back journey", "Verifies the corporate call back journey for Full Flexibility");
		UserProfile userProfile = new TestDataHelper().getUserProfile("tetris-contactUs");
		final String strPage ="Full Flexibility";
		new CorporateCallbackAction()
		.verifyAndClick(strPage)
		.verifyCallBackAction(userProfile,strPage)
		.verifyDB(userProfile, strPage);
}
	@Test(groups = {Slingshot})
	public void verifyMonthlyBlock() {
		Report.createTestLogHeader("Corporate Call back journey", "Verifies the corporate call back journey for Monthly Block");
		UserProfile userProfile = new TestDataHelper().getUserProfile("tetris-contactUs");
		final String strPage ="Monthly Block";
		new CorporateCallbackAction()
		.verifyAndClick(strPage)
		.verifyCallBackAction(userProfile,strPage);
	//	.verifyDB(userProfile, strPage);
}
	@Test(groups = {Slingshot})
	public void verifyRollingContract() {
		Report.createTestLogHeader("Corporate Call back journey", "Verifies the corporate call back journey for Rolling Contract");
		UserProfile userProfile = new TestDataHelper().getUserProfile("tetris-contactUs");
		final String strPage ="Rolling Contract";
		new CorporateCallbackAction()
		.verifyAndClick(strPage)
		.verifyCallBackAction(userProfile,strPage);
		//.verifyDB(userProfile, strPage);
}
	@Test(groups = {Slingshot})
	public void verifyHalfHourlyCashOut() {
		Report.createTestLogHeader("Corporate Call back journey", "Verifies the corporate call back journey for Half Hourly Cash Out");
		UserProfile userProfile = new TestDataHelper().getUserProfile("tetris-contactUs");
		final String strPage ="Half Hourly Cash Out";
		new CorporateCallbackAction()
		.verifyAndClick(strPage)
		.verifyCallBackAction(userProfile,strPage);
		//.verifyDB(userProfile, strPage);
}
	@Test(groups = {Slingshot})
	public void verifyEnergyOptimisationDesk() {
		Report.createTestLogHeader("Corporate Call back journey", "Verifies the corporate call back journey for Energy Optimisation Desk");
		UserProfile userProfile = new TestDataHelper().getUserProfile("tetris-contactUs");
		final String strPage ="Energy Optimisation Desk";
		new CorporateCallbackAction()
		.verifyAndClick(strPage)
		.verifyCallBackAction(userProfile,strPage);
		//.verifyDB(userProfile, strPage);
}
	@Test(groups = {Slingshot})
	public void verifyRenewableenergy() {
		Report.createTestLogHeader("Corporate Call back journey", "Verifies the corporate call back journey for Renewable energy");
		UserProfile userProfile = new TestDataHelper().getUserProfile("tetris-contactUs");
		final String strPage ="Renewable energy?";
		new CorporateCallbackAction()
		.verifyAndClick(strPage)
		.verifyCallBackAction(userProfile,strPage);
		//.verifyDB(userProfile, strPage);
}
	@Test(groups = {Slingshot})
	public void verifyGasElec() {
		Report.createTestLogHeader("Corporate Call back journey", "Verifies the corporate call back journey for Gas & electricity");
		UserProfile userProfile = new TestDataHelper().getUserProfile("tetris-contactUs");
		final String strPage ="Gas & electricity";
		new CorporateCallbackAction()
		.verifyAndClickGasElec(strPage)
		.verifyCallBackAction(userProfile,strPage);
		//.verifyDB(userProfile, strPage);
}
/*	@Test(groups = {Slingshot})
	public void verifyConnectionsAndMetering() {
		Report.createTestLogHeader("Corporate Call back journey", "Verifies the corporate call back journey for Connections and metering");
		UserProfile userProfile = new TestDataHelper().getUserProfile("tetris-contactUs");
		final String strPage ="Connections and metering";
		new CorporateCallbackAction()
		.verifyAndClick(strPage)
		.verifyCallBackAction(userProfile,strPage)
		.verifyDB(userProfile, strPage);
}*/
	@Test(groups = {Slingshot})
	public void verifyFirstForService() {
		Report.createTestLogHeader("Corporate Call back journey", "Verifies the corporate call back journey for First for service");
		UserProfile userProfile = new TestDataHelper().getUserProfile("tetris-contactUs");
		final String strPage ="First for service";
		new CorporateCallbackAction()
		.verifyAndClick(strPage)
		.verifyCallBackAction(userProfile,strPage);
		//.verifyDB(userProfile, strPage);
}
	@Test(groups = {Slingshot})
	public void verifyBrokers() {
		Report.createTestLogHeader("Corporate Call back journey", "Verifies the corporate call back journey for Brokers");
		UserProfile userProfile = new TestDataHelper().getUserProfile("tetris-contactUs");
		final String strPage ="Brokers";
		new CorporateCallbackAction()
		.verifyAndClick(strPage)
		.verifyCallBackAction(userProfile,strPage)
		.verifyDB(userProfile, strPage);
}
	@Test(groups = {Slingshot})
	public void verifyBiomassSystems() {
		Report.createTestLogHeader("Corporate Call back journey", "Verifies the corporate call back journey for Biomass systems");
		UserProfile userProfile = new TestDataHelper().getUserProfile("tetris-contactUs");
		final String strPage ="Biomass systems";
		new CorporateCallbackAction()
		.verifyAndClick(strPage)
		.verifyCallBackAction(userProfile,strPage);
		//.verifyDB(userProfile, strPage);
}
	@Test(groups = {Slingshot})
	public void verifyMicrogeneration() {
		Report.createTestLogHeader("Corporate Call back journey", "Verifies the corporate call back journey for Microgeneration");
		UserProfile userProfile = new TestDataHelper().getUserProfile("tetris-contactUs");
		final String strPage ="Microgeneration";
		new CorporateCallbackAction()
		.verifyAndClick(strPage)
		.verifyCallBackAction(userProfile,strPage);
		//.verifyDB(userProfile, strPage);
}
	@Test(groups = {Slingshot})
	public void verifySwitchingtoBritishGas() {
		Report.createTestLogHeader("Corporate Call back journey", "Verifies the corporate call back journey for Switching to British Gas");
		UserProfile userProfile = new TestDataHelper().getUserProfile("tetris-contactUs");
		final String strPage ="Switching to British Gas";
		new CorporateCallbackAction()
		.verifyAndClick(strPage)
		.verifyCallBackAction(userProfile,strPage);
		//.verifyDB(userProfile, strPage);
}
	@Test(groups = {Slingshot})
	public void verifySmarterWorking_PublicSector() {
		Report.createTestLogHeader("Corporate Call back journey", "Verifies the corporate call back journey for smarter working Property management");
		UserProfile userProfile = new TestDataHelper().getUserProfile("tetris-contactUs");
		final String strPage ="Public sector";
		new CorporateCallbackAction()
		.verifyAndClickSmarterWorking_Insight(strPage)
		.verifyCallBackAction(userProfile,strPage);
	//	.verifyDB(userProfile, strPage);
}
	@Test(groups = {Slingshot})
	public void verifySmarterWorking_PropertyManagement() {
		Report.createTestLogHeader("Corporate Call back journey", "Verifies the corporate call back journey for smarter working Property management");
		UserProfile userProfile = new TestDataHelper().getUserProfile("tetris-contactUs");
		final String strPage ="Property management";
		new CorporateCallbackAction()
		.verifyAndClickSmarterWorking_Insight(strPage)
		.verifyCallBackAction(userProfile,strPage);
		//.verifyDB(userProfile, strPage);
}
	@Test(groups = {Slingshot})
	public void verifySmarterWorking_Retail() {
		Report.createTestLogHeader("Corporate Call back journey", "Verifies the corporate call back journey for smarter working Retail");
		UserProfile userProfile = new TestDataHelper().getUserProfile("tetris-contactUs");
		final String strPage ="Retail";
		new CorporateCallbackAction()
		.verifyAndClickSmarterWorking_Insight(strPage)
		.verifyCallBackAction(userProfile,strPage);
	//	.verifyDB(userProfile, strPage);
}
	@Test(groups = {Slingshot})
	public void verifySmarterWorking_Education() {
		Report.createTestLogHeader("Corporate Call back journey", "Verifies the corporate call back journey for smarter working Education");
		UserProfile userProfile = new TestDataHelper().getUserProfile("tetris-contactUs");
		final String strPage ="Education";
		new CorporateCallbackAction()
		.verifyAndClickSmarterWorking_Insight(strPage)
		.verifyCallBackAction(userProfile,strPage);
		//.verifyDB(userProfile, strPage);
}
	@Test(groups = {Slingshot})
	public void verifySmarterWorking_UtilitiesAndTelecoms() {
		Report.createTestLogHeader("Corporate Call back journey", "Verifies the corporate call back journey for smarter working Utilities and telecoms");
		UserProfile userProfile = new TestDataHelper().getUserProfile("tetris-contactUs");
		final String strPage ="Utilities and telecoms";
		new CorporateCallbackAction()
		.verifyAndClickSmarterWorking_Insight(strPage)
		.verifyCallBackAction(userProfile,strPage);
		//.verifyDB(userProfile, strPage);
}
	@Test(groups = {Slingshot})
	public void verifySmarterWorking_HotelsAndLeisure() {
		Report.createTestLogHeader("Corporate Call back journey", "Verifies the corporate call back journey for smarter working Hotels and leisure");
		UserProfile userProfile = new TestDataHelper().getUserProfile("tetris-contactUs");
		final String strPage ="Hotels and leisure";
		new CorporateCallbackAction()
		.verifyAndClickSmarterWorking_Insight(strPage)
		.verifyCallBackAction(userProfile,strPage);
		//.verifyDB(userProfile, strPage);
}
	@Test(groups = {Slingshot})
	public void verifySmarterWorking_PubsAndRestaurants() {
		Report.createTestLogHeader("Corporate Call back journey", "Verifies the corporate call back journey for smarter working Pubs and restaurants");
		UserProfile userProfile = new TestDataHelper().getUserProfile("tetris-contactUs");
		final String strPage ="Pubs and restaurants";
		new CorporateCallbackAction()
		.verifyAndClickSmarterWorking_Insight(strPage)
		.verifyCallBackAction(userProfile,strPage);
	//	.verifyDB(userProfile, strPage);
}
	@Test(groups = {Slingshot})
	public void verifySmarterWorking_Charities() {
		Report.createTestLogHeader("Corporate Call back journey", "Verifies the corporate call back journey for smarter working Charities");
		UserProfile userProfile = new TestDataHelper().getUserProfile("tetris-contactUs");
		final String strPage ="Charities";
		new CorporateCallbackAction()
		.verifyAndClickSmarterWorking_Insight(strPage)
		.verifyCallBackAction(userProfile,strPage);
		//.verifyDB(userProfile, strPage);
}
	@Test(groups = {Slingshot})
	public void verifySmarterWorking_AutomotiveAndManufacturing() {
		Report.createTestLogHeader("Corporate Call back journey", "Verifies the corporate call back journey for smarter working Automotive and manufacturing");
		UserProfile userProfile = new TestDataHelper().getUserProfile("tetris-contactUs");
		final String strPage ="Automotive and manufacturing";
		new CorporateCallbackAction()
		.verifyAndClickSmarterWorking_Insight(strPage)
		.verifyCallBackAction(userProfile,strPage);
		//.verifyDB(userProfile, strPage);
}
	@Test(groups = {Slingshot})
	public void verifySmarterWorking_SocialHousing() {
		Report.createTestLogHeader("Corporate Call back journey", "Verifies the corporate call back journey for smarter working Social housing");
		UserProfile userProfile = new TestDataHelper().getUserProfile("tetris-contactUs");
		final String strPage ="Social housing";
		new CorporateCallbackAction()
		.verifyAndClickSmarterWorking_Insight(strPage)
		.verifyCallBackAction(userProfile,strPage);
		//.verifyDB(userProfile, strPage);
}
	@Test(groups = {Slingshot})
	public void verifySmarterWorking_CaseStudies() {
		Report.createTestLogHeader("Corporate Call back journey", "Verifies the corporate call back journey for smarter working Case studies");
		UserProfile userProfile = new TestDataHelper().getUserProfile("tetris-contactUs");
		final String strPage ="Case studies";
		new CorporateCallbackAction()
		.verifyAndClickSmarterWorking_CaseStudies(strPage)
		.verifyCallBackAction(userProfile,strPage);
		//.verifyDB(userProfile, strPage);
}
	@Test(groups = {Slingshot})
	public void verifySmarterWorking_WaterUtilityCompany() {
		Report.createTestLogHeader("Corporate Call back journey", "Verifies the corporate call back journey for smarter working Water utility company");
		UserProfile userProfile = new TestDataHelper().getUserProfile("tetris-contactUs");
		final String strPage ="Water utility company";
		new CorporateCallbackAction()
		.verifyAndClickSmarterWorking_CaseStudies(strPage)
		.verifyCallBackAction(userProfile,strPage);
		//.verifyDB(userProfile, strPage);
}
	@Test(groups = {Slingshot})
	public void verifySmarterWorking_HeathrowAirport() {
		Report.createTestLogHeader("Corporate Call back journey", "Verifies the corporate call back journey for smarter working Heathrow Airport");
		UserProfile userProfile = new TestDataHelper().getUserProfile("tetris-contactUs");
		final String strPage ="Heathrow Airport";
		new CorporateCallbackAction()
		.verifyAndClickSmarterWorking_CaseStudies(strPage)
		.verifyCallBackAction(userProfile,strPage);
		//.verifyDB(userProfile, strPage);
}
	@Test(groups = {Slingshot})
	public void verifySmarterWorking_WraysburyPrimarySchool() {
		Report.createTestLogHeader("Corporate Call back journey", "Verifies the corporate call back journey for smarter working Wraysbury Primary School");
		UserProfile userProfile = new TestDataHelper().getUserProfile("tetris-contactUs");
		final String strPage ="Wraysbury Primary School";
		new CorporateCallbackAction()
		.verifyAndClickSmarterWorking_CaseStudies(strPage)
		.verifyCallBackAction(userProfile,strPage);
		//.verifyDB(userProfile, strPage);
}
	@Test(groups = {Slingshot})
	public void verifySmarterWorking_TorbayCountyCouncil() {
		Report.createTestLogHeader("Corporate Call back journey", "Verifies the corporate call back journey for smarter working Torbay County Council");
		UserProfile userProfile = new TestDataHelper().getUserProfile("tetris-contactUs");
		final String strPage ="Torbay County Council";
		new CorporateCallbackAction()
		.verifyAndClickSmarterWorking_CaseStudies(strPage)
		.verifyCallBackAction(userProfile,strPage);
		//.verifyDB(userProfile, strPage);
}
	@Test(groups = {Slingshot})
	public void validateCallBackOverlayFields() {
		Report.createTestLogHeader("Corporate Call back journey", "Validates all the fields from call back overlay");
		UserProfile userProfile = new TestDataHelper().getUserProfile("tetris-contactUs");
		final String strPage ="Gas & electricity";
		new CorporateCallbackAction()
		.verifyAndClickGasElec(strPage)
		.validateFirstnameFields(userProfile);	
}
	
}
