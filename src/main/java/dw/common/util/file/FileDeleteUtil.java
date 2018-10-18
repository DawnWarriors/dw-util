package dw.common.util.file;

import dw.common.util.str.StrUtil;

import java.io.File;
import java.io.IOException;

public class FileDeleteUtil {
    /**
     * 根据fileIds删除文件
     *
     * @param rootPath 附件根目录
     * @param fileIds
     * @throws IOException
     */
    public static void delFile(String rootPath, String fileIds) throws IOException {
        //文件内容为空时不作处理，避免删掉整个文件夹
        if (StrUtil.isStrTrimNull(fileIds)) {
            return;
        }
        String fileIdAry[] = fileIds.split(",");
        for (String fileId : fileIdAry) {
            String fileName = fileId.replaceAll("_", "/");
            deleteFile(rootPath, fileName);
            //处理缩略图
            int sepIndex = fileName.lastIndexOf("/");
            String thumbFileName = fileName.substring(0, sepIndex) + "/thumb" + fileName.substring(sepIndex);
            deleteFile(rootPath, thumbFileName);
        }
    }

    /**
     * 删除文件
     *
     * @param rootPath
     * @param fileName
     */
    private static void deleteFile(String rootPath, String fileName) {
        File file = new File(rootPath, fileName);
        if (file.exists()) {
            file.delete();
        }
    }

}
