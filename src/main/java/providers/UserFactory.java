package providers;

import model.User;

import java.security.SecureRandom;

public class UserFactory {

    public User getWholeUser() {
        return new User(randomString("") + "@gmail.com", randomString("Kasia"),
                randomString("Kowalska"), randomString("ul. Woronicza15"),
                randomString("Warszawa"), randomString("mazowieckie"), "Poland",
                randomString("501"));
    }

    static final String alphabet = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
    static SecureRandom random = new SecureRandom();


    public String randomString(String text) {
        StringBuilder stringBuilder = new StringBuilder(5);
        for (int i = 0; i < 5; i++) {
            stringBuilder.append(alphabet.charAt(random.nextInt(alphabet.length())));
        }
        return text + stringBuilder.toString();
    }
}
