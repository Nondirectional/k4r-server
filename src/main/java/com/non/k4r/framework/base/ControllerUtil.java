package com.non.k4r.framework.base;

import com.mybatisflex.annotation.Column;
import com.mybatisflex.core.constant.SqlOperator;
import com.mybatisflex.core.query.SqlOperators;
import com.mybatisflex.core.util.ClassUtil;
import com.mybatisflex.core.util.StringUtil;
import org.apache.ibatis.util.MapUtil;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ControllerUtil {

    private static final Map<Class<?>, SqlOperators> sqlOperatorsMap = new ConcurrentHashMap<>();

    public static SqlOperators buildOperators(Class<?> entityClass) {
        return new SqlOperators(MapUtil.computeIfAbsent(sqlOperatorsMap, entityClass, aClass -> {
            SqlOperators sqlOperators = new SqlOperators();
            List<Field> allFields = ClassUtil.getAllFields(entityClass);
            allFields.forEach(field -> {
                if (field.getType() == String.class) {
                    Column column = field.getAnnotation(Column.class);
                    if (column != null && column.ignore()) {
                        return;
                    }
                    sqlOperators.set(field.getName(), SqlOperator.LIKE);
                }
            });
            return sqlOperators;
        }));
    }
    
    public static String buildOrderBy(String sortKey, String sortType) {
        return buildOrderBy(sortKey, sortType, "");
    }

    public static String buildOrderBy(String sortKey, String sortType, String defaultOrderBy) {
        if (StringUtil.isBlank(sortKey)) {
            return defaultOrderBy;
        }

        sortKey = sortKey.trim();
        if (StringUtil.isBlank(sortType)) {
            return sortKey;
        }

        sortType = sortType.toLowerCase().trim();
        if (!"asc".equals(sortType) && !"desc".equals(sortType)) {
            throw new IllegalArgumentException("sortType only support asc or desc");
        }

        com.mybatisflex.core.util.SqlUtil.keepOrderBySqlSafely(sortKey);

        return sortKey + " " + sortType;
    }
}
