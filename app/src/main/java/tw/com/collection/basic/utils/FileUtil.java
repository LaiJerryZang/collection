package tw.com.collection.basic.utils;

import android.util.Log;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;

public class FileUtil {
    /**檔案Copy備份
     *  exam : new File(Environment.getExternalStorageDirectory().toString() + File.separator + "DCIM" );
     * @param file 來源檔案
     * @param backup 目的檔案
     */
    public static void fileCopy(File file, File backup) throws IOException {
        //TODO Auto-generated method stub
        FileChannel inChannel = new FileInputStream(file).getChannel();
        FileChannel outChannel = new FileOutputStream(backup).getChannel();
        try {
            inChannel.transferTo(0, inChannel.size(), outChannel);
        } catch (IOException e) {
            //TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            if (inChannel != null) {
                inChannel.close();
            }
            if (outChannel != null) {
                outChannel.close();
            }
        }
    }
}
