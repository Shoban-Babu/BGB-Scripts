/**
 * 
 */
package bg.framework.app.functional.test.tetris;

import static bg.framework.app.functional.entities.FunctionalCategory.Tetris;

import org.testng.annotations.Test;

import bg.framework.app.functional.action.tetris.LargeBusinessElectricityCallBackAction;
import bg.framework.app.functional.action.tetris.MarketingConsentAction;
import bg.framework.app.functional.entities.UserProfile;
import bg.framework.app.functional.test.common.TestBase;
import bg.framework.app.functional.util.Report;
import bg.framework.app.functional.util.TestDataHelper;

/**
 * @author 292238
 *
 */
public class LargeBusinessElectricityCallBackTest extends TestBase{
	@Test(groups={Tetris})	
	public void verifyLargeBusinessElectricityCallBack() throws Exception{
		Report.createTestLogHeader("Large Business Electricity CallBack","Verifies functionality of Large Business Electricity CallBack page");		
		UserProfile userProfile= new TestDataHelper().getUserProfile("tetris-contactUs");
		new LargeBusinessElectricityCallBackAction().openLargeBusinesspage(userProfile)
		.verifyAndEntervalues(userProfile)
		.verifyThankYouPage(userProfile)
		.verifyDBDetails(userProfile);
	}

}
