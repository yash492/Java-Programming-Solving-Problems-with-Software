public class Part3 {
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

    public int countGenes(String dna){
        int startIndex = 0;
        int count = 0;
        while (true) {
            String currentGene = findGene(dna, startIndex);
            if (currentGene.isEmpty()) {
                break;
            }
            count = count + 1;
            startIndex = dna.indexOf(currentGene, startIndex) + currentGene.length();
        }
        return count;   
    }

    public void testcountGenes() {
        String dna = "ATGxxxyyyzzzTAA";
        int count =  countGenes(dna);
        System.out.println(count);

        dna = "ATGTAAGATGCCCTAGT";
        count =  countGenes(dna);
        System.out.println(count);
    }

    public static void main(String[] args) {
        Part3 obj = new Part3();
        obj.testcountGenes();
    }
}
