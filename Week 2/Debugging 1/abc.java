public class abc{
    public void findAbc(String input) {
        int index = input.indexOf("abc");
        while (true) {
            if (index == -1) {
                break;
            }
            String found = input.substring(index + 1, index+4);
            System.out.println(found);
            index = input.indexOf("abc", index+4);
        }
    }

    public void test() {
        //no code yet
        //findAbc("abcd");
        findAbc("abcdabc");
    }

    public static void main(String[] args) {
        abc obj = new abc();
        obj.test();
    }
    
}