package gatling.java.common;

import com.openjaw.api.model.PassengerType;
import com.openjaw.api.model.PassengerTypeCount;

import java.util.ArrayList;
import java.util.List;

public class CommonFunc {
    public static List<PassengerTypeCount> parsePassengerCounts(String passengerCounts) {
        String[] a = passengerCounts.split("-");
        List<PassengerTypeCount> r = new ArrayList<>();

        for(int i=0; i<a.length; i++){
            if(a[i] == "0")
                continue;
            PassengerTypeCount p = new PassengerTypeCount();
            p.setCount(Integer.parseInt(a[i]));
            switch (i){
                case 0:
                    p.setPassengerType(PassengerType.ADT);
                    break;
                case 1:
                    p.setPassengerType(PassengerType.CHD);
                    break;
                case 2:
                    p.setPassengerType(PassengerType.INF);
                    break;
                case 3:
                    p.setPassengerType(PassengerType.YTH);
                    break;
            }
            r.add(p);
        }
        return r;
    }
}
