package x.data.ucf;

// Класс для получения обьекта XUcfCoder
public class XUcfCoderFactory {
    private static XUcfCoder xUcfCoder;

    public static XUcfCoder getInstance(){
        if (xUcfCoder == null) {
            synchronized (XUcfCoderFactory.class) {
                if (xUcfCoder == null){
                    xUcfCoder = new XUcfCoder();
                }
            }
        }
        return xUcfCoder;
    }
}
