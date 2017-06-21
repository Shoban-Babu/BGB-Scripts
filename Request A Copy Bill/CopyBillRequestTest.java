/**
 * 
 */
package bg.framework.app.functional.test.tetris;

import static bg.framework.app.functional.entities.FunctionalCategory.Slingshot;
import static bg.framework.app.functional.entities.FunctionalCategory.Tetris;

import org.testng.annotations.Test;

import bg.framework.app.functional.action.tetris.CopyBillRequestAction;
import bg.framework.app.functional.action.tetris.CorporateTrainingContactUsAction;
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
public class CopyBillRequestTest extends TestBase{
	@Test(groups={Tetris})	
	public void verifyLargeBusinessElectricityCallBack() throws Exception{
		Report.createTestLogHeader("Copy Bill Request","Verifies functionality of Copy Bill Request page");		
		UserProfile userProfile= new TestDataHelper().getUserProfile("tetris-contactUs");
		new CopyBillRequestAction().openCopyBillrequestPage(userProfile)
		.verifyAndEntervalues(userProfile)
		.verifyThankYouPage(userProfile)
		.verifyDBDetails(userProfile);
	}
	@Test(groups={Tetris})	
	public void verifyAddAnotherAccount() throws Exception{
		Report.createTestLogHeader("Copy Bill Request","Verifies add another functionality of Copy Bill Request page");		
		UserProfile userProfile= new TestDataHelper().getUserProfile("tetris-contactUs");
		new CopyBillRequestAction().openCopyBillrequestPage(userProfile)
		.verifyAddAnotherAccount(userProfile)
		.verifyAndEntervalues(userProfile)
		.verifyThankYouPage(userProfile)
		.verifyDBDetails(userProfile);
	}
	@Test(groups = {Slingshot})
	public void validateFieldsInCopyBillPage() {
		Report.createTestLogHeader("Copy Bill Request","Verifies the field validation in Copy Bill Request page");
		UserProfile userProfile = new TestDataHelper().getUserProfile("tetris-contactUs");
		new CopyBillRequestAction().openCopyBillrequestPage(userProfile)
		.validateFields(userProfile);
	
}

}
