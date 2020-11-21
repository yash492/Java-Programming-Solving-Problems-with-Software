public class Part1 {
    public int findStopCodon (String dna, int startIndex, String stopCodon) {

        int currentIndex = dna.indexOf(stopCodon, startIndex + 3);
        while (currentIndex != -1) {
            int diff = currentIndex - startIndex;
            if (diff % 3 == 0) {
                return currentIndex;
            } 
            else {
                currentIndex = dna.indexOf(stopCodon, currentIndex + 1);
            }
        }

        return dna.length();
    }

    public void testStopCodon() {
        String dna = "ATGxxxyyyzzzTAA";
        String stopCodon = "TAA";
        int startIndex = dna.indexOf("ATG");
        int stopIndex =  findStopCodon(dna, startIndex, stopCodon);
        System.out.println(stopIndex);

        dna = "ATGxxxyyyzzzaTGA";
        stopCodon = "TGA";
        startIndex = dna.indexOf("ATG");
        stopIndex =  findStopCodon(dna, startIndex, stopCodon);
        System.out.println(stopIndex);
    }

    public String findGene(String dna, int where){
        String startCodon = "ATG";
        int startIndex = dna.indexOf(startCodon, where);
        if (startIndex == -1) {
            return "";
        }
        int taaIndex = findStopCodon(dna, startIndex, "TAA");
        int tagIndex = findStopCodon(dna, startIndex, "TAG");
        int tgaIndex = findStopCodon(dna, startIndex, "TGA");

        int minIndex = Math.min(Math.min(taaIndex, tagIndex), tgaIndex);

        if (minIndex == dna.length()) {
            return "";
        }

        return dna.substring(startIndex, minIndex + 3);
    }

    public void testFindGene(){
        String dna = "ATGxxxyyyzzzTAA";
        String gene =  findGene(dna, 0);
        System.out.println(gene);

        dna = "ATGxxxyyyzzzTGAaabTAAbbcTAG";
        gene =  findGene(dna, 0);
        System.out.println(gene);

    }

    public void printAllGenes(String dna) {
        int startIndex = 0;
        while (true) {
            String currentGene = findGene(dna, startIndex);
            if (currentGene.isEmpty()) {
                break;
            }
            System.out.println(currentGene);
            startIndex = dna.indexOf(currentGene, startIndex) + currentGene.length();
        }
    }



    public static void main(String[] args) {
        Part1 obj = new Part1();
        //obj.testStopCodon();
        //obj.testFindGene();
        String dna = "AATGCTAACTAGCTGACTAAT";
        obj.printAllGenes(dna);
    }
}
