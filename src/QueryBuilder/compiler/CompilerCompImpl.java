package QueryBuilder.compiler;

import QueryBuilder.compiler.SQL.*;

public class CompilerCompImpl implements CompilerComp {

    private Select select ;
    private Agregation agregation ;
    private Sort sort;
    private Filtering filter;
    private MergeTable mergeTable;
    private Having having;

    @Override
    public String compile(String query) {

        select = new Select();
        agregation = new Agregation(select);
        sort = new Sort();
        filter = new Filtering();
        mergeTable = new MergeTable();
        having=new Having();

        String split [] = query.split("[.]");


        String generatedCode = "";

        for(int i = 0 ; i < split.length; i++){

            if(split[i].contains("Query")){

                select.napraviFrom(split[i]);
            }
        }

        for(int i = 0 ; i < split.length; i++){

            if(split[i].contains("Select")){

                select.napraviSelect(split[i]);
            }
        }

        for(int i = 0 ; i < split.length; i++){

            if(split[i].contains("Avg")){
                String tip = "Avg";

                agregation.napraviAgr(split[i], tip);
            }
        }
        for(int i = 0 ; i < split.length; i++){

            if(split[i].contains("Max")){
                String tip = "Max";

                agregation.napraviAgr(split[i], tip);
            }
        }
        for(int i = 0 ; i < split.length; i++){

            if(split[i].contains("Min")){
                String tip = "Min";

                agregation.napraviAgr(split[i], tip);
            }
        }
        for(int i = 0 ; i < split.length; i++){

            if(split[i].contains("Count")){
                String tip = "Count";

                agregation.napraviAgr(split[i], tip);
            }
        }


        for(int i = 0 ; i < split.length; i++){

            if(split[i].contains("GroupBy")){

                agregation.groupBy(split[i]);
            }
        }

        for(int i = 0 ; i < split.length; i++){

            if(!split[i].contains("OrderByDesc") && split[i].contains("OrderBy") ){

                sort.sortAsc(split[i]);
            }
            else  if(split[i].contains("OrderByDesc")){

                sort.sortAscDesc(split[i]);
            }
        }

        int flag = 0;

        for(int i = 0 ; i < split.length; i++){

            if(split[i].contains("WhereBetween") ){
                flag = 1;

                filter.napraviWhereBetween(split[i]);
            }
        }
        for(int i = 0 ; i < split.length; i++){

            if(split[i].contains("WhereIn") ){
                flag = 1;

                filter.napraviWhereIn(split[i]);
            }
        }
        for(int i = 0 ; i < split.length; i++){

            if(split[i].contains("WhereEndsWith") ){
                flag = 1;

                filter.napraviStringOperacije(split[i]);
            }
        }
        for(int i = 0 ; i < split.length; i++){

            if(split[i].contains("WhereStartsWith") ){
                flag = 1;

                filter.napraviStringOperacije(split[i]);
            }
        }
        for(int i = 0 ; i < split.length; i++){

            if(split[i].contains("WhereContains") ){
                flag = 1;

                filter.napraviStringOperacije(split[i]);
            }
        }

        for(int i = 0 ; i < split.length; i++){
            if(flag == 1)
                break;
            if(split[i].contains("Where") || split[i].contains("OrWhere") || split[i].contains("AndWhere") ){

                filter.napraviWhereAndOr(split[i]);
            }
        }

        for(int i = 0 ; i < split.length; i++){

            if(split[i].contains("ParametarList") ){

                filter.parametarList(split[i]);
            }
        }

        for(int i = 0 ; i < split.length; i++){

            if(split[i].contains("Join") ){

                mergeTable.napraviJoin(split[i]);
                mergeTable.napraviJoinParametre(split[i + 1] +"."+ split[i + 2] +"."+ split[i + 3]);
            }
        }

        for(int i = 0 ; i < split.length; i++){
            if(split[i].contains("Having") || split[i].contains("AndHaving") || split[i].contains("OrHaving") ){
                having.napraviHaving(split[i]);
            }
        }

        if(query.contains("WhereInQ")){
            String novi[] = query.split("var");



            if(novi[1].contains("WhereInQ")){

                String upitBezWhera = vratiStringBezWhera(novi[2]);

                String rezultat = vratiSaWhereIq(novi[1], upitBezWhera);

                return rezultat;
            }
            else if(novi[2].contains("WhereInQ")){
                System.out.println("Usao u prvi if");
                System.out.println("Bez whera:" + novi[1]);
                String upitBezWhera = vratiStringBezWhera(novi[1]);
                System.out.println("Sa wherom:" + novi[2]);
                String rezultat = vratiSaWhereIq(novi[2], upitBezWhera);
                System.out.println("REZULTAT|" + rezultat);
                return rezultat;
            }
        }



        generatedCode = "SELECT " + agregation.vratiRez() + " FROM " + select.getFrom()+ mergeTable.vratiJoin()+ filter.vratiFilter() +agregation.getGroupByParametri()+having.vratiHaving()+ sort.vratiRez();

        System.out.println("Konacan kod:" + generatedCode);
        System.out.println(generatedCode.length());

        return generatedCode;
    }









    public String vratiStringBezWhera(String query){

        select = new Select();
        agregation = new Agregation(select);
        sort = new Sort();
        filter = new Filtering();
        mergeTable = new MergeTable();
        having=new Having();

        String split [] = query.split("[.]");


        String generatedCode = "";

        for(int i = 0 ; i < split.length; i++){

            if(split[i].contains("Query")){

                select.napraviFrom(split[i]);
            }
        }

        for(int i = 0 ; i < split.length; i++){

            if(split[i].contains("Select")){

                select.napraviSelect(split[i]);
            }
        }

        for(int i = 0 ; i < split.length; i++){

            if(split[i].contains("Avg")){
                String tip = "Avg";

                agregation.napraviAgr(split[i], tip);
            }
        }
        for(int i = 0 ; i < split.length; i++){

            if(split[i].contains("Max")){
                String tip = "Max";

                agregation.napraviAgr(split[i], tip);
            }
        }
        for(int i = 0 ; i < split.length; i++){

            if(split[i].contains("Min")){
                String tip = "Min";

                agregation.napraviAgr(split[i], tip);
            }
        }
        for(int i = 0 ; i < split.length; i++){

            if(split[i].contains("Count")){
                String tip = "Count";

                agregation.napraviAgr(split[i], tip);
            }
        }


        for(int i = 0 ; i < split.length; i++){

            if(split[i].contains("GroupBy")){

                agregation.groupBy(split[i]);
            }
        }

        for(int i = 0 ; i < split.length; i++){

            if(!split[i].contains("OrderByDesc") && split[i].contains("OrderBy") ){

                sort.sortAsc(split[i]);
            }
            else  if(split[i].contains("OrderByDesc")){

                sort.sortAscDesc(split[i]);
            }
        }

        int flag = 0;

        for(int i = 0 ; i < split.length; i++){

            if(split[i].contains("WhereBetween") ){
                flag = 1;

                filter.napraviWhereBetween(split[i]);
            }
        }
        for(int i = 0 ; i < split.length; i++){

            if(split[i].contains("WhereIn") ){
                flag = 1;

                filter.napraviWhereIn(split[i]);
            }
        }
        for(int i = 0 ; i < split.length; i++){

            if(split[i].contains("WhereEndsWith") ){
                flag = 1;

                filter.napraviStringOperacije(split[i]);
            }
        }
        for(int i = 0 ; i < split.length; i++){

            if(split[i].contains("WhereStartsWith") ){
                flag = 1;

                filter.napraviStringOperacije(split[i]);
            }
        }
        for(int i = 0 ; i < split.length; i++){

            if(split[i].contains("WhereContains") ){
                flag = 1;

                filter.napraviStringOperacije(split[i]);
            }
        }

        for(int i = 0 ; i < split.length; i++){
            if(flag == 1)
                break;
            if(split[i].contains("Where") || split[i].contains("OrWhere") || split[i].contains("AndWhere") ){

                filter.napraviWhereAndOr(split[i]);
            }
        }

        for(int i = 0 ; i < split.length; i++){

            if(split[i].contains("ParametarList") ){

                filter.parametarList(split[i]);
            }
        }

        for(int i = 0 ; i < split.length; i++){

            if(split[i].contains("Join") ){

                mergeTable.napraviJoin(split[i]);
                mergeTable.napraviJoinParametre(split[i + 1] +"."+ split[i + 2] +"."+ split[i + 3]);
            }
        }

        for(int i = 0 ; i < split.length; i++){
            if(split[i].contains("Having") || split[i].contains("AndHaving") || split[i].contains("OrHaving") ){

                having.napraviHaving(split[i]);
            }
        }

        if(query.contains("WhereInQ")){

        }



        generatedCode = "SELECT " + agregation.vratiRez() + " FROM " + select.getFrom()+ mergeTable.vratiJoin()+ filter.vratiFilter()  +agregation.getGroupByParametri()+having.vratiHaving()+ sort.vratiRez();

        System.out.println("Konacan kod:" + generatedCode);
        System.out.println(generatedCode.length());

        return generatedCode;
    }
    public String vratiSaWhereIq(String query, String generatedQuery){

        select = new Select();
        agregation = new Agregation(select);
        sort = new Sort();
        filter = new Filtering();
        mergeTable = new MergeTable();
        having=new Having();

        String split [] = query.split("[.]");


        String generatedCode = "";

        for(int i = 0 ; i < split.length; i++){

            if(split[i].contains("Query")){

                select.napraviFrom(split[i]);
            }
        }

        for(int i = 0 ; i < split.length; i++){

            if(split[i].contains("Select")){

                select.napraviSelect(split[i]);
            }
        }

        for(int i = 0 ; i < split.length; i++){

            if(split[i].contains("Avg")){
                String tip = "Avg";

                agregation.napraviAgr(split[i], tip);
            }
        }
        for(int i = 0 ; i < split.length; i++){

            if(split[i].contains("Max")){
                String tip = "Max";

                agregation.napraviAgr(split[i], tip);
            }
        }
        for(int i = 0 ; i < split.length; i++){

            if(split[i].contains("Min")){
                String tip = "Min";

                agregation.napraviAgr(split[i], tip);
            }
        }
        for(int i = 0 ; i < split.length; i++){

            if(split[i].contains("Count")){
                String tip = "Count";

                agregation.napraviAgr(split[i], tip);
            }
        }


        for(int i = 0 ; i < split.length; i++){

            if(split[i].contains("GroupBy")){

                agregation.groupBy(split[i]);
            }
        }

        for(int i = 0 ; i < split.length; i++){

            if(!split[i].contains("OrderByDesc") && split[i].contains("OrderBy") ){

                sort.sortAsc(split[i]);
            }
            else  if(split[i].contains("OrderByDesc")){

                sort.sortAscDesc(split[i]);
            }
        }


        for(int i = 0 ; i < split.length; i++){

            if(split[i].contains("Join") ){

                mergeTable.napraviJoin(split[i]);
                mergeTable.napraviJoinParametre(split[i + 1] +"."+ split[i + 2] +"."+ split[i + 3]);
            }
        }

        for(int i = 0 ; i < split.length; i++){
            if(split[i].contains("Having") || split[i].contains("AndHaving") || split[i].contains("OrHaving") ){

                having.napraviHaving(split[i]);
            }
        }

        String WhereParametar = "";

        for(int i = 0 ; i < split.length; i++){
            if(split[i].contains("WhereInQ")){
                String UIAvg = split[i];
                int flag = 0;
                String parametar = "";

                for(int j = 0; j < UIAvg.length(); j ++){

                    if(UIAvg.charAt(j) == '"' && flag == 1) {
                        flag = 0;
                        WhereParametar = parametar;
                        parametar = "";
                        continue;
                    }

                    if(flag == 1){
                        parametar += String.valueOf(UIAvg.charAt(j));

                    }


                    if(UIAvg.charAt(j) == '"')
                        flag = 1;


                }

            }
        }



        generatedCode = "SELECT " + agregation.vratiRez() + " FROM " + select.getFrom()+ mergeTable.vratiJoin()+" Where "+WhereParametar +" in("+generatedQuery+") "  +agregation.getGroupByParametri()+having.vratiHaving()+ sort.vratiRez();

        System.out.println("Konacan kod:" + generatedCode);
        System.out.println(generatedCode.length());

        return generatedCode;
    }
}
