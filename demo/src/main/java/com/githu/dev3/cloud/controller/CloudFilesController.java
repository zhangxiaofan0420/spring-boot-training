package com.githu.dev3.cloud.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.githu.dev3.cloud.entity.CloudFiles;
import com.githu.dev3.cloud.repository.CloudFilesRepository;

/**
 * 
 * Cloud Files Controller
 * @author Daniel Z Zhou
 *
 */
@RestController
@RequestMapping(value = "cloudfiles")
public class CloudFilesController {
	
	@Autowired
	private CloudFilesRepository cloudFilesRepository;
	
	@PostMapping(path = "add")
    public void addCloudFiles(CloudFiles cloudFiles) {
		cloudFilesRepository.save(cloudFiles);
    }
	
	@DeleteMapping(path = "delete")
    public void deleteCloudFiles(Long id) {
		cloudFilesRepository.delete(id);
    }
	
	@RequestMapping(value="/upload", method = RequestMethod.POST)
    public @ResponseBody String uploadCloudFiles(@RequestParam("file") MultipartFile file,
            HttpServletRequest request) {
		return "success";
	}
	
	@RequestMapping(value = "/download", method = RequestMethod.GET)
    public void downloadCloudFiles(HttpServletResponse response) {
	}
}
