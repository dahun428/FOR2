package quiz;

import java.util.Scanner;

public class Quiz1 {
	// 배열 이용한 야구 게임
	public static void main(String[] args) {

		Scanner scanner = new Scanner(System.in);

		int[] secret = new int[3];
		secret[0] = (int) (Math.random() * 10 + 1);
		secret[1] = (int) (Math.random() * 10 + 1);
		secret[2] = (int) (Math.random() * 10 + 1);
		int[] input = new int[3];
		int count = 0;

		while (true) {

			int strike = 0;
			int ball = 0;

			for (int i = 0; i < secret.length; i++) {
				System.out.print("숫자를 입력하세요 : ");
				input[i] = scanner.nextInt();
			}
			for (int i = 0; i < secret.length; i++) {
				for (int j = 0; j < input.length; j++) {
					if (secret[i] == input[j]) {
						if (i == j) {
							strike++;
						} else {
							ball++;
						}
					}
				}
			}
			count++;
			System.out.println(strike + "스트라이크, " + ball + "볼");

			if (strike == 3) {
				System.out.println("정답입니다.");
				break;
			}
			if (count == 10) {
				System.out.println("제한 횟수 초과.");
				break;
			}
		}
	}
}
