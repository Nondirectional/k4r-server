package com.non.k4r.core.controller;

import com.non.k4r.core.entity.Schedules;
import com.non.k4r.framework.base.BaseCurdController;
import com.non.k4r.core.service.SchedulesService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *  控制层。
 *
 * @author root
 * @since 2024-08-02
 */
@RestController
@RequestMapping("/schedules")
public class SchedulesController extends BaseCurdController<SchedulesService, Schedules> {
    public SchedulesController(SchedulesService service) {
        super(service);
    }
}