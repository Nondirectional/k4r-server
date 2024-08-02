package com.non.k4r.core.controller;

import com.non.k4r.core.entity.Expenses;
import com.non.k4r.core.service.ExpensesService;
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
@RequestMapping("/expenses")
public class ExpensesController extends BaseCurdController<ExpensesService, Expenses> {
    public ExpensesController(ExpensesService service) {
        super(service);
    }
}