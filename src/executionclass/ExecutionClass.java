package executionclass;
import pageobject.PageObject;

public class ExecutionClass extends PageObject {
    public static void main(String[] args) throws InterruptedException {
        ExecutionClass ec = new ExecutionClass();
        ec.launchBrowser("https://auto.att.hylatest.com/ssoTest.htm");
        ec.loginPage();
        ec.homePage();
        ec.searchDevice();
        ec.deviceQuote();
        ec.reviewTrade();
        ec.tradeComplete();
        ec.inVoiceNum();
        ec.tradeInVerificationForm();
    }
}
