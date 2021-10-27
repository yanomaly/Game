package RPC;

import com.bethecoder.ascii_table.ASCIITable;

import java.util.ArrayList;
import java.util.List;

public class Help {

    private static List<Integer> win = new ArrayList<>();
    private static List<Integer> lose = new ArrayList<>();

    public static void show_table(String[] args){
        String [] header = new String[args.length + 1];
        header[0] = " ";
        for (int i = 0; i < args.length; i++)
            header[i+1] = args[i];
        String [][] data = new String[args.length][args.length + 1];
        for (int i = 0; i < args.length; i++)
            data[i][0] = args[i];
        for (int i = 0; i < args.length; i++) {
            w_or_l(i, args);
            for (int j = 1; j < args.length + 1; j++) {
                if(lose.indexOf(j - 1) >= 0)
                    data[i][j] = "L";
                if(win.indexOf(j - 1) >= 0)
                    data[i][j] = "W";
                if(i == j - 1)
                    data[i][j] = "D";
            }
            win = new ArrayList<>();
            lose = new ArrayList<>();
        }
        System.out.println("Help: ");
        ASCIITable.getInstance().printTable(header, data);
        System.out.println("Legend: D - draw, W - win, L - lose");
    }

    public static void w_or_l(int chose, String[] args){
        int count = (args.length - 1) / 2;
        for(int i = chose + 1; i < args.length; i++) {
            if (lose.size() == count)
                win.add(i);
            if (lose.size() < count)
                lose.add(i);
        }
        for(int i = 0; i < chose; i++){
            if (lose.size() == count)
                win.add(i);
            if (lose.size() < count)
                lose.add(i);
        }
    }
}
