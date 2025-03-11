package ui.utility;

import com.github.javafaker.Faker;
import ui.pojos.AddressPojo;

import java.util.Locale;
import java.util.Random;

public class FakerUtility {

    private static Faker faker = new Faker(new Locale("en-IND"));

    public static AddressPojo getFakeAddress() {

        AddressPojo addressPojo = new AddressPojo(faker.company().name(), faker.address().buildingNumber(),
                faker.address().streetAddress(), faker.address().city(), faker.numerify("17401"),
                faker.phoneNumber().cellPhone(), faker.phoneNumber().cellPhone(),
                "Present Address", "Home Address", faker.address().state());

        return addressPojo;
    }

    public static String getFakeEmail() {
        return faker.internet().emailAddress();
    }

    public static String getFakeMobile() {
        Random random = new Random();

        // Ensure the first digit is 7, 8, or 9
        int firstDigit = 7 + random.nextInt(3); // Generates 7, 8, or 9

        // Generate remaining 9 digits using Faker
        String remainingDigits = faker.numerify("#########"); // 9 random digits

        // Combine to form a valid Indian mobile number
        String mobileNumber = firstDigit + remainingDigits;
        return mobileNumber;
    }


}
