package QueryBuilder.compiler.SQL;

import java.util.ArrayList;
import java.util.List;

public class MergeTable {
    private String join;
    private List<String> onParametri = new ArrayList<>();

    public void napraviJoin(String string){
            String UIAvg = string;
            int flag = 0;
            String parametar = "";



            for(int i = 0; i < UIAvg.length(); i ++){

                if(UIAvg.charAt(i) == '"' && flag == 1) {
                    flag = 0;
                    join = parametar;
                    parametar = "";
                    continue;
                }

                if(flag == 1){
                    parametar += String.valueOf(UIAvg.charAt(i));

                }


                if(UIAvg.charAt(i) == '"')
                    flag = 1;
            }


    }
    public void napraviJoinParametre(String string){

        String UIAvg = string;
        int flag = 0;
        String parametar = "";



        for(int i = 0; i < UIAvg.length(); i ++){

            if(UIAvg.charAt(i) == '"' && flag == 1) {
                flag = 0;
                System.out.println(parametar + "DODAVANJE U LISTU");
                onParametri.add(parametar);
                parametar = "";
                continue;
            }

            if(flag == 1){
                parametar += String.valueOf(UIAvg.charAt(i));

            }


            if(UIAvg.charAt(i) == '"')
                flag = 1;
        }




    }

    public String vratiJoin(){



        if(!onParametri.isEmpty()) {
            String parametar1 = onParametri.get(0);
            String parametar2 = onParametri.get(2);

            String splitParametar1[] = parametar1.split("[.]");
            String splitParametar2[] = parametar2.split("[.]");


            if (!splitParametar1[0].equals(join)) {
                return " join " + join + " on (" + parametar1 + onParametri.get(1) + parametar2 + ") ";
            } else if (!splitParametar2[0].equals(join)) {
                return " join " + join + " on (" + parametar2 + onParametri.get(1) + parametar1 + ") ";
            }

        }

       return "";
    }


}
