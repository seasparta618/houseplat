package au.leon.platform.biz.service.impl;

import java.io.File;
import java.io.IOException;
import java.time.Instant;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.google.common.collect.Lists;
import com.google.common.io.Files;

import au.leon.platform.biz.service.FileService;

@Service
public class FileServiceImpl implements FileService {

	// set the path, for the nginx static file path
	@Value("${file.path}")
	private String filePath;

	// 将上传文件的列表返回一个文件路径
	// return a path for the files uploaded
	public List<String> getImgPath(List<MultipartFile> files) {
		List<String> paths = Lists.newArrayList();
		files.forEach(file -> {
			// 声明新的file
			File localFile = null;
			if (!file.isEmpty()) {
				try {
					localFile = saveToLocal(file, filePath);
					// only concern the file path, as the database only store
					// the relative path
					String path = StringUtils.substringAfterLast(localFile.getAbsolutePath(), filePath);
					paths.add(path);
				} catch (Exception e) {
					throw new IllegalArgumentException(e);
				}
			}
		});
		return paths;
	}

	@Override
	// 将上传的文件保存到本地，静态文件路径
	public File saveToLocal(MultipartFile file, String filePath) throws IOException {
		//在文件的路径上面加一个时间戳
		File newFile = new File(filePath + "/" + Instant.now().getEpochSecond() + "/" + file.getOriginalFilename());
		//如果文件不存在，则创建上级目录
		if (!newFile.exists()) {
			//创建上级目录
			newFile.getParentFile().mkdirs();
			newFile.createNewFile();
		}
		//借助guava将上传的文件考入new file
		Files.write(file.getBytes(), newFile);
		return newFile;
	}
}
