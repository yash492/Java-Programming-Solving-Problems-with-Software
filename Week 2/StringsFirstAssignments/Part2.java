public class Part2{
    public String findSimpleGene(String dna, String startCodon, String stopCodon){
        // Start Codon is "ATG"
        // End Codon is "TAA"
        if (dna.equals(dna.toLowerCase())){
            startCodon = startCodon.toLowerCase();
            stopCodon = stopCodon.toLowerCase();
        }

        int startIndex = dna.indexOf(startCodon);
        int endIndex = dna.indexOf(stopCodon, startIndex + 3);
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
        String startCodon = "ATG";
        String stopCodon = "TAA";


        String dna = "ATGTTTGGAAGGTTAGGGA";
        String result = findSimpleGene(dna, startCodon, stopCodon);
        System.out.println(result);

        dna = "ATTATCCTGGAAGGTAA";
        result = findSimpleGene(dna, startCodon, stopCodon);
        System.out.println(result);

        dna = "ATGATTATCCTGGAAGGTAA";
        result = findSimpleGene(dna, startCodon, stopCodon);
        System.out.println(result);

        dna = "TTGATGAGGTAAAAGT";
        result = findSimpleGene(dna, startCodon, stopCodon);
        System.out.println(result);
        
        dna = "ttgatgaggtaaaagt";
        result = findSimpleGene(dna, startCodon, stopCodon);
        System.out.println(result);

        dna = "AAATGCCCTAACTAGATTAAGAAACC";
        result = findSimpleGene(dna, startCodon, stopCodon);
        System.out.println(result);

    }
    public static void main(String[] args){
        Part2 obj = new Part2();
        obj.testSimpleGene();
    }

}