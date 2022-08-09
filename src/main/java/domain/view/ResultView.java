package domain.view;

import domain.CustomException;
import domain.Problem;
import domain.Result;

public class ResultView {

    public static void main(String[] args) {
        ResultView.start();
    }

    public static void start() {
        PlayerView player = new PlayerView(Result.defaultResult());
        Problem problem = Problem.generate();
        while (player.isProcessingGame() || player.isRestart()) {
            try {
                if (player.isRestarting()) {
                    problem = Problem.generate();
                    player.restart();
                }
                System.out.println("숫자를 입력해 주세요 : ");
                String answer = player.input();
                System.out.println();
                Result lastResult = problem.match(answer);
                player.record(lastResult);
                announceResult(player);
            } catch (CustomException e) {
                System.out.println("오류가 발생했습니다. 처음부터 다시 진행해주세요");
                System.out.println(e.getMessage());
            }
        }
    }

    public static void announceResult(PlayerView player) {
        if (player.isWin()) {
            System.out.println("3개의 숫자를 모두 맞히셨습니다! 게임 종료");
        }
        if (player.isBall()) {
            System.out.print(player.result().ballCount() + "볼");
        }
        if (player.isStrike()) {
            System.out.print(" " + player.result().strikeCount() + "스트라이크");
        }
        if (player.result().isNothing()) {
            System.out.println("낫싱!");
        }
        System.out.println();
    }
}
