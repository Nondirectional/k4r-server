package com.non.k4r.core.controller;

import com.non.k4r.core.entity.Records;
import com.non.k4r.core.service.RecordsService;
import com.non.k4r.framework.base.BaseCurdController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *  控制层。
 *
 * @author root
 * @since 2024-08-02
 */
@RestController
@RequestMapping("/records")
public class RecordsController extends BaseCurdController<RecordsService, Records> {
    public RecordsController(RecordsService service) {
        super(service);
    }
}