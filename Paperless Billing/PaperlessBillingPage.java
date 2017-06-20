package bg.framework.app.functional.page.Slingshot;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Properties;



import bg.framework.app.functional.entities.DirectDebit;
import bg.framework.app.functional.entities.SMRAccountDetails;
import bg.framework.app.functional.entities.UserProfile;
import bg.framework.app.functional.page.common.BasePage;
import bg.framework.app.functional.util.OnlineDBConnector;
import bg.framework.app.functional.util.PropertyLoader;
import bg.framework.app.functional.util.Report;
import bg.framework.app.functional.util.RobotSendKeys;
import bg.framework.app.functional.util.RunQTP;


public class PaperlessBillingPage extends BasePage {
	
	private final static String FILE_NAME = "resources/Slingshot/PaperlessBilling.properties";
	private static Properties pageProperties = new PropertyLoader(FILE_NAME).load();
    static String timestamp=new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime());

	public void specificacctlpaperlesssetup()
	{
		verifyIsTextPresent("Paperless billing");
		verifyIsTextPresent("This account is setup for the paperless billing");
		verifyAndClickWithXpath(pageProperties.getProperty("PaperlessBillingPage.acctswitchtopaperlessbill_checkbox"), "Switch to paperless billing");
		if(browser.isElementVisibleWithXpath(pageProperties.getProperty("PaperlessBillingPage.acctChangetoPaperlessbillingOverlayTitle")))
	    {
		   String overlayContent = browser.getTextByXpath(pageProperties.getProperty("PaperlessBillingPage.acctChangetoPaperlessbillingOverlayTitle"));
		   System.out.println("overlayContent: "+overlayContent);
		   Report.updateTestLog("Overlay content: "+overlayContent, "PASS");
		   verifyAndClickWithXpath(pageProperties.getProperty("PaperlessBillingPage.acctChangetoPaperlessbillingOverlay_yesbutton"), "Yes"); 
	    }	    
	    verifyIsTextPresent("You have switched to paperless billing");
	}
	
	public void PBDirectDebitLogin(UserProfile userProfile) {
		verifyAndInputById(pageProperties.getProperty("PaperlessBillingPage.Email"), "Email Id", userProfile.getEmail());
		verifyAndInputById(pageProperties.getProperty("PaperlessBillingPage.Password"), "Password", userProfile.getPassword());
	
		//verifyAndClickWithXpath(pageProperties.getProperty("PaperlessBillingPage.NewLoginSubmitXpath"), "Submit button");
		verifyAndClickWithXpath(pageProperties.getProperty("PaperlessBillingPage.NewLoginSubmitXpathNew"), "Submit button");
		System.out.println("==============================================");
		System.out.println(pageProperties.getProperty("PaperlessBillingPage.NewLoginSubmitXpath"));
		//verifyAndClickWithXpath(pageProperties.getProperty("LoginPage.NewLoginSubmitXpath1"), "Submit button");		
		browser.wait(getWaitTime());
		//continueToMyAccount();
		//closeInfoOverlay();
	}
	public void PBDirectDebitLogin_secondtime(UserProfile userProfile) {
		verifyAndInputById(pageProperties.getProperty("PaperlessBillingPage.Email"), "Email Id", userProfile.getNewEmail());
		verifyAndInputById(pageProperties.getProperty("PaperlessBillingPage.Password"), "Password", userProfile.getPassword());
	
		verifyAndClickWithXpath(pageProperties.getProperty("PaperlessBillingPage.NewLoginSubmitXpath"), "Submit button");
		System.out.println("==============================================");
		System.out.println(pageProperties.getProperty("PaperlessBillingPage.NewLoginSubmitXpath"));
		//verifyAndClickWithXpath(pageProperties.getProperty("LoginPage.NewLoginSubmitXpath1"), "Submit button");		
		browser.wait(getWaitTime());
		//continueToMyAccount();
		//closeInfoOverlay();
	}
	public void specificacctlpapersetup()
	{
		verifyIsTextPresent("Paperless billing");
		verifyIsTextPresent("You are currently receiving your bills electronically for all your accounts");
		verifyAndClickWithXpath(pageProperties.getProperty("PaperlessBillingPage.acctswitchtopaperbill_checkbox"), "Switch to paper billing");
		if(browser.isElementVisibleWithXpath(pageProperties.getProperty("PaperlessBillingPage.acctChangetoPaperbillingOverlayTitle")))
	    {
		   String overlayContent = browser.getTextByXpath(pageProperties.getProperty("PaperlessBillingPage.acctChangetoPaperbillingOverlayTitle"));
		   System.out.println("overlayContent: "+overlayContent);
		   Report.updateTestLog("Overlay content: "+overlayContent, "PASS");
		   verifyAndClickWithXpath(pageProperties.getProperty("PaperlessBillingPage.acctChangetoPaperbillingOverlay_yesbutton"), "Yes"); 
	    }	    
	    verifyIsTextPresent("You have switched to paper billing");
	}
	public void gloablpaperlessnotsetup()
	{
		verifyIsTextPresent("Paperless billing");
		verifyIsTextPresent("You don't currently have paperless billing set up for all your accounts");
		verifyAndClickWithXpath(pageProperties.getProperty("PaperlessBillingPage.switchtopaperlessbill_checkbox"), "Switch to paperless");		

	    if(browser.isElementVisibleWithXpath(pageProperties.getProperty("PaperlessBillingPage.ChangetoPaperlessbillingOverlayTitle")))
	    {
		   String overlayContent = browser.getTextByXpath(pageProperties.getProperty("PaperlessBillingPage.ChangetoPaperlessbillingOverlayTitle"));
		   System.out.println("overlayContent: "+overlayContent);
		   Report.updateTestLog("Overlay content: "+overlayContent, "PASS");
		   verifyAndClickWithXpath(pageProperties.getProperty("PaperlessBillingPage.ChangetoPaperlessbillingOverlay_yesbutton"), "Yes"); 
	    }	    
	    verifyIsTextPresent("You have switched to paperless billing");
	    
	}
	

	// Your account (up to 15 accounts) – global paperless Set up	

	public void gloablpaperlesssetup()
	{
		browser.wait(1000);
		verifyIsTextPresent("Paperless billing");
		  browser.wait(2000);
		Report.updateTestLog("paperless biling overlay is displayed", "WARN");
		verifyAndClickWithXpath(pageProperties.getProperty("PaperlessBillingPage.switchtopaperlessbill_checkbox"), "Switch to paperless billing");
		
		if(browser.isElementVisibleWithXpath(pageProperties.getProperty("PaperlessBillingPage.ChangetoPaperlessbillingOverlayTitle")))
	    {
		   String overlayContent = browser.getTextByXpath(pageProperties.getProperty("PaperlessBillingPage.ChangetoPaperlessbillingOverlayTitle"));
		   System.out.println("overlayContent: "+overlayContent);
		   Report.updateTestLog("Overlay content: "+overlayContent, "PASS");
		  
		   verifyAndClickWithXpath(pageProperties.getProperty("PaperlessBillingPage.ChangetoPaperlessbillingOverlay_yesbutton"), " Overlay Yes Button");
		
	    }	
		 browser.wait(2000);
		 verifyIsTextPresent("You have switched to paperless billing");
		 
		String jqueryToCloseOverlay=("$('a.ui-dialog-titlebar-close').trigger('click');");
    	Report.updateTestLog("Paperless Billing Overlay is displayed", "WARN");    	
		browser.executeScript(jqueryToCloseOverlay);
	   
	}
	public void globalpapersetup()
	{
		verifyIsTextPresent("Paperless billing");		
		  browser.wait(2000);		
		verifyAndClickWithXpath(pageProperties.getProperty("PaperlessBillingPage.switchtopaperbill_checkbox"), "Switch to paper billing");
		if(browser.isElementVisibleWithXpath(pageProperties.getProperty("PaperlessBillingPage.ChangetoPaperbillingOverlayTitle")))
	    {
		   String overlayContent = browser.getTextByXpath(pageProperties.getProperty("PaperlessBillingPage.ChangetoPaperbillingOverlayTitle"));
		   System.out.println("overlayContent: "+overlayContent);
		   Report.updateTestLog("Overlay content: "+overlayContent, "PASS");
		   verifyAndClickWithXpath(pageProperties.getProperty("PaperlessBillingPage.ChangetoPaperbillingOverlay_yesbutton"), "Yes"); 
	    }	  
		browser.wait(2000);
		 verifyIsTextPresent("You have switched to paper billing");
		 
		// Report.updateTestLog("Paperless Billing Overlay is displayed", "Pass");
		 String jqueryToCloseOverlay=("$('a.ui-dialog-titlebar-close').trigger('click');"); 	
	      browser.executeScript(jqueryToCloseOverlay);
	}
	
	public void specificpaperlesssetup()
	{
		verifyIsTextPresent("Paperless billing");		
		verifyAndClickWithXpath(pageProperties.getProperty("PaperlessBillingPage.whatisthisoverlay"), "what is this overlay");
		/*String jqueryToCloseOverlay1=("$('a.ui-dialog-titlebar-close').trigger('click');");
		browser.executeScript(jqueryToCloseOverlay1);	    			
		browser.wait(2000);*/
		verifyAndClickWithXpath(pageProperties.getProperty("PaperlessBillingPage.switchtopaperlessbill_checkbox"), "Switch to paperless billing");
		browser.wait(1000);
		if(browser.isElementVisibleWithXpath(pageProperties.getProperty("PaperlessBillingPage.ChangetoPaperlessbillingOverlayTitle")))
	    {
		   String overlayContent = browser.getTextByXpath(pageProperties.getProperty("PaperlessBillingPage.ChangetoPaperlessbillingOverlayTitle"));
		   System.out.println("overlayContent: "+overlayContent);
		   Report.updateTestLog("Overlay content: "+overlayContent, "PASS");		 
		   verifyAndClickWithXpath(pageProperties.getProperty("PaperlessBillingPage.ChangetoPaperlessbillingOverlay_yesbutton"), " Overlay Yes Button"); 
	    }	
		 browser.wait(2000);
		 verifyIsTextPresent("You have switched to paper bills for this account");		 
		 String jqueryToCloseOverlay=("$('a.ui-dialog-titlebar-close').trigger('click');");			
		 browser.executeScript(jqueryToCloseOverlay);
	   
	}		

	public void specificpaperbillsetup()
	{
		verifyIsTextPresent("Paperless billing");
		
		/*verifyAndClickWithXpath(pageProperties.getProperty("PaperlessBillingPage.whatisthisoverlay"), "what is this overlay");
		
		browser.wait(getWaitTime());
		
		verifyAndClickWithXpath(pageProperties.getProperty("PaperlessBillingPage.paperlessoverlayclose"),"Close Overlay");*/
		
		//String jqueryToCloseOverlay=("$('a.ui-dialog-titlebar-close').trigger('click');");
		//browser.executeScript(jqueryToCloseOverlay);
	    //verifyIsTextPresent("You have switched to paper bills for this account");			
	   
		browser.wait(getWaitTime());
		
		verifyAndClickWithXpath(pageProperties.getProperty("PaperlessBillingPage.switchtopaperbill_checkbox"), "Switch to paper billing");
		 browser.wait(2000);
		if(browser.isElementVisibleWithXpath(pageProperties.getProperty("PaperlessBillingPage.ChangetoPaperbillingOverlayTitle")))
	    {
		 
		  // Report.updateTestLog("Overlay Displayed", "WARN");
		   verifyAndClickWithXpath(pageProperties.getProperty("PaperlessBillingPage.ChangetoPaperlessbillingOverlay_yesbutton"), "Yes"); 
		   Report.updateTestLog("Overlay Displayed", "WARN");
	    }	    
		     browser.wait(10000);
		    verifyIsTextPresent("You have switched to paper bills for this account");
			
		    verifyAndClickWithXpath(pageProperties.getProperty("PaperlessBillingPage.ManageAccountcloseoverlay"),"paperless Overlay Closed");
		    
		    //String jqueryToCloseOverlay1=("$('a.ui-dialog-titlebar-close').trigger('click');");	
		    browser.wait(5000);
			//browser.executeScript(jqueryToCloseOverlay1);
			Report.updateTestLog("paperless billing is switched sucessfully", "WARN");
			
			browser.wait(3000);
			
			if(browser.isElementVisibleWithXpath(pageProperties.getProperty("PaperlessBillingPage.ManageAccountsuccess" )))
					{
				Report.updateTestLog("PaperlessBilling Setup", "Pass");
					}
	     
	}
	
	public void collectivegloablpaperlesssetup()
	{
		verifyIsTextPresent("Paperless Bills");
		  browser.wait(2000);
		verifyAndClickWithXpath(pageProperties.getProperty("PaperlessBillingPage.switchtopaperlessbill_checkbox"), "Switch to paperless billing");
		
		if(browser.isElementVisibleWithXpath(pageProperties.getProperty("PaperlessBillingPage.ChangetoPaperlessbillingOverlayTitle")))
	    {
		   String overlayContent = browser.getTextByXpath(pageProperties.getProperty("PaperlessBillingPage.ChangetoPaperlessbillingOverlayTitle"));
		   System.out.println("overlayContent: "+overlayContent);
		   Report.updateTestLog("Overlay content: "+overlayContent, "PASS");
		  
		   verifyAndClickWithXpath(pageProperties.getProperty("PaperlessBillingPage.ChangetoPaperlessbillingOverlay_yesbutton"), " Overlay Yes Button");
		   verifyIsTextPresent("You have switched to paperless billing");
	    }	
		 browser.wait(2000);
		String jqueryToCloseOverlay=("$('a.ui-dialog-titlebar-close').trigger('click');");
    	Report.updateTestLog("Paperless Billing Overlay is displayed", "Pass");
    	verifyIsTextPresent("You have switched to paperless billing");
		browser.executeScript(jqueryToCloseOverlay);
	   
	}
	public void PaperlessJouneyverification()
	{
		verifyIsTextPresent("Paperless Bills");
		  browser.wait(2000);
		verifyAndClickWithXpath(pageProperties.getProperty("PaperlessBillingPage.switchtopaperbill_checkbox"), "Switch to paper billing");
		
		if(browser.isElementVisibleWithXpath(pageProperties.getProperty("PaperlessBillingPage.ChangetoPaperlessbillingOverlayTitle")))
	    {
		   String overlayContent = browser.getTextByXpath(pageProperties.getProperty("PaperlessBillingPage.ChangetoPaperlessbillingOverlayTitle"));
		   System.out.println("overlayContent: "+overlayContent);
		   Report.updateTestLog("Overlay content: "+overlayContent, "PASS");
		   if(overlayContent.equals("Switch back to paper billing"))
		   {
			   Report.updateTestLog("Switch back to paper billing Overlay is displayed Successfully", "Pass");
		   }
		 
	    }	
		 browser.wait(2000);
		String jqueryToCloseOverlay=("$('a.ui-dialog-titlebar-close').trigger('click');");    	
		browser.executeScript(jqueryToCloseOverlay);
	}
	
	
	public void EDITVIVerification()
	{
		 if (browser.isElementVisible("Paperless Bills")) 
		 {
	            Report.updateTestLog("User is  Enrolled as EDI/TVI user in Account Summary Page", "Fail");
	            
	     } else 
	     {
	            Report.updateTestLog("User is  Enrolled as EDI/TVI user in Account Summary Page","Pass");         
	        
		 }
	}
	public void corporateVerification()
	{

		 if (browser.isElementVisible("Paperless Bills")) 
		 {
	            Report.updateTestLog("User is  Enrolled as corporate user in Account Overview Page", "Fail");
	            
	     } else {
	            Report.updateTestLog("User is  Enrolled as corporate user in Account Overview Page","Pass");	         
	        }
		 }
	
	public void gloablpaperlesssetupcloseoverlay()
	{
		verifyIsTextPresent("Paperless billing");		
		verifyAndClickWithXpath(pageProperties.getProperty("PaperlessBillingPage.switchtopaperlessbill_checkbox"), "Switch to paper billing");	
		
		String jqueryToCloseOverlay=("$('a.ui-dialog-titlebar-close').trigger('click');");    	
	    browser.executeScript(jqueryToCloseOverlay);
	    Report.updateTestLog("Switch Paper or Paperless Overlay Closed", "PASS");	    
	}
	public void acctspecificpaperlesssetupcloseoverlay()
	{
		verifyIsTextPresent("Paperless billing");		
		verifyAndClickWithXpath(pageProperties.getProperty("PaperlessBillingPage.switchtopaperlessbill_checkbox"), "Switch to paper billing");	
		
		String jqueryToCloseOverlay=("$('a.ui-dialog-titlebar-close').trigger('click');");    	
	    browser.executeScript(jqueryToCloseOverlay);
	    Report.updateTestLog("Switch Paper or Paperless Overlay Closed", "PASS");	    
	}
	public void gloablpaperlessnotsetupcancelAndclose()
	{
		verifyIsTextPresent("Paperless billing");
		verifyIsTextPresent("You are currently receiving your bills electronically for all your accounts");
		verifyAndClickWithXpath(pageProperties.getProperty("PaperlessBillingPage.switchtopaperlessbill_checkbox"), "Switch to paperless");		
		String jqueryToCloseOverlay=("$('a.ui-dialog-titlebar-close').trigger('click');");
			browser.executeScript(jqueryToCloseOverlay);
			browser.wait(getWaitTime());
		verifyAndClickWithXpath(pageProperties.getProperty("PaperlessBillingPage.switchtopaperlessbill_checkbox"), "Switch to paperless");
		if(browser.isElementVisibleWithXpath(pageProperties.getProperty("PaperlessBillingPage.ChangetoPaperlessbillingOverlayTitle")))
	    {
			verifyAndClickWithXpath(pageProperties.getProperty("PaperlessBillingPage.switchtopaperlessbill_cancelbutton"), "Cancel");
	    }		    
	    
	}
	
	public void morethan15accounts_globally()
	{
		 if (browser.isTextPresent("Paperless billing")) {
	            Report.updateTestLog("Enrolled user is having more then 15 accts and you are not able to find Paperless Billing option", "PASS");
	            
	        } else {
	            Report.updateTestLog("Enrolled user is having more then 15 accts and you are not able to find Paperless Billing option","FAIL");	            
	        }
	}	
	// have to update the Audit Entry details
	 public void VerifyAudit_Emailtriggered(UserProfile userProfile){
		 try
		 {
			OnlineDBConnector dbFunctions = new OnlineDBConnector();
			String date=dbFunctions.DBsysdateDdmmyyhhmi();			
			String[] auditType3 = dbFunctions.getAuditEventTypeIdForMUMV(date,userProfile.getEmail(),"PaperlessBilling:Confirmation mail sent:success:");
			System.out.println("auditType3[0]"+auditType3[0]);
			String data = dbFunctions.getAuditType(auditType3[0]);			
			Report.updateTestLog("Audit id is made in audit table as expected. Audit id: "+auditType3[0]+ " Audit event type is"+data,data.equalsIgnoreCase("BGBUSINESS_SAVE_PAPERLESS_BILLING_MAIL_SENT_SUCCESS")?"PASS":"FAIL");
		 }
		 catch(Exception e)
		 {
			 System.out.println(e);
		 }
			}
		// have to update the Audit Entry details
	 public void VerifyAudit_Emailtriggeredsaveddetails(UserProfile userProfile){
		 try
		 {
			OnlineDBConnector dbFunctions = new OnlineDBConnector();
			String date=dbFunctions.DBsysdateDdmmyyhhmi();			
			String[] auditType3 = dbFunctions.getAuditEventTypeIdForMUMV(date,userProfile.getEmail(),"PaperlessBilling and remainders:Save Details:success:");
			System.out.println("auditType3[0]"+auditType3[0]);
			String data = dbFunctions.getAuditType(auditType3[0]);			
			Report.updateTestLog("Audit id is made in audit table as expected. Audit id: "+auditType3[0]+ " Audit event type is"+data,data.equalsIgnoreCase("BGBUSINESS_SAVE_PAPERLESS_BILLING_AND_REMAINDERS_SUCCESS")?"PASS":"FAIL");
		 }
			 catch(Exception e)
			 {
				 System.out.println(e);
			 }
			}
	 //specific acct paper billing
	 public void VerifyAudit_EmailtriggeredforSpecificAcctPaperBilling(UserProfile userProfile){
			OnlineDBConnector dbFunctions = new OnlineDBConnector();
			String date=dbFunctions.DBsysdateDdmmyyhhmi();			
			String[] auditType3 = dbFunctions.getAuditEventTypeIdForMUMV(date,userProfile.getNewEmail(),"Registered Successfully Inform To ALL Superusers:success");
			System.out.println("auditType3[0]"+auditType3[0]);
			String data = dbFunctions.getAuditType(auditType3[0]);			
			Report.updateTestLog("Audit id is made in audit table as expected. Audit id: "+auditType3[0]+ " Audit event type is"+data,data.equalsIgnoreCase("BGBUSINESS_ADDUSER_REGISTRATION_SUCCESS_MAILSENT_SUPERUSERS")?"PASS":"FAIL");
			}
	 //specific acct paperless billing
	 public void VerifyAudit_EmailtriggeredforSpecificAcctPaperlessBilling(UserProfile userProfile){
			OnlineDBConnector dbFunctions = new OnlineDBConnector();
			String date=dbFunctions.DBsysdateDdmmyyhhmi();			
			String[] auditType3 = dbFunctions.getAuditEventTypeIdForMUMV(date,userProfile.getNewEmail(),"Registered Successfully Inform To ALL Superusers:success");
			System.out.println("auditType3[0]"+auditType3[0]);
			String data = dbFunctions.getAuditType(auditType3[0]);			
			Report.updateTestLog("Audit id is made in audit table as expected. Audit id: "+auditType3[0]+ " Audit event type is"+data,data.equalsIgnoreCase("BGBUSINESS_ADDUSER_REGISTRATION_SUCCESS_MAILSENT_SUPERUSERS")?"PASS":"FAIL");
			}
	
	public void paperlesssetupforindivialaccts()
	{
		verifyIsTextPresent("Paperless billing");
		verifyIsTextPresent("You are currently receiving your bills electronically for specific accounts .You can change each  accounts paperless settings on its  'Account summary' page");
		if(browser.isElementVisibleWithXpath(pageProperties.getProperty("PaperlessBillingPage.ChangetoPaperlessbillingOverlayTitle")))
	    {
		   String overlayContent = browser.getTextByXpath(pageProperties.getProperty("PaperlessBillingPage.ChangetoPaperlessbillingOverlayTitle"));
		   System.out.println("overlayContent: "+overlayContent);
		   Report.updateTestLog("Overlay content: "+overlayContent, "PASS");
		   verifyAndClickWithXpath(pageProperties.getProperty("PaperlessBillingPage.ChangetoPaperlessbillingOverlay_yesbutton"), "Yes"); 
	    }	    
	    verifyIsTextPresent("You have switched to paperless billing");
	}
	public void paperlessBillsforAcctSpecificoptedintopaperless()
	{
		verifyIsTextPresent("Paperless billing");
		verifyIsTextPresent("This account is set up for Paperless bills");
		verifyAndClickWithXpath(pageProperties.getProperty("PaperlessBillingPage.AcctSummary_switchtopaperlessbill_checkbox"), "Switch to paperless");
		if(browser.isElementVisibleWithXpath(pageProperties.getProperty("PaperlessBillingPage.ChangetoPaperlessbillingOverlayTitle")))
	    {
		   String overlayContent = browser.getTextByXpath(pageProperties.getProperty("PaperlessBillingPage.ChangetoPaperlessbillingOverlayTitle"));
		   System.out.println("overlayContent: "+overlayContent);
		   Report.updateTestLog("Overlay content: "+overlayContent, "PASS");
		   verifyAndClickWithXpath(pageProperties.getProperty("PaperlessBillingPage.ChangetoPaperlessbillingOverlay_yesbutton"), "Yes"); 
	    }	    
	    verifyIsTextPresent("You have switched to paperless billing for this account");
	}
	public void paperlessBillsforAcctSpecificoptedoutofpaperless()
	{
		verifyIsTextPresent("Paperless billing");
		verifyIsTextPresent("Reduce your paperwork and help the environment by choosing to receive your bill electrically.");
		verifyAndClickWithXpath(pageProperties.getProperty("PaperlessBillingPage.AcctSummary_switchtopaperlessbill_checkbox"), "Switch to paperless");
		if(browser.isElementVisibleWithXpath(pageProperties.getProperty("PaperlessBillingPage.ChangetoPaperlessbillingOverlayTitle")))
	    {
		   String overlayContent = browser.getTextByXpath(pageProperties.getProperty("PaperlessBillingPage.ChangetoPaperlessbillingOverlayTitle"));
		   System.out.println("overlayContent: "+overlayContent);
		   Report.updateTestLog("Overlay content: "+overlayContent, "PASS");
		   verifyAndClickWithXpath(pageProperties.getProperty("PaperlessBillingPage.ChangetoPaperlessbillingOverlay_yesbutton"), "Yes"); 
	    }	    
	    verifyIsTextPresent("You have switched to paperless billing for this account");
	}
	 
	 public void papaerlesessetupWhatisThisverlay(){	   
		 	verifyAndClickWithXpath(pageProperties.getProperty("PaperlessBillingPage.switchtopaperlessbill_WhatisThisOverlay"), "Manage User What is This Overlay Clicked");
		 	
		    if(browser.isElementVisibleWithXpath(pageProperties.getProperty("PaperlessBillingPage.switchtopaperlessbill_ThisOverlayTitle"))){
			   String overlayContent = browser.getTextByXpath(pageProperties.getProperty("PaperlessBillingPage.switchtopaperlessbill_ThisOverlayTitle"));
			   System.out.println("overlayContent: "+overlayContent);
			   Report.updateTestLog("Overlay content: "+overlayContent, "PASS");
			   	   }
		   else{
			   Report.updateTestLog("Please check Why we need this link " , "WARN");
		   }
		      verifyAndClickWithXpath(pageProperties.getProperty("PaperlessBillingPage.switchtopaperlessbill_WhatisThisOverlayClose"), "Overlay close button");
		   
	     }
	 public void verifyManageAccount(UserProfile userProfile){
			
		   //verifyAndClickWithXpath(pageProperties.getProperty("PaperlessBillingPage.ManageAccountLink").replace("ACCOUNTNUMBER", userProfile.getAccNumber()), "Manage account link");
		 verifyAndClickWithXpath(pageProperties.getProperty("PaperlessBillingPage.ManageAccountLinkNew1").replace("ACCOUNTNUMBER", userProfile.getAccNumber()), "Manage account link");
		}
	 
	 public void paperlesslink(UserProfile userProfile)
	 {
		
			  	if (browser.isTextPresent("Paperless billing"))
			  	{
			  	  Report.updateTestLog("(Paperless billing Jouney is Available) User is  Enrolled as Super user or Full access user in Account Overview Page", "PASS");
	           	} 
			  	else
			  	{
			  	  Report.updateTestLog("(Paperless billing Jouney is Available) User is  Enrolled as Super user or Full access user in Account Overview Page", "Fail");
	            }
		
		 /*verifyAndClickWithXpath(pageProperties.getProperty("PaperlessBillingPage.ManageAccountLink").replace("ACCOUNTNUMBER", userProfile.getAccNumber()), "Manage account link");
		 
		 	if (browser.isTextPresent("Paperless billing")) {
		 		
		 		Report.updateTestLog("(Paperless billing Jouney is Available) User is  Enrolled as Super user or Full access user in Account Summary Page", "PASS");
	            
	        } else {
	        	Report.updateTestLog("(Paperless billing Jouney is Available) User is  Enrolled as Super user or Full access user in Account Summary Page", "PASS");
	            
	    }		*/ 
		 
	}
	 
	 public void morethan15acctsacctoverview()
	 {
		 	if (!browser.isTextPresent("Paperless billing"))
		  	{
		 		Report.updateTestLog("Paperless billing Jouney is Not Available in Global level for More than 15 Accts", "Pass");
           	} 
		  	else
		  	{		  	 
		  	 Report.updateTestLog("Paperless billing Jouney is  Not Available in Global level for More than 15 Accts", "Fail");
            }
		 verifyIsTextPresent("PPaperless billing");
	 }
	 public void submitNpsSurvey(){
			
		 verifyAndClickWithXpath(pageProperties.getProperty("PaperlessBillingPage.NPS"), "NpsSubmit button");
		 verifyAndClickWithXpath(pageProperties.getProperty("PaperlessBillingPage.Feedbackrating"), "NpsSubmit button");
		 		 
		}
		public void verifySurveyTableInDb(String emailid,String surveytype,String option,String surveytext){
			browser.wait(2000);	
			String[] surveydata=new OnlineDBConnector().getSurveyInfo(emailid,surveytype);
			 if(surveydata!=null){
				try{
					Report.updateTestLog("Expected Result: "+option+" Actual Result: "+surveydata[0],surveydata[0].equals(option)?"Pass":"Fail");
					Report.updateTestLog("Expected Result: "+surveytext+timestamp+" Actual Result: "+surveydata[1],surveydata[1].equals(surveytext+timestamp)?"Pass":"Fail");
				}catch(Exception e){
					Report.updateTestLog("Exception occured while fetching surveytable value"+e,"Fail");
				}
			  }
			
		    }
		public void closeThankYouOverlay(){
	 		RobotSendKeys.typeenter();
	 		
	 	}
	 public void searchBy(UserProfile userProfile) {	

			verifyAndSelectDropDownBox(pageProperties.getProperty("PaperlessBillingPage.SearchBy"), "Search By", "Account number");
			verifyAndInputById(pageProperties.getProperty("PaperlessBillingPage.KeywordToSearch"), "Keyword", userProfile.getAccNumber());
			verifyAndClick(pageProperties.getProperty("PaperlessBillingPage.SearchButton"), "Search Button");
		}
	 
	 public static void verifyThankyouInDb() // have to consider the paramaters
	 {
			String emailid="abc@gas.com";
			String surveytype="thanku";
			String surveytext="onetwothree";
			String option="sdf";
			String[] surveydata=new OnlineDBConnector().getSurveyInfo(emailid,surveytype);
			 if(surveydata!=null){
				try{
					Report.updateTestLog("Expected Result: "+option+" Actual Result: "+surveydata[0],surveydata[0].equals(option)?"Pass":"Fail");
					Report.updateTestLog("Expected Result: "+surveytext+timestamp+" Actual Result: "+surveydata[1],surveydata[1].equals(surveytext+timestamp)?"Pass":"Fail");
				}catch(Exception e){
					Report.updateTestLog("Exception occured while fetching surveytable value"+e,"Fail");
				}
			  }
			
	}	 
	 public void VerifySAPISU_PBChange(UserProfile userProfile){
		  	String PBoutput="PrintedInvoice";
		  	System.out.println("PBoutput"+PBoutput);
			String Acctno=userProfile.getAccNumber();
			System.out.print("Acctno"+Acctno);		
			String bpnumber=userProfile.getBpnumber();
			System.out.print("bpnumber"+bpnumber);
			String bpOrgNumber =bpnumber.concat("-").concat(Acctno);
			System.out.print("bpOrgNumber"+bpOrgNumber);	
			RunQTP runQTP = new RunQTP();
		    System.out.println("Initiating QTP.............");
		   runQTP.runQTP("Slingshot\\src\\bg\\framework\\app\\functional\\util\\vbsScripts\\Pbisu.vbs", bpOrgNumber);
		   System.out.println("QTP Process Over...........");

		   browser.wait(15000);
			try {
				System.out.println("*************************************");
				 File file1 = new File("D:\\PBISU\\PBISU.txt");
                 FileReader fr = new FileReader(file1);
                 BufferedReader br = new BufferedReader(fr);
                 String paperStatus = br.readLine();   
               System.out.println("paperStatus"+paperStatus);
             
             if(paperStatus.contains("paperstatus")&& paperStatus.contains(":")){
                             String[] paperStatusvalue=paperStatus.split(":");
                             String PBstatus=paperStatusvalue[1];
                             System.out.print("PB Status"+PBstatus);
                             if(PBstatus.trim().equals(PBoutput.trim())){
                                             Report.updateTestLog("PB Status in Application"+PBoutput.trim()+"PB Status in  SAP ISU :"+PBstatus, "Pass");
                             }else{
                                             Report.updateTestLog("PB Status in Application"+PBoutput.trim()+"PB Status in  SAP ISU :"+PBstatus, "Fail");
                             }
             }               				
				br.close();		
			  }
			     catch (IOException e) {
				        Report.updateTestLog("Exception while accessing the .txt file"+e, "Fail");
			}
	}
	 
}