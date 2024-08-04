package com.non.k4r.core.service.impl;

import com.mybatisflex.spring.service.impl.ServiceImpl;
import com.non.k4r.core.entity.Memos;
import com.non.k4r.core.service.MemosService;
import com.non.k4r.core.mapper.MemosMapper;
import org.springframework.stereotype.Service;

/**
 *  服务层实现。
 *
 * @author root
 * @since 2024-08-02
 */
@Service
public class MemosServiceImpl extends ServiceImpl<MemosMapper, Memos> implements MemosService {

}
