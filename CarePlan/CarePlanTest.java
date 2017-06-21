package bg.framework.app.functional.test.Slingshot;

import static bg.framework.app.functional.entities.FunctionalCategory.BGBRegistration;
import static bg.framework.app.functional.entities.FunctionalCategory.Slingshot;
import static bg.framework.app.functional.entities.FunctionalCategory.Smoke;

import org.testng.annotations.Test;

import bg.framework.app.functional.action.Slingshot.BgbRegistrationAction;
import bg.framework.app.functional.action.Slingshot.CareplanAction;
import bg.framework.app.functional.action.common.LoginAction;
import bg.framework.app.functional.entities.UserProfile;
import bg.framework.app.functional.entities.Slinshot.CarePlanProfile;
import bg.framework.app.functional.test.common.TestBase;
import bg.framework.app.functional.util.Report;
import bg.framework.app.functional.util.TestDataHelper;

public class CarePlanTest extends TestBase {

	public static String BusinessCustomer;
	public static String GasCustomer;
	public static String landlord;

	@SuppressWarnings("unchecked")
	@Test(groups ={Slingshot,Smoke,BGBRegistration})

	public void verifyDemo() throws Exception {
		Report.createTestLogHeader("CarePlan", "To Verify user perform CarePlan Quote");
		UserProfile userProfile = new TestDataHelper().getUserProfile("BgbCareplan");
		new CareplanAction()
		.navigateToCareplan()
		.contactDetails(userProfile, "yes")
		.businessDetails(userProfile, "yes")
		.landlordCheck("yes");
	}
	
////////////////////////////////////////////////////////////////////////////post code validation/////////////////////////////////////////////////////////////////	
	@SuppressWarnings("unchecked")
	@Test(groups ={Slingshot,Smoke,BGBRegistration})
	public void postcodeValidation() throws Exception {
		Report.createTestLogHeader("CarePlan", "To Verify user perform CarePlan Quote");
		UserProfile userProfile = new TestDataHelper().getUserProfile("BgbCareplan");
		new CareplanAction()
		.navigateToCareplan()
		.contactDetails(userProfile, "yes")
		.postcodeValidation();
		//.landlordCheck(userProfile, "yes");
	}
//////////////////////////////////////////////////////////////////////////post code validation non coverage area////////////////////////////////////////////////////////
	@SuppressWarnings("unchecked")
	@Test(groups ={Slingshot,Smoke,BGBRegistration})
	
	public void postcodeValidationNonCovergeError() throws Exception {
		Report.createTestLogHeader("CarePlan", "To Verify user perform CarePlan Quote");
		UserProfile userProfile = new TestDataHelper().getUserProfile("BgbCareplan");
		new CareplanAction()
		.navigateToCareplan()
		.contactDetails(userProfile, "yes")
		.postcodeValidationNonMainLand();
		//.landlordCheck("yes");
	}
//////////////////////////////////////////////////////////////////////////////pricing verification london////////////////////////////////////////////////////////////////////
	@SuppressWarnings("unchecked")
	@Test(groups ={Slingshot,Smoke,BGBRegistration})
	public void PricingVerificationForLondon() throws Exception {

		Report.createTestLogHeader("CarePlan", "To Verify Pricing for London");
		try {
			for (String x : productsLondon()) {
				CarePlanProfile carePlanProfile = new TestDataHelper().getCarePlanProfile(x);
				UserProfile userProfile = new TestDataHelper().getUserProfile("BgbCareplan");
				Report.updateTestLog(" ***************** Care plan combination : " + x  + " ***************** ", "PASS");
				new CareplanAction()
				.navigateToCareplan()
				//.pricingVerification(carePlanProfile,userProfile,"yes");
				.pricingVerificationNew(carePlanProfile,userProfile,"yes");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

///////////////////////////////////////////////////////////////////////////////pricing verification rest of UK//////////////////////////////////////////////////////////////////////////////
	@SuppressWarnings("unchecked")
	@Test(groups ={Slingshot,Smoke,BGBRegistration})
	public void PricingVerificationForRestOfUK() throws Exception {

		Report.createTestLogHeader("CarePlan", "To Verify Pricing for Rest of UK");
		try {
			for (String x : productsRestOfUK()) {
				CarePlanProfile carePlanProfile = new TestDataHelper().getCarePlanProfile(x);
				UserProfile userProfile = new TestDataHelper().getUserProfile("BgbCareplanUk");
				Report.updateTestLog(" ***************** Care plan combination : " + x  + " ***************** ", "PASS");
				new CareplanAction()
				.navigateToCareplan()
				//.pricingVerification(carePlanProfile,userProfile,"No");
				.pricingVerificationNew(carePlanProfile,userProfile,"Yes");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
////////////////////////////////////////////////////////////////////////LGSRPricingVerificationForLondon//////////////////////////////////////////////////////////////////////////////////////////////////	

	@SuppressWarnings("unchecked")
	@Test(groups ={Slingshot,Smoke,BGBRegistration})
	public void LGSRPricingVerificationForLondon() throws Exception {

		Report.createTestLogHeader("CarePlan", "To Verify LGSR Pricing for London");
		try {
			for (String x : productsLondon1()) {
				CarePlanProfile carePlanProfile = new TestDataHelper().getCarePlanProfile(x);
				UserProfile userProfile = new TestDataHelper().getUserProfile("BgbCareplan");
				Report.updateTestLog(" ***************** Care plan combination : " + x  + " ***************** ", "PASS");
				new CareplanAction()
				.navigateToCareplan()
				.pricingVerificationNew(carePlanProfile,userProfile,"Yes");
				//.pricingVerificationForLGSRandPGSR(carePlanProfile,userProfile,"yes");
				for (String y : productsLondonLGSR()){
					CarePlanProfile carePlanProfile1 = new TestDataHelper().getCarePlanProfile(y);
					Report.updateTestLog(" ***************** Care plan combination : " + y + " ***************** ", "PASS");
					new CareplanAction()
					//.verifyingPricingForDifferentQuatityForLGSRandPGSR(carePlanProfile,carePlanProfile1);
					.verifyingPricingForDifferentQuatityForLGSRandPGSRNew(carePlanProfile,carePlanProfile1);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
////////////////////////////////////////////////////////////////////////LGSRPricingVerificationForRestOfUK//////////////////////////////////////////////////////////////////////////////

	@SuppressWarnings("unchecked")
	@Test(groups ={Slingshot,Smoke,BGBRegistration})
	public void LGSRPricingVerificationForRestOfUK() throws Exception {

		Report.createTestLogHeader("CarePlan", "To Verify LGSR Pricing for Rest of UK");
		try {
			for (String x : productsRestOfUK1()) {
				CarePlanProfile carePlanProfile = new TestDataHelper().getCarePlanProfile(x);
				UserProfile userProfile = new TestDataHelper().getUserProfile("BgbCareplanUk");
				Report.updateTestLog(" ***************** Care plan combination : " + x  + " ***************** ", "PASS");
				new CareplanAction()
				.navigateToCareplan()
				//.pricingVerificationForLGSRandPGSR(carePlanProfile,userProfile,"yes");
				.pricingVerificationNew(carePlanProfile,userProfile,"Yes");
				for (String y : productsUKLGSR()){
					CarePlanProfile carePlanProfile1 = new TestDataHelper().getCarePlanProfile(y);
					Report.updateTestLog(" ***************** Care plan combination : " + y + " ***************** ", "PASS");
					new CareplanAction()
					//.verifyingPricingForDifferentQuatityForLGSRandPGSR(carePlanProfile,carePlanProfile1);
					.verifyingPricingForDifferentQuatityForLGSRandPGSRNew(carePlanProfile,carePlanProfile1);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

//////////////////////////////////////////////////////////////////PGSRPricingVerificationForLondon/////////////////////////////////////////////////////////////////////
	@SuppressWarnings("unchecked")
	@Test(groups ={Slingshot,Smoke,BGBRegistration})
	public void PGSRPricingVerificationForLondon() throws Exception {

		Report.createTestLogHeader("CarePlan", "To Verify PGSR Pricing for London");
		try {
			for (String x : productsLondon1()) {
				CarePlanProfile carePlanProfile = new TestDataHelper().getCarePlanProfile(x);
				UserProfile userProfile = new TestDataHelper().getUserProfile("BgbCareplan");
				Report.updateTestLog(" ***************** Care plan combination : " + x  + " ***************** ", "PASS");
				new CareplanAction()
				.navigateToCareplan()
				//.pricingVerificationForLGSRandPGSR(carePlanProfile,userProfile,"no");
				.pricingVerificationNew(carePlanProfile,userProfile,"Yes");
				for (String y : productsLondonPGSR()){
					CarePlanProfile carePlanProfile1 = new TestDataHelper().getCarePlanProfile(y);
					Report.updateTestLog(" ***************** Care plan combination : " + y + " ***************** ", "PASS");
					new CareplanAction()
					//.verifyingPricingForDifferentQuatityForLGSRandPGSR(carePlanProfile,carePlanProfile1);
					.verifyingPricingForDifferentQuatityForLGSRandPGSRNew(carePlanProfile,carePlanProfile1);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
////////////////////////////////////////////////////////////////////////////////////PGSRPricingVerificationForRestOfUK///////////////////////////////////////////////////////////
	@SuppressWarnings("unchecked")
	@Test(groups ={Slingshot,Smoke,BGBRegistration})
	public void PGSRPricingVerificationForRestOfUK() throws Exception {

		Report.createTestLogHeader("CarePlan", "To Verify PGSR Pricing for Rest Of UK");
		try {
			for (String x : productsRestOfUK1()) {
				CarePlanProfile carePlanProfile = new TestDataHelper().getCarePlanProfile(x);
				UserProfile userProfile = new TestDataHelper().getUserProfile("BgbCareplanUk");
				Report.updateTestLog(" ***************** Care plan combination : " + x  + " ***************** ", "PASS");
				new CareplanAction()
				.navigateToCareplan()
				//.pricingVerificationForLGSRandPGSR(carePlanProfile,userProfile,"no");
				.pricingVerificationNew(carePlanProfile,userProfile,"Yes");
				for (String y : productsUKPGSR()){
					CarePlanProfile carePlanProfile1 = new TestDataHelper().getCarePlanProfile(y);
					Report.updateTestLog(" ***************** Care plan combination : " + y + " ***************** ", "PASS");
					new CareplanAction()
					//.verifyingPricingForDifferentQuatityForLGSRandPGSR(carePlanProfile,carePlanProfile1);
					.verifyingPricingForDifferentQuatityForLGSRandPGSRNew(carePlanProfile,carePlanProfile1);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();

		}
	}
	///////////////////////////////////////////////////////////////////////////////PricingVerificationForMulitpleproducts////////////////////////////////////////////////////////////////////////////
	@SuppressWarnings("unchecked")
	@Test(groups ={Slingshot,Smoke,BGBRegistration})
	
		public void PricingVerificationForMulitpleproductsforLondon() throws Exception {
		Report.createTestLogHeader("CarePlan", "To Verify Pricing Verification for Multiple products London region");
	
		UserProfile userProfile = new TestDataHelper().getUserProfile("BgbCareplan");
		new CareplanAction()
		 .navigateToCareplan()
		 .contactDetails(userProfile, "yes")
		 .yesIHaveGasSupply(userProfile)
		 .landlordCheck("yes");	 	
		try {
			
			for (String x : productsendtoendforLondon()) {
		
				CarePlanProfile carePlanProfile = new TestDataHelper().getCarePlanProfile(x);
				Report.updateTestLog(" ********* Care plan combination : " + x  + " *******", "PASS");
				new CareplanAction()
			    .pricingVerificationforMultipleproducts(carePlanProfile)
			    .addAnotherAppliance();
			
			}

			 } catch (Exception e) {
	             e.printStackTrace();
	             
	         }
		
		}
		
/*******************************************************************************************************PricingVerificationForMulitpleproducts uk region****************************************************************/
	
	@SuppressWarnings("unchecked")
	@Test(groups ={Slingshot,Smoke,BGBRegistration})
	
		public void PricingVerificationForMulitpleproductsforRestofUK() throws Exception {
		Report.createTestLogHeader("CarePlan", "To Verify Pricing Verification for Multiple products Rest of UK region");
	
		UserProfile userProfile = new TestDataHelper().getUserProfile("BgbCareplanUk");
		new CareplanAction()
		 .navigateToCareplan()
		 .contactDetails(userProfile, "No")
		 .yesIHaveGasSupply(userProfile)
		 .landlordCheck("yes");	 	
		try {
			
			for (String x : productsendtoendfoRestofUK()) {
		
				CarePlanProfile carePlanProfile = new TestDataHelper().getCarePlanProfile(x);
				Report.updateTestLog(" ********* Care plan combination : " + x  + " *******", "PASS");
				new CareplanAction()
			    .pricingVerificationforMultipleproducts(carePlanProfile)
			    .addAnotherAppliance();
			
			}

			 } catch (Exception e) {
	             e.printStackTrace();
	             
	         }
		
		}

	/***********************************************************************************Bespoke Rest Of UK******************************************************************************************	*/	
	@SuppressWarnings("unchecked")
	@Test(groups ={Slingshot,Smoke,BGBRegistration})

	public void bespokeErrorVerificationForRestofUKregion() throws Exception {
		Report.createTestLogHeader("CarePlan", "To Verify Bespoke Error Pricing for Rest of UK");
		try {
		for (String x : bespokeErrorMessageRestOfUK()) {
			CarePlanProfile carePlanProfile = new TestDataHelper().getCarePlanProfile(x);
			UserProfile userProfile = new TestDataHelper().getUserProfile("BgbCareplanUk");
			Report.updateTestLog(" ***************** Care plan combination : " + x  + " ***************** ", "PASS");
			new CareplanAction()
			.navigateToCareplan()
			//.bespokeVerification(carePlanProfile,userProfile,"No");
			.bespokeVerificationNew(carePlanProfile,userProfile);
			
		}
		 
		 } catch (Exception e) {
	         e.printStackTrace();
	         
	     }
	}

	/***************************************************************************************Bespoke UK**********************************************************************************************/
	@SuppressWarnings("unchecked")
	@Test(groups ={Slingshot,Smoke,BGBRegistration})

	public void bespokeErrorVerificationForLondonregion() throws Exception {
		Report.createTestLogHeader("CarePlan", "To Verify Bespoke Error Pricing for London Region");
		try {
		for (String x : bespokeErrorMessageUK()) {
			CarePlanProfile carePlanProfile = new TestDataHelper().getCarePlanProfile(x);
			UserProfile userProfile = new TestDataHelper().getUserProfile("BgbCareplan");
			Report.updateTestLog(" ***************** Care plan combination : " + x  + " ***************** ", "PASS");
			new CareplanAction()
			.navigateToCareplan()
			.bespokeVerificationNew(carePlanProfile,userProfile);
			
		}
		 
		 } catch (Exception e) {
	         e.printStackTrace();
	         
	     }
	}
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////	
	@SuppressWarnings("unchecked")
	@Test(groups ={Slingshot,Smoke,BGBRegistration})
	
		public void PricingVerificationForMulitpleproductsforLondonEndToEnd() throws Exception {
		Report.createTestLogHeader("CarePlan", "To Verify Pricing Verification for Multiple products London region");
	
		UserProfile userProfile = new TestDataHelper().getUserProfile("BgbCareplan");
		new CareplanAction()
		 .navigateToCareplan()
		 //.contactDetails(userProfile, "yes")
		 .contactDetailsNew(userProfile)
		 .yesIHaveGasSupplyNew(userProfile);
		 //.landlordCheck("yes");	 	
		try {
			
			for (String x : productsendtoendforLondon()) {
		
				CarePlanProfile carePlanProfile = new TestDataHelper().getCarePlanProfile(x);
				Report.updateTestLog(" ********* Care plan combination : " + x  + " *******", "PASS");
				
				new CareplanAction()
				 .appliancesPricingNew(carePlanProfile)
				 //.SelectlandlordCheckNew(carePlanProfile1)
			    .pricingVerificationforMultipleproducts(carePlanProfile)
			    .addAnotherAppliance();
			
			}
			new CareplanAction()
			.addAnotherAppliance();
			CarePlanProfile carePlanProfile1 = new TestDataHelper().getCarePlanProfile("L PGSR 2");
			//new CareplanAction()
			new CareplanAction()
			 .SelectlandlordCheckNew(carePlanProfile1)
			.continueToOrderNew()
			.ConfirmationPageNew(userProfile);
			
			 } catch (Exception e) {
	             e.printStackTrace();
	             
	         }
		
		}
	
/*********************************************************************************************End to End with single appliance London****************************************************************************************/		
		 @SuppressWarnings("unchecked")
		@Test(groups ={Slingshot,Smoke,BGBRegistration})
		public void endToEndLondon() throws Exception {
			 Report.createTestLogHeader("CarePlan", "To Verify end to end for London post code");
				
				try {
					for (String x : productsendtoendforLondon()) {
						CarePlanProfile carePlanProfile = new TestDataHelper().getCarePlanProfile(x);
						UserProfile userProfile = new TestDataHelper().getUserProfile("BgbCareplan");
						Report.updateTestLog(" ***************** Care plan combination : " + x  + " ***************** ", "PASS");
						new CareplanAction()
						.navigateToCareplan()
						//.pricingVerification(carePlanProfile,userProfile,"yes")
						.pricingVerificationNew(carePlanProfile,userProfile,"yes")
						.ConfirmationPage(userProfile);
						
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			 
		 }
/*********************************************************************************************End to End with single appliance rest of uk****************************************************************************************/	
		 @SuppressWarnings("unchecked")
			@Test(groups ={Slingshot,Smoke,BGBRegistration})
			public void endToEndRestofUK() throws Exception {
				 Report.createTestLogHeader("CarePlan", "To Verify end to end for Rest of uk postcode");
					
					try {
						for (String x : productsendtoendfoRestofUK()) {
							CarePlanProfile carePlanProfile = new TestDataHelper().getCarePlanProfile(x);
							UserProfile userProfile = new TestDataHelper().getUserProfile("BgbCareplanUk");
							Report.updateTestLog(" ***************** Care plan combination : " + x  + " ***************** ", "PASS");
							new CareplanAction()
							.navigateToCareplan()
							.pricingVerificationNew(carePlanProfile,userProfile,"yes")
							//.pricingVerification(carePlanProfile,userProfile,"yes")
							.ConfirmationPage(userProfile);
							
						}
					} catch (Exception e) {
						e.printStackTrace();
					}
				 
			 }
	
		 
/*********************************************************************************************End to End with single appliance London add another site****************************************************************************************/	 
/////Give London Post code///////////////////////////////
		 @SuppressWarnings("unchecked")
			@Test(groups ={Slingshot,Smoke,BGBRegistration})
			public void endToEndLondonMultipleAppliance() throws Exception {
				 Report.createTestLogHeader("CarePlan", "To Verify end to end for London post code with Add another site");
					
					try {
						for (String x : productsendtoendforLondon()) {
							CarePlanProfile carePlanProfile = new TestDataHelper().getCarePlanProfile(x);
							UserProfile userProfile = new TestDataHelper().getUserProfile("BgbCareplanUk");
							Report.updateTestLog(" ***************** Care plan combination : " + x  + " ***************** ", "PASS");
							new CareplanAction()
							.navigateToCareplan()
							.pricingVerification(carePlanProfile,userProfile,"yes")
							.ConfirmationPage(userProfile)
							.addAnotherSite();
							
						}
					} catch (Exception e) {
						e.printStackTrace();
					}
				 
			 }
		 
/*********************************************************************************************End to End with single appliance Rest of UK add another site****************************************************************************************/	 
		 /////Give Rest of UK Post code///////////////////////////////
		 @SuppressWarnings("unchecked")
			@Test(groups ={Slingshot,Smoke,BGBRegistration})
			public void endToEndRestofUKMultipleAppliance() throws Exception {
				 Report.createTestLogHeader("CarePlan", "To Verify end to end for London post code with Add another site");
					
					try {
						for (String x : productsendtoendfoRestofUK()) {
							CarePlanProfile carePlanProfile = new TestDataHelper().getCarePlanProfile(x);
							UserProfile userProfile = new TestDataHelper().getUserProfile("BgbCareplanUk");
							Report.updateTestLog(" ***************** Care plan combination : " + x  + " ***************** ", "PASS");
							new CareplanAction()
							.navigateToCareplan()
							.pricingVerification(carePlanProfile,userProfile,"yes")
							.ConfirmationPage(userProfile)
							.addAnotherSite();
							
						}
					} catch (Exception e) {
						e.printStackTrace();
					}
				 
			 }				 
		 
/*********************************************************************************************OAM Single product journey****************************************************************************************/	 
		 @SuppressWarnings("unchecked")
		 @Test(groups ={Slingshot,Smoke,BGBRegistration})
		 public void verifyOAMCareplanGAQ() throws Exception {
			 Report.createTestLogHeader("CarePlan", "To Verify OAM customer can get careplan quote sucessfully");
			 UserProfile userProfile = new TestDataHelper().getUserProfile("BgbCareplan");
			 
			 new LoginAction()
				.BgbloginDetails(userProfile);
				try {
					for (String x : productsendtoendforLondon()) {
						CarePlanProfile carePlanProfile = new TestDataHelper().getCarePlanProfile(x);
						//UserProfile userProfile = new TestDataHelper().getUserProfile("BgbCareplanUk");
						Report.updateTestLog(" ***************** Care plan combination : " + x  + " ***************** ", "PASS");
						new CareplanAction()
						.navigateToCareplan()
						.pricingVerificationNew(carePlanProfile,userProfile,"yes")
						.ConfirmationPage(userProfile)
						.addAnotherSite();
						
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			 
			 
			 
		 
		 }
		 

///// LSGR with 20 products E2E - BG Customer - "Yes"/////
		 @SuppressWarnings("unchecked")
		 @Test(groups ={Slingshot,Smoke,BGBRegistration})
		 public void multipleProductsE2EWithLGSRLondon() throws Exception {
			 Report.createTestLogHeader("CarePlan", "E2E Journey for Multiple products LGSR and other Products - London - BG Customer -'Yes'");
			 UserProfile userProfile = new TestDataHelper().getUserProfile("BgbCareplan");
			 new CareplanAction()
			 .navigateToCareplan()
			 .contactDetailsNew(userProfile)
			 .yesIHaveGasSupplyNew(userProfile);
			 //.landlordCheck("yes");	 	
			 try {
				 for (String x : LGSRMultipleProducts1()) {
					 CarePlanProfile carePlanProfile = new TestDataHelper().getCarePlanProfile(x);
					 Report.updateTestLog(" ********* Care plan combination : " + x  + " *******", "PASS");
					 new CareplanAction()
					 .appliancesPricingNew(carePlanProfile)
					 .pricingVerificationforMultipleproducts(carePlanProfile)
					 .addAnotherAppliance();

				 }
				 CarePlanProfile carePlanProfile1 = new TestDataHelper().getCarePlanProfile("L LGSR 1");
				 new CareplanAction()
				 .addAnotherAppliance()
				 .pricingVerificationForLGSRAndPGSRNew(carePlanProfile1)
				 .validtePriceforMultipleproductsWithLGSRorPGSR()
				 .ConfirmationPageNew(userProfile);

			 } catch (Exception e) {
				 e.printStackTrace();

			 }
		 }
		 
		 
		///// PGSR with 20 products E2E - BG Customer "Yes" /////
				 @SuppressWarnings("unchecked")
				 @Test(groups ={Slingshot,Smoke,BGBRegistration})
				 public void multipleProductsE2EWithPGSRLondon() throws Exception {
					 Report.createTestLogHeader("CarePlan", "E2E Journey for Multiple products PGSR and other Products - London - BG Customer -'Yes'");
					 UserProfile userProfile = new TestDataHelper().getUserProfile("BgbCareplan");
					 new CareplanAction()
					 .navigateToCareplan()
					 .contactDetailsNew(userProfile)
			 .yesIHaveGasSupplyNew(userProfile);
					 //.landlordCheck("No");	 	
					 try {
						 for (String x : PGSRMultipleProducts1()) {
							 CarePlanProfile carePlanProfile = new TestDataHelper().getCarePlanProfile(x);
							 Report.updateTestLog(" ********* Care plan combination : " + x  + " *******", "PASS");
							 new CareplanAction()
							 .appliancesPricingNew(carePlanProfile)
							 .pricingVerificationforMultipleproducts(carePlanProfile)
							 .addAnotherAppliance();

						 }
						 CarePlanProfile carePlanProfile1 = new TestDataHelper().getCarePlanProfile("L PGSR 1");
						 new CareplanAction()
						 .addAnotherAppliance()
						 .pricingVerificationForLGSRAndPGSRNew(carePlanProfile1)
						 .validtePriceforMultipleproductsWithLGSRorPGSR()
						 .ConfirmationPageNew(userProfile);

					 } catch (Exception e) {
						 e.printStackTrace();

					 }
				 }
				 
				 
				///// LSGR with 20 products E2E - Bg Customer - "Yes"/////
				 
				 
				 
				 @SuppressWarnings("unchecked")
				 @Test(groups ={Slingshot,Smoke,BGBRegistration})
				 public void multipleProductsE2EWithLGSR_RestOfUK_YES() throws Exception {
					 Report.createTestLogHeader("CarePlan", "E2E Journey for Multiple products LGSR and other Products - Rest Of UK - BG Customer -'Yes'");
					 UserProfile userProfile = new TestDataHelper().getUserProfile("BgbCareplanUk");
					 new CareplanAction()
					 .navigateToCareplan()
					 .contactDetailsNew(userProfile)
					 .yesIHaveGasSupplyNew(userProfile);
					 //.landlordCheck("yes");	 	
					 try {
						 for (String x : MutipleProductsRestOfUKYes()) {
							 CarePlanProfile carePlanProfile = new TestDataHelper().getCarePlanProfile(x);
							 Report.updateTestLog(" ********* Care plan combination : " + x  + " *******", "PASS");
							 new CareplanAction()
							 .appliancesPricingNew(carePlanProfile)
							 .pricingVerificationforMultipleproducts(carePlanProfile)
							 .addAnotherAppliance();

						 }
						 CarePlanProfile carePlanProfile1 = new TestDataHelper().getCarePlanProfile("LGSR 3");
						 new CareplanAction()
						 .addAnotherAppliance()
						 .pricingVerificationForLGSRAndPGSRNew(carePlanProfile1)
						 .validtePriceforMultipleproductsWithLGSRorPGSR()
						 .ConfirmationPageNew(userProfile);

					 } catch (Exception e) {
						 e.printStackTrace();

					 }
				 }
				 
				 
				///// PGSR with 20 products E2E - BG Customer - "Yes"/////
				 @SuppressWarnings("unchecked")
				 @Test(groups ={Slingshot,Smoke,BGBRegistration})
				 public void multipleProductsE2EWithPGSRRestOfUK() throws Exception {
					 Report.createTestLogHeader("CarePlan", "E2E Journey for Multiple products PGSR and other Products - Rest Of UK - BG Customer -'Yes'");
					 UserProfile userProfile = new TestDataHelper().getUserProfile("BgbCareplanUk");
					 new CareplanAction()
					 .navigateToCareplan()
					 .contactDetailsNew(userProfile)
					  .yesIHaveGasSupplyNew(userProfile);
					 //.landlordCheck("No");	 	
					 try {
						 for (String x : MutipleProductsRestOfUKYes()) {
							 CarePlanProfile carePlanProfile = new TestDataHelper().getCarePlanProfile(x);
							 Report.updateTestLog(" ********* Care plan combination : " + x  + " *******", "PASS");
							 new CareplanAction()
							 .appliancesPricingNew(carePlanProfile)
							 .pricingVerificationforMultipleproducts(carePlanProfile)
							 .addAnotherAppliance();

						 }
						 CarePlanProfile carePlanProfile1 = new TestDataHelper().getCarePlanProfile("PGSR 4");
						 new CareplanAction()
						 .addAnotherAppliance()
						 .pricingVerificationForLGSRAndPGSRNew(carePlanProfile1)
						 .validtePriceforMultipleproductsWithLGSRorPGSR()
						 .ConfirmationPageNew(userProfile);

					 } catch (Exception e) {
						 e.printStackTrace();

					 }
				 }
				 
				 
				///// LGSR with 20 products E2E - BG Customer - No/////
				 @SuppressWarnings("unchecked")
				 @Test(groups ={Slingshot,Smoke,BGBRegistration})
				 public void multipleProductsE2EWithLGSR_RestOfUK_NO() throws Exception {
					 Report.createTestLogHeader("CarePlan", "E2E Journey for Multiple products LGSR and other Products- Rest Of UK - BG Customer -'No'");
					 UserProfile userProfile = new TestDataHelper().getUserProfile("BgbCareplanUk");
					 new CareplanAction()
					 .navigateToCareplan()
					 .contactDetails(userProfile, "No")
					 .yesIHaveGasSupply(userProfile)
					 .landlordCheck("yes");	 	
					 try {
						 for (String x : LGSRMutipleProductsRestOfUKNo()) {
							 CarePlanProfile carePlanProfile = new TestDataHelper().getCarePlanProfile(x);
							 Report.updateTestLog(" ********* Care plan combination : " + x  + " *******", "PASS");
							 new CareplanAction()
							 .appliancesPricing(carePlanProfile)
							 .pricingVerificationforMultipleproducts(carePlanProfile)
							 .addAnotherAppliance();

						 }
						 CarePlanProfile carePlanProfile1 = new TestDataHelper().getCarePlanProfile("LGSR 2");
						 new CareplanAction()
						 .addAnotherAppliance()
						 .pricingVerificationForLGSRAndPGSR(carePlanProfile1)
						 .validtePriceforMultipleproductsWithLGSRorPGSR()
						 .ConfirmationPage(userProfile);

					 } catch (Exception e) {
						 e.printStackTrace();

					 }
				 }
				 
				///// PGSR with 20 products E2E - BG Customer - No /////
						 @SuppressWarnings("unchecked")
						 @Test(groups ={Slingshot,Smoke,BGBRegistration})
						 public void multipleProductsE2EWithPGRS_London_YES() throws Exception {
							 Report.createTestLogHeader("CarePlan", "E2E Journey for Multiple products PGSR and other Products - London - BG Customer -'No'");
							 UserProfile userProfile = new TestDataHelper().getUserProfile("BgbCareplan");
							 new CareplanAction()
							 .navigateToCareplan()
							 .contactDetailsNew(userProfile)
							 //.contactDetails(userProfile, "No")
							 .yesIHaveGasSupplyNew(userProfile);
							 //.yesIHaveGasSupply(userProfile)
							 //.landlordCheck("No");	 	
							 try {
								 for (String x : PGSRMutipleProductsLondonNo()) {
									 CarePlanProfile carePlanProfile = new TestDataHelper().getCarePlanProfile(x);
									 Report.updateTestLog(" ********* Care plan combination : " + x  + " *******", "PASS");
									 new CareplanAction()
									 .appliancesPricingNew(carePlanProfile)
									 //.appliancesPricing(carePlanProfile)
									 
									 .pricingVerificationforMultipleproducts(carePlanProfile)
									 .addAnotherAppliance();

								 }
								 CarePlanProfile carePlanProfile1 = new TestDataHelper().getCarePlanProfile("L PGSR 2");
								 new CareplanAction()
								 .addAnotherAppliance()
								 .pricingVerificationForLGSRAndPGSRNew(carePlanProfile1)
								 //.pricingVerificationForLGSRAndPGSR(carePlanProfile1)
								 .validtePriceforMultipleproductsWithLGSRorPGSR()
								 .ConfirmationPageNew(userProfile);
								 //.ConfirmationPage(userProfile);

							 } catch (Exception e) {
								 e.printStackTrace();

							 }
						 }

		 
		 
		 
		 
		 //// Multiple Products for E2E with LGSR and PGSR - London////
		 
		 private String[] LGSRMultipleProducts1(){
			 String[] prod ={
					 
					 "L Heating and hotwater appliances boiler 1",
					 "L Heating and hotwater appliances boiler 3",
					 "L Heating and hotwater appliances boiler 4",
					 "L Heating and hotwater appliances boiler 6",
					 "L Heating and hotwater appliances boiler 13",
					 "L Heating and hotwater appliances boiler 9",
					 "L Heating and hotwater appliances FHS 1",
					 "L Heating and hotwater appliances FHS 6",
					 "L Heating and hotwater appliances FHS 7",
					 "L Heating and hotwater appliances FHS 8",
					 "L Heating and hotwater appliances FHS 9",
					 "L Heating and hotwater appliances SWH 1",
					 "L Heating and hotwater appliances SWH 2",
					 "L Heating and hotwater appliances SWH 7",
					 "L Heating and hotwater appliances SWH 4",
					 "L Heating and hotwater appliances SWH 9",
					 "L Heating and hotwater appliances SWAH 1",
					 "L Heating and hotwater appliances SWAH 2",
					 "L Heating and hotwater appliances SWAH 3",
					 "L Heating and hotwater appliances SWAH 10"
					 
					 /*"L Laundry Commercial 1",
					 "L Heating and hotwater appliances boiler 15",
					 "L Heating and hotwater appliances boiler 1",
					 "L Heating and hotwater appliances SWAH 2",
					 "L Catering equipment CC 1",
					 "L Heating and hotwater appliances SWAH 1",	
					 "L Heating and hotwater appliances SWH 1",
					 "L Heating and hotwater appliances boiler 11",
					 "L Heating and hotwater appliances boiler 12",
					 "L Catering equipment Domestic 3",
					 "L Catering equipment Domestic 1",
					 "L Catering equipment Domestic 9",
					 "L Catering equipment Domestic 11",
					 "L Laundry Domestic 1",
					 "L Laundry Commercial 1",
					 "L Laundry Commercial 3",
					 "L Heating and hotwater appliances boiler 29",
					 "L Heating and hotwater appliances boiler 30",
					 "L Heating and hotwater appliances SWH 2",
					 "L Catering equipment CC 7",*/
					 
			 };
			 return prod;
		 }
		 
		 
		 
		 
		 //// Multiple Products for E2E with LGSR and PGSR  - London////
		 
		 private String[] PGSRMultipleProducts1(){
			 String[] prod ={
					 "L Heating and hotwater appliances boiler 5",
					 "L Heating and hotwater appliances boiler 2",
					 "L Heating and hotwater appliances boiler 7",
					 "L Heating and hotwater appliances boiler 8",
					 "L Heating and hotwater appliances boiler 14",
					 "L Heating and hotwater appliances boiler 10",
					 "L Heating and hotwater appliances boiler 11",
					 "L Heating and hotwater appliances boiler 12",
					 "L Heating and hotwater appliances FHS 2",
					 "L Heating and hotwater appliances FHS 3",
					 "L Heating and hotwater appliances FHS 4",
					 "L Heating and hotwater appliances FHS 5",
					 "L Heating and hotwater appliances FHS 10",
					 "L Heating and hotwater appliances SWH 3",
					 "L Heating and hotwater appliances SWH 6",
					 "L Heating and hotwater appliances SWH 7",
					 "L Heating and hotwater appliances SWAH 1",
					 "L Heating and hotwater appliances SWAH 2",
					 "L Heating and hotwater appliances SWAH 3",
					 "L Heating and hotwater appliances SWAH 10"
			/*		 "L Laundry Commercial 3",
					 "L Heating and hotwater appliances boiler 14",
					 "L Heating and hotwater appliances boiler 2",
					 "L Heating and hotwater appliances SWAH 3",
					 "L Catering equipment CC 1",
					 "L Heating and hotwater appliances SWAH 2",
					 "L Heating and hotwater appliances boiler 21",
					 "L Heating and hotwater appliances boiler 19",
					 "L Catering equipment CC 9",
					 "L Catering equipment CC 11",
					 "L Catering equipment Domestic 1",
					 "L Catering equipment CC 13",
					 "L Catering equipment Domestic 9",
					 "L Catering equipment Domestic 11",
					 "L Laundry Domestic 1",
					 "L Heating and hotwater appliances SWH 9",
		    		 "L Heating and hotwater appliances SWH 11",
		    		 "L Heating and hotwater appliances SWAH 10",
		    		 "L Heating and hotwater appliances boiler 23",
		    		 "L Heating and hotwater appliances boiler 24",*/
					/* "L Heating and hotwater appliances 1",
					 "L Heating and hotwater appliances SWH 1",
					 "L Catering equipment CC 1",
					 "L Catering equipment CC 3",
					 "L Catering equipment CC 9",
					 "L Catering equipment CC 11",
					 "L Catering equipment CC 15",
					 "L Catering equipment Domestic 3",
					 "L Catering equipment Domestic 9",
					 "L Laundry Domestic 1",
					 "L Heating and hotwater appliances SWH 9",*/
			 };
			 return prod;
		 }
		 
		 /// multiple products with 'no' Bg Customer - London///
		 
		 private String[] PGSRMutipleProductsLondonNo(){
			 String[] prod ={
					 
					 "L Heating and hotwater appliances boiler 1",
					 "L Heating and hotwater appliances boiler 3",
					 "L Heating and hotwater appliances boiler 6",
					 "L Heating and hotwater appliances boiler 11",
					 "L Heating and hotwater appliances boiler 12",
					 "L Heating and hotwater appliances boiler 14",
					 "L Heating and hotwater appliances FHS 1",
					 "L Heating and hotwater appliances FHS 3",
					 "L Heating and hotwater appliances FHS 6",
					 "L Heating and hotwater appliances FHS 8",
					 "L Heating and hotwater appliances FHS 10",
					 "L Heating and hotwater appliances SWH 1",
					 "L Heating and hotwater appliances SWH 4",
					 "L Heating and hotwater appliances SWH 7",
					 "L Heating and hotwater appliances SWH 8",
					 "L Heating and hotwater appliances SWH 6",
					 "L Heating and hotwater appliances SWAH 1",
					 "L Heating and hotwater appliances SWAH 3",
					 "L Heating and hotwater appliances SWAH 2",
					 "L Heating and hotwater appliances SWAH 10"
					 
					/* "L Heating and hotwater appliances boiler 1",
					 "L Heating and hotwater appliances boiler 6",
					 "L Heating and hotwater appliances boiler 10",
					 "L Heating and hotwater appliances boiler 11",
					 "L Heating and hotwater appliances boiler 12",
					 "L Heating and hotwater appliances boiler 13",
					 "L Heating and hotwater appliances FHS 1",
					 "L Heating and hotwater appliances FHS 10",
					 "L Heating and hotwater appliances FHS 2",
					 "L Heating and hotwater appliances FHS 3",
					 "L Heating and hotwater appliances FHS 4",
					 "L Heating and hotwater appliances FHS 8",
					 "L Heating and hotwater appliances SWH 4",
					 "L Heating and hotwater appliances SWH 5",
					 "L Heating and hotwater appliances SWH 6",
					 "L Heating and hotwater appliances SWH 7",
					 "L Heating and hotwater appliances SWAH 1",
					 "L Heating and hotwater appliances SWAH 10",
					 "L Heating and hotwater appliances SWAH 2",
					 "L Heating and hotwater appliances SWAH 3"*/
					 
					 
					 /*"L Heating and hotwater appliances boiler 5",
					 "L Heating and hotwater appliances boiler 7",
					 "L Heating and hotwater appliances SWH 3",
					 "L Heating and hotwater appliances SWH 4",
					 "L Catering equipment CC 6",
					 "L Catering equipment CC 8",
					 "L Catering equipment CC 10",
					 "L Catering equipment CC 12",
					 "L Catering equipment CC 14",
					 "L Catering equipment CC 22",
					 "L Catering equipment CC 24",
					 "L Catering equipment Domestic 2",
					 "L Catering equipment Domestic 4",
					 "L Catering equipment Domestic 6",
					 "L Catering equipment Domestic 12",
					 "L Laundry Domestic 2",
					 "L Laundry Commercial 2",
					 "L Laundry Commercial 4",
					 "L Heating and hotwater appliances boiler 26",
					 "L Heating and hotwater appliances SWH 10"*/

			 };
			 return prod;
		 }
		 
 /// multiple products with 'no' Bg Customer - Rest Of UK///
		 
		 private String[] LGSRMutipleProductsRestOfUKNo(){
			 String[] prod ={
					 "Heating and hotwater appliances boiler 5",
					 "Heating and hotwater appliances boiler 6",
					 "Heating and hotwater appliances boiler 7",
					 "Heating and hotwater appliances boiler 8",
					 "Catering equipment CC 2",
					 "Catering equipment CC 4",
					 "Catering equipment CC 6",
					 "Catering equipment CC 8",
					 "Catering equipment CC 10",
					 "Catering equipment CC 12",
					 "Catering equipment CC 24",
					 "Catering equipment Domestic 2",
					 "Catering equipment Domestic 4",
					 "Catering equipment Domestic 6",
					 "Catering equipment Domestic 12",
					 "Laundry Domestic 2",
					 "Laundry Commercial 2",
					 "Laundry Commercial 4",
					 "Heating and hotwater appliances boiler 26",
					 "Heating and hotwater appliances SWH 9",

			 };
			 return prod;
		 }
		 
		 /// multiple products with 'Yes' Bg Customer - Rest Of UK///
		 
		 private String[] MutipleProductsRestOfUKYes(){
			 String[] prod ={
					 "Heating and hotwater appliances boiler 1",
					 "Heating and hotwater appliances boiler 6",
					 "Heating and hotwater appliances boiler 3",
					 "Heating and hotwater appliances boiler 8",
					 "Heating and hotwater appliances boiler 13",
					 "Heating and hotwater appliances boiler 5",
					 "Heating and hotwater appliances FHS 1",
					 "Heating and hotwater appliances FHS 6",
					 "Heating and hotwater appliances FHS 3",
					 "Heating and hotwater appliances FHS 7",
					 "Heating and hotwater appliances FHS 8",
					 "Heating and hotwater appliances FHS 4",
					 "Heating and hotwater appliances FHS 9",
					 "Heating and hotwater appliances SWH 1",
					 "Heating and hotwater appliances SWH 5",
					 "Heating and hotwater appliances SWH 6",
					 "Heating and hotwater appliances SWAH 1",
					 "Heating and hotwater appliances SWAH 2",
					 "Heating and hotwater appliances SWAH 3",
					 "Heating and hotwater appliances SWAH 10"
					/* "Heating and hotwater appliances boiler 1",
                     "Heating and hotwater appliances boiler 2",
                     "Heating and hotwater appliances boiler 4",
                     "Catering equipment CC 7",
                     "Catering equipment CC 21",
                     "Catering equipment CC 17",
                     "Catering equipment Domestic 1",
                     "Catering equipment Domestic 7",
                     "Catering equipment Domestic 9",
                     "Catering equipment Domestic 3",
                     "Laundry Commercial 1",
                     "Laundry Domestic 1",
                     "Laundry Commercial 3",
                     "Heating and hotwater appliances boiler 25",
                     "Heating and hotwater appliances SWH 1",
                     "Heating and hotwater appliances SWH 2",
                     "Heating and hotwater appliances boiler 3",
                     "Heating and hotwater appliances SWH 8",
                     "Heating and hotwater appliances boiler 25",
                     "Catering equipment Domestic 11",*/
			 };
			 return prod;
		 }
		 
	
/////////////////////////////////////////////////////////////////////////////////////////////productsLondonLGSR////////////////////////////////////////////////////////////////////////////////////////////////////	
	private String[] productsLondonLGSR(){

		String [] London ={
				"L LGSR 1",
				"L LGSR 2",
				"L LGSR 3",
				"L LGSR 4",			
		};
		return London;
	}
//////////////////////////////////////////////////////////////////////////////////////////////////productsLondonPGSR//////////////////////////////////////////////////////////////////////////////////////////////////////
	private String[] productsLondonPGSR(){

		String [] London ={
				"L PGSR 1",
				"L PGSR 2",
				"L PGSR 3",
				"L PGSR 4",	
		};
		return London;
	}
/////////////////////////////////////////////////////////////////////////////////////////////productsUKLGSR///////////////////////////////////////////////////////////////////////////////////////////////////////////

	private String[] productsUKLGSR(){

		String [] RestOfUK ={
				"LGSR 1",
				"LGSR 2",
				"LGSR 3",
				"LGSR 4",			
		};
		return RestOfUK;
	}
/////////////////////////////////////////////////////////////////////////////////////////////productsUKPGSR///////////////////////////////////////////////////////////////////////////////////////////////////////////
	private String[] productsUKPGSR(){

		String [] RestOfUK ={
				"PGSR 1",
				"PGSR 2",
				"PGSR 3",
				"PGSR 4",	
		};
		return RestOfUK;
	}
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////	
	private String[] productsLondon1(){
		String [] London1 ={
				
				"L Heating and hotwater appliances boiler 1",
				"L Heating and hotwater appliances boiler 2",
				"L Heating and hotwater appliances boiler 3",
				"L Heating and hotwater appliances boiler 4",
				"L Heating and hotwater appliances boiler 5",
				"L Heating and hotwater appliances boiler 6",
				"L Heating and hotwater appliances boiler 7",
				"L Heating and hotwater appliances boiler 8",
				"L Heating and hotwater appliances FHS 1",
				"L Heating and hotwater appliances FHS 2",
				"L Heating and hotwater appliances FHS 3",
				"L Heating and hotwater appliances FHS 4",
				"L Heating and hotwater appliances boiler 9",
				"L Heating and hotwater appliances boiler 10",
				
			/*	"L Heating and hotwater appliances boiler 11",
				"L Heating and hotwater appliances boiler 12",
				"L Heating and hotwater appliances FHS 5",
				"L Heating and hotwater appliances FHS 6",
				"L Heating and hotwater appliances FHS 7",
				"L Heating and hotwater appliances FHS 8",
				"L Heating and hotwater appliances SWH 1",
				"L Heating and hotwater appliances SWH 2",
				"L Heating and hotwater appliances SWH 3",
				"L Heating and hotwater appliances SWH 4",
				"L Heating and hotwater appliances SWH 5",
				"L Heating and hotwater appliances SWH 6",
				"L Heating and hotwater appliances SWAH 1",
				"L Heating and hotwater appliances SWAH 2",*/
				"L Heating and hotwater appliances SWAH 3",
				"L Heating and hotwater appliances boiler 13",
				"L Heating and hotwater appliances boiler 14",
				"L Heating and hotwater appliances FHS 9",
				"L Heating and hotwater appliances boiler 15",
				"L Heating and hotwater appliances FHS 10",
				"L Heating and hotwater appliances SWAH 10",
				"L Heating and hotwater appliances SWH 7",
				"L Heating and hotwater appliances SWH 8",
				"L Heating and hotwater appliances SWH 9"
				/*"L Catering equipment CC 1",
				"L Catering equipment Domestic 1",
				"L Laundry Domestic 1",
				"L Laundry Commercial 1",	
				"L Heating and hotwater appliances boiler 2",
				"L Heating and hotwater appliances boiler 19",
				"L Heating and hotwater appliances boiler 12",
				"L Heating and hotwater appliances boiler 14",*/
			/*	"L Heating and hotwater appliances SWH 1",
				"L Heating and hotwater appliances SWH 8",
				"L Heating and hotwater appliances SWH 6",
				"L Heating and hotwater appliances SWAH 2",*/
				
				/*"L Heating and hotwater appliances boiler 1",
				"L Heating and hotwater appliances boiler 2",*/
				/*"L Heating and hotwater appliances boiler 3",
				"L Heating and hotwater appliances boiler 4",
				"L Heating and hotwater appliances boiler 5",
				"L Heating and hotwater appliances boiler 6",
				"L Heating and hotwater appliances boiler 7",
				"L Heating and hotwater appliances boiler 8",
				"L Heating and hotwater appliances FHS 1",
				"L Heating and hotwater appliances FHS 2",
				"L Heating and hotwater appliances FHS 3",
				"L Heating and hotwater appliances FHS 4",
				"L Heating and hotwater appliances boiler 9",
				"L Heating and hotwater appliances boiler 10",
				"L Heating and hotwater appliances boiler 11",*/
				//"L Heating and hotwater appliances boiler 12"
				//"L Heating and hotwater appliances FHS 5",
				//"L Heating and hotwater appliances FHS 6",
				//"L Heating and hotwater appliances FHS 7",
				//"L Heating and hotwater appliances FHS 8"
				
				
		};
		return London1;
	}
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	private String[] productsRestOfUK1(){
		String [] London1 ={
				
				"Heating and hotwater appliances boiler 1",
				"Heating and hotwater appliances boiler 2",
				"Heating and hotwater appliances boiler 3",
				"Heating and hotwater appliances boiler 4",
				"Heating and hotwater appliances boiler 5",
				"Heating and hotwater appliances boiler 6",
				"Heating and hotwater appliances boiler 7",
				"Heating and hotwater appliances boiler 8",
				"Heating and hotwater appliances FHS 1",
				"Heating and hotwater appliances FHS 2",
				"Heating and hotwater appliances FHS 3",
				"Heating and hotwater appliances FHS 4",
				"Heating and hotwater appliances boiler 9",
				"Heating and hotwater appliances boiler 10",
				"Heating and hotwater appliances boiler 11",
				"Heating and hotwater appliances boiler 12",
				"Heating and hotwater appliances FHS 5",
				"Heating and hotwater appliances FHS 6",
				"Heating and hotwater appliances FHS 7",
				"Heating and hotwater appliances FHS 8",
				"Heating and hotwater appliances SWH 1",
				"Heating and hotwater appliances SWH 2",
				"Heating and hotwater appliances SWH 3",
				"Heating and hotwater appliances SWH 4",
				"Heating and hotwater appliances SWH 5",
				"Heating and hotwater appliances SWAH 1",
				"Heating and hotwater appliances SWAH 2",
				"Heating and hotwater appliances SWAH 3",
				"Heating and hotwater appliances boiler 13",
				"Heating and hotwater appliances boiler 14",
            	"Heating and hotwater appliances FHS 9",
				"Heating and hotwater appliances boiler 15",
				"Heating and hotwater appliances FHS 10",
				"Heating and hotwater appliances SWAH 10",
				"Heating and hotwater appliances SWH 6",
				"Heating and hotwater appliances SWH 7",
				"Heating and hotwater appliances SWH 8",
				"Heating and hotwater appliances SWH 9"

				
				/*"Heating and hotwater appliances boiler 1",
				"Heating and hotwater appliances SWH 1",
				"Catering equipment CC 1",
				"Catering equipment CC 5",
				"Catering equipment CC 9",
				"Catering equipment CC 15",
				"Catering equipment Domestic 1",
				"Catering equipment Domestic 7",
				"Laundry Commercial 1",
				"Heating and hotwater appliances boiler 25",
*/
				/*"Catering equipment CC 5",
				"Catering equipment Domestic 4",
				"Laundry Domestic 2",
				"Laundry Commercial 4",	
				"Heating and hotwater appliances boiler 5",
				"Heating and hotwater appliances boiler 9",
				"Heating and hotwater appliances boiler 13",
				"Heating and hotwater appliances boiler 17",
				"Heating and hotwater appliances boiler 21",
				"Heating and hotwater appliances SWH 5",
				"Heating and hotwater appliances SWH 4",
				"Heating and hotwater appliances SWAH 3",*/
				/*"Heating and hotwater appliances boiler 5",
				"Heating and hotwater appliances boiler 10",
				"Heating and hotwater appliances boiler 15",
				"Heating and hotwater appliances boiler 20",
				"Heating and hotwater appliances SWH 3",
				"Heating and hotwater appliances SWH 7",
				"Heating and hotwater appliances SWAH 1",
				"Catering equipment CC 2",
				"Catering equipment Domestic 2",
				"Catering equipment Domestic 4",
				"Catering equipment Domestic 6",
				"Catering equipment Domestic 8",
				"Catering equipment Domestic 10",
				"Catering equipment Domestic 12",
				"Laundry Domestic 2",
				"Heating and hotwater appliances boiler 26",
				"Heating and hotwater appliances boiler 27",
				"Heating and hotwater appliances boiler 28",
				"Heating and hotwater appliances boiler 29",
				"Heating and hotwater appliances boiler 30",
				"Heating and hotwater appliances SWAH 10",*/
				/*"Heating and hotwater appliances SWH 9",
				"Heating and hotwater appliances SWH 10",
				"Heating and hotwater appliances SWH 11",
				"Heating and hotwater appliances SWH 12",*/
		};
		return London1;
	}
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	private String[] productsendtoendforLondon(){
		
		String [] productsendtoendforLondon ={
				
				"L Heating and hotwater appliances boiler 1",
				"L Heating and hotwater appliances boiler 2",
				"L Heating and hotwater appliances boiler 3",
				"L Heating and hotwater appliances boiler 4",
				"L Heating and hotwater appliances boiler 7",
				"L Heating and hotwater appliances FHS 1",
				"L Heating and hotwater appliances FHS 2",
				"L Heating and hotwater appliances FHS 3",
				"L Heating and hotwater appliances FHS 4",
				"L Heating and hotwater appliances SWH 1",
				"L Heating and hotwater appliances SWH 2",
				"L Heating and hotwater appliances SWH 4",
				"L Heating and hotwater appliances SWAH 1",
				"L Heating and hotwater appliances SWAH 2",
				"L Heating and hotwater appliances SWAH 3",
				"L Heating and hotwater appliances boiler 13",
				"L Heating and hotwater appliances FHS 9",
				"L Heating and hotwater appliances SWAH 10",
				"L Heating and hotwater appliances SWH 9",
				"L Heating and hotwater appliances SWH 7"

				
				
				/*"L Heating and hotwater appliances boiler 1",
				"L Heating and hotwater appliances boiler 2",
				"L Heating and hotwater appliances boiler 3",
				"L Heating and hotwater appliances boiler 4",
				"L Heating and hotwater appliances boiler 5",
				"L Heating and hotwater appliances boiler 6",
				"L Heating and hotwater appliances boiler 7",
				"L Heating and hotwater appliances boiler 8",
				"L Heating and hotwater appliances FHS 1",
				"L Heating and hotwater appliances FHS 2",
				"L Heating and hotwater appliances FHS 3",
				"L Heating and hotwater appliances FHS 4",
				"L Heating and hotwater appliances boiler 9",
				"L Heating and hotwater appliances boiler 10",
				"L Heating and hotwater appliances boiler 11",
				"L Heating and hotwater appliances boiler 12",
				"L Heating and hotwater appliances FHS 5",
				"L Heating and hotwater appliances FHS 6",
				"L Heating and hotwater appliances FHS 7",
				"L Heating and hotwater appliances FHS 8",
				"L Heating and hotwater appliances SWH 1",
				"L Heating and hotwater appliances SWH 2",
				"L Heating and hotwater appliances SWH 3",
				"L Heating and hotwater appliances SWH 4",
				"L Heating and hotwater appliances SWH 5",
				"L Heating and hotwater appliances SWH 6",
				"L Heating and hotwater appliances SWAH 1",
				"L Heating and hotwater appliances SWAH 2",
				"L Heating and hotwater appliances SWAH 3",
				"L Heating and hotwater appliances boiler 13",
				"L Heating and hotwater appliances boiler 14",
				"L Heating and hotwater appliances FHS 9",
				"L Heating and hotwater appliances boiler 15",
				"L Heating and hotwater appliances FHS 10",
				"L Heating and hotwater appliances SWAH 10",
				"L Heating and hotwater appliances SWH 7",
				"L Heating and hotwater appliances SWH 8",
				"L Heating and hotwater appliances SWH 9"*/
				
/*				 "L Heating and hotwater appliances boiler 1",
		          "L Heating and hotwater appliances boiler 2",*/
		   /* "L Heating and hotwater appliances boiler 3",*/
		 /*   "L Heating and hotwater appliances boiler 4",*/
		   /* "L Heating and hotwater appliances boiler 9",*/
	/*	   "L Heating and hotwater appliances boiler 15",
		    "L Heating and hotwater appliances boiler 22",
		    "L Heating and hotwater appliances boiler 20",
		    "L Heating and hotwater appliances SWH 1",
		    "L Heating and hotwater appliances SWH 2",
		    "L Heating and hotwater appliances SWH 5",
		    "L Heating and hotwater appliances SWAH 1",
		    "L Heating and hotwater appliances SWAH 2",
		    "L Heating and hotwater appliances SWAH 3",
		    "L Catering equipment CC 1",
		    "L Catering equipment Domestic 3",
		    "L Laundry Commercial 1",
		    "L Catering equipment Domestic 7",
		    "L Catering equipment CC 11",
		    "L Laundry Commercial 3",*/
				
				
				
				
				/*
				"L Heating and hotwater appliances boiler 3",
			    "L Heating and hotwater appliances boiler 4",
			    "L Heating and hotwater appliances boiler 9",*/
				
				
				
			//	"L Heating and hotwater appliances boiler 1",
	
	/*	"L Heating and hotwater appliances boiler 1",
				
		
        "L Heating and hotwater appliances boiler 2",
	   "L Heating and hotwater appliances boiler 3",*/
	/*	"L Heating and hotwater appliances boiler 4",*/
	  /*   "L Heating and hotwater appliances boiler 9",
       "L Heating and hotwater appliances boiler 15",
	  "L Heating and hotwater appliances boiler 22",
				"L Heating and hotwater appliances boiler 20",
				"L Heating and hotwater appliances boiler 1",
				"L Heating and hotwater appliances SWH 1",
				"L Heating and hotwater appliances SWH 2",
				"L Heating and hotwater appliances SWH 5",
				"L Heating and hotwater appliances SWAH 1",
				"L Heating and hotwater appliances SWAH 2",
				"L Heating and hotwater appliances SWAH 3",
				"L Catering equipment CC 1",
				"L Catering equipment Domestic 3",
				"L Laundry Commercial 1",
				"L Catering equipment Domestic 7",
				"L Catering equipment CC 11",
				"L Laundry Commercial 3",*/
				
		/*	"Heating and hotwater appliances boiler 27",*/
				
			
			
		};
		return productsendtoendforLondon;
	}
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////	
	private String[] productsendtoendfoRestofUK(){
		
		String [] productsendtoendfoRestofUK ={
				
				"Heating and hotwater appliances SWAH 3",
				/*"Heating and hotwater appliances boiler 5",
				"Heating and hotwater appliances boiler 6",
				"Heating and hotwater appliances boiler 7",
				"Catering equipment CC 2",
				"Catering equipment CC 4",
				"Catering equipment CC 6",
				"Catering equipment Domestic 2",
				"Catering equipment Domestic 4",
				"Catering equipment Domestic 6",
				"Catering equipment Domestic 12",
				"Laundry Domestic 2",
				"Laundry Commercial 2",
				"Heating and hotwater appliances SWH 5",
				"Heating and hotwater appliances SWH 6",
				"Heating and hotwater appliances SWH 7",
			    "Heating and hotwater appliances SWAH 1",
				"Heating and hotwater appliances SWAH 2",
				"Heating and hotwater appliances boiler 27",
				"Heating and hotwater appliances boiler 28",
				"Heating and hotwater appliances boiler 29",*/


         
			
		};
		return productsendtoendfoRestofUK;
	}
//////////////////////////////////////////////////////////////////////////////////////////////bespokeErrorMessageRestOfUK//////////////////////////////////////////////////////////////////////////////////////////////////////////	
	private String[] bespokeErrorMessageRestOfUK(){
		
		String [] bespokeErrorMessageRestOfUK ={
			/*	"Heating and hotwater appliances SWAH 11",*/
				//"Heating and hotwater appliances SWAH 12",
				/*"Heating and hotwater appliances SWAH 4",
				"Heating and hotwater appliances SWAH 5",
				"Heating and hotwater appliances SWAH 6",*/
			/*	"Heating and hotwater appliances SWAH 7",
				"Heating and hotwater appliances SWAH 8",
				"Heating and hotwater appliances SWAH 9",*/
				"Heating and hotwater appliances SWAH 4",
				"Heating and hotwater appliances SWAH 5",
				"Heating and hotwater appliances SWAH 6",
				"Heating and hotwater appliances SWAH 7",
				"Heating and hotwater appliances SWAH 8",
				"Heating and hotwater appliances SWAH 9",
				"Heating and hotwater appliances SWAH 11",
				"Heating and hotwater appliances SWAH 12"
					
		};
		return bespokeErrorMessageRestOfUK;
	}
	
/////////////////////////////////////////////////////////////////////////////////////////////bespokeErrorMessageUK///////////////////////////////////////////////////////////////////////////////////////////////////////////
	private String[] bespokeErrorMessageUK(){
		
		String [] bespokeErrorMessageUK ={
				/*"L Heating and hotwater appliances SWAH 4",
				"L Heating and hotwater appliances SWAH 5",
				"L Heating and hotwater appliances SWAH 6",
				"L Heating and hotwater appliances SWAH 7",
				"L Heating and hotwater appliances SWAH 8",
				"L Heating and hotwater appliances SWAH 9",
				"L Heating and hotwater appliances SWAH 11",
				"L Heating and hotwater appliances SWAH 12",*/
				"L Heating and hotwater appliances SWAH 4",
				"L Heating and hotwater appliances SWAH 5",
				"L Heating and hotwater appliances SWAH 6",
				"L Heating and hotwater appliances SWAH 7",
				"L Heating and hotwater appliances SWAH 8",
				"L Heating and hotwater appliances SWAH 9",
				"L Heating and hotwater appliances SWAH 11",
				"L Heating and hotwater appliances SWAH 12"
					
		};
		return bespokeErrorMessageUK;
	}
	
/////////////////////////////////////////////////////////////////////////////////////////////productsLondon////////////////////////////////////////////////////////////////////	
	
	
	private String[] productsLondon(){

		String [] London ={
				
				"L Heating and hotwater appliances boiler 1",
				"L Heating and hotwater appliances boiler 2",
				"L Heating and hotwater appliances boiler 3",
				"L Heating and hotwater appliances boiler 4",
				"L Heating and hotwater appliances boiler 5",
				"L Heating and hotwater appliances boiler 6",
				"L Heating and hotwater appliances boiler 7",
				"L Heating and hotwater appliances boiler 8",
				"L Heating and hotwater appliances FHS 1",
				"L Heating and hotwater appliances FHS 2",
				"L Heating and hotwater appliances FHS 3",
				"L Heating and hotwater appliances FHS 4",
				"L Heating and hotwater appliances boiler 9",
				"L Heating and hotwater appliances boiler 10",
				"L Heating and hotwater appliances boiler 11",
				"L Heating and hotwater appliances boiler 12",
				"L Heating and hotwater appliances FHS 5",
				"L Heating and hotwater appliances FHS 6",
				"L Heating and hotwater appliances FHS 7",
				"L Heating and hotwater appliances FHS 8",
				"L Heating and hotwater appliances SWH 1",
				"L Heating and hotwater appliances SWH 2",
				"L Heating and hotwater appliances SWH 3",
				"L Heating and hotwater appliances SWH 4",
				"L Heating and hotwater appliances SWH 5",
				"L Heating and hotwater appliances SWH 6",
				"L Heating and hotwater appliances SWAH 1",
				"L Heating and hotwater appliances SWAH 2",
				"L Heating and hotwater appliances SWAH 3",
				"L Heating and hotwater appliances boiler 13",
				"L Heating and hotwater appliances boiler 14",
				"L Heating and hotwater appliances FHS 9",
				"L Heating and hotwater appliances boiler 15",
				"L Heating and hotwater appliances FHS 10",
				"L Heating and hotwater appliances SWAH 10",
				"L Heating and hotwater appliances SWH 7",
				"L Heating and hotwater appliances SWH 8",
				"L Heating and hotwater appliances SWH 9"

				/*
				"L Heating and hotwater appliances boiler 1",
				"L Heating and hotwater appliances boiler 2",
				"L Heating and hotwater appliances boiler 3",
				"L Heating and hotwater appliances boiler 4",
				"L Heating and hotwater appliances boiler 5",
				"L Heating and hotwater appliances boiler 6",
				"L Heating and hotwater appliances boiler 7",
				"L Heating and hotwater appliances boiler 8",*/
			/*	"L Heating and hotwater appliances FHS 1",
				"L Heating and hotwater appliances FHS 2",
				"L Heating and hotwater appliances FHS 3",
				"L Heating and hotwater appliances FHS 4",
				"L Heating and hotwater appliances boiler 9",
				"L Heating and hotwater appliances boiler 10",
				"L Heating and hotwater appliances boiler 11",
				"L Heating and hotwater appliances boiler 12",*/
				/*"L Heating and hotwater appliances FHS 5",
				"L Heating and hotwater appliances FHS 6",
				"L Heating and hotwater appliances FHS 7",
				"L Heating and hotwater appliances FHS 8",
				"L Heating and hotwater appliances SWH 1",
				"L Heating and hotwater appliances SWH 2",
				"L Heating and hotwater appliances SWH 3",
				"L Heating and hotwater appliances SWH 4",
				"L Heating and hotwater appliances SWH 5",
				"L Heating and hotwater appliances SWH 6",
				"L Heating and hotwater appliances SWAH 1",
				"L Heating and hotwater appliances SWAH 2",
				"L Heating and hotwater appliances SWAH 3",
				"L Heating and hotwater appliances boiler 13",
				"L Heating and hotwater appliances boiler 14",
				"L Heating and hotwater appliances FHS 9",
				"L Heating and hotwater appliances boiler 15",
				"L Heating and hotwater appliances FHS 10",
				"L Heating and hotwater appliances SWAH 10",*/
				
				/*"L Heating and hotwater appliances SWH 7",
				"L Heating and hotwater appliances SWH 8",
				"L Heating and hotwater appliances SWH 9"

				*/
				
				
				
			
				
				//"L Heating and hotwater appliances 13",
				//"L Heating and hotwater appliances 17",
				//"L Heating and hotwater appliances 18",
				//"L Heating and hotwater appliances 23",
				//"L Heating and hotwater appliances 24",
				//"L Heating and hotwater appliances SWAH 4",
				//"L Heating and hotwater appliances SWAH 5",
				//"L Heating and hotwater appliances SWAH 6",
				//"L Heating and hotwater appliances SWAH 7",
				//"L Heating and hotwater appliances SWAH 8",
				//"L Heating and hotwater appliances SWAH 9",
				/*"L Catering equipment CC 2",
				"L Catering equipment CC 4",
				"L Catering equipment CC 6",
				"L Catering equipment CC 8",
				"L Catering equipment CC 10",
				"L Catering equipment CC 12",
				"L Catering equipment CC 14",
				"L Catering equipment CC 16",
				"L Catering equipment CC 18",
				"L Catering equipment CC 20",
				"L Catering equipment CC 22",
				"L Catering equipment CC 24",
				"L Catering equipment Domestic 2",
				"L Catering equipment Domestic 4",
				"L Catering equipment Domestic 6",
				"L Catering equipment Domestic 8",
				"L Catering equipment Domestic 10",
				"L Catering equipment Domestic 12",
				"L Laundry Domestic 2",
				"L Laundry Commercial 2",
				"L Laundry Commercial 4",
				"L Heating and hotwater appliances 26",
				"L Heating and hotwater appliances 27",
				"L Heating and hotwater appliances 28",
				"L Heating and hotwater appliances 29",
				"L Heating and hotwater appliances 30",
				"L Heating and hotwater appliances SWAH 10",
				"L Heating and hotwater appliances SWAH 11",
				"L Heating and hotwater appliances SWAH 12",*/
			/*	"L Heating and hotwater appliances SWH 10",
				"L Heating and hotwater appliances SWH 11",
				"L Heating and hotwater appliances SWH 12"*/

				
				
				
				//London Pricing  -Yes
				/*"L Heating and hotwater appliances 1",
				"L Heating and hotwater appliances 2",
				"L Heating and hotwater appliances 3",
				"L Heating and hotwater appliances 4",
				"L Heating and hotwater appliances SWH 1",
				"L Heating and hotwater appliances SWH 2",
				"L Catering equipment CC 1",
				"L Catering equipment CC 3",
				"L Catering equipment CC 5",
				"L Catering equipment CC 7",
				"L Catering equipment CC 9",
				"L Catering equipment CC 11",
				"L Catering equipment CC 13",
				"L Catering equipment CC 15",
				"L Catering equipment CC 17",
				"L Catering equipment CC 19",
				"L Catering equipment CC 21",
				"L Catering equipment CC 23",
				"L Catering equipment Domestic 1",
				"L Catering equipment Domestic 3",
				"L Catering equipment Domestic 5",
				"L Catering equipment Domestic 7",
				"L Catering equipment Domestic 9",
				"L Catering equipment Domestic 11",
				"L Laundry Domestic 1",
				"L Laundry Commercial 1",
				"L Laundry Commercial 3",
				"L Heating and hotwater appliances 25",
				"L Heating and hotwater appliances SWH 9"*/


/*
				 "L Heating and hotwater appliances boiler 1",
				       "L Heating and hotwater appliances boiler 2",
				       "L Heating and hotwater appliances boiler 3",
				       "L Heating and hotwater appliances boiler 4",
				       
				 "L Heating and hotwater appliances SWH 1",
				 
				 
				       "L Heating and hotwater appliances SWH 2",
				       "L Heating and hotwater appliances SWH 3",
				 "L Heating and hotwater appliances SWAH 1",
				       "L Heating and hotwater appliances SWAH 2",
				       "L Heating and hotwater appliances SWAH 3",
				        "L Catering equipment CC 8",
				       "L Catering equipment CC 9",*/
				     /*  "L Catering equipment CC 10",
				   "L Catering equipment Domestic 1",
				       "L Catering equipment Domestic 2",
				 "L Laundry Domestic 1",
				       "L Laundry Domestic 2",*/
				
				
				
				
				
				
				
				
				/*"L Heating and hotwater appliances boiler 1",
    			"L Heating and hotwater appliances boiler 2",
    			"L Heating and hotwater appliances boiler 3",
    			"L Heating and hotwater appliances boiler 4",
    			"L Heating and hotwater appliances boiler 5",
    			"L Heating and hotwater appliances boiler 6",
    			"L Heating and hotwater appliances boiler 7",
    			"L Heating and hotwater appliances boiler 8",
    			"L Heating and hotwater appliances boiler 9",
    			"L Heating and hotwater appliances boiler 10",
    			"L Heating and hotwater appliances boiler 11",
    			"L Heating and hotwater appliances boiler 12",
    			"L Heating and hotwater appliances boiler 13",
    			"L Heating and hotwater appliances boiler 14",
    			"L Heating and hotwater appliances boiler 15",
    			"L Heating and hotwater appliances boiler 16",
    			"L Heating and hotwater appliances boiler 17",
    			"L Heating and hotwater appliances boiler 18",
    			"L Heating and hotwater appliances boiler 19",
    			"L Heating and hotwater appliances boiler 20",
    			"L Heating and hotwater appliances boiler 21",
    			"L Heating and hotwater appliances boiler 22",
    			"L Heating and hotwater appliances boiler 23",
    			"L Heating and hotwater appliances boiler 24",
    			"L Heating and hotwater appliances SWH 1",
    			"L Heating and hotwater appliances SWH 2",
    			"L Heating and hotwater appliances SWH 3",
    			"L Heating and hotwater appliances SWH 4",
    			"L Heating and hotwater appliances SWH 5",
    			"L Heating and hotwater appliances SWH 6",
    			"L Heating and hotwater appliances SWH 7",
    			"L Heating and hotwater appliances SWH 8",
    		
    			

    			
    		*/
    			
		};
		return London;
	}
/////////////////////////////////////////////////////////////////////////////////////////////////productsRestOfUK/////////////////////////////////////////////////////////////////////////////////
	private String[] productsRestOfUK(){

		String [] RestOfUK ={
				
				
				//"Heating and hotwater appliances boiler 1"
				//"Heating and hotwater appliances boiler 2",
				//"Heating and hotwater appliances boiler 3",
				/*"Heating and hotwater appliances boiler 4",
				"Heating and hotwater appliances boiler 5",*/
				"Heating and hotwater appliances boiler 6"
				/*"Heating and hotwater appliances boiler 7",
				"Heating and hotwater appliances boiler 8",
				"Heating and hotwater appliances FHS 1",
				"Heating and hotwater appliances FHS 2",
				"Heating and hotwater appliances FHS 3",
				"Heating and hotwater appliances FHS 4",
				"Heating and hotwater appliances boiler 9",
				"Heating and hotwater appliances boiler 10",
				"Heating and hotwater appliances boiler 11",
				"Heating and hotwater appliances boiler 12",
				"Heating and hotwater appliances FHS 5",
				"Heating and hotwater appliances FHS 6",
				"Heating and hotwater appliances FHS 7",
				"Heating and hotwater appliances FHS 8",
				"Heating and hotwater appliances SWH 1",
				"Heating and hotwater appliances SWH 2",
				"Heating and hotwater appliances SWH 3",
				"Heating and hotwater appliances SWH 4",
				"Heating and hotwater appliances SWH 5",
				"Heating and hotwater appliances SWAH 1",
				"Heating and hotwater appliances SWAH 2",
				"Heating and hotwater appliances SWAH 3",
				"Heating and hotwater appliances boiler 13",
				"Heating and hotwater appliances boiler 14",
				"Heating and hotwater appliances FHS 9",
				"Heating and hotwater appliances boiler 15",
				"Heating and hotwater appliances FHS 10",
				"Heating and hotwater appliances SWAH 10",
				"Heating and hotwater appliances SWH 6",
				"Heating and hotwater appliances SWH 7",
				"Heating and hotwater appliances SWH 8",
				"Heating and hotwater appliances SWH 9"*/

				
				
				/************************** existing customer "yes"******************************************/
				
				
			/*	"Heating and hotwater appliances boiler 1",
				"Heating and hotwater appliances boiler 2",
				"Heating and hotwater appliances boiler 3",
				"Heating and hotwater appliances boiler 4",
				"Heating and hotwater appliances SWH 1",
				"Heating and hotwater appliances SWH 2",*/
				
			/*	"Catering equipment CC 1",
	
				"Catering equipment CC 3",
	
				"Catering equipment CC 5",
		
				"Catering equipment CC 7",
		
				"Catering equipment CC 9",
			
				"Catering equipment CC 11",
	
				"Catering equipment CC 13",

				"Catering equipment CC 15",

				"Catering equipment CC 17",

				"Catering equipment CC 19",
			
				"Catering equipment CC 21",

				"Catering equipment CC 23",
				
				"Catering equipment Domestic 1",

				"Catering equipment Domestic 3",
				"Catering equipment Domestic 5",
	
				"Catering equipment Domestic 7",
			
				"Catering equipment Domestic 9",
			
				"Catering equipment Domestic 11",
				"Laundry Domestic 1",
				"Laundry Commercial 1",

				"Laundry Commercial 3",
				
				"Heating and hotwater appliances boiler 25",
				
				"Heating and hotwater appliances SWH 8",
				*/
				
				
				/************************** existing customer "No"******************************************/			
				
			/*	"Heating and hotwater appliances boiler 5",
				"Heating and hotwater appliances boiler 6",
				"Heating and hotwater appliances boiler 7",
				"Heating and hotwater appliances boiler 8",
				"Heating and hotwater appliances boiler 9",
				"Heating and hotwater appliances boiler 10",
				"Heating and hotwater appliances boiler 11",
				"Heating and hotwater appliances boiler 12",
				"Heating and hotwater appliances boiler 13",
				"Heating and hotwater appliances boiler 14",
				"Heating and hotwater appliances boiler 15",
				"Heating and hotwater appliances boiler 16",*/
				/*"Heating and hotwater appliances boiler 17",
				"Heating and hotwater appliances boiler 18",
				"Heating and hotwater appliances boiler 19",
				"Heating and hotwater appliances boiler 20",
				"Heating and hotwater appliances boiler 21",
				"Heating and hotwater appliances boiler 22",
				"Heating and hotwater appliances boiler 23",
				"Heating and hotwater appliances boiler 24",*/
		/*		"Heating and hotwater appliances SWH 1",
				"Heating and hotwater appliances SWH 2",*/
				/*"Heating and hotwater appliances SWH 3",
				"Heating and hotwater appliances SWH 4",
				"Heating and hotwater appliances SWH 5",
				"Heating and hotwater appliances SWH 6",
				"Heating and hotwater appliances SWH 7",
				"Heating and hotwater appliances SWAH 1",
				"Heating and hotwater appliances SWAH 2",
				"Heating and hotwater appliances SWAH 3",*/
			/*	"Heating and hotwater appliances SWAH 4",
				"Heating and hotwater appliances SWAH 5",
				"Heating and hotwater appliances SWAH 6",
				"Heating and hotwater appliances SWAH 7",
				"Heating and hotwater appliances SWAH 8",
				"Heating and hotwater appliances SWAH 9",*/

				/*"Catering equipment CC 2",

				"Catering equipment CC 4",
	
				"Catering equipment CC 6",
	
				"Catering equipment CC 8",

				"Catering equipment CC 10",
			
				"Catering equipment CC 12",
			
				"Catering equipment CC 14",
		
				"Catering equipment CC 16",
			
				"Catering equipment CC 18",
		
				"Catering equipment CC 20",
				
				"Catering equipment CC 22",
		
				"Catering equipment CC 24",
	
				"Catering equipment Domestic 2",
		
				"Catering equipment Domestic 4",
			
				"Catering equipment Domestic 6",
	
				"Catering equipment Domestic 8",
	
				"Catering equipment Domestic 10",
	
				"Catering equipment Domestic 12",

				"Laundry Domestic 2",
		
				"Laundry Commercial 2",

				"Laundry Commercial 4",
		
				
				"Heating and hotwater appliances boiler 26",
	
			    "Heating and hotwater appliances boiler 27",
				"Heating and hotwater appliances boiler 28",
				"Heating and hotwater appliances boiler 29",
				"Heating and hotwater appliances boiler 30",
				"Heating and hotwater appliances SWAH 10",

				"Heating and hotwater appliances SWH 9",
				"Heating and hotwater appliances SWH 10",
				"Heating and hotwater appliances SWH 11",
				"Heating and hotwater appliances SWH 12",
				*/
				
		};
		return RestOfUK;
	}


}
