import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;

public class RequestTest {

    @Test
    void shouldSubmitRequest() {
        open("http://localhost:9999/");
        SelenideElement form = $("[method=post]");
        form.$("[data-test-id=name] input").setValue("Петров Иван");
        form.$("[data-test-id=phone] input").setValue("+79213663636");
        form.$("[data-test-id=agreement]").click();
        form.$("[type=button]").click();
        $("[data-test-id=order-success]").shouldHave(exactText("Ваша заявка успешно отправлена! Наш менеджер свяжется с вами в ближайшее время."));
    }

    @Test
    void anErrorMessageShouldBeDisplayed() {
        open("http://localhost:9999/");
        SelenideElement form = $("[method=post]");
        form.$("[data-test-id=name] input").setValue("Petrov Ivan");
        form.$("[data-test-id=phone] input").setValue("+79213663636");
        form.$("[data-test-id=agreement]").click();
        form.$("[type=button]").click();
        $(".input__sub").shouldHave(exactText("Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы."));
    }
}