import org.csource.common.NameValuePair;
import org.csource.fastdfs.*;

import java.net.URLDecoder;

/**
 * Created by apple on 2019/5/8.
 */
public class FastDFSClient {

    private static TrackerClient trackerClient = null;
    private static TrackerServer trackerServer = null;
    private static StorageServer storageServer = null;
    private static StorageClient1 client = null;
    // fdfsclient的配置文件的路径
    private static String CONF_NAME = "/fast_dfs.conf";

    static {
        try {
            // 配置文件必须指定全路径
            String confName = FastDFSClient.class.getResource(CONF_NAME).getPath();
            // 配置文件全路径中如果有中文，需要进行utf8转码
            confName = URLDecoder.decode(confName, "utf8");

            ClientGlobal.init(confName);
            trackerClient = new TrackerClient();
            trackerServer = trackerClient.getConnection();
            storageServer = null;
            client = new StorageClient1(trackerServer, storageServer);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    /**
     * 上传文件方法
     * <p>
     * Title: uploadFile
     * </p>
     * <p>
     * Description:
     * </p>
     *
     * @param fileName
     *            文件全路径
     * @param extName
     *            文件扩展名，不包含（.）
     * @param metas
     *            文件扩展信息
     * @return
     * @throws Exception
     */
    public static String uploadFile(String fileName, String extName, NameValuePair[] metas) throws Exception {
        String result = client.upload_file1(fileName, extName, metas);
        return result;
    }

    public static void main(String[] args) {
        try {
            String uploadFile = uploadFile("C:/Users/hp/Desktop/mieba.jpg", "jpg", null);
            System.out.println("upload:"+uploadFile);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("upload file to FastDFS failed.");
        }
    }

}
