package com.vas.staging.smoke_tests;

import com.codeborne.selenide.SelenideElement;
import com.vas.staging.smoke_tests.web.pages.LoginPage;
import com.vas.staging.smoke_tests.web.pages.MyHomePage;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;


public class StagingSmokeTests extends BaseTest {

    private static final LoginPage loginPage = new LoginPage();
    private static final MyHomePage myHomePage = new MyHomePage();


    static String userName = envVas.get("USERNAME_VAS");
    static String password = envVas.get("PASSWORD_VAS");
    String targetDairyName = "Demo Dairy";


    @BeforeAll
    static void login() {
        loginPage.open();
        loginPage.loginUser(userName, password);
    }


    @Test
    public void userCanGetStartedWithMydcFlow() {
        myHomePage.quickUserOptionsVisible();
        myHomePage.openMydcDialog();
        myHomePage.isMydcDialogOpened();
        myHomePage.closeMydcDialog();
    }


    @Test
    public void connectionsAreAvailable() {
        myHomePage.myConnectionTableVisible();
        myHomePage.myConnectionTableHasElement(targetDairyName);
    }


    @Test
    public void countOfDairiesGreaterThanZero() {
        myHomePage.myConnectionTableVisible();
        int recordsCount = myHomePage.getMyConnectionsTableTotalRecords();
        Assertions.assertTrue(recordsCount > 0);
    }


    @Test
    public void findExactDairyName() {
        myHomePage.openSearch();
        SelenideElement result = myHomePage.findDairyByHerdcode(11111118);
        String dairyName = myHomePage.getDairyName(result);
        Assertions.assertEquals(dairyName, targetDairyName);
    }
}
