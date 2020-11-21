import java.io.File;
import edu.duke.*;

public class ImageSaver {
    public void doSave() {
        DirectoryResource dr = new DirectoryResource();
        for (File f: dr.selectedFiles()) {
            ImageResource ir = new ImageResource(f);
            String fname = ir.getFileName();
            String newName = "copy-" + fname;
            ir.setFileName(newName);
            ir.draw();
            ir.save();
        }
    }

}
