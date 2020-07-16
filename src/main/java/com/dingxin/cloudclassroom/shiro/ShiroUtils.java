package com.dingxin.cloudclassroom.shiro;

import com.dingxin.cloudclassroom.entity.CasEmploys;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author jianghuaidi
 * @email jianghuaidi@szdxsoft.com
 * @date 2020/7/16
 * @desc shiro工具类
 */
public class ShiroUtils {

    public static Session getSession() {
        return SecurityUtils.getSubject().getSession();
    }

    public static Subject getSubject() {
        return SecurityUtils.getSubject();
    }

    public static Object getUserEntity() {
        return SecurityUtils.getSubject().getPrincipal();
    }

    /**
     * 获取当前用户 id
     *
     * @return 当前用户 id
     */
    public static String getUserId() {
        Object object = getUserEntity();
        if (object instanceof CasEmploys) {
            CasEmploys casEmploys = (CasEmploys) object;
            return casEmploys.getSid();
        }
        return null;
    }

    /**
     * 获取当前用户 名称
     *
     * @return 当前用户名称
     */
    public static String getUserName() {
        Object object = getUserEntity();
        if (object instanceof CasEmploys) {
            CasEmploys casEmploys = (CasEmploys) object;
            return casEmploys.getName();
        }
        return null;
    }

    /**
     * 获取当前用户 手机号码
     *
     * @return 当前用户手机号码
     */
    public static String getPhone() {
        Object object = getUserEntity();
        if (object instanceof CasEmploys) {
            return ((CasEmploys) object).getPhoneNum();
        }
        return null;
    }

    /**
     * 获取当前用户部门名称
     *
     * @return 当前用户部门名称
     */
    public static String getDepartmentName() {
        Object object = getUserEntity();
        if (object instanceof CasEmploys) {
            CasEmploys casEmploys = (CasEmploys) object;
            return casEmploys.getDepts();
        }
        return null;
    }

    /**
     * 获取当前用户部门名称
     *
     * @return 当前用户部门名称
     */
    public static List<String> getDepartmentCodes() {
        List<String> sydmList = new ArrayList<>();
        Object object = ShiroUtils.getUserEntity();
        if (object instanceof CasEmploys) {
            CasEmploys casEmploys = (CasEmploys) object;
            String sydms = casEmploys.getDepts();
            if (sydms != null && sydms.length() > 2) {
                if (sydms.contains("[") && sydms.contains("]")) {
                    sydms = sydms.substring(1, casEmploys.getDepts().length() - 1);
                }
                if (StringUtils.isNotEmpty(sydms)) {
                    sydmList = Arrays.asList(sydms.split(","));
                }
            }
        }
        return sydmList;
    }

    public static void setSessionAttribute(Object key, Object value) {
        getSession().setAttribute(key, value);
    }

    public static Object getSessionAttribute(Object key) {
        return getSession().getAttribute(key);
    }

    public static boolean isLogin() {
        return SecurityUtils.getSubject().getPrincipal() != null;
    }

    public static void logout() {
        SecurityUtils.getSubject().logout();
    }

}
