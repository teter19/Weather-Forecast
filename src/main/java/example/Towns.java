package example;

import java.util.Arrays;
import java.util.List;

public class Towns {

        //0 Bialystok,1 Bydgoszcz,2 Krakow,3 Gdansk,4 Katowice,5 Kielce,6 Lodz,7 Lublin,8 Olsztyn,9 Poznan,10 Rzeszow,11 Szczecin,12 Warszawa,13 Wroclaw,14 Zielona Gora,
        public List<String> towns = Arrays.asList("#weather_map_1197",
                "#weather_map_3696",
                "#weather_map_4970",
                "#weather_map_8048",
                "#weather_map_13095",
                "#weather_map_13378",
                "#weather_map_19059",
                "#weather_map_19393",
                "#weather_map_24210",
                "#weather_map_27457",
                "#weather_map_30389",
                "#weather_map_34668",
                "#weather_map_36917",
                "#weather_map_39240",
                "#weather_map_41517");

        //typ zachmurzenia, temperatura, miasto
        public List <String> data = Arrays.asList(".weather-map-container-list-item-ico",
                ".weather-map-container-list-item-temp",
                ".weather-map-container-list-item-name");

        //wspolrzedne miast, po kolei tak jak w powyzszej kolejnosci  615"                                              795
        public List <String> coordiatesX = Arrays.asList("840","400","615","470","440","660","560","850","650","305","795","70","710","300","155");
        public List <String> coordiatesY = Arrays.asList("310","325","840","140","740","700","545","640","210","440","820","245","425","650","500");
        //                                                              830                                             850
}
