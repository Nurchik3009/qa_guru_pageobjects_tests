package pages;

import com.codeborne.selenide.SelenideElement;
import pages.components.CalendarComponent;
import pages.components.RegistrationResultComponent;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class RegistrationPage {

    private final CalendarComponent calendar = new CalendarComponent();
    private final RegistrationResultComponent registrationResult =
            new RegistrationResultComponent();

    // Elements
    private SelenideElement firstNameInput = $("#firstName");
    private SelenideElement lastNameInput = $("#lastName");
    private SelenideElement emailInput = $("#userEmail");
    private SelenideElement genderWrapper = $("#genterWrapper");
    private SelenideElement phoneNumberInput = $("#userNumber");
    private SelenideElement dateOfBirthInput = $("#dateOfBirthInput");
    private SelenideElement subjectsInput = $("#subjectsInput");
    private SelenideElement hobbiesWrapper = $("#hobbiesWrapper");
    private SelenideElement pictureUploadInput = $("#uploadPicture");
    private SelenideElement currentAddressInput = $("#currentAddress");
    private SelenideElement stateDropdown = $("#state");
    private SelenideElement cityDropdown = $("#city");
    private SelenideElement stateCityWrapper = $("#stateCity-wrapper");
    private SelenideElement submitButton = $("#submit");

    // Actions

    public RegistrationPage openPage() {
        open("/automation-practice-form");
        $(".practice-form-wrapper").shouldHave(text("Student Registration Form"));

        executeJavaScript("document.getElementById('fixedban')?.remove();");
        executeJavaScript("document.querySelector('footer')?.remove();");

        return this;
    }

    public RegistrationPage typeFirstName(String value) {
        firstNameInput.setValue(value);

        return this;
    }

    public RegistrationPage typeLastName(String value) {
        lastNameInput.setValue(value);

        return this;
    }

    public RegistrationPage typeEmail(String value) {
        emailInput.setValue(value);

        return this;
    }

    public RegistrationPage setGender(String value) {
        genderWrapper.$(byText(value)).click();

        return this;
    }

    public RegistrationPage typePhoneNumber(String value) {
        phoneNumberInput.setValue(value);

        return this;
    }

    public RegistrationPage setDateOfBirth(String day, String month, String year) {
        dateOfBirthInput.click();
        calendar.setDate(day, month, year);

        return this;
    }

    public RegistrationPage typeSubject(String value) {
        subjectsInput.setValue(value).pressEnter();

        return this;
    }

    public RegistrationPage setHobby(String value) {
        hobbiesWrapper.$(byText(value)).click();

        return this;
    }

    public RegistrationPage uploadPicture(String fileName) {
        pictureUploadInput.uploadFromClasspath(fileName);

        return this;
    }

    public RegistrationPage typeCurrentAddress(String value) {
        currentAddressInput.setValue(value);

        return this;
    }

    public RegistrationPage setState(String value) {
        stateDropdown.click();
        stateCityWrapper.$(byText(value)).click();

        return this;
    }

    public RegistrationPage setCity(String value) {
        cityDropdown.click();
        stateCityWrapper.$(byText(value)).click();

        return this;
    }

    public RegistrationPage setStateAndCity(String state, String city) {
        setState(state);
        setCity(city);

        return this;
    }

    public RegistrationPage submit() {
        submitButton.scrollIntoView(true);
        submitButton.shouldBe(com.codeborne.selenide.Condition.visible);
        executeJavaScript("arguments[0].click();", submitButton);

        return this;
    }

    public RegistrationPage checkRegistrationResult() {
        registrationResult.checkRegistrationResult();
        return this;
    }

    public RegistrationPage checkResult(String key, String value) {
        registrationResult.checkResult(key, value);
        return this;
    }

}