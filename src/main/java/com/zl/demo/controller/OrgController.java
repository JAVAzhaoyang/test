package com.zl.demo.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.google.common.base.Strings;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.zl.demo.base.BaseController;
import com.zl.demo.entity.MasterMember;
import com.zl.demo.entity.Member;
import com.zl.demo.entity.Org;
import com.zl.demo.service.MasterMemberService;
import com.zl.demo.service.MemberService;
import com.zl.demo.service.OrgService;
import com.zl.demo.utils.ListSort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
public class OrgController extends BaseController {

    @Autowired
    private OrgService orgService;
    @Autowired
    private MasterMemberService masterMemberService;
    @Autowired
    private MemberService memberService;

    /**
     * 机构管理组织树
     */
    @RequestMapping(value = "/org/tree")
    @ResponseBody
    public List<Map<String, Object>> orgTree(@RequestParam(required = false) String id) {
        List<Map<String, Object>> result = Lists.newArrayList();
        List<Org> orgList = Lists.newArrayList();
        Map<String, Object> root = Maps.newHashMap();
        //如果id为空,显示总公司名称
        if (StringUtils.isEmpty(id)) {
            //根据用户登录信息,判断用户的归属营业部
            Org org = orgService.getById(getCurrentUser().getOrgId());
            //查询公司下的所有子公司
            orgList = orgService.getSubset(org.getComcode());
            root.put("id", org.getId());
            root.put("name", org.getName());
            root.put("code", org.getComcode());
            root.put("isParent", true);
        } else {
            orgList = orgService.getSubset(orgService.getById(id).getComcode());
        }

        List<Map<String, Object>> sub = Lists.newArrayList();
        if (orgList != null) {
            for (Org org : orgList) {
                Map<String, Object> map = Maps.newHashMap();
                map.put("id", org.getId());
                map.put("code", org.getComcode().trim());
                map.put("name", org.getName());
                if (orgService.getSubset(org.getPcode()).size() == 0 || orgService.getSubset(org.getPcode()) == null) {
                    map.put("isParent", false);
                } else {
                    map.put("isParent", true);
                }
                sub.add(map);
            }
        }
        ListSort.sortByIntByDesc(sub, "code");
        if (StringUtils.isEmpty(id)) {
            root.put("children", sub);
            result.add(root);
        } else {
            return sub;
        }
        ListSort.sortByIntByASC(result, "code");
        return result;
    }



    // 人员查询
    @RequestMapping(value = "/member/select")
    @ResponseBody
    public List<Map<String, Object>> orgMasterSelect(@RequestParam String keyword) {
        System.out.println("接受的值keyword为: "+keyword);
      //  List<Member> memberList = memberService.findByNameLikeOrCode(keyword);
        List<Member> memberList = memberService.list(new QueryWrapper<Member>().lambda()
                .eq(Member::getDeleteflag,0)
                .like(Member::getName,keyword));
        List<Map<String, Object>> list = new ArrayList<>();
        if(memberList!=null){
            for (Member member:memberList) {
                Map<String, Object> map = Maps.newHashMap();
                map.put("memberId", member.getId());
                map.put("memberName", member.getName());
                map.put("memberCode", member.getComcode());
                String orgId = member.getOrgId();
                if (orgId == null) {
                    map.put("memberComName", "");
                } else {
                    map.put("memberComName", orgService.getById(orgId).getName());
                }
                list.add(map);
            }
        }
        return list;
    }


    /**
     * 权限查询
     *
     * @param id   机构Id
     * @param type 0：系统管理员 1:业务岗 2:运维岗
     * @return
     */
    @RequestMapping("/org/permission/query")
    @ResponseBody
    public List<Map<String, Object>> orgPermissionQuery(@RequestParam String id, @RequestParam int type) {
        Org org = orgService.getById(id);
        List<Map<String, Object>> mapList = masterMemberService.listMaps(new QueryWrapper<MasterMember>().lambda()
                .eq(MasterMember::getDeleteflag, 0)
                .eq(MasterMember::getType, type)
                .eq(MasterMember::getOrgId, id));
        List<Map<String, Object>> result = Lists.newArrayList();
        if (mapList == null || mapList.size() == 0) {
            return result;
        }
        for (Map mm : mapList) {
            Map<String, Object> map = Maps.newHashMap();
            Member member = memberService.getById(String.valueOf(mm.get("member_id")));
            if (member != null) {
                map.put("memberId", member.getId());
                map.put("memberName", member.getName());
                map.put("memberCode", member.getComcode());
                String orgId = member.getOrgId();
                if (!Strings.isNullOrEmpty(orgId)) {
                    map.put("memberComName", orgService.getById(orgId).getName());
                }
                result.add(map);
            }
        }
        return result;
    }


    /**
     * 删除权限
     *
     * @param type     0：系统管理员 1:业务岗 2:运维岗
     * @param id       机构Id
     * @param memberId 人员Id
     * @return
     */
    @RequestMapping("/org/permission/delete")
    @ResponseBody
    public int orgPermissionDelete( @RequestParam String memberId, @RequestParam String id,@RequestParam int type) {
        int status = masterMemberService.orgPermissionDelete(memberId, id, type);
        logger.info("【当前操作人：{}】删除人员权限,人员ID{}", getCurrentUser().getName(), memberId);
        return status;
    }


    /**
     * 增加权限
     *
     * @param type
     *            0：系统管理员 1:业务岗 2:运维岗
     * @param id
     *            机构ID
     * @param memberId
     *            人员ID
     * @return
     */
    @RequestMapping(value = "/org/permission/set")
    @ResponseBody
    public int orgPermissionSet( @RequestParam String memberId,@RequestParam String id,@RequestParam int type) {
        int status = masterMemberService.orgPermissionSet(getCurrentUser(),memberId,id,type);
        logger.info("【当前操作人：{}】添加人员权限,人员ID{}", getCurrentUser().getName(), memberId);
        return status;
    }

}
