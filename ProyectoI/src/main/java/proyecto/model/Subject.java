package proyecto.model;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author arnoldgq
 */
public class Subject {

    private String idSub;
    private String nameSubj;
    private List<Group> groups;

    public Subject(String ID, String nameS) {
        this.idSub = ID;
        this.nameSubj = nameS;
        groups = new ArrayList<>();
    }

    public Subject() {
        this("", "");
    }

    public void insertGroups(Group gr) {
        groups.add(gr);
    }

    public void deleteGroups(String nrc) {
        for (int i = 0; i < groups.size(); i++) {
            if (groups.get(i).getNrc().equals(nrc)) {
                groups.remove(i);
            }
        }
    }

    public String showGroup(int pos) {
        return groups.get(pos).show();
    }
    
    public String show(){
        String f = "", tn = idSub, tc = nameSubj, numS = Integer.toString(groups.size());
        f = f + "Identificador de curso: " + tn
                + "\n" + "Nombre del curso: " + tc
                + "\n" + "Grupos disponibles: " + numS
                + "\n";
        return f;
    }

    public List<Group> getGroups() {
        return groups;
    }

    public void setGroups(List<Group> groups) {
        this.groups = groups;
    }

    public String getIdSub() {
        return idSub;
    }

    public void setIdSub(String ID) {
        this.idSub = ID;
    }

    public String getNameSubj() {
        return nameSubj;
    }

    public void setNameSubj(String nameSubj) {
        this.nameSubj = nameSubj;
    }

}
