package app.utils;

import java.util.ArrayList;
import java.util.List;

public final class IterableToListConverter {

    private IterableToListConverter(){

    }

    public static <T> List<T> getList(Iterable<T> iterable){
        List<T> resultList = new ArrayList<>();
        iterable.forEach(resultList::add);
        return resultList;
    }
}
