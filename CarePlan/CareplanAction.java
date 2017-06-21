package bg.framework.app.functional.action.Slingshot;

import bg.framework.app.functional.entities.UserProfile;
import bg.framework.app.functional.entities.Slinshot.CarePlanProfile;
import bg.framework.app.functional.page.Slingshot.CarePlanPage;

public class CareplanAction {
	
	public CareplanAction contactDetails (UserProfile userProfile, String BusinessCustomer) {
		CarePlanPage careplanpage=new CarePlanPage();
		careplanpage.contactDetails(userProfile, BusinessCustomer);
		return this;	
		}
	public CareplanAction contactDetailsNew (UserProfile userProfile) {
		CarePlanPage careplanpage=new CarePlanPage();
		careplanpage.contactDetailsNew(userProfile);
		return this;	
		}
	public CareplanAction businessDetails (UserProfile userProfile, String GasCustomer) {
		CarePlanPage careplanpage=new CarePlanPage();
		careplanpage.businessDetails(userProfile, GasCustomer);
		return this;	
		}
	public CareplanAction landlordCheck (String landlord) {
		CarePlanPage careplanpage=new CarePlanPage();
		careplanpage.landlordCheck(landlord);
		return this;	
		}
	public CareplanAction SelectlandlordCheckNew (CarePlanProfile carePlanProfile1) {
		CarePlanPage careplanpage=new CarePlanPage();
		careplanpage.landlordCheckNew(carePlanProfile1);
		return this;	
		}

	public CareplanAction postcodeValidation () {
		CarePlanPage careplanpage=new CarePlanPage();
		careplanpage.postcodeValidation();
		return this;	
		}
	public CareplanAction yesIHaveGasSupply (UserProfile userProfile) {
		CarePlanPage careplanpage=new CarePlanPage();
		careplanpage.yesIHaveGasSupply(userProfile);
		return this;	
		}
	public CareplanAction yesIHaveGasSupplyNew (UserProfile userProfile) {
		CarePlanPage careplanpage=new CarePlanPage();
		careplanpage.yesIHaveGasSupplyNew(userProfile);
		return this;	
		}
	public CareplanAction postcodeValidationNonMainLand () {
		CarePlanPage careplanpage=new CarePlanPage();
		careplanpage.postcodeValidationNonMainLand();
		return this;	
		}
	public CareplanAction navigateToCareplan () {
		CarePlanPage careplanpage=new CarePlanPage();
		careplanpage.navigateToCareplan();
		return this;	
		}
	
	public CareplanAction pricingVerification(CarePlanProfile carePlanProfile,UserProfile userProfile,String landlord){
		CarePlanPage carepage = new CarePlanPage(); 
		carepage.pricingVerification(carePlanProfile,userProfile,landlord);
		return this;
	}
	public CareplanAction pricingVerificationNew(CarePlanProfile carePlanProfile,UserProfile userProfile,String landlord){
		CarePlanPage carepage = new CarePlanPage(); 
		carepage.pricingVerificationNew(carePlanProfile,userProfile,landlord);
		return this;
	}
	public CareplanAction pricingVerificationforMultipleproducts(CarePlanProfile carePlanProfile){
		CarePlanPage carepage = new CarePlanPage(); 
		carepage.pricingVerificationforMultipleproducts(carePlanProfile);
		return this;
	}
    
	public CareplanAction pricingVerificationForLGSRandPGSR(CarePlanProfile carePlanProfile,UserProfile userProfile,String Landlord){
		CarePlanPage carepage = new CarePlanPage(); 
		carepage.pricingVerificationForLGSRandPGSR(carePlanProfile,userProfile,Landlord);
		return this;
	}
	public CareplanAction addAnotherAppliance () {
		CarePlanPage careplanpage=new CarePlanPage();
		careplanpage.addAnotherAppliance();
		return this;	
		}
	public CareplanAction continueToOrderNew () {
		CarePlanPage careplanpage=new CarePlanPage();
		careplanpage.continueToOrderNew();
		return this;	
		}
	public CareplanAction bespokeVerification(CarePlanProfile carePlanProfile,UserProfile userProfile,String landlord){
		CarePlanPage carepage = new CarePlanPage(); 
		carepage.bespokeVerification(carePlanProfile,userProfile,landlord);
		return this;
	}
	public CareplanAction bespokeVerificationNew(CarePlanProfile carePlanProfile,UserProfile userProfile){
		CarePlanPage carepage = new CarePlanPage(); 
		carepage.bespokeVerificationNew(carePlanProfile,userProfile);
		return this;
	}
	public CareplanAction ConfirmationPage (UserProfile userProfile) {
		CarePlanPage careplanpage=new CarePlanPage();
		careplanpage.ConfirmationPage(userProfile);
		return this;	
		}
	public CareplanAction ConfirmationPageNew (UserProfile userProfile) {
		CarePlanPage careplanpage=new CarePlanPage();
		careplanpage.ConfirmationPageNew(userProfile);
		return this;	
		}
	public CareplanAction addAnotherSite () {
		CarePlanPage careplanpage=new CarePlanPage();
		careplanpage.addAnotherSite();
		return this;	
		}
	public CareplanAction verifyingPricingForDifferentQuatityForLGSRandPGSR(CarePlanProfile carePlanProfile,CarePlanProfile carePlanProfile1){
		CarePlanPage carepage = new CarePlanPage(); 
		carepage.verifyingPricingForDifferentQuatityForLGSRandPGSR(carePlanProfile,carePlanProfile1);
		return this;
	}
	public CareplanAction verifyingPricingForDifferentQuatityForLGSRandPGSRNew(CarePlanProfile carePlanProfile,CarePlanProfile carePlanProfile1){
		CarePlanPage carepage = new CarePlanPage(); 
		carepage.verifyingPricingForDifferentQuatityForLGSRandPGSRNew(carePlanProfile,carePlanProfile1);
		return this;
	}
	
	
	
	public CareplanAction selectingCustomerDetails(CarePlanProfile carePlanProfile,UserProfile userProfile){
		CarePlanPage carepage = new CarePlanPage(); 
		carepage.selectingCustomerDetails(carePlanProfile,userProfile);
		return this;
	}
	
	public CareplanAction selectingLGSRorPGSR(CarePlanProfile carePlanProfile){
		CarePlanPage carepage = new CarePlanPage(); 
		carepage.selectingLGSRorPGSR(carePlanProfile);
		return this;
	}
	
	public CareplanAction appliancesPricing(CarePlanProfile carePlanProfile){
		CarePlanPage carepage = new CarePlanPage(); 
		carepage.appliancesPricing(carePlanProfile);
		return this;
	}
	public CareplanAction appliancesPricingNew(CarePlanProfile carePlanProfile){
		CarePlanPage carepage = new CarePlanPage(); 
		carepage.appliancesPricingNew(carePlanProfile);
		return this;
	}
	
	public CareplanAction appliancesPricingForLGSRandPGSRMultipleProducts(CarePlanProfile carePlanProfile){
		CarePlanPage carepage = new CarePlanPage(); 
		carepage.appliancesPricingForLGSRandPGSRMultipleProducts(carePlanProfile);
		return this;
	}
	
	public CareplanAction pricingVerificationForLGSRAndPGSR(CarePlanProfile carePlanProfile){
		CarePlanPage carepage = new CarePlanPage(); 
		carepage.pricingVerificationForLGSRAndPGSR(carePlanProfile);
		return this;
	}
	public CareplanAction pricingVerificationForLGSRAndPGSRNew(CarePlanProfile carePlanProfile){
		CarePlanPage carepage = new CarePlanPage(); 
		carepage.pricingVerificationForLGSRAndPGSRNew(carePlanProfile);
		return this;
	}
	
	public CareplanAction validtePriceforMultipleproductsWithLGSRorPGSR(){
		CarePlanPage carepage = new CarePlanPage(); 
		carepage.validtePriceforMultipleproductsWithLGSRorPGSR();
		return this;
	}
}
	

