package x.logic.force;

//класс для получения обьекта XForceFactory
public class XForceFactory {
    private static XForce xForceFactory;

    public static XForce getInstance(){
        if (xForceFactory == null) {
            synchronized (XForceFactory.class) {
                if (xForceFactory == null){
                    xForceFactory = new XForce();
                }
            }
        }
        return xForceFactory;
    }
}
