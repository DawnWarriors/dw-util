package dw.common.util.file;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;

/**
 * Created by xins_cyf on 2017/3/28.
 */
public class FileUtil
{
    /**
     * 文件copy
     */
    public static void copy(String oldPath, String newPath)
    {
        try
        {
            int bytesum = 0;
            int byteread = 0;
            File oldfile = new File(oldPath);
            if (oldfile.exists())
            {
                InputStream inStream = new FileInputStream(oldPath);
                FileOutputStream fs = new FileOutputStream(newPath);
                byte[] buffer = new byte[1444];
                int length;
                while ((byteread = inStream.read(buffer)) != -1)
                {
                    bytesum += byteread;
                    System.out.println(bytesum);
                    fs.write(buffer, 0, byteread);
                }
                inStream.close();
            }
        } catch (Exception e)
        {
            System.out.println("error  ");
            e.printStackTrace();
        }
    }

    /**
     * 创建文件夹
     * @param foldPath
     */
    public static void mkDir(String foldPath)
    {
        File file = new File(foldPath);
        if (file.exists() && file.isDirectory())
        {
            return;
        } else
        {
            file.mkdir();
        }
    }
}
