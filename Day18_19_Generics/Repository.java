import java.util.ArrayList;
import java.util.List;

/**
 * A generic repository for storing any type of entity.
 *
 * @param <T> The type of the entity
 */
public class Repository<T> {
    
    private List<T> entities;

    /**
     * Constructor for Repository.
     */
    public Repository() {
        this.entities = new ArrayList<>();
    }

    /**
     * Adds an entity to the repository.
     * @param entity the entity to add
     */
    public void add(T entity) {
        entities.add(entity);
    }

    /**
     * Removes an entity from the repository.
     * @param entity the entity to remove
     * @return true if removed, false otherwise
     */
    public boolean remove(T entity) {
        return entities.remove(entity);
    }

    /**
     * Gets all entities stored in the repository.
     * @return the list of entities
     */
    public List<T> getAll() {
        return entities;
    }
}
