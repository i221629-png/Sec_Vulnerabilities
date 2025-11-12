import java.io.*;
import java.sql.*;
import java.util.*;
import java.util.logging.Logger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import javax.servlet.http.*;

// Issue 1: SQL Injection vulnerability
public class SecurityVulnerabilitiesDemo extends HttpServlet {
    private static final Logger logger = Logger.getLogger(SecurityVulnerabilitiesDemo.class.getName());
    private Connection connection;
    
    // Issue 2: Hard-coded password
    private static final String DB_PASSWORD = "secret123";
    private static final String API_KEY = "ak_1234567890abcdef";
    
    // Issue 3: Insecure deserialization
    public void insecureDeserializationExample(byte[] data) {
        try {
            ByteArrayInputStream bis = new ByteArrayInputStream(data);
            ObjectInputStream ois = new ObjectInputStream(bis);
            Object obj = ois.readObject(); // Insecure deserialization
            ois.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    // Issue 4: SQL Injection in servlet
    public void doGet(HttpServletRequest request, HttpServletResponse response) {
        try {
            String userId = request.getParameter("userId");
            Statement stmt = connection.createStatement();
            String query = "SELECT * FROM users WHERE id = " + userId; // SQL Injection
            ResultSet rs = stmt.executeQuery(query);
            
            while (rs.next()) {
                // Process results
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    // Issue 5: Command Injection
    public void commandInjectionExample(String userInput) {
        try {
            String command = "ls " + userInput; // Command Injection
            Runtime.getRuntime().exec(command);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    // Issue 6: Path Traversal
    public void pathTraversalExample(String fileName) {
        try {
            File file = new File("/uploads/" + fileName); // Path Traversal
            FileInputStream fis = new FileInputStream(file);
            // Process file
            fis.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    // Issue 7: Weak hashing algorithm
    public String weakHashingExample(String password) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5"); // Weak hashing
            byte[] hash = md.digest(password.getBytes());
            return bytesToHex(hash);
        } catch (NoSuchAlgorithmException e) {
            return password;
        }
    }
    
    // Issue 8: Use of insecure random
    public int insecureRandomExample() {
        Random random = new Random(); // Insecure for security purposes
        return random.nextInt();
    }
    
    // Issue 9: Potential null pointer dereference
    public void nullPointerExample(String input) {
        if (input.length() > 0) { // Potential NPE
            System.out.println("Input: " + input);
        }
    }
    
    // Issue 10: Information exposure through logs
    public void logSensitiveData(String username, String password) {
        logger.info("User login attempt - Username: " + username + " Password: " + password); // Info exposure
    }
    
    // Issue 11: Insecure object comparison
    public boolean insecureComparison(String input) {
        return input == "admin"; // String comparison with ==
    }
    
    // Issue 12: Resource leak
    public void resourceLeakExample(String filePath) {
        try {
            FileInputStream fis = new FileInputStream(filePath);
            // Missing finally block to close stream - resource leak
            int data = fis.read();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    // Issue 13: Insecure TLS configuration (simulated)
    public void insecureTLSExample() {
        // In production, this might disable certificate validation
        System.setProperty("com.sun.net.ssl.checkRevocation", "false");
    }
    
    // Issue 14: Mass assignment vulnerability (simplified)
    public class User {
        public String username;
        public String role;
        public boolean isAdmin;
        
        // No input validation - mass assignment
        public void setPropertiesFromMap(Map<String, Object> properties) {
            for (Map.Entry<String, Object> entry : properties.entrySet()) {
                switch (entry.getKey()) {
                    case "username": this.username = (String) entry.getValue(); break;
                    case "role": this.role = (String) entry.getValue(); break;
                    case "isAdmin": this.isAdmin = (Boolean) entry.getValue(); break;
                }
            }
        }
    }
    
    // Issue 15: XSS vulnerability in servlet
    public void doPost(HttpServletRequest request, HttpServletResponse response) {
        try {
            String userComment = request.getParameter("comment");
            PrintWriter out = response.getWriter();
            out.println("<div>" + userComment + "</div>"); // XSS vulnerability
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    // Issue 16: Insecure file permissions
    public void createInsecureFile() {
        try {
            File file = new File("config.properties");
            file.createNewFile();
            file.setReadable(true, false); // World-readable
            file.setWritable(true, false); // World-writable
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    // Issue 17: Use of deprecated unsafe methods
    public void threadStopExample() {
        Thread thread = new Thread(() -> {
            // Some work
        });
        thread.start();
        thread.stop(); // Unsafe deprecated method
    }
    
    // Issue 18: Potential array index out of bounds
    public void arrayOutOfBoundsExample() {
        int[] arr = new int[5];
        for (int i = 0; i <= 5; i++) { // Index 5 is out of bounds
            arr[i] = i;
        }
    }
    
    // Issue 19: Insecure temporary file
    public void insecureTempFileExample() {
        try {
            File tempFile = File.createTempFile("temp", ".tmp"); // Predictable name
            // Use temp file
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    // Issue 20: Weak encryption
    public String weakEncryptionExample(String data) {
        // Simple XOR "encryption" - very weak
        byte[] key = {0x12, 0x34, 0x56, 0x78};
        byte[] bytes = data.getBytes();
        for (int i = 0; i < bytes.length; i++) {
            bytes[i] = (byte) (bytes[i] ^ key[i % key.length]);
        }
        return new String(bytes);
    }
    
    // Helper method
    private String bytesToHex(byte[] bytes) {
        StringBuilder sb = new StringBuilder();
        for (byte b : bytes) {
            sb.append(String.format("%02x", b));
        }
        return sb.toString();
    }
    
    // Issue 21: Unsafe reflection
    public void unsafeReflectionExample(String className) {
        try {
            Class<?> clazz = Class.forName(className);
            Object instance = clazz.newInstance(); // Unsafe reflection
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    // Issue 22: Potential DOS through excessive logging
    public void excessiveLoggingExample(String largeInput) {
        logger.info("Large input received: " + largeInput); // Potential DOS
    }
    
    // Issue 23: Insecure cookie handling
    public void insecureCookieExample(HttpServletResponse response) {
        Cookie cookie = new Cookie("sessionId", "12345");
        cookie.setSecure(false); // Not secure
        cookie.setHttpOnly(false); // Accessible via JavaScript
        response.addCookie(cookie);
    }
    
    public static void main(String[] args) {
        SecurityVulnerabilitiesDemo demo = new SecurityVulnerabilitiesDemo();
        
        // Test some examples
        demo.nullPointerExample(null);
        demo.weakHashingExample("password123");
        demo.insecureRandomExample();
        
        System.out.println("Security vulnerabilities demo completed");
    }
}