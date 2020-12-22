package com.chxlay.common.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

/**
 * @author Alay
 * @date 2020-12-22 13:30
 * @project netty
 */
public class DataUtil {
    /**
     * 判断集合是否有效
     *
     * @param collection 待验证集合
     * @return true表示有效，false表示无效
     */
    public static <E> boolean collEffective(Collection<E> collection) {
        return collection != null && collection.size() > 0;
    }

    /**
     * 数组转List集合
     *
     * @param array
     * @param <T>
     * @return
     */
    public static <T> List<T> asList(T... array) {
        List<T> toList = new ArrayList<>();
        Arrays.stream(array).forEach(arr -> toList.add(arr));
        return toList;
    }
}