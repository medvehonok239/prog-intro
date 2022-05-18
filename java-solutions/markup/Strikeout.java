package markup;

import java.util.List;

public class Strikeout extends Symbol implements Type {

    public Strikeout(List<Type> list1){
        super(list1);
    }

    @Override
    public void toMarkdown(StringBuilder sb) {
        toMarkdown(sb,"~");
    }

    @Override
    public void toBBCode(StringBuilder sb) {
        toBBCode(sb, "[s]", "[/s]");
    }
}
