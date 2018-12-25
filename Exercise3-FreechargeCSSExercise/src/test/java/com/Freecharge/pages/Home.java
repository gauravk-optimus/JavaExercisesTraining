package com.Freecharge.pages;

import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

import com.Freecharge.executor.Keywords;

public class Home {
	public static final String VALIDPHONENUMBER="9818012345";
	public static final String SELECT_OPERATOR="Airtel";
	public static final String PLAN_FTT = "Full Talktime";
	public static final String PLAN_ISD = "ISD";
	public static final String PLAN_VALIDITY = "Unlimited";
	public static final String FTT = "Full Talktime";
	public static final String ISD = "ISD";
	public static final String VALUE = "Validity";
	
	Logger logger;
	Keywords keywords;

public Home() {
	logger = Logger.getLogger(Home.class);
	keywords = new Keywords();
	}
	
	@FindBy(css="input[name='number']")
	private WebElement phoneNumber;
	
	@FindBy(css="select[name='operator']")               
	private WebElement selectOperator;
	
	@FindBy(css="button[type='submit']")
	private WebElement proceedButton;
	
	@FindBy(xpath = "html/body/div[2]/div/div[2]/div[2]/div/div[3]/div/div[1]/span[2]")
	private WebElement fullTtTab;

	@FindBy(xpath="p._2WAF-")
	private WebElement effectivePlan;

	@FindBy(css="button._3G6A5")
	private WebElement viewAllPlan;
	
	@FindBy(xpath="html/body/div[2]/div/div[2]/div[2]/div/div[3]/div/div[2]/ul/li")
	private List<WebElement> recommenedPlans;
	
	@FindBy(xpath = "html/body/div[2]/div/div[2]/div[2]/div/div[3]/div/div[2]/ul/li")
	private List<WebElement> recommendedPlansTypes;

	@FindBy(css = "p._264pV")
	private List<WebElement> fttPlans;
	
	@FindBy(xpath = "html/body/div[2]/div/div[2]/div[2]/div/div[3]/div/div[1]/span[5]")
	private WebElement rateCutterTab;

	@FindBy(xpath = "html/body/div[2]/div/div[2]/div[2]/div/div[3]/div/div[1]/span[3]")
	private WebElement topupTab;
	
	@FindBy(css = "p._264pV")
	private List<WebElement> otherPlans;
	
	@FindBy(xpath = "html/body/div[2]/div/div[2]/div[2]/div/div[3]/div/div[2]/ul/li/div/p[3]/span[2]")
	private List<WebElement> validity;
	
	/**
	 * select plan
	 * 
	 */
	public void findPlan() {
		logger.info("Finding effective plans");
		try {
			keywords.clickButtonFluently(viewAllPlan);
			keywords.findEffectivePlan(recommendedPlansTypes, PLAN_FTT);
			keywords.findEffectivePlan(recommendedPlansTypes, PLAN_ISD);
		} catch (Exception e) {
			Keywords.failValues.put(
					Thread.currentThread().getStackTrace()[2].getMethodName(),
					"Unable to select the plan");
			Assert.fail();
		}
	}
	/**
	 * entering phone number
	 * 
	 */
	public void enterPhoneNumberForRecharge() {
		
		logger.info("Entering Phone Number for Recharge");
		try{
			keywords.sendTextInTextBox(phoneNumber,VALIDPHONENUMBER);			
			
		}
		catch(Exception e){
			Keywords.failValues.put(Thread.currentThread().getStackTrace()[2].getMethodName(),"Unable to enter the phone number");
			Assert.fail();
		}
	}
	/**
	 * verifying the existence of non FTT plan in FTT tab
	 * 
	 */
	public void getNonFTTPlan() {
		
		logger.info("Verifying the existance of non FTT plan in FTT tab");
		try{
			keywords.clickButtonFluently(viewAllPlan);
			keywords.clickButtonFluently(fullTtTab);
			keywords.verifyListNotContains(fttPlans, FTT);
		}
		catch(Exception e){
			Keywords.failValues.put(Thread.currentThread().getStackTrace()[2].getMethodName(),"Non FTT plan exists in FTT tab");
			Assert.fail();
		}
	} 
	/**
	 * Counting ISD plans in rate cutter tab
	 * 
	 */
	public void getISDPlanCount() {
		
		logger.info("Counting ISD plans");
		try{
			keywords.clickButtonFluently(viewAllPlan);
			keywords.clickButtonFluently(rateCutterTab);
			keywords.getElementCount(otherPlans, ISD);
		}
		catch(Exception e){
			Keywords.failValues.put(Thread.currentThread().getStackTrace()[2].getMethodName(),"Unable to count or not exist");
			Assert.fail();
		}
	}
	/**
	 * Finding least validity plan
	 * 
	 */
	public void getLeastValidityPlan() {
		
		logger.info("Finding least validity plan");
		try{
			keywords.clickButtonFluently(viewAllPlan);
			keywords.clickButtonFluently(topupTab);
			//keywords.clickButtonFluently(rateCutterTab);
			keywords.getLeastValueFromList(validity, PLAN_VALIDITY, VALUE);
		}
		catch(Exception e){
			Keywords.failValues.put(Thread.currentThread().getStackTrace()[2].getMethodName(),"Unable to count or not exist");
			Assert.fail();
		}
	} 
	/**
	 * select an operator
	 * 
	 */
	public void selectOperator() {
		
		logger.info("Selecting an operator");
		try{
			keywords.selectValueFromDropdown(selectOperator,SELECT_OPERATOR);
		}
		catch(Exception e){
			Keywords.failValues.put(Thread.currentThread().getStackTrace()[2].getMethodName(),"Unable to select operator "+SELECT_OPERATOR);
			Assert.fail();
		}
	}
	/**
	 * Click Proceed Button
	 * 
	 */
	public void clickProceedButton() {
		
		logger.info("Clicking on proceed button");
		try{Thread.sleep(1000);
			keywords.clickButtonFluently(proceedButton);
			Thread.sleep(1000);
			//keywords.clickButtonFluently(viewAllPlan);
		}
		catch(Exception e){
			Keywords.failValues.put(Thread.currentThread().getStackTrace()[2].getMethodName(),"Unable to click on Proceed Button");
			Assert.fail();
		}
	}		
	/**
	 * count number of recommended plans
	 * 
	 */
	public void noOfRecommendedPlans(){
		logger.info("Counting number of recommended plans");
		try{
			int size = recommenedPlans.size();
			logger.info("Total number of recommended plans are: "+size);
			//TODO what to do with the result
		}
		catch(Exception e){
			Keywords.failValues.put(Thread.currentThread().getStackTrace()[2].getMethodName(),"Unable to count recomended plans");
			Assert.fail();
		}
	}	
}