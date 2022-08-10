package domain.view;

import domain.Result;

import java.util.Scanner;

public class PlayerView {
    private Result lastResult;
    private boolean restarting;

    public PlayerView(Result result) {
        this.lastResult = result;
    }

    public Result result() {
        return this.lastResult;
    }

    public String input() {
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }

    public Boolean isWin() {
        return this.lastResult.isCorrectAnswer();
    }

    public Boolean isProcessingGame() {
        return !isWin();
    }

    public void record(Result result) {
        this.lastResult = result;
    }

    public boolean isBall() {
        return this.lastResult.ballCount() > 0;
    }

    public boolean isStrike() {
        return this.lastResult.strikeCount() > 0;
    }

    public boolean isRestart() {
        while (true) {
            System.out.println("게임을 새로 시작하려면 1, 종료하려면 2를 입력하세요.");
            Scanner scanner = new Scanner(System.in);
            String input = scanner.nextLine();
            if (input.equals("1")) {
                this.restarting = true;
                return true;
            }
            if (input.equals("2")) {
                return false;
            }
            System.out.println("숫자를 다시 입력해주세요");
        }
    }

    public boolean isRestarting() {
        return this.restarting;
    }

    public void restart() {
        this.restarting = false;
    }
}
