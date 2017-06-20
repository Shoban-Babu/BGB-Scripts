package bg.framework.app.functional.action.Slingshot;

import java.text.ParseException;

import bg.framework.app.functional.entities.SMRAccountDetails;
import bg.framework.app.functional.page.Slingshot.AccountSummaryPage;
import bg.framework.app.functional.page.Slingshot.ViewBillPage;
import bg.framework.app.functional.page.common.LegacyHomePage;
import bg.framework.app.functional.page.common.LegacyLoginPage;

public class ViewBillAction {
	public ViewBillAction BgbnavigateToLogin() {
		LegacyHomePage legacyHomePage = new LegacyHomePage();
		legacyHomePage.BgbnavigateToLoginPage(); 
		return this;
	}	
	public ViewBillAction BgbloginDetails(SMRAccountDetails smrProfile) {
		LegacyLoginPage legacyLoginPage = new LegacyLoginPage();
		legacyLoginPage.BgbLoginEmail(smrProfile.getEmail());
		legacyLoginPage.BgbLoginPassword(smrProfile.getPassword());
		legacyLoginPage.BgbClickSubmit();
		return this;
	}	
	public ViewBillAction BgbverifyAfterLogin() {
		new  AccountSummaryPage().AccountOverviewVerification();
		return this;
	}	
	public ViewBillAction downloadAndVerifyBill(SMRAccountDetails smrProfile){
		ViewBillPage viewbill=new ViewBillPage();
		viewbill.clickViewBillLink();
		viewbill.selectTerm("Account number");
		viewbill.enterSearchCriteria(smrProfile.getAccountNumber());
		viewbill.enterFromDate(12);
		viewbill.enterToDate(0);
		viewbill.clickSearchBillSearchButton();
		viewbill.verifyBillTable();
		viewbill.selectCheckboxDownload();
		viewbill.clickSearchBillDownload();
		try {
			viewbill.openAndVerifyZipFile();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return this;
	}	
	public ViewBillAction verifySearchOptionBill(){
		ViewBillPage viewbill=new ViewBillPage();
		viewbill.clickViewBillLink();
		viewbill.verifySearchByOptions();
		return this;
	}	
	public ViewBillAction verifyBillTable(SMRAccountDetails smrProfile){
		ViewBillPage viewbill=new ViewBillPage();
		viewbill.clickViewBillLink();
		viewbill.selectTerm("Account number");
		viewbill.enterSearchCriteria(smrProfile.getAccountNumber());
		viewbill.enterFromDate(3);
		viewbill.enterToDate(0);
		viewbill.clickSearchBillSearchButton();
		viewbill.verifyBillTable();
		viewbill.verifyBillTableColumns();
		viewbill.verifySortingBillTable();
		viewbill.selectTerm("Meter point reference number");
		viewbill.enterSearchCriteria(smrProfile.getMprn());
		viewbill.enterFromDate(3);
		viewbill.enterToDate(0);
		viewbill.clickSearchBillSearchButton();
		viewbill.verifyBillTable();		
		return this;
	}	
	public ViewBillAction verifyBackLink(SMRAccountDetails smrProfile){
		ViewBillPage viewbill=new ViewBillPage();
		viewbill.clickViewBillLink();
		viewbill.clickBackLink();
		new  AccountSummaryPage().AccountOverviewVerification();
		return this;
	}	
	public ViewBillAction verifyFromToDateError(SMRAccountDetails smrProfile){
		ViewBillPage viewbill=new ViewBillPage();
		viewbill.clickViewBillLink();
		viewbill.selectTerm("Account number");
		viewbill.enterSearchCriteria(smrProfile.getAccountNumber());
		viewbill.verifyFromToDateError(smrProfile,0);
		viewbill.verifyFromToDateError(smrProfile,1);
		return this;
	}
	public ViewBillAction verifyViewBillPage(){
		new ViewBillPage().verifyViewBillPage(); 
		return new ViewBillAction();    	 
	}
	public ViewBillAction verifyBackToAccountLink(){
		new ViewBillPage().verifyBackToAccountLink();
		return new ViewBillAction();
	}
	public ViewBillAction clickViewYourLastBill(){
		new ViewBillPage().clickAndVerifyViewYourLastBillLink(); 
		return new ViewBillAction();    	 
	}
	public ViewBillAction verifyPDFFile() throws ParseException{
		ViewBillPage viewBill = new ViewBillPage();		 
		viewBill.deleteFile();
		viewBill.downloadPDF();
		viewBill.verifyPDFFile(); 
		return new ViewBillAction();    	 
	}
	public ViewBillAction openAndVerifyZipFile() throws ParseException{
		ViewBillPage viewBill = new ViewBillPage();		 
		viewBill.deleteFolder();
		viewBill.downloadPDF();
		viewBill.openAndVerifyZipFile(); 
		return new ViewBillAction();    	 
	}
	public ViewBillAction verifyAccountNumberError(SMRAccountDetails smrProfile){
		ViewBillPage viewbill=new ViewBillPage();
		viewbill.clickViewBillLink();
		viewbill.selectTerm("Account number");	
		viewbill.enterSearchCriteria();
		return this;
	}
	public ViewBillAction verifyBillTableByMprn(SMRAccountDetails smrProfile){
		ViewBillPage viewbill=new ViewBillPage();
		viewbill.clickViewBillLink();			
		viewbill.selectTerm("Meter point reference number");
		viewbill.enterSearchCriteria(smrProfile.getMprn());
		viewbill.enterFromDate(3);
		viewbill.enterToDate(0);
		viewbill.clickSearchBillSearchButton();
		viewbill.verifyBillTable();			
		viewbill.verifyBillTableColumns();
		viewbill.verifySortingBillTable();		
		return this;
	}
	public ViewBillAction verifyMaxNoOfBillSelection(){
		ViewBillPage viewbill=new ViewBillPage();
		viewbill.selectMaxNoOfBills();
		viewbill.verifyandCloseOverlay();
		return this;
	}
	public ViewBillAction verifyDataSortingFields(SMRAccountDetails smrProfile){
		ViewBillPage viewbill=new ViewBillPage();
		viewbill.clickViewBillLink();			
		viewbill.selectTerm("Account number");
		viewbill.enterSearchCriteria(smrProfile.getAccountNumber());
		viewbill.enterFromDate(3);
		viewbill.enterToDate(0);
		viewbill.clickSearchBillSearchButton();			
		viewbill.verifyDataSortingFields();
		return this;
	}	 
	public ViewBillAction viewBillAuditDetailsEntry(SMRAccountDetails smrProfile){
		new ViewBillPage().verifyAuditTable(smrProfile);
		return this;
	}
	public ViewBillAction viewYourbillRHS(){
		new ViewBillPage().viewYourbillRHS();
		return this;
	}
	public ViewBillAction viewBillTimestampAuditEntry(SMRAccountDetails smrProfile){
		new ViewBillPage().verifyAuditTableLoginTimestamp(smrProfile);
		return this;
	}
	public ViewBillAction EnergyMadeSimpleLink(){
		new ViewBillPage().EnergyMadeSimple();
		return this;
	}
	
	public ViewBillAction verifyViewBillDue(){
		new ViewBillPage().verifyViewBillDue();
		return this;
	}
	public ViewBillAction verifyBillByMprn(SMRAccountDetails smrProfile){
		ViewBillPage viewbill=new ViewBillPage();
		viewbill.clickViewBillLink();			
		viewbill.selectTerm("Meter point reference number");
		viewbill.enterSearchCriteria(smrProfile.getMprn());
		viewbill.enterFromDate(3);
		viewbill.enterToDate(0);
		viewbill.clickSearchBillSearchButton();
		viewbill.verifyBillTable();
		return this;
}
	public ViewBillAction verifyBillByAccNumber(SMRAccountDetails smrProfile){
		ViewBillPage viewbill=new ViewBillPage();
		viewbill.clickViewBillLink();
		viewbill.selectTerm("Account number");	
		viewbill.enterSearchCriteria(smrProfile.getAccountNumber());
		viewbill.enterFromDate(3);
		viewbill.enterToDate(0);
		viewbill.clickSearchBillSearchButton();
		viewbill.verifyBillTable();
		return this;
	}
	public ViewBillAction verifyBillBySupplyNumber(SMRAccountDetails smrProfile){
		ViewBillPage viewbill=new ViewBillPage();
		//viewbill.clickViewBillLink();
		viewbill.selectTerm("Supply number");	
		viewbill.enterSearchCriteria(smrProfile.getSupplyNumber());
		viewbill.enterFromDate(3);
		viewbill.enterToDate(0);
		viewbill.clickSearchBillSearchButton();
		viewbill.verifyBillTable();
		return this;
	}
	public ViewBillAction verifyBillByBillNumber(SMRAccountDetails smrProfile){
		ViewBillPage viewbill=new ViewBillPage();
		//viewbill.clickViewBillLink();
		viewbill.selectTerm("Bill number");	
		viewbill.enterSearchCriteria(smrProfile.getBillNumber());
		//viewbill.enterFromDate("3");
		//viewbill.enterToDate("0");
		viewbill.clickSearchBillSearchButton();
		viewbill.verifyBillTable();
		return this;
	}
	public ViewBillAction clickBillingLink(SMRAccountDetails smrProfile){
		ViewBillPage viewbill=new ViewBillPage();
		viewbill.verifyAndClinkBillingLink();
		return this;
	}
	public ViewBillAction verifyviewbillpagetitlesection(SMRAccountDetails smrProfile){
		ViewBillPage viewbill=new ViewBillPage();
		viewbill.verifyviewbillpagetitlesection(smrProfile);
		return this;
	}
	public ViewBillAction verifyviewbillapybalancesection(SMRAccountDetails smrProfile){
		ViewBillPage viewbill=new ViewBillPage();
		viewbill.verifyviewbillapybalancesection(smrProfile);
		return this;
	}
	public ViewBillAction verifyviewbilltablenew_Header(){
		ViewBillPage viewbill=new ViewBillPage();
		viewbill.verifyviewbilltablenewHeader();
		return this;
	}
	
	public ViewBillAction verifyviewbilltable_newcontents(){
		ViewBillPage viewbill=new ViewBillPage();
		viewbill.verifyviewbilltablenewcontents();
		return this;
		
	}
	
	public ViewBillAction verfiypagenation(){
		ViewBillPage viewbill=new ViewBillPage();
		viewbill.verfiypagenation();
		return this;
		
	}
	
	public ViewBillAction verifyViewBillnew_Links(SMRAccountDetails smrProfile){
		ViewBillPage viewbill=new ViewBillPage();
		viewbill.verifyViewBillnewLinks(smrProfile);
		viewbill.verifyGlobalAccountsection_paybalance(smrProfile);
		return this;		
	}
	
	
	/*public ViewBillAction verifyLinkNavigations(){
		ViewBillPage viewbill=new ViewBillPage();
		viewbill.verifyLinkNavigations();
		return this;
	}*/
}
