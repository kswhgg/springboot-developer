import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class JUnitCycleQuiz {

    @BeforeEach
    public void beforeEach() {
        System.out.println("Hello!");
    }

    @Test
    public void junitQuiz3() {
        System.out.println("This is first Test");
    }

    @Test
    public void junitQuiz4() {
        System.out.println("This is second Test");
    }

    @AfterAll
    public static void AfterAll() {
        System.out.println("Bye!");
    }

}
