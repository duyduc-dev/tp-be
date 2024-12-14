package com.learn.techplatform.common.utils.ListHelper;

import com.learn.techplatform.common.utils.ListHelper.FnIterface.IFindItem;

import java.util.List;
import java.util.Optional;

public class ListHelperFactory<T> {
    private final List<T> list;

    public ListHelperFactory(List<T> list) {
        this.list = list;
    }

    Optional<T> findItem(IFindItem<T> findItem) {
       for (T t : list) {
           if(findItem.findItem(t)) {
               return Optional.of(t);
           }
       }
       return Optional.empty();
    }

    int findIndex(IFindItem<T> findItem) {
        for (int i = 0; i < list.size(); i++) {
            if(findItem.findItem(list.get(i))) {
                return i;
            }
        }
        return -1;
    }

    boolean deleteItem(IFindItem<T> findItem) {
       return list.removeIf(findItem::findItem);
    }

    public static <T> Optional<T> findItem(List<T> list, IFindItem<T> findItem) {
        ListHelperFactory<T> factory = new ListHelperFactory<>(list);
        return factory.findItem(findItem);
    }

    public static <T> boolean deleteItem(List<T> list, IFindItem<T> findItem) {
        ListHelperFactory<T> factory = new ListHelperFactory<>(list);
        return factory.deleteItem(findItem);
    }

    public static <T> int findIndex(List<T> list, IFindItem<T> findItem) {
        ListHelperFactory<T> factory = new ListHelperFactory<>(list);
        return factory.findIndex(findItem);
    }
}
