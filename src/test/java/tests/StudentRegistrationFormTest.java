package tests;

import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;


public class StudentRegistrationFormTest extends TestBase {
    @Test
    void successfulFillFormTest() {

        open("/automation-practice-form");

        $("[id=firstName]").setValue("Ainur");
        $("[id=lastName]").setValue("Kaliakbarova");
        $("[id=userEmail]").setValue("Ainur@gmail.com");

        $("#genterWrapper").$(byText("Female")).click();

        $("[id=userNumber]").setValue("7018880808");

        $("[id=dateOfBirthInput]").click();
        $(".react-datepicker__year-select").selectOption("1986");
        $(".react-datepicker__month-select").selectOption("September");
        $(".react-datepicker__day--029").click();

        $("[id=subjectsInput]").setValue("English").pressEnter();

        $("#hobbiesWrapper").$$("label").findBy(text("Music")).click();

        $("#uploadPicture").uploadFromClasspath("QA.jpg");

        $("[id=currentAddress]").setValue("Abaya 130");

        $("#react-select-3-input").setValue("NCR").pressEnter();
        $("#react-select-4-input").setValue("Delhi").pressEnter();


        executeJavaScript("""
                    document.getElementById('fixedban')?.remove();
                    document.querySelector('footer')?.remove();
                """);
        $("#submit").scrollIntoView(true);

        executeJavaScript("arguments[0].click();", $("#submit"));

        //sleep(5000);

        $(".modal-content").should(appear);
        $("[id=example-modal-sizes-title-lg]").shouldHave(text("Thanks for submitting the form"));

        $(".table-responsive").shouldHave(text("Ainur Kaliakbarova"));
        $(".table-responsive").shouldHave(text("Ainur@gmail.com"));
        $(".table-responsive").shouldHave(text("Female"));
        $(".table-responsive").shouldHave(text("7018880808"));
        $(".table-responsive").shouldHave(text("29 September,1986"));
        $(".table-responsive").shouldHave(text("English"));
        $(".table-responsive").shouldHave(text("Music"));
        $(".table-responsive").shouldHave(text("QA.jpg"));
        $(".table-responsive").shouldHave(text("Abaya 130"));
        $(".table-responsive").shouldHave(text("NCR Delhi"));

    }

    @Test
    void submitFormWithRequiredFieldsOnlyTest() {
        open("/automation-practice-form");

        $("[id=firstName]").setValue("Ainur");
        $("[id=lastName]").setValue("Kaliakbarova");

        $("#genterWrapper").$(byText("Female")).click();

        $("[id=userNumber]").setValue("7018880808");

        $("#submit").scrollIntoView(true);

        executeJavaScript("arguments[0].click();", $("#submit"));


        $(".modal-content").should(appear);
        $("[id=example-modal-sizes-title-lg]").shouldHave(text("Thanks for submitting the form"));

        $(".table-responsive").shouldHave(text("Ainur Kaliakbarova"));
        $(".table-responsive").shouldHave(text("Female"));
        $(".table-responsive").shouldHave(text("7018880808"));
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

        $("#lastName").setValue("Kaliakbarova");
        $("#genterWrapper").$(byText("Female")).click();
        $("#userNumber").setValue("7018880808");

        $("#submit").scrollIntoView(true);
        executeJavaScript("arguments[0].click();", $("#submit"));

        $(".modal-content").shouldNot(appear);
    }

    @Test
    void missingGenderNegativeTest() {

        open("/automation-practice-form");

        $("#firstName").setValue("Ainur");
        $("#lastName").setValue("Kaliakbarova");
        $("#userNumber").setValue("7018880808");

        $("#submit").scrollIntoView(true);
        executeJavaScript("arguments[0].click();", $("#submit"));

        $(".modal-content").shouldNot(appear);
    }

    @Test
    void invalidPhoneNumberNegativeTest() {

        open("/automation-practice-form");

        $("#firstName").setValue("Ainur");
        $("#lastName").setValue("Kaliakbarova");
        $("#genterWrapper").$(byText("Female")).click();
        $("#userNumber").setValue("12345");

        $("#submit").scrollIntoView(true);
        executeJavaScript("arguments[0].click();", $("#submit"));

        $(".modal-content").shouldNot(appear);
    }

    @Test
    void invalidEmailNegativeTest() {
        open("/automation-practice-form");

        $("#userEmail").setValue("invalidEmail");

        $("#submit").scrollIntoView(true);

        executeJavaScript("arguments[0].click();", $("#submit"));

        $(".modal-content").shouldNot(exist);
    }
}

