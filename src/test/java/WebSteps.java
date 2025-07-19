import com.codeborne.selenide.Condition;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static org.openqa.selenium.By.linkText;

public class WebSteps {

    @Step ("Открываем главную страницу")
    public void openPage() {
        open("https://github.com/");
    }

    @Step ("Ищем репозиторий")
    public void searchForRepository(String repo) {
        $(".search-input-container").click();
        $("#query-builder-test").sendKeys(repo);
        $("#query-builder-test").submit();
    }

    @Step ("Кликаем по ссылке репозитория")
    public void clickOnRepositoryLink(String repo) {
        $(linkText(repo)).click();
    }

    @Step("Открываем Issues")
    public void openIssuesTab() {
        $("#issues-tab").click();
    }

    @Step ("Проверяем наличие Issues с номером")
    public void shouldSeeIssueWithNumber(int issue) {
        $(withText("#" + issue))
                .should(Condition.exist);
    }


}
