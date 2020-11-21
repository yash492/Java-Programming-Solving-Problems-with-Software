public class Part2 {
    public int howMany(String stringa, String stringb) {
        int startIndex = 0;
        int currentIndex = 0;
        int count = 0;
        while (true) {
            //It searches for the index
            currentIndex = stringb.indexOf(stringa, startIndex);
            //It updated the index
            startIndex = stringb.indexOf(stringa, startIndex) + stringa.length();
            if (currentIndex == -1) {
                break;
            }
            count = count + 1;
        }
        return count;
    }

    public void testhowMany() {
        String stringa = "GAA";
        String stringb = "ATGAACGAATTGAATC";
        int howmany = howMany(stringa, stringb);
        System.out.println(howmany);

    }

    public static void main(String[] args) {
        Part2 obj = new Part2();
        obj.testhowMany();
    }
}
