package io.testomat.e2e_tests_light;

import io.testomat.e2e_tests_light.utils.StringParsers;
import io.testomat.e2e_tests_light.web.pages.ProjectPage;
import io.testomat.e2e_tests_light.web.pages.ProjectsPage;
import io.testomat.e2e_tests_light.web.pages.SignInPage;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static io.testomat.e2e_tests_light.utils.StringParsers.parseIntegerFromString;


public class ProjectPageTests extends BaseTest {

    private static final ProjectsPage projectsPage = new ProjectsPage();
    private static final SignInPage signInPage = new SignInPage();
    private static final ProjectPage projectPage = new ProjectPage();
    static String userName = env.get("USERNAME");
    static String password = env.get("PASSWORD");
    String targetProjectName = "Manufacture light";

    @BeforeAll
    static void openTestomatAndLogin() {
        signInPage.open();
        signInPage.loginUser(userName, password);
        projectsPage.signInSuccess();
    }

    @BeforeEach
    void openProjectsPage() {
        projectsPage.open();
        projectsPage.isLoaded();
    }

    @Test
    public void userCanFindProjectWithTests() {

        projectsPage.searchForProject(targetProjectName);

        projectsPage.selectProject(targetProjectName);

        projectPage.isLoaded(targetProjectName);
    }

    @Test
    public void anotherTest() {

        projectsPage.searchForProject(targetProjectName);

        var targetProject = projectsPage.countOfProjectsShouldBeEqualTo(1).first();

        projectsPage.countOfTestCasesShouldBeEqualTo(targetProject, 0);

        projectsPage.totalCountOfProjectsIsVisible();
        var totalProjects = projectsPage.getTotalCountOfTestCases();
        var actualCountOfTotalTests = parseIntegerFromString(totalProjects);
        Assertions.assertTrue(actualCountOfTotalTests > 100);
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
}
