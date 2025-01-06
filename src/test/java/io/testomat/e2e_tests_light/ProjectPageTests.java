package io.testomat.e2e_tests_light;

import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class ProjectPageTests {

    @Test
    public void userCanFindProjectWithTests() {
        open("https://app.testomat.io/");

        //login user
        $("#content-desktop #user_email").setValue("pluzhnikovajulya@gmail.com");
        $("#content-desktop #user_password").setValue("Rg3L@78Ce3@fnFQ");
        $("#content-desktop #user_remember_me").click();
        $("#content-desktop [name=\"commit\"]").click();
        $(".common-flash-success-right").shouldBe(visible);

        //search project
        $("#search").setValue("manufacture light");

        //select project
        $(byText("Manufacture light")).click();

        //wait for project is loaded
        $("h2").shouldHave(text("Manufacture light"));
    }

}
