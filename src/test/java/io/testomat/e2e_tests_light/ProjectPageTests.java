package io.testomat.e2e_tests_light;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.testomat.e2e_tests_light.utils.StringParsers;
import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.CollectionCondition.size;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;
import static io.testomat.e2e_tests_light.utils.StringParsers.parseIntegerFromString;

public class ProjectPageTests extends BaseTest {

    static String baseUrl = env.get("BASE_URL");
    static String userName = env.get("USERNAME");
    static String password = env.get("PASSWORD");
    String targetProjectName = "Manufacture light";

    @BeforeAll
    static void openTestomatAndLogin() {
        open(baseUrl);
        loginUser(userName,password);
    }

    @BeforeEach
    void openHomePage() {
        open(baseUrl);
    }

    @Test
    public void userCanFindProjectWithTests() {

        searchForProject(targetProjectName);

        selectProject(targetProjectName);

        waitForProjectPageIsLoaded(targetProjectName);
    }

    @Test
    public void anotherTest() {

        searchForProject(targetProjectName);

        SelenideElement targetProject = countOfProjectsShouldBeEqualTo(1).first();

        countOfTestCasesShouldBeEqualTo(targetProject, 0);

        totalCountOfTestCasesGreaterThan(0);
    }

    @Test
    public void exampleAssertDouble() {
        var text = "15.4 coverage";

        Double actualDouble = StringParsers.parseDoubleFromString(text);

        Assertions.assertTrue(15.4 >= actualDouble);
    }

    @Test
    public void exampleParseInteger() {
        var text = "0 tests";

        Integer actual = parseIntegerFromString(text);

        Assertions.assertEquals(0, actual);
    }

    @Test
    public void exampleParseBoolean() {
        var text = "true";

        Boolean actual = Boolean.parseBoolean(text);

        Assertions.assertEquals(true, actual);
    }

    private void waitForProjectPageIsLoaded(String targetProjectName) {
        $(".first h2").shouldHave(text(targetProjectName));
        $(".first [href*='/readme']").shouldHave(text("Readme"));
    }

    private void selectProject(String targetProjectName) {
        $(byText(targetProjectName)).click();
    }

    private void searchForProject(String targetProjectName) {
        $("#search").setValue(targetProjectName);
    }

    private void totalCountOfTestCasesGreaterThan(int expectedTotalCount) {
        String totalProjects = $("#container").getText();
        Integer actualCountOfTotalTests = parseIntegerFromString(totalProjects);
        Assertions.assertTrue(actualCountOfTotalTests > expectedTotalCount);
    }

    private void countOfTestCasesShouldBeEqualTo(SelenideElement targetProject, int expectedCount) {
        String countOfTests = targetProject.$("p").getText();
        Integer actualCountOfTests = parseIntegerFromString(countOfTests);
        Assertions.assertEquals(expectedCount , actualCountOfTests);
    }

    public static void loginUser(String email, String password) {
        $("#content-desktop #user_email").setValue(email);
        $("#content-desktop #user_password").setValue(password);
        $("#content-desktop #user_remember_me").click();
        $("#content-desktop [name=commit]").click();
        $(".common-flash-success-right").shouldBe(visible);
    }

    @NotNull
    private ElementsCollection countOfProjectsShouldBeEqualTo(int expectedSize) {
        return $$("#grid ul li").filter(visible).shouldHave(size(expectedSize));
    }

}
