/**
 * 
 */
package bg.framework.app.functional.action.Slingshot;

import java.text.ParseException;

import bg.framework.app.functional.entities.SMRAccountDetails;
import bg.framework.app.functional.entities.UserProfile;
import bg.framework.app.functional.page.Slingshot.AccountOverviewPage;
import bg.framework.app.functional.page.Slingshot.StatementOfAccountPage;
import bg.framework.app.functional.page.Slingshot.ViewBillPage;
import bg.framework.app.functional.page.common.LegacyHomePage;
import bg.framework.app.functional.page.common.LegacyLoginPage;

/**
 * @author 255501
 *
 */
public class StatementOfAccountAction 
{
		
	public StatementOfAccountAction BgbnavigateToLogin() {
		LegacyHomePage legacyHomePage = new LegacyHomePage();
		legacyHomePage.BgbnavigateToLoginPage(); 
		return this;
	}
	
	public StatementOfAccountAction BgbloginDetails(UserProfile userProfile) {
		LegacyLoginPage legacyLoginPage = new LegacyLoginPage();
		System.out.println(userProfile.getEmail());
		legacyLoginPage.BgbLoginEmail(userProfile.getEmail());

		legacyLoginPage.BgbLoginPassword(userProfile.getPassword());
		legacyLoginPage.BgbClickSubmit();
		return this;
	}
	
	public StatementOfAccountAction VerifyBillingLinkAction(UserProfile userProfile) {
		StatementOfAccountPage AcctPage = new StatementOfAccountPage();
		AcctPage.verifyManageAccountLink(userProfile);
		AcctPage.clickBillingLink();
		return this;
	}
	public StatementOfAccountAction verifyManageAccountLink(UserProfile userProfile) {
		StatementOfAccountPage AcctPage = new StatementOfAccountPage();
		AcctPage.verifyManageAccountLink(userProfile);	
		return this;
	}
	public StatementOfAccountAction VerifyManageAccount(UserProfile userProfile) {
		StatementOfAccountPage AcctPage = new StatementOfAccountPage();
		AcctPage.selectSmrForAccount1(userProfile);
		return this;
	}
	public StatementOfAccountAction VerifyBillingLinkAction1() {
		StatementOfAccountPage AcctPage = new StatementOfAccountPage();
		AcctPage.clickBillingLink();
		return this;
	}
	public StatementOfAccountAction VerifyStatementOfAccountLinkAction(UserProfile userProfile)
	{
		StatementOfAccountPage AcctPage = new StatementOfAccountPage();
		AcctPage.clickViewStatementOfAccountLink(userProfile);
		return this;
	}
	public StatementOfAccountAction FromDateToDateValidation(UserProfile userProfile)
	{
		StatementOfAccountPage AcctPage = new StatementOfAccountPage();
		AcctPage.FromDateToDateValidation(userProfile);
		return this;
	}
	public StatementOfAccountAction VerifyLHNStatementOfAccountLinkAction()
	{
		StatementOfAccountPage AcctPage = new StatementOfAccountPage();
		AcctPage.clickLHNViewStatementOfAccountLink();
		return this;
	}
	
	public StatementOfAccountAction verifyBillingNavigatinoLinks() {
		StatementOfAccountPage AcctPage = new StatementOfAccountPage();
		AcctPage.verifyBillingNavigatinoLinks();
		return this;
	}
	
	public StatementOfAccountAction verifyBreadCrumbLinks() {
		StatementOfAccountPage AcctPage = new StatementOfAccountPage();
		AcctPage.verifyBreadCrumbLinks();
		return this;
	}
	
	public StatementOfAccountAction ClickFromAndToDateInDatePicker() {
		StatementOfAccountPage AcctPage = new StatementOfAccountPage();
		AcctPage.enterFromDate(2);
		AcctPage.enterToDate(0);
		return this;
	}
	public StatementOfAccountAction FromDateToDateMonthsValidation() {
		StatementOfAccountPage AcctPage = new StatementOfAccountPage();
		AcctPage.MonthDifferenceCalculator();
		
		return this;
	}
	public StatementOfAccountAction FromDateToDateErrorValidation(UserProfile userProfile) {
		StatementOfAccountPage AcctPage = new StatementOfAccountPage();
		AcctPage.FromDateToDateErrorValidation(userProfile);
		return this;
	}
	public StatementOfAccountAction ClickviewButton() {
		StatementOfAccountPage AcctPage = new StatementOfAccountPage();
		AcctPage.verifyclickViewButton();
		return this;
	}
	public StatementOfAccountAction ClickDownLoadPDFLink() throws ParseException {
		StatementOfAccountPage AcctPage = new StatementOfAccountPage();
		AcctPage.downloadPDF();
	//	AcctPage.verifyPDFFile();
		return this;
	}
	
	public StatementOfAccountAction verifyBillTable(){
		StatementOfAccountPage AcctPage = new StatementOfAccountPage();
		AcctPage.verifyBillTableColumns();
		//AcctPage.verifyBillTable();
		AcctPage.verifyBillTableValues();
			return this;
	}	
	public StatementOfAccountAction SOABackToYourAccount(UserProfile userProfile){
		StatementOfAccountPage AcctPage = new StatementOfAccountPage();
		AcctPage.SOABackToYourAccount(userProfile);
	
			return this;
	}
	public StatementOfAccountAction BreadCrumbLinks(){
		StatementOfAccountPage AcctPage = new StatementOfAccountPage();
		AcctPage.verifyBreadCrumbLinks();
	
			return this;
	}
	
	public StatementOfAccountAction verifyLeadTable(UserProfile userProfile){
		StatementOfAccountPage AcctPage = new StatementOfAccountPage();
		AcctPage.verifyLeadTable(userProfile);
				return this;
	}
	public AccountOverviewPage verifyCustomerAccountDetails(UserProfile userProfile){
		AccountOverviewPage accOverviewpage =   new AccountOverviewPage();
		accOverviewpage. verifyPagination();
		accOverviewpage.verifyAccountDetails(userProfile);
		return new AccountOverviewPage(); 
	}
	/*public StatementOfAccountAction downloadAndVerifyBill(SMRAccountDetails smrProfile){
		ViewBillPage viewbill=new ViewBillPage();
	//	viewbill.clickViewBillLink();
	//	viewbill.selectTerm("Account number");
	//	viewbill.enterSearchCriteria(smrProfile.getAccountNumber());
		viewbill.enterFromDate("3");
		viewbill.enterToDate("0");
	//	viewbill.clickSearchBillSearchButton();
	//	viewbill.verifyBillTable();
	//	viewbill.selectCheckboxDownload();
	//	viewbill.clickSearchBillDownload();
		try {
			viewbill.openAndVerifyZipFile();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return this;
*/

	public StatementOfAccountAction verifySOAPagination()throws ParseException{
		StatementOfAccountPage AcctPage = new StatementOfAccountPage();
		AcctPage.clickAndVerifyPaginationSOA();
		return this; 
	}
	public StatementOfAccountAction verifyPaginationNumbersLink()throws ParseException{
		StatementOfAccountPage AcctPage = new StatementOfAccountPage();
		//AcctPage.PaginationNumbersLink();
		AcctPage.clickAndVerifyPaginationSOA();
		return this; 
	}
	public StatementOfAccountAction verifySOADataThroughQTP(UserProfile userProfile)throws ParseException{
		StatementOfAccountPage AcctPage = new StatementOfAccountPage();
		//AcctPage.PaginationNumbersLink();
		AcctPage.verifyDataThroughQTP(userProfile);
		return this; 
	}		
	
}
