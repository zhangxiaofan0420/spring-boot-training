package com.githu.dev3.cloud.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.githu.dev3.cloud.entity.CloudFiles;

@Controller
@RequestMapping(value = "cloud")
public class CloudFilesPageController {
	
	@RequestMapping("/list")
    public String list(Model model) {
        List<CloudFiles> cloudFilesList = new ArrayList<CloudFiles>();
        for (int i = 0; i <10; i++) {
        	cloudFilesList.add(new CloudFiles(new Long(i), "文件"+i,new Date(),"user"+i));
        }
        
        model.addAttribute("cloudFiles", cloudFilesList);
        return "cloud/index";
    }
}
