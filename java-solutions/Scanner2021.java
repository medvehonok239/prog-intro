import java.io.*;

public class Scanner2021 implements Closeable  {
    final private BufferedReader in;
    private String nextLine;

    public Scanner2021 (String args, String code) throws IOException {
        this.in = new BufferedReader(new InputStreamReader(new FileInputStream(args), code));
        this.nextLine = this.in.readLine();
    }

    public Scanner2021(InputStream in) throws IOException {
        this.in = new BufferedReader(new InputStreamReader(in));
        this.nextLine = this.in.readLine();
    }


    public boolean hasNextLine() {
        return nextLine != null;
    }

    public boolean isPartOfWord(char c) {
        return Character.isLetter(c) || Character.getType(c) == Character.DASH_PUNCTUATION || c == '\'';
    }

    public String nextLine() throws IOException {
        String line = nextLine;
        nextLine = in.readLine();
        return line;
    }

    public void close() throws IOException {
        in.close();
    }

}