/**
 * 
 */
package bg.framework.app.functional.action.tetris;

import bg.framework.app.functional.entities.UserProfile;
import bg.framework.app.functional.page.tetris.LargeBusinessElectricityCallBackPage;
import bg.framework.app.functional.page.tetris.LargeBusinessGasCallBackPage;

/**
 * @author 292238
 *
 */
public class LargeBusinessGasCallBackAction {
	public LargeBusinessGasCallBackAction openLargeBusinesspage(UserProfile userProfile){
		LargeBusinessGasCallBackPage largeGasBusinessPage = new LargeBusinessGasCallBackPage();
		largeGasBusinessPage.openLargeBusinessPage();
		return this;	
	}
	public LargeBusinessGasCallBackAction verifyAndEntervalues(UserProfile userProfile){
		LargeBusinessGasCallBackPage largeGasBusinessPage = new LargeBusinessGasCallBackPage();
		largeGasBusinessPage.enterValuesInLargeBusinessPage(userProfile);
		return this;	
	}
	public LargeBusinessGasCallBackAction verifyThankYouPage(UserProfile userProfile){
		LargeBusinessGasCallBackPage largeGasBusinessPage = new LargeBusinessGasCallBackPage();
		largeGasBusinessPage.verifyThankYouPage();
		return this;	
	}
	public LargeBusinessGasCallBackAction verifyDBDetails(UserProfile userProfile){
		LargeBusinessGasCallBackPage largeGasBusinessPage = new LargeBusinessGasCallBackPage();
		largeGasBusinessPage.verifyDBDetails(userProfile);
		return this;	
	}
}
