import org.apache.commons.lang3.StringUtils;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class SixthLab {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String[] second_key_symbols = {"a", "c", "o", "x", "e"};
        System.out.println("Вас приветствует user-friendly консоль.\n" +
                "Вводите слова \"Шифруем\" или \"Дешифруем\" и следуйте дальнейшим указаниям.\n" +
                "Для остановки программы введите \"Стоп\".");
        String message = scanner.nextLine();
        String[][] saver = new String[0][0];
        while (!message.equalsIgnoreCase("Стоп")) {
            if (message.equalsIgnoreCase("Шифруем")) {
                System.out.println("Введите то, что хотите зашифровать:");
                String unencryptedMessageString = scanner.nextLine();
                System.out.println("Введите последовательность неповторяющихся цифр не содержащую ноль в произвольном порядке и слитно" +
                        ", чтобы задать уникальный ключ шифрования:");
                Integer[] key = Stream.of(scanner.nextLine().split("")).mapToInt(Integer::parseInt).boxed().toArray(Integer[]::new);

                //2 ключ + расчеты
                int repeatFrequency = Integer.parseInt(scanner.nextLine());
                Integer[] repeatingBlocksSizes = Stream.of(scanner.nextLine().split("")).mapToInt(Integer::parseInt).boxed().toArray(Integer[]::new);
                List<Integer> intermediate_array = new ArrayList<>();
                List<Integer> sub_intermediate_array = new ArrayList<>();
                for (int i = 0; i < unencryptedMessageString.length() / repeatFrequency; i++) {
                    Collections.addAll(intermediate_array,repeatingBlocksSizes);
                }
                sub_intermediate_array = intermediate_array.subList(0,unencryptedMessageString.length()/repeatFrequency);
                //
                List<String>unencryptedMessageArray = Stream.of(unencryptedMessageString.split("")).collect(Collectors.toList());
                for (int i = 1; i < sub_intermediate_array.size() + 1; i++) {

                }
                unencryptedMessageArray.forEach(System.out::print);

                int realMessageSize = ((int) Math.ceil((float)unencryptedMessageString.length()/ key.length))* key.length;
                int spaceCount = realMessageSize - unencryptedMessageString.length();
                int columnSize = realMessageSize/ key.length;
                unencryptedMessageString += StringUtils.repeat(" ",spaceCount);
                String[] unencrypted_message = unencryptedMessageString.split("");



               /* String[][] unencrypted_matrix = new String[key.length][columnSize];
                String[][] encrypted_matrix = new String[key.length][columnSize];
                String[][] intermediate_matrix = new String[columnSize][key.length];
                // делим на колонки
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

                for (int i = 0; i < unencrypted_matrix.length; i++) {
                    for (int j = 0; j < unencrypted_matrix[i].length; j++) {
                        System.out.print(unencrypted_matrix[i][j] + "\t");
                    }
                    System.out.println();
                }

                //

                for (int i = 1; i < key.length + 1; i++) {//по ключу
                    for (int j = 0; j < columnSize; j++) {//по колонкам
                        encrypted_matrix[i - 1][j] = unencrypted_matrix[List.of(key).indexOf(i)][j];//i-1 (0) столбик кр-мы на такой столбик начальной матрицы,у которого индекс равен i-ой букве ключа
                    }
                }

                for (int i = 0; i < encrypted_matrix.length; i++) {
                    for (int j = 0; j < encrypted_matrix[i].length; j++) {
                        System.out.print(encrypted_matrix[i][j]);
                    }
                }
                System.out.println();
                saver = encrypted_matrix;*/
            } else if (message.equalsIgnoreCase("Дешифруем")) {
                String[][] encrypted_matrix = saver;
                System.out.println("Введите ваш уникальный ключ шифрования:");
                Integer[] key = Stream.of(scanner.nextLine().split("")).mapToInt(Integer::parseInt).boxed().toArray(Integer[]::new);
                int columnSize = encrypted_matrix[1].length;
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
