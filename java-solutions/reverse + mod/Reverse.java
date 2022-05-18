
import java.io.IOException;
import java.util.ArrayList;


public class Reverse {
    public static void main(String[] args) throws IOException {
        Scanner2021 in = new Scanner2021(System.in);

        ArrayList<Integer> ints = new ArrayList<>();
        ArrayList<String> lines = new ArrayList<>();

        while (in.hasNextLine()) {
            String str = in.nextLine();
            str += " ";
            StringBuilder num = new StringBuilder("");
            StringBuilder line = new StringBuilder("");
            for (int i = 0; i < str.length(); i++) {
                if (!Character.isWhitespace(str.charAt(i))) {
                    num.append(str.charAt(i));
                } else {
                    if (num.length() != 0) {
                        ints.add(Integer.parseInt(String.valueOf(num)));
                    }
                    num = new StringBuilder("");
                }
            }
            for (int i = ints.size() - 1; i >= 0; i--) {
                line.append(ints.get(i)).append(" ");
            }
            lines.add(String.valueOf(line));
            ints.clear();
        }
        in.close();

        for (int i = lines.size() - 1; i >= 0; i--) {
            System.out.println(lines.get(i));
        }

    }
}
