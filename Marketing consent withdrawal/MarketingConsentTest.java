/**
 * 
 */
package bg.framework.app.functional.test.tetris;

import static bg.framework.app.functional.entities.FunctionalCategory.Slingshot;
import static bg.framework.app.functional.entities.FunctionalCategory.Tetris;

import org.testng.annotations.Test;

import bg.framework.app.functional.action.tetris.CorporateCallbackAction;
import bg.framework.app.functional.action.tetris.MarketingConsentAction;
import bg.framework.app.functional.entities.UserProfile;
import bg.framework.app.functional.test.common.TestBase;
import bg.framework.app.functional.util.Report;
import bg.framework.app.functional.util.TestDataHelper;

/**
 * @author 292238
 *
 */
public class MarketingConsentTest extends TestBase{
	@Test(groups={Tetris})	
	public void verifyMarketingConsent() throws Exception{
		Report.createTestLogHeader("Marketing consent withdrawal","Verifies functionality of Marketing consent withdrawal  Miscellaneous page");		
		UserProfile userProfile= new TestDataHelper().getUserProfile("tetris-contactUs");
		new MarketingConsentAction().openMarketingConsentpage(userProfile)
		.verifyAndEntervalues(userProfile)
		.verifyThankYouPage(userProfile)
		.verifyDBDetails(userProfile);
	}
	@Test(groups = {Slingshot})
	public void validateCallBackOverlayFields() {
		Report.createTestLogHeader("Marketing consent withdrawal", "Validates all the fields from Marketing consent withdrawal page");
		UserProfile userProfile = new TestDataHelper().getUserProfile("tetris-contactUs");
		new MarketingConsentAction().openMarketingConsentpage(userProfile)
		.validateFields(userProfile);
	
}
}
