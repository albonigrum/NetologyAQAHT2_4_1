package ru.netology.data;

import lombok.Value;
import org.yaml.snakeyaml.Yaml;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;

public class DataHelper {
    private DataHelper() {}
    private static final Path HARDCODED_DATA_DOCUMENT_PATH = Paths.get("src/test/resources/hardcoded_user.yml");
    private static final AuthorizationInfo HARDCODED_USER;
    private static final String HARDCODED_VERIFY_CODE;
    private static final String[] HARDCODED_CARDS;

    static {
        InputStream in = null;
        try {
            in = Files.newInputStream(HARDCODED_DATA_DOCUMENT_PATH);
        } catch (IOException e) {
            e.printStackTrace();
        }

        Yaml yaml = new Yaml();
        Object mapL0 = yaml.load(in);
        Map<String, Object> mapL1 = (Map<String, Object>) mapL0;
        Map<String, Map<String, Map<String, Object>>> mapL3 = (Map<String, Map<String, Map<String, Object>>>) mapL0;

        HARDCODED_USER = new AuthorizationInfo((String) mapL1.get("login"), (String) mapL1.get("password"));
        HARDCODED_VERIFY_CODE = (String) mapL1.get("verification code (hardcoded)");

        String firstCard = (String) mapL3.get("cards").get("first").get("number");
        String secondCard = (String) mapL3.get("cards").get("second").get("number");
        HARDCODED_CARDS = new String[]{firstCard, secondCard};
    }

    public static AuthorizationInfo getHardcodedUser() {
        return HARDCODED_USER;
    }

    public static String getHardcodedVerifyCode() {
        return HARDCODED_VERIFY_CODE;
    }

    public static String[] getHardcodedCards() {
        return HARDCODED_CARDS;
    }

    @Value
    public static class AuthorizationInfo {
        String login;
        String password;
    }
}
