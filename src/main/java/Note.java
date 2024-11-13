import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


public class Note {
    private String name;
    private String creationDate;
    private String lastModifiedDate;

    public Note(String name){
        this.name = name;
        this.creationDate = getCurrentDateTime();
        this.lastModifiedDate = creationDate;
    }

    public void updatedLastModified() {
        this.lastModifiedDate = getCurrentDateTime();
    }
    private String getCurrentDateTime(){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return LocalDateTime.now().format(formatter);
    }

    public String getName() {
        return name;
    }

    public String getCreationDate() {
        return creationDate;
    }

    public String getLastModifiedDate() {
        return lastModifiedDate;
    }
}
