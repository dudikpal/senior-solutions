package locations;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;

public class LocationWithNameMatcher extends TypeSafeMatcher<Location> {

    private Matcher<String> matcher;

    public LocationWithNameMatcher(Matcher<String> matcher) {
        this.matcher = matcher;
    }

    @Override
    protected boolean matchesSafely(Location location) {
        return matcher.matches(location.getName());
    }

    @Override
    protected void describeMismatchSafely(Location item, Description mismatchDescription) {
        mismatchDescription.appendText(" location with name ")
                .appendValue(item.getName());
    }

    @Override
    public void describeTo(Description description) {
        description
                .appendText(" location with name ")
                .appendDescriptionOf(matcher);
    }

    public static Matcher locationWithName(Matcher<String> matcher) {
        return new LocationWithNameMatcher(matcher);
    }
}
