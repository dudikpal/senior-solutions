package microservices.training.musicstore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateInstrumentCommand {

    @NotBlank
    private String brand;

    private InstrumentType type;

    @Positive
    private Integer price;
}
