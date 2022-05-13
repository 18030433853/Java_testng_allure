
public class ResourceTest {

/**
 *          Class.getResource()的资源获取如果以 / 开头，则从根路径开始搜索资源。
            Class.getResource()的资源获取如果不以 / 开头，则从当前类所在的路径开始搜索资源。
            ClassLoader.getResource()的资源获取不能以 / 开头，统一从根路径开始搜索资源。
**/
        public static void main(String[] args) {
            // 1、通过Class的getResource方法
            String a1 = ResourceTest.class.getResource("/java/Httpclient.class").getPath();
            String a2 = ResourceTest.class.getResource("Httpclient.class").getPath();
            String a3 = ResourceTest.class.getResource("/api.yaml").getPath();
            String a4 = ResourceTest.class.getResource("../../api.yaml").getPath();


            // 2、通过本类的ClassLoader的getResource方法
            String b1 = ResourceTest.class.getClassLoader().getResource("java/Httpclient.class").getPath();
            String b2 = ResourceTest.class.getClassLoader().getResource("api.yaml").getPath();


            // 3、通过ClassLoader的getSystemResource方法
            String c1 = ClassLoader.getSystemClassLoader().getResource("java/Httpclient.class").getPath();
            String c2 = ClassLoader.getSystemClassLoader().getResource("api.yaml").getPath();


            // 4、通过ClassLoader的getSystemResource方法
            String d1 = ClassLoader.getSystemResource("java/Httpclient.class").getPath();
            String d2 = ClassLoader.getSystemResource("api.yaml").getPath();


            // 5、通过Thread方式
            String e1 = Thread.currentThread().getContextClassLoader().getResource("java/Httpclient.class").getPath();
            String e2 = Thread.currentThread().getContextClassLoader().getResource("api.yaml").getPath();

        }



    }


