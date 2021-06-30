package dailytasks.bikessharing;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class BikeServiceTest {

    @Mock
    BikeController bikeController;

    @InjectMocks
    BikeService bikeService;

    @Test
    void allShare() {
        when(bikeController.allShare())
                .thenReturn(List.of(
                        new Share("bikeIdValue", "userIdValue", LocalDateTime.of(2111, 1, 1, 1, 1, 1), 11.3)
                ));

        assertThat(bikeController.allShare())
                .hasSize(1)
                .extracting("bikeId")
                .containsExactly("bikeId");
    }
}