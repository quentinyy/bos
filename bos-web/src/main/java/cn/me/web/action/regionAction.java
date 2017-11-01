package cn.me.web.action;

import cn.me.domain.Region;
import cn.me.service.RegionService;
import cn.me.utils.PinYin4jUtils;
import cn.me.web.action.base.BaseAction;
import org.apache.commons.lang.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;

@Controller
@Scope("prototype")
public class regionAction extends BaseAction<Region>{
    private File regionFile;
    @Autowired
    private RegionService regionService;

    public void setRegionFile(File regionFile) {
        this.regionFile = regionFile;
    }

    public String improtXls() throws Exception {
        List<Region> regionList = new ArrayList<Region>();
        HSSFWorkbook workbook = new HSSFWorkbook(new FileInputStream(regionFile));
        HSSFSheet rows = workbook.getSheet("sheet1");
        for (Row row: rows
             ) {
            int rowNum = row.getRowNum();
            if(rowNum==0){
                continue;
            }
            String id = row.getCell(0).getStringCellValue();
            String province = row.getCell(1).getStringCellValue();
            String city = row.getCell(2).getStringCellValue();
            String district = row.getCell(3).getStringCellValue();
            String postcode = row.getCell(4).getStringCellValue();

            Region region = new Region(id,province,city,district,postcode,null,null,null);
            province = province.substring(0, province.length() - 1);
            city = city.substring(0, city.length() - 1);
            district = district.substring(0, district.length() - 1);
            String info = province + city + district;
            String[] headByString = PinYin4jUtils.getHeadByString(info);
            String shortcode = StringUtils.join(headByString);
            //城市编码---->>shijiazhuang
            String citycode = PinYin4jUtils.hanziToPinyin(city, "");

            region.setShortcode(shortcode);
            region.setCitycode(citycode);
            regionList.add(region);
        }
        regionService.saveBeatch(regionList);
        return NONE;
    }
}
