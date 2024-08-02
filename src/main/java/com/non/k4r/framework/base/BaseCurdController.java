package com.non.k4r.framework.base;

import com.mybatisflex.core.paginate.Page;
import com.mybatisflex.core.query.QueryWrapper;
import com.mybatisflex.core.query.SqlOperators;
import com.mybatisflex.core.service.IService;
import com.mybatisflex.core.table.TableInfo;
import com.mybatisflex.core.table.TableInfoFactory;
import com.mybatisflex.core.util.StringUtil;
import com.non.k4r.framework.commons.Result;
import com.non.k4r.framework.jsonbody.JsonBody;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class BaseCurdController<S extends IService<M>, M> extends BaseController {

    protected final S service;

    public BaseCurdController(S service) {
        this.service = service;
    }

    /**
     * 添加（保存）数据
     *
     * @param entity 表生成内容配置
     * @return {@code Result.errorCode == 0} 添加成功，否则添加失败
     */
    @PostMapping("save")
    public Result save(@JsonBody M entity) {
        Result result = onSaveOrUpdateBefore(entity, true);
        if (result != null) return result;
        boolean success = service.save(entity);
        onSaveOrUpdateAfter(entity, true);
        TableInfo tableInfo = TableInfoFactory.ofEntityClass(entity.getClass());
        Object[] pkArgs = tableInfo.buildPkSqlArgs(entity);
        return Result.create(success).set("id", pkArgs);
    }

    /**
     * 根据主键删除数据，id 需通过 json 传入，例如：
     * <pre>
     * {
     *   "id":123
     * }
     * <pre/>
     *
     *
     * @param id 主键
     * @return {@code Result.errorCode == 0} 删除成功，否则删除失败
     */
    @PostMapping("remove")
    @Transactional
    public Result remove(@JsonBody(value = "id", required = true) Serializable id) {
        List<Serializable> ids = Collections.singletonList(id);
        Result result = onRemoveBefore(ids);
        if (result != null) return result;
        boolean success = service.removeById(id);
        onRemoveAfter(ids);
        return Result.create(success);
    }


    /**
     * 根据多个主键删数据内容，id 需通过 json 传入，例如：
     * <pre>
     * {
     *   "ids":[123, 234, 222]
     * }
     * <pre/>
     *
     * @param ids 主键
     * @return {@code Result.errorCode == 0} 删除成功，否则删除失败
     */
    @PostMapping("remove-batch")
    @Transactional
    public Result removeBatch(@JsonBody(value = "ids", required = true) Collection<Serializable> ids) {
        if (ids == null || ids.isEmpty()) {
            return Result.fail();
        }
        Result result = onRemoveBefore(ids);
        if (result != null) return result;
        boolean success = service.removeByIds(ids);
        onRemoveAfter(ids);
        return Result.create(success);
    }

    /**
     * 根据主键更新内容
     *
     * @param entity 实体类数据
     * @return {@code Result.errorCode == 0} 更新成功，否则更新失败
     */
    @PostMapping("update")
    public Result update(@JsonBody M entity) {
        Result result = onSaveOrUpdateBefore(entity, false);
        if (result != null) return result;
        boolean success = service.updateById(entity);
        onSaveOrUpdateAfter(entity, false);
        return Result.create(success);
    }

    /**
     * 查询所有所有数据
     *
     * @return 所有数据
     */
    @GetMapping("list")
    public Result list(M entity,  String sortKey, String sortType) {
        QueryWrapper queryWrapper = QueryWrapper.create(entity, buildOperators(entity));
        queryWrapper.orderBy(buildOrderBy(sortKey, sortType, getDefaultOrderBy()));
        return Result.success(service.list(queryWrapper));
    }


    /**
     * 根据表主键查询数据详情。
     *
     * @param id 主键值
     * @return 内容详情
     */
    @GetMapping("detail")
    public Result detail(String id) {
        return Result.success(service.getById(id));
    }


    /**
     * 分页查询数据列表
     *
     * @param entity     查询数据
     * @param sortKey    排序字段
     * @param sortType   排序方式 asc | desc
     * @param pageNumber 当前页码
     * @param pageSize   每页的数据量
     * @return 查询的结果集
     */
    @GetMapping("page")
    public Result page(M entity, String sortKey, String sortType, Long pageNumber, Long pageSize) {
        if (pageNumber == null || pageNumber < 1) {
            pageNumber = 1L;
        }
        if (pageSize == null || pageSize < 1) {
            pageSize = 10L;
        }

        QueryWrapper queryWrapper = QueryWrapper.create(entity, buildOperators(entity));
        queryWrapper.orderBy(buildOrderBy(sortKey, sortType, getDefaultOrderBy()));
        return Result.success(queryPage(new Page<>(pageNumber, pageSize), queryWrapper));
    }


    protected Class<?> getEntityClass() {
        Type type = getClass().getGenericSuperclass();
        if (type instanceof ParameterizedType) {
            return (Class<M>) ((ParameterizedType) type).getActualTypeArguments()[1];
        }

        return null;
    }


    /**
     * 方便子类复写，构建自己的 SqlOperators
     *
     * @param entity 实体类
     * @return SqlOperators
     */
    protected SqlOperators buildOperators(M entity) {
        return entity == null ? SqlOperators.empty() : ControllerUtil.buildOperators(entity.getClass());
    }

    protected String getDefaultOrderBy() {
        return "id desc";
    }

    /**
     * 方便子类复写，构建自己的 orderBy
     *
     * @param sortKey        排序字段
     * @param sortType       排序类型
     * @param defaultOrderBy 默认方式内容
     * @return orderBy 的内容，返回 null 或者 空字符串，表示不参与排序
     */
    protected String buildOrderBy(String sortKey, String sortType, String defaultOrderBy) {
        sortKey = StringUtil.camelToUnderline(sortKey);
        return ControllerUtil.buildOrderBy(sortKey, sortType, defaultOrderBy);
    }

    protected Page<M> queryPage(Page<M> page, QueryWrapper queryWrapper) {
        return service.page(page, queryWrapper);
    }

    protected Result onSaveOrUpdateBefore(M entity, boolean isSave) {
        return null;
    }

    protected void onSaveOrUpdateAfter(M entity, boolean isSave) {
        //void
    }

    protected Result onRemoveBefore(Collection<Serializable> ids) {
        return null;
    }

    protected void onRemoveAfter(Collection<Serializable> ids) {
        //void
    }

}
