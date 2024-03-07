public class CreditPage {
    private final SelenideElement header = $(byText("Кредит по данным карты"));
    private final SelenideElement number = $("[placeholder='0000 0000 0000 0000']");
    private final SelenideElement month = $("[placeholder='08']");
    private final SelenideElement year = $("[placeholder='22']");
    private final SelenideElement holder = $(byText("Владелец")).parent().$(".input__control");
    private final SelenideElement cvc = $("[placeholder='999']");
    private final SelenideElement successNotification = $(".notification_status_ok");
    private final SelenideElement errorNotification = $(".notification_status_error");
    private final SelenideElement continueButton = $$("button").find(exactText("Продолжить"));
    private final SelenideElement invalidValueFormat = $(byText("Неверный формат"));
    private final SelenideElement invalidOwner = $(byText("Поле обязательно для заполнения"));
    private final SelenideElement invalidDateFormat = $(byText("Неверно указан срок действия карты"));
    private final SelenideElement cardEndedError = $(byText("Истёк срок действия карты"));
}

 public class CreditPage {
    private final SelenideElement header = $(byText("Кредит по данным карты"));
    private final SelenideElement number = $("[placeholder='0000 0000 0000 0000']");
    private final SelenideElement month = $("[placeholder='08']");
    private final SelenideElement year = $("[placeholder='22']");
    private final SelenideElement holder = $(byText("Владелец")).parent().$(".input__control");
    private final SelenideElement cvc = $("[placeholder='999']");
    private final SelenideElement successNotification = $(".notification_status_ok");
    private final SelenideElement errorNotification = $(".notification_status_error");
    private final SelenideElement continueButton = $$("button").find(exactText("Продолжить"));
    private final SelenideElement invalidValueFormat = $(byText("Неверный формат"));
    private final SelenideElement invalidOwner = $(byText("Поле обязательно для заполнения"));
    private final SelenideElement invalidDateFormat = $(byText("Неверно указан срок действия карты"));
    private final SelenideElement cardEndedError = $(byText("Истёк срок действия карты"));
}
}
