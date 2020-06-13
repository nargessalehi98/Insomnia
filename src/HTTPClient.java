import java.io.*;
import java.net.*;
import java.net.http.HttpClient;
import java.nio.charset.StandardCharsets;
import java.util.*;

/**
 * Provide a console http-request-app
 *
 * @author Narges Salehi
 */

public class HTTPClient {
    HttpClient client = HttpClient.newHttpClient(); //creat a http client to connect
    boolean showHeaders; //creat a boolean to check if headers are going to be shown
    boolean saveResponse; //creat a boolean to check if response are going to be saved
    boolean saveRequest; //creat a boolean to check if request are going to be saved
    boolean formData; //creat a boolean to check if request are going to be formData
    boolean setHeaders; //creat a boolean to check if headers are going to be set
    boolean listIsOn = false; //creat a boolean to check if request are showing
    static int statusCode; //save status code
    static String statusMassage; // save status massage
    static String takedTime; // save taked time
    static String takedByte; // save taked byte
    static String word1; //save url with out 'http://'
    static Map<String, List<String>> map; //save Header of request
    static HashMap<String, String> Headers; // save given header by user
    static String stringOfData; // save given data by user as a string
    static HashMap<String, String> Data; //save given data as HashMap


    /**
     * Creat a console app
     *
     * @throws IOException
     * @throws InterruptedException
     */
    public HTTPClient() throws IOException, InterruptedException {
        Headers = new HashMap<>();
        Data = new HashMap<>();
        Scanner scanner = new Scanner(System.in);
        while (true) {
            String method = "GET"; //set default method as get
            String key = null;
            String value = null;
            String name = null;
            String directory = null;
            String command = scanner.nextLine(); //creat a scanner to scan commands
            String[] line = (command.split(" ")); //spilt commands in " "
            ArrayList<String> words = new ArrayList<String>(Arrays.asList(line)); //put commands in an ArrayList
            String URL = "http://" + words.get(1); //save real url
            word1 = words.get(1); //save raw url
            if (words.get(0).equals("jurl")) { //check if given command start with jurl or no
                if (words.get(1).equals("--help") || words.get(1).equals("-h")) {
                    listIsOn = false;
                    System.out.println(
                            "-M or --method " + "," + "select method , can be deleted for GET " +
                                    "\n" + "-H or --headers" + "," + "set request headers " +
                                    "\n" + "-O or --output" + "," + "save body in file" +
                                    "\n" + "-S or --save" + "," + "save request in file " +
                                    "\n" + "-d or --data" + "," + "set massage body as form data " +
                                    "\n" + "-i" + " ," + "showing headers of response " +
                                    "\n" + "end" + " ," + "Exit program " +
                                    "\n" + "create" + " , " + "create a new directory"
                    );
                    continue;
                } else if (words.get(1).equals("list")) {
                    if (Files.isDirectory(words.get(2))) {
                        Files.showRequestList(words.get(2));
                        listIsOn = true;
                    } else
                        System.out.println("This directory is not true");
                    continue;

                } else if (words.get(1).equals("fire") && Files.isDirectory(words.get(2)) && listIsOn) {
                    for (int i = 3; i < words.size(); i++) {
                        if (isInteger(words.get(i))) {
                            int number = Integer.valueOf(words.get(i));
                            Files.showRequest(number, words.get(2));
                        } else {
                            System.out.println("No more Number");
                            break;
                        }
                    }
                    continue;
                } else if (words.get(1).equals("end")) {
                    System.exit(0);
                } else if (words.get(1).equals("create")) {
                    String PathName = words.get(2);
                    Files.makeDirectory(PathName);
                    continue;
                } else {
                    listIsOn = false;
                    if (true) {
                        for (String word : words) {
                            switch (word) {
                                case "-i":
                                    showHeaders = true;
                                    break;
                                case "-O":
                                    int indexOfName = words.indexOf("-O");
                                    if (words.size() - 1 > indexOfName) {
                                        indexOfName++;
                                        if (!words.get(indexOfName).startsWith("-"))
                                            name = words.get(indexOfName);
                                        else {
                                            Date date = new Date();
                                            name = "output_" + date.toString();
                                        }
                                    } else {
                                        Date date = new Date();
                                        name = "output_" + date.toString();
                                    }
                                    saveResponse = true;
                                    break;
                                case "--output":
                                    indexOfName = words.indexOf("-O");
                                    if (words.size() - 1 > indexOfName) {
                                        indexOfName++;
                                        if (!words.get(indexOfName).startsWith("-"))
                                            name = words.get(indexOfName);
                                        else {
                                            Date date = new Date();
                                            name = "output_" + date.toString();
                                        }
                                    } else {
                                        Date date = new Date();
                                        name = "output_" + date.toString();
                                    }
                                    saveResponse = true;
                                    break;

                                case "-S":
                                    int indexOfDirectory = words.indexOf("-S");
                                    indexOfDirectory++;
                                    directory = words.get(indexOfDirectory);
                                    saveRequest = true;
                                    break;
                                case "--save":
                                    indexOfDirectory = words.indexOf("--save");
                                    indexOfDirectory++;
                                    directory = words.get(indexOfDirectory);
                                    saveRequest = true;
                                    break;
                                case "-d":

                                    int indexOfData = words.indexOf("-d");
                                    indexOfData++;
                                    String temp = words.get(indexOfData);
                                    stringOfData = temp;

                                    temp = temp.replace("\"", "");
                                    String[] data1 = temp.split("&");
                                    ArrayList<String> data2 = new ArrayList<String>(Arrays.asList(data1));
                                    for (String s : data2) {
                                        String[] keyValue = s.split("=");
                                        ArrayList<String> keyValue2 = new ArrayList<String>(Arrays.asList(keyValue));
                                        Data.put(keyValue2.get(0), keyValue2.get(1));
                                    }
                                    System.out.println(Data);

                                    formData = true;
                                    break;

                                case "--data":

                                    indexOfData = words.indexOf("--data");
                                    indexOfData++;
                                    temp = words.get(indexOfData);
                                    stringOfData = temp;

                                    temp = temp.replace("\"", "");
                                    data1 = temp.split("&");
                                    data2 = new ArrayList<String>(Arrays.asList(data1));
                                    for (String s : data2) {
                                        String[] keyValue = s.split("=");
                                        ArrayList<String> keyValue2 = new ArrayList<String>(Arrays.asList(keyValue));
                                        Data.put(keyValue2.get(0), keyValue2.get(1));
                                    }

                                    formData = true;
                                    break;

                                case "-H":

                                    int indexOfHeader = words.indexOf("-H");
                                    indexOfHeader++;
                                    temp = words.get(indexOfHeader);
                                    temp = temp.replace("\"", "");
                                    String[] header1 = temp.split(";");
                                    ArrayList<String> header2 = new ArrayList<String>(Arrays.asList(header1));
                                    for (String s : header2) {
                                        String[] keyValue = s.split(":");
                                        ArrayList<String> keyValue2 = new ArrayList<String>(Arrays.asList(keyValue));
                                        Headers.put(keyValue2.get(0), keyValue2.get(1));
                                    }
                                    setHeaders = true;
                                    break;

                                case "--headers":

                                    indexOfHeader = words.indexOf("--headers");
                                    indexOfHeader++;
                                    temp = words.get(indexOfHeader);
                                    temp = temp.replace("\"", "");
                                    header1 = temp.split(";");
                                    header2 = new ArrayList<String>(Arrays.asList(header1));
                                    for (String s : header2) {
                                        String[] keyValue = s.split(":");
                                        ArrayList<String> keyValue2 = new ArrayList<String>(Arrays.asList(keyValue));
                                        Headers.put(keyValue2.get(0), keyValue2.get(1));
                                    }
                                    setHeaders = true;
                                    break;

                                case "-M":
                                    int indexOfMethod = words.indexOf("-M");
                                    indexOfMethod++;
                                    method = words.get(indexOfMethod);
                                    break;
                                case "--method":
                                    indexOfMethod = words.indexOf("--method");
                                    indexOfMethod++;
                                    method = words.get(indexOfMethod);
                                    break;
                                default:
                                    break;
                            }
                        }
                    } else {
                        System.out.println("2 command not found" + "try 'jurl --help' or 'jurl -h' for more information");
                        continue;
                    }
                }
            } else {
                System.out.println("3 command not found" + "try 'jurl --help' or 'jurl -h' for more information");
                continue;
            }
            //send request
            Request(URL, showHeaders, setHeaders, saveRequest, saveResponse, formData
                    , Headers,Data,stringOfData,method,key, value, name, directory);
            //set value to default for new request
            showHeaders = false;
            setHeaders = false;
            saveRequest = false;
            saveResponse = false;
            formData = false;
        }
    }

    /**
     * check if given string is url or not
     * @param url given string
     * @return true of false
     */
    public boolean isURL(String url) {
        try {
            (new java.net.URL(url)).openStream().close();
            return true;
        } catch (Exception ignored) {
        }
        return false;
    }

    /**
     * check if given string is a integer or not
     * @param s given string
     * @return true of false
     */
    public static boolean isInteger(String s) {
        try {
            Integer.parseInt(s);
        } catch (NumberFormatException | NullPointerException e) {
            return false;
        }
        return true;
    }

    /**
     * Get taked time of request
     * @return taked time
     */
    public static String getTakedTime() {
        return takedTime;
    }

    /**
     * Get status code of request
     * @return status code
     */
    public static int getStatusCode() {
        return statusCode;
    }

    /**
     * Send a HTTPRequest with given data
     *
     * @param URL          url
     * @param showHeaders
     * @param setHeaders
     * @param saveRequest
     * @param saveResponse
     * @param formData
     * @param method       method of request
     * @param key          header key
     * @param value        header value
     * @param name         name of file
     * @param directory    to save request in
     * @return string of body
     * @throws IOException
     */
    public static String Request(String URL, boolean showHeaders, boolean setHeaders,
                                 boolean saveRequest, boolean saveResponse, boolean formData,HashMap<String,String> Headers,
                                 HashMap<String,String> Data ,String stringOfData,String method,
                                 String key, String value, String name, String directory) throws IOException {
        long start = System.currentTimeMillis();//start counting time
        URL url = new URL(URL); //creat a url
        HttpURLConnection yc = (HttpURLConnection) url.openConnection(); //open connection
        if (!method.equals("null") && !method.equals("GET")) { //set method default is get
            yc.setRequestMethod(method); //set method
            yc.setDoOutput(true);
        }
        if (setHeaders) { //if set headers is true set given headers by user as headers
            for (String Key : Headers.keySet()) {
                for (String Value : Headers.values()) {
                    if (Headers.get(Key).equals(Value)) {
                        yc.setRequestProperty(Key, Value);
                    }
                }
            }
        }
        if (formData) { // if user give data
            if (word1.contains("urlencoded")) { //check if its url data
                try {
                    stringOfData=stringOfData.replace("{","").replace("}","");
                    int data = stringOfData.getBytes(StandardCharsets.UTF_8).length; //get bytes of data
                    yc.setRequestProperty("Content-Type", "application/x-www-form-urlencoded"); //set properties
                    yc.setRequestProperty("charset", "utf-8");
                    yc.setRequestProperty("Content-Length", Integer.toString(data));
                    try (OutputStream os = yc.getOutputStream()) { //creat stream to write values
                        os.write(stringOfData.getBytes(StandardCharsets.UTF_8));
                        os.flush();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                    yc.disconnect();
                }
            }
            if (word1.contains("formdata")) { //if it is form data
                try {
                    yc.setRequestProperty("Content-Type", "multipart/form-data; boundary=" + "1111"); //set properties
                    BufferedOutputStream request = new BufferedOutputStream(yc.getOutputStream()); //creat a stream
                    bufferOutFormData(Data, "1111", request); //call method of form data set values
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        //start reading body
        StringBuilder Body = new StringBuilder(); // to build body as a string
        BufferedReader in = new BufferedReader(new InputStreamReader(yc.getInputStream())); //stream to get body
        String inputLine; //line to keep body lines
        while ((inputLine = in.readLine()) != null) { //check end of body
            System.out.println(inputLine); //print body in console
            Body.append(inputLine); //creat body
            Body.append("\n");
        }
        in.close(); //close stream
        map = yc.getHeaderFields(); //get headers of request
        if (showHeaders) { //if show headers is true set map value as headers of request
            for (Map.Entry<String, List<String>> entry : map.entrySet()) {
                System.out.println(entry.getKey()
                        + " : " + entry.getValue());
            }
        }
        //count byte of a request
        int byteCount = 0;
        int headerIndex = 0;
        while (true) {
            String key2 = yc.getHeaderFieldKey(headerIndex);
            if (key == null)
                break;
            String value2 = yc.getHeaderField(headerIndex++);
            byteCount += key.getBytes(StandardCharsets.US_ASCII).length
                    + value.getBytes(StandardCharsets.US_ASCII).length + 2;
        }
        byteCount += yc.getHeaderFieldInt("Content-Length", Integer.MIN_VALUE);
        takedByte = String.valueOf(byteCount);
        long end = System.currentTimeMillis();
        takedTime = String.valueOf((end - start) / 1000);
        takedTime += ".";
        takedTime += String.valueOf((end - start) % 1000);
        if (saveRequest) {//if save request is true save request as below pattern
            //first line of file show 'option view' in list
            String topLine = "url: " + URL + " | " + "method: " + method + " | " + "headers: " + key + ": " + value + " | ";
            String content = topLine + "\n" +
                    "body" + Body +
                    "headers" + map + "\n" +
                    "takedTime" + takedTime + "\n" +
                    "statusCode" + yc.getResponseCode() + "\n" +
                    "statusMassage" + yc.getResponseMessage() + "\n" +
                    "byteCount" + byteCount+"\n"+
                    "data" + Data;
                    Files.fileWriterRequest(content, directory, String.valueOf(Files.numberOfFiles(directory)));//write content in given directory
        }
        if (saveResponse) { //if save response is true save response of request
            Files.fileWriterResponse(Body.toString(), name);
        }
        System.out.println(yc.getResponseCode());//print status code
        statusCode = yc.getResponseCode();//set status code
        statusMassage = yc.getResponseMessage();//set status massage
        return Body.toString();//return string of body
    }


    /**
     * Set Values as data im form data
     *
     * @param body                 set of values
     * @param boundary             boundary
     * @param bufferedOutputStream stream to write values
     * @throws IOException
     */
    public static void bufferOutFormData(HashMap<String, String> body, String boundary, BufferedOutputStream bufferedOutputStream) throws IOException {
        for (String key : body.keySet()) {
            bufferedOutputStream.write(("--" + boundary + "\r\n").getBytes());
            if (key.contains("file")) {
                bufferedOutputStream.write(("Content-Disposition: form-data; filename=\"" +
                        (new File(body.get(key))).getName() + "\"\r\nContent-Type: Auto\r\n\r\n").getBytes());
                try {
                    BufferedInputStream tempBufferedInputStream = new BufferedInputStream(new FileInputStream(new File(body.get(key))));
                    byte[] filesBytes = tempBufferedInputStream.readAllBytes();
                    bufferedOutputStream.write(filesBytes);
                    bufferedOutputStream.write("\r\n".getBytes());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else {
                bufferedOutputStream.write(("Content-Disposition: form-data; name=\"" + key + "\"\r\n\r\n").getBytes());
                bufferedOutputStream.write((body.get(key) + "\r\n").getBytes());
            }
        }
        bufferedOutputStream.write(("--" + boundary + "--\r\n").getBytes());
        bufferedOutputStream.flush();
        bufferedOutputStream.close();
    }
}

