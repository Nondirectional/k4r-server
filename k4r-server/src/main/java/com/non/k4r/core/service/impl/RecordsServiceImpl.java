package com.non.k4r.core.service.impl;

import com.mybatisflex.spring.service.impl.ServiceImpl;
import com.non.k4r.core.entity.Records;
import com.non.k4r.core.service.RecordsService;
import com.non.k4r.core.mapper.RecordsMapper;
import org.springframework.stereotype.Service;

/**
 *  服务层实现。
 *
 * @author root
 * @since 2024-08-02
 */
@Service
public class RecordsServiceImpl extends ServiceImpl<RecordsMapper, Records> implements RecordsService {

}
