package pageobject;
import baseclass.BaseClass;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WindowType;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import java.util.ArrayList;
import java.util.List;

;

public class PageObject extends BaseClass {
    String btn1Text="";
    String btn2Text="";
    String btn3Text="";

    String imeiText="";
    public void loginPage() {
        WebElement userId = driver.findElement(By.id("ssoUserId"));
        userId.sendKeys("ak598v");
        System.out.println(userId.getText());
        WebElement isTablet = driver.findElement(By.id("isTablet"));
        Select dropDown = new Select(isTablet);
        dropDown.selectByIndex(0);
        WebElement isOpus = driver.findElement(By.id("isOpus"));
        Select dropDown1 = new Select(isOpus);
        dropDown1.selectByIndex(1);
        WebElement referenceNumber = driver.findElement(By.id("referenceNumber"));
        referenceNumber.sendKeys("356743738738");
        WebElement loginBtn = driver.findElement(By.id("submit"));
        loginBtn.click();
    }

         // home page------------------
        public void homePage(){
            WebElement startTrade = driver.findElement(By.id("startTrade"));
            startTrade.click();
        }
        // search device page-------------
        public void searchDevice(){
            WebElement deviceName= driver.findElement(By.id("appendedInputButton"));
            deviceName.sendKeys("Galaxy S8 Active 64GB");
            WebElement searchDeviceBtn = driver.findElement(By.id("serchDeviceBtn"));
            searchDeviceBtn.click();
    }


         // device-quote---------------------
         public void deviceQuote() throws InterruptedException{
        //Verify title
        String title = driver.getTitle();
        System.out.println("Page title is" + title);
        System.out.println("Is Page title is AT&T " + title.contains("AT&T"));
        Assert.assertEquals("AT&T | Get Device Quote", title);
        //Verify Device-quote is in url
        String url = driver.getCurrentUrl();
        String[] currentUrl = url.split("/");
        System.out.println("Is device-quote is in url - " +url.contains("device-quote"));
        for (String s : currentUrl
        ) {
            if (s.contains("device-quote")) {


            }
        }
        // Verify page header----------------
        WebElement pageHeader = driver.findElement(By.id("deviceQuote"));
        String pageHeaderText = pageHeader.getText();
        System.out.println("Page Header is Get Device Quote - " + pageHeaderText.contains("Get Device Quote"));
        Assert.assertEquals("Get Device Quote", pageHeaderText);
        //Tooltip-------------

            WebElement toolTip1 = driver.findElement(By.xpath("//a[@id='tooltip0']"));
            Actions action1 = new Actions(driver);
            action1.moveToElement(toolTip1).perform();
            WebElement toolTip11 = driver.findElement(By.xpath("//a[@id='tooltip0']"));
            String txt = toolTip11.getText();
            System.out.println(txt);
         //   Assert.assertEquals("Power the device completely off and on again. Make sure the device stays on.",txt);

        WebElement toolTip2 = driver.findElement(By.xpath("//a[@id='tooltip1']"));
        Actions action2 = new Actions(driver);
        action2.moveToElement(toolTip2).perform();

        WebElement toolTip3 = driver.findElement(By.xpath("//a[@id='tooltip2']"));
        Actions action3 = new Actions(driver);
        action3.moveToElement(toolTip3).perform();

        //Device condition table-----

        WebElement btn1= driver.findElement(By.id("questionResponsePOWER_ON___Y"));
        btn1.click();
        btn1Text = btn1.getText();
        WebElement btn2= driver.findElement(By.id("questionResponseDEACTIVATED___Y"));
        btn2.click();
        btn2Text = btn2.getText();
        WebElement btn3= driver.findElement(By.id("questionResponseLCD_FUNCTIONALITY___Y"));
        btn3.click();
        btn3Text = btn3.getText();

        //want to send later element
        WebElement sendLater = driver.findElement(By.id("certify-hrk-yes"));
          sendLater.click();
        // select send a return label radio button
          Thread.sleep(3000);
         driver.findElement(By.xpath("//input[@value='REQUEST_SHIPPING_MATERIAL']")).click();

         //Get imei number

            ArrayList<String> windows =  new ArrayList<>(driver.getWindowHandles());
            driver.switchTo().newWindow(WindowType.TAB);
            Thread.sleep(1000);
            driver.get("https://dyrk.org/tools/imei/");
            WebElement imeiBtn = driver.findElement(By.xpath("//input[@type='button'][@value='Generate IMEI Number ->']"));
            imeiBtn.click();
            WebElement imeiInput = driver.findElement(By.id("imei_num"));
             imeiText = imeiInput.getAttribute("value");
           System.out.println("Text of Imei num-" + imeiText);
             Thread.sleep(4000);
           driver.close();
           driver.switchTo().window(windows.get(0));

        //Pass imei number
         WebElement imeiNum = driver.findElement(By.id("sliderCELL_PHONE_IMEI_ESN"));
         imeiNum.sendKeys(imeiText);
        //pass mobile number
         WebElement phoneNum = driver.findElement(By.id("sliderCELL_PHONE_PHONE_NUM"));
         phoneNum.sendKeys("8585948732");

         Thread.sleep(2000);
         WebElement checkout = driver.findElement(By.id("checkout"));
         System.out.println("Checkout button is enabled or not- " + checkout.isEnabled());
        checkout.click();
    }

    public void reviewTrade() throws InterruptedException{
        List<WebElement> tableHeading = driver.findElements(By.className("device-summary-header"));
        String txt ="";
        System.out.println("Headers of device table----- ");
        for (WebElement d:tableHeading
        ) {
             txt = d.getText();
            System.out.println(txt);
        }
        //Verification
        WebElement ans1 = driver.findElement(By.id("POWER_ON_item0_answer"));
        String ans1Text = ans1.getText();
        System.out.println(("\tDoes the Device Power On and Off? --- " + ans1Text.equalsIgnoreCase(btn1Text)));

        WebElement ans2 = driver.findElement(By.id("DEACTIVATED_item0_answer"));
        String ans2Text = ans2.getText();
        System.out.println(("\tIs the Activation Lock turned off? (e.g. Find My iPhone or Reactivation Lock)\t --- " + ans2Text.equalsIgnoreCase(btn2Text)));

        WebElement ans3 = driver.findElement(By.id("LCD_FUNCTIONALITY_item0_answer"));
        String ans3Text = ans3.getText();
        System.out.println(("\tDoes the Device Power On and Off? --- " + ans3Text.equalsIgnoreCase(btn3Text)));

        //certify Yes

        WebElement certifyYs = driver.findElement(By.cssSelector(".btn-group>.positive"));

        JavascriptExecutor js =(JavascriptExecutor)driver;
        js.executeScript("arguments[0].scrollIntoView();",certifyYs);

        certifyYs.click();
        System.out.println("Is Certify-Yes button enabled ?-- "  + certifyYs.isEnabled());



        //Customer Personal Information
        WebElement firstName= driver.findElement(By.id("firstName"));
        firstName.sendKeys("John");
        WebElement lastName= driver.findElement(By.id("lastName"));
        lastName.sendKeys("david");
        WebElement email = driver.findElement(By.id("emailAddress"));
        email.sendKeys("John123@gmail.com");
        String emailText = email.getAttribute("value");
        WebElement confirmEmail = driver.findElement(By.id("confirmEmail"));
        confirmEmail.sendKeys("John123@gmail.com");
        String confirmEmailText = confirmEmail.getAttribute("value");
        System.out.println("Mail confirmation-- " + emailText.equalsIgnoreCase(confirmEmailText));
        WebElement address1 = driver.findElement(By.id("address.address1"));
        address1.sendKeys("755 Texas Ave");

        WebElement address2 = driver.findElement(By.id("address.address2"));
        address2.sendKeys("9999954353");
        WebElement city = driver.findElement(By.id("address.city"));
        city.sendKeys("Bridge City");
        WebElement state = driver.findElement(By.id("address.state"));
        Select stateSelection = new Select(state);
        stateSelection.selectByVisibleText("Texas");
        WebElement zipCode = driver.findElement(By.id("address.zipCode"));
        zipCode.sendKeys("77611");
        WebElement signaturePad = driver.findElement(By.id("signature-pad"));
        signaturePad.click();
        WebElement completeTrade = driver.findElement(By.id("customer-accept-complete-trade"));
        JavascriptExecutor jse = (JavascriptExecutor)driver;
        jse.executeScript("arguments[0].scrollIntoView();",completeTrade);
        System.out.println("Is complete-Trade btn is enabled or not-- " + completeTrade.isEnabled());
        completeTrade.click();

    }

    public  void tradeComplete(){
        String pageTitle = driver.getTitle();
        System.out.println("Title of page is- " + pageTitle);
        System.out.println("Is title of page is same--  + " + pageTitle.contains("AT&T | Trade Complete"));
       //Assert.assertEquals("AT&T | Trade Complete",pageTitle,"not same");


        WebElement completionMessage= driver.findElement(By.cssSelector("h2[class='h1 text-success-erc mbm text-success']"));
        String completionMessageText = completionMessage.getText();
        System.out.println("Completion Message is-" + completionMessageText);
        System.out.println("Is Trade Completion message is same as expected-- " + completionMessageText.equals("Congratulations, your trade submission is complete!"));

       // Assert.assertEquals("Congratulations, your trade submission is complete!",completionMessageText,"not same");

        WebElement completeTradeValue = driver.findElement(By.cssSelector(".trade-complete-value>h3>strong"));
        String completeTradeText = completeTradeValue.getText();
        System.out.println("Is complete trade value is $35.00 -- " + completeTradeText.contains("$35.00"));
    }
public void inVoiceNum(){
        WebElement inVoiceNum = driver.findElement(By.cssSelector(".span4>div>h5>b"));
        String fetchText = inVoiceNum.getText();
        // click on device trade option
       WebElement devices = driver.findElement(By.cssSelector(".subnav-trigger"));

    Actions selectDevices = new  Actions(driver);
    selectDevices.moveToElement(devices).build().perform();
    WebElement deviceTrade = driver.findElement(By.cssSelector(".subnav>li:nth-child(3)>a"));
    String b = deviceTrade.getText();
   Actions deviceS= new Actions(driver);
   deviceS.moveToElement(deviceTrade).build().perform();
   deviceTrade.click();

   //pass value to invoice input field
   WebElement invoiceInputField = driver.findElement(By.cssSelector("input[name='invoiceId']"));
   invoiceInputField.sendKeys(fetchText);

   //click on submit button

    WebElement submitBtn= driver.findElement(By.cssSelector("input[name='_target1']"));
    submitBtn.click();
    //click on item id
    WebElement itemId = driver.findElement(By.cssSelector("#itemId"));
    itemId.click();


}
public void tradeInVerificationForm(){
    ArrayList<String> switchWin = new ArrayList<>(driver.getWindowHandles());
    //switch to window
    driver.switchTo().window( switchWin.get(1));
    String windowTitle = driver.getTitle();

    Assert.assertEquals("AT&T :: Item Description",windowTitle);
    System.out.println("Is window title same as expected -- " + windowTitle.equalsIgnoreCase("AT&T :: Item Description"));
    WebElement deviceName = driver.findElement(By.cssSelector("th[colspan='2']"));
    String fetchDeviceNameText = deviceName.getText();

    System.out.println("Is device name  same as selected-- " + fetchDeviceNameText.equalsIgnoreCase("Samsung Galaxy S8 Active 64GB"));
    Assert.assertEquals("Samsung Galaxy S8 Active 64GB",fetchDeviceNameText);
    WebElement verifyImei = driver.findElement(By.cssSelector("table[border='0']>tbody>tr:nth-child(4)>td:nth-child(3)"));
    String verifyImeiText = verifyImei.getText();
    System.out.println("Is Imei number same as generated - " + verifyImeiText.equalsIgnoreCase(imeiText));
    Assert.assertEquals(imeiText,verifyImeiText);
    String currentUrl = driver.getCurrentUrl();
          String[]d=  currentUrl.split("/");
    for (String url:d){
        System.out.println(url);
    }

driver.close();



}
}
