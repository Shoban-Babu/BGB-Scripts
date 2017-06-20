package bg.framework.app.functional.action.selfServe;

import bg.framework.app.functional.page.selfServe.HomeMovePage;
import bg.framework.app.functional.entities.HomeMove;
import bg.framework.app.functional.entities.UserProfile;

public class HomeMoveAction {
	
	
	public HomeMoveAction navigateToStartMovingHomeForAnonymous()
    {
    	HomeMovePage startMovingIn =new HomeMovePage();
    	startMovingIn.navigateToStartMovingHomeForAnonymous();
    	return this;
    }
	
	public HomeMoveAction enterMoveOutDetails(HomeMove homeMove,int day)
    {
    	HomeMovePage moveOutDetails =new HomeMovePage();
    	moveOutDetails.enterMoveOutDetails(homeMove,day);
    	return this;
    }
	
	public HomeMoveAction enterMoveOutDetailsForOAM(HomeMove homeMove,int day)
    {
    	HomeMovePage moveOutDetailsForOAM=new HomeMovePage();
    	moveOutDetailsForOAM.enterMoveOutDetailsForOAM(homeMove,day);
    	return this;
    }
	
    public HomeMoveAction navigateToCloseAccountsPage()
    {
    	HomeMovePage closeAllAccounts=new HomeMovePage();
    	closeAllAccounts.navigateToCloseAccountsPage();
    	return this;
    }
    
    public HomeMoveAction navigateToTransferAccountsPage()
    {
    	HomeMovePage transferAllAccounts=new HomeMovePage();
    	transferAllAccounts.navigateToTransferAccountsPage();
    	return this;
    }
	
    public HomeMoveAction selectCloseAllAccounts()
    {
    	HomeMovePage moveOutDetailsForOAM=new HomeMovePage();
    	moveOutDetailsForOAM.selectCloseAllAccounts();
    	return this;
    }
    
    public HomeMoveAction selectTransferAllAccounts()
    {
    	HomeMovePage transferAll=new HomeMovePage();
    	transferAll.selectTransferAllAccounts();
    	return this;
    }
	
    public HomeMoveAction enterMeterReading(String fuelType)
	{
		HomeMovePage enterMeterReading=new HomeMovePage();
		enterMeterReading.enterMeterReading(fuelType);
		return this;
	}
    
    public HomeMoveAction selectRemindMeLaterForGasMeterRead()
    {
    	HomeMovePage remindLater=new HomeMovePage();
    	remindLater.selectRemindMeLaterForGasMeterRead();
    	return this;
    }
    
    public HomeMoveAction selectRemindMeLaterForElecMeterRead()
    {
    	HomeMovePage remindLaterElec=new HomeMovePage();
    	remindLaterElec.selectRemindMeLaterForElecMeterRead();
    	return this;
    }
    
    public HomeMoveAction navigateToForwardingAddressEntry()
    {
    	HomeMovePage continueToForwardingAddress=new HomeMovePage();
    	continueToForwardingAddress.navigateToForwardingAddressEntry();
    	return this;
    }
	
    public HomeMoveAction enterForwardingAddressDetails(HomeMove homeMove)
	{
		HomeMovePage enterForwardingAddress=new HomeMovePage();
		enterForwardingAddress.enterForwardingAddressDetails(homeMove);
    	return this;
	}
	
    public HomeMoveAction continueToAdditionalProductsPage()
	{
		
		HomeMovePage continueToUpgrade=new HomeMovePage();
		continueToUpgrade.continueToAdditionalProductsPage();
		return this;
	}
	
	public HomeMoveAction selectMarketingConsent()
	{
		HomeMovePage marketingConsent=new HomeMovePage();
		marketingConsent.selectMarketingConsent();
    	return this;
	}
	
	public HomeMoveAction navigateToSubmitMoveOut()
	{
		HomeMovePage submit=new HomeMovePage();
		submit.navigateToSubmitMoveOut();
    	return this;
	}
	
	public HomeMoveAction navigateToMovingInOnly()
    {
    	HomeMovePage movingInOnly =new HomeMovePage();
    	movingInOnly.navigateToMovingInOnly();
    	return this;
    }
	
	public HomeMoveAction navigateToMovingInForAnonymousCustomer()
    {
    	HomeMovePage anonymousMovingIn =new HomeMovePage();
    	anonymousMovingIn.navigateToMovingInForAnonymousCustomer();
    	return this;
    }
	
	public HomeMoveAction enterPersonalDetailsForAnonymousCustomer(HomeMove homeMove)
    {
    	HomeMovePage personalDetails =new HomeMovePage();
    	personalDetails.enterPersonalDetailsForAnonymousCustomer(homeMove);
    	return this;
    }
	
	public HomeMoveAction unselectContactExistingSuppliers()
    {
    	HomeMovePage unSelectContactExistingSuppliers =new HomeMovePage();
    	unSelectContactExistingSuppliers.unselectContactExistingSuppliers();
    	return this;
    }
	
	public HomeMoveAction selectProductsMovingInBundle()
    {
    	HomeMovePage movingInBundle =new HomeMovePage();
    	movingInBundle.selectProductsMovingInBundle();
    	return this;
    }
	
	public HomeMoveAction selectProductsCreateBundle()
    {
    	HomeMovePage createBundle =new HomeMovePage();
    	createBundle.selectProductsCreateBundle();
    	return this;
    }
	
	public HomeMoveAction enterMovingInDetails(HomeMove homeMove,int day)
    {
    	HomeMovePage movingInDetails =new HomeMovePage();
    	movingInDetails.enterMovingInDetails(homeMove,day);
    	return this;
    }
	
	public HomeMoveAction errorMessageForMoveInDateOutOfLimit()
	{
		HomeMovePage moveInDateOutOfLimit=new HomeMovePage();
		moveInDateOutOfLimit.errorMessageForMoveInDateOutOfLimit();
	return this;
	}
	
	public HomeMoveAction selectMoveInAddress(HomeMove homeMove)
	{
		HomeMovePage selectMoveInAddress=new HomeMovePage();
		selectMoveInAddress.selectMoveInAddress(homeMove);
		return this;
	}
	
	public HomeMoveAction selectMoveInAddressWithDifferentBillAddress(HomeMove homeMove)
	{
		HomeMovePage differentBillAddress=new HomeMovePage();
		differentBillAddress.selectMoveInAddressWithDifferentBillAddress(homeMove);
		return this;
	}
	
	public HomeMoveAction enterMeterReadingForPostMoveCustomer(HomeMove homeMove)
	{
		HomeMovePage enterMeterReading=new HomeMovePage();
		enterMeterReading.enterMeterReadingForPostMoveCustomer(homeMove);
		return this;
	}
	
	public HomeMoveAction homeMoveaddressNotListed(HomeMove homeMove)
	{
		HomeMovePage homeMoveAddressNotListed =new HomeMovePage();
		homeMoveAddressNotListed.homeMoveaddressNotListed(homeMove);
    	return this;
	}
	
	public HomeMoveAction previousAddressNotListed(HomeMove homeMove,int noPrevAddress)
	{
		HomeMovePage previousAddressNotListed =new HomeMovePage();
		previousAddressNotListed.previousAddressNotListed(homeMove,noPrevAddress);
    	return this;
	}
	
	public HomeMoveAction sendBillToAntherAddress(HomeMove homeMove)
    {
    	HomeMovePage sendBillToAnontherAddress =new HomeMovePage();
    	sendBillToAnontherAddress.sendBillToAntherAddress(homeMove);
    	return this;
    }
	
	public HomeMoveAction enterPreviousAddressHistory(HomeMove homeMove)
    {
    	HomeMovePage addressHistory =new HomeMovePage();
    	addressHistory.enterPreviousAddressHistory(homeMove);
    	return this;
    }
	
	
	public HomeMoveAction selectNumberPreviousAddresses(HomeMove homeMove,int noaddress)
	{	
		HomeMovePage selectPreviousAddress=new HomeMovePage();
		selectPreviousAddress.selectNumberPreviousAddresses(homeMove,noaddress);
		return this;
	}
	
	public HomeMoveAction selectPaymentTypeDirectDebit()
	{
		HomeMovePage paymentTypeDirectDebit= new HomeMovePage();
		paymentTypeDirectDebit.selectPaymentTypeDirectDebit();
		return this;
	}
	
	public HomeMoveAction selectDirectDebitAndunselectPaperLessBilling()
	{
		HomeMovePage paperLessBilling= new HomeMovePage();
		paperLessBilling.selectDirectDebitAndunselectPaperLessBilling();
		return this;
	}
	
	public HomeMoveAction selectPaymentTypeDifferentPayment()
	{
		HomeMovePage differentPaymentOption= new HomeMovePage();
		differentPaymentOption.selectPaymentTypeDifferentPayment();
		return this;
	}
	
	public HomeMoveAction paymentOptionsForGasElectricityAndHomeservices()
	{
		HomeMovePage gasElectricityHomeservices =new HomeMovePage();
		gasElectricityHomeservices.paymentOptionsForGasElectricityAndHomeservices();
		return this;
	}
	
	public HomeMoveAction paymentOptionsForDualAndHomeServices()
	{
		HomeMovePage dualAndHomeServices =new HomeMovePage();
		dualAndHomeServices.paymentOptionsForDualAndHomeServices();
		return this;
	}
	
	public HomeMoveAction enterBankDetailsForPayment(HomeMove homeMove)
	{
		HomeMovePage paymentDetails= new HomeMovePage();
		paymentDetails.enterBankDetailsForPayment(homeMove);
		return this;
	}
	
	public HomeMoveAction bankAccountDetailsErrorValidation()
	{
		HomeMovePage bankAccountDetailsErrorValidation= new HomeMovePage();
		bankAccountDetailsErrorValidation.bankAccountDetailsErrorValidation();
		return this;
	}
	
	public HomeMoveAction enterAnnualCreditCardDetails(HomeMove homeMove)
	{
		HomeMovePage annualCreditCardDetails= new HomeMovePage();
		annualCreditCardDetails.enterAnnualCreditCardDetails(homeMove);
		return this;
	}
	
	public HomeMoveAction enterUpgradingDetails(int flexiVal)
	{
		HomeMovePage upgradeDetails= new HomeMovePage();
		upgradeDetails.enterUpgradingDetails(flexiVal);
	return this;
		
	}
	
	public HomeMoveAction changeMoveOutDate(int changeDate,HomeMove homeMove)
	{
		HomeMovePage changeDateTo=new HomeMovePage();
		changeDateTo.changeMoveOutDate(changeDate,homeMove);
		return this;
	}
	
	public AccountOverviewAction navigateToConfirmationPage()
	{
		HomeMovePage navigateToConfirmation= new HomeMovePage();
		navigateToConfirmation.navigateToConfirmationPage();
		return new AccountOverviewAction();
	}
	
	public HomeMoveAction navigateToHome()
	{
		HomeMovePage navigateToHome= new HomeMovePage();
		navigateToHome.navigateToHome();
		return this;
	}
	
	public HomeMoveAction addMissingAccountsDetails(HomeMove homeMove)
	{
		HomeMovePage addMissingAccounts= new HomeMovePage();
		addMissingAccounts.addMissingAccountsDetails(homeMove);
		return this;
	}
	
	public HomeMoveAction navigateToLandlordMovingHome()
	{
		HomeMovePage landlordMovingHome=new HomeMovePage();
		landlordMovingHome.navigateToLandlordMovingHome();
		return this;
	}
	
	public HomeMoveAction navigateToLandLordBusinessNature()
	{
		HomeMovePage landlordBusinessNature=new HomeMovePage();
		landlordBusinessNature.navigateToLandLordBusinessNature();
		return this;
	}
	
	public HomeMoveAction navigateToLettingAgentsBusinessNature()
	{
		HomeMovePage lettingAgentsBusinessNature=new HomeMovePage();
		lettingAgentsBusinessNature.navigateToLettingAgentsBusinessNature();
		return this;
	}
	
	
	public HomeMoveAction enterYourDetailsInLandLordMovingHome(HomeMove homeMove)
	{
		HomeMovePage landLordYourDetails=new HomeMovePage();
		landLordYourDetails.enterYourDetailsInLandLordMovingHome(homeMove);
		return this;
	}
	
	public HomeMoveAction enterMoveHomeDetailsForLandLord(String readingType,int day,HomeMove homeMove)
	{
		HomeMovePage moveDetails=new HomeMovePage();
		moveDetails.enterMoveHomeDetailsForLandLord(readingType,day,homeMove);
		return this;
	}
	public HomeMoveAction enterSingleAddAccountForLandLord(String readingType,int day,HomeMove homeMove)
	{
		HomeMovePage moveDetails=new HomeMovePage();
		moveDetails.enterSingleAddAccountForLandLord(readingType,day,homeMove);
		return this;
	}
	public HomeMoveAction enterDoubleAddAccountForLandLord(String readingType,int day,HomeMove homeMove)
	{
		HomeMovePage moveDetails=new HomeMovePage();
		moveDetails.enterDoubleAddAccountForLandLord(readingType,day,homeMove);
		return this;
	}
	public HomeMoveAction enterThreeAddAccountWithoutMOMIForLandLord(String readingType,int day,HomeMove homeMove)
	{
		HomeMovePage moveDetails=new HomeMovePage();
		moveDetails.enterThreeAddAccountWithoutMOMIForLandLord(readingType,day,homeMove);
		return this;
	}
	public HomeMoveAction enterDoubleAddAccountWithoutMOMIForLandLord(String readingType,int day,HomeMove homeMove)
	{
		HomeMovePage moveDetails=new HomeMovePage();
		moveDetails.enterDoubleAddAccountWithoutMOMIForLandLord(readingType,day,homeMove);
		return this;
	}
	public HomeMoveAction enterSingleAddAccountWithoutMOMIForLandLord(String readingType,int day,HomeMove homeMove)
	{
		HomeMovePage moveDetails=new HomeMovePage();
		moveDetails.enterSingleAddAccountWithoutMOMIForLandLord(readingType,day,homeMove);
		return this;
	}
	
	public HomeMoveAction enterSupplyAddressDetails(HomeMove homeMove)
	{
		HomeMovePage supplyAddress=new HomeMovePage();
		supplyAddress.enterSupplyAddressDetails(homeMove);
		return this;
	}
	
	public HomeMoveAction editDetailsForLandLordHomeMove(HomeMove homeMove,int moveOutDate,String detailsToEdit)
	{
		HomeMovePage editDetails=new HomeMovePage();
		editDetails.editDetailsForLandLordHomeMove(homeMove,moveOutDate,detailsToEdit);
		return this;
	}
	
	public  AccountOverviewAction homeMoveLandLordConfirmationPage()
	{
		HomeMovePage landLordConfirmation=new HomeMovePage();
		landLordConfirmation.homeMoveLandLordConfirmationPage();
		return new AccountOverviewAction();
	}
	public  void runBatch(String sysdate,String batchType,UserProfile userProfile)
	{
		HomeMovePage batchRun=new HomeMovePage();
		batchRun.runBatch(sysdate,batchType,userProfile);
	}
	public HomeMoveAction addAccountsToCloseForAnonymous(String accountTypeToClose,HomeMove homeMove)
	{
		HomeMovePage accountsToClose=new HomeMovePage();
		accountsToClose.addAccountsToCloseForAnonymous(accountTypeToClose,homeMove);
		return this;
	}
	public HomeMoveAction navigateToMeterRead()
	{
		HomeMovePage navigateToMeterRead=new HomeMovePage();
		navigateToMeterRead.navigateToMeterRead();
		return this;
	}
	public HomeMoveAction selectAccountsToTransferAnonymous(HomeMove homeMove)
	{
		HomeMovePage selectAccounts=new HomeMovePage();
		selectAccounts.selectAccountsToTransferAnonymous(homeMove);
		return this;
	}
	public HomeMoveAction verifyandClickMOAddAccountHolder()
	{
		HomeMovePage supplyAddress=new HomeMovePage();
		supplyAddress.verifyandClickMOAddAccountHolder();
		return this;
	}
	public HomeMoveAction verifyandClickMIAddAccountHolder()
	{
		HomeMovePage supplyAddress=new HomeMovePage();
		supplyAddress.verifyandClickMIAddAccountHolder();
		return this;
	}
	public HomeMoveAction enterYourDetailsMOAccountOne(HomeMove homeMove)
	{
		HomeMovePage supplyAddress=new HomeMovePage();
		supplyAddress.enterYourDetailsMOAccountOne(homeMove);
		return this;
	}
	public HomeMoveAction enterYourDetailsMOAccountTwo(HomeMove homeMove)
	{
		HomeMovePage supplyAddress=new HomeMovePage();
		supplyAddress.enterYourDetailsMOAccountTwo(homeMove);
		return this;
	}
	public HomeMoveAction enterYourDetailsMIAccountThree(HomeMove homeMove)
	{
		HomeMovePage supplyAddress=new HomeMovePage();
		supplyAddress.enterYourDetailsMIAccountThree(homeMove);
		return this;
	}
	public HomeMoveAction enterYourDetailsMIAccountOne(HomeMove homeMove)
	{
		HomeMovePage supplyAddress=new HomeMovePage();
		supplyAddress.enterYourDetailsMIAccountOne(homeMove);
		return this;
	}
	public HomeMoveAction enterYourDetailsMIAccountTwo(HomeMove homeMove)
	{
		HomeMovePage supplyAddress=new HomeMovePage();
		supplyAddress.enterYourDetailsMIAccountTwo(homeMove);
		return this;
	}
	public HomeMoveAction enterYourDetailsMOAccountThree(HomeMove homeMove)
	{
		HomeMovePage supplyAddress=new HomeMovePage();
		supplyAddress.enterYourDetailsMOAccountThree(homeMove);
		return this;
	}
}
