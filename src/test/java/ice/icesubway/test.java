package ice.icesubway;

import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;

@SpringBootTest
@Transactional
public class test {

    @Test
    public void 파일이름출력() throws Exception {

//        String DATA_DIR = "c:/resources/raw/";
//        File dir = new File(DATA_DIR);
//
//        //    String os = System.getProperty("os.name").toLowerCase();
//        //    String imageRoot = "";
//        //
//        //    if (os.contains("win")) {
//        //        imageRoot = "C:/resources/raw/";
//        //    }
//        //        else if (os.contains("linux")) {
//        //        imageRoot = "/Home/resources/raw/";
//        //    }
//
//        String[] filenames = dir.list();
//        for (String filename : filenames) {
//            System.out.println("filename: " + filename);
//        }
    }
}

