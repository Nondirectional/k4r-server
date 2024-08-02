package com.non.k4r.core.service.impl;

import com.mybatisflex.spring.service.impl.ServiceImpl;
import com.non.k4r.core.entity.Schedules;
import com.non.k4r.core.mapper.SchedulesMapper;
import com.non.k4r.core.service.SchedulesService;
import org.springframework.stereotype.Service;

/**
 *  服务层实现。
 *
 * @author root
 * @since 2024-08-02
 */
@Service
public class SchedulesServiceImpl extends ServiceImpl<SchedulesMapper, Schedules> implements SchedulesService {

}
