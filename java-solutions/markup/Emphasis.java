package markup;

import java.util.List;

public class Emphasis extends Symbol implements Type {

    public Emphasis(List<Type> list1){
        super(list1);
    }

    @Override
    public void toMarkdown(StringBuilder sb) {
        toMarkdown(sb,"*");
    }

    @Override
    public void toBBCode(StringBuilder sb) {
        toBBCode(sb, "[i]","[/i]");
    }
}
