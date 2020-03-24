package homework;

public class QuizDemo2 {
	public static void main(String[] args) {

		String[] Quiz = { "O", "O", "X", "X", "O", "X", "X", "O", "O", "O" };
		int score = 0;
		int sum = 0;

		for (int i = 0; i < Quiz.length; i++) {
			if (Quiz[i].equals("O")) {
				score++;
			} else {
				score = 0;
			}
			sum += score;
		}
		System.out.println(sum);
	}
}
