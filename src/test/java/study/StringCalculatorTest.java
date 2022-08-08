package study;

import com.sun.tools.javac.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;

public class StringCalculatorTest {

    /**
     * 사용자가 입력한 문자열 값에 따라 사칙연산을 수행할 수 있는 계산기를 구현해야 한다.
     * 문자열 계산기는 사칙연산의 계산 우선순위가 아닌 입력 값에 따라 계산 순서가 결정된다. 즉, 수학에서는 곱셈,
     * 나눗셈이 덧셈, 뺄셈 보다 먼저 계산해야 하지만 이를 무시한다.
     * 예를 들어 "2 + 3 * 4 / 2"와 같은 문자열을 입력할 경우 2 + 3 * 4 / 2 실행 결과인 10을 출력해야 한다.
     */
    @ParameterizedTest
    @CsvSource(value = {"2 + 3 * 4 / 2,10,true", "1 + 4 * 5,21,false"}, delimiter = ',')
    @DisplayName("문자열 계산기")
    void stringCalculate(String input, Integer resultNumber, Boolean expected) {
        Calculator calculator = new Calculator(input);
        Integer result = calculator.calculate();
        assertThat(result.equals(resultNumber)).isEqualTo(expected);
    }

    static class Calculator {

        private final List<String> operator = List.of("+", "-", "*", "/");
        private final String[] splitString;

        public Calculator(String expression) {
            validate(expression);
            this.splitString = expression.split(" ");
        }

        private void validate(String expression) {
            if (expression.length() < 1) {
                throw new IllegalArgumentException("유효하지 않는 문자열입니다.");
            }

            String[] splitString = expression.split(" ");
            for (String string : splitString) {
                if (!isInteger(string) && !operator.contains(string)) {
                    throw new IllegalArgumentException("유효하지 않는 식입니다.");
                }
            }
        }

        public Integer calculate() {
            Integer result = 0;
            String targetOperator = "";
            for (String point : splitString) {
                if (isInteger(point)) {
                    if (targetOperator.equals("")) {
                        result = operate(result, Integer.parseInt(point), Operator.PLUS);
                    } else {
                        Operator operator = Operator.findByOperatorString(targetOperator);
                        result = operate(result, Integer.parseInt(point), operator);
                    }
                } else if (operator.contains(point)) {
                    targetOperator = point;
                } else {
                    throw new IllegalArgumentException("유효하지 않는 문자열입니다.");
                }
            }
            return result;
        }

        private Boolean isInteger(String input) {
            try {
                Integer.parseInt(input);
                return true;
            } catch (NumberFormatException e) {
                return false;
            }
        }

        private Integer operate(Integer before, Integer after, Operator operator) {
            switch (operator) {
                case PLUS: {
                    before += after;
                    break;
                }
                case MINUS: {
                    before -= after;
                    break;
                }
                case MULTIPLY: {
                    before *= after;
                    break;
                }
                case DIVIDE: {
                    before /= after;
                    break;
                }
                default: throw new IllegalArgumentException("존재하지 않는 연산자입니다.");
            }
            return before;
        }
    }

    public enum Operator {
        PLUS("+"), MINUS("-"), MULTIPLY("*"), DIVIDE("/");

        private String operatorStr;

        Operator(String string) {
            this.operatorStr = string;
        }

        public String getOperatorStr() {
            return this.operatorStr;
        }

        public static Operator findByOperatorString(String string) {
            for (Operator operator : Operator.values()) {
                if (string.equals(operator.getOperatorStr())) {
                    return operator;
                }
            }
            throw new IllegalArgumentException("존재하지 않는 연산자입니다.");
        }
    }
}
