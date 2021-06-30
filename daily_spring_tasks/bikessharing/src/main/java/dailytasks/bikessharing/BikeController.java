package dailytasks.bikessharing;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Tag(name = "operations of bikes")
public class BikeController {

    private BikeService bikeService;

    public BikeController(BikeService bikeService) {
        this.bikeService = bikeService;
    }

    @GetMapping("history")
    @Operation(summary = "get all datas")
    @ApiResponse(responseCode = "201", description = "datas getted")
    public List<Share> allShare() {
        return bikeService.allShare();
    }

    @GetMapping("/")
    public String root() {
        return """
                <a href="/history">historyk</a><br>
                <a href="/users">users</a>""";
    }

    @GetMapping("users")
    public List<String> userIds() {
        return bikeService.userIds();
    }
}
