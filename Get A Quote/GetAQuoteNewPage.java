package bg.framework.app.functional.page.Slingshot;

import java.util.List;
import java.util.Properties;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import bg.framework.app.functional.common.ApplicationConfig;
import bg.framework.app.functional.page.common.BasePage;
import bg.framework.app.functional.util.PropertyLoader;
import bg.framework.app.functional.util.Report;
import bg.framework.app.functional.entities.GetAQuoteNew;

public class GetAQuoteNewPage extends BasePage{
	
	public static String File_Name = "resources/Slingshot/GetaQuoteNewPage.properties";
	
	private static Properties pageProperties = new PropertyLoader(File_Name).load();
	
	
	
	public void NavigatetoGaqpage()
	{
		browser.wait(2000);
		
		browser.open(ApplicationConfig.APP_BG_URL+pageProperties.getProperty("GetAQuote.Navigationlink"));

		browser.wait(3000);
	}
	
	public void EnterPostCode(GetAQuoteNew gaqnew)
	{
		browser.wait(2000);
		
		if(browser.isElementVisibleWithXpath(pageProperties.getProperty("GetAQuote.Title")))
		{
			verifyAndInputById(pageProperties.getProperty("GetAQuote.postcode"),"Post Code",gaqnew.getpostcode());
			browser.wait(2000);
			verifyAndClickWithXpath(pageProperties.getProperty("GetAQuote.searchbutton"),"Find Address");
			browser.wait(2000);
			
			Select select = new Select(browser.getElementByXpath(pageProperties.getProperty("GetAQuote.findaddress")));
			select.selectByIndex(gaqnew.getAddress());
			
			//verifyAndSelectDropDownBoxByXpath(pageProperties.getProperty("GetAQuote.findaddress"),"Find Address",gaqnew.getAddress());
			
			Report.updateTestLog("Step One Completed", "WARN");
			browser.wait(2000);
			verifyAndClickWithXpath(pageProperties.getProperty("GetAQuote.NextButton"),"Next Button");
			browser.wait(2000);
			
				}
	}
	
	public void verifyEditFunctionality(GetAQuoteNew gaqnew)
	{
		if(browser.isElementVisibleWithXpath(pageProperties.getProperty("GetAQuote.editbutton")))
		{
			verifyAndClickWithXpath(pageProperties.getProperty("GetAQuote.editbutton"),"Edit Button");
			browser.wait(2000);
			Select select = new Select(browser.getElementByXpath(pageProperties.getProperty("GetAQuote.findaddress")));
			select.selectByIndex(gaqnew.getAddress());
			Report.updateTestLog("Edit process Completed", "WARN");
			verifyAndClickWithXpath(pageProperties.getProperty("GetAQuote.NextButton"),"Next Button");
			browser.wait(2000);
			
		}
	}
	
	public void verifymeterdeatilsFunctionality(GetAQuoteNew gaqnew)
	{
		if(browser.isElementVisibleWithXpath(pageProperties.getProperty("GetAQuote.savemeterdetails")))
		{
			verifyAndClickWithXpath(pageProperties.getProperty("GetAQuote.Savemeterdeatilsbutton"),"Save Meter Deatils Button");
		}
		
		if(browser.isElementVisibleWithXpath(pageProperties.getProperty("GetAQuote.EnergyUsage")))
		{
			if(browser.isElementVisibleWithXpath(pageProperties.getProperty("GetAQuote.Electricitypanel"))){
				verifyAndInputById(pageProperties.getProperty("GetAQuote.elecvalue"),"Electricty Consumption",gaqnew.getElecMeter());
				
				Select select = new Select(browser.getElementByXpath(pageProperties.getProperty("GetAQuote.elecconsumption")));
				select.selectByIndex(1);
			}
			if(browser.isElementVisibleWithXpath(pageProperties.getProperty("GetAQupte.Gasusagepanel")))
			{
				verifyAndInputById(pageProperties.getProperty("GetAQuote.gasvalue"),"Post Code",gaqnew.getgasMeter());
				Select select = new Select(browser.getElementByXpath(pageProperties.getProperty("GetAQuote.gascconsumption")));
				select.selectByIndex(1);				
			}
		}
		browser.wait(2000);
		verifyAndClickWithXpath(pageProperties.getProperty("GetAQuote.Button"),"Get A Quote Button");
		Report.updateTestLog("Energy Consumption Detils", "WARN");
		browser.wait(18000);
	}
	
	public void verifymeterdeatilsFunctionality_Renewals(GetAQuoteNew gaqnew)
	{
		
		if(browser.isElementVisibleWithXpath(pageProperties.getProperty("GetAQuote.EnergyUsage")))
		{
			if(browser.isElementVisibleWithXpath(pageProperties.getProperty("GetAQuote.Electricitypanel"))){
				verifyAndInputById(pageProperties.getProperty("GetAQuote.elecvalue"),"Post Code",gaqnew.getElecMeter());
				
				Select select = new Select(browser.getElementByXpath(pageProperties.getProperty("GetAQuote.elecconsumption")));
				select.selectByIndex(1);
			}
			if(browser.isElementVisibleWithXpath(pageProperties.getProperty("GetAQupte.Gasusagepanel")))
			{
				verifyAndInputById(pageProperties.getProperty("GetAQuote.gasvalue"),"Post Code",gaqnew.getgasMeter());
				Select select = new Select(browser.getElementByXpath(pageProperties.getProperty("GetAQuote.gascconsumption")));
				select.selectByIndex(1);				
			}
		}
		browser.wait(2000);
		verifyAndClickWithXpath(pageProperties.getProperty("GetAQuote.Button"),"Get A Quote Button");
		Report.updateTestLog("Energy Consumption Detils", "WARN");
		browser.wait(18000);
	}
	
	public void verifysavemeterdeatilsFunctionality(GetAQuoteNew gaqnew)
	{
		if(browser.isElementVisibleWithXpath(pageProperties.getProperty("GetAQuote.savemeterdetails")))
		{
			verifyAndClickWithXpath(pageProperties.getProperty("GetAQuote.Savemeterdeatilsbutton"),"Save Meter Deatils Button");
		}
	}
	
	public void verifysavemeterdeatilswithinput(GetAQuoteNew gaqnew)
	{
		if(browser.isElementVisibleWithXpath(pageProperties.getProperty("GetAQuote.savemeterdetails")))
		{
			verifyAndInputByXpath(pageProperties.getProperty("GetAQuote.GasMeterDetails"),"Gas meter Details", "1256695874");
			browser.wait(2000);
			verifyAndClickWithXpath(pageProperties.getProperty("GetAQuote.Savemeterdeatilsbutton"),"Save Meter Deatils Button");
		}
	}
	
	public void ElectricityEnergyUsage_Verification(GetAQuoteNew gaqnew)
	{
		if(browser.isElementVisibleWithXpath(pageProperties.getProperty("GetAQuote.EnergyUsage")))
		{
			if(browser.isElementVisibleWithXpath(pageProperties.getProperty("GetAQuote.Electricitypanel"))){
				verifyAndInputById(pageProperties.getProperty("GetAQuote.elecvalue"),"Post Code",gaqnew.getElecMeter());
				
				Select select = new Select(browser.getElementByXpath(pageProperties.getProperty("GetAQuote.elecconsumption")));
				select.selectByIndex(1);
			}	
			
			browser.wait(2000);
			verifyAndClickWithXpath(pageProperties.getProperty("GetAQuote.Button"),"Get A Quote Button");
			Report.updateTestLog("Energy Consumption Detils", "WARN");
			browser.wait(18000);
		}
	}
	
	
	public void GasEnergyUsage_Verification(GetAQuoteNew gaqnew)
	{
		if(browser.isElementVisibleWithXpath(pageProperties.getProperty("GetAQuote.EnergyUsage")))
		{
			if(browser.isElementVisibleWithXpath(pageProperties.getProperty("GetAQupte.Gasusagepanel")))
			{
				verifyAndInputById(pageProperties.getProperty("GetAQuote.gasvalue"),"Post Code",gaqnew.getgasMeter());
				Select select = new Select(browser.getElementByXpath(pageProperties.getProperty("GetAQuote.gascconsumption")));
				select.selectByIndex(1);				
			
			}	
			browser.wait(2000);
			verifyAndClickWithXpath(pageProperties.getProperty("GetAQuote.Button"),"Get A Quote Button");
			Report.updateTestLog("Energy Consumption Detils", "WARN");
			browser.wait(18000);
		}
	}
	
	
	public void verifyQuotesummarypage_Electricity()
	{
		if(browser.isElementVisibleWithXpath(pageProperties.getProperty("GetAQuote.QuoteTiltle")))
		{
			System.out.println("Enterred in Quote Sumary Page");
			
			verifyIsElementVisibleWithXpath(pageProperties.getProperty("GetAQuote.referencenumber"),"Refrence Number");
			verifyIsElementVisibleWithXpath(pageProperties.getProperty("GetAQuote.Address"),"Address");
			verifyIsElementVisibleWithXpath(pageProperties.getProperty("GetAQuote.MPANnumber"),"MPAN is");
			verifyIsElementVisibleWithXpath(pageProperties.getProperty("GetAQuote.Discounts"),"Discount Section");
			verifyIsElementVisibleWithXpath(pageProperties.getProperty("GetAQuote.ElecRecalculatesection"),"Electricity recalculate Section");
		}
		
		else
		{
			Report.updateTestLog("Get A Quote Summary Page not Displayed", "Fail");
		}
	}
	
	
	public void verifyQuotesummarypage_Gas()
	{
		if(browser.isElementVisibleWithXpath(pageProperties.getProperty("GetAQuote.QuoteTiltle")))
		{
			System.out.println("Enterred in Quote Sumary Page");
			
			verifyIsElementVisibleWithXpath(pageProperties.getProperty("GetAQuote.referencenumber"),"Refrence Number");
			verifyIsElementVisibleWithXpath(pageProperties.getProperty("GetAQuote.Address"),"Address");
			verifyIsElementVisibleWithXpath(pageProperties.getProperty("GetAQuote.MPRNnumber"),"MPRN is");
			verifyIsElementVisibleWithXpath(pageProperties.getProperty("GetAQuote.Discounts"),"Discount Section");
			verifyIsElementVisibleWithXpath(pageProperties.getProperty("GetAQuote.GasRecalculateSection"),"Gas recalculate Section");
		}
		
		else
		{
			Report.updateTestLog("Get A Quote Summary Page not Displayed", "Fail");
		}
	}
		
		

	public void verifyQuotesummarypage()
	{
		if(browser.isElementVisibleWithXpath(pageProperties.getProperty("GetAQuote.QuoteTiltle")))
		{
			System.out.println("Enterred in Quote Sumary Page");
			
			verifyIsElementVisibleWithXpath(pageProperties.getProperty("GetAQuote.referencenumber"),"Refrence Number");
			verifyIsElementVisibleWithXpath(pageProperties.getProperty("GetAQuote.Address"),"Address");
			verifyIsElementVisibleWithXpath(pageProperties.getProperty("GetAQuote.MPANnumber"),"MPAN is");
			verifyIsElementVisibleWithXpath(pageProperties.getProperty("GetAQuote.MPRNnumber"),"MPRN is");
			verifyIsElementVisibleWithXpath(pageProperties.getProperty("GetAQuote.Filternumber"),"Filter Section");
			verifyIsElementVisibleWithXpath(pageProperties.getProperty("GetAQuote.Discounts"),"Discount Section");
			verifyIsElementVisibleWithXpath(pageProperties.getProperty("GetAQuote.ElecRecalculatesection"),"Electricity recalculate Section");
			verifyIsElementVisibleWithXpath(pageProperties.getProperty("GetAQuote.GasRecalculateSection"),"Gas recalculate Section");
		}
		
		else
		{
			Report.updateTestLog("Get A Quote Summary Page not Displayed", "Fail");
		}
	}
	
	
	public void verifyElectricityQuote()
	{
		browser.wait(2000);
		verifyAndClickWithXpath(pageProperties.getProperty("GetAQuote.gascheckbox"),"Gas UnChecked");
		browser.wait(2000);
		if(browser.isElementVisibleWithXpath(pageProperties.getProperty("GetAQuote.gasquotedetails")))
		{
			Report.updateTestLog("Gas Quote Details Displayed even uncheked", "Fail");
		}
		else
		{
			Report.updateTestLog("Gas Quote Details not Displayed", "PASS");
			Report.updateTestLog("Electricity Quote Details", "WARN");
		}
	   browser.wait(2000);
	   verifyAndClickWithXpath(pageProperties.getProperty("GetAQuote.gascheckbox"),"Gas box checked");
	   browser.wait(2000);
	   verifyAndClickWithXpath(pageProperties.getProperty("GetAQuote.ElectCheckbox"),"Electricity box unchecked");
	   browser.wait(2000);
	   if(browser.isElementVisibleWithXpath(pageProperties.getProperty("GetAQuote.Elecquotedeatils")))
		{
			Report.updateTestLog("Electricity Quote Details Displayed even uncheked", "Fail");
		}
		else
		{
			Report.updateTestLog("Electricity Quote Details not Displayed", "PASS");
			Report.updateTestLog("Gas Quote Details", "WARN");
		}
	    verifyAndClickWithXpath(pageProperties.getProperty("GetAQuote.ElectCheckbox"),"Electricity box checked");
	    browser.wait(2000);
	   
	}
	
	public void verifydropdown_filters_check()
	{
		System.out.println("*************************Entered into Loop*******************");
		
		List<String> contract = browser.getFromDropBox("id", pageProperties.getProperty("GetAQuote.contractlenght"));
		System.out.println("Enter into contract");
		System.out.println(contract.size());
		
		int iteration = 1;
		
		for(int i = 0 ;i <contract.size();i++)
		{
			browser.selectfromDropBox("id", pageProperties.getProperty("GetAQuote.contractlenght"),contract.get(i));
			String name = contract.get(i);
			List<String> price = browser.getFromDropBox("id",pageProperties.getProperty("GetAQuote.Priceplan"));
			System.out.println("Enter into price");
			System.out.println(price.size());
			
			browser.wait(2000);
			
			for(int j = 0; j < price.size();j++)
			{
				browser.selectfromDropBox("id", pageProperties.getProperty("GetAQuote.Priceplan"),price.get(j));
				
				List<String> product = browser.getFromDropBox("id",pageProperties.getProperty("GetAQuote.ProductType"));
				System.out.println("Enter into Product");
				System.out.println(product.size());
				String pricec = price.get(j);
				browser.wait(2000);
				
				for(int k = 0; k < product.size();k++)
				{
					browser.selectfromDropBox("id", pageProperties.getProperty("GetAQuote.ProductType"),product.get(k));
					String producttype = product.get(k);
					browser.wait(1000);
					Report.updateTestLog("Contract::"  +name+   "Price Plan::" +pricec+ "Producttype::"  +producttype , "WARN");
				}
				
			}
			
		}
		    browser.wait(2000);
		    verifyAndClickWithXpath(pageProperties.getProperty("GetAQuote.Clearfilter"),"Clear Filter");
		    browser.wait(2000);
		    
		    try{
		    verifyAndClickWithXpath(pageProperties.getProperty("GetAQuote.Elecseemoredeatils"),"Electricity see more Details link");
		    }
		    catch(Exception e)
		    {
		    	Report.updateTestLog("Electricity see more Details link not displayed due to Gas Quote", "Done");
		    }
		    
		    try{
		    verifyAndClickWithXpath(pageProperties.getProperty("GetAQuote.gasseemoredeatils"),"Gas see more Details link");
		    }
		    catch(Exception e)
		    {
		    	Report.updateTestLog("Gas see more Details link not displayed due to Gas Quote", "Done");
		    }
		    Report.updateTestLog("After Expand Gas and Electricity", "WARN");
		    browser.wait(5000);
	}
	
	public void verify_recalculatecalender()
	{
		browser.wait(5000);
		int month = 2;
		if(browser.isElementVisibleWithXpath(pageProperties.getProperty("GetAQuote.Recalculateelec")))
	{
			System.out.println("Enetering into Date");
			verifyAndClickWithXpath(pageProperties.getProperty("GetAQuote.Contractstartdateelec"),"Electricity Calender Clicked");
			System.out.println("Success with Date");
			for(int i=0 ;i<=month;i++)
			{
				System.out.println("Enter in Date Picker");
				verifyAndClickWithXpath(pageProperties.getProperty("GetAQuote.Datepickerprevious"),"Next Date Picker Selected"+i);
			}
			
			
	}
		browser.wait(5000);
		calender_fuction();
		
	}
	
	public void calender_fuction()
	{
		browser.wait(5000);
		
		int rowcount = browser.getRowCountByXpath(pageProperties.getProperty("GetAQuote.Datepickertable"));
		
		int cloumncount = browser.getColCountByXpath(pageProperties.getProperty("GetAQuote.Datepickertable"));
		
		System.out.println("RowCount" +rowcount+  "Column Count" +cloumncount);
		browser.wait(5000);
		for(int s = 1 ; s<rowcount;s++)
		{
			for(int h = 1; h<cloumncount;h++)
			{
				 browser.clickWithXpath(pageProperties.getProperty("GetAQuote.Dateselectorwithsingle"+"//tr["+s+"]+/td["+h+"]/a"));
				 
				 System.out.println("Enter into Date Picker with ::" +s+ "Row" +h+ "column");
			}
		}
	}
	
	public void verify_Buyonline()
	{
		try{
			if(browser.isElementVisibleWithXpath(pageProperties.getProperty("GetAQuote.pricelist"))){
					        
	            verifyAndClickWithXpath(pageProperties.getProperty("GetAQuote.Byonlinebuttonelec"),"BuyOnline Clicked for Elec");
		   }	
			else
			{
				 verifyAndClickWithXpath(pageProperties.getProperty("GetAQuote.Byonlinebuttongas"),"BuyOnline Clicked for gas");
			}
				
	}
		catch(Exception e)
		{
			Report.updateTestLog("Customer is not elegible for Buy Online ", "WARN");
		}
		
		browser.wait(5000);
		if(browser.isElementVisibleWithXpath(pageProperties.getProperty("GetAQuote.ByonlineOverlay"))){
	        
			verifyAndClickWithXpath(pageProperties.getProperty("GetAQuote.ContinueOnline"),"Continue Online");  
			browser.wait(3000);
		}
		
	}
	
	public void Gas_Buyonline()
	{
		try{
			if(browser.isElementVisibleWithXpath(pageProperties.getProperty("GetAQuote.pricelist"))){
					        
				 verifyAndClickWithXpath(pageProperties.getProperty("GetAQuote.Byonlinebuttongas"),"BuyOnline Clicked for gas");
				 
				 if(browser.isElementVisibleWithXpath(pageProperties.getProperty("GetAQuote.ByonlineOverlay"))){
				        
					 browser.wait(4000);
						verifyAndClickWithXpath(pageProperties.getProperty("GetAQuote.ContinueOnline"),"Continue Online");  
					}
			}
				
	}
		catch(Exception e)
		{
			Report.updateTestLog("Customer is not elegible for Buy Online ", "WARN");
		}
		
		browser.wait(5000);
		if(browser.isElementVisibleWithXpath(pageProperties.getProperty("GetAQuote.ByonlineOverlay"))){
	        
			verifyAndClickWithXpath(pageProperties.getProperty("GetAQuote.ContinueOnline"),"Continue Online");  
		}	
	}
	
	public void verifyandenter_customerdeatils(GetAQuoteNew gaqnew)
	{
		
		browser.wait(5000);
		try{
			if(browser.isElementVisibleWithXpath(pageProperties.getProperty("GetAQuote.MainPage"))){
				browser.wait(2000);
				verifyAndInputById(pageProperties.getProperty("GetAQuote.firstname"),"First name",gaqnew.getFirstname());
				verifyAndInputById(pageProperties.getProperty("GetAQuote.Lastname"),"Last Name",gaqnew.getLastName());
				verifyAndInputById(pageProperties.getProperty("GetAQuote.Bname"),"Business name",gaqnew.getBusinessname());
				verifyAndInputById(pageProperties.getProperty("GetAQuote.Email"),"Email Address",gaqnew.getEmailid());
				verifyAndInputById(pageProperties.getProperty("GetAQuote.telephonenumber"),"Telephonenumber",gaqnew.getTelePhonenumber());
				Report.updateTestLog("Customer Details Filled ", "WARN");
				browser.wait(2000);
		}	
						
		}
			catch(Exception e)
		{
				Report.updateTestLog("Customer Details Disable for Logged in Customer ", "WARN");
			
		}
		browser.wait(2000);
		verifyAndClickWithXpath(pageProperties.getProperty("GetAQuote.sendbutton"),"Buy Online");
		browser.wait(30000);
	}
	
	public void GetAQuoteSummarypage()
	{
		browser.wait(2000);
		if(browser.isElementVisibleWithXpath(pageProperties.getProperty("GetAQuote.Thankstitle"))){
			
			verifyIsElementVisibleWithXpath(pageProperties.getProperty("GetAQuote.Refrencenumber"),"Reference Number");
			Report.updateTestLog("Thank you page ", "WARN");
			browser.wait(2000);
			verifyIsElementVisibleWithXpath(pageProperties.getProperty("GetAQuote.summaryresult"),"Summary Result");
			browser.wait(2000);
			verifyAndClickWithXpath(pageProperties.getProperty("GetAQupte.Thankyoupagebutton"),"Buy Online button");
			browser.wait(25000);
		}
	}
	
	public void VerifyRenewalcontract()
	{
		if(browser.isElementVisibleWithXpath(pageProperties.getProperty("GetAQuote.pricelist"))){
			  
			verifyIsElementVisibleWithXpath(pageProperties.getProperty("GetAQuote.Renewalslink"),"Renewals");
			
			String text = browser.getTextByXpath(pageProperties.getProperty("GetAQuote.Renewalslink"));
			
			Report.updateTestLog("Renewals Displayed in Quote Summary page::" +text+ "is displayed", "PASS");
			
		}
		
	}
	
	public void verifySaveandUpgrade()
	{
		if(browser.isElementVisibleWithXpath(pageProperties.getProperty("GetAQuote.pricelist"))){
			  
			verifyIsElementVisibleWithXpath(pageProperties.getProperty("GetAQuote.Save"),"Save");
			verifyIsElementVisibleWithXpath(pageProperties.getProperty("GetAQuote.Upgrade"),"Save");
			
			String text2 = browser.getTextByXpath(pageProperties.getProperty("GetAQuote.savetext"));
			String text1 = browser.getTextByXpath(pageProperties.getProperty("GetAQupte.Upgradetext"));
			
			
			Report.updateTestLog("Save and Upgrade Displayed in Quote Summary page:" +text2+ "and"  +text1+ "is displayed", "PASS");
		}
	}
	
/******************************sales*************************/
	
	public void salesBusinessPageVerification()
	{
		browser.wait(getWaitTime());
		
		if(browser.isElementVisibleWithXpath(pageProperties.getProperty("Sales.firstpage"))){
			
			verifyAndClickWithXpath(pageProperties.getProperty("Sales.BuyOnline"),"Buy Online button");
			Report.updateTestLog("Complete Yor Purchase page ", "WARN");
			
		}
		
		browser.wait(getWaitTime());
		if(browser.isElementVisibleWithXpath(pageProperties.getProperty("Sales.Businessname"))){
			
			verifyAndClickWithXpath(pageProperties.getProperty("Sales.selectaddress"),"Address Select");
			verifyIsElementVisibleWithXpath(pageProperties.getProperty("Sales.referencenumber"),"Referece Number");
			
			Select select = new Select(browser.getElementByXpath(pageProperties.getProperty("Sales.inductrytype")));
			select.selectByIndex(3);
			
			browser.wait(2000);
			Select select1 = new Select(browser.getElementByXpath(pageProperties.getProperty("Sales.SICCode")));
			select1.selectByIndex(3);
			
			verifyAndClickWithXpath(pageProperties.getProperty("Sales.charity"),"Charity Option");
			browser.wait(2000);
			verifyAndInputByXpath(pageProperties.getProperty("Sales.charityregnumber"),"Charity Number", "1256695874");
			
			verifyAndClickWithXpath(pageProperties.getProperty("Sales.Nextbutton"),"Next Button");
			browser.wait(2000);
			verifyAndClickWithXpath(pageProperties.getProperty("Sales.confirmbutton"),"Confirm Button");	
			browser.wait(2000);
			Select select2 = new Select(browser.getElementByXpath(pageProperties.getProperty("Sales.noofemployess")));
			select2.selectByIndex(1);
			
			Select select3 = new Select(browser.getElementByXpath(pageProperties.getProperty("Sales.turnover")));
			select3.selectByIndex(1);
			
			Select select4 = new Select(browser.getElementByXpath(pageProperties.getProperty("Sales.gasconcumption")));
			select4.selectByIndex(1);
			
			Select select5 = new Select(browser.getElementByXpath(pageProperties.getProperty("Sales.Elecconcumption")));
			select5.selectByIndex(1);
			browser.wait(2000);
			verifyAndClickWithXpath(pageProperties.getProperty("Sales.Microservisesbutton1"),"Next Button");
		
			Report.updateTestLog("Your Business Details ", "WARN");
			browser.wait(getWaitTime());
			verifyAndClickWithXpath(pageProperties.getProperty("Sales.Confirmbutton"),"Confirm Button");	
		
		}
		
		
	}
	
	public void salesCreditCheckVerification()
	{
		browser.wait(getWaitTime());
	
		if(browser.isElementVisibleWithXpath(pageProperties.getProperty("Sales.CheckCreditTitle"))){
			
			Report.updateTestLog("Credit check details ", "WARN");
			verifyIsElementVisibleWithXpath(pageProperties.getProperty("Sales.referencenumber"),"Referece Number credit Check page");
			verifyAndClickWithXpath(pageProperties.getProperty("Sales.CVContinuebutton"),"Continue Button");
		}
		
	}
	public void salespaymentdeatils()
	{
		browser.wait(getWaitTime());
	
		if(browser.isElementVisibleWithXpath(pageProperties.getProperty("Sales.Payementdeatilstitle"))){
			
			verifyAndClickWithXpath(pageProperties.getProperty("Sales.PaymentAddrsss"),"Payment Address");
			browser.wait(2000);
			verifyAndClickWithXpath(pageProperties.getProperty("Sales.BillingAddress"),"Billing Next Button");
			browser.wait(2000);
			
			verifyAndClickWithXpath(pageProperties.getProperty("Sales.DDsetupoption"),"No Option");
			browser.wait(2000);
			verifyAndClickWithXpath(pageProperties.getProperty("Sales.DDContinuebutton"),"Continue Button");
			browser.wait(2000);
			Report.updateTestLog("Payment Details Page", "WARN");
			
		}
		
		}
	
	public void SalesPurchaseSummarypage()
	{
		browser.wait(getWaitTime());
		if(browser.isElementVisibleWithXpath(pageProperties.getProperty("Sales.SummaryPageTitle"))){
			
			Report.updateTestLog("Purchase Summary Page", "WARN");
			verifyIsElementVisibleWithXpath(pageProperties.getProperty("Sales.newreference"),"Referece Number Purchase Summary");
			
			verifyAndClickWithXpath(pageProperties.getProperty("Sales.Terms&condition"),"Terms and Conditon");
			verifyAndClickWithXpath(pageProperties.getProperty("Sales.Terms&condition1"),"Terms and Conditon1");
			
			Report.updateTestLog("Purchase Summary Page", "PASS");
				
			verifyAndClickWithXpath(pageProperties.getProperty("Sales.Submitbutton"),"Submit Button");
			
			
		}
	}
	
	public void confirmDeatils_verification()
	{
		browser.wait(8000);		
		if(browser.isElementVisibleWithXpath(pageProperties.getProperty("Sales.confirmdeatils1"))){
			
			Select select2 = new Select(browser.getElementByXpath(pageProperties.getProperty("Sales.SearchAddress")));
			select2.selectByIndex(1);
			browser.wait(2000);
			verifyAndClickWithXpath(pageProperties.getProperty("Sales.confirmdeatilspagecofirm"),"Confirmation Address page");			
			Report.updateTestLog("Purchase Summary Page", "PASS");
		}
		else
		{
			Report.updateTestLog("Sorry, there seems to be a problem Displayed in confirmation Page", "WARN");
		}
	}
	
	public void salesFinalpageverifcation()
	{
		
		browser.wait(3000);
		//if(browser.isElementVisibleWithXpath(pageProperties.getProperty("Sales.Thanksorder"))){
			System.out.println("I am in");
			browser.wait(3000);
			if(browser.isElementVisibleWithXpath(pageProperties.getProperty("Sales.confirmationPanel"))){
				
				verifyIsElementVisibleWithXpath(pageProperties.getProperty("Sales.ConfirmationpageQuteregno"),"Quote reference number");
				
				verifyIsElementVisibleWithXpath(pageProperties.getProperty("Sales.ConfirmationpageQuteregno"),"Date Of Order");
				
				verifyIsElementVisibleWithXpath(pageProperties.getProperty("Sales.CompanyAddress"),"Date Of Order");		
				
				Report.updateTestLog("Thank You Order page", "PASS");
			}				
		
	else
		{
			Report.updateTestLog("Confirmation Not Displayed due to Sales Fail", "WARN");
		}
	
	}
	
/*****************************NON-CMA Pages*****************************/
	
	public void industralcustomer(GetAQuoteNew gaqnew)
	{
		browser.wait(3000);
		
		if(browser.isElementVisibleWithXpath(pageProperties.getProperty("GetAQuote.noncmalargebusinesssection"))){
			
			verifyAndInputById(pageProperties.getProperty("GetAQuote.noncmafirstname"),"First name",gaqnew.getFirstname());
			verifyAndInputById(pageProperties.getProperty("GetAQuote.noncmaLastname"),"Last Name",gaqnew.getLastName());
			verifyAndInputById(pageProperties.getProperty("GetAQuote.noncmaBusinessname"),"Business name",gaqnew.getBusinessname());
			verifyAndInputById(pageProperties.getProperty("GetAQuote.noncmaemailid"),"Email ID",gaqnew.getEmailid());
			verifyAndInputById(pageProperties.getProperty("GetAQuote.noncmatelephone"),"Telephone Number",gaqnew.getTelePhonenumber());
			browser.wait(5000);
			
			Report.updateTestLog("Industrial Business customer deatils Filled", "WARN");
			
			verifyAndClickWithXpath(pageProperties.getProperty("GetAQuote.noncmasend"),"Send Object Clicked");			
			
		}
	}
	
	public void industralcustomer_confirmation()
	{
		browser.wait(8000);
		
		if(browser.isElementVisibleWithXpath(pageProperties.getProperty("GetAQuote.noncmaThankyousection"))){
			
			Report.updateTestLog("Industrial Business Confiramtion Page", "WARN");
			
			verifyAndClickWithXpath(pageProperties.getProperty("GetAQuote.noncmacallbacklink"),"Call back Link");	
					
			
			browser.wait(6000);
			
			verifyIsElementVisibleWithXpath(pageProperties.getProperty("GetAQuote.noncmaRequestacallback"),"Request A Call back");
			
			Report.updateTestLog("Industrial Business Call back", "WARN");
			
		}
	}
	
}
