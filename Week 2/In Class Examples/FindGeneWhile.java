public class FindGeneWhile{
    public String findGeneSimple(String dna){
        int startIndex = dna.indexOf("ATG");
        int currIndex = dna.indexOf("TAA", startIndex + 3);
        while (currIndex != -1){
            if ((currIndex - startIndex) % 3 == 0){
                return dna.substring(startIndex, currIndex + 3);
            }
            else{
                currIndex = dna.indexOf("TAA", currIndex + 1);
            }
        }
        return "Not a Gene";
    }

    public void testfindGeneSimple(){
       String dna = "AATGCGTAATTAATCG";
       System.out.println("DNA strand is: " + dna);
       String gene = findGeneSimple(dna);
       System.out.println(gene); 

       dna = "CGATGGTTGATAAGCCTAAGCTATAA";
       System.out.println("DNA strand is: " + dna);
       gene = findGeneSimple(dna);
       System.out.println(gene);

       dna = "CGATGGTTGATAAGCCTAAGCTAA";
       System.out.println("DNA strand is: " + dna);
       gene = findGeneSimple(dna);
       System.out.println(gene);

       dna = "CCATGCGCTTAATGATAGATTAA";
       System.out.println("DNA strand is: " + dna);
       gene = findGeneSimple(dna);
       System.out.println(gene);
    }

    public static void main(String[] args){
        FindGeneWhile obj = new FindGeneWhile();
        obj.testfindGeneSimple();
    }
}