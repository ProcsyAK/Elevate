import java.util.*;

class Question {
    private String questionText;
    private List<String> options;
    private int correctOption; 

    public Question(String questionText, List<String> options, int correctOption) {
        this.questionText = questionText;
        this.options = options;
        this.correctOption = correctOption;
    }

    public void displayQuestion() {
        System.out.println(questionText);
        for (int i = 0; i < options.size(); i++) {
            System.out.println((i + 1) + ". " + options.get(i));
        }
    }

    public boolean checkAnswer(int userAnswer) {
        return userAnswer == correctOption;
    }
}

public class QuizApp {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);


        List<Question> questions = new ArrayList<>();
        questions.add(new Question(
                "What is the capital of France?",
                Arrays.asList("Berlin", "Madrid", "Paris", "Rome"),
                3
        ));
        questions.add(new Question(
                "Which programming language runs in a web browser?",
                Arrays.asList("C", "Python", "JavaScript", "Java"),
                3
        ));
        questions.add(new Question(
                "Who developed Java?",
                Arrays.asList("Microsoft", "Sun Microsystems", "Apple", "Google"),
                2
        ));

        int score = 0;

        System.out.println("===== Welcome to the Quiz App =====");
        for (int i = 0; i < questions.size(); i++) {
            System.out.println("\nQuestion " + (i + 1) + ":");
            questions.get(i).displayQuestion();

            System.out.print("Your Answer (1-" + questions.get(i).options.size() + "): ");
            int answer;
            try {
                answer = sc.nextInt();
            } catch (Exception e) {
                System.out.println("Invalid input! Skipping question.");
                sc.nextLine();
                continue;
            }

            if (questions.get(i).checkAnswer(answer)) {
                System.out.println("Correct!");
                score++;
            } else {
                System.out.println("\ Wrong!");
            }
        }

        System.out.println("\n===== Quiz Finished! =====");
        System.out.println("Your Score: " + score + " / " + questions.size());

        sc.close();
    }
}
