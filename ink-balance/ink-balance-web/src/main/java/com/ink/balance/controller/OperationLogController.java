
/**
 * <p> Copyright (c) 2015-2025 银客网.</p>
 * <p>All Rights Reserved. 保留所有权利. </p>
 */
package com.ink.balance.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ink.base.BaseController;

/**
 * @author
 * @version 1.0
 * @since 1.0
 */

@RequestMapping("OperationLog")
@Controller
public class OperationLogController extends BaseController {
    //默认多列排序,example: username desc,createTime asc
    protected static final String DEFAULT_SORT_COLUMNS = null;
    protected static final String LIST_JSP = "WEB-INF/views/OperationLog/list";
    protected static final String CREATE_JSP = "WEB-INF/views/OperationLog/create";
    protected static final String EDIT_JSP = "WEB-INF/views/OperationLog/edit";
    protected static final String SHOW_JSP = "WEB-INF/views/OperationLog/show";
}
