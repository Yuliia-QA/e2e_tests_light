package com.vas.staging.smoke_tests.web.pages;

import com.codeborne.selenide.Selenide;

import static com.codeborne.selenide.Selenide.$;

public class LoginPage {
    public void open() {
        Selenide.open("/login");
    }

    public void loginUser(String userName, String password) {
        $("#username").setValue(userName);
        $("#password").setValue(password);
        $(".form-control__item>.form-control__label").click();
        $("[type=\"submit\"]").click();
    }
}
