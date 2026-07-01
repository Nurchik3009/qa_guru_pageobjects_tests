package tests;

import org.junit.jupiter.api.Test;

import static testdata.TestData.*;

public class StudentRegistrationFormTest extends TestBase {

    @Test
    void successfulFillFormTest() {

        registrationPage
                .openPage()
                .removeBanners()
                .typeFirstName(studentFirstName)
                .typeLastName(studentLastName)
                .typeEmail(studentEmail)
                .setGender(studentGender)
                .typePhoneNumber(studentPhone)
                .setDateOfBirth(studentBirthDay, studentBirthMonth, studentBirthYear)
                .typeSubject(studentSubject)
                .setHobby(studentHobby)
                .uploadPicture(studentPicture)
                .typeCurrentAddress(studentAddress)
                .setStateAndCity(studentState, studentCity)
                .submit()
                .checkRegistrationResult()
                .checkResult("Student Name", studentFirstName + " " + studentLastName)
                .checkResult("Student Email", studentEmail)
                .checkResult("Gender", studentGender)
                .checkResult("Mobile", studentPhone)
                .checkResult("Date of Birth",
                        studentBirthDay + " " + studentBirthMonth + "," + studentBirthYear)
                .checkResult("Subjects", studentSubject)
                .checkResult("Hobbies", studentHobby)
                .checkResult("Picture", studentPicture)
                .checkResult("Address", studentAddress)
                .checkResult("State and City", studentState + " " + studentCity);
    }

    @Test
    void submitFormWithRequiredFieldsOnlyTest() {

        registrationPage
                .openPage()
                .removeBanners()
                .typeFirstName(studentFirstName)
                .typeLastName(studentLastName)
                .setGender(studentGender)
                .typePhoneNumber(studentPhone)
                .submit()
                .checkRegistrationResult()
                .checkResult("Student Name", studentFirstName + " " + studentLastName)
                .checkResult("Gender", studentGender)
                .checkResult("Mobile", studentPhone);
    }

    @Test
    void emptyFormNegativeTest() {

        registrationPage
                .openPage()
                .removeBanners()
                .submit();
    }

    @Test
    void missingFirstNameNegativeTest() {

        registrationPage
                .openPage()
                .removeBanners()
                .typeLastName(studentLastName)
                .setGender(studentGender)
                .typePhoneNumber(studentPhone)
                .submit();
    }

    @Test
    void missingGenderNegativeTest() {

        registrationPage
                .openPage()
                .removeBanners()
                .typeFirstName(studentFirstName)
                .typeLastName(studentLastName)
                .typePhoneNumber(studentPhone)
                .submit();
    }

    @Test
    void invalidPhoneNumberNegativeTest() {

        registrationPage
                .openPage()
                .removeBanners()
                .typeFirstName(studentFirstName)
                .typeLastName(studentLastName)
                .setGender(studentGender)
                .typePhoneNumber(invalidPhone)
                .submit();
    }

    @Test
    void invalidEmailNegativeTest() {

        registrationPage
                .openPage()
                .removeBanners()
                .typeEmail(invalidEmail)
                .submit();
    }
}