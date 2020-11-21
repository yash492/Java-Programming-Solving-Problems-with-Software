public class AllCodons {
    public int findStopCodon(String dnaStr, int startIndex, String stopCodon) {
        int currIndex = dnaStr.indexOf(stopCodon, startIndex + 3);
        while (currIndex != -1) {
            int diff = currIndex - startIndex;
            if (diff % 3 == 0){
                return currIndex;
            }
            else {
                currIndex = dnaStr.indexOf(stopCodon, currIndex + 1);
            }
        }

        //return dnaStr.length();
        return -1;
    }

    public String findGene(String dna, int where) {
        int startIndex = dna.indexOf("ATG", where);
        if (startIndex == -1){
            return "";
        }

        int taaIndex = findStopCodon(dna, startIndex, "TAA");
        int tagIndex = findStopCodon(dna, startIndex, "TAG");
        int tgaIndex = findStopCodon(dna, startIndex, "TGA");

        // int temp = Math.min(taaIndex, tagIndex);
        // int minIndex = Math.min(temp, tgaIndex);

        int minIndex = 0;

        if (taaIndex == -1 || (tagIndex != -1 && tagIndex < taaIndex)) {
            minIndex = tagIndex;
        }

        else{
            minIndex = taaIndex;
        }

        if (minIndex == -1 || (tgaIndex != -1 && tgaIndex < minIndex)) {
            minIndex = tgaIndex;
        }
        
        // if (minIndex == dna.length()) {
        //     return "";
        // }

        if (minIndex == -1) {
            return "";
        }

        return dna.substring(startIndex, minIndex + 3);
    }

    public void printAllGenes(String dna) {
        int startIndex = 0;
        while (true) {
            String currentGene = findGene(dna, startIndex);
            if (currentGene.length() == 0) {
                break;
            }
            System.out.println(currentGene);
            startIndex = dna.indexOf(currentGene, startIndex) + currentGene.length();
        }
    }


    public void testFindStop(){
        //AATGCGTAATTAATCG88899948hhhajfnj
        String dna = "AATGCGTAATTAATCxxxxxxxxxAATGCGTAATTAATC";
        System.out.println("DNA strand is: " + dna);
        printAllGenes(dna);
         
        /*
        dna = "CGATGGTTGATAAGCCTAAGCTATAA";
        System.out.println("DNA strand is: " + dna);
        printAllGenes(dna);

        dna = "CGATGGTTGATAAGCCTAAGCTAA";
        System.out.println("DNA strand is: " + dna);
        printAllGenes(dna);

        dna = "CCATGCGCTTAATGATAGATTAA";
        System.out.println("DNA strand is: " + dna);
        printAllGenes(dna);
        */
    }

    public static void main(String[] args){
        AllCodons obj = new AllCodons();
        obj.testFindStop();
    }

}
