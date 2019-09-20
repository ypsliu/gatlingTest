package gatling.java.bookingSeat;

import com.openjaw.api.model.AddSeatAssignment;
import com.openjaw.api.model.SeatAssignmentRequest;
import gatling.java.common.RequestBase;
import gatling.java.common.Tools;

import java.util.List;
import java.util.ArrayList;

public class SeatAssignmentRequestRq extends RequestBase {
    public String getUrl(String bookingId) {
        return "/bookings/"+bookingId+"/flight/seatAssignments";
    }
    public String getBody(String seatMapResponseId,
                          String customer1,
                          String zeroRowNo1,
                          String seatMapId,
                          String zeroSeatNo1,
                          String customer2,
                          String chargeableRowNo2,
                          //String seatMapId,
                          String chargeableSeatNo2){
        SeatAssignmentRequest rq = new SeatAssignmentRequest();
        rq.setSeatMapResponseId(seatMapResponseId);
        List<AddSeatAssignment> seatAssignments = new ArrayList<AddSeatAssignment>();
        AddSeatAssignment seat1 = new AddSeatAssignment();
        AddSeatAssignment seat2 = new AddSeatAssignment();
        seat1.setCustomerId(Integer.parseInt(customer1));
        seat1.setRowNumber(zeroRowNo1);
        seat1.setSeatMapId(seatMapId);
        seat1.setSeatNumber(zeroSeatNo1);
        seatAssignments.add(seat1);
        seat2.setCustomerId(Integer.parseInt(customer2));
        seat2.setSeatMapId(chargeableRowNo2);
        seat2.setSeatMapId(seatMapId);
        seat2.setRowNumber(chargeableSeatNo2);
        seatAssignments.add(seat2);
        rq.setSeatAssignments(seatAssignments);

        return Tools.objectTOString(rq);
    }
}
