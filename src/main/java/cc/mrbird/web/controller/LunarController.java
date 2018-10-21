package cc.mrbird.web.controller;

import cc.mrbird.common.annotation.Log;
import cc.mrbird.common.controller.BaseController;
import cc.mrbird.common.domain.ResponseBo;
import cc.mrbird.common.util.HttpUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class LunarController extends BaseController {

    private Logger log = LoggerFactory.getLogger(this.getClass());

    @Log("勇气号控制")
    @RequestMapping("lunar")
    @RequiresPermissions("weather:list")
    public String weather() {
        return "web/weather/weather";
    }

    @RequestMapping("lunar/forward")
    @ResponseBody
    public ResponseBo queryLunar(String areaId) {
        try {
            String data = HttpUtils.sendGet("http://192.168.1.66:9900/forward", "cityIds=" + areaId);
            return ResponseBo.ok(data);
        } catch (Exception e) {
            log.error("lunar控制失败", e);
            return ResponseBo.error("lunar控制失败");
        }
    }

    @RequestMapping("lunar/back")
    @ResponseBody
    public ResponseBo queryLunar1(String areaId) {
        try {
            String data = HttpUtils.sendGet("http://192.168.1.66:9900/back", "cityIds=" + areaId);
            return ResponseBo.ok(data);
        } catch (Exception e) {
            log.error("lunar控制失败", e);
            return ResponseBo.error("lunar控制失败");
        }
    }

    @RequestMapping("lunar/left")
    @ResponseBody
    public ResponseBo queryLunar2(String areaId) {
        try {
            String data = HttpUtils.sendGet("http://192.168.1.66:9900/right", "cityIds=" + areaId);
            return ResponseBo.ok(data);
        } catch (Exception e) {
            log.error("lunar控制失败", e);
            return ResponseBo.error("lunar控制失败");
        }
    }

    @RequestMapping("lunar/right")
    @ResponseBody
    public ResponseBo queryLunar3(String areaId) {
        try {
            String data = HttpUtils.sendGet("http://192.168.1.66:9900/left", "cityIds=" + areaId);
            return ResponseBo.ok(data);
        } catch (Exception e) {
            log.error("lunar控制失败", e);
            return ResponseBo.error("lunar控制失败");
        }
    }

    @RequestMapping("lunar/headup")
    @ResponseBody
    public ResponseBo queryLunar4(String areaId) {
        try {
            String data = HttpUtils.sendGet("http://192.168.1.66:9900/headup", "cityIds=" + areaId);
            return ResponseBo.ok(data);
        } catch (Exception e) {
            log.error("lunar控制失败", e);
            return ResponseBo.error("lunar控制失败");
        }
    }

    @RequestMapping("lunar/headdown")
    @ResponseBody
    public ResponseBo queryLunar5(String areaId) {
        try {
            String data = HttpUtils.sendGet("http://192.168.1.66:9900/headdown", "cityIds=" + areaId);
            return ResponseBo.ok(data);
        } catch (Exception e) {
            log.error("lunar控制失败", e);
            return ResponseBo.error("lunar控制失败");
        }
    }

}
