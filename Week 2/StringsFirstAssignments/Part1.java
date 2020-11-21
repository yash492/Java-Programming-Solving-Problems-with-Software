public class Part1{
    public String findSimpleGene(String dna){
        // Start Codon is "ATG"
        // End Codon is "TAA"
        int startIndex = dna.indexOf("ATG");
        int endIndex = dna.indexOf("TAA", startIndex + 3);
        if (startIndex == -1) {
            return "There is no ATG.";
        }
        if (endIndex == -1) {
            return "There is no TAA.";
        }

        if ((endIndex - startIndex) % 3 == 0){
            String result = dna.substring(startIndex, endIndex + 3);
            return "The gene is " + result;
        }
        return "Error";
    }
    
    public void testSimpleGene(){
        String dna = "ATGTTTGGAAGGTTAGGGA";
        String result = findSimpleGene(dna);
        System.out.println(result);
        dna = "ATTATCCTGGAAGGTAA";
        result = findSimpleGene(dna);
        System.out.println(result);
        dna = "ATGATTATCCTGGAAGGTAA";
        result = findSimpleGene(dna);
        System.out.println(result);
        dna = "TTGATGAGGTAAAAGT";
        result = findSimpleGene(dna);
        System.out.println(result);

    }

    public static void main(String[] args){
        Part1 obj = new Part1();
        obj.testSimpleGene();
    }

}