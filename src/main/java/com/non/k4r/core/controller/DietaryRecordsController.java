package com.non.k4r.core.controller;

import com.non.k4r.core.entity.DietaryRecords;
import com.non.k4r.framework.base.BaseCurdController;
import com.non.k4r.core.service.DietaryRecordsService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *  控制层。
 *
 * @author root
 * @since 2024-08-02
 */
@RestController
@RequestMapping("/dietary-records")
public class DietaryRecordsController extends BaseCurdController<DietaryRecordsService, DietaryRecords> {
    public DietaryRecordsController(DietaryRecordsService service) {
        super(service);
    }
}