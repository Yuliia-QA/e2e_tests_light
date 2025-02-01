package com.vas.staging.smoke_tests;

import com.codeborne.selenide.SelenideElement;
import com.vas.staging.smoke_tests.utils.StringParsers;
import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.time.Duration;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;


public class StagingSmokeTests extends BaseTest {

    static String homeUrl = envVas.get("HOME_URL_VAS");
    static String loginUrl = envVas.get("LOGIN_URL_VAS");
    static String userName = envVas.get("USERNAME_VAS");
    static String password = envVas.get("PASSWORD_VAS");
    String targetDairyName = "Demo Dairy";


    @BeforeAll
    static void login() {

        open(loginUrl);
        $("#username").setValue(userName);
        $("#password").setValue(password);
        $(".form-control__item>.form-control__label").click();
        $("[type=\"submit\"]").click();

    }


    @Test
    public void userCanGetStartedWithMydcFlow(){

        $("#page-content-wrapper > section").shouldBe(visible);

        $$("button").filterBy(text("GET STARTED WITH MYDC")).first().click();

        $("form").shouldBe(visible).shouldHave(text("Get started with MYDC"));
    }


    @Test
    public void ConnectionsAreAvailable() {

        open(homeUrl);

        SelenideElement table = GetMyConnectionsTable();

        SelenideElement demoRow = table.$$("td").filterBy(text(targetDairyName)).first();

        demoRow.shouldBe(visible);
    }


    @NotNull
    private SelenideElement GetMyConnectionsTable() {

        SelenideElement table = $$("table").filterBy(attribute("data-attribute",
                "my-connected-dairies")).first();

        table.shouldBe(visible, Duration.ofSeconds(8));

        return table;
    }


    @Test
    public void CountOfDairiesGreaterThanZero() {
        //GoToURL(homeUrl);
        //open(homeUrl);

        SelenideElement table = GetMyConnectionsTable();

        String records = $$("span").filterBy(attribute("data-attribute",
                "total-records")).first().getText();

        int recordsCount = StringParsers.parseIntegerFromString(records);

        Assertions.assertTrue(recordsCount>0);
    }

    @Test
    public void FindExactDairyName() {

        $(".context-selector__caret").click();

        $("#context-selector-input").shouldBe(visible, Duration.ofSeconds(2));

        $("#context-selector-input").setValue(targetDairyName);

        SelenideElement first = $$("li.results > a").first();

        String herd = first.$("span.dairy-item__details").getText();

        int herdCode = StringParsers.parseIntegerFromString(herd);

        Assertions.assertEquals(11111118, herdCode);
    }


    /* attempt to improve pageswitching
    static void GoToURL(String url){
        open(url);
        String currentURL = webdriver().driver().url();
        if(currentURL.contains("login")) {
            $("#username").clear();
            $("#password").clear();
            $("#username").setValue(userName);
            $("#password").setValue(password);
            $(".form-control__item>.form-control__label").click();
            $("[type=\"submit\"]").click();
        }
    }*/











}
