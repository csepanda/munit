package csepanda.munit.runner.services.simple.utils;

import csepanda.munit.runner.core.TestPlanRecord;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class Helpers {
    public static <T> T findByPredicate(List<T> source, Predicate<T> predicate) {
        return source.stream()
            .filter(predicate)
            .findAny()
            .orElse(null);
    }

    public static List<TestPlanRecord> iterableToArray(Iterable<TestPlanRecord> planRecords) {
        var resultingList = new ArrayList<TestPlanRecord>();

        planRecords.forEach(resultingList::add);
        return resultingList;
    }
}
