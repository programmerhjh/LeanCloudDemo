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
public class LeanMessageTest {

    private static LeanMessageTest leanMessageTest = new LeanMessageTest();
    private static Properties properties;
    private static InputStream stream;

    @Test
    public void testConnect() throws IOException, AVException {
        properties = new Properties();
        stream = leanMessageTest.getClass().getClassLoader().getResourceAsStream("LeanCloudAppInfo.properties");
        properties.load(stream);
        // 参数依次为 AppId、AppKey、MasterKey
        AVOSCloud.initialize(properties.getProperty("AppID").toString(),properties.getProperty("AppKey").toString(),properties.getProperty("MasterKey").toString());
        stream.close();
        AVObject testObject = new AVObject("Demo1");
        testObject.put("words","demo");
        testObject.save();
    }

}
