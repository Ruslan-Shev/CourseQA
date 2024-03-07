package data;

import com.github.javafaker.Faker;
import lombok.Value;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class DataHelper {
    private static final Faker fakerEn = new Faker(new Locale("en"));
    private static final Faker fakerRu = new Faker(new Locale("ru"));

    private DataHelper() {
    }

    public static String getLastYear(int minusOneYear) {
        return LocalDate.now().minusYears(minusOneYear).format(DateTimeFormatter.ofPattern("yy"));
    }

    public static String generateHolderName() {
        return fakerEn.name().lastName() + " " + fakerEn.name().firstName();
    }

    public static String generateMonth(int month) {
        return LocalDate.now().plusMonths(month).format(DateTimeFormatter.ofPattern("MM"));
    }

    public static String generateYear(int year) {
        return LocalDate.now().plusYears(year).format(DateTimeFormatter.ofPattern("yy"));
    }

    @Value
    public static class CardInfo {
        String number;
        String month;
        String year;
        String holder;
        String cvc;
    }

    public static String getApprovedCardNumber() {
        return "5555 5555 5555 5551";
    }

    public static String getDeclinedCardNumber() {
        return "5555 5555 5555 5552";
    }

    public static CardInfo getValidDataWithApprovedCard() {
        return new CardInfo(getApprovedCardNumber(),
                generateMonth(0),
                generateYear(0),
                generateHolderName(),
                fakerEn.numerify("###"));
    }

    public static CardInfo getValidDataWithDeclinedCard() {
        return new CardInfo(getDeclinedCardNumber(),
                generateMonth(0),
                generateYear(0),
                generateHolderName(),
                fakerEn.numerify("###"));
    }

    public static CardInfo getCardInfoWithEmptyFields() {
        return new CardInfo(null, null, null, null, null);
    }

    public static CardInfo getCardInfoWithEmptyNumber() {
        return new CardInfo(null, generateMonth(0), generateYear(0), generateHolderName(), fakerEn.numerify("###"));
    }

    @Value
    public static class CardInfo {
        String number;
        String month;
        String year;
        String holder;
        String cvc;
    }

    public static CardInfo getCardInfoWithRandomNumber() {
        return new CardInfo(fakerEn.numerify("#### #### #### ####"),
                generateMonth(0),
                generateYear(0),
                generateHolderName(),
                fakerEn.numerify("###"));
    }

    public static CardInfo getCardInfoWith17() {
        return new CardInfo(fakerEn.numerify("#### #### #### #### #"),
                generateMonth(0),
                generateYear(0),
                generateHolderName(),
                fakerEn.numerify("###"));
    }

    public static CardInfo getCardInfoWith15() {
        return new CardInfo(fakerEn.numerify("#### #### #### ###"),
                generateMonth(0),
                generateYear(0),
                generateHolderName(),
                fakerEn.numerify("###"));
    }

    public static CardInfo getCardInfoWithSpecialCharacters() {
        return new CardInfo(fakerEn.numerify("@#$% !&*( ^&*( )(*&"),
                generateMonth(0),
                generateYear(0),
                generateHolderName(),
                fakerEn.numerify("###"));
    }

    @Value
    public static class CardInfo {
        String number;
        String month;
        String year;
        String holder;
        String cvc;

        public CardInfo(String approvedCardNumber, String s, String s1, String s2, String numerify) {
        }
    }

    public static CardInfo createCardInfoWithNullMonth() {
    return new CardInfo(getApprovedCardNumber(),
            null,
            generateYear(0),
            generateHolderName(),
            fakerEn.numerify("###"));
}

public static CardInfo createCardInfoWithMonthZeroZero() {
    return new CardInfo(getApprovedCardNumber(),
            LocalDate.now().format(DateTimeFormatter.ofPattern("00")),
            generateYear(0),
            generateHolderName(),
            fakerEn.numerify("###"));
}

public static CardInfo createCardInfoWithMonthThirteen() {
    return new CardInfo(getApprovedCardNumber(),
            "13",
            generateYear(0),
            generateHolderName(),
            fakerEn.numerify("###"));
}

public static CardInfo createCardInfoWithMonthSingleDigit() {
    return new CardInfo(getApprovedCardNumber(),
            fakerEn.numerify("#"),
            generateYear(0),
            generateHolderName(),
            fakerEn.numerify("###"));
}

public static CardInfo createCardInfoWithMonthSpecialCharacters() {
    return new CardInfo(getApprovedCardNumber(),
            fakerEn.numerify("&$"),
            generateYear(0),
            generateHolderName(),
            fakerEn.numerify("###"));
}

    public static CardInfo createCardInfoWithNullYear() {
    return new CardInfo(getDeclinedCardNumber(),
            generateMonth(0),
            null,
            generateHolderName(),
            fakerEn.numerify("###"));
}

public static CardInfo createCardInfoWithLastYear() {
    return new CardInfo(getApprovedCardNumber(),
            generateMonth(0),
            getLastYear(1),
            generateHolderName(),
            fakerEn.numerify("###"));
}

public static CardInfo createCardInfoWithSpecialCharactersYear() {
    return new CardInfo(getApprovedCardNumber(),
            generateMonth(0),
            fakerEn.numerify("&$"),
            generateHolderName(),
            fakerEn.numerify("###"));
}

public static CardInfo createCardInfoWith1NumberYear() {
    return new CardInfo(getApprovedCardNumber(),
            generateMonth(0),
            fakerEn.numerify("#"),
            generateHolderName(),
            fakerEn.numerify("###"));
}

public static CardInfo createCardInfoWithNullOwner() {
    return new CardInfo(getApprovedCardNumber(),
            generateMonth(0),
            generateYear(0),
            null,
            fakerEn.numerify("###"));
}

   public static CardInfo createCardInfoWithOwnerNumbers() {
    return new CardInfo(getApprovedCardNumber(),
            generateMonth(0),
            generateYear(0),
            fakerEn.numerify("####### ######"),
            fakerEn.numerify("###"));
}

public static CardInfo createCardInfoWithOwnerSpecialCharacters() {
    return new CardInfo(getApprovedCardNumber(),
            generateMonth(0),
            generateYear(0),
            ")(*&^%$#@!@",
            fakerEn.numerify("###"));
}

public static CardInfo createCardInfoWithOwnerCyrillic() {
    return new CardInfo(getApprovedCardNumber(),
            generateMonth(0),
            generateYear(0),
            fakerRu.name().firstName() + " " + fakerRu.name().lastName(),
            fakerEn.numerify("###"));
}

public static CardInfo createCardInfoWithEmptyCVC() {
    return new CardInfo(getApprovedCardNumber(),
            generateMonth(0),
            generateYear(0),
            generateHolderName(),
            null);
}

public static CardInfo createCardInfoWithCVC2Numbers() {
    return new CardInfo(getApprovedCardNumber(),
            generateMonth(0),
            generateYear(0),
            generateHolderName(),
            fakerEn.numerify("##"));
}

    public static CardInfo getCardInfoWithCVCSpecialCharacters() {
    return new CardInfo(getApprovedCardNumber(),
            generateMonth(0),
            generateYear(0),
            generateHolderName(),
            "CVC");
}

public static CardInfo getCardInfoWithCVCLetter() {
    return new CardInfo(getApprovedCardNumber(),
            generateMonth(0),
            generateYear(0),
            generateHolderName(),
            "CVC");
}
}
