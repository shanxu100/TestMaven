package weather.api;


import io.reactivex.Observable;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.*;
import weather.bean.WeatherRep;

import java.util.Map;


public interface WeatherReq {

    String weatherDataUrl = "https://free-api.heweather.com/";

    String key = "c3292385f67f4441bb03283c0a7a9f73";


    @GET("{url}")
    Call<WeatherRep> getNowWeather(@Path("url") String url, @QueryMap Map<String, String> maps);

    @GET("{url}")
    Observable<WeatherRep> get(@Path("url") String url, @QueryMap Map<String, String> maps);

    @POST("{url}")
    Observable<ResponseBody> post(
            @Path("url") String url,
            //  @Header("") String authorization,
            @FieldMap Map<String, String> maps);

}
