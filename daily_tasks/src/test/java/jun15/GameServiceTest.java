package jun15;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class GameServiceTest {

    private GameRepository gameRepository;
    private GameService gameService;

    @BeforeEach
    void setUp() {
        gameRepository = new GameRepository();
        gameRepository.addGameFromFile("src/main/resources/jun15/results.csv");
        gameService = new GameService(gameRepository);

    }

    @Test
    void test_Biggest_Goal_Difference() {
        Game result = gameService.biggestGoalDifference();
        int difference = Math.abs(result.getFirstCountryScore() - result.getSecondCountryScore());
        assertEquals(3, difference);
    }

    @ParameterizedTest
    @MethodSource("sumOfGoals")
    void test_Sum_Of_Goals_By_Country(String country, int goals) {
        assertEquals(goals, gameService.sumGoalOfCountry(country));
    }

    static Stream<Arguments> sumOfGoals() {
        return Stream.of(
                Arguments.arguments("Switzerland", 1),
                Arguments.arguments("Wales", 2)
        );
    }

    @TestFactory
    Stream<DynamicTest> sumGoalsByCountry() {
        return Stream.of(new Object[][]{new Object[]{"Wales", 2}, new Object[]{"Switzerland", 1}})
                .map(item ->
                        DynamicTest.dynamicTest(
                                item[0] + " have " + item[1] + " goals.",
                                () -> assertEquals(item[1],
                                        gameService.sumGoalOfCountry(item[0].toString()))
                        ));
    }

    @TestFactory
    Stream<DynamicTest> mostGoalsByCountry() {
        return Stream.of("Italy")
                .map(country ->
                        DynamicTest.dynamicTest(
                                "A legtöbb gólt rűgta: " + country,
                                () -> assertEquals(country,
                                        gameService.mostGoalsCountry())
                        ));
    }

}