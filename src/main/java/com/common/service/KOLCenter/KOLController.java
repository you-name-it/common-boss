package com.common.service.KOLCenter;

import com.baomidou.mybatisplus.mapper.Condition;
import com.baomidou.mybatisplus.plugins.Page;
import com.common.service.KOLCenter.entity.GlobalKOL;
import com.common.service.KOLCenter.service.GlobalKOLService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * Date:2018/12/19
 * Author:QR.Huang
 */
@Controller
@RequestMapping("/kol")
public class KOLController {


    @Autowired
    private GlobalKOLService globalKOLService;

    @GetMapping("submitSuccess")
    public String submitSuccess(){

        return "kol/submitSuccess";
    }

    @GetMapping("{kolid}/kolInfo")
    public String info (HttpServletResponse response,Map<String,Object> map, @PathVariable("kolid") String kolid){

        GlobalKOL kolinfo = this.globalKOLService.selectOne(Condition.instance().eq("KOLID", kolid));

        if(kolinfo.getPlatform()!=null){

            if(!kolinfo.getPlatform().contains(" ")){
                String[] temp = kolinfo.getPlatform().split("，");
                for (int i = 0; i<temp.length;i++){
                    map.put(temp[i].toUpperCase(),true);
                }
            }else{
                String[] temp = kolinfo.getPlatform().split(" ");
                for (int i = 0; i<temp.length;i++){
                    map.put(temp[i].toUpperCase(),true);
                }
            }


        }

        map.put("kolInfo",kolinfo);

        return "kol/kolInfo";
    }

    @GetMapping("{state}/listUI")
    public String kolInfo (HttpServletResponse response , Map<String , Object> map,@PathVariable("state") String state){

        map.put("state",state);

            Integer page = 1;
            Integer limit = 8;

        if(!"".equals(state)){
            if(state.equals("China")){
                state = "中国";
            }else if(state.equals("US")){
                state = "美国";
            }else if(state.equals("Korea")){
                state = "韩国";
            }else if(state.equals("Japan")){
                state = "日本";
            }else if(state.equals("Brazil")){
                state = "印度尼西亚";
            }else if(state.equals("Vietnam")){
                state = "越南";
            }else if(state.equals("Thailand")){
                state = "泰国";
            }
        }

        Page<GlobalKOL> globalKOLPage = this.globalKOLService.selectKolPage(new Page<>(1, 5),state);

        map.put("curr",1);
        map.put("count", globalKOLPage.getTotal());

        return "kol/kolBuyerlistUI";
    }

    /*
    * state
    * */
    @GetMapping("{state}/list")
    @ResponseBody
    public Object list(HttpServletRequest request , @PathVariable("state") String state,@RequestParam(value = "page",required = false) Integer page , @RequestParam(value = "limit",required = false) Integer limit){


        if(page==null){
            page = 1;
        }

        if (limit == null){
            limit = 8;
        }

        if(!"".equals(state)){
            if(state.equals("China")){
                state = "中国";
            }else if(state.equals("US")){
                state = "美国";
            }else if(state.equals("Korea")){
                state = "韩国";
            }else if(state.equals("Japan")){
                state = "日本";
            }else if(state.equals("Brazil")){
                state = "印度尼西亚";
            }else if(state.equals("Vietnam")){
                state = "越南";
            }else if(state.equals("Thailand")){
                state = "泰国";
            }
        }


        Map<String , Object> parameters = new HashMap<>();

        Page<GlobalKOL> globalKOLPage = this.globalKOLService.selectKolPage(new Page<GlobalKOL>(page, limit),state);

        parameters.put("code", 0);
        parameters.put("msg", 0);
        parameters.put("count", globalKOLPage.getTotal());
        parameters.put("data", globalKOLPage.getRecords());
        return parameters;
        //return "kol/kolBuyer-China";

    }



}
