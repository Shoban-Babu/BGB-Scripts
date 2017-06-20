package bg.framework.app.functional.page.Slingshot;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Properties;

import bg.framework.app.functional.entities.DirectDebit;
import bg.framework.app.functional.entities.UserProfile;
import bg.framework.app.functional.page.common.BasePage;
import bg.framework.app.functional.util.OnlineDBConnector;
import bg.framework.app.functional.util.PropertyLoader;
import bg.framework.app.functional.util.Report;
import bg.framework.app.functional.util.RobotSendKeys;
import bg.framework.app.functional.util.RunQTP;
import bg.framework.app.functional.page.common.SlingshotErrorMessages;
//

public class DirectDebitPage extends BasePage{
	private final static String FILE_NAME = "resources/Slingshot/DirectDebit.properties";
    
    private static Properties pageProperties = new PropertyLoader(FILE_NAME).load();
    SlingshotErrorMessages errormsg = new SlingshotErrorMessages();
	public void BgbloginUser(DirectDebit directDebit) {	
	
	browser.open("https://10.224.70.18/bgbusiness/login"); 
	
    verifyAndInputById(pageProperties.getProperty("LoginPage.Email"), "Email Id", directDebit.getEmail());
    verifyAndInputById(pageProperties.getProperty("LoginPage.Password"), "Password", directDebit.getPassword());
    verifyAndClickWithXpath(pageProperties.getProperty("LoginPage.NewLoginSubmitXpath"), "Submit button");
    browser.wait(getWaitTime());
    
	}	
	public void clickDirectDebitLink(DirectDebit directDebit){
		verifyAndClickWithXpath(pageProperties.getProperty("DirectDebit.DirectDebitLink").replace("ACCOUNTNUMBER", directDebit.getAccNumber()), "Setup DD link");
	}
	
	
	public void SelectSetupVariableDirectDebitLink(){
		verifyAndClickWithXpath(pageProperties.getProperty("DirectDebit.selectVariableDirectDebitLink"), " Variable DirectDebitLink");
	}
	
	public void clickDirectAmendDebitLink(){
		verifyAndClickWithXpath(pageProperties.getProperty("DirectDebit.ManageDirectDebitLink"), " Amend DirectDebitLink");
	}
		
	public void selectDirectDebitForBank(String option){
		
		
		browser.wait(5000);
		System.out.println("zzzzzzzzzzzzzzz");
		verifyAndClickWithXpath(pageProperties.getProperty("DirectDebit.DirectDebitYes"), " DirectDebitOption-Yes");
		
		
		/*if(option.equalsIgnoreCase("Yes")){
			
			 verifyAndClick(pageProperties.getProperty("DirectDebit.DirectDebitYes"), "DirectDebitOption-Yes");		  
		}else{
			verifyAndClick(pageProperties.getProperty("DirectDebit.DirectDebitNo"), "DirectDebitOption-No");
		}*/
	}
	
	
	public void directDebitFormpaymentdate(DirectDebit directDebit){
			getWaitTime();		
			verifyAndSelectDropDownBox(pageProperties.getProperty("DirectDebit.Directdebitpaymentdate"),"Direct Debit Payment date","2");
	}
	public void directDebitForm(DirectDebit directDebit){
		getWaitTime();
	   // verifyAndSelectDropDownBox(pageProperties.getProperty("DirectDebit.Directdebitpaymentdate"),"Direct Debit Payment date","2");
		browser.clearValue(pageProperties.getProperty("DirectDebit.AccountHolderName"));
		verifyAndInputById(pageProperties.getProperty("DirectDebit.AccountHolderName"), "AccountHolderName",directDebit.getAccountHolderName());
//		String sortcode1="$('#sortCode1').val("+directDebit.getSortCode1()+")";
//		sortcode1.replace("values", directDebit.getSortCode1());
		
		
		verifyAndInputById_value(pageProperties.getProperty("DirectDebit.BranchSortCode1"), "BranchSortCode1",directDebit.getSortCode1());
		verifyAndInputById_value(pageProperties.getProperty("DirectDebit.BranchSortCode2"), "BranchSortCode2",directDebit.getSortCode2());
		verifyAndInputById_value(pageProperties.getProperty("DirectDebit.BranchSortCode3"), "BranchSortCode3",directDebit.getSortCode3());
		verifyAndInputById(pageProperties.getProperty("DirectDebit.BankAccountNumber"), "BankAccountNumber",directDebit.getBankAccountnumber());
		 verifyAndClick(pageProperties.getProperty("DirectDebit.ContiuneToDD"), "Continue");
	}
	 public void selectAccountForDirectDebit(DirectDebit directDebit){
		 System.out.println("==========================");
		   verifyAndClickWithXpath(pageProperties.getProperty("DirectDebit.AccountSummaryimage").replace("ACCOUNTNUMBER", directDebit.getAccNumber()), "Manage account link");
	   }
	public void editdetails()
	{
			browser.wait(3000);
			verifyAndClickWithXpath(pageProperties.getProperty("DirectDebit.SummaryPageEditDetails"), "Edit details");
	}
	public void backandcancel(DirectDebit directDebit)
	{
		verifyAndClickWithXpath(pageProperties.getProperty("DirectDebit.summaryback"), "back");
		directDebitForm(directDebit);
		verifyAndClickWithXpath(pageProperties.getProperty("DirectDebit.summarycancel"), "cancel");
		verifyIsTextPresent("Account overview");
	}
	public void summarypageVerification(DirectDebit directDebit)
	{
		//String getaccountHolderName=browser.getAttribute(pageProperties.getProperty("DirectDebit.verifyaccountHolderName"),"value");
	//	String getsortcode3=browser.getAttribute(pageProperties.getProperty("DirectDebit.BranchSortCode3"),"value");
		String getsortcode3="$('#sortCode3').val()";
		browser.executeScript(getsortcode3);
		
		String getaccountHolderName="$('#accountHolderName1').val()";
		browser.executeScript(getaccountHolderName);
		
		System.out.println("*****************"+getaccountHolderName);		
	//	String getSortcode3=browser.getAttribute(pageProperties.getProperty("DirectDebit.BranchSortCode3"),"value");
		System.out.println("*****************"+getsortcode3);
		
		if(getaccountHolderName!=null && getsortcode3!=null)
		{
			Report.updateTestLog("Setup Direct Debit Enterd values are appearing Successfully", "Pass");
		}
		else
		{
			Report.updateTestLog("Setup Direct Debit Enterd values are appearing Successfully", "Fail");
		}
	}
	
	
	public void clickContinueButtonInAmendPage1(){
		verifyAndClick(pageProperties.getProperty("DirectDebit.ContiuneToDD"), "Continue");
	}
	public void clickDownloadDirectLink(){
		
		verifyAndClickWithXpath(pageProperties.getProperty("DirectDebit.DownloadDirectDebitLink"), "DownloadDirectDebit");
		
	}
	public void clickBackToAccountSummary(){
		
		verifyAndClick(pageProperties.getProperty("DirectDebit.BackToAccountSummary"), "BackToAccountSummary");
	}
	public void checkTermsAndConditions(){
			
		verifyAndSelectCheckBoxByID(pageProperties.getProperty("DirectDebit.TermsAndConditions"), "TermsAndConditions");
		Report.updateTestLog("Direct debit details are filled sucessfully", "WARN");
		
	}
	public void clickSetupDirectDebit(){
			
		verifyAndClick(pageProperties.getProperty("DirectDebit.SetUpDirectDebit"), "SetUpDirectDebit");
			
	}
	public void clickAmendDirectDebit(){
		
		verifyAndClick(pageProperties.getProperty("DirectDebit.AmendDirectDebit"), "AmendDirectDebit");
			
	}
	public void verifyDirectDebitLink(){
		getWaitTime();
		if(browser.isElementVisibleWithXpath(pageProperties.getProperty("DirectDebit.DownloadDirectDebitLink"))){
			Report.updateTestLog("DownloadDirectDebit Link is visible", "Pass");
		}else{
			Report.updateTestLog("DownloadDirectDebit Link is not visible", "Fail");
		}
	}
	public void verifyBackToAccountSummary(){
		if(browser.isElementVisible(pageProperties.getProperty("DirectDebit.BackToAccountSummary"))){
			verifyAndClick(pageProperties.getProperty("DirectDebit.BackToAccountSummary"), "Back to Account summary");
			Report.updateTestLog("BackToAccountSummary button is visible and successfully Clicked", "Pass");
		}else{
			Report.updateTestLog("BackToAccountSummary button is not visible and successfully Clicked", "Fail");
		}
		
	}
	public void verifyAccountSummaryTitle(){
		verifyPageTitle(pageProperties.getProperty("DirectDebit.AccountSummaryTitle"));
	}
	public void invalidDataTodirectDebitForm(){
		for(int i=0;i<=3;i++){
		getWaitTime();
			verifyAndInputById(pageProperties.getProperty("DirectDebit.AccountHolderName"), "AccountHolderName","Invalid Account");
			verifyAndInputById(pageProperties.getProperty("DirectDebit.BranchSortCode1"), "BranchSortCode1","00");
			verifyAndInputById(pageProperties.getProperty("DirectDebit.BranchSortCode2"), "BranchSortCode2","01");
			verifyAndInputById(pageProperties.getProperty("DirectDebit.BranchSortCode3"), "BranchSortCode3","02");
			verifyAndInputById(pageProperties.getProperty("DirectDebit.BankAccountNumber"), "BankAccountNumber","98989898");
			verifyAndClick(pageProperties.getProperty("DirectDebit.ContiuneToDD"), "Continue");
		}
	}
	public void verifyOverlayAfterDirectDebit(){
		
		
	}
	public void verifySetupConfirmationText(){
	
		verifyIsTextPresent("Your Fixed Direct Debit is set up");
	   Report.updateTestLog("Direct debit setup sucessfully", "WARN");
				
	}
	public void verifyvariableAmendConfirmationText(){
		
		verifyIsTextPresent("Your Variable Direct Debit is now amended");
					
	}
	public void verifyVariableSetupConfirmationText(){
		
		verifyIsTextPresent("Your Variable Direct Debit is set up");
				
	}
	public void verifySetupConfirmationText_page(){
		
		verifyIsTextPresent("Your Fixed Direct Debit is set up");				
	}
public void AmendVariableConfirmationPage(){
		
		verifyIsTextPresent("Your Variable Direct Debit is now amended");				
	}
public void AmendFixedConfirmationPage(){
	
	verifyIsTextPresent("Your Fixed Direct Debit is now amended");				
}



	public void clickManageDirectDebitLink(){
		verifyAndClickWithXpath(pageProperties.getProperty("DirectDebit.ManageDirectDebitLink"), "Manage Direct Debit Link");
				
	}
	public void clickManageDirectDebitPodLink(){
		verifyAndClickWithXpath(pageProperties.getProperty("DirectDebit.ManageDirectDebitpodlink"), "Manage Direct Debit Link");				
	}
	public void clickAmendFixedDirectDebit()
	{
		verifyAndClickWithXpath(pageProperties.getProperty("DirectDebit.AmendFixedDirectDebit"), "Amend Fixed direct debit link");				
	}
	
	
	
	
	public void verifyExistingTextInDdForm(){
		try{
		String getAccHolderName=browser.getAttribute(pageProperties.getProperty("DirectDebit.AccountHolderName"),"value");
		String sortcode1="$('#sortCode1').val()";
		browser.executeScript(sortcode1);
		String sortcode2="$('#sortCode2').val()";
		browser.executeScript(sortcode2);
		String sortcode3="$('#sortCode3').val()";
		browser.executeScript(sortcode3);
//		String sortcode1=browser.getAttribute(pageProperties.getProperty("DirectDebit.BranchSortCode1"),"value");
//		String sortcode2=browser.getAttribute(pageProperties.getProperty("DirectDebit.BranchSortCode2"),"value");
//		String sortcode3=browser.getAttribute(pageProperties.getProperty("DirectDebit.BranchSortCode3"),"value");
		String bankAccNumber=browser.getAttribute(pageProperties.getProperty("DirectDebit.BankAccountNumber"),"value");
		System.out.println("========================"+bankAccNumber);
		if(getAccHolderName!=null && sortcode1!=null && sortcode2!=null && bankAccNumber!=null){
			Report.updateTestLog("AccountName:"+getAccHolderName+" sortcode1:"+sortcode1+" sortcode2:"+sortcode2+" sortcode3:"+sortcode3+" bankAccNumber:"+bankAccNumber, "Pass");
			Report.updateTestLog("Amend Direct Debit Page is displayed", "Pass");
		}else{
			Report.updateTestLog("Amend Direct Debit Page is not displayed", "Warn");
			Report.updateTestLog("AccountName:"+getAccHolderName+" sortcode1:"+sortcode1+" sortcode2:"+sortcode2+" sortcode3:"+sortcode3+" bankAccNumber:"+bankAccNumber, "Fail");
		}}catch(Exception e){
			Report.updateTestLog("Exception occured in the method (verifyExistingTextInDdForm) :"+e, "Fail");
		}
	}
	public void enterAccountHoldername(String accountHolderName){
		verifyAndInputById(pageProperties.getProperty("DirectDebit.AccountHolderName"), "AccountHolderName",accountHolderName);
				
	}
	public void enterSorCode1(String sorcode1){
		verifyAndInputById(pageProperties.getProperty("DirectDebit.BranchSortCode1"), "BranchSortCode1",sorcode1);
		
	}
    public void enterSorCode2(String sorcode2){
    	verifyAndInputById(pageProperties.getProperty("DirectDebit.BranchSortCode2"), "BranchSortCode2",sorcode2);
		
	}
    public void enterSorCode3(String sorcode3){
    	verifyAndInputById(pageProperties.getProperty("DirectDebit.BranchSortCode3"), "BranchSortCode3",sorcode3);
		
	}
    public void bankAccountNumber(String bankAccountName){
		verifyAndInputById(pageProperties.getProperty("DirectDebit.BankAccountNumber"), "BankAccountNumber",bankAccountName);
		
    }
    public void clickEditTheseFields(){
		verifyAndClickWithXpath(pageProperties.getProperty("DirectDebit.EditBankDetail"), "EditTheseFields");
	}
    public void verifyErrorMessageForSameInformation(){
		verifyAndClickWithXpath(pageProperties.getProperty("DirectDebit.EditBankDetail"), "EditTheseFields");
	}
    public void verifyOverlayAfterManageDirectDebitLink(){
    	if(browser.isElementVisibleWithXpath(pageProperties.getProperty("DirectDebit.CloseOverLay"))||
    			browser.isElementVisibleWithXpath(pageProperties.getProperty("DirectDebit.OverLayTextValidation"))){
    		Report.updateTestLog("Overlay displayed", "Pass");
    	}else{
    		Report.updateTestLog("Overlay not displayed", "Fail");
    	}		
	}
    public void verifyOverlayErrorMessageFor28Days(){
    	getWaitTime();
    	if(browser.isElementVisibleWithXpath(pageProperties.getProperty("DirectDebit.CloseOverLay"))||
    			browser.isElementVisibleWithXpath(pageProperties.getProperty("DirectDebit.OverLayTextValidation"))){
    		String getErrorMessage=browser.getTextByXpath(pageProperties.getProperty("DirectDebit.OverLayTextValidation"));
    		if(getErrorMessage.contains(errormsg.DirectDebit_28DaysAmend)){
    			Report.updateTestLog("Error message for 28 days - Expected Result :"+errormsg.DirectDebit_28DaysAmend+"ActualResult "+getErrorMessage, "Pass");
    		}else{
    			Report.updateTestLog("Error message for 28 days - Expected Result :"+errormsg.DirectDebit_28DaysAmend+"ActualResult "+getErrorMessage, "Fail");
    		}
    		String scripts="$('#recognition_overlay').dialog('close');";
        	browser.executeScript(scripts);
//    		closeOverlay();
    	}else{
    		Report.updateTestLog("Overlay not displayed", "Fail");
    	}		
	}
    public void closeOverlay(){
    	if(browser.isElementVisibleWithXpath(pageProperties.getProperty("DirectDebit.CloseOverLay"))){
			browser.clickWithXpath(pageProperties.getProperty("DirectDebit.CloseOverLay"));	
			Report.updateTestLog("Overlay clicked with Xpath-condition1", "Pass");
		}else{
			RobotSendKeys.typeenter();	
			Report.updateTestLog("Robot Send Key-'Enter'typed", "Pass");
		}
    	if(browser.isElementVisibleWithXpath(pageProperties.getProperty("DirectDebit.CloseOverLay"))||
    			browser.isElementVisibleWithXpath(pageProperties.getProperty("DirectDebit.OverLayTextValidation"))){
    		RobotSendKeys.typeenter();
    		Report.updateTestLog("Robot Send Key-'Enter'typed-condition2", "Pass");
    	}
    	if(browser.isElementVisibleWithXpath(pageProperties.getProperty("DirectDebit.CloseOverLay"))||
    			browser.isElementVisibleWithXpath(pageProperties.getProperty("DirectDebit.OverLayTextValidation"))){
    		RobotSendKeys.typeenter();
    		Report.updateTestLog("Robot Send Key-'Enter'typed-condition3", "Pass");
    	}
    	
    }
    public void clickDirectDebitAgreementLink(){
    	verifyAndClickWithXpath(pageProperties.getProperty("DirectDebit.DirectDebitAgreementLink"), "Direct Debit Agreement");
    	String jqueryToCloseOverlay=("$('a.ui-dialog-titlebar-close').trigger('click');");
    	Report.updateTestLog("Direct Debit Agreement Overlay displayed", "Pass");
		browser.executeScript(jqueryToCloseOverlay);
    	
    }
    public void verifyDirectDebitAgreeOVerlay(){
    	if(browser.isElementVisibleWithXpath(pageProperties.getProperty("DirectDebit.DdAgreeLinkOverlayClose"))||
    			browser.isElementVisibleWithXpath(pageProperties.getProperty("DirectDebit.DdAgreeLinkOverlayText"))){
    		
    		Report.updateTestLog("Direct Debit Agreement Overlay displayed", "Pass");
    		
    		String getMessage=browser.getTextByXpath(pageProperties.getProperty("DirectDebit.DdAgreeLinkOverlayText"));
    		Report.updateTestLog("Text in DD overlay - Expected Result : "+pageProperties.getProperty("DirectDebit.DdLinkOverlayText")+"" +
    				"ActualResult "+getMessage,getMessage.contains(pageProperties.getProperty("DirectDebit.DdLinkOverlayText"))?"Pass":"Fail");

    		String jqueryToClosDirectDebit=("$('#direct-debit-overlay').dialog('close');");
    		browser.executeScript(jqueryToClosDirectDebit);
    		closeDirectDebitAgreeOVerlay();
    	}else{
    		Report.updateTestLog("Direct Debit Agreement Overlay is not displayed", "Fail");
    	}			
	}
    
    public void closeDirectDebitAgreeOVerlay(){
    	if(browser.isElementVisibleWithXpath(pageProperties.getProperty("DirectDebit.DdAgreeLinkOverlayClose"))||
    			browser.isElementVisibleWithXpath(pageProperties.getProperty("DirectDebit.DdAgreeLinkOverlayText"))){
    		RobotSendKeys.typeenter();
    		Report.updateTestLog("Robot Send Key-'Enter'typed", "Pass");
    	}
    	if(browser.isElementVisibleWithXpath(pageProperties.getProperty("DirectDebit.DdAgreeLinkOverlayClose"))||
    			browser.isElementVisibleWithXpath(pageProperties.getProperty("DirectDebit.DdAgreeLinkOverlayText"))){
    		RobotSendKeys.typeenter();
    		Report.updateTestLog("Robot Send Key-'Enter'typed", "Pass");
    	}
    	if(browser.isElementVisibleWithXpath(pageProperties.getProperty("DirectDebit.DdAgreeLinkOverlayClose"))||
    			browser.isElementVisibleWithXpath(pageProperties.getProperty("DirectDebit.DdAgreeLinkOverlayText"))){
    		RobotSendKeys.typeenter();
    		Report.updateTestLog("Robot Send Key-'Enter'typed", "Pass");
    	}
    }
    public void clickPaymentInfoLink(){
    	verifyAndClickWithXpath(pageProperties.getProperty("DirectDebit.PaymentInformation"), "Direct Debit Agreement");
    }
    public void verifyTextPaymentInfoClicked(){
    	verifyIsTextPresent(pageProperties.getProperty("DirectDebit.checkAfterPaymentInfoClicked"));
    }
    public void verifyBankDetailsForSameInfo(){
    	verifyBankDetailsError(errormsg.DirectDebit_AmendSameBankDetails1);
    	verifyBankDetailsError(errormsg.DirectDebit_AmendSameBankDetails2);
    }
    public String verifyBankDetailsError(String error){
    	String errorforSameInfo="";
    	try{
    		errorforSameInfo=browser.getTextByXpath(pageProperties.getProperty("DirectDebit.ErrorMsgFirstPage"));
    		Report.updateTestLog("Error msg in DD first page - Expected Result :"+error+"ActualResult "+errorforSameInfo,errorforSameInfo.contains(error)?"Pass":"Fail");
    	}catch(Exception e){
    		Report.updateTestLog("Error message not displayed ","Fail");
    	}
    	return errorforSameInfo;
    }
    public void clickBackToYourAccount(){
    	
    	verifyAndClickWithXpath(pageProperties.getProperty("DirectDebit.BackToYourAccount"), "BackToYourAccount");
    	verifyPageTitle("Account overview"); // have to change title
    	browser.browserBack();
    	
    }
  public void FDDclickBackToYourAccount(){
    	
    	verifyAndClickWithXpath(pageProperties.getProperty("DirectDebit.FDDBackToYourAccount"), "BackToYourAccount");
    	verifyPageTitle("Account overview"); // have to change title
    	browser.browserBack();
    	
    }
    
    public void clickBackToYourAccount_ConfirmationPage(){
    	
    	verifyAndClickWithXpath(pageProperties.getProperty("DirectDebit.confimationpageBackToYourAccount"), "BackToYourAccount");
    	
    }
    public void enterInvalidAccHolderName(DirectDebit directDebit){
    	String data="aut!@#$%";
    	enterAccountHoldername(data);
    	enterSorCode1(directDebit.getSortCode1());
    	enterSorCode2(directDebit.getSortCode2());
    	enterSorCode3(directDebit.getSortCode3());
    	bankAccountNumber(directDebit.getBankAccountnumber());
    	verifyAndClick(pageProperties.getProperty("DirectDebit.ContiuneToDD"), "Continue");       	
    	verifyBankDetailsError(errormsg.DirectDebit_AccHolderNameSpecial);
    	/*enterAccountHoldername(data[1]);
    	verifyAndClick(pageProperties.getProperty("DirectDebit.ContiuneToDD"), "Continue");
    	verifyBankDetailsError(errormsg.DirectDebit_AccHolderNameSpecial);*/
    }
    public void enterInvalidSortCode(DirectDebit directDebit){
    	enterAccountHoldername(directDebit.getAccountHolderName());
    	String invalidSortdcode[]={"99","99","99"};
    	enterSorCode1(invalidSortdcode[0]);
    	enterSorCode2(invalidSortdcode[1]);
    	enterSorCode3(invalidSortdcode[2]);
    	bankAccountNumber(directDebit.getBankAccountnumber());
    	verifyAndClick(pageProperties.getProperty("DirectDebit.ContiuneToDD"), "Continue");       	
    	verifyBankDetailsError(errormsg.DirectDebit_InvalidSortCode);
    }
    public void enterInvalidBankAccName(DirectDebit directDebit){
    	enterAccountHoldername(directDebit.getAccountHolderName());
    	enterSorCode1(directDebit.getSortCode1());
    	enterSorCode2(directDebit.getSortCode2());
    	enterSorCode3(directDebit.getSortCode3());
    	bankAccountNumber("07456415455");
    	verifyAndClick(pageProperties.getProperty("DirectDebit.ContiuneToDD"), "Continue");       	
    	verifyBankDetailsError(errormsg.DirectDebit_InvalidAccNumber);
    }
    public void enterIncorrectDetails(DirectDebit directDebit){
    	enterAccountHoldername(directDebit.getAccountHolderName());
    	enterSorCode1(directDebit.getSortCode1());
    	enterSorCode2(directDebit.getSortCode2());
    	enterSorCode3(directDebit.getSortCode3());
    	bankAccountNumber("444055556978");
    	verifyAndClick(pageProperties.getProperty("DirectDebit.ContiuneToDD"), "Continue");       	
    }
    public void verifyLockErrorMessage(){    	
    	verifyBankDetailsError(errormsg.DirectDebit_InvalidDetailsFor3Times);
    }
    public void clickCancelButton(){    	
    	verifyAndClickWithXpath(pageProperties.getProperty("DirectDebit.CancelButtonInFirstPage"), "Cancel button");
    }
    public void clickAccordian1(){ 
    	try{
        	if(browser.isElementVisibleWithXpath(pageProperties.getProperty("DirectDebit.accordian1"))){
        		browser.clickWithXpath(pageProperties.getProperty("DirectDebit.accordian1"));
        		Report.updateTestLog("Accordian1 clicked"+pageProperties.getProperty("DirectDebit.accordian1"), "Pass");
        	}else{
        		Report.updateTestLog("Accordian1 clicked"+pageProperties.getProperty("DirectDebit.accordian1"), "Fail");
        	}}catch(Exception e){
        		Report.updateTestLog("Exception"+e+"property is:"+pageProperties.getProperty("DirectDebit.accordian1"), "Fail");
        	}
    }
    public void clickAccordian2() throws Exception{    	
    	try{
    		
    	if(browser.isElementVisibleWithXpath(pageProperties.getProperty("DirectDebit.accordian2"))){
    		browser.clickWithXpath(pageProperties.getProperty("DirectDebit.accordian2"));
    		Report.updateTestLog("Accordian1 clicked"+pageProperties.getProperty("DirectDebit.accordian2"), "Pass");
    	}else{
    		Report.updateTestLog("Accordian1 clicked"+pageProperties.getProperty("DirectDebit.accordian2"), "Fail");
    	}}catch(Exception e){
    		Report.updateTestLog("Exception"+e+"property is:"+pageProperties.getProperty("DirectDebit.accordian2"), "Fail");
    	}
    	
    	String scripts="document.getElementById('BGBAccountList')";
    	browser.executeScript(scripts);
    	
    }
    public void verifyServicePromo(){
    	verifyIsTextPresent(pageProperties.getProperty("DirectDebit.ServicePromo"), "Service Promo");
    }
    public void verifyOverlay24Hours(){
    	try{
	    	browser.wait(2000);
	    	if(browser.isElementVisibleWithXpath(pageProperties.getProperty("DirectDebit.TryAfter24Hours"))||
	    			browser.isElementVisibleWithXpath(pageProperties.getProperty("DirectDebit.DdAgreeLinkOverlayText"))){
	    		Report.updateTestLog("Try After 24 hours Overlay displayed", "Pass");
	    		String getMessage=browser.getTextByXpath(pageProperties.getProperty("DirectDebit.TryAfter24Hours"));
//	    		Report.updateTestLog("Expected Result :"+pageProperties.getProperty("DirectDebit_TryAfter24Hours")+"ActualResult "+getMessage,getMessage.contains(pageProperties.getProperty("DirectDebit_TryAfter24Hours"))?"Pass":"Fail");
	    		Report.updateTestLog("Expected Result :"+errormsg.DirectDebit_TryAfter24Hours+"ActualResult "+getMessage,getMessage.contains(errormsg.DirectDebit_TryAfter24Hours)?"Pass":"Fail");
	    	}else{
	    		Report.updateTestLog("Try After 24 hours is not displayed", "Fail");
	    	}
	//    	closeOverlay();
	    	
    	}catch(Exception e){
    		Report.updateTestLog("Exception while retrieving the error message"+e, "Fail");
    	}
    	String scripts="$('#recognition_overlay').dialog('close');";
    	browser.executeScript(scripts);
    	
	}
    
    @Override
    public boolean verifyIsTextPresent(String value) {
    	// TODO Auto-generated method stub
    	return super.verifyIsTextPresent(value);
    }
    
    public String BillingAddress()
    {
		String billingaddress=null;
		//billingaddress = browser.getTextByXpath(pageProperties.getProperty("DirectDebit.BillingAddress"));
		System.out.println("billingaddress"+billingaddress);
		return billingaddress;	
    	
    }
    
    public void verifyDataThroughQTP(DirectDebit directDebit,String billingaddressApp){
    	
    	String strRetreiveEmailQry="select BUSINESS_PARTNER_ORG_NUMBER from BG_BUSINESS_TA_CUSTOMER_REG" +
    			" where email='emailid'";
    	String query=strRetreiveEmailQry.replace("emailid",directDebit.getEmail().toLowerCase());
    	if(new OnlineDBConnector().getColumn("BUSINESS_PARTNER_ORG_NUMBER", query).equals(null)){
    		Report.updateTestLog("BPORGNUMBER is null for the email"+directDebit.getEmail(), "Fail");
    		return;
    	}
    	String bpOrgNumber=new OnlineDBConnector().getColumn("BUSINESS_PARTNER_ORG_NUMBER", query);
   	    System.out.println("RUN QTP"+bpOrgNumber);
   	    String Acctno=directDebit.getAccNumber();
   	    bpOrgNumber =bpOrgNumber.concat("-").concat(Acctno);
		System.out.print("bpOrgNumber"+bpOrgNumber);	
        RunQTP runQTP = new RunQTP();
        runQTP.runQTP("Slingshot\\src\\bg\\framework\\app\\functional\\util\\vbsScripts\\DirectDebit.vbs", bpOrgNumber);

        browser.wait(15000);
        
        String bankDetails = null;

        try {
            File file1 = new File("D:\\Directdebit\\Directdebittxt.txt");
            FileReader fr = new FileReader(file1);
            BufferedReader br = new BufferedReader(fr);
            String sortCodeFromXml=directDebit.getSortCode1()+directDebit.getSortCode2()+directDebit.getSortCode3();
            
            String sortCod = br.readLine();
            if(sortCod.contains("Sortcode")&& sortCod.contains(":") ){
            	String[] sortCo=sortCod.split(":");
            	String sortCode=sortCo[1];
            	if(sortCode.trim().equals(sortCodeFromXml)){
                	Report.updateTestLog("Sortcode in Application :"+sortCodeFromXml+"Sortcode in ISU :"+sortCode, "Pass");
                }else{
                	Report.updateTestLog("Sortcode in the Application :"+sortCodeFromXml+"Sortcode in ISU :"+sortCode, "Fail");
                } 	
            }else{
            	Report.updateTestLog("Sortcode is not written in the text file"+sortCod, "Fail");            	
            }
            
        	String bankAccountNumb = br.readLine();
        	if(bankAccountNumb.contains("Bank Account")){
        		String[] bankAccountNum=bankAccountNumb.split(":");
        	    String bankAccountNumber=bankAccountNum[1];
	        	if(bankAccountNumber.trim().equals(directDebit.getBankAccountnumber())){
	            	Report.updateTestLog("Bank Account Number in Application :"+directDebit.getBankAccountnumber()+"Bank Account Number in ISU :"+bankAccountNumber, "Pass");
	            }else{
	            	Report.updateTestLog("Sortcode in the Application :"+directDebit.getBankAccountnumber()+"Bank Account Number in ISU :"+bankAccountNumber, "Fail");
	            }
        	}
        	
        	String bankAccHold = br.readLine();
        	if(bankAccHold.contains("Account Holder Name")&& bankAccHold.contains(":")){
        		String[] bankAccHol=bankAccHold.split(":");
        	    String bankAccHolder=bankAccHol[1];    
        	if(bankAccHolder.trim().equals(directDebit.getAccountHolderName())){
            	Report.updateTestLog("Account Holder Name in Application :"+directDebit.getAccountHolderName()+"Account Holder Name in ISU :"+bankAccHolder, "Pass");
            }else{
            	Report.updateTestLog("Account Holder Name in Application :"+directDebit.getAccountHolderName()+"Account Holder Name in ISU :"+bankAccHolder, "Fail");
            }
        	}
                
        	String Billingaddress = br.readLine();
           	if(Billingaddress.contains("Billing Address")&& Billingaddress.contains(":")){
        		String[] Billingaddress1=Billingaddress.split(":");
        	    String Billingaddressisu=Billingaddress1[1];    
        	    System.out.println("Billing Address"+Billingaddressisu);
        	if(Billingaddressisu.trim().equals(billingaddressApp)){
        		Report.updateTestLog("Billing Address in Application :"+billingaddressApp+"Billing Address in ISU :"+Billingaddressisu , "Pass");
            }else{
            	Report.updateTestLog("Billing Address in Application :"+billingaddressApp+"Billing Address in ISU :"+Billingaddressisu , "Fail");
             }
        	}
	        String directdebit = br.readLine();
	       	if(directdebit.contains("DirectDebitStatus")&& directdebit.contains(":")){
	    		String[] directdebit1=directdebit.split(":");
	    	    String directDebit1=directdebit1[1];    
	    	    System.out.println("DirectDebit"+directDebit1);
	    	if(directDebit1.trim().equals("DirectDebit")){
	        	Report.updateTestLog("Direct Debit is successfully Completed in the Application" ,"Pass");
	        }else{
	        	Report.updateTestLog("Direct Debit is successfully Completed in the Application", "Fail");
	         }
	    	}
       	
       
        br.close();
       }catch (IOException e) {
        System.out.println("bad"+e);
    }
        
    }
    public void verifyDirectDebitForDiffAccount(DirectDebit directDebit){
    	String accountNumber=directDebit.getAccNumber();
    	if(accountNumber.contains("-")){
    	String[] splitAcc=accountNumber.split("-");
    	for(int i=0;i<splitAcc.length;i++){ 
    		selectAccountForDiffAccount(splitAcc[i]);
	    	clickDirectDebitLink(directDebit);
	    	selectDirectDebitForBank("YES");
	    	verifyServicePromo();
	    	directDebitForm(directDebit);
	    	checkTermsAndConditions();
	    	clickSetupDirectDebit();
	    	verifyOverlayAfterDirectDebit();
	    	verifySetupConfirmationText();
	    	clickBackToYourAccount();
	    	
	    //	verifyIsTextPresent(value);
    	 }
       }else{
    	   Report.updateTestLog("Test data Error - accountnumber is not more than one","Fail");
       }
	}
    
    public void selectAccountForDiffAccount(String splitAcc){
 	   String Manageaccount=pageProperties.getProperty("AccountSummary.ManageAccountLink")+splitAcc+")]";
 	   System.out.println("manageaccount link:"+Manageaccount);
 	   for(int i=1;i<4;i++){
 		   try{			   			   
 			   if(browser.isElementVisibleWithXpath(Manageaccount)){
 				   verifyAndClickWithXpath(Manageaccount, "ManageAccountLink clicked for this account"+splitAcc);
 				   break;
 			   }else if(browser.isElementVisibleWithXpath(pageProperties.getProperty("AccountSummary.PaginationNextLink"))){
 				   //function to click the pagination
 				   if(clickPaginationNextLink(i).contains("false")){
// 					   break;
 				   }
 			   }
 		   }catch(Exception e){
 			   Report.updateTestLog("Exception occured while clicking the manage Account link for the account:"+Manageaccount , "Fail");
 		     }
 	   }
 	   if(browser.isElementVisible(pageProperties.getProperty("AccountSummary.OptionsToSearch"))){
 		   //function to click the drop down and select the account
 		   
 		   if(selectAccountFromDropDown(splitAcc).contains("true")){
 			   verifyAndClickWithXpath(Manageaccount, "ManageAccountLink clicked for this account"+splitAcc);
 		   }else{
 			   Report.updateTestLog("Manage Account link is not found/clicked for the account:"+Manageaccount , "Fail");   
 		   }		   
 	   }
 	   
    }
    public String clickPaginationNextLink(int i){
 	   String functionResult;
 	   if(browser.isElementVisibleWithXpath(pageProperties.getProperty("AccountSummary.PaginationNextLink"))){
 		   browser.clickWithXpath(pageProperties.getProperty("AccountSummary.PaginationNextLink"));
 		   System.out.println("Pagination clicked :"+i);
 		   functionResult="true";
 	   }else{
 		   functionResult="false";
 	   }
 	   return functionResult;
    }
    
 	public String selectAccountFromDropDown(String directDebit){
 		String functionResult;
 		   if(browser.isElementVisible(pageProperties.getProperty("AccountSummary.OptionsToSearch"))){
 			   Report.updateTestLog("Search by drop down displayed", "Pass");   
 		    verifyAndSelectDropDownBox(pageProperties.getProperty("AccountSummary.OptionsToSearch"), "Search Accounts", "Contract Account Number");
 		    verifyAndInputById(pageProperties.getProperty("AccountSummary.ContractAccountNumber"), "ContractAccountNumber", directDebit);
 		    verifyAndClick(pageProperties.getProperty("AccountSummary.SearchAccounts"),"Search-Accounts");
 		       functionResult="true";
 		   }else{
 			   functionResult="false";
 		   }
 		   return functionResult;
 	   } 
    
 	public void emailErrorMessageValidation() {
 		String[] email = new String[3];
 		email[0]="";
 		email[1]="qw2w3w3w@bgtest.co.uk";
 		email[2]="smartinsight_25@smart@bgtest.co.uk";
 		enterInvalidEmail(email[0]);
 		getErrorMsgLoginscreen(errormsg.Login_EmailEmpty);
 		enterInvalidEmail(email[1]);
 		getErrorMsgLoginscreen(errormsg.Login_EmailNotRegistered);
 		enterInvalidEmail(email[2]);
 		getErrorMsgLoginscreen(errormsg.Login_EmailInccorectFormat);
 	}
 	public void enterInvalidEmail(String value){
 		browser.open("https://10.224.70.18/business/agent/audit-details");
 		verifyAndInputById(pageProperties.getProperty("AuditTrial.EmailId"), "Email Id",value);
 		verifyAndClickWithXpath(pageProperties.getProperty("AuditTrial.ToCalender"), "To date calender");
 		RobotSendKeys.typeenter();
 		DateFormat dateFormat=new SimpleDateFormat("dd/MM/yyyy");
 		Calendar cal=Calendar.getInstance();
 		cal.add(Calendar.MONTH,-3);
 		System.out.println("subtract one month"+ dateFormat.format(cal.getTime()));
 		verifyAndInputById(pageProperties.getProperty("AuditTrial.FromDate"), "From date", dateFormat.format(cal.getTime()));
	    verifyAndClickWithXpath(pageProperties.getProperty("AuditTrial.SubmitButton"), "Submit button");
 		getWaitTime();
 	}
 	public void getErrorMsgLoginscreen(String emailErrorMessage) {
 		try{
 		String errormsgvalue=browser.getTextByXpath(pageProperties.getProperty("AuditTrial.ErrorMessage")); 		
 		Report.updateTestLog("Audit Login screen-Error msg Expected Result: "+emailErrorMessage+" Actual Result: "+errormsgvalue,errormsgvalue.contains(emailErrorMessage)?"Pass":"Fail");
 		}catch(Exception e){
 			Report.updateTestLog("Error locating xpath in error msg section :"+e, "Fail");
 		}
 			
 	}
public void bpnumberErrorMessageValidation() {
 		String[] bpnumber = new String[4];
 		bpnumber[0]="";
 		bpnumber[1]="9999999999";
 		bpnumber[2]="99999999!@";
 		bpnumber[3]="99999999ab";
 		enterInvalidBpnumber(bpnumber[0]);
 		getErrorMsgLoginscreen(errormsg.Login_EmailEmpty);
 		enterInvalidBpnumber(bpnumber[1]);
 		getErrorMsgLoginscreen(errormsg.Login_EmailNotRegistered);
 		enterInvalidBpnumber(bpnumber[2]);
 		getErrorMsgLoginscreen(errormsg.Login_EmailInccorectFormat);
 		enterInvalidBpnumber(bpnumber[3]);
 		getErrorMsgLoginscreen(errormsg.Login_EmailInccorectFormat);
 	}
public void enterInvalidBpnumber(String value){
 		verifyAndInputById(pageProperties.getProperty("AuditTrial.BpNumber"), "Bpnumber",value);
 		verifyAndClickWithXpath(pageProperties.getProperty("AuditTrial.ToCalender"), "To date calender");
 		RobotSendKeys.typeenter();
 		DateFormat dateFormat=new SimpleDateFormat("dd/MM/yyyy");
 		Calendar cal=Calendar.getInstance();
 		cal.add(Calendar.MONTH,-3);
 		System.out.println("subtract one month"+ dateFormat.format(cal.getTime()));
 		verifyAndInputById(pageProperties.getProperty("AuditTrial.FromDate"), "From date", dateFormat.format(cal.getTime()));
	    verifyAndClickWithXpath(pageProperties.getProperty("AuditTrial.SubmitButton"), "Submit button");
 		getWaitTime();
 	} 	
public void verifyAccountExist(DirectDebit directDebit){
	int accountExist=new OnlineDBConnector().verifyAccountExit("email","accountnumber");
	if(accountExist>0){
		new RegistrationPage()	
			/*.registerDetailsFirstPage(userProfile)
			.registerDetailsSecondPage(userProfile)
			.openUrlandVerifyRegistration(userProfile)
			.verifyAfterEncryptedUrl()*/;
	}else{
		Report.updateTestLog("Account exist with the given Email id and account number", "Done");
	}
}
public void verifyAmendConfirmationText(){
	verifyIsTextPresent(pageProperties.getProperty("AmendDebit.ConfirmationText"));
			
}
//***************************************************************************RP3*************************************************************************************
	public void clickVariableDirectDebitLink(){
		verifyAndClickWithXpath(pageProperties.getProperty("DirectDebit.selectVariableDirectDebitLink"), "VariableDirectDebitLink");
	}
	public void clickFixedDirectDebitLink(){
		verifyAndClickWithXpath(pageProperties.getProperty("DirectDebit.selectFixedDirectDebitLink"), "FixedDirectDebitLink");
	}
   public void pleasecallusOverlay_FixedDirectDebit()
   {
	  if(browser.isElementVisibleWithXpath(pageProperties.getProperty("DirectDebit.pleaseCallusOverlayTitle"))){
		   String overlayContent = browser.getTextByXpath(pageProperties.getProperty("DirectDebit.FixedDirectDebitOverlayTitle"));
		   System.out.println("overlayContent: "+overlayContent);
		   Report.updateTestLog("Ovelay content: "+overlayContent, "PASS");
		   verifyAndClickWithXpath(pageProperties.getProperty("DirectDebit.FixedDirectDebitOverlayclose"), "FixedDirectDebitLink");
		   	   }   
   }
   
   public void Directdebitformdata_entry(DirectDebit directDebit){
		getWaitTime();
	//	verifyAndSelectDropDownBox(pageProperties.getProperty("MultiUserMultiViewPage.AddNewUserRightsType"), "User Type","onr");  
		verifyAndInputById(pageProperties.getProperty("DirectDebit.AccountHolderName"), "AccountHolderName",directDebit.getAccountHolderName());
//		String sortcode1="$('#sortCode1').val("+directDebit.getSortCode1()+")";
//		sortcode1.replace("values", directDebit.getSortCode1());
		verifyAndInputById(pageProperties.getProperty("DirectDebit.BranchSortCode1"), "BranchSortCode1",directDebit.getSortCode1());
		verifyAndInputById(pageProperties.getProperty("DirectDebit.BranchSortCode2"), "BranchSortCode2",directDebit.getSortCode2());
		verifyAndInputById(pageProperties.getProperty("DirectDebit.BranchSortCode3"), "BranchSortCode3",directDebit.getSortCode3());
		verifyAndInputById(pageProperties.getProperty("DirectDebit.BankAccountNumber"), "BankAccountNumber",directDebit.getBankAccountnumber());
		verifyAndClick(pageProperties.getProperty("DirectDebit.ContiuneToDD"), "Continue");		
	//	directdebitformpagevalidataion(directDebit);
	}
   
	public void directdebitformpagevalidataion(DirectDebit directDebit)
	{			
		 String AccountHolderName = browser.getTextByXpath(pageProperties.getProperty("DirectDebitsummarypage.AccountHolderName")+ directDebit.getAccountHolderName()+"')]");
		    System.out.println("AccountHolderName"+AccountHolderName); // have to update payment date with string comparsion
		 
		    String branchcode1 = browser.getTextByXpath(pageProperties.getProperty("DirectDebitsummarypage.AccountHolderName")+ directDebit.getSortCode1()+"')]");
		    System.out.println("branchcode1"+branchcode1);
		    String branchcode2 = browser.getTextByXpath(pageProperties.getProperty("DirectDebitsummarypage.BranchSortCode1")+ directDebit.getSortCode2()+"')]");
		    System.out.println("branchcode2"+branchcode2);
		    String branchcode3 = browser.getTextByXpath(pageProperties.getProperty("DirectDebitsummarypage.BranchSortCode2")+ directDebit.getSortCode3()+"')]");
		    System.out.println("branchcode3"+branchcode3);
		    String BankAcctno = browser.getTextByXpath(pageProperties.getProperty("DirectDebitsummarypage.BranchSortCode3")+ directDebit.getBankAccountnumber()+"')]");
		    System.out.println("BankAcctno"+BankAcctno);		   
		    		
			if(	(AccountHolderName.equals(directDebit.getAccountHolderName())) && (branchcode1.equals(directDebit.getSortCode1())) && (branchcode2.equals(directDebit.getSortCode2())) && (branchcode3.equals(directDebit.getSortCode3()))&& (BankAcctno.equals(directDebit.getBankAccountnumber())))
					{
					 	System.out.println("i am done");					 
					 	Report.updateTestLog("Entered User details are prepopulated in the Confirmation page", "PASS");					}
			else
					{
						Report.updateTestLog("Prepopulated values is not Match with the Entered Value", "Fail");
					}	
	}
	
	public void directdebitlinknavigation()
	{
		verifyAndClickWithXpath(pageProperties.getProperty("DirectDebit.howismydirectdebitcalculated"), "howismydirectdebitcalculated");
		verifyAndClickWithXpath(pageProperties.getProperty("DirectDebit.whyshouldipaydirectdebit"), "whyshouldipaydirectdebit");
		verifyAndClickWithXpath(pageProperties.getProperty("DirectDebit.directdebitgurantee"), "directdebitgurantee");
		verifyAndClickWithXpath(pageProperties.getProperty("DirectDebit.contacus"), "contactus");
	}
	public void collectiveAcctOverlay()
	{
		verifyIsTextPresent("customers are not able to set up the Direct Debit ");
	}
	public void PleasecallusOverlay()
	{
	verifyIsTextPresent("Please call us")	;
	String jqueryToCloseOverlay=("$('a.ui-dialog-titlebar-close').trigger('click');");
	browser.executeScript(jqueryToCloseOverlay);
	browser.wait(getWaitTime());
	}
	public void ValidatedirectDebitForm(DirectDebit directDebit){
		
	for(int i=0;i<=2;i++)
	{
		getWaitTime();
		verifyAndInputById(pageProperties.getProperty("DirectDebit.AccountHolderName"), "AccountHolderName",directDebit.getAccountHolderName());
//		String sortcode1="$('#sortCode1').val("+directDebit.getSortCode1()+")";
//		sortcode1.replace("values", directDebit.getSortCode1());
		verifyAndInputById(pageProperties.getProperty("DirectDebit.BranchSortCode1"), "BranchSortCode1",directDebit.getSortCode1());
		verifyAndInputById(pageProperties.getProperty("DirectDebit.BranchSortCode2"), "BranchSortCode2",directDebit.getSortCode2());
		verifyAndInputById(pageProperties.getProperty("DirectDebit.BranchSortCode3"), "BranchSortCode3",directDebit.getSortCode3());
		verifyAndInputById(pageProperties.getProperty("DirectDebit.BankAccountNumber"), "BankAccountNumber",directDebit.getBankAccountnumber());
		verifyAndClick(pageProperties.getProperty("DirectDebit.ContiuneToDD"), "Continue");
		verifyIsTextPresent("You have unsuccessfully tried to enter your Direct Debit details three times. Please try again online in 24 hours or call us on 0800 316 2010.");
	}	
  }
	public void ValidatedirectDebitFormOverlay(DirectDebit directDebit){
		
		
		for(int i=1;i<=3;i++)
		{
			getWaitTime();
			switch(i)
			{
			case 1:			
				verifyAndInputById(pageProperties.getProperty("DirectDebit.AccountHolderName"), "AccountHolderName",directDebit.getAccountHolderName());
				verifyAndInputById_value(pageProperties.getProperty("DirectDebit.BranchSortCode1"), "BranchSortCode1",directDebit.getSortCode1());
				verifyAndInputById_value(pageProperties.getProperty("DirectDebit.BranchSortCode2"), "BranchSortCode2",directDebit.getSortCode2());
				verifyAndInputById_value(pageProperties.getProperty("DirectDebit.BranchSortCode3"), "BranchSortCode3",directDebit.getSortCode3());
				int bankno=Integer.parseInt(directDebit.getBankAccountnumber());
				System.out.println("bankno"+bankno);
				bankno=bankno-2;
				String banknostr = Integer.toString(bankno);
				verifyAndInputById(pageProperties.getProperty("DirectDebit.BankAccountNumber"), "BankAccountNumber",banknostr);
				verifyAndClick(pageProperties.getProperty("DirectDebit.ContiuneToDD"), "Continue");
				break;
			case 2:			
				verifyAndInputById(pageProperties.getProperty("DirectDebit.AccountHolderName"), "AccountHolderName",directDebit.getAccountHolderName());				
				int sortno1=Integer.parseInt(directDebit.getSortCode1());
				sortno1= sortno1-0;
				String sortno1str=Integer.toString(sortno1);
				verifyAndInputById_value(pageProperties.getProperty("DirectDebit.BranchSortCode1"), "BranchSortCode1",sortno1str);
				int sortno2=Integer.parseInt(directDebit.getSortCode1());
				sortno2= sortno2-0;
				String sortno2str=Integer.toString(sortno2);			
				verifyAndInputById_value(pageProperties.getProperty("DirectDebit.BranchSortCode2"), "BranchSortCode2",sortno2str);				
				int sortno3=Integer.parseInt(directDebit.getSortCode1());
				sortno3= sortno3-2;
				String sortno3str=Integer.toString(sortno3);				
				verifyAndInputById_value(pageProperties.getProperty("DirectDebit.BranchSortCode3"), "BranchSortCode3",sortno3str);
				verifyAndInputById(pageProperties.getProperty("DirectDebit.BankAccountNumber"), "BankAccountNumber",directDebit.getBankAccountnumber());
				verifyAndClick(pageProperties.getProperty("DirectDebit.ContiuneToDD"), "Continue");
				break;
			case 3:			
				verifyAndInputById(pageProperties.getProperty("DirectDebit.AccountHolderName"), "AccountHolderName",directDebit.getAccountHolderName());
				verifyAndInputById_value(pageProperties.getProperty("DirectDebit.BranchSortCode1"), "BranchSortCode1",directDebit.getSortCode1());
				verifyAndInputById_value(pageProperties.getProperty("DirectDebit.BranchSortCode2"), "BranchSortCode2",directDebit.getSortCode2());
				verifyAndInputById_value(pageProperties.getProperty("DirectDebit.BranchSortCode3"), "BranchSortCode3",directDebit.getSortCode3());
				int bankno1=Integer.parseInt(directDebit.getBankAccountnumber());
				System.out.println("bankno1"+bankno1);
				bankno1=bankno1-2;
				String banknostr1 = Integer.toString(bankno1);
				verifyAndInputById(pageProperties.getProperty("DirectDebit.BankAccountNumber"), "BankAccountNumber",banknostr1);
				verifyAndClick(pageProperties.getProperty("DirectDebit.ContiuneToDD"), "Continue");
				browser.wait(1000);				
				break;				
			}
				}		
		ErrorValidation();
	  }		
		public void ErrorValidation(){
		
		String displayedErrorMsg=browser.getTextByXpath(pageProperties.getProperty("DirectDebit.MaximumuserError"));
		System.out.println("displayedErrorMsg"+displayedErrorMsg);
        errorMsgComparisonSearch(displayedErrorMsg,SlingshotErrorMessages.DirectDebit_Morethannooftries);
			}
	
	public void errorMsgComparisonSearch(final String displayedErrorMsg, String expectedErrorMsg) 
	{
	 verifyErrorMessage(displayedErrorMsg, expectedErrorMsg);	
	}
	
	public void verifyErrorMessage(final String displayedErrorMsg, final String expectedErrorMsg) {
		if (displayedErrorMsg.trim().contains(expectedErrorMsg.trim())) {
			Report.updateTestLog("Error message validation. Expected message:" +expectedErrorMsg+" Actual message:"+ displayedErrorMsg, "PASS");
		} else {
			Report.updateTestLog("Error message validation. Expected message:" +expectedErrorMsg+" Actual message:"+ displayedErrorMsg, "FAIL");
		}
	}
	public void confirmationpagePodLinks()
	{
		verifyAndClickWithXpath(pageProperties.getProperty("DirectDebit.SubmitMeterreadPodLink"), "Submit meter read");
		verifyPageTitle("submit meter read");
		browser.browserBack();
		verifyAndClickWithXpath(pageProperties.getProperty("DirectDebit.ViewyouracctPodLink"), "View your Account");
		verifyPageTitle("Account overview");
		browser.browserBack();
		verifyAndClickWithXpath(pageProperties.getProperty("DirectDebit.ViewyourbillPodLink"), "View your Bill");
		verifyPageTitle("Search bill");
		browser.browserBack();
		//verifyAndClickWithXpath(pageProperties.getProperty("DirectDebit.EnergymadesimplePodLink"), "Energy made simple");
		//verifyPageTitle("Home - Energy Made Simple - British Gas Business");
		//browser.browserBack();
	}
	 public void verifyAudit_VariableDirectdebitsetupsuccess(DirectDebit directDebit){
			OnlineDBConnector dbFunctions = new OnlineDBConnector();
			String date=dbFunctions.DBsysdateDdmmyyhhmi();			
			String[] auditType3 = dbFunctions.getAuditEventTypeIdForMUMV(date,directDebit.getEmail(),"Variable Direct Debit Setup:success");
			System.out.println("auditType3[0]"+auditType3[0]);
			String data = dbFunctions.getAuditType(auditType3[0]);			
			Report.updateTestLog("Audit id is made in audit table as expected. Audit id: "+auditType3[0]+ " Audit event type is"+data,data.equalsIgnoreCase("DIRECT_DEBIT_SETUP_SUCCESS")?"PASS":"FAIL");
			}
	 public void verifyAudit_VariableDirectdebitAmenduccess(DirectDebit directDebit){
			OnlineDBConnector dbFunctions = new OnlineDBConnector();
			String date=dbFunctions.DBsysdateDdmmyyhhmi();			
			String[] auditType3 = dbFunctions.getAuditEventTypeIdForMUMV(date,directDebit.getEmail(),"Variable Direct Debit Amend:success");
			System.out.println("auditType3[0]"+auditType3[0]);
			String data = dbFunctions.getAuditType(auditType3[0]);			
			Report.updateTestLog("Audit id is made in audit table as expected. Audit id: "+auditType3[0]+ " Audit event type is"+data,data.equalsIgnoreCase("DIRECT_DEBIT_AMEND_SUCCESS")?"PASS":"FAIL");
			}
	 public void verifyAudit_FixedDirectDebitSetupSuccess(DirectDebit directDebit){
			OnlineDBConnector dbFunctions = new OnlineDBConnector();
			String date=dbFunctions.DBsysdateDdmmyyhhmi();			
			String[] auditType3 = dbFunctions.getAuditEventTypeIdForMUMV(date,directDebit.getEmail(),"Fixed Direct Debit Setup::success");
			System.out.println("auditType3[0]"+auditType3[0]);
			String data = dbFunctions.getAuditType(auditType3[0]);			
			Report.updateTestLog("Audit id is made in audit table as expected. Audit id: "+auditType3[0]+ " Audit event type is"+data,data.equalsIgnoreCase("BGBUSINESS_FIXED_DD_SET_UP_SUCCESS")?"PASS":"FAIL");
			}
	 public void verifyAudit_AmendFixedDirectDebitSetupSuccess(DirectDebit directDebit){
			OnlineDBConnector dbFunctions = new OnlineDBConnector();
			String date=dbFunctions.DBsysdateDdmmyyhhmi();			
			String[] auditType3 = dbFunctions.getAuditEventTypeIdForMUMV(date,directDebit.getEmail(),"Fixed Direct Debit Amend:success:");
			System.out.println("auditType3[0]"+auditType3[0]);
			String data = dbFunctions.getAuditType(auditType3[0]);			
			Report.updateTestLog("Audit id is made in audit table as expected. Audit id: "+auditType3[0]+ " Audit event type is"+data,data.equalsIgnoreCase("BGBUSINESS_FIXED_DD_AMEND_SUCCESS")?"PASS":"FAIL");
			}
	 public void verifyAudit_Directdebitemailsentstatus(DirectDebit directDebit){
			OnlineDBConnector dbFunctions = new OnlineDBConnector();
			String date=dbFunctions.DBsysdateDdmmyyhhmi();			
			String[] auditType2 = dbFunctions.getAuditEventTypeIdForMUMV(date,directDebit.getEmail(),"Direct Debit Email Sent Status::success");
			System.out.println("auditType3[0]"+auditType2[0]);
			String data = dbFunctions.getAuditType(auditType2[0]);			
			Report.updateTestLog("Audit id is made in audit table as expected. Audit id: "+auditType2[0]+ " Audit event type is"+data,data.equalsIgnoreCase("DIRECT_DEBIT_EMAIL_SENT_STATUS")?"PASS":"FAIL");
			}
	
	public void online_Thankyouemail_Auditentry()
	{
		 System.out.println(" i am here in table"); 
		 browser.wait(4000);
		 String numberOfTransactions= browser.getTextByXpath(pageProperties.getProperty("DirectDebit.Thankyouemail"));
		 System.out.println(browser.getTextByXpath(pageProperties.getProperty("DirectDebit.Thankyouemail")));
		 System.out.println("rowcount"+numberOfTransactions);
		 String[] totalTransaction = numberOfTransactions.split("of");         
		 totalTransaction = totalTransaction[1].split(" ");
		  String numberOfTransactionsInAuditTable = totalTransaction[1];	        
	}
	

	public void youraccountbreadcrumb()
	{
		verifyAndClickWithXpath(pageProperties.getProperty("DirectDebit.youraccountbreadcrumblink"), "your account");
		verifyPageTitle("Account overview");
		
	}
	public void setupdirectdebitbreadcrumb()
	{
		verifyAndClickWithXpath(pageProperties.getProperty("DirectDebit.setupadirectdebitbreadcrumblink"), "Setup a direct debit");
		verifyPageTitle("setup direct debit");
	}
	
	public void fixeddirectdebitebreadcrumb()
	{
		verifyAndClickWithXpath(pageProperties.getProperty("DirectDebit.Fixeddirectdebitbreadcrumblink"), "Fixed direct debit");
		verifyPageTitle("Fixed direct debit");
	}
	public void SetupVariablebreadCrumblink()
	{
		youraccountbreadcrumb();
		browser.browserBack();
		setupdirectdebitbreadcrumb();
		browser.browserBack();
		fixeddirectdebitebreadcrumb();
		browser.browserBack();
		
	}
	
	public void Breadcrumbnavigationlink()
	
	
	{
		browser.wait(5000);
		verifyAndClickWithXpath(pageProperties.getProperty("DirectDebit.BackToYourAccount"), "Back to your account");
		verifyPageTitle("Account overview");
		browser.browserBack();
		verifyAndClickWithXpath(pageProperties.getProperty("DirectDebit.setupadirectdebitbreadcrumblink"), "Setup a direct debit");
		verifyPageTitle("Set up a Direct Debit");
		browser.browserBack();
		verifyAndClickWithXpath(pageProperties.getProperty("DirectDebit.variabledirectdebitbreadcrumblink"), "Variable direct debit");
		verifyPageTitle("Variable Direct Debit ");
		browser.browserBack();
		
	}
	public void AmendVariableBreadCrumb()
	{
		verifyAndClickWithXpath(pageProperties.getProperty("DirectDebit.youraccountbreadcrumblink"), "your account");
		verifyPageTitle("Account overview");
		browser.browserBack();
		verifyAndClickWithXpath(pageProperties.getProperty("DirectDebit.Amendvariabledirectdebitbreadcrumblink"), "Amend variable direct debit");
		verifyPageTitle("Amend Variable Direct Debit");
		browser.browserBack();
	}
	public void AmendFixedBreadCrumb()
	{
		verifyAndClickWithXpath(pageProperties.getProperty("DirectDebit.youraccountbreadcrumblink"), "your account");
		verifyPageTitle("Account overview");
		/*browser.browserBack();*/
		/*verifyAndClickWithXpath(pageProperties.getProperty("DirectDebit.AmendFixeddirectdebitbreadcrumblink"), "Amend variable direct debit");
		verifyPageTitle("Amend Variable Direct Debit");
		browser.browserBack();*/
	}
	public void ManageAccount(UserProfile userProfile)
	{
		verifyAndClickWithXpath(pageProperties.getProperty("DirectDebit.ManageAccountLinkWithAccNo").replace("ACCOUNTNUMBER", userProfile.getAccNumber()), "Manage account link");
	}
	public void AmendDirectDebit()
	{
		verifyAndClickWithXpath(pageProperties.getProperty("DirectDebit.AmendFixedDirectDebit"), "Amend Fixed Direct Debit");
	}
	public void AmendvariableDirectDebit()
	{
		verifyAndClickWithXpath(pageProperties.getProperty("DirectDebit.AmendFixedDirectDebit"), "Amend Fixed Direct Debit");
	}
	public void ErrorValidationAmendwithin28days()
	{
		verifyIsTextPresent("We are sorry that we are not able to complete your request");
		 String jqueryToCloseOverlay=("$('a.ui-dialog-titlebar-close').trigger('click');");
			browser.executeScript(jqueryToCloseOverlay);
			browser.wait(getWaitTime());
			Report.updateTestLog("Overlay Closed", "PASS");
	}
	
	public void ValidateCollectiveuser()
	{
		if (browser.isTextPresent("Set up Direct Debit") || browser.isTextPresent("Manage Direct Debit") )
		{
            Report.updateTestLog("Direct Debit Link is not present in the application for the collective User", "PASS");
       
        } else {
            Report.updateTestLog("Direct Debit Link is  present in the application for the collective User", "FAIL");
        }
	}
	public void validatePDvWarrentusercustomer()
	{
		if (browser.isTextPresent("Set up Direct Debit") || browser.isTextPresent("Manage Direct Debit") )
		{
            Report.updateTestLog("Direct Debit Link is not present in the application for the PDV warrent stage Customer", "PASS");
       
        } else {
            Report.updateTestLog("Direct Debit Link is not present in the application for the PDV warrent stage Customer", "FAIL");
        }
	}
	public void AmendFixedDDLandingPagebackandcancel()
	{
		verifyAndClickWithXpath(pageProperties.getProperty("DirectDebit.AmendFixedDirectDebitback"), "Back");
		browser.browserBack();
		verifyAndClickWithXpath(pageProperties.getProperty("DirectDebit.AmendFixedDirectDebitcancel"), "cancel");
	}
	public void SetupFixedDDLandingPagebackandcancel()
	{
		verifyAndClickWithXpath(pageProperties.getProperty("DirectDebit.AmendFixedDirectDebitback"), "Back");
		browser.browserBack();
		verifyAndClickWithXpath(pageProperties.getProperty("DirectDebit.AmendFixedDirectDebitcancel"), "cancel");
		browser.browserBack();
	}
	public void AmendFixedDDLandingPageBackandcancel()
	{
		verifyAndClickWithXpath(pageProperties.getProperty("DirectDebit.AmendFixedDirectDebitpageback"), "Back");
		browser.browserBack();
		verifyAndClickWithXpath(pageProperties.getProperty("DirectDebit.AmendFixedDirectDebitpagecancel"), "cancel");
	}
	public void AmendFixedDDEditdetailsPage(DirectDebit directDebit)
	{
		verifyAndClickWithXpath(pageProperties.getProperty("DirectDebit.AmendFixedDirectDebiteditdetails1"), "Edit details 1");	
		verifyAndInputById(pageProperties.getProperty("DirectDebit.AccountHolderName"), "AccountHolderName",directDebit.getAccountHolderName());
//		String sortcode1="$('#sortCode1').val("+directDebit.getSortCode1()+")";
//		sortcode1.replace("values", directDebit.getSortCode1());
		verifyAndClickWithXpath(pageProperties.getProperty("DirectDebit.AmendFixedDirectDebiteditdetails2"), "Edit details 2");
		verifyAndInputById(pageProperties.getProperty("DirectDebit.BranchSortCode1"), "BranchSortCode1",directDebit.getSortCode1());
		verifyAndInputById(pageProperties.getProperty("DirectDebit.BranchSortCode2"), "BranchSortCode2",directDebit.getSortCode2());
		verifyAndInputById(pageProperties.getProperty("DirectDebit.BranchSortCode3"), "BranchSortCode3",directDebit.getSortCode3());
		verifyAndInputById(pageProperties.getProperty("DirectDebit.BankAccountNumber"), "BankAccountNumber",directDebit.getBankAccountnumber());
		verifyAndClick(pageProperties.getProperty("DirectDebit.ContiuneToDD"), "Continue");		
	
	}
	
	public void AmendsamebankDDerrorvalidation()
	{
		verifyIsTextPresent("Customer is not allowed to amend DD with same bank details");
	}
	
	
}
