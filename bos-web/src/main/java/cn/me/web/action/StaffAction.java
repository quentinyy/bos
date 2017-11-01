package cn.me.web.action;

import cn.me.domain.Staff;
import cn.me.service.StaffService;
import cn.me.web.action.base.BaseAction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class StaffAction extends BaseAction<Staff>{
    @Autowired
    private StaffService staffService;

    public String add() throws Exception {
        String row = staffService.add(model);
        return LIST;
    }
}
