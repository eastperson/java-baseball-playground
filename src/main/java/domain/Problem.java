package domain;

import java.util.Random;

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
        if (answer.length() != 3) {
            throw new CustomException();
        }
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

    public String correctAnswer() {
        return this.correctAnswer;
    }
}
