package leanMessageDemo;

import com.avos.avoscloud.AVException;
import com.avos.avoscloud.AVOSCloud;
import com.avos.avoscloud.AVObject;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Created by acer on 2017/10/8.
 */
public class LeanMessageTest{

    private static LeanMessageTest leanMessageTest;

    private static Properties properties;

    private static InputStream stream;

    /**
     * 生成对象，初始化方法，获取配置文件中的数据加载 LeanCloud 初始化方法
     */
    static {
        leanMessageTest = new LeanMessageTest();
        init();
        AVOSCloud.initialize(properties.getProperty("AppID").toString(),properties.getProperty("AppKey").toString(),properties.getProperty("MasterKey").toString());
    }

    /**
     * 初始化 properties 对象，steam 对象
     */
    public static void init(){
        try {
            getProperties().load(getStream());
            stream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取配置文件的信息
     * @return
     */
    public static InputStream getStream() {
        if(stream == null){
            stream = leanMessageTest.getClass().getClassLoader().getResourceAsStream("LeanCloudAppInfo.properties");
        }

        return stream;
    }

    /**
     * 初始化 properties 对象
     * @return
     * @throws IOException
     */
    public static Properties getProperties() throws IOException {
        if(properties == null){
            properties = new Properties();
            return properties;
        }
        return properties;
    }

    /**
     * 测试传输数据
     * @throws IOException
     * @throws AVException
     */
    @Test
    public void testPushData() throws IOException, AVException {
        // 参数依次为 AppId、AppKey、MasterKey
        AVObject testObject = new AVObject("Demo1");
        testObject.put("words","demo");
        testObject.save();
    }

    /**
     * 测试信息发送
     * @throws Exception
     */
    @Test
    public void testLeanCloudSendMessage() throws Exception {
        AVOSCloud.requestSMSCode("15819696097");
    }

}
