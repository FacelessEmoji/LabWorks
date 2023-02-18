import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FifthLabSecondTry {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Вас приветствует user-friendly консоль.\n" +
                "Вводите слова \"Шифруем\" или \"Дешифруем\" и следуйте дальнейшим указаниям.\n" +
                "Для остановки программы введите \"Стоп\".");
        String message = scanner.nextLine();

        System.out.println("Введите данные для первой итерации: длина ключа и размер колонки");
        int k = Integer.parseInt(scanner.nextLine());
        int s = Integer.parseInt(scanner.nextLine());
        String[][] saver = new String[k][s];
        while (!message.equalsIgnoreCase("Стоп")) {
            if (message.equalsIgnoreCase("Шифруем")) {
                System.out.println("Введите то, что хотите зашифровать:");
                String[] unencrypted_message = scanner.nextLine().split("");
                System.out.println("Размер вашего сообщения:" + unencrypted_message.length);
                System.out.println("Введите последовательность неповторяющихся цифр не содержащую ноль в произвольном порядке" +
                        ", чтобы задать уникальный ключ шифрования:");
                Integer[] key = Stream.of(scanner.nextLine().split("")).mapToInt(Integer::parseInt).boxed().toArray(Integer[]::new);
                System.out.println("Введите желаемый размер колонки в шифре перестановки:");
                int columnSize = Integer.parseInt(scanner.nextLine());
                String[][] unencrypted_matrix = new String[key.length][columnSize];
                String[][] encrypted_matrix = new String[key.length][columnSize];
                String[][] intermediate_matrix = new String[columnSize][key.length];
                int counter = 0;
                int position = 0;
                for (int i = 0; i < columnSize; i++) {
                    counter = 0;
                    while (counter < key.length) {
                        intermediate_matrix[i][counter] = unencrypted_message[position];
                        position++;
                        counter++;
                    }
                }
                for (int i = 0; i < key.length; i++) {
                    for (int j = 0; j < columnSize; j++) {
                        unencrypted_matrix[i][j] = intermediate_matrix[j][i];
                    }
                }

                for (int i = 1; i < key.length + 1; i++) {//по ключу
                    for (int j = 0; j < columnSize; j++) {//по колонкам
                        encrypted_matrix[i - 1][j] = unencrypted_matrix[List.of(key).indexOf(i)][j];//первый столбик кр-мы на такой столбик начальной матрицы,у которого индекс равен i-ой букве ключа
                    }
                }

                for (int i = 0; i < encrypted_matrix.length; i++) {
                    for (int j = 0; j < encrypted_matrix[i].length; j++) {
                        System.out.print(encrypted_matrix[i][j]);
                    }
                }
                System.out.println();
                saver = encrypted_matrix;
            } else if (message.equalsIgnoreCase("Дешифруем")) {
                String[][] encrypted_matrix = saver;
                System.out.println("Введите ваш уникальный ключ шифрования:");
                Integer[] key = Stream.of(scanner.nextLine().split("")).mapToInt(Integer::parseInt).boxed().toArray(Integer[]::new);
                System.out.println("Введите выбранный размер колонки:");
                int columnSize = Integer.parseInt(scanner.nextLine());
                for (int i = 0; i < columnSize; i++) {
                    for (int j = 0; j < key.length; j++) {
                        System.out.print(encrypted_matrix[key[j] - 1][i]);
                    }
                }
            }
            message = scanner.nextLine();
        }
    }
}
