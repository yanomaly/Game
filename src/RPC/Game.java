package RPC;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.*;

public class Game {

    private static int chose;
    private static int user_chose;

    public static void check_entry_data(String[] args){
        Set<String> check = new HashSet(Arrays.asList(args));
        if(args.length < 3 || args.length % 2 == 0 || check.size() != args.length){
            System.out.println("Неверный ввод. Количество строк должно быть нечётным > 1 и строки должны не повторяться");
            System.exit(0);
        }
    }

    public static void computer_choise(String[] args){chose = Math.abs(new SecureRandom().nextInt() % args.length);}

    public static int getChose() {return chose;}

    public static void menu(String[] args) {
        String show = "Commands:\n";
        for (int i = 1; i <= args.length; i++)
            show += i + " - " + args[i - 1] + '\n';
        show += "0 - exit\n? - help";
        System.out.println(show);
        Scanner scaner = new Scanner(System.in);
        String command = scaner.nextLine();
        int flag = 0;
        for (int i = 0; i <= args.length; i++) {
            if(command.compareTo(Integer.toString(i)) == 0)
                flag++;
        }
        if (flag == 0 && command.compareTo("?") != 0){
            System.out.println("Неправильная команда, попробуйте снова!");
            menu(args);
        }
        else {
            if (command.compareTo("0") == 0)
                System.exit(0);
            if (command.compareTo("?") == 0) {
                Help.show_table(args);
                menu(args);
            }
            else {
                user_chose = Integer.parseInt(command);
                --user_chose;
                procces(args);
            }
        }
    }

    public static void procces(String[] args) {
        int count = (args.length - 1) / 2;
        List<Integer> win = new ArrayList<>();
        List<Integer> lose = new ArrayList<>();
        for(int i = user_chose + 1; i < args.length; i++) {
            if (lose.size() == count)
                win.add(i);
            if (lose.size() < count)
                lose.add(i);
        }
        for(int i = 0; i < user_chose; i++){
            if (lose.size() == count)
                win.add(i);
            if (lose.size() < count)
                lose.add(i);
        }
        if(lose.indexOf(chose) >= 0)
            System.out.println("You lose(");
        if(win.indexOf(chose) >= 0)
            System.out.println("You win!");
        if(chose == user_chose)
            System.out.println("Draw");
        System.out.print("Your chose: " + args[user_chose] + "\nComputer chose: " + args[chose] + "\nKey: ");
        KeyGen.show_key();
    }

    public static void main(String[] args) throws NoSuchAlgorithmException, InvalidKeyException{
        Game.check_entry_data(args);
        KeyGen.create_key();
        computer_choise(args);
        HMAC.createHMAC();
        menu(args);
    }
}
