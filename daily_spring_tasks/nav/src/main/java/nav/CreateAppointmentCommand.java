package nav;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class CreateAppointmentCommand {

    @IsValidTaxNumber
    private String taxNumber;

    //@IsValidInterval
    private Interval interval;

    @IsValidType
    private String typeCode;
}
