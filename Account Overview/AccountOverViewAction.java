package bg.framework.app.functional.action.Slingshot;

import bg.framework.app.functional.action.common.LoginAction;
import bg.framework.app.functional.entities.SMRAccountDetails;
import bg.framework.app.functional.entities.UserProfile;
import bg.framework.app.functional.page.common.LegacyLoginPage;
import bg.framework.app.functional.page.Slingshot.AccountOverviewPage;
import bg.framework.app.functional.page.Slingshot.AccountSummaryPage;
import bg.framework.app.functional.page.Slingshot.StatementOfAccountPage;


public class AccountOverViewAction {	

	public AccountSummaryAction verifyAccountOverviewActionForLessthanFiveAccount(UserProfile userProfile){
		AccountOverviewPage accountOverviewPage=new AccountOverviewPage();
		accountOverviewPage.verifyAccountOverviewPage();
		accountOverviewPage.getNumberAccountsDisplayed();
		accountOverviewPage.verifyPagination();
		accountOverviewPage.clickManageAccountLink(userProfile);
		return new AccountSummaryAction();
	}

	public AccountOverViewAction verifyAccountOverviewActionForMorethanFiveAccount(){
		AccountOverviewPage accountOverviewPage=new AccountOverviewPage();
		accountOverviewPage.verifyAccountOverviewPage();
		accountOverviewPage.verifyPaginationForMorethan5Customer();
		//accountOverviewPage.verifySearchFunctionalitySection();
		return new AccountOverViewAction();
	}

	public AccountOverViewAction verifyAccountOverviewActionForMorethanFiffteenAccount(UserProfile userProfile){
		AccountOverviewPage accountOverviewPage=new AccountOverviewPage();
		accountOverviewPage.verifyAccountOverviewPage();		
		accountOverviewPage.verifySearchFunctionalitySection();
		return new AccountOverViewAction();
	}
	public AccountSummaryAction selectContractAccountAndVerifySearchFunctionality(UserProfile userProfile){
		AccountOverviewPage accountOverviewPage=new AccountOverviewPage();	
		//accountOverviewPage .selectContractAccountAndVerifySearchFunctionality(userProfile);
		accountOverviewPage .clickManageAccountLink(userProfile);
		return new AccountSummaryAction();
	}
	
	public AccountSummaryAction selectContractAccountAndVerifySearchFunctionalitynew(SMRAccountDetails smrAccountDetails){
		AccountOverviewPage accountOverviewPage=new AccountOverviewPage();	
		//accountOverviewPage .selectContractAccountAndVerifySearchFunctionality(userProfile);
		accountOverviewPage .clickManageAccountLink1(smrAccountDetails);
		return new AccountSummaryAction();
	}
	
	
	public ManagePersonalDetailsAction clickMPDLink(UserProfile userProfile){
		new  AccountSummaryPage().clickMPDLink(userProfile);
		return new ManagePersonalDetailsAction();
	}

	public AccountSummaryAction verifyNumberOfCustomerDisplayed(){
		AccountOverviewPage accountOverviewPage=new AccountOverviewPage();
		accountOverviewPage.verifyAccountOverviewPage();
		accountOverviewPage.getNumberAccountsDisplayed();
		return new AccountSummaryAction();
	}
	public AccountSummaryAction clickAndVerifyManageAccountLink(UserProfile userProfile){
		AccountOverviewPage accountOverviewPage=new AccountOverviewPage();
		accountOverviewPage.clickAndVerifyManageAccountLink(userProfile);
		
		return new AccountSummaryAction();
	}
	public AccountOverViewAction verifySmrLink(){
		new AccountSummaryPage().smrLink();
		return this;		
	}
	public AccountOverViewAction verifyBillingAddress(UserProfile userProfile){
		new AccountSummaryPage().smrCollectiveBillingAddressComparsion(userProfile);
		return this;		
	}
	public AccountOverViewAction verifyManageAccLink(){
		new AccountSummaryPage().manageAccLink();
		return this;		
	}
	public AccountOverViewAction verifyBillingAddressInAccSummary(UserProfile userProfile){
		new AccountSummaryPage().smrCollectiveBillingAddressComparsionInAccSummary(userProfile);
		return this;		
	}
	public AccountOverViewAction verifyViewYourLastBillLink(){
		new AccountSummaryPage().viewBillLink();
		return this;		
	}
	public AccountOverViewAction verifyPayYourLastBillLink(){
		new AccountSummaryPage().payBillLink();
		return this;		
	}
	public AccountOverViewAction verifyEnergyConsumptionLink(){
		new AccountSummaryPage().viewConsumptionLink();
		return this;		
	}
	public AccountOverViewAction verifyStatementOfAccLink(){
		new AccountSummaryPage().viewStatementOfAccLink();
		return this;		
	}
	public AccountOverViewAction makeAPaymentLink(){
		new AccountSummaryPage().makeAPaymentLink();
		return this;		
	}
	public AccountOverViewAction bgbVerifyLogin(UserProfile userProfile) {
		new  AccountSummaryPage().AccountSummaryVerification(userProfile);
		return new AccountOverViewAction();
	}

	public AccountSummaryAction clickManageAccountLink(UserProfile userProfile){
		AccountOverviewPage accountOverviewPage=new AccountOverviewPage();
		accountOverviewPage.clickManageAccountLink(userProfile);
		return new AccountSummaryAction();
	}
	public AccountOverViewAction verifyAccOverview_StdUser(){
		AccountOverviewPage accountOverviewPage=new AccountOverviewPage();
		accountOverviewPage.verifyAccOverview_StdUser();
		return new AccountOverViewAction();
	}
	public AccountOverViewAction verifyQuickLinks_stdUser(){
		AccountOverviewPage accountOverviewPage=new AccountOverviewPage();
		accountOverviewPage.verifyQuickLinks_stdUser();
		return new AccountOverViewAction();
	}
	
	
	public AccountOverViewAction AccountOverViewSMRQuickNavigation(UserProfile userProfile){
		AccountOverviewPage accountOverviewPage=new AccountOverviewPage();
		accountOverviewPage.AccountOverViewSMRQuickNavigation(userProfile);
		return new AccountOverViewAction();
	}

	public AccountOverViewAction AccountOverViewQuickNavigationViewBill(UserProfile userProfile){
		AccountOverviewPage accountOverviewPage=new AccountOverviewPage();
		accountOverviewPage.AccountOverViewQuickNavigationViewBill(userProfile);
		return new AccountOverViewAction();
	}
	public AccountOverViewAction AccountOverViewQuickNavigationtoViewBill(UserProfile userProfile){
		AccountOverviewPage accountOverviewPage=new AccountOverviewPage();
		accountOverviewPage.AccountOverViewQuickNavigationtoViewBill(userProfile);
		return new AccountOverViewAction();
	}
	
	public AccountOverViewAction AccountOverViewQuickNavigationMAP(UserProfile userProfile){
		AccountOverviewPage accountOverviewPage=new AccountOverviewPage();
		accountOverviewPage.AccountOverViewQuickNavigationMAP(userProfile);
		return new AccountOverViewAction();
	}
	
	public AccountOverViewAction AccountOverViewQuickNavigationtoviewMAP(UserProfile userProfile){
		AccountOverviewPage accountOverviewPage=new AccountOverviewPage();
		accountOverviewPage.AccountOverViewQuickNavigationtoviewMAP(userProfile);
		return new AccountOverViewAction();
	}
	public AccountOverViewAction AccountOverViewQuickNavigationRenewals(){
		AccountOverviewPage accountOverviewPage=new AccountOverviewPage();
		accountOverviewPage.AccountOverViewQuickNavigationRenewals();
		return new AccountOverViewAction();
	}
	
	
	
	
	////zzz
	public LoginAction AccountOverviewRightPromo(){
		AccountOverviewPage accountOverviewPage=new AccountOverviewPage();
		accountOverviewPage.AccountOverviewRightPromo();
		return new LoginAction();
	}
	
	public AccountOverViewAction Verifywelcomemessage(){
        AccountOverviewPage accountOverviewPage=new AccountOverviewPage();
        accountOverviewPage.Verifywelcomemessage();
        return new AccountOverViewAction();
 }


	
	public LoginAction AccountOverviewRightPromoStandardUsers(){
		AccountOverviewPage accountOverviewPage=new AccountOverviewPage();
		accountOverviewPage.AccountOverviewRightPromoStandardUsers();
		return new LoginAction();
	}
	
	public AccountOverViewAction AccountSummaryVerification(UserProfile userProfile){
		AccountOverviewPage accountOverviewPage=new AccountOverviewPage();
		accountOverviewPage.AccountSummaryVerification(userProfile);
		return new AccountOverViewAction();
	}
	
	public AccountOverViewAction AccountOverViewlink(UserProfile userProfile){
		AccountOverviewPage accountOverviewPage=new AccountOverviewPage();
		accountOverviewPage.AccountOverViewlink(userProfile);
		return new AccountOverViewAction();
	}
	
	
	
	/*public AccountOverViewAction AccoutnSummaryVerification(UserProfile userProfile){
		AccountOverviewPage accountOverviewPage=new AccountOverviewPage();
		accountOverviewPage.AccoutnSummaryVerification(userProfile);
		return new AccountOverViewAction();
	}*/
	
	
	public AccountOverViewAction Morethan15(String strSearchByOption,String strKeyword){
		AccountOverviewPage accountOverviewPage=new AccountOverviewPage();
		accountOverviewPage.Morethan15(strSearchByOption, strKeyword);
		return new AccountOverViewAction();
	}
	
	public AccountOverViewAction VerifyAccountOverviewDashboardpage(){
		AccountOverviewPage accountOverviewPage=new AccountOverviewPage();
		accountOverviewPage.VerifyAccountOverviewDashboardpage();
		return new AccountOverViewAction();
	}
	
	public AccountOverViewAction NavigatetoBillingLandingpage(){
		AccountOverviewPage accountOverviewPage=new AccountOverviewPage();
		accountOverviewPage.NavigatetoBillingLandingpage();
		return new AccountOverViewAction();
	}
	public AccountOverViewAction NavigatetoUpdateYourDetailsLandingpage(){
		AccountOverviewPage accountOverviewPage=new AccountOverviewPage();
		accountOverviewPage.NavigatetoUpdateYourDetailsLandingpage();
		return new AccountOverViewAction();
	}
	public AccountOverViewAction NavigatetoMovingHomeLandingPage(){
		AccountOverviewPage accountOverviewPage=new AccountOverviewPage();
		accountOverviewPage.NavigatetoMovingHomeLandingPage();
		return new AccountOverViewAction();
	}
	
	public AccountOverViewAction NavigatetoManageUsers(){
		AccountOverviewPage accountOverviewPage=new AccountOverviewPage();
		accountOverviewPage.NavigatetoManageUsers();
		return new AccountOverViewAction();
	}
	
	public AccountOverViewAction NavigatetoSMRLandingpage(){
		AccountOverviewPage accountOverviewPage=new AccountOverviewPage();
		accountOverviewPage.NavigatetoSMRLandingpage();
		return new AccountOverViewAction();
	}
	public AccountOverViewAction NavigatetoPayments(){
		AccountOverviewPage accountOverviewPage=new AccountOverviewPage();
		accountOverviewPage.NavigatetoPayments();
		return new AccountOverViewAction();
	}
	public AccountOverViewAction NavigatetoGetAQuotePage(){
		AccountOverviewPage accountOverviewPage=new AccountOverviewPage();
		accountOverviewPage.NavigatetoGetAQuotePage();
		return new AccountOverViewAction();
	}
	public AccountOverViewAction VerfiNavigatetoAccountSummarypage(){
		AccountOverviewPage accountOverviewPage=new AccountOverviewPage();
		accountOverviewPage.VerfiNavigatetoAccountSummarypage();
		return new AccountOverViewAction();
	}
	
	
}
