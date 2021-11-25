package calendar7.usedbookproject;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

@Controller
public class ImageController
{

    @GetMapping(path = "/image")
    public ResponseEntity<Byte[]> getImage(@RequestParam("fileName") String fileName)
    {
        File file = new File("C:\\Users\\byong\\SpringBootProject\\UsedBookProject\\upload-dir\\" + fileName);

        ResponseEntity<Byte[]> result = null;

        try
        {
            HttpHeaders header = new HttpHeaders();
            header.add("Content-type", Files.probeContentType(file.toPath()));
            result = new ResponseEntity(FileCopyUtils.copyToByteArray(file), header, HttpStatus.OK);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

        return result;

    }
}