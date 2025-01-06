package com.vas.staging.smoke_tests;

import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class StagingSmokeTests {

    @Test
    public void userCanGetStartedWithMydcFlow(){
        open("https://platform-staging.vas.com/login");

        //Login user
        $("#username").setValue("yuliia.pluzhnikova@vas.com");
        $("#password").setValue("MyVASpass1");
        $(".form-control__item>.form-control__label").click();
        $("[type=\"submit\"]").click();
        $("#page-content-wrapper > section").shouldBe(visible);
        $(".fruzqf").click();
        $(".sc-jdUcAg").shouldBe(visible);
    }
}
