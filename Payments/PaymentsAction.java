/**
 * 
 */
package bg.framework.app.functional.action.Slingshot;

import bg.framework.app.functional.entities.UserProfile;
import bg.framework.app.functional.page.Slingshot.PaymentsPage;

/**
 * @author 208070
 *
 */
public class PaymentsAction {
	public PaymentsAction verifyLinks() {
		new PaymentsPage().verifyLinks();
		return new PaymentsAction();	
	}

	public PaymentsAction navigatetoMakeAPayment() {
		new PaymentsPage().navigatetoMakeAPayment();
		return new PaymentsAction();	
	}

	public PaymentsAction paymentDetails(UserProfile userProfile) {
		new PaymentsPage().paymentDetails(userProfile);
		return new PaymentsAction();	
	}

	public PaymentsAction errorValidation(UserProfile userProfile) {
		new PaymentsPage().errorValidation(userProfile);
		return new PaymentsAction();	
	}

	public PaymentsAction logOut() {
		new PaymentsPage().logOut();
		return new PaymentsAction();
		
	}
	public PaymentsAction TwoCardlimit(int increment, UserProfile userProfile) {
		new PaymentsPage().TwoCardlimit(increment, userProfile);
		return new PaymentsAction();
	}

	public PaymentsAction navigatetoMakeAPaymentError() {
		new PaymentsPage().navigatetoMakeAPaymentError();
		return new PaymentsAction();
	}
	public PaymentsAction navigateToManageYourPaymentsCards() {
		new PaymentsPage().navigateToManageYourPaymentsCards();
		return new PaymentsAction();	
	}
	public PaymentsAction verifyAndDeleteCardsInManageTable() {
		new PaymentsPage().verifyAndDeleteCardsInManageTable();
		return new PaymentsAction();	
	}
	public PaymentsAction verifyAndEditCardsInManageTable(UserProfile userProfile) {
		new PaymentsPage().verifyAndEditCardsInManageTable(userProfile);
		return new PaymentsAction();	
	}
	public PaymentsAction cardStorageFunctionality(UserProfile userProfile) {
		new PaymentsPage().cardStorageFunctionality(userProfile);
		return new PaymentsAction();	
	}
	
	public PaymentsAction paymentViaNewCard(UserProfile userProfile,String SaveCard) {
		new PaymentsPage().paymentViaNewCard(userProfile, SaveCard);
		return new PaymentsAction();	
	}
	
	public PaymentsAction verifySummaryPage() {
		new PaymentsPage().verifySummaryPage();
		return new PaymentsAction();	
	}
	public PaymentsAction verifyAccountsummary() {
		new PaymentsPage().verifyAccountsummary();
		return new PaymentsAction();	
	}
	public PaymentsAction paymentViaNewCard_NotClickSaveButton(UserProfile userProfile) {
		new PaymentsPage().paymentViaNewCard_NotClickSaveButton(userProfile);
		return new PaymentsAction();	
	}
	public PaymentsAction paymentlink_searchbar() {
		new PaymentsPage().payment_search();
		return new PaymentsAction();	
	}
	public PaymentsAction open_bgburl() {
		new PaymentsPage().open_bgburl();
		return new PaymentsAction();	
	}
	

	
	public PaymentsAction paymentDue() {
		new PaymentsPage().paymentDue();
		return new PaymentsAction();	
	}
	
	
	public PaymentsAction paymentViaNewCard_InValidAmount() {
		new PaymentsPage().paymentViaNewCard_InValidAmount();
		return new PaymentsAction();	
	}
	public PaymentsAction VerifypaymentViaExistingCard(UserProfile userProfile) {
		new PaymentsPage().VerifypaymentViaExistingCard(userProfile);
		return new PaymentsAction();	
	}
	public PaymentsAction VerifypaymentViaExistingCardNew(UserProfile userProfile) {
		new PaymentsPage().VerifypaymentViaExistingCardNew(userProfile);
		return new PaymentsAction();	
	}
	public PaymentsAction verifySetupDD() {
		new PaymentsPage().verifySetupDD();
		return new PaymentsAction();	
	}
	public PaymentsAction verifyamendDD(UserProfile userProfile) {
		new PaymentsPage().verifyamendDD(userProfile);
		return new PaymentsAction();	
	}
	public PaymentsAction VerifyAccountSummaryPage(UserProfile userProfile) {
		new PaymentsPage().VerifyAccountSummaryPage(userProfile);
		return new PaymentsAction();	
	}
	public PaymentsAction VerifyPaymentforDebitDDAccount() {
		new PaymentsPage().VerifyPaymentforDebitDDAccount();
		return new PaymentsAction();	
	}
	public PaymentsAction VerifyPaymentforZeroBalanceAccount() {
		new PaymentsPage().VerifyPaymentforZeroBalanceAccount();
		return new PaymentsAction();	
	}
	public PaymentsAction VerifyAccountSummaryPage_forCreditAccount() {
		new PaymentsPage().VerifyAccountSummaryPage_forCreditAccount();
		return new PaymentsAction();	
	}
	public PaymentsAction auditentryverification_for_managecards(UserProfile userProfile, String databasemsg) {
		new PaymentsPage().auditentryverification_for_managecards(userProfile, databasemsg);
		return new PaymentsAction();	
	}
	
	
	
	
	
}