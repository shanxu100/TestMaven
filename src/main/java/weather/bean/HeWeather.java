package weather.bean;

public class HeWeather {

    public String state;
    public Basic basic;
    public Update update;
    public Now now;


    public static final class Basic{
        public String cid;
        public String location;
        public String parent_city;
        public String admin_area;
        public String cnty;
        public String lat;
        public String tz;
    }

    public static final class Update{
        public String loc;
        public String utc;
    }

    public static final class Now{
        public String cloud;
        public String cond_code;
        public String cond_txt;
        public String hum;
        public String pcpn;
        public String pres;
        public String tmp;
        public String vis;
        public String wind_deg;
        public String wind_dir;
        public String wind_sc;
        public String wind_spd;
    }
}
