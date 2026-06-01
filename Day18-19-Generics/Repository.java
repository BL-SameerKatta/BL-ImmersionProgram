import java.util.ArrayList;
import java.util.List;

public class Repository<T> {

    private List<T> records = new ArrayList<>();

    public void save(T record) {
        records.add(record);
    }

    public List<T> findAll() {
        return records;
    }
}