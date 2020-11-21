public class FindGeneSimpleAndTest {
    public String findGeneSimple(String dna){
        // Start codon is 'ATG'
        // End codon is 'TAA'
        String result = "";
        int startIndex = dna.indexOf("ATG");
        if (startIndex == -1){
            return "There is no 'ATG'";
        }
        int endIndex = dna.indexOf("TAA", startIndex + 3);
        if (endIndex == -1){
            return "There is no 'TAA'";
        }
        result = dna.substring(startIndex, endIndex + 3);
        return result;
    }

    public static void main(String args[]){
        FindGeneSimpleAndTest genesimple = new FindGeneSimpleAndTest();
        //"AATGCGTTAATATGG"
        String dna = "AATGCGTT";
        System.out.println(dna);
        String gene = genesimple.findGeneSimple(dna);
        System.out.println(gene);
    }
}
