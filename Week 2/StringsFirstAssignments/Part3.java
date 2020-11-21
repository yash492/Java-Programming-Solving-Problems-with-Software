public class Part3 {
    public String twoOccurences(String Stringa, String Stringb){
        int firstIndex = Stringa.indexOf(Stringb);
        int Stringb_len = Stringb.length();
        int secondIndex = Stringa.indexOf(Stringb, firstIndex + Stringb_len);
        if (secondIndex != -1){
            return "True";
        }
        return "False";
    }

    public String lastPart(String Stringa, String Stringb){
        int partIndex = Stringb.indexOf(Stringa);
        int len = Stringa.length();
        if (partIndex == -1){
            return Stringb;
        }
        String result = Stringb.substring(partIndex, partIndex + len + 1);
        return result;
    }

    public void testtwoOccurrences(){
        String Stringa = "A story by Abby Long";
        String Stringb = "by";
        String result = twoOccurences(Stringa, Stringb);
        System.out.println(result);

        Stringa = "ctgtatgta";
        Stringb = "atg";
        result = twoOccurences(Stringa, Stringb);
        System.out.println(result);

        Stringa = "banana";
        Stringb = "a";
        result = twoOccurences(Stringa, Stringb);
        System.out.println(result);
    }

    public void testlastPart(){
        String Stringa = "an";
        String Stringb = "banana";
        String result = lastPart(Stringa, Stringb);
        System.out.println(result);

        Stringa = "zoo";
        Stringb = "forest";
        result = lastPart(Stringa, Stringb);
        System.out.println(result);
    }

    public static void main(String[] args){
        Part3 obj = new Part3();
        obj.testtwoOccurrences();
        obj.testlastPart();
    }
}
