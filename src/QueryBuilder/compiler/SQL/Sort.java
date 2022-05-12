package QueryBuilder.compiler.SQL;

import java.util.ArrayList;
import java.util.List;

public class Sort {

    private List<String> orderBy = new ArrayList<>();
    private List<String> orderByDesc = new ArrayList<>();


    public void sortAsc(String string){

        String parametar = "";
        int flag = 0;

        for(int i = 0; i < string.length(); i ++){

            if(string.charAt(i) == '"' && flag == 1) {
                flag = 0;
                orderBy.add(parametar);
                parametar = "";
                continue;
            }

            if(flag == 1){
                parametar += String.valueOf(string.charAt(i));

            }


            if(string.charAt(i) == '"')
                flag = 1;


        }


    }
    public void sortAscDesc(String string){

        String parametar = "";
        int flag = 0;

        for(int i = 0; i < string.length(); i ++){

            if(string.charAt(i) == '"' && flag == 1) {
                flag = 0;
                orderByDesc.add(parametar);
                parametar = "";
                continue;
            }

            if(flag == 1){
                parametar += String.valueOf(string.charAt(i));

            }


            if(string.charAt(i) == '"')
                flag = 1;


        }




    }
    public String vratiRez(){
        if(!orderBy.isEmpty()){
            String rez = " order by ";
            if(orderBy.size() > 1){
                for(int i = 0; i < orderBy.size(); i++){

                    rez += orderBy.get(i);
                    if(i == (orderBy.size() - 1))
                        break;
                    rez += ",";
                }
            }
            else
                rez += orderBy.get(0);

            return rez;
        }
        else if(!orderByDesc.isEmpty()){
            String rez = " order by ";
            if(orderByDesc.size() > 1){
                for(int i = 0; i < orderByDesc.size(); i++){

                    rez += orderByDesc.get(i);
                    if(i == (orderByDesc.size() - 1))
                        break;
                    rez += ",";
                }
            }
            else
                rez += orderByDesc.get(0);

            return rez + " Desc";
        }
        return "";
    }

    public List<String> getOrderBy() {
        return orderBy;
    }

    public List<String> getOrderByDesc() {
        return orderByDesc;
    }
}
