/**
 * 
 */
package bg.framework.app.functional.test.tetris;

import static bg.framework.app.functional.entities.FunctionalCategory.Slingshot;
import static bg.framework.app.functional.entities.FunctionalCategory.Tetris;

import org.testng.annotations.Test;

import bg.framework.app.functional.action.tetris.CopyBillRequestAction;
import bg.framework.app.functional.action.tetris.FinancialDifficultyAction;
import bg.framework.app.functional.action.tetris.LargeBusinessElectricityCallBackAction;
import bg.framework.app.functional.action.tetris.MarketingConsentAction;
import bg.framework.app.functional.action.tetris.StatementOfAccountRequestAction;
import bg.framework.app.functional.entities.UserProfile;
import bg.framework.app.functional.test.common.TestBase;
import bg.framework.app.functional.util.Report;
import bg.framework.app.functional.util.TestDataHelper;

/**
 * @author 292238
 *
 */
public class FinancialDifficultyTest extends TestBase{
	@Test(groups={Tetris})	
	public void verifyFinancialDifficultyJourney() throws Exception{
		Report.createTestLogHeader("Financial Difficulty Page","Verifies functionality of Financial Difficulty page");		
		UserProfile userProfile= new TestDataHelper().getUserProfile("tetris-contactUs");
		new FinancialDifficultyAction()
		.openFinancialDifficultyPage(userProfile)
		.verifyAndEntervalues(userProfile)
		.verifyThankYouPage(userProfile)
		.verifyDBDetails(userProfile);
	}
	@Test(groups={Tetris})	
	public void verifyFinancialDifficultyByManuallAddress() throws Exception{
		Report.createTestLogHeader("Financial Difficulty Page","Verifies functionality of Financial Difficulty page by adding address manually");		
		UserProfile userProfile= new TestDataHelper().getUserProfile("tetris-contactUs");
		new FinancialDifficultyAction().openFinancialDifficultyPage(userProfile)
		.verifyAddAddressManually(userProfile)
		.verifyThankYouPage(userProfile)
		.verifyDBDetails(userProfile);
	}
	@Test(groups = {Slingshot})
	public void validateFieldsInFinancialDifficulty() {
		Report.createTestLogHeader("Copy Bill Request","Verifies the field validation in Copy Bill Request page");
		UserProfile userProfile = new TestDataHelper().getUserProfile("tetris-contactUs");
		new FinancialDifficultyAction().openFinancialDifficultyPage(userProfile)
		.validateFieldsInFinancialDifficultyPage(userProfile);
		
	
}
}
