package allure_tests;

import io.qameta.allure.*;
import org.testng.Assert;
import org.testng.annotations.Test;

@Epic("Math Epic")
@Feature("Math feature")
public class AllureTest {

    @Test
    @Description("it's additional test")
    @Severity(SeverityLevel.NORMAL)
    @Story("basic math operation")
    public void additional(){
        int a = 2, b=3;
        int sum = a+b;
        Allure.step("first step sum = a+b");
        Assert.assertEquals(sum, 5);
    }

    @Test
    @Description("it's additional negative test")
    @Severity(SeverityLevel.CRITICAL)
    @Story("basic math operation")
    public void additionalNegative(){
        int a = 2, b=3;
        Allure.step("get a anb b");
        int sum = a+b;
        Allure.step("first step sum = a+b");
        Assert.assertEquals(sum, 6);
    }
}

//Основные аннотации:
//@Epic, @Feature, @Story: Для группировки тестов по бизнес-логике
//        (например, «Аутентификация» -> «Вход» -> «Успешный вход»).
//@DisplayName: Присваивает тесту человекопонятное название.
//@Description: Добавляет подробное описание к тесту.
//@Severity: Указывает уровень критичности (BLOCKER, CRITICAL, NORMAL, MINOR, TRIVIAL).
//@Owner: Назначает ответственного за тест.
//@Link, @Issue, @TmsLink: Связывают тест с задачами в Jira или системах управления тестами.
//@Attachment: Прикрепляет файлы (скриншоты, логи) к отчету.
//@Step: Маркирует метод как отдельный шаг в отчете.
