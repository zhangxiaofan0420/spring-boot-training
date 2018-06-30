package com.githu.dev3.cloud.controller;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Date;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.githu.dev3.cloud.entity.CloudFiles;
import com.githu.dev3.cloud.exception.StorageFileNotFoundException;
import com.githu.dev3.cloud.repository.CloudFilesRepository;
import com.githu.dev3.cloud.service.StorageService;

/**
 * 
 * Cloud Files Controller
 * @author Daniel Z Zhou
 *
 */
@Controller
@RequestMapping(value = "cloudfiles")
public class CloudFilesController {
	
	@Autowired
	private StorageService storageService;
	
	@Autowired
	private CloudFilesRepository cloudFilesRepository;
	
	/**
	 * upload init
	 * query files under path
	 * @param model
	 * @return
	 * @throws IOException
	 */
	@GetMapping("/list")
	public String listUploadedFiles(Model model) throws IOException {

		Iterable<CloudFiles> cloudFilesIt = cloudFilesRepository.findAll();
		model.addAttribute("cloudfiles",cloudFilesIt);
		model.addAttribute("files",
				storageService.loadAll()
						.map(path -> MvcUriComponentsBuilder
								.fromMethodName(CloudFilesController.class, "serveFile", path.getFileName().toString())
								.build().toString())
						.collect(Collectors.toList()));

		return "cloud/uploadForm";
	}

	/**
	 * read files from server location
	 * @param filename
	 * @return
	 */
	@GetMapping("/files/{filename:.+}")
	@ResponseBody
	public ResponseEntity<Resource> serveFile(@PathVariable String filename) {

		Resource file = storageService.loadAsResource(filename);
		return ResponseEntity.ok()
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFilename() + "\"")
				.body(file);
	}

	@PostMapping("/uploadfile")
	public String handleFileUpload(@RequestParam("file") MultipartFile file, RedirectAttributes redirectAttributes) {

		String fileName = storageService.store(file);
		
		//add to db
		//filename
		String fileRealName = StringUtils.cleanPath(file.getOriginalFilename());
		CloudFiles cloudFile = new CloudFiles(fileName,fileRealName,new Date(),"user");
		cloudFilesRepository.save(cloudFile);
		
		redirectAttributes.addFlashAttribute("message",
				"You successfully uploaded " + file.getOriginalFilename() + "!");

		return "redirect:/cloudfiles/list";
	}
	
	@RequestMapping(value = "/download/{id}", method = RequestMethod.GET)
    public void downloadCloudFiles(HttpServletResponse response, @PathVariable Long id) {
		CloudFiles singleFile = cloudFilesRepository.findOne(id);
		
		response.setHeader("content-type", "application/octet-stream");
		response.setContentType("application/octet-stream");
		response.setHeader("Content-Disposition", "attachment;filename=" + singleFile.getFileName());
        byte[] buff = new byte[1024];
        BufferedInputStream bis = null;
        OutputStream os = null;
        try {
          os = response.getOutputStream();
          bis = new BufferedInputStream(storageService.loadAsResource(singleFile.getFileName()).getInputStream());
          int i = bis.read(buff);
          while (i != -1) {
            os.write(buff, 0, buff.length);
            os.flush();
            i = bis.read(buff);
          }
        } catch (IOException e) {
          e.printStackTrace();
        } finally {
          if (bis != null) {
            try {
              bis.close();
            } catch (IOException e) {
              e.printStackTrace();
            }
          }
        }
	}
	
	@DeleteMapping(path = "/delete/{id}")
    public void deleteCloudFiles(@PathVariable Long id) {
		cloudFilesRepository.delete(id);
    }
	
	@ExceptionHandler(StorageFileNotFoundException.class)
	public ResponseEntity<?> handleStorageFileNotFound(StorageFileNotFoundException exc) {
		return ResponseEntity.notFound().build();
	}
}
