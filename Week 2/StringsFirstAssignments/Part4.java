import edu.duke.*;

public class Part4 {
    public StorageResource findWebLinks(String link){

        URLResource url = new URLResource(link);
        StorageResource myList = new StorageResource();
        String youtube = "youtube";
        String YouTube = "YouTube";
        for (String s: url.words()){
            int check1 = s.indexOf(youtube);
            int check2 = s.indexOf(YouTube);
            if (check1 != -1 || check2 != -1){
                int firstIndex = s.indexOf("\"");
                int lastIndex = s.indexOf("\"", firstIndex + 1);
                String result = s.substring(firstIndex, lastIndex + 1);
                myList.add(result);
            }
        }
        return myList;
    }

    public void testfindWebLinks(){
        String link = "https://www.dukelearntoprogram.com//course2/data/manylinks.html";    
        StorageResource result = findWebLinks(link);
        for(String item: result.data()){
            System.out.println(item);
        }
    
    }

    public static void main(String[] args){
        Part4 obj = new Part4();
        obj.testfindWebLinks();
    }
}
