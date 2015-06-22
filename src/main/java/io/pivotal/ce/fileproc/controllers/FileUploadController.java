package io.pivotal.ce.fileproc.controllers;

import org.apache.log4j.Logger;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

/**
 * Created by cq on 21/6/15.
 */
@Controller
public class FileUploadController {

    private static final Logger LOGGER = Logger.getLogger(FileUploadController.class);

    @Autowired
    private AmqpTemplate amqpTemplate;


    @RequestMapping(value="/upload", method= RequestMethod.POST)
    public @ResponseBody
    String handlesFileUpload(@RequestParam("file") MultipartFile file){

        if (!file.isEmpty()) {
            try {
                byte[] bytes = file.getBytes();
                String content = new String(bytes);

                LOGGER.info("You successfully uploaded!");
                amqpTemplate.convertAndSend(content);
                LOGGER.info("File content sent!!!");
                return "File sent for processing!!!";

            } catch (Exception e) {
                return "You failed to upload file  => " + e.getMessage();
            }
        } else {
            return "You failed to upload file because the file was empty.";
        }
    }

}
