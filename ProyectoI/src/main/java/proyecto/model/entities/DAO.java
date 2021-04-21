package proyecto.model.entities;

import java.util.List;
/**
 *
 * @author GONCAR4
 */
public interface DAO<K, V> { // DAO (Data Access Object Interface)

    public List<V> listAll();

    // CRUD         (IMEC)
    // C(reate)     I(nsertar)
    // R(etrieve)   C(onsultar)
    // U(pdate)     M(odificar)
    // D(elete)     E(liminar)
    
    public void add(K id, V valor);

    public V recover(K id,K contra);

    public void update(K id, V valor);

    public void delete(K id);

}