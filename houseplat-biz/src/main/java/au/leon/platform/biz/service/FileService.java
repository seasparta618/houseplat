package au.leon.platform.biz.service;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

public interface FileService {
	public List<String> getImgPath(List<MultipartFile> files) ;

	File saveToLocal(MultipartFile file, String filePath) throws IOException;
}
