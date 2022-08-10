package domain;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Problem {

    private static final Integer MAX_INT = 9;
    private static final Integer MIN_INT = 1;
    private final String correctAnswer;

    private Problem(String correctAnswer) {
        this.correctAnswer = correctAnswer;
    }

    public static Problem generate() {
        Random random = new Random();
        StringBuilder correctAnswer = new StringBuilder();
        while (correctAnswer.toString().length() < 3) {
            int randomInteger = random.nextInt(MAX_INT - MIN_INT + 1) + MIN_INT;
            String intString = String.valueOf(randomInteger);
            if (correctAnswer.toString().contains(intString)) continue;
            correctAnswer.append(intString);
        }
        return new Problem(correctAnswer.toString());
    }

    public Result match(String answer) {
        validate(answer);
        Result result = new Result();
        if (this.correctAnswer.equals(answer)) {
            return new Result(3, 0);
        }

        for (int i = 0; i < 3; i++) {
            String firstNumber = Character.toString(answer.charAt(i));
            if (this.correctAnswer.contains(firstNumber)) {
                if (correctAnswer.indexOf(firstNumber) == i) result.strike();
                else result.ball();
            }
        }
        return result;
    }

    private void validate(String answer) {
        char[] charArray = answer.toCharArray();
        if (charArray.length != 3) {
            throw new CustomException("숫자는 반드시 3글자만 입력해주세요.");
        }
        Set<String> set = Stream.of(new String(charArray).split("")).collect(Collectors.toSet());
        if (set.size() != 3) {
            throw new CustomException("중복되는 문자열이 있습니다.");
        }
        try {
            set.forEach(Integer::parseInt);
        } catch (NumberFormatException e) {
            throw new CustomException("숫자만 입력해주세요.");
        }
    }

    public String correctAnswer() {
        return this.correctAnswer;
    }
}
