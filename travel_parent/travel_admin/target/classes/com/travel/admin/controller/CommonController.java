package com.travel.admin.controller;


import com.travel.admin.service.ICityService;
import com.travel.admin.service.ILevelService;
import com.travel.admin.service.IPriceService;
import com.travel.admin.service.IThemeService;
import com.travel.common.domain.City;
import com.travel.common.domain.Level;
import com.travel.common.domain.PriceArea;
import com.travel.common.domain.Theme;
import com.travel.common.util.ResResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @description: CommonController
 * @date: created by 2020/10/25 22:30
 */
@Controller
public class CommonController {

    @Autowired
    private ICityService cityService;

    @Autowired
    private ILevelService levelService;

    @Autowired
    private IThemeService themeService;

    @Autowired
    private IPriceService priceService;

    /**
     * 得到所有的城市
     * @return
     */
    @PostMapping("/common/getAllCities")
    @ResponseBody
    public ResResult getAllCities(){
        try {
            List<City> cities = cityService.queryAllCity();
            return new ResResult(20000,"操作成功",cities);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResResult(50000,"操作失败");
        }
    }
    /**
     * 得到所有的等级
     * @return
     */

    @PostMapping("/common/getAllLevel")
    @ResponseBody
    public ResResult getAllLevel(){
        try {
            List<Level> levels = levelService.findAllLevel();
            return new ResResult(20000,"操作成功",levels);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResResult(50000,"操作失败");
        }
    }
    /**
     * 得到所有的主题 getAllTheme
     * @return
     */
    @PostMapping("/common/getAllTheme")
    @ResponseBody
    public ResResult getAllTheme(){
        try {
            List<Theme> themes =  themeService.findAllTheme();
            return new ResResult(20000,"操作成功",themes);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResResult(50000,"操作失败");
        }
    }

    /**
     * 得到所有的价格区间 getAllPrice
     * @return
     */
    @PostMapping("/common/getAllPrice")
    @ResponseBody
    public ResResult getAllPrice(){
        try {
            List<PriceArea> prices =  priceService.findAllPriceArea();
            return new ResResult(20000,"操作成功",prices);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResResult(50000,"操作失败");
        }
    }
}
