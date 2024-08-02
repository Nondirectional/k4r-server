package com.non.k4r.core.controller;

import com.non.k4r.core.entity.Memos;
import com.non.k4r.core.service.MemosService;
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
@RequestMapping("/memos")
public class MemosController extends BaseCurdController<MemosService, Memos> {
    public MemosController(MemosService service) {
        super(service);
    }
}