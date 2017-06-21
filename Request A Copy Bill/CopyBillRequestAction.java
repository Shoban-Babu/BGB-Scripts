/**
 * 
 */
package bg.framework.app.functional.action.tetris;

import bg.framework.app.functional.entities.UserProfile;
import bg.framework.app.functional.page.tetris.CopyBillRequestPage;
import bg.framework.app.functional.page.tetris.CorporateTrainingContactUs;
import bg.framework.app.functional.page.tetris.LargeBusinessElectricityCallBackPage;

/**
 * @author 292238
 *
 */
public class CopyBillRequestAction {
	public CopyBillRequestAction openCopyBillrequestPage(UserProfile userProfile){
		CopyBillRequestPage copyBillRequestPage = new CopyBillRequestPage();
		copyBillRequestPage.openCopyBillrequestPage();
		return this;	
	}
	public CopyBillRequestAction verifyAndEntervalues(UserProfile userProfile){
		CopyBillRequestPage copyBillRequestPage = new CopyBillRequestPage();
		copyBillRequestPage.enterValuesInCopyBillRequestPage(userProfile);
		copyBillRequestPage.clickSubmit();
		return this;	
	}
	public CopyBillRequestAction verifyThankYouPage(UserProfile userProfile){
		CopyBillRequestPage copyBillRequestPage = new CopyBillRequestPage();
		copyBillRequestPage.verifyThankYouPage();
		return this;	
	}
	public CopyBillRequestAction verifyDBDetails(UserProfile userProfile){
		CopyBillRequestPage copyBillRequestPage = new CopyBillRequestPage();
		copyBillRequestPage.verifyDBDetails(userProfile);
		return this;	
	}
	public CopyBillRequestAction verifyAddAnotherAccount(UserProfile userProfile){
		CopyBillRequestPage copyBillRequestPage = new CopyBillRequestPage();
		copyBillRequestPage.enterValuesInCopyBillRequestPage(userProfile);
		copyBillRequestPage.verifyAddAnotherAccount();
		copyBillRequestPage.verifyRequestedBill();
		copyBillRequestPage.enterValuesInCopyBillRequestPage(userProfile);
		copyBillRequestPage.verifyAddAnotherAccount();
		copyBillRequestPage.verifyRequestedBill();
				return this;	
	}
	public CopyBillRequestAction validateFields(UserProfile userProfile){
		CopyBillRequestPage copyBillRequestPage = new CopyBillRequestPage();
		copyBillRequestPage.validateFieldsInTrainingContactUs(userProfile);
				return this;
	}
}
