package tests;

import org.junit.jupiter.api.Test;

import static testdata.TestData.*;

public class TextBoxTests extends TestBase {

    @Test
    void successfulFillFormTest() {

        textBoxPage
                .openPage()
                .typeUserName(userName)
                .typeUserEmail(userEmail)
                .typeCurrentAddress(currentAddress)
                .typePermanentAddress(permanentAddress)
                .submitForm()
                .checkField("name", userName)
                .checkField("email", userEmail)
                .checkField("currentAddress", currentAddress)
                .checkField("permanentAddress", permanentAddress);
    }

    @Test
    void successfulFillFormWithoutAddressTest() {

        textBoxPage
                .openPage()
                .typeUserName(userName)
                .typeUserEmail(userEmail)
                .submitForm()
                .checkField("name", userName)
                .checkField("email", userEmail);
    }

    @Test
    void successfulFillFormWithoutAddressTest_chaining() {

        textBoxPage
                .openPage()
                .typeUserName(userName)
                .typeUserEmail(userEmail)
                .submitForm()
                .checkField("name", userName)
                .checkField("email", userEmail);
    }

    @Test
    void emptyFormTest() {

        textBoxPage
                .openPage()
                .submitForm();
    }
}