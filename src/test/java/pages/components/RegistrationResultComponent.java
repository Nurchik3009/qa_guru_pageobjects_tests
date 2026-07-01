package pages.components;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.appear;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;

public class RegistrationResultComponent {

    private final SelenideElement modalTitle = $("#example-modal-sizes-title-lg");
    private final SelenideElement table = $(".table-responsive");

    public RegistrationResultComponent checkRegistrationResult() {
        $(".modal-dialog").should(appear);
        modalTitle.shouldHave(text("Thanks for submitting the form"));
        return this;
    }

    public RegistrationResultComponent checkResult(String key, String value) {
        table.$(byText(key))
                .parent()
                .shouldHave(text(value));

        return this;
    }
}