import com.sun.net.httpserver.Headers;

import java.io.*;
import java.util.Scanner;

/**
 * Manage requests files
 * @author Narges Salehi
 */
public class Files {
    //creat 2 path for response and requests
    static final String PATH1 = "./requests/";
    static final String PATH2 = "./response/";
    //creat static variable to keep value of requests
    static String Body; //body of massage
    static String byteCount; //byte of request
    static String takedTime; //time of request
    static String statusCode; //status code of request
    static String statusMassage; //status massage of request
    static String HeadersOfMassage; //Headers of request
    static String Data;//data of form data or url

    /*
     * static constructor to creat directories
     */
    static {
        File directory1 = new File(PATH1);
        directory1.mkdirs();//create directory for request
        File directory2 = new File(PATH2);
        directory2.mkdirs(); //create directory for response
    }

    /**
     * Creat new directory by user request
     * @param name of directory
     */
    public static void makeDirectory(String name) {
        //creat a path in root folder
        final String PATH = "./requests/" + name;
        File directory = new File(PATH);// new file in given path
        directory.mkdirs(); //create directory
    }

    /**
     * Write response if asked for
     * @param content of response
     * @param fileName name of file
     * @throws IOException
     */
    public static void fileWriterResponse(String content, String fileName) throws IOException {
        OutputStream out = new FileOutputStream(PATH2 + fileName + ".txt/"); //create a stream
        out.write(content.getBytes()); //write content on file
        out.flush();
    }

    /**
     * Write all the information of request
     * @param content of request
     * @param directory to save in
     * @param fileName path
     * @throws IOException
     */
    public static void fileWriterRequest(String content, String directory, String fileName) throws IOException {
        OutputStream out = new FileOutputStream(PATH1 + directory + "/" + fileName + ".txt/"); //creat a stream
        out.write(content.getBytes()); //write content on file
        out.flush();
    }


    /**
     * Read file if asked
     * @param file to be read
     * @return content of file
     */
    public static String fileReader(File file) {
        StringBuilder line = new StringBuilder(); //create a string builder
        try (InputStream in = new FileInputStream(file)) {
            int content;
            while ((content = in.read()) != -1) {
                line.append((char) content);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return line.toString();
    }

    /**
     * List all the directory
     * @return File[] of directories
     */
    public static File[] showDirectories() {
        File directories = new File(PATH1);
        File[] listOfDirectories = directories.listFiles(); //creat a list of file in given directory
        assert listOfDirectories != null; //check if directory is not empty
        return listOfDirectories;
    }

    /**
     * show all the requests in a directory
     * @param name of directory
     * @return list of requests
     */
    public static File[] showList(String name) {
        File requests = new File(PATH1 + name);
        File[] listOfRequests = requests.listFiles();
        assert listOfRequests != null;
        return listOfRequests;
    }

    /**
     * check if the given name is a directory name or no
     * @param name of directory
     * @return true or false
     */
    public static boolean isDirectory(String name) {
        File directories = new File(PATH1);
        File[] listOfDirectories = directories.listFiles();
        assert listOfDirectories != null;
        for (File file : listOfDirectories) //check out in directory to find given file name
            if (file.getName().equals(name))
                return true;
        return false;
    }

    /**
     * show options list of requests
     * @param Directory given directory
     * @throws FileNotFoundException
     */
    public static void showRequestList(String Directory) throws FileNotFoundException {
        File requests = new File(PATH1 + Directory);
        File[] listOfRequests = requests.listFiles();
        assert listOfRequests != null;
        if (listOfRequests.length == 0)
            System.out.println("No Request yet");
        for (int i = 1; i <= listOfRequests.length; i++) { //count from 1 to the size of file list
            for (File file : listOfRequests) {  //check if present file has the same number as previous loop
                if (file.getName().contains(String.valueOf(i))) {
                    Scanner scanner = new Scanner(new FileInputStream(file));
                    String line = scanner.nextLine().trim();
                    System.out.println(i + " . " + line);
                }
            }
        }
    }

    /**
     * Give number of file in a directory
     * @param directory to check number of file in it
     * @return number of file
     */
    public static int numberOfFiles(String directory) {
        File requests = new File(PATH1 + directory); //Get file in given path
        File[] listOfRequests = requests.listFiles(); //make list of files in given path
        return listOfRequests.length + 1; //return number of file
    }


    /**
     * Show information of a requests
     * @param number Of request in shown list
     * @param directory Directory of request
     */
    public static void showRequest(Integer number, String directory) {
        File requests = new File(PATH1 + directory);
        File[] listOfRequests = requests.listFiles();
        assert listOfRequests != null;
        for (File file : listOfRequests) {
            if (file.getName().equals(String.valueOf(number) + ".txt")) {
                System.out.println(fileReader(file));
            }
        }
    }

    /**
     * Read different value of a requests in a file
     * @param filePath given file address
     * @throws FileNotFoundException
     */
    public static void setMassageValues(String filePath) throws FileNotFoundException {
        File file = new File(filePath);
        Scanner scanner = new Scanner(new FileInputStream(file));
        String line = scanner.nextLine();
        line = scanner.nextLine();
        while (!line.startsWith("headers")) { //read from body word till header
            if (scanner.hasNextLine()) {
                Body += line + "\n";
                line = scanner.nextLine();
            } else
                break;
        }
        Body=Body.replace("body","").replace("[","").replace("]",""); //remove body word
        Body=Body.replace("null","");
        while (!line.startsWith("takedTime")) { //read from headers till takedTime word
            if (scanner.hasNextLine()) {
                HeadersOfMassage += line + "\n"; //set headers
                line = scanner.nextLine();
            } else
                break;
        }
        HeadersOfMassage=HeadersOfMassage.replace("headers","");//remove headers word
        HeadersOfMassage=HeadersOfMassage.replace("null","");
        takedTime = line.replace("takedTime", ""); //set takedTime
        statusCode = scanner.nextLine().replace("statusCode", ""); //set statusCode
        statusMassage=scanner.nextLine().replace("statusMassage",""); //set statusMassage
        byteCount=scanner.nextLine().replace("byteCount",""); //set byteCount
        Data=scanner.nextLine().replace("data",""); //set data
    }
}
