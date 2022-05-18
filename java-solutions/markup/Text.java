package markup;

public class Text implements Type {
    String str;

    public Text(String sb){
        str = sb;
    }

    @Override
    public void toMarkdown(StringBuilder sb) {
        sb.append(str);
    }

    @Override
    public void toBBCode(StringBuilder sb) {
        sb.append(str);
    }
}
