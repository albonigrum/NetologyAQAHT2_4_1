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
    private static final AuthorizationInfo HardcodedUser;
    private static final String HardcodedVerifyCode;
    private static final String[] HardcodedCards;

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

        HardcodedUser = new AuthorizationInfo((String) mapL1.get("login"), (String) mapL1.get("password"));
        HardcodedVerifyCode = (String) mapL1.get("verification code (hardcoded)");

        String firstCard = (String) mapL3.get("cards").get("first").get("number");
        String secondCard = (String) mapL3.get("cards").get("second").get("number");
        HardcodedCards = new String[]{firstCard, secondCard};
    }

    public static AuthorizationInfo getHardcodedUser() {
        return HardcodedUser;
    }

    public static String getHardcodedVerifyCode() {
        return HardcodedVerifyCode;
    }

    public static String[] getHardcodedCards() {
        return HardcodedCards;
    }

    @Value
    public static class AuthorizationInfo {
        String login;
        String password;
    }
}
