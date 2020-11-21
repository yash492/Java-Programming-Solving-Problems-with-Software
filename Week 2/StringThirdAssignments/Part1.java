import edu.duke.*;

public class Part1 {
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
        dna = dna.toUpperCase();
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

    public StorageResource getAllGenes(String dna) {
        StorageResource geneList = new StorageResource();
        int startIndex = 0;
        while (true) {
            String currentGene = findGene(dna, startIndex);
            if (currentGene.isEmpty()) {
                break;
            }
            geneList.add(currentGene);
            startIndex = dna.indexOf(currentGene, startIndex) + currentGene.length();
        }
        return geneList;
    }

    public void printAllGenes(String dna) {
        StorageResource genes = getAllGenes(dna);
        for (String gene: genes.data()) {
            System.out.println(gene);
        }
    }

    public double cgRatio(String dna) {
        dna = dna.toUpperCase();
        int cIndex = 0;
        int countC = 0;
        while (true) {
            int currentIndexC = dna.indexOf("C", cIndex);
            if (currentIndexC == -1) {
                break;
            }
            cIndex = currentIndexC + 1;
            countC = countC + 1;   
        }

        int gIndex = 0;
        int countG = 0;
        while (true) {
            int currentIndexG = dna.indexOf("G", gIndex);
            if (currentIndexG == -1) {
                break;
            }

            gIndex = currentIndexG + 1;
            countG = countG + 1;
        }
        int totalCount = countC + countG;
        double ratio = (double) totalCount / dna.length();
        return ratio;
    }

    public void processGenes(StorageResource sr) {
        int countLength = 0;
        int countcgRatio = 0;
        int countcgRatioGene = 0;
        StorageResource genes = null;
        int maxLength = 0;
        int countGene = 0;
        int countCTG = 0;

        for(String str: sr.data()) {
            if (str.length() > 9) {
                //System.out.println(str);
                countLength = countLength + 1;
            }

            if (cgRatio(str) > 0.35) {
                //System.out.println(str);
                countcgRatio = countcgRatio + 1;
            }

            countCTG = countCTG(str);


            genes = getAllGenes(str);
            for(String gene: genes.data()) {
                if (gene.length() > maxLength) {
                    maxLength = gene.length();
                }

                if (gene.length() > 60) {
                    countGene = countGene + 1;
                }

                if (cgRatio(gene) > 0.35) {
                    countcgRatioGene = countcgRatioGene + 1;
                }
            }
        }
        
        System.out.println("File");
        // Prints the length of the longest gene in sr
        System.out.println("The length of the longest gene in sr: " + maxLength);    
        // Prints the number of strings in sr whose C-G-ratio is higher than 0.35
        System.out.println("The number of strings in sr whose C-G-ratio is higher than 0.35: " + countcgRatio);
        // Prints the number of Gene in sr whose C-G-ratio is higher than 0.35
        System.out.println("The number of Gene in sr whose C-G-ratio is higher than 0.35: " + countcgRatioGene);  
        // Prints the number of Strings in sr that are longer than 9 characters     
        System.out.println("The number of Strings in sr that are longer than 9 characters: " + countLength);
        // Prints the number of genes
        System.out.println("The number of Genes: " + genes.size());
        // Prints the number of genes longer than 60
        System.out.println("The number of Genes longer than 60: " + countGene);
        // Prints the number of time codon 'CTG' appears in dna strand
        System.out.println("The number of time codon 'CTG' appears in dna strand: " + countCTG);
        System.out.println();
  
    }

    public int countCTG(String dna) {
        dna = dna.toUpperCase();
        int count = 0;
        int startpos = 0;
        while (true) {
            int currentIndex = dna.indexOf("CTG", startpos);
            if (currentIndex == -1) {
                break;
            }
            startpos = currentIndex + 3;
            count = count + 1;
        }
        return count;
    }

    public void testprocessGenes() {
        FileResource fr1 = new FileResource("brca1line.fa");
        String dna1 = fr1.asString();
        FileResource fr2 = new FileResource("mansmall.fa");
        String dna2 = fr2.asString();
        URLResource link = new URLResource("https://users.cs.duke.edu/~rodger/GRch38dnapart.fa");
        String dna3 = link.asString();

        StorageResource list1 = new StorageResource();
        StorageResource list2 = new StorageResource();
        StorageResource list3 = new StorageResource();


        list1.add(dna1);
        list2.add(dna2);
        list3.add(dna3);
        processGenes(list1);
        processGenes(list2);
        processGenes(list3);

    }

    public void testprintAllGenes() {
        String dna = "AATGCGTAATTAATCxxxxxxxxxAATGCGTAATTAATC";
        System.out.println("DNA strand is: " + dna);
        printAllGenes(dna);
            
        
        dna = "CGATGGTTGATAAGCCTAAGCTATAA";
        System.out.println("DNA strand is: " + dna);
        printAllGenes(dna);

        dna = "CGATGGTTGATAAGCCTAAGCTAA";
        System.out.println("DNA strand is: " + dna);
        printAllGenes(dna);

        dna = "";
        System.out.println("DNA strand is: " + dna);
        printAllGenes(dna);
        
    }

    public void testcgRatio(){
        String dna = "ATGCCATAG";
        System.out.println("DNA strand is: " + dna);
        double ratio = cgRatio(dna);
        System.out.println(ratio);

        dna = "ATGCCATA";
        System.out.println("DNA strand is: " + dna);
        ratio = cgRatio(dna);
        System.out.println(ratio);
    }

    public static void main(String[] args) {
        Part1 obj = new Part1();
        obj.testprocessGenes();
        //obj.testcgRatio();
    }

}
    

