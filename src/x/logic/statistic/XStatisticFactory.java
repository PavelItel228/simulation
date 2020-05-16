package x.logic.statistic;

//класс для получения обьекта XStatistic
public class XStatisticFactory {
    private static XStatistic xStatistic;

    public static XStatistic getInstance(){
        if (xStatistic == null) {
            synchronized (XStatisticFactory.class) {
                if (xStatistic == null){
                    xStatistic = new XStatistic();
                }
            }
        }
        return xStatistic;
    }
}
