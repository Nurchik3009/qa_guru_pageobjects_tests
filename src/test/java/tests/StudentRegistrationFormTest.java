package tests;

import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;


public class StudentRegistrationFormTest extends TestBase{
    @Test
    void successfulFillFormTest() {
        open("/automation-practice-form");

        $("[id=firstName]").setValue("Ainur");
        $("[id=lastName]").setValue("Kaliakbarova");
        $("[id=userEmail]").setValue("Ainur@gmail.com");

        $("[for=gender-radio-2]").click();

        $("[id=userNumber]").setValue("7018880808");

        $("[id=dateOfBirthInput]").click();
        $(".react-datepicker__year-select").selectOption("1986");
        $(".react-datepicker__month-select").selectOption("September");
        $(".react-datepicker__day--029").click();

        $("[id=subjectsInput]").setValue("English").pressEnter();

        $("[for=hobbies-checkbox-3]").click();

        $("#uploadPicture").uploadFromClasspath("QA.jpg");

        $("[id=currentAddress]").setValue("Abaya 130");

        $("#react-select-3-input").setValue("NCR").pressEnter();
        $("#react-select-4-input").setValue("Delhi").pressEnter();

        executeJavaScript(
                "let banner = document.getElementById('fixedban');" +
                        "if(banner) banner.remove();"
        );

        executeJavaScript(
                "let footer = document.querySelector('footer');" +
                        "if(footer) footer.remove();"
        );

        $("#submit").scrollIntoView(true);

        executeJavaScript("arguments[0].click();", $("#submit"));

        //sleep(5000);

        $(".modal-content").shouldBe(visible);
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

        $("[for=gender-radio-2]").click();

        $("[id=userNumber]").setValue("7018880808");

        $("#submit").scrollIntoView(true);

        executeJavaScript("arguments[0].click();", $("#submit"));
        //sleep(2000);

        $(".modal-content").shouldBe(visible);
        $("[id=example-modal-sizes-title-lg]").shouldHave(text("Thanks for submitting the form"));

        $(".table-responsive").shouldHave(text("Ainur Kaliakbarova"));
        $(".table-responsive").shouldHave(text("Female"));
        $(".table-responsive").shouldHave(text("7018880808"));
        $(".table-responsive").shouldHave(text("29 May,2026"));
    }
    @Test
    void emptyFormNegativeTest() {

        open("/automation-practice-form");

        $("#submit").scrollIntoView(true);
        executeJavaScript("arguments[0].click();", $("#submit"));

        $(".modal-content").shouldNot(appear);
    }
    @Test
    void missingFirstNameNegativeTest() {

        open("/automation-practice-form");

        $("#lastName").setValue("Kaliakbarova");
        $("[for=gender-radio-2]").click();
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
        $("[for=gender-radio-2]").click();
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

        $("#userEmail").shouldHave(value("invalidEmail"));
    }
}

