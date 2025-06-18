package Models;

import java.time.LocalDateTime;
import java.util.HashMap;

public class Commit {
    private static long idGenerator=1;
    private final long id;
    private final String message;
    private final Branch branchSnapshot;

    private final LocalDateTime timestamp;
    public Commit(String message, Branch branchSnapshot) {
        this.id = idGenerator++;
        this.message = message;
        this.branchSnapshot =branchSnapshot;
        this.timestamp = LocalDateTime.now();
    }

    public static long getIdGenerator() {
        return idGenerator;
    }

    public static void setIdGenerator(long idGenerator) {
        Commit.idGenerator = idGenerator;
    }

    public long getId() {
        return id;
    }

    public String getMessage() {
        return message;
    }

    public Branch getBranchSnapshot() {
        return branchSnapshot.deepCopy();
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    @Override
    public String toString() {
        return "Models.Commit{" +
                "id=" + id +
                ", message='" + message + '\'' +
                ", branchSnapshot=" + branchSnapshot +
                ", timestamp=" + timestamp +
                '}';
    }
}
