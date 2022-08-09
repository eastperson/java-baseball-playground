package domain;

public class Result {
    private int strike;
    private int ball;

    public Result() {}
    public Result(Integer strike, Integer ball) {
        this.strike = strike;
        this.ball = ball;
    }

    public static Result defaultResult() {
        return new Result();
    }

    public Boolean isCorrectAnswer() {
        return this.strike == 3;
    }

    public void strike() {
        this.strike++;
    }

    public void ball() {
        this.ball++;
    }

    public int strikeCount() {
        return this.strike;
    }

    public int ballCount() {
        return this.ball;
    }

    public Boolean isNothing() {
        return this.strike == 0 && this.ball == 0;
    }
}
