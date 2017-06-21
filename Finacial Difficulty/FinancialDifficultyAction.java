/**
 * 
 */
package bg.framework.app.functional.action.tetris;

import bg.framework.app.functional.entities.UserProfile;
import bg.framework.app.functional.page.tetris.CopyBillRequestPage;
import bg.framework.app.functional.page.tetris.FinancialDifficultyPage;
import bg.framework.app.functional.page.tetris.LargeBusinessElectricityCallBackPage;
import bg.framework.app.functional.page.tetris.StatementOfAccountPage;

/**
 * @author 292238
 *
 */
public class FinancialDifficultyAction {
	public FinancialDifficultyAction openFinancialDifficultyPage(UserProfile userProfile){
		FinancialDifficultyPage financeDifficultypage = new FinancialDifficultyPage();
		financeDifficultypage.openFinanacialDifficultyPage();
		return this;	
	}
	public FinancialDifficultyAction verifyAndEntervalues(UserProfile userProfile){
		FinancialDifficultyPage financeDifficultypage = new FinancialDifficultyPage();
		financeDifficultypage.entervalidData(userProfile);
		financeDifficultypage.findAddress(userProfile);
		//financeDifficultypage.clickSubmitButton();
		return this;	
	}
	public FinancialDifficultyAction verifyAddAddressManually(UserProfile userProfile){
		FinancialDifficultyPage financeDifficultypage = new FinancialDifficultyPage();
		financeDifficultypage.entervalidData(userProfile);
		financeDifficultypage.addAddressManually(userProfile);
		financeDifficultypage.clickSubmitButton();
		return this;	
	}
	public FinancialDifficultyAction verifyThankYouPage(UserProfile userProfile){
		FinancialDifficultyPage financeDifficultypage = new FinancialDifficultyPage();
		financeDifficultypage.verifyThankYouPage();
		return this;	
	}
	public FinancialDifficultyAction verifyDBDetails(UserProfile userProfile){
		FinancialDifficultyPage financeDifficultypage = new FinancialDifficultyPage();
		financeDifficultypage.verifyLeadTable(userProfile);
		return this;	
	}
	public FinancialDifficultyAction validateFieldsInFinancialDifficultyPage(UserProfile userProfile){
		FinancialDifficultyPage financeDifficultypage = new FinancialDifficultyPage();
		financeDifficultypage.validateFieldsInFinancialDifficultyPage(userProfile);
		return this;	
	}
}
