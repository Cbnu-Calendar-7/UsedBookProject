package calendar7.usedbookproject.FileUpload;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.FileSystemUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.stream.Stream;



@Service
public class FileSystemStorageService implements StorageService {

	private final Path rootLocation;  // 파일 경로 객체 파일의 경로는 StorageProperties.java 에 있다.

	@Autowired
	public FileSystemStorageService(StorageProperties properties)
	{	// 파일 경로 객체 생성
		this.rootLocation = Paths.get(properties.getLocation());
	}

	//파일 저장
	@Override
	public void store(MultipartFile file)
	{
		try {
			// 파일이 비어있으면(클라이언트의 post request 내용이 null) 예외
			if (file.isEmpty())
			{
				throw new StorageException("Failed to store empty file.");
			}

			// resolve - 고정된 루트 경로에 부분 경로를 추가한다.
			// 여기서는 파일이 저장된 디렉터리에 파일 이름을 추가시켜줌.
			// 그리고 절대 경로로 변환해서 destinationFile에 저장.
			Path destinationFile = this.rootLocation.resolve(
							Paths.get(file.getOriginalFilename()))
					.normalize().toAbsolutePath();


			// 보안 체크.
			if (!destinationFile.getParent().equals(this.rootLocation.toAbsolutePath()))
			{
				throw new StorageException("Cannot store file outside current directory.");
			}


			// 파일을 저장. 해당하는 이름의 파일이 이미 있으면 덮어쓴다.
			try (InputStream inputStream = file.getInputStream())
			{
				Files.copy(inputStream, destinationFile, StandardCopyOption.REPLACE_EXISTING);
			}
		}
		catch (IOException e) {throw new StorageException("Failed to store file.", e);}

	}

	// 경로에 저장된 파일들의 스트림을 전달.
	@Override
	public Stream<Path> loadAll()
	{
		try
		{
			return Files.walk(this.rootLocation, 1)
					.filter(path -> !path.equals(this.rootLocation))
					.map(this.rootLocation::relativize);
		}
		catch (IOException e) {
			throw new StorageException("Failed to read stored files", e);
		}

	}

	// filename과 같은 파일명의 경로를 반환
	@Override
	public Path load(String filename)
	{
		return rootLocation.resolve(filename);
	}

	// filename과 같은 파일이름을 가진 파일 리소스를 반환
	@Override
	public Resource loadAsResource(String filename) {
		try
		{
			// 경로에 파일을 받아온다.
			Path file = load(filename);
			Resource resource = new UrlResource(file.toUri());

			// 파일이 null이 아니거나, 읽을 수 있으면 반환.
			if (resource.exists() || resource.isReadable()) return resource;
			else throw new StorageFileNotFoundException("Could not read file: " + filename);

		}
		catch (MalformedURLException e)
		{
			throw new StorageFileNotFoundException("Could not read file: " + filename, e);
		}
	}


	// 경로에 저장된 파일 전부 삭제
	@Override
	public void deleteAll()
	{
		FileSystemUtils.deleteRecursively(rootLocation.toFile());
	}

	// 초기화
	@Override
	public void init()
	{
		// 경로에 디렉터리 생성.
		try
		{
			Files.createDirectories(rootLocation);
		}
		catch (IOException e)
		{
			throw new StorageException("Could not initialize storage", e);
		}
	}
}
