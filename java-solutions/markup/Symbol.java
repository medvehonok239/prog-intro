package markup;

import java.util.List;

public abstract class Symbol implements Type {

    List<Type> list;
    public Symbol(List<Type> list1){
        list = list1;
    }

    public void toMarkdown(StringBuilder sb, String str) {
        sb.append(str);
        for (Type types : list) {
            types.toMarkdown(sb);
        }
        sb.append(str);
    }

    public void toBBCode(StringBuilder sb, String str,String str2){
        sb.append(str);
        for (Type type : list) {
            type.toBBCode(sb);
        }
        sb.append(str2);
    }
}
