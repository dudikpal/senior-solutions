package dailytasks.bikessharing;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class BikeControllerTestIT {

    @Autowired
    BikeController bikeController;

    @Test
    void userIds() {
        String userId = bikeController.userIds().get(1);
        assertThat(userId).contains("US3a34");
    }
}