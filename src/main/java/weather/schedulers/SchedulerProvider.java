package weather.schedulers;


import io.reactivex.ObservableTransformer;
import io.reactivex.Scheduler;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Zaifeng on 2018/2/28.
 * 线程切换
 */
public class SchedulerProvider implements BaseSchedulerProvider {


    private static SchedulerProvider INSTANCE;

    // Prevent direct instantiation.
    private SchedulerProvider() {
    }

    public static synchronized SchedulerProvider getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new SchedulerProvider();
        }
        return INSTANCE;
    }

    @Override
    public Scheduler computation() {
        return Schedulers.computation();
    }

    @Override

    public Scheduler io() {
        return Schedulers.io();
    }

    @Override
    public Scheduler ui() {
//        return AndroidSchedulers.mainThread();
        return Schedulers.io();
    }


    @Override
    public <T> ObservableTransformer<T, T> applySchedulers() {

        //TODO lambda表达式
        return observable -> observable.subscribeOn(io())
                .observeOn(ui());
    }
}
