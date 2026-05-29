package tests;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.cssClass;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;

public class TextBoxTests extends TestBase {

    @Test
    void successfulFillFormTest() {
        open("/text-box");
        $("[id=userName]").setValue("Ainur K");
        $("[id=userEmail]").setValue("Ainur@gmail.com");
        $("[id=currentAddress]").setValue("Abaya 130");
        $("[id=permanentAddress]").setValue("Abaya 140");
        $("[id=submit]").click();

        $("[id=output] [id=name]").shouldHave(text("Ainur K"));
        $("[id=output] [id=email]").shouldHave(text("Ainur@gmail.com"));
        $("[id=output] [id=currentAddress]").shouldHave(text("Abaya 130"));
        $("[id=output] [id=permanentAddress]").shouldHave(text("Abaya 140"));
        //sleep(2000);
    }
    @Test
    void invalidEmailTest() {
        open("/text-box");
        $("[id=userName]").setValue("Ainur K");
        $("[id=userEmail]").setValue("invalidEmail");
        $("[id=currentAddress]").setValue("Abaya 130");
        $("[id=permanentAddress]").setValue("Abaya 140");
        $("[id=submit]").click();


        $("#userEmail").shouldHave(cssClass("field-error"));
    }
}