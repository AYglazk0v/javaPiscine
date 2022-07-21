import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Collectors;
import java.util.Scanner;
import java.util.List;
import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;;

public class Program{
	
	private static void printFileSize(long size){
			System.out.println("" + 1.0 * (size/1024) + " KB");
	}

	private static void mv(Path path, String srcPath, String destination) throws IOException {
        Path src = Paths.get(path + "/" + srcPath).normalize();
        Path dst = Paths.get(path + "/" + destination).normalize();

        if (Files.isRegularFile(src)) {
            if (Files.isDirectory(dst))
                dst = Paths.get(dst + "/" + src.getFileName()).normalize();
            Files.move(src, dst, REPLACE_EXISTING);
        } else {
            System.out.println("Incorect path!");
        }
    }

	private static Path cd(Path path, String destDir) {
        Path destinationFolder = Paths.get(destDir).normalize();
        if (Files.exists(destinationFolder) && Files.isDirectory(destinationFolder) && Files.isExecutable(destinationFolder)) {
			if (destinationFolder.isAbsolute()) {
				System.out.println(destinationFolder.toString());
        		return (destinationFolder);
			}
			else {
				System.out.println((Paths.get(path + "/" + destDir).normalize()).toString());
            	return (Paths.get(path + "/" + destDir).normalize());
			}
        } else {
            System.out.println("Incorrect path!");
            return (path);
        }
	}

	private static long getFolderSize(File folder) {
		long size = 0;
		for (File file : folder.listFiles()) {
			if (file.isFile()) {
				size += file.length();
			} else if (file.isDirectory()) {
				size += getFolderSize(file);
			}
		}
		return size;
	}


	private static void ls(Path path) throws IOException {
        List<Path> content = Files.walk(path, 1).collect(Collectors.toList());
        for (Path item : content) {
            if (!item.equals(path)) {
				System.out.print(item.getFileName() + " ");
				if (Files.isRegularFile(item)) {
					printFileSize(Files.size(item));
				} else if (Files.isDirectory(item)) {
					printFileSize(getFolderSize(item.toFile()));
				}
			}

        }
    }
	public static void main(String[] args) {
		if (args.length != 1 || !args[0].contains("--current-folder=")) {
			System.err.println("Number of arguments != 1");
			System.exit(-1);
		}
		Path path = Paths.get(args[0]);
		if (args[0].startsWith("--current-folder=")) {
			path = Paths.get(args[0].substring(17));
		}
		if (!path.isAbsolute() || !Files.exists(path)) {
			System.err.println("Incorect path");
			System.exit(-1);
		}
		System.out.println(path);
		try(Scanner line = new Scanner(System.in)){
			String cmd = "";
			while (!cmd.equals("exit"))
			{
				cmd = line.nextLine();
				String[] cmdArgArray = cmd.split(" ");
				if (cmdArgArray[0].equals("mv")) {
					mv(path, cmdArgArray[1], cmdArgArray[2]);
				} else if (cmdArgArray[0].equals("ls")) {
					// ls(path);
					Process process = Runtime.getRuntime().exec(new String[]{"/bin/bash", "-c","ls -lhgo " + path.toString() + " | awk '{print $7, $3}'"});
					StringBuilder output = new StringBuilder();
					BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
					String doughterLine;
					int i = 0;
					while ((doughterLine = reader.readLine()) != null) {
						if (i != 0) {
							output.append(doughterLine + "\n");
						}
						i++;
					}
					int exitVal = process.waitFor();
					if (exitVal == 0) {
						System.out.print(output);
					}
				} else if (cmdArgArray[0].equals("cd")) {
					path = cd(path, cmdArgArray[1]);
				} else if (!cmdArgArray[0].equals("exit")) {
					System.out.println("Sorry, Bro, I't understend U");
				}
			}
			line.close();
		}
		catch (Exception e) {
			System.err.println("Couldn't read");
			System.exit(-1);
		}
	}
}