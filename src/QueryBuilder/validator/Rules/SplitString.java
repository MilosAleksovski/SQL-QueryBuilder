package QueryBuilder.validator.Rules;

import java.util.ArrayList;
import java.util.List;

public class SplitString {

    private List<String> splitovanUnutarZagrade = new ArrayList<>();
    private List<String> splitovani = new ArrayList<>();

    public List<String> vratiSplitovan(String query){

        splitovani.clear();

        String string[] = query.split("[.]");

        for(int i = 0 ; i < string.length; i++){
            splitovani.add(string[i]);
        }


        return splitovani;
    }

    public List<String> vratiNaziveFunkcija(String query){
        List<String> listaNaziva = new ArrayList<>();

        String split[] = query.split("[.]");
        int pos = 0;
        for(int j = 0; j < split.length; j++) {
            String string = "";

            for (int i = 0; i < split[j].length(); i++) {
                if (split[j].charAt(i) != ' ') {
                    pos = i;
                    break;
                }
            }
            if(!(split[j].indexOf("(") == -1)) {
                string = split[j].substring(pos, split[j].indexOf("("));
            }
            else continue;

            listaNaziva.add(string);
        }
        return listaNaziva;
    }


    public List<String> vratiParametreUnutarZagrade(String query, String imeFunkije){



        String split[] = query.split("[.]");



        int flag = 0;
        String rezultat = "";
        String parametar = "";
        List<String> listaParametara = new ArrayList<>();

        for(String funkcija : split){


            int pos = 0;

            for(int i = 0; i < funkcija.length(); i++){
                if(funkcija.charAt(i) != ' '){
                    pos = i;
                    break;
                }
            }


            String string = "";

            if(!(funkcija.indexOf("(") == -1)) {
                string = funkcija.substring( pos, funkcija.indexOf("(") );

            }
            else continue;

            if(string.equals(imeFunkije)){

                for(int i = 0; i < funkcija.length(); i ++){

                    if(funkcija.charAt(i) == ')' && flag == 1) {
                        flag = 0;
                        rezultat = parametar;
                        break;
                    }

                    if(flag == 1){
                        parametar += funkcija.charAt(i);
                    }


                    if(funkcija.charAt(i) == '(')
                        flag = 1;


                }


               // if(rezultat.contains(",")) {
                    String splitParametar[] = rezultat.split(",");
                    for (int i = 0; i < splitParametar.length; i++) {
                        listaParametara.add(splitParametar[i]);
                    }

              //  }
                return listaParametara;

            }
        }


        return listaParametara;
    }
}
