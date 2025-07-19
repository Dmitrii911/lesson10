import com.codeborne.selenide.Condition;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static io.qameta.allure.Allure.step;
import static org.openqa.selenium.By.linkText;


public class LambdaStepTest {
    private static final String REPO = "Dmitrii911/lesson-9-qa-guru-36";
    private static final int ISSUE = 1;

    @Test
    public void testLambdaStep(){
        SelenideLogger.addListener("allure", new AllureSelenide());

        step("Открываем главную страницу", () ->{
            open("https://github.com/");
        });

        step("Ищем репозиторий" + REPO, () ->{
            $(".search-input-container").click();
            $("#query-builder-test").sendKeys(REPO);
            $("#query-builder-test").submit();
        });

        step("Кликаем по ссылке репозитория" + REPO, () ->{
            $(linkText(REPO)).click();
        });

        step("Открываем Issues", () ->{
            $("#issues-tab").click();

        });

        step("Проверяем наличие Issues с номером" + ISSUE, () ->{
            $(withText("#" + ISSUE))
                    .should(Condition.exist);
        });

    }

    @Test
    public void testAnnotatedStep() {
        SelenideLogger.addListener("allure", new AllureSelenide());
        WebSteps steps = new WebSteps();

        steps.openPage();
        steps.searchForRepository(REPO);
        steps.clickOnRepositoryLink(REPO);
        steps.openIssuesTab();
        steps.shouldSeeIssueWithNumber(ISSUE);

    }
}
