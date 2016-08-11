import com.ink.base.utils.ZipUtil;

import java.io.File;

/**
 * 测试解压缩小工具
 * Created by yangchen on 16/8/1.
 */
public class TestZipUtil {

    public static void main(String[] args) {
        try {
            File folder = new File("/Users/yangchen/Downloads/excel");
            if(folder.isDirectory()){
                File[] files = folder.listFiles();
                for(File file : files){
                    ZipUtil.zip(file.getAbsolutePath(), "/Users/yangchen/Downloads/ziped", file.getName() + ".zip");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
