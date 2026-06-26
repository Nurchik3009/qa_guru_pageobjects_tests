package tests;

import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;
import static testdata.TestData.*;

public class StudentRegistrationFormTest extends TestBase {

    @Test
    void successfulFillFormTest() {
        open("/automation-practice-form");

        executeJavaScript("""
                    document.getElementById('fixedban')?.remove();
                    document.querySelector('footer')?.remove();
                """);

        $("[id=firstName]").setValue(studentFirstName);
        $("[id=lastName]").setValue(studentLastName);
        $("[id=userEmail]").setValue(studentEmail);

        $("#genterWrapper").$(byText(studentGender)).click();

        $("[id=userNumber]").setValue(studentPhone);

        $("[id=dateOfBirthInput]").click();
        $(".react-datepicker__year-select").selectOption(studentBirthYear);
        $(".react-datepicker__month-select").selectOption(studentBirthMonth);

        String dayClass = String.format(".react-datepicker__day--0%s", studentBirthDay);
        $(dayClass).click();

        $("[id=subjectsInput]").setValue(studentSubject).pressEnter();

        $("#hobbiesWrapper").$$("label").findBy(text(studentHobby)).click();

        $("#uploadPicture").uploadFromClasspath(studentPicture);

        $("[id=currentAddress]").setValue(studentAddress);

        $("#react-select-3-input").setValue(studentState).pressEnter();
        $("#react-select-4-input").setValue(studentCity).pressEnter();

        $("#submit").scrollIntoView(true);
        executeJavaScript("arguments[0].click();", $("#submit"));


        $(".modal-content").should(appear);
        $("[id=example-modal-sizes-title-lg]").shouldHave(text("Thanks for submitting the form"));

        $(".table-responsive").shouldHave(
                text(studentFirstName + " " + studentLastName),
                text(studentEmail),
                text(studentGender),
                text(studentPhone),
                text(studentBirthDay + " " + studentBirthMonth + "," + studentBirthYear),
                text(studentSubject),
                text(studentHobby),
                text(studentPicture),
                text(studentAddress),
                text(studentState + " " + studentCity)
        );
    }

    @Test
    void submitFormWithRequiredFieldsOnlyTest() {
        open("/automation-practice-form");

        $("[id=firstName]").setValue(studentFirstName);
        $("[id=lastName]").setValue(studentLastName);
        $("#genterWrapper").$(byText(studentGender)).click();
        $("[id=userNumber]").setValue(studentPhone);

        $("#submit").scrollIntoView(true);
        executeJavaScript("arguments[0].click();", $("#submit"));

        $(".modal-content").should(appear);
        $("[id=example-modal-sizes-title-lg]").shouldHave(text("Thanks for submitting the form"));

        $(".table-responsive").shouldHave(
                text(studentFirstName + " " + studentLastName),
                text(studentGender),
                text(studentPhone)
        );
    }

    @Test
    void emptyFormNegativeTest() {
        open("/automation-practice-form");

        $("#submit").scrollIntoView(true);
        executeJavaScript("arguments[0].click();", $("#submit"));

        $(".modal-content").shouldNot(exist);
    }

    @Test
    void missingFirstNameNegativeTest() {
        open("/automation-practice-form");

        $("#lastName").setValue(studentLastName);
        $("#genterWrapper").$(byText(studentGender)).click();
        $("#userNumber").setValue(studentPhone);

        $("#submit").scrollIntoView(true);
        executeJavaScript("arguments[0].click();", $("#submit"));

        $(".modal-content").shouldNot(appear);
    }

    @Test
    void missingGenderNegativeTest() {
        open("/automation-practice-form");

        $("#firstName").setValue(studentFirstName);
        $("#lastName").setValue(studentLastName);
        $("#userNumber").setValue(studentPhone);

        $("#submit").scrollIntoView(true);
        executeJavaScript("arguments[0].click();", $("#submit"));

        $(".modal-content").shouldNot(appear);
    }

    @Test
    void invalidPhoneNumberNegativeTest() {
        open("/automation-practice-form");

        $("#firstName").setValue(studentFirstName);
        $("#lastName").setValue(studentLastName);
        $("#genterWrapper").$(byText(studentGender)).click();
        $("#userNumber").setValue(invalidPhone);

        $("#submit").scrollIntoView(true);
        executeJavaScript("arguments[0].click();", $("#submit"));

        $(".modal-content").shouldNot(appear);
    }

    @Test
    void invalidEmailNegativeTest() {
        open("/automation-practice-form");

        $("#userEmail").setValue(invalidEmail);

        $("#submit").scrollIntoView(true);
        executeJavaScript("arguments[0].click();", $("#submit"));

        $(".modal-content").shouldNot(exist);
    }
}