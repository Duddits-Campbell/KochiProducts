package logic;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Paths;
import java.util.Random;

import javax.servlet.http.Part;

public class FileManipulator {
	final String SAVE_DIR = "/Users/thisprodigious/git/KochiProducts/WebContent/images/";

	public boolean deleteFile(String file_name) {
		File file = new File(getFileSavePath(file_name));
		if (file.exists()) {
			if (file.delete()) {
				System.out.println("ファイルを削除しました");
			} else {
				System.out.println("ファイルの削除に失敗しました");
			}
		} else {
			System.out.println("ファイルが見つかりません");
		}
		return true;

	}

	public String saveFile(Part part) throws IOException {
		// ファイルアップロード
		String file_name = "no_image.png";
		if (part != null) {
			String upload_name = Paths.get(part.getSubmittedFileName()).getFileName().toString();
			String extension  = getExtension(upload_name);

			do {
				file_name = random_string(20) + extension;
			}while(new File(getFileSavePath(file_name)).exists());

			InputStream file_content = part.getInputStream();
			FileOutputStream outputStream = new FileOutputStream(new File(getFileSavePath(file_name)));
			int read = 0;
			byte[] bytes = new byte[1024];
			while ((read = file_content.read(bytes)) != -1) {
				outputStream.write(bytes, 0, read);
			}
			outputStream.close();
			System.out.println(getFileSavePath(file_name) + "を保存しました。");
		}else {
			System.out.println("ファイルがアップロードされていません。");
		}
		return file_name;
	}

	private String getExtension(String name) {
		return name.substring(name.lastIndexOf("."));
	}

	private String getFileSavePath(String name) {
		return SAVE_DIR + name;
	}

	public String random_string(int length) {
		String CHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
        StringBuilder string = new StringBuilder();
        Random rnd = new Random();
        while (string.length() < length) { // length of the random string.
            int index = (int) (rnd.nextFloat() * CHARS.length());
            string.append(CHARS.charAt(index));
        }
        return string.toString();
	}
}
